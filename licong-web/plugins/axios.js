import axios from 'axios'

// 设置baseURL
axios.defaults.baseURL = ''

// 创建axios对象，暴露
export default axios.create()
