<template>
  <div class="sidebar">
    <el-menu style="height: 100%"
      default-active="2"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      background-color="#f5f5f5"
      text-color="#000"
      active-text-color="#fff">
      <avator></avator>
      <sidebar-items></sidebar-items>
    </el-menu>
  </div>
</template>

<script>
  import SidebarItems from "../components/SidebarItems";
  import Avator from "../components/Avator";
  export default {
    components: {Avator, SidebarItems}
  }
</script>

<style>
  .sidebar {
    height: 100%;
  }
  .el-menu-item.is-active {
    background-color: rgb(0, 0, 0) !important;
  }
</style>
