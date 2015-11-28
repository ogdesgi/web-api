module.exports = function(app){
    app.use('/api/todos', require('./todos')(app));
};