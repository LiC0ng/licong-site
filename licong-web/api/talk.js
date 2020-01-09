import request from '@/utils/request'
const group_name = 'talk';
const api_name = 'talk';
export default {
  getTalk() {
    return request({
      url: `/${group_name}/${api_name}`,
      method: 'get',
    })
  },
  submitTalk(pojo) {
    return request({
      url: `/${group_name}/${api_name}`,
      method: 'post',
      data: pojo
    })
  }
}
