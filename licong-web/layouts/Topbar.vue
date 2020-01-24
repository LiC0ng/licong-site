<template>
  <div  class="sui-navbar" style="height: 100%; width: 100%">
    <ul class="sui-nav">
      <router-link tag="li" to="/"><a>首页</a></router-link>
      <router-link tag="li" to="/note"><a>笔记</a></router-link>
      <router-link tag="li" to="/talk"><a>留言板</a></router-link>
      <router-link tag="li" to="/agmob"><a>AgMob</a></router-link>
      <router-link tag="li" to="/aboutme"><a>关于我</a></router-link>
    </ul>
    <form class="sui-form" @keyup.enter="search">
      <input type="text" v-model="searchKey" placeholder="输入一个关键词..."/>
      <input style="display: none"/>
      <i @click="search" class="el-icon-search"></i>
    </form>
  </div>
</template>

<script>
  import searchApi from "@/api/search"

  export default {
    data() {
      return {
        searchKey: ""
      }
    },
    methods: {
      search() {
        searchApi.searchNote(this.key, 1, 12);
        this.$router.push({name: "search-key", params: { key : this.searchKey}})
      }
    }
  }
</script>

<style>
  .sui-navbar form {
    line-height: 59px;
    height: 100%;
    width: 20%;
    float: left;
    margin-left: 20%;
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
    width: 50%;
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
    margin-right: 5%;
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
      font-size: 25px;
    }
  }

  @media only screen and (min-width: 1160px) and (max-width: 1534px) {
    .sui-navbar .sui-nav li a {
      font-size: 20px;
    }
  }

  @media (max-width: 1160px) {
    .sui-navbar .sui-nav li a {
      font-size: 15px;
    }
  }
</style>
