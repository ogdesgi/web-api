module.exports = function(app) {
	app.actions = {
        auth: require('./auth')(app),
		events: require('./events')(app),
		categories: require('./categories')(app),
		users: require('./users')(app)
	};
};
