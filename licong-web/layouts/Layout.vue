<template>
  <div class="app-wapper">
    <el-container>
      <el-aside width="140px">
        <sidebar></sidebar>
      </el-aside>
      <el-container>
        <el-header>
          <Topbar></Topbar>
        </el-header>
        <el-main>Main</el-main>
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
    line-height: 160px;
    height: 90%;
  }

  .el-container {
    height: 100%;
  }
</style>
<script>
  import Sidebar from "./Sidebar";
  import Topbar from "./Topbar"

  export default {
    components: {Sidebar, Topbar}
  }
</script>
