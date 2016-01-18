module.exports = function(app){
	var EventSchema = app.mongoose.Schema({
		name: {
			type: String,
			required: true
		},
		description: String,
		date: {
			type: Date,
			required: true
		},
		logo: {
			type: String,
			required: true
		},
		category: {
			type: app.mongoose.ObjectId,
			ref: 'Category',
			required: true
		},
		creator: {
			type: app.mongoose.ObjectId,
			ref: 'User',
			required: true
		},
		participants: {[
			type: app.mongoose.ObjectId,
			ref: 'User',
			default: []
		]}
	});
};
