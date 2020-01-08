import axios from 'axios'
import {getUser} from '@/utils/auth'
// 创建axios实例
const service = axios.create({
  baseURL: 'http://localhost:9007', // api的base_url
  timeout: 1000000, // 请求超时时间
  headers: {'Authorization': 'Bearer ' + getUser().token}
});
export default service
