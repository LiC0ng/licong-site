import request from '@/utils/request'
const group_name = 'search';
const api_name = 'note';
export default {
  searchNote(key, page, size) {
    return request({
      url: `/${group_name}/${api_name}/${key}/${page}/${size}`,
      method: 'get',
    })
  }
}
