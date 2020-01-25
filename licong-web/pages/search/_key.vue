<template>
  <div class="search" style="height: 100%; width: 100%">
    <el-table v-infinite-scroll="loadMore"
              :data="items"
              style="font-size: 20px"
              :header-cell-style="{background:'#eef1f6',color:'#606266', fontSize:'30px',width: '100%'}"
              @row-click="overallClick">
      <el-table-column
        label="搜索结果">
        <template slot-scope="scope">
          <div v-html="computeTitle(scope.row.title)" style="margin-left: 10px; width: 100%;"></div>
          <div style="height: 10px"></div>
          <div style="margin-left: 10px; width: 100%; font-size: 15px" v-html="computeContent(scope.row.content)"></div>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import searchApi from "@/api/search"

  export default {
    data() {
      return {
        pageNo: 1,
      }
    },
    asyncData({params}) {
      return searchApi.searchNote(encodeURI(params.key), 1, 12).then(res => {
        if (res.data.flag) {
          return {items: res.data.data.rows}
        } else {
          return {items: ""}
        }
      })
    },
    methods: {
      loadMore() {
        this.pageNo++;
        searchApi.searchNote(this.$route.params.key, this.pageNo, 12).then(res => {
          this.items = this.items.concat(res.data.data.rows)
        })
      },
      overallClick(row) {
        if (row) {
          this.$router.push({name: "note-item-id", params: {id: row.id}});
        }
      },
      computeTitle(title) {
        let searchKey = this.$route.params.key;
        if (searchKey && searchKey.length > 0) {
          // 匹配关键字正则
          let replaceReg = new RegExp(searchKey, 'g');
          // 高亮替换v-html值
          let replaceString = '<span class="search-text">' + searchKey + '</span>';
          // 开始替换
          title = title.replace(replaceReg, replaceString);
        }
        return title;
      },
      computeContent(content) {
        let result = "";
        let searchKey = this.$route.params.key;
        let begin;
        while (true) {
          begin = content.indexOf(searchKey);
          if (begin >= 0 && begin + 20 <= content.length) {
            result = result + this.computeTitle(content.slice(begin, begin + 20)) + "...";
            content = content.slice(begin + 21, content.length);
            if (result.length > 300) {
              return result + "...";
            }
          } else if (begin >= 0 && begin + 20 > content.length) {
            result = result + this.computeTitle(content) + "...";
            return result;
          } else {
            if (result.length > 0) {
              return result;
            } else if (content.length <= 100) {
              return content;
            } else {
              return content.slice(0, 100)
            }
          }
        }
      }
    }
  }
</script>

<style>
  .search .el-table-column {
    width: 100%;
  }
  .search .el-table th>.cell {
    height: 50px;
    padding-top: 10px;
  }

  .search-text {
    color: #ff4500;
  }
</style>
