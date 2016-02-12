/* Category model
* Represents the category of an event
*	name: name of the category
*/

module.exports = function(app) {
	var CategorySchema = app.mongoose.Schema({
		name: { type: String, required: true }
	});
	
	CategorySchema.plugin(require('mongoose-timestamp'));
	var Category = app.mongoose.model('Category', CategorySchema);
	
	return Category;
};