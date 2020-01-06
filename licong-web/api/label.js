import request from '@/utils/request'
const group_name = 'label'
const api_name = 'label'
export default {
  getLabels(id) {
    return request({
      url: `/${group_name}/${api_name}/labels/${id}`,
      method: 'get',
    })
  },
  getLabel(id) {
    return request({
      url: `/${group_name}/${api_name}/${id}`,
      method: 'get',
    })
  },
  getLabelList(id) {
    return request({
      url: `/${group_name}/${api_name}/sub/${id}`,
      method: 'get',
    })
  },
  getRootLabels() {
    return request({
      url: `/${group_name}/${api_name}/labels`,
      method: 'get',
    })
  }
}
