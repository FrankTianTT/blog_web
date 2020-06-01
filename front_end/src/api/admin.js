import { axios } from '@/utils/request'
const api = {
    adminPre: '/api/admin'
}
export function getUserListAPI(){
    return axios({
        url: `${api.adminPre}/getAllUsers`,
        method: 'POST'
    })
}
export function deleteUserAPI(data) {
    return axios({
        url: `${api.adminPre}/DelUser/${data.id}`,
        method: 'POST',
    })
}