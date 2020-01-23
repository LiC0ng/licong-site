<template>
  <div class="sidebar">
    <el-menu style="height: 100%"
             :default-active="$route.path"
             class="el-menu-vertical-demo"
             background-color="#f5f5f5"
             text-color="#000"
             active-text-color="#fff">
      <Login></Login>
      <sidebar-items></sidebar-items>
    </el-menu>
  </div>
</template>

<script>
  import SidebarItems from "../components/SidebarItems";
  import Login from "../components/Login";

  export default {
    components: {Login, SidebarItems},
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
