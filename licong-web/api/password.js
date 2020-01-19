import request from '@/utils/request'
const group_name = 'password'
const api_name = 'password'
export default {
  getAll() {
    return request({
      url: `/${group_name}/${api_name}`,
      method: 'get',
    })
  },
  savePassword(pojo) {
    return request({
      url: `/${group_name}/${api_name}`,
      method: 'post',
      data: pojo
    })
  },
  deletePassword(id) {
    return request({
      url: `/${group_name}/${api_name}/${id}`,
      method: 'delete',
    })
  },
}
