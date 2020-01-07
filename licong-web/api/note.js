import request from '@/utils/request'
const group_name = 'note'
export default {
  getPublicNote(page, size){
    return request({
      url:`/${group_name}/note/public/${page}/${size}`,
      method: 'get',
    })
  },
  getNoteByLabel(id){
    return request({
      url:`/${group_name}/note/label/${id}`,
      method: 'get',
    })
  },
  getNewId() {
    return request({
      url:`/${group_name}/note/id`,
      method: 'get',
    })
  },
  getNoteById(id) {
    return request({
      url:`/${group_name}/note/${id}`,
      method: 'get',
    })
  },
  getReply(id) {
    return request({
      url: `/${group_name}/reply/${id}`,
      method: 'get',
    })
  },
  updateNote(id, pojo) {
    return request({
      url: `/${group_name}/note/${id}`,
      method: 'put',
      data: pojo
    })
  },
  saveNote(pojo) {
    return request({
      url: `/${group_name}/note`,
      method: 'post',
      data: pojo
    })
  },
  deleteNote(id) {
    return request({
      url: `/${group_name}/note/${id}`,
      method: 'delete',
    })
  },
  submitReply(pojo) {
    return request({
      url: `/${group_name}/reply`,
      method: 'post',
      data: pojo
    })
  },
}
