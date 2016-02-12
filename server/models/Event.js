/* Event model
* Represents an event
*	title: the event designation
*	description: a quick presentation of the event
*	date: the date the event will be held
*	logo: an image associated with the event
*	creator: the User in charge of the event
*	category: the Category of the event
*	participants: the list of Users who have joined the event
*/

module.exports = function(app) {
	var EventSchema = app.mongoose.Schema({
		title: { type: String, required: true },
		description: { type: String, required: true },
		date: { type: Date, default: Date.now, required: true },
		logo: { type: String },
		creator: { type: app.mongoose.Schema.ObjectId, ref: 'User', required: true },
		category: { type: app.mongoose.Schema.ObjectId, ref: 'Category', required: true },
		participants: [{ type: app.mongoose.Schema.ObjectId, ref: 'User', required: true }]
	});
	
	EventSchema.plugin(require('mongoose-timestamp'));
	var Event = app.mongoose.model('Event', EventSchema);
	
	return Event;
};