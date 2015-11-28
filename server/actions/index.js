module.exports = function(app){
    app.actions = {};
    app.actions.todos = require('./todos')(app);
};