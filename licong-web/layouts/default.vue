<template>
  <div class="app-wapper">
    <el-container>
      <el-aside width="12%" v-if="sidebarState">
        <sidebar></sidebar>
      </el-aside>
      <el-aside width="13%" v-if="subSidebarState">
        <SubSidebar></SubSidebar>
      </el-aside>
      <el-container>
        <el-header>
          <Topbar></Topbar>
        </el-header>
        <el-main>
          <div class="layout-content" style="height: 100%">
            <nuxt/>
          </div>
        </el-main>
        <el-button icon="el-icon-s-fold" class="fold-button" v-if="sidebarState" @click="close"></el-button>
        <el-button icon="el-icon-s-unfold" class="fold-button" v-if="!sidebarState" @click="open"></el-button>
      </el-container>
    </el-container>
  </div>
</template>


<style>
  .app-wapper {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
  }

  .el-header {
    display: block;
    background-color: #2c2c2c;
    height: 10%;
    width: 100%;
    text-align: center;
  }

  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    height: 100%;
    width: 100%;
    padding: 0px;
  }

  .el-container {
    height: 100%;
  }
  .fold-button {
    position: absolute;
    top: 80px;
    right: 20px;
    width: 20px;
    height: 40px;
    z-index: 1000;
  }
  .el-icon-s-fold {
    position: relative;
    right: 15px;
    bottom: 8px;
    font-size: 30px;
  }
  .el-icon-s-unfold {
    position: relative;
    right: 15px;
    bottom: 8px;
    font-size: 30px;
  }
</style>
<script>
  import Sidebar from "./Sidebar";
  import SubSidebar from "./SubSidebar"
  import Topbar from "./Topbar"

  export default {
    components: {Sidebar, SubSidebar, Topbar},
    methods: {
      close() {
        this.$store.commit("updateSidebarState", false);
      },
      open() {
        this.$store.commit("updateSidebarState", true);
      }
    },
    computed: {
      sidebarState() {
        return  this.$store.state.sidebar;
      },
      subSidebarState() {
        return this.$store.state.sidebar && this.$route.path.indexOf('note') >= 0;
      }
    }
  }
</script>
