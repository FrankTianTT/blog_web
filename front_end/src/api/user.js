import { axios } from '@/utils/request'

const api = {
    userPre: '/api/user'
}
export function loginAPI(data){
    return axios({
        url:`${api.userPre}/login`,
        method: 'POST',
        data
    })
}
export function registerAPI(data){
    return axios({
        url: `${api.userPre}/register`,
        method: 'POST',
        data
    })
}
export function getUserInfoAPI(id){
    return axios({
        url: `${api.userPre}/${id}/getUserInfo`,
        method: 'GET'
    })
}
export function getUserArticleAPI(id){
    return axios({
        url: `${api.userPre}/${id}/getUserArticle`,
        method: 'GET'
    })
}
export function updateUserInfoAPI(data) {
    return axios({
        url: `${api.userPre}/${data.id}/update`,
        method: 'POST',
        data
    })
}
export function makeCommentAPI(id,data) {
    return axios({
        url: `${api.userPre}/${id}/makeComment`,
        method: 'POST',
        data
    })
}