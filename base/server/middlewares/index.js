var cookieParser = require('cookie-parser');

module.exports = function(app) {
    app.use(cookieParser());

    require('./session')(app);
    require('./authenticated')(app);
    require('./upload')(app);
};