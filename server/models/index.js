/* Index file to redirect models definition scripts
*
* Nodes implemented: mongoose (schema and model definition)
*
* NOTE: Mongoose objects carry an _id by default. It is of ObjectId type (see Mongoose documentation).
*/

var mongoose = require('mongoose');

module.exports = function(app) {
	app.mongoose = mongoose.connect(app.settings.db);
	
	app.models = {
		User: require('./User')(app),
		Event: require('./Event')(app),
		Category: require('./Category')(app)
	};
};