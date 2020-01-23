<template>
  <div class="sidebar-items">
      <el-menu-item index="/memo" @click="goTo('/memo')">
        <i class="el-icon-bell"></i>
        <span slot="title">备忘录</span>
      </el-menu-item>
      <el-menu-item index="/file" @click="goTo('/file')">
        <i class="el-icon-folder"></i>
        <span slot="title">传文件</span>
      </el-menu-item>
      <el-menu-item index="/password" @click="goTo('/password')">
        <i class="el-icon-view"></i>
        <span slot="title">密码本</span>
      </el-menu-item>
    <div class="foot-bar">
      <div class="github">
        <input type="submit" onclick="window.open('https://github.com/LiC0ng')"/>
      </div>
      <div class="mail">
        <el-tooltip class="item" effect="dark" content="1040484@qq.com" placement="top">
          <input type="submit"/>
        </el-tooltip>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    methods: {
      goTo(url) {
        if (this.isLogin) {
          this.$router.push({path: url});
        } else {
          this.$message({
            message: "登陆后可用",
            type: "error"
          });
        }
      }
    },
    computed: {
      isLogin() {
        return this.$store.state.isLogin;
      }
    },
  }
</script>

<style>
  .el-menu-item {
    padding-right: 30px !important;
  }

  .foot-bar {
    padding-top: 50%;
  }
  .github {
    width: 50%;
    float: left;
    height: 50px;
  }

  .github input {
    font-size: 0px;
    background-image: url("../assets/image/github.png");
    background-position: center;
    background-repeat: no-repeat;
    background-size: 100% 100%;
    display: inline-block;
    width: 40px;
    height: 40px;
    border-radius: 50%;
    border: 0px solid #7F828B;
    text-align: center;
  }

  .mail {
    width: 50%;
    float: left;
    height: 48px;
  }

  .mail input {
    font-size: 0px;
    background-image: url("../assets/image/mail.png");
    background-size: 100% 100%;
    background-color: #f5f5f5 !important;
    background-position: center;
    background-repeat: no-repeat;
    display: inline-block;
    width: 32px;
    height: 32px;
    border: 0px solid #f5f5f5;
    text-align: center;
    padding: 0px;
  }
</style>
