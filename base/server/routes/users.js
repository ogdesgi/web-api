var router = require('express').Router();
var bodyparser = require('body-parser').json();

module.exports = function(app) {
    router.post('/',
        bodyparser,
        app.actions.users.create
    );

    router.get('/',
        app.actions.users.list
    );

    router.post('/avatar',
        app.middlewares.authenticated,
        app.middlewares.upload.single('avatar'),
        app.actions.users.uploadAvatar
    );

    return router;
};
