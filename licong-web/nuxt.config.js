module.exports = {
  /*
  ** Headers of the page
  */
  head: {
    title: '李聪的个人博客',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: '{{escape description }}' }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }
    ]
  },
  /*
  ** Customize the progress bar color
  */
  loading: { color: '#3B8070' },
  /*
  ** Build configuration
  */
  css: [
    'element-ui/lib/theme-chalk/index.css'
  ],
  plugins: [
    // ssr: true表示这个插件只在服务端起作用
    {src: '~/plugins/ElementUI', ssr: true }
  ],
  build: {
    /*
    ** Run ESLint on save
    */
    // 防止多次打包
    vendor: ['element-ui'],
    vendor: ['axios'],
    extend (config, { isDev, isClient }) {
      if (isDev && isClient) {
        config.module.rules.push({
          enforce: 'pre',
          test: /\.(js|vue)$/,
          loader: 'eslint-loader',
          exclude: /(node_modules)/
        })
      }
    }
  }
}

