var mongoose = require('mongoose');

module.exports = function(app){
	app.mongoose = mongoose.connect(app.settings.db);

	app.models = {
		Event: require('./Event')(app),
		Category: require('./Category')(app),
		User: require('./User')(app)
	};
};
