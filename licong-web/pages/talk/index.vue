<template>
  <div style="padding: 20px; height: 100%" >
    <div class="talk-item" style="height: 100%">
      <h2>发表留言</h2>
      <div style="margin: 10px 0;"></div>
      <el-form ref="form" label-width="80px">
        <el-form-item label="昵称">
          <el-input v-model="talkNew.nickname"
                    maxlength="10"
                    show-word-limit
                    placeholder="请输入昵称"
                    style="width: 200px;float: left"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input
            v-model="talkNew.content"
            type="textarea"
            placeholder="请输入留言"
            :autosize=true
            style="width: 80%; float: left"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitTalk" style="float: left">留言</el-button>
        </el-form-item>
      </el-form>
      <div style="margin: 10px 0;"></div>
      <div class="talk-list">
        <div class="talk-value" v-for="talk in talkList" :key="talk.id">
          <div class="talk-content">
            <label class="talk-name">{{talk.nickname + ':' + '&#160;'}}</label>
            {{talk.content}}
            <label class="talk-time">{{dateFormat(talk.createtime)}}</label>
          </div>
          <div style="height: 1px;background-color:#E6E3E3;margin: 10px 0px auto auto"></div>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
  import talkApi from "@/api/talk"

  export default {
    data() {
      return {
        talkList: "",
        talkNew: {
          nickname: "",
          createtime: "",
          content: "",
        },
      }
    },
    created() {
      talkApi.getTalk().then(res => {
        this.talkList = res.data.data;
      });
    },
    methods: {
      submitTalk() {
        talkApi.submitTalk(this.talkNew).then(res => {
          this.$message({
            message: res.data.message,
            type: (res.data.flag ? 'success' : 'error')
          });
          if (res.data.flag) {
            talkApi.getTalk().then(res => {
              this.talkList = res.data.data;
            });
          }
        })
      },
      dateFormat(timestamp) {
        if (timestamp) {
          let datetime = new Date(timestamp);
          let y = datetime.getFullYear() + '年';
          let mon = datetime.getMonth() + 1 + '月';
          let d = datetime.getDate() + '日';
          return y + mon + d;
        }
        return ''
      }
    }
  }
</script>

<style>
  .talk-item {
    background-color: #ffffff;
    box-shadow: 0px 0px 2px 1px #c7c7c7;
  }

  .talk-item h2 {
    height: 40px;
    line-height: 30px;
    padding-top: 10px;
    padding-left: 20px;
    margin-block-start: 5px;
    margin-block-end: 0px;
    text-align: left;
  }

  .talk-list {
    border: 1px solid #DCDFE6;
    width: 90%;
    margin-left: 5%;
    margin-bottom: 120px;
    margin-top: 20px;
  }

  .talk-value {
    width: 100%;
    margin: 10px 0px auto;
  }

  .talk-name {
    font-weight: bold;
    font-size: 20px;
  }

  .talk-content {
    font-size: 20px;
    text-align: left;
    padding-left: 10px;
  }

  .talk-time {
    color: #7F828B;
    font-size: 14px;
    padding-top: 15px;
    float: right;
  }
</style>
