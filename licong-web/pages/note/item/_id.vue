<template>
  <div style="padding: 20px; height: 100%">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item v-for="label in labels" :key="label.id" style="font-size: 18px; font-weight: bold">
        {{label.labelname}}
      </el-breadcrumb-item>
    </el-breadcrumb>
    <div class="note-item" style="margin-top: 30px">
      <el-input v-model="note.title" :readonly="editableTitle"></el-input>
      <mavon-editor
        v-model="note.content"
        :subfield="false"
        default-open="preview"
        :editable=isLogin
        :toolbars=toolbarConfigs
        @save="saveMd"
        @subfieldToggle="editTitle"
        @imgAdd="addImg"
        ref=md
      />
    </div>
    <div class="reply-item">
      <h2>发表评论</h2>
      <div style="margin: 10px 0;"></div>
      <el-form ref="form" label-width="80px">
        <el-form-item label="昵称">
          <el-input v-model="replyNew.nickname"
                    maxlength="10"
                    show-word-limit
                    placeholder="请输入昵称"
                    style="width: 200px;float: left"></el-input>
        </el-form-item>
        <el-form-item>
          <el-input
            v-model="replyNew.content"
            type="textarea"
            placeholder="请输入评论"
            :autosize=true
            style="width: 80%; float: left"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitReply" style="float: left">评论</el-button>
        </el-form-item>
      </el-form>
      <div style="margin: 10px 0;"></div>
      <div class="reply-list">
        <div class="reply-value" v-for="reply in replyList" :key="reply.id">
          <div class="reply-content">
            <label class="reply-name">{{reply.nickname + ':' + '&#160;'}}</label>
            {{reply.content}}
            <label class="reply-time">{{dateFormat(reply.createtime)}}</label>
          </div>
          <div style="height: 1px;background-color:#E6E3E3;margin: 10px 0px auto auto"></div>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
  import noteApi from "@/api/note"
  import labelApi from "@/api/label"
  import fileApi from "@/api/file"

  const host = ; // 文件服务器IP

  export default {
    data() {
      return {
        note: {
          title: "",
          content: ""
        },
        replyList: "",
        replyNew: {
          nickname: "",
          createtime: "",
          content: "",
        },
        labels: [],
        editableTitle: ""
      }
    },
    created() {
      let id = this.$route.path.split("/")[3];
      noteApi.getNoteById(id).then(res => {
        if (res.data.flag) {
          this.note = res.data.data;
          labelApi.getLabels(this.note.labelid).then(res => {
            this.labels = res.data.data;
          });
        } else {
          this.$message({
            message: "连接超时",
            type: "error"
          });
        }
      });
      noteApi.getReply(id).then(res => {
        if (res.data.flag) {
          this.replyList = res.data.data;
        } else {
          this.$message({
            message: "连接超时",
            type: "error"
          });
        }
      });
    },
    methods: {
      saveMd() {
        noteApi.updateNote(this.note.id, this.note).then(res => {
          this.$message({
            message: res.data.message,
            type: (res.data.flag ? 'success' : 'error')
          });
          if (res.data.flag) {
            if (this.$store.state.sidebar) {
              this.$store.commit("updateSidebarState", false);
              this.$nextTick(() => (this.$store.commit("updateSidebarState", true)))
            }
            noteApi.getNoteById(this.note.id).then(res => {
              this.note = res.data.data;
              labelApi.getLabels(this.note.labelid).then(res => {
                this.labels = res.data.data;
              });
            });
          }
        })
      },
      editTitle(status) {
        if (status) {
          this.editableTitle = false
        } else {
          this.editableTitle = 'readonly'
        }
      },
      submitReply() {
        this.replyNew.noteid = this.note.id;
        noteApi.submitReply(this.replyNew).then(res => {
          this.$message({
            message: res.data.message,
            type: (res.data.flag ? 'success' : 'error')
          });
          if (res.data.flag) {
            noteApi.getReply(this.note.id).then(res => {
              this.replyList = res.data.data;
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
      },
      addImg(pos, $file) {
        let formdata = new FormData();
        formdata.append('file', $file);
        fileApi.uploadImage(this.note.id, formdata).then((res) => {
          if (res.data.flag) {
            this.$refs.md.$imglst2Url([[pos, host + res.data.data]])
          } else {
            this.$message({
              message: "图片上传失败",
              type: "error"
            });
          }
        })
      }
    },
    computed: {
      isLogin() {
        return this.$store.state.isLogin;
      },
      toolbarConfigs() {
        let toolbar = {
          bold: this.$store.state.isLogin, // 粗体
          italic: this.$store.state.isLogin, // 斜体
          header: this.$store.state.isLogin, // 标题
          underline: this.$store.state.isLogin, // 下划线
          strikethrough: this.$store.state.isLogin, // 中划线
          mark: this.$store.state.isLogin, // 标记
          superscript: this.$store.state.isLogin, // 上角标
          subscript: this.$store.state.isLogin, // 下角标
          quote: this.$store.state.isLogin, // 引用
          ol: this.$store.state.isLogin, // 有序列表
          ul: this.$store.state.isLogin, // 无序列表
          link: this.$store.state.isLogin, // 链接
          imagelink: this.$store.state.isLogin, // 图片链接
          code: this.$store.state.isLogin, // code
          table: this.$store.state.isLogin, // 表格
          fullscreen: this.$store.state.isLogin, // 全屏编辑
          readmodel: true, // 沉浸式阅读
          htmlcode: true, // 展示html源码
          help: true, // 帮助
          /* 1.3.5 */
          undo: this.$store.state.isLogin, // 上一步
          redo: this.$store.state.isLogin, // 下一步
          trash: this.$store.state.isLogin, // 清空
          save: this.$store.state.isLogin, // 保存（触发events中的save事件）
          /* 1.4.2 */
          navigation: true, // 导航目录
          /* 2.1.8 */
          alignleft: this.$store.state.isLogin, // 左对齐
          aligncenter: this.$store.state.isLogin, // 居中
          alignright: this.$store.state.isLogin, // 右对齐
          /* 2.2.1 */
          subfield: true, // 单双栏模式
          preview: this.$store.state.isLogin, // 预览
        };
        return toolbar;
      }
    }
  }
</script>

<style>
  .el-breadcrumb__separator {
    color: #7F828B;
  }

  .note-item {
    background-color: #ffffff;
  }

  .note-item .el-input__inner {
    text-align: center;
    height: 60px;
    line-height: 40px;
    font-size: 40px;
    font-weight: bold;
    color: #2c2c2c;
  }

  .reply-item {
    background-color: #ffffff;
    box-shadow: 0px 0px 2px 1px #c7c7c7;
    padding-bottom: 100px;
  }

  .reply-item h2 {
    height: 40px;
    line-height: 30px;
    padding-top: 10px;
    padding-left: 20px;
    margin-block-start: 5px;
    margin-block-end: 0px;
    text-align: left;
  }

  .reply-list {
    border: 1px solid #DCDFE6;
    width: 90%;
    margin-left: 5%;
  }

  .reply-value {
    width: 100%;
    margin: 10px 0px auto auto;
  }

  .reply-name {
    font-weight: bold;
    font-size: 20px;
  }

  .reply-content {
    font-size: 20px;
    text-align: left;
    padding-left: 10px;
  }

  .reply-time {
    color: #7F828B;
    font-size: 14px;
    padding-top: 15px;
    float: right;
  }
</style>
