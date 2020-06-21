import Vue from 'vue'
import router from '@/router'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'
import { Message } from 'element-ui'
import {
    loginAPI,
    registerAPI,
    getUserInfoAPI,
    getUserArticleAPI,
    updateUserInfoAPI,
    makeCommentAPI,
} from '@/api/user'
import {
    addArticleAPI,
    updateArticleAPI
} from "@/api/article"
const getDefaultState = () => {
    return {
        updateUserInfoModalVisible: false,
        userId: '',
        userInfo: {

        },
        userArticleList: [

        ],
        userCommentList: [

        ],
        token: '',
        articleNeedModify: false,
        articleFormNeedModify: {}
    }
}

const user = {
    state: getDefaultState(),

    mutations: {
        reset_state: function (state) {
            state.token = '',
                state.userId = '',
                state.userInfo = {

                },
                state.userBlogList = [],
                state.userCommentList = [],
                articleNeedModify = false,
                articleFormNeedModify = {}
        },
        set_token: (state, data) => {
            state.token = data
        },
        set_email: (state, data) => {
            state.email = data
        },
        set_userId: (state, data) => {
            state.userId = data
        },
        set_userInfo: (state, data) => {
            state.userInfo = {
                ...state.userInfo,
                ...data
            }
        },
        set_userArticleList: (state, data) => {
            state.userArticleList = data
        },
        set_userCommentList: (state, data) => {
            state.userCommentList = {
                ...state.userCommentList,
                ...data
            }
        },
        set_updateUserInfoModalVisible: function (state, data) {
            state.updateUserInfoModalVisible = data
        },
        set_articleNeedModify: (state, data) => {
            state.articleNeedModify = data;
        },
        set_articleFormNeedModify:(state,data)=>{
            state.articleFormNeedModify = data;
        }
    },

    actions: {
        login: async ({ state, dispatch, commit }, userData) => {
            const res = await loginAPI(userData)
            console.log("请求登陆的返回")
            console.log(res)
            if (res) {
                setToken(res.id)
                commit('set_userId', res.id)
                commit('set_token', res.id)
                dispatch('getUserInfo').then(() => {
                    if (state.userInfo.userType === 'Admin') {
                        router.push({ name: 'Admin' })
                    } else {
                        router.push({ name: "BlogHome" })
                    }
                })
                Message.success("登陆成功")
                dispatch('getUserArticle')
            } else {
                Message.error("登陆失败")
            }
        },
        register: async ({ commit }, data) => {
            const res = await registerAPI(data)
            console.log(res)
            if (res) {
                Message.success('注册成功')
            }
        },
        getUserInfo({ state, commit }) {
            return new Promise((resolve, reject) => {
                getUserInfoAPI(state.userId).then(response => {
                    const data = response
                    if (!data) {
                        reject('登录已过期，请重新登录')
                    }
                    console.log(data)
                    commit('set_userInfo', data)
                    commit('set_userId', data.id)
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        getUserArticle: async ({ state, commit }) => {
            const res = await getUserArticleAPI(state.userId)
            if (res) {
                commit('set_userArticleList', res)
                console.log('用户文章获取成功', res)
            }
        },
        updateUserInfo: async ({ state, dispatch }, data) => {
            const params = {
                id: state.userId,
                ...data,
            }
            const res = await updateUserInfoAPI(params)
            if (res) {
                Message.success('修改成功')
                dispatch('getUserInfo')
            }
        },
        logout: async ({ commit }) => {
            removeToken()
            resetRouter()
            commit('reset_state')
        },
        // remove token
        resetToken({ commit }) {
            return new Promise(resolve => {
                removeToken() // must remove  token  first
                commit('reset_state')
                resolve()
            })
        },
        addArticle: async ({ state, dispatch }, data) => {
            const res = await addArticleAPI(data);
            if (res) {
                dispatch("getUserArticle");
                Message.success("文章添加成功");
                router.push({ name: "BlogHome" });
            }
        },
        addComment: async ({ state, dispatch }, data) => {
            const res = await makeCommentAPI(state.userId, data);
            if (res) {
                dispatch("getUserArticle");
                Message.success("评论添加成功");
            }
        },
        modifyArticle:async ({ state, dispatch },data) => {
            const res = await updateArticleAPI(data);
            if (res) {
                dispatch("getUserArticle");
                Message.success("文章修改成功");
                router.push({ name: "BlogHome" });
            }
        },
    }
}

export default user