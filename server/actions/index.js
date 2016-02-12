// Index file to redirect actions

module.exports = function(app) {
	app.actions = {
		auth: require('./auth')(app),
		users: require('./users')(app),
		events: require('./events')(app),
		categories: require('./categories')(app)
	};
};