var router = require('express').Router();
var bodyparser = require('body-parser').json();

module.exports = function(app){
    router.post('/',
        bodyparser,
        app.middlewares.authenticated,
        app.actions.todos.create
    );

    router.get('/',
        app.actions.todos.list
    );

    router.get('/:id',
        app.actions.todos.show
    );

    router.delete('/:id',
        app.actions.todos.remove
    );

    return router;
};
