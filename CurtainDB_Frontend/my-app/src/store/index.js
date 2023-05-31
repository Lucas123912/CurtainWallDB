import Vue from 'vue'
import Vuex from 'vuex'
import tab from './tab'

Vue.use(Vuex)

//创建vuex实例并暴露
export default new Vuex.Store({
    modules: {
        tab
    }
})
