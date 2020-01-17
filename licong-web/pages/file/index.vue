<template>
  <div class="file-container">
    <div class="my-file-list">
      <el-card :body-style="{ padding: '0px'}"
               style="float: left; margin-left: 1%; margin-top: 1%;height: 180px;width: 360px;">
        <el-upload
          class="upload-demo"
          drag
          action="http://localhost:9007/upload/upload"
          :headers=head
          :show-file-list="false"
          :on-success="updateList"
          multiple>
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        </el-upload>
      </el-card>
      <el-card v-for="(item, index) in this.items" :key="index" :body-style="{ padding: '0px'}" style="
      float: left; margin-left: 1%; margin-top: 1%; height: 180px;width: 360px;">
        <div style="height: 5px"></div>
        <span style="padding-top: 10px">文件名: </span>
        <el-input v-model="item.filename" :readonly="true"></el-input>
        <span style="padding-top: 10px">文件大小: </span>
        <el-input :value="formatFileSize(item.filesize)" :readonly="true"></el-input>
        <div>
          <div class="bottom clearfix">
            <span style="padding-top: 10px">上传时间: </span>
            <el-date-picker
              v-model="item.createtime"
              :readonly="true"
              type="date"
              format="yyyy-MM-dd"
              :clearable=false>
            </el-date-picker>
            <el-button type="success" class="file-button" size="mini"
                       @click="downloadFile(item.fileurl, item.filename)">下载
            </el-button>
            <el-button type="danger" class="file-button" size="mini" @click="deleteFile(item.id)">删除</el-button>
          </div>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
  import {getUser} from '@/utils/auth'
  import fileApi from "@/api/file"

  const host = "https://www.licong.co/";

  export default {
    data() {
      return {
        head: {'Authorization': 'Bearer ' + getUser().token},
        items: [],
      }
    },
    methods: {
      updateList() {
        fileApi.getAll().then((res) => {
          if (res.data.flag) {
            this.items = res.data.data.sort(this.mySort);
          } else {
            this.$message({
              message: res.data.message,
              type: 'error'
            });
          }
        })
      },
      downloadFile(url, name) {
        let aEle = document.createElement("a");
        aEle.href = host + url + "?filename=" + name;
        aEle.click();
      },
      deleteFile(id) {
        fileApi.deleteFile(id).then(res => {
          this.$message({
            message: res.data.message,
            type: (res.data.flag ? 'success' : 'error')
          });
          if (res.data.flag) {
            this.updateList();
          }
        })
      },
      formatFileSize(fileSize) {
        if (fileSize < 1024) {
          return fileSize + 'B';
        } else if (fileSize < (1024 * 1024)) {
          let temp = fileSize / 1024;
          temp = temp.toFixed(2);
          return temp + 'KB';
        } else if (fileSize < (1024 * 1024 * 1024)) {
          let temp = fileSize / (1024 * 1024);
          temp = temp.toFixed(2);
          return temp + 'MB';
        } else {
          let temp = fileSize / (1024 * 1024 * 1024);
          temp = temp.toFixed(2);
          return temp + 'GB';
        }
      },
      mySort(item1, item2) {
        return item2.createtime - item1.createtime;
      }
    },
    created() {
      this.updateList();
    }
  }
</script>

<style>
  .file-container {
    width: 100%;
    height: 100%;
    background-color: #ffffff;
  }

  .my-file-list {
    width: 100%;
    height: 100%;
  }

  .el-date-editor {
    width: 50%;
  }

  .my-file-list .el-input__inner {
    border-radius: 0px;
  }

  .my-file-list .el-textarea__inner {
    border-radius: 0px;
  }

  .my-file-list span {
    width: 25%;
    float: left;
  }

  .my-file-list .el-input {
    width: 70%;
    float: left;
  }

  .my-file-list .el-input__icon {
    float: left;
  }

  .file-button {
    margin-top: 5%;
    margin-left: 2%;
  }
</style>
