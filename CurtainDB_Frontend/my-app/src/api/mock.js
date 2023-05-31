import Mock from 'mockjs'
import homeApi from './mockServerData/home'//获取home.js中的对象
import user from './mockServerData/user'
import permission from './mockServerData/permission'

//定义mock请求拦截
//第二个参数是接口类型，get省略,然后去index里面定义API
Mock.mock('/api/home/getData', homeApi.getStatisticalData)


//用户列表的数据
Mock.mock('/api/user/add', 'post', user.createUser)
Mock.mock('/api/user/edit', 'post', user.updateUser)
Mock.mock('/api/user/del', 'post', user.deleteUser)
Mock.mock(/api\/user\/get/, user.getUserList)//get

Mock.mock(/api\/permission\/getMenu/,'post',permission.getMenu)