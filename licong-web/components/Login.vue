<template>
  <div>
    <a class="avator-box">
      <div class="avator" @click="dialogFormVisible = true">
        <img src="../assets/image/avator.png" class="avator_img" alt="">
      </div>
      <el-button v-if="isLogin" type="text" @click="logout">登出</el-button>
      <el-button v-else type="text" @click="dialogFormVisible = true">登录</el-button>
    </a>
    <el-dialog v-if="!isLogin" title="登录" :visible.sync="dialogFormVisible" width="30%" center>
      <el-form>
        <el-form-item label="账号" label-width="50px">
          <el-input v-model="loginname"></el-input>
        </el-form-item>
        <el-form-item label="密码" label-width="50px">
          <el-input type="password" v-model="password" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="login">登 录</el-button>
        <el-button type="success" @click="register">注 册</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  import userApi from '@/api/user'
  import {setUser, getUser, removeUser} from '@/utils/auth'

  export default {
    data() {
      return {
        loginname: '',
        password: '',
        dialogFormVisible: false,
      }
    },
    methods: {
      login() {
        userApi.login(this.loginname, this.password).then(res => {
          if (res.data.flag) {
            //保存用户信息
            setUser(res.data.data.token);

            this.$router.go(0);

          } else {
            this.$message({
              message: res.data.message,
              type: "error"
            }),
              this.loginname = '',
              this.password = ''
          }
        })
      },
      logout() {
        removeUser();
        location.href = '/' //首页
      },
      register() {
        this.$message("暂不开放注册");
      },
    },
    computed: {
      isLogin() {
        return this.$store.state.isLogin;
      }
    },
    mounted() {
      if (getUser().token != null && getUser().token != undefined) {
        this.$store.commit("updateLoginState", true);
      } else {
        this.$store.commit("updateLoginState", false);
      }
    }
  }
</script>

<style>

  .avator-box {
    display: inline-block;
    width: 100%;
    height: 200px;
    text-align: center;
  }

  .avator {
    width: 100%;
    height: 40px;
  }

  .avator_img {
    width: 70px;
    height: 70px;
  }
  .el-dialog__header{
    height: 30px;
  }

  .el-dialog__title{
    font-size: 28px;
    vertical-align: top !important;
  }
  .el-dialog__footer{
    height: 70px;
    line-height: 10px;
  }
</style>
