const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  //配置跨域 configure cross domain
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8081',
        changeOrigin: true,
      }
    }
  }
})

