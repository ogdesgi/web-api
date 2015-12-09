var cookieParser = require('cookie-parser');
module.exports = function(app) {
    require('./session')(app);
    app.use(cookieParser());

    app.middlewares = {
        authenticated: require('./authenticated')(app)
    };
};