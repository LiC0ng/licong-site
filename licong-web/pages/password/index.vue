<template>
  <div class="password">
    <h2 style="float: left; width: 100%">密码列表</h2>
    <el-button type="success" @click="addRow" style="float: left; margin: 10px">新增</el-button>
    <el-table :data="items" element-loading-text="Loading" border fit highlight-current-row>
      <el-table-column label="网站名" min-width="25%" align="center">
        <template slot-scope="scope">
          <el-input v-model="scope.row.sitename" :readonly="!scope.row.disabled"></el-input>
        </template>
      </el-table-column>
      <el-table-column label="用户名" min-width="25%" align="center">
        <template slot-scope="scope">
          <el-input v-model="scope.row.username" :readonly="!scope.row.disabled"></el-input>
        </template>
      </el-table-column>
      <el-table-column label="密码" min-width="25%" align="center">
        <template slot-scope="scope">
          <el-input v-model="scope.row.password" :readonly="!scope.row.disabled"></el-input>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" min-width="25%">
        <template slot-scope="scope">
          <el-button v-if="scope.row.edit" type="primary" size="mini"
                     icon="el-icon-circle-check-outline" @click="confirmEdit(scope.row)">保存
          </el-button>
          <el-button v-else type="success" size="mini"
                     @click="startEdit(scope.row)">编辑
          </el-button>
          <el-button type="danger" size="mini" @click="deletePassword(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import passwordApi from "@/api/password"

  export default {
    data() {
      return {
        items: []
      }
    },
    methods: {
      updateList() {
        passwordApi.getAll().then((res) => {
          if (res.data.flag) {
            this.items = res.data.data;
          } else {
            this.$message({
              message: res.data.message,
              type: 'error'
            });
          }
        })
      },
      startEdit(row) {
        row.edit = !row.edit;
        row.disabled = true;
      },
      confirmEdit(row) {
        passwordApi.savePassword(row).then(res => {
          this.$message({
            message: res.data.message,
            type: (res.data.flag ? 'success' : 'error')
          });
          if (res.data.flag) {
            this.updateList();
          }
        })
      },
      deletePassword(row) {
        passwordApi.deletePassword(row.id).then(res => {
          this.$message({
            message: res.data.message,
            type: (res.data.flag ? 'success' : 'error')
          });
          if (res.data.flag) {
            this.updateList();
          }
        })
      },
      addRow () {
        let newLine = {
          sitename: "",
          username: "",
          password: "",
          edit: true, // 新增时处于可编辑状态，所以按钮是保存和取消
          disabled: true // 打开编辑状态
        };
        this.items.unshift(newLine); // 移到第一行
      },
    },
    created() {
      this.updateList();
    }
  }
</script>

<style>
  .password {
    width: 100%;
    height: 100%;
    overflow: auto;
  }
</style>
