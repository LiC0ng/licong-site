import request from '@/utils/request'
import {getUser} from '@/utils/auth'
const group_name = 'upload';
const api_name = 'upload';
export default {
  getAll() {
    return request({
      url: `/${group_name}/${api_name}`,
      method: 'get',
    })
  },
  deleteFile(id) {
    return request({
      url: `/${group_name}/${api_name}/${id}`,
      method: 'delete',
    })
  },
  uploadImage(id, file) {
    return request({
      url: `/${group_name}/image/${id}`,
      method: 'post',
      data: file,
      headers: {
        'Authorization': 'Bearer ' + getUser().token,
        'Content-Type': 'multipart/form-data'
      }
    });
  },
  deleteImage(id) {
    return request({
      url: `/${group_name}/image/${id}`,
      method: 'delete',
    })
  },
}
