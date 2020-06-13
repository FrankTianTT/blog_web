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
export function getAllCommentsAPI(){
    return axios({
        url:`${api.adminPre}/getAllComments`,
        method:'POST'
    })
}
export function deleteCommentsAPI(data){
    return axios({
        url:`${api.adminPre}/DelComments/${data.id}`,
        method:'POST'
    })
}
export function deleteArticleAPI(data){
    return axios({
        url:`${api.adminPre}/DelBlog/${data.id}`,
        method:'POST'
    })
}
