//mock数据模拟
import Mock from 'mockjs'

// 图表数据
let List = []
//暴露一个对象，返回数据
export default {
    getStatisticalData: () => {
        //Mock.Random.float 产生随机数100到8000之间，保留小数，最小0位，最大0位
        for (let i = 0; i < 7; i++) {
            List.push(
                Mock.mock({
                    Grooming: Mock.Random.float(100, 8000, 0, 0),
                    Health: Mock.Random.float(100, 8000, 0, 0),
                    Medical: Mock.Random.float(100, 8000, 0, 0),
                    Entertainment: Mock.Random.float(100, 8000, 0, 0),
                    Outdoor: Mock.Random.float(100, 8000, 0, 0),
                    Food: Mock.Random.float(100, 8000, 0, 0),
                })
            )
        }
        return {
            code: 20000,
            data: {
                //饼图
                videoData: [
                    {
                        name: 'GROOMING',
                        value: 2999
                    },
                    {
                        name: 'HEALTH',
                        value: 5999
                    },
                    {
                        name: 'MEDICAL',
                        value: 1500
                    },
                    {
                        name: 'ENTERTAINMENT',
                        value: 1999
                    },
                    {
                        name: 'OUTDOOR',
                        value: 2200
                    },
                    {
                        name: 'FOOD',
                        value: 4500
                    },
                ],
                //柱状图
                userData: [
                    {
                        date: 'MON',
                        new: 5,
                        active: 200
                    },
                    {
                        date: 'TUE',
                        new: 10,
                        active: 500
                    },
                    {
                        date: 'WED',
                        new: 60,
                        active: 800
                    },
                    {
                        date: 'THU',
                        new: 5,
                        active: 200
                    },
                    {
                        date: 'SAT',
                        new: 53,
                        active: 770
                    },
                    {
                        date: 'SUN',
                        new: 33,
                        active: 170
                    },
                ],
                //折线图
                orderData: {
                    date: ['20191001', '20191002', '20191003', '20191004', '20191005', '20191006', '20191007'],
                    data: List
                },
                tableData: [
                    {
                        name: "PinkDog",
                        todayHP: 600,
                        monthHP: 300,
                        totalHP: 800,
                    },
                    {
                        name: "BlueCat",
                        todayHP: 100,
                        monthHP: 300,
                        totalHP: 800,
                    },
                    {
                        name: "GreenBird",
                        todayHP: 100,
                        monthHP: 300,
                        totalHP: 800,
                    },
                    {
                        name: "BlackEagle",
                        todayHP: 100,
                        monthHP: 300,
                        totalHP: 800,
                    },
                    {
                        name: "YellowBee",
                        todayHP: 100,
                        monthHP: 300,
                        totalHP: 800,
                    },
                    {
                        name: "BrownBear",
                        todayHP: 100,
                        monthHP: 300,
                        totalHP: 800,
                    },
                ]
            }
        }
    }
}