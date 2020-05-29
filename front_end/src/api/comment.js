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

export function getCommentsByHotelIdAPI(param) {
    return axios({
        url: `${api.commentPre}/allCommentsOfHotel`,
        method: `GTE`,
    })
}

export function getCommentsByUserIdAPI(param) {
    return axios({
        url: `${api.commentPre}/allCommentsOfUser`,
        method: `GTE`,
    })
}

export function addCommentAPI(data) {
    return axios({
        url: `${api.commentPre}/addComment`,
        method: 'POST',
        data
    })
}