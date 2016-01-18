module.exports = function(app) {
	app.actions = {
		events: require('./events')(app),
		categories: require('./categories')(app),
		users: require('./users')(app)
	};
};
