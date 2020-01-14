import request from '@/utils/request'
const group_name = 'memo';
const api_name = 'memo';
export default {
  getAll() {
    return request({
      url: `/${group_name}/${api_name}`,
      method: 'get',
    })
  },
  saveMemo(pojo) {
    return request({
      url: `/${group_name}/${api_name}`,
      method: 'post',
      data: pojo
    })
  },
  deleteMemo(id) {
    return request({
      url: `/${group_name}/${api_name}/${id}`,
      method: 'delete',
    })
  },
}
