package com.dwq.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtils {
    @Value("${spring.security.jwt.key}")
    String key;
    @Value("${spring.security.jwt.expire}")
    int expire;
    @Resource
    StringRedisTemplate template;


    public boolean invalidateJwt(String headerToken){
        String token = this.convertToken(headerToken);
        if (token == null) return false;
        Algorithm algorithm = Algorithm.HMAC256(key);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        try{
            DecodedJWT jwt=jwtVerifier.verify(token);
            String id = jwt.getId();
            return deleteToken(id,jwt.getExpiresAt());
        }catch (JWTVerificationException e){
            return false;
        }


    }
    private boolean deleteToken(String uuid,Date time){
        if(this.isInvalidToken(uuid)) return false;
        Date now=new Date();
        long expire= Math.max(time.getTime()- now.getTime(),0);
        template.opsForValue().set(Const.JWT_BLACK_LIST+uuid,"",expire, TimeUnit.MILLISECONDS);

        return true;
    }
    private boolean isInvalidToken(String uuid){
        return Boolean.TRUE.equals(template.hasKey(Const.JWT_BLACK_LIST+uuid));
    }
    public DecodedJWT resolveJwt(String headerToken) {
        String token = this.convertToken(headerToken);
        if (token == null) return null;
        Algorithm algorithm = Algorithm.HMAC256(key);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();

        try {
            DecodedJWT verify = jwtVerifier.verify(token);      //检查jwt是否被篡改
            if(this.isInvalidToken(verify.getId()))
                return null;
            Date expiresAt = verify.getExpiresAt();     //判断JWT令牌是否过期
            return new Date().after(expiresAt) ? null : verify; //当前时间过期就返回null，否则返回verify
        } catch (JWTVerificationException e) {
            return null;
        }
    }
    public String createJwt(UserDetails details, int id, String username) {
        Algorithm algorithm = Algorithm.HMAC256(key); //加密算法
        Date expire = this.expireTime();
        return JWT.create()
                .withJWTId(UUID.randomUUID().toString())
                .withClaim("id", id) //用户id
                .withClaim("name", username) //用户名
                .withClaim("authorities", details.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()) //权限
                .withExpiresAt(expire)  //过期时间
                .withIssuedAt(new Date()) //颁发token时间
                .sign(algorithm);  //签名

    }

    public Date expireTime() {   //计算过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, expire * 24);
        return calendar.getTime();
    }


    public UserDetails toUser(DecodedJWT jwt) {  //解析用户信息  转换成UserDetails格式
        Map<String, Claim> claims = jwt.getClaims();
        return User
                .withUsername(claims.get("name").asString())
                .password("******")
                .authorities(claims.get("authorities").asArray(String.class))
                .build();

    }
    public int toId(DecodedJWT jwt){    //解析得到id
        Map<String, Claim> claims = jwt.getClaims();
        return claims.get("id").asInt();
    }

    private String convertToken(String headerToken) {   //判断token是否合法
        if (headerToken == null || !headerToken.startsWith("Bearer "))
            return null;
        return headerToken.substring(7);    //合法，将"Bearer "后的截取
    }
}
