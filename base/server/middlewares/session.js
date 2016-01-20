var session = require('express-session');
var Mongostore = require('connect-mongo')(session);

module.exports = function(app) {
    app.use(session({
        secret: 's3cr3t',
        resave: true,
        saveUninitialized: false,
        store: new Mongostore({
            url: app.settings.sessionDb,
            ttl: app.settings.sessionTTL
        })
    }));
};