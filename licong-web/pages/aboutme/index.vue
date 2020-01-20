<template>
  <div class="aboutme">
    <div>
      <h2>关于我</h2>
      <div class="info">
        <p>&#12288;<strong>本科:</strong> 电子科技大学(网络工程)</p>
        <p>&#12288;<strong>研究生:</strong> 东京工业大学(计算机科学)</p>
        <p>&#12288;<strong>格言:</strong> Stay hungry, stay foolish!</p>
        <p>&#12288;<strong>我的邮箱:</strong> 1040484@qq.com</p>
      </div>
      <div class="info">
        <img src="../../assets/image/me.JPG" style="height: 40%;width: 40%; float: left; padding: 0px" alt="">
      </div>
    </div>
    <h2 style="padding-top: 200px">关于本站</h2>
    <div>
      <p>&#12288;<strong>简介:</strong>个人开发的网站，游客可以观看笔记，评论和留言。用户可以开启写笔记，传文件，备忘录和密码簿功能(暂不开放注册)</p>
      <p>&#12288;<strong>技术:</strong>
        <br>&#12288;&#12288;<strong>前端:</strong> Vue + JS + ElementUI
        <br>&#12288;&#12288;<strong>后端:</strong> Java(SpringBoot + SpringCloud)，数据库MySQL,
        采用Redis缓存笔记，MangoDB存储留言，FastDFS传文件，ElasticSearch实现搜索
      </p>
      <p>&#12288;<strong>源码:</strong><a href="https://github.com/LiC0ng/licong-site" target="_blank"
                                        style="margin-left: 2%">源码点这</a></p>
      <p>&#12288;<strong>网站功能(备忘录):</strong></p>
      <img src="../../assets/image/memo.PNG" style="height: 50%;width: 70%; margin-left: 10%" alt="">
      <p>&#12288;<strong>网站功能(传文件):</strong></p>
      <img src="../../assets/image/file.PNG" style="height: 50%;width: 70%; margin-left: 10%" alt="">
      <p>&#12288;<strong>网站功能(密码簿):</strong></p>
      <img src="../../assets/image/password.PNG" style="height: 50%;width: 70%; margin-left: 10%" alt="">
    </div>
  </div>
</template>

<script>
  export default {}
</script>

<style>
  .aboutme {
    text-align: left;
    line-height: 30px;
    padding: 2%;
    width: 96%;
    height: 450%;
    background-color: #ffffff;
  }

  .aboutme .info {
    width: 30%;
    height: 10%;
    float: left;
  }

  .aboutme .info img {
    width: 30%;
    height: 50%;
    margin-left: 25%;
    padding: 20px 0px;
    background-size: 50% 100%;
  }


  .aboutme h2 {
    width: 100%;
    float: left;
  }

  .aboutme a {
    text-decoration: none;
    font-weight: bold;
    color: tan;
  }

  .aboutme p {
    width: 100%;
    float: left;
  }

</style>

