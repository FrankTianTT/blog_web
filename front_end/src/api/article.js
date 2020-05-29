import { axios } from '@/utils/request'
const api = {
    articlePre: '/api/blog'
}
export function addArticleAPI(data){
    return axios({
        url: `${api.articlePre}/addBlog`,
        method: 'POST',
        data
    })
}
export function getArticleListAPI(){
    return axios({
        url: `${api.articlePre}/all`,
        method: 'GET'
    })
}