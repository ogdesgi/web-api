// Index file to redirect auth actions

module.exports = function(app) {
	return {
		login: require('./login')(app)
	};
};