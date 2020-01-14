<template>
  <div class="memo-container">
    <div class="my-memo-list">
      <el-card v-for="(item, index) in this.items" :key="index" :body-style="{ padding: '0px'}" style="float: left; margin-left: 1%; margin-top: 1%">
        <el-input v-model="item.title" :readonly="!item.disabled" placeholder="请输入标题"></el-input>
        <el-input rows="4" type="textarea" v-model="item.content" :readonly="!item.disabled" placeholder="请输入内容"></el-input>
        <div style="padding: 14px;">
          <div class="bottom clearfix">
            <el-date-picker
              v-model="item.deadline"
              :readonly="!item.disabled"
              type="date"
              placeholder="截止时间"
              format="yyyy-MM-dd"
              :clearable=false
              :default-value="currentDate">
            </el-date-picker>
            <el-button v-if="item.edit" type="primary" class="memo-button" size="mini" @click="confirmEdit(item)">保存</el-button>
            <el-button v-else type="success" class="memo-button" @click="startEdit(index)" size="mini">编辑</el-button>
            <el-button type="danger" class="memo-button" size="mini" @click="deleteMemo(item.id)">删除</el-button>
          </div>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
  import memoApi from "@/api/memo"

  export default {
    data() {
      return {
        items: [],
        currentDate: new Date()
      }
    },
    methods: {
      updateList() {
        memoApi.getAll().then((res) => {
          if (res.data.flag) {
            this.items = res.data.data;
            this.items.forEach((item) => {
              item["edit"] = false;
              item["disabled"] = false;
            });
            this.add()
          } else {
            this.$message({
              message: res.data.message,
              type: 'error'
            });
          }
        })
      },
      startEdit(index) {
        let item = this.items[index];
        item.edit = !item.edit;
        item.disabled = true;
        this.$set(this.items, index, item);
      },
      confirmEdit(item) {
        memoApi.saveMemo(item).then(res => {
          this.$message({
            message: res.data.message,
            type: (res.data.flag ? 'success' : 'error')
          });
          if (res.data.flag) {
            this.updateList();
          }
        })
      },
      deleteMemo(id) {
        memoApi.deleteMemo(id).then(res => {
          this.$message({
            message: res.data.message,
            type: (res.data.flag ? 'success' : 'error')
          });
          if (res.data.flag) {
            this.updateList();
          }
        })
      },
      add () {
        let newItem = {
          title: "",
          content: "",
          deadline: "",
          edit: true, // 新增时处于可编辑状态，所以按钮是保存和取消
          disabled: true // 打开编辑状态
        };
        this.items.unshift(newItem); // 移到第一行
      },
    },
    created() {
      this.updateList();
    }
  }
</script>

<style>
  .memo-container {
    width: 100%;
    height: 100%;
    background-color: #ffffff;
  }

  .my-memo-list {
    width: 100%;
    height: 100%;
  }

  .el-date-editor {
    width: 50%;
  }

  .my-memo-list .el-input__inner{
    border-radius: 0px;
  }

  .my-memo-list .el-textarea__inner{
    border-radius: 0px;
  }

  .memo-button {
    margin-top: 2%;
    margin-left: 2%;
  }
</style>
