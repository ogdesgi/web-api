// Index file to redirect routes

module.exports = function(app) {
	app.use('/myeventmanager/auth', require('./auth')(app));
	app.use('/myeventmanager/users', require('./users')(app));
	app.use('/myeventmanager/events', require('./events')(app));
	app.use('/myeventmanager/categories', require('./categories')(app));
};