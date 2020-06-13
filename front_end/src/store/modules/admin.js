import {
    getUserListAPI,
    addManagerAPI,
    deleteUserAPI,
    getAllCommentsAPI,
    deleteCommentsAPI,
    deleteArticleAPI
} from '@/api/admin'
import {
    registerAPI,
    updateUserInfoAPI
} from '@/api/user'
import {
    addArticleAPI,
    getArticleListAPI
} from '@/api/article'
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
            profile: ""
        },
        editUserModalVisible: false,
        currentUserInfo: {},
        articleList:[],
        commentsList:[]
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
        set_editUserModalVisible: function (state, data) {
            state.editUserModalVisible = data
        },
        set_currentUserInfo: function (state, data) {
            state.currentUserInfo = {
                ...state.currentUserInfo,
                ...data
            }
        },
        set_articleList: function (state,data) {
            state.articleList = data
        },
        set_commentsList(state,data){
            state.commentsList = data;
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
                profile: ""
            })
            dispatch('getUserList')
            // } else {
            //     Message.error('添加失败')
            // }
            Message.success('添加成功')
            commit('set_addUserModalVisible', false)
        },
        editUser: async ({ state, commit, dispatch }, data) => {
            const res = await updateUserInfoAPI(data)
            console.log(res)
            // if (res) {
            dispatch('getUserList')
            // } else {
            //     Message.error('添加失败')
            // }
            Message.success('修改成功')
            commit('set_editUserModalVisible', false)
        },
        getArticleList: async ({commit})=>{
            const res = await getArticleListAPI();
            console.log(res)
            if(res){
                commit('set_articleList',res);
            }else{
                Message.error("获取文章列表出错");
            }
        },
        deleteArticle:async({dispatch},data)=>{
            const res = await deleteArticleAPI(data);
            console.log("删除文章接口");
            if(res){
                Message.success("删除文章成功");
                dispatch("getArticleList");
            }
        },
        deleteUser:async ({dispatch},data)=>{
            const res = await deleteUserAPI(data);
            if(res){
                dispatch('getUserList');
            }else{
                Message.error("删除失败");
            }
        },
        getAllComments: async({state,commit})=>{
            const res = await getAllCommentsAPI();
            if(res){
                commit("set_commentsList",res);
            }
        },
        deleteComments:async({state,dispatch},data)=>{
            const res = await deleteCommentsAPI(data);
            if(res){
                dispatch("getAllComments");
                Message.success(res);
            }
        }
    }
}
export default admin