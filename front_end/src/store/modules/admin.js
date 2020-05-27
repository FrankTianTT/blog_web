import {
    getUserListAPI,
    addManagerAPI,
} from '@/api/admin'
import {
    registerAPI,
    updateUserInfoAPI
} from '@/api/user'
import { Message } from 'element-ui'

const admin = {
    state: {
        userList: [

        ],
        addUserModalVisible: false,
        addUserParams: {
            email: '',
            password: '',
            userName: '',
            userType: 0,
            qqNumber: "",
            profile:""
        },
        editUserModalVisible:false,
        currentUserInfo:{}
    },
    mutations: {
        set_userList: function (state, data) {
            state.userList = data
        },
        set_addUserModalVisible: function (state, data) {
            state.addUserModalVisible = data
        },
        set_addUserParams: function (state, data) {
            state.addUserParams = {
                ...state.addUserParams,
                ...data,
            }
        },
        set_editUserModalVisible:function(state,data){
            state.editUserModalVisible=data
        },
        set_currentUserInfo:function(state,data){
            state.currentUserInfo = {
                ...state.currentUserInfo,
                ...data
            }
        }
    },
    actions: {
        getUserList: async ({ commit }) => {
            const res = await getUserListAPI()
            if (res) {
                commit('set_userList', res)
            }
        },
        addUser: async ({ state, commit, dispatch }) => {
            console.log(state.addUserParams)
            const res = await registerAPI(state.addUserParams)
            console.log(res)
            // if (res) {
                commit('set_addUserParams', {
                    email: '',
                    password: '',
                    userName: '',
                    userType: 0,
                    qqNumber: "",
                    profile:""
                })
                dispatch('getUserList')
            // } else {
            //     Message.error('添加失败')
            // }
            Message.success('添加成功')
            commit('set_addUserModalVisible', false)
        },
        editUser: async ({ state, commit, dispatch },data) => {
            const res = await updateUserInfoAPI(data)
            console.log(res)
            // if (res) {
            dispatch('getUserList')
            // } else {
            //     Message.error('添加失败')
            // }
            Message.success('修改成功')
            commit('set_editUserModalVisible', false)
        }
    }
}
export default admin