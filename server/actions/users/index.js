// Index file to redirect users actions

module.exports = function(app) {
	return {
		create: require('./create')(app),
		retrieve: require('./retrieve')(app),
		update: require('./update')(app),
		delete: require('./delete')(app)
	};
};