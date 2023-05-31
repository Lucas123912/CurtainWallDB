//导出对象
export default {
    state: {
        isCollapse: false, //控制菜单展开
        tabsList: [
            //面包屑数据
            {
                path: "/",
                name: "home",
                label: "首页",
                icon: "s-home",
                url: "Home/Home",
            },
        ]
    },
    mutations: {
        //修改菜单展开收起的方法
        collapseMenu(state) {
            state.isCollapse = !state.isCollapse
        },
        //更新面包屑数据
        selectMenu() {

        }
    }
}