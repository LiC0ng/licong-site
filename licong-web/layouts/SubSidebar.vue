<template>
  <div class="sub-bar">
    <el-menu style="height: 100%"
             :default-active="$route.path"
             class="el-menu-vertical-demo"
             background-color="#f0f0f0"
             text-color="#000"
             active-text-color="#fff">
      <div class="menu-bar">
        <el-tooltip content="返回上一级" placement="right-start">
          <i class="el-icon-back" @click="backOne"></i>
        </el-tooltip>
        <span slot="title" class="menu-bar-title">列表</span>
        <router-link v-if="isLogin" to="/note/new">
          <el-tooltip content="新建笔记" placement="right-start">
            <i class="el-icon-circle-plus-outline"></i>
          </el-tooltip>
        </router-link>
      </div>
      <div class="label-list">
        <el-menu-item v-for="label in labels" :key="label.id" :index="label.id" @click="updateList(label.id)">
          <i class="el-icon-folder"></i>
          <span slot="title">{{label.labelname}}</span>
        </el-menu-item>
      </div>
      <div class="memo-list">
        <el-menu-item v-for="item in items" :key="item.id" :index="'/note/item/' + item.id">
          <i class="el-icon-document" @click="goToNote(item.id)"></i>
          <span slot="title" class="" @click="goToNote(item.id)">{{item.title}}
          </span>
          <i v-if="isLogin" class="el-icon-delete" @click="deleteNote(item.id)"></i>
        </el-menu-item>
      </div>
    </el-menu>
  </div>
</template>

<script>
  import noteApi from "@/api/note"
  import labelApi from "@/api/label"

  export default {
    data() {
      return {
        labels: [],
        items: [],
      }
    },
    created() {
      this.updateList();
    },
    methods: {
      updateList(id = this.$store.state.label) {
        this.$store.commit("setLabel", id);
        if (id == "") {
          labelApi.getRootLabels().then(res => {
            this.labels = res.data.data;
          });
          this.items = [];
        } else {
          labelApi.getLabelList(id).then(res => {
            this.labels = res.data.data;
          });
          noteApi.getNoteByLabel(id).then(res => {
            this.items = res.data.data;
          });
        }
      },
      backOne() {
        labelApi.getLabel(this.$store.state.label).then(res => {
          this.$store.commit("setLabel", res.data.data.parentid || "");
          this.updateList(this.$store.state.label);
        });
      },
      goToNote(id) {
        this.$router.push({path: "/note/item/" + id});
      },
      deleteNote(id) {
        noteApi.deleteNote(id).then(res => {
          this.$message({
            message: res.data.message,
            type: (res.data.flag ? 'success' : 'error')
          });
          if (res.data.flag) {
            this.updateList(this.$store.state.label);
            this.$router.push({path: "/note"});
          }
        });
      },
      computeLength(title) {
        let lens = 0; // 中文算2个字
        for (let i = 0; i < title.length; i++) {
          if ((title.charCodeAt(i) >= 0) && (title.charCodeAt(i) <= 255)) {
            lens = lens + 1;
          } else {
            lens = lens + 2;
          }
        }
        return lens;
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
  .sub-bar {
    height: 100%;
  }

  .menu-bar {
    height: 50px;
    background-color: #ffffff;
    display: block;
    overflow: hidden;
  }

  .menu-bar .el-icon-back {
    line-height: 50px;
    float: left;
    font-size: 2vw;
    width: 25%;
  }

  .menu-bar .el-icon-circle-plus-outline {
    line-height: 50px;
    float: left;
    font-size: 2vw;
    width: 25%;
  }

  .menu-bar .menu-bar-title {
    float: left;
    font-size: 1.2vw;
    font-weight: bold;
    line-height: 50px;
    width: 50%;
  }

  .sub-bar .el-menu-item {
    width: 100%;
    display: block;
    overflow: hidden;
    padding-left: 5% !important;
    padding-right: 5% !important;
  }

  .sub-bar .el-menu-item i {
    line-height: 56px;
    float: left;
    font-size: 1.2vw;
    width: 10%;
    padding-right: 2%;
  }

  .sub-bar .el-menu-item .el-icon-document {
    line-height: 56px;
    float: left;
    font-size: 1.2vw;
    width: 10%;
  }

  .sub-bar .el-menu-item span {
    float: left;
    font-size: 1.2vw;
    line-height: 56px;
    width: 70%;
    text-align: left;
    text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden;
  }

  .sub-bar .el-menu-item .el-icon-delete {
    line-height: 56px;
    float: right;
    font-size: 1.2vw;
    width: 10%;
    padding-right: 0px;
  }

</style>
