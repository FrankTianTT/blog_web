import Vue from 'vue'
import router from '@/router'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'
import {
    loginAPI,
    registerAPI,
    getUserInfoAPI,
    updateUserInfoAPI,
} from '@/api/user'
const getDefaultState = () => {
    return {
        userId: '',
        userInfo: {

        },
        userBlogList: [

        ],
        userCommentList:[

        ]
    }
}

const user = {
    state : getDefaultState(),

    mutations: {
        reset_state: function(state) {
            state.token = '',
            state.userId = '',
            state.userInfo = {
                
            },
            state.userBlogList = [],
            state.userCommentList = []
        },
        set_token: function(state, token){
            state.token = token
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
        set_userBlogList:(state,data)=>{
            state.userBlogList = {
                ...state.userBlogList,
                ...data
            }
        },
        set_userCommentList:(state,data)=>{
            state.userCommentList = {
                ...state.userCommentList,
                ...data
            }
        }
    },

    actions: {
        login: async ({ state,dispatch, commit }, userData) => {
            const res = await loginAPI(userData)
            if(res){
                console.log(res)
                setToken(res.id)
                commit('set_userId', res.id)
                dispatch('getUserInfo')
                if(state.userInfo.userType==='Admin'){
                    router.push({name:'admin'})
                }else{
                    router.push({name:'blogHome'})
                }
            }
        },
        register: async({ commit }, data) => {
            const res = await registerAPI(data)
            if(res){
                message.success('注册成功')
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
        updateUserInfo: async({ state, dispatch }, data) => {
            const params = {
                id: state.userId,
                ...data,
            }
            const res = await updateUserInfoAPI(params)
            if(res){
                message.success('修改成功')
                dispatch('getUserInfo')
            }
        },
        logout: async({ commit }) => {
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
    }
}

export default user