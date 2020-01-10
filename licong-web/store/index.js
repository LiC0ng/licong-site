import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);
const store = () => new Vuex.Store({
  state: {
    isLogin: false,
    sidebar: true,
    label: "",
    directory: ""
  },
  mutations: {
    updateLoginState(state, val) {
      state.isLogin = val
    },
    updateSidebarState(state, val) {
      state.sidebar = val
    },
    setLabel(state, val) {
      state.label = val
    },
    setDirectory(state, val) {
      state.directory = val
    },
  }
});
export default store
