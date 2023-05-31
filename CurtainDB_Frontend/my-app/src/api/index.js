import http from "@/utils/request";

//请求首页数据
// export const getData = () => {
//     //返回一个promise对象，之后用.then获取数据，此处get方法没有第二个参数
//     return http.get('/home/getData')
// }

// export const getMenu = (data) => {
//     return http.post('/user/login', data)
// }

export const getVib = (params) => {
    return http.get('/device/pageList/'+params, params)
}

export const getVibCSV = (params) => {
    return http.post('/device/import/', params)
}

export const getExcVib = (data) => {
    //返回用户列表
    return http.post('/device/xyzException', data)
}

export const getHeat = (data) => {
    //返回用户列表
    return http.post('/log/heatlist', data)
}
// export const addVid = (data) => {
//     return http.post('/Vid/save', data)
// }

// export const editVid = (data) => {
//     return http.put('/Vid/update', data)
// }

// export const delVid = (params) => {
//     return http.delete('/Vid/delete/'+params, params)
// }

// export const SearchVid = (params) => {
//     return http.get('/Vid/get/'+params, params)
// }

// export const addRegister = (data) => {
//     return http.post('/user/save', data)
// }

// export const adoptVid = (data) => {
//     return http.post('/user/save', data)
// }

// export const getDoc = (data) => {
//     //返回用户列表
//     return http.post('/doctor/getAll', data)
// }

// export const editDoc = (data) => {
//     return http.put('/doctor/update', data)
// }

// export const delDoc = (params) => {
//     return http.delete('/doctor/delete/'+params, params)
// }

// export const SearchDoc = (params) => {
//     return http.get('/doctor/get/'+params, params)
// }

// export const addDoc = (data) => {
//     return http.put('/doctor/save', data)
// }

// //**********/

// export const getAdp = (data) => {
//     //返回用户列表
//     return http.post('/adopter/list', data)
// }

// export const editAdp = (data) => {
//     return http.put('/adopter/update', data)
// }


// export const SearchAdp = (params) => {
//     return http.get('/adopter/search/'+params, params)
// }

// export const addAdp = (data) => {
//     return http.post('/adopter/save', data)
// }

// export const searchUser = (params) => {
//     return http.get('/user/search/'+params, params)
// }