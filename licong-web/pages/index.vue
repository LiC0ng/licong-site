<template>
  <div class="index" style="height: 100%;width: 100%">
    <el-table v-infinite-scroll="loadMore"
              :data="items"
              style="font-size: 20px"
              :header-cell-style="{background:'#eef1f6',color:'#606266', fontSize:'30px', height:'40px'}"
              @row-click="overallClick">
      <el-table-column prop="title" label="最新笔记" fixed="left"></el-table-column>
      <el-table-column prop="updatetime" label="" width="180px" fixed="right">
        <template slot-scope="scope">
          <div class="tableDate">
            <span v-html="getDate(scope.row, scope.column)"></span>
          </div>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import noteApi from "@/api/note"

  export default {
    data() {
      return {
        pageNo: 1
      }
    },
    asyncData() {
      return noteApi.getPublicNote(1, 12).then(res => {
        return {items: res.data.data.rows}
      })
    },
    methods: {
      loadMore() {
        this.pageNo++;
        noteApi.getPublicNote(this.pageNo, 12).then(res => {
          this.items = this.items.concat(res.data.data.rows)
        })
      },
      getDate(row, column) {
        let datetime = row.updatetime;
        if (datetime) {
          datetime = new Date(datetime);
          let y = datetime.getFullYear() + '年';
          let mon = datetime.getMonth() + 1 + '月';
          let d = datetime.getDate() + '日';
          return y + mon + d;
        }
        return ''
      },
      overallClick(row) {
        if (row) {
          this.$router.push({name: "note-item-id", params: {id: row.id}});
        }
      }
    }
  }
</script>

<style>
  .index .el-table th > .cell {
    height: 40px;
    padding-top: 10px;
  }

  .index .el-table td > .cell {
    padding-left: 50px;
    height: 30px;
    padding-top: 5px;
  }

  .index .tableDate {
    background: #7F828B;
    color: #f7f8fb;
    font-size: 15px;
    height: 25px;
    width: 120px;
    text-align: center;
    border-radius: 5px 5px 5px 5px;
  }
</style>
