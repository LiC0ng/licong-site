<template>
  <div class="sui-navbar" style="height: 100%; width: 100%" >
    <ul class="sui-nav">
      <router-link tag="li" to="/"><a>首页</a></router-link>
      <router-link tag="li" to="/memo"><a>笔记</a></router-link>
      <router-link tag="li" to="/talk"><a>留言板</a></router-link>
      <router-link tag="li" to="/agmob"><a>AgMob</a></router-link>
      <router-link tag="li" to="/aboutme"><a>关于我</a></router-link>
    </ul>
    <form class="sui-form">
      <input type="text" placeholder="输入关键词..."/>
      <i class="el-icon-search"></i>
    </form>
  </div>
</template>

<style>
   .sui-navbar form {
    line-height: 59px;
    height: 100%;
    width: 20%;
    float: right;
    font-size: 14px;
    text-align: center;
    vertical-align: middle;
  }

   .sui-navbar input {
    width: 80%;
    height: 60%;
    padding: 2px 10px;
    margin-top: 0;
    border: 0;
    border-radius: 25px;
    line-height: 38px;
    font-size: 14px;
    font-family: "微软雅黑";
  }

  .el-icon-search {
    display: inline-block;
    vertical-align: middle;
    margin-left: -40px;
    line-height: 38px;
    font-size: 25px;
  }

  .sui-navbar .sui-nav {
    width: 35%;
    height: 100%;
    float: right;
  }

  .sui-navbar .sui-nav ul {
    display: inline-block;
    display: flex;
    flex-direction: column;
    justify-content: center;
    float: right;
    height: 100%;
    width: 100%;
  }

  .sui-navbar .sui-nav li {
    list-style-type: none;
    display: inline;
    height: 100%;
    width: 16%;
    vertical-align: middle;
  }

  @media (min-width: 1534px) {
    .sui-navbar .sui-nav li {
      margin-right: 20px;
    }
  }

   @media only screen and (min-width: 1160px) and (max-width: 1534px) {
     .sui-navbar .sui-nav li {
       margin-right: 15px;
     }
   }

   @media (max-width: 1160px) {
     .sui-navbar .sui-nav li {
       margin-right: 4px;
     }
   }

  .sui-navbar .sui-nav li a {
    display: inline;
    text-decoration: none;
    color: #fff;
    height: 100%;
    width: 100%;
    line-height: 100%;
    vertical-align: middle;
  }

   @media (min-width: 1534px) {
     .sui-navbar .sui-nav li a {
       font-size: 26px;
     }
   }

   @media only screen and (min-width: 1160px) and (max-width: 1534px) {
     .sui-navbar .sui-nav li a {
       font-size: 18px;
     }
   }

   @media (max-width: 1160px) {
     .sui-navbar .sui-nav li a {
       font-size: 12px;
     }
   }
</style>
