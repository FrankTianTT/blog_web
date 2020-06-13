//yst add comment api
import { axios } from '@/utils/request'
const api = {
    commentPre: '/api/comment'
}

export function getAllCommentsAPI() {
    return axios({
        url: `${api.commentPre}/all`,
        method: 'GET',
    })
}

export function getCommentsByUserIdAPI(param) {
    return axios({
        url: `${api.commentPre}/allCommentsOfUser`,
        method: `GTE`,
    })
}

export function addCommentByArticleAPI(data) {
    return axios({
        url: `${api.commentPre}/addComment`,
        method: 'POST',
        data
    })
}

export function getCommentsByArticleAPI(articleId) {
    return axios({
        url: `${api.commentPre}/${articleId}/Comments`,
        method: `GTE`,
    })
}