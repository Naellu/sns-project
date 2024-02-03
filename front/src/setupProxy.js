const {createProxyMiddleware} = require('http-proxy-middleware');

module.exports = (app) => {

    app.use('/local',
        createProxyMiddleware(
            {
            target: 'http://localhost:8080',
            pathRewrite: {'^/local': ''},
            changeOrigin: true
            }
        )
    );

}