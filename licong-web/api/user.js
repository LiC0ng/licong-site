import request from '@/utils/request'
const group_name = 'user'
const api_name = 'admin'
export default {
    login(loginname,password){
        return request({
            url:`/${group_name}/${api_name}/login`,
            method: 'post',
            data: {
                loginname,
                password
            }
        })
    }
}
