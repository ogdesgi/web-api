// Index file to redirect middlewares scripts

module.exports = function(app) {
	app.middlewares = {
		authenticated: require('./authenticated')(app)
	};
};