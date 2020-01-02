<template>
  <div class="sidebar-items">
    <el-menu-item index="1">
      <i class="el-icon-bell"></i>
      <span slot="title">备忘录</span>
    </el-menu-item>
    <el-menu-item index="2">
      <i class="el-icon-folder"></i>
      <span slot="title">传文件</span>
    </el-menu-item>
    <el-menu-item index="3">
      <i class="el-icon-view"></i>
      <span slot="title">密码本</span>
    </el-menu-item>
  </div>
</template>

<script>
  export default{
    name: 'SidebarItems',
  }
</script>

<style>
  .el-menu-item {
    padding-right: 30px !important;
  }
</style>
