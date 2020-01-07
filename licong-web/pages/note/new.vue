<template>
  <div style="padding: 20px; height: 100%">
    <el-select v-model="note.ispublic" placeholder="是否公开">
      <el-option label="私密笔记" value="0"></el-option>
      <el-option label="公开笔记" value="1"></el-option>
    </el-select>
    <div class="note-item" style="margin-top: 30px">
      <el-input v-model="note.title" placeholder="请输入标题"></el-input>
      <mavon-editor
        v-model="note.content"
        @save="saveMd"
        @imgAdd="addImg"
        ref=md
      />
    </div>
  </div>
</template>

<script>
  import noteApi from "@/api/note"
  import fileApi from "@/api/file"

  const host = ''; // 文件服务器IP

  export default {
    data() {
      return {
        note: {
          id: "",
          title: "",
          content: "",
          labelid: "",
          ispublic: "1",
        },
        isSave: false
      }
    },
    watch: {
      '$route': 'deleteImage',
    },
    methods: {
      saveMd() {
        if (this.$store.state.label == "") {
          this.$message({
            message: '无法在根标签下创建笔记',
            type: 'error'
          });
          return;
        } else {
          this.note.labelid = this.$store.state.label;
        }
        noteApi.saveNote(this.note).then(res => {
          this.$message({
            message: res.data.message,
            type: (res.data.flag ? 'success' : 'error')
          });
          if (res.data.flag) {
            this.isSave = true;
            this.$router.push({path: '/note/item/' + res.data.data.id})
            if (this.$store.state.sidebar) {
              this.$store.commit("updateSidebarState", false);
              this.$nextTick(() => (this.$store.commit("updateSidebarState", true)))
            }
          } else {
            this.$message({
              message: "保存超时，请重试",
              type: "error"
            });
          }
        })
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
      },
    },
    created() {
      noteApi.getNewId().then((res) => {
        if (res.data.flag) {
          this.note.id = res.data.data;
        } else {
          this.$message({
            message: "获取ID超时，请刷新界面",
            type: "error"
          });
        }
      })
    },
    destroyed() {
      if (!this.isSave) {
        // 笔记未保存，删除服务器中的图片
        fileApi.deleteImage(this.note.id)
      }
    }
  }
</script>


<style>
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
</style>
