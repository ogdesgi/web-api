// Index file to redirect categories actions

module.exports = function(app) {
	return {
		create: require('./create')(app),
		retrieve: require('./retrieve')(app),
		update: require('./update')(app),
		delete: require('./delete')(app),
		list: require('./list')(app)
	};
};