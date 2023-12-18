<template>
  <div style="width: 100vw;height: 100vh;overflow: hidden;display: flex">
    <div style="flex: 1;background-color: #181818">
      <el-image style="width: 100%;height: 100%" fit="cover"
          src="https://th.bing.com/th/id/R.fab23d56ab72d2a56963f5ee81dbbf01?rik=gQuRdX4SCa7hag&riu=http%3a%2f%2fwww.quazero.com%2fuploads%2fallimg%2f140306%2f1-1403061G518.jpg&ehk=KIjmwChD6p%2bUa0tVeEiPQGkYEsbZsVL2FaxZbbtqC9s%3d&risl=&pid=ImgRaw&r=0"></el-image>
    </div>
    <div class="welcome-title">
      <div style="font-size: 30px;font-weight: bold">欢迎来到宠物管理平台</div>
      <div style="margin-top: 10px">在这里你可以看到各种宠物</div>
      <div style="margin-top: 5px">这里也可以因为宠物获取友谊</div>
    </div>
    <div class="right-card">
      <router-view/>
    </div>

  </div>
</template>

<script>
export default {
  name: "WelcomeView.vue"
}
</script>

<style scoped>
.right-card {
  width: 400px;
  z-index: 1;
  background-color: white;
}
.welcome-title {
  position: absolute;
  bottom: 30px;
  left: 30px;
  color: white;
  text-shadow: 0 0 10px black;
}
</style>