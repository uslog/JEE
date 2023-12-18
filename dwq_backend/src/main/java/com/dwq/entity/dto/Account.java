package com.dwq.entity.dto;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dwq.entity.BaseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;
@Data
@TableName("dwq_admin")
@AllArgsConstructor
public class Account implements BaseData{
    @TableId(type = IdType.AUTO)
    int id;
    String username;
    String password;
    String email;
    String role;
    Date registerTime;
}
