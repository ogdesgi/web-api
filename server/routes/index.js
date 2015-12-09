module.exports = function(app){
    app.use('/api/todos', require('./todos')(app));
    app.use('/api/auth', require('./auth')(app));
    app.use('/api/users', require('./users')(app));
};