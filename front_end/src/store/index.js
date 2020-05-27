import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'
import admin from './modules/admin'
import getters from './getters'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    user,
    admin
  },
  getters
})
