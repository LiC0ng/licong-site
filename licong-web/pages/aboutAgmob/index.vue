<template>
  <div class="agmob">
    <h2>AgMob - 一款基于WebRTC的桌面共享应用</h2>
    <div>
      <a href="https://www.licong.co/agmob">AgMob</a>是我们Elang团队(李 聪, Yamaguchi Kazuki, Naito Yuki, Kaku Linsho)于
      2019年采用<strong>敏捷(Agile)</strong>方式开发的一款支援远程<strong>Mob编程</strong>的应用。
      <p><a href="https://www.licong.co/agmob" target="_blank">官网点这</a>
        <a href="https://github.com/LiC0ng/AgMob" target="_blank" style="margin-left: 2%">源码点这</a></p>
      <img src="../../assets/image/agmob1.png" alt="">
      <p><strong>使用场景:</strong> 远程Mob编程，云会议</p>
      <p><strong>支持平台:</strong>
        <br>&#12288;观看方: 谷歌浏览器，火狐浏览器，Edge浏览器
        <br>&#12288;共享方: Windows, Ubuntu, MacOS(维护中)</p>
      <p><strong>技术:</strong> 基于WebRTC和WebSocket
        <br><strong>&#12288;前端:</strong> React + TypeScript
        <br><strong>&#12288;客户端:</strong> React + TypeScript + Electron
        <br><strong>&#12288;服务器:</strong> Kotlin</p>
      <p><strong>特点:</strong>
        <br>&#12288;桌面共享，文字聊天，语音聊天，激光笔</p>
      <p><strong>使用说明:</strong>
        <br>在Mob编程中，有两个角色:驾驶员(Driver)和领航员(Navigator),驾驶员需下载我们提供的客户端获取私钥并将私钥发送给
        领航员，领航员使用获取到的私钥在客户端或者网页输入即可开始远程Mob编程或者云会议。整个过程无须注册账号即可快速使用。</p>
      <p><strong>应用截图:</strong></p>
      <img src="../../assets/image/agmob2.JPG" style="height: 50%;width: 70%; margin-left: 10%" alt="">
    </div>
  </div>
</template>

<script>
  export default {}
</script>

<style>
  .agmob {
    text-align: left;
    line-height: 30px;
    padding: 2%;
    width: 96%;
    height: 350%;
    background-color: #ffffff;
  }

  .agmob img {
    width: 30%;
    height: 50%;
    margin-left: 25%;
    padding: 20px 0px;
    background-size: 50% 100%;
  }


  .agmob h2 {
    width: 100%;
  }

  .agmob a {
    text-decoration: none;
    font-weight: bold;
    float: left;
    color: tan;
  }

  .agmob p {
    width: 100%;
    float: left;
  }

</style>
