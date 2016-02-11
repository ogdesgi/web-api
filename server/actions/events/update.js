/* Update event script
* Edits an event in the database
* Only its title, description, date and logo are editable
* 
* Access route: PUT /myeventmanager/events/{evt_id}
* Middlewares used: authenticated (checks if user is logged in)
* Nodes implemented: fs (handle OS filesystem)
*/

var fs = require('fs');

module.exports = function(app) {
	return function(req, res, next) {
		if(!req.body || 
		   (!req.body.title && !req.body.description && !req.body.date && !req.file))
			return res.status(400).json({success: false, error: 'There is nothing to update'}); // 400 Bad Request
		var body = req.body;
		
		var evtId = req.params.evtid;
		
		// Check existence of event based on its ID
		var Event = app.models.Event;
		Event.findOne({_id: evtId}, function(err, event) {
			if(err || !event)
				return res.status(404).json({success: false, error: 'Event was not found'}); // 404 Not Found
			if(!event.creator.equals(req.user._id))
				return res.status(403).json({success: false, error: 'Event can only be modified by its creator'}); // 403 Forbidden
			
			var changes = {};
			if(body.title) {
				event.title = body.title;
				changes.title = event.title;
			}
			if(body.description) {
				event.description = body.description;
				changes.description = event.description;
			}
			if(body.date) {					
				event.date = body.date;
				changes.date = event.date;
			}
			if(req.file) {
				if(event.logo)
					fs.unlink('storage/' + event.logo); // Removes the previous logo if there was one
				event.logo = req.file.filename;
				changes.logo = event.logo;
			}
			
			event.save(function(err, done) {
				if(err || !done)
					return res.status(500).json({success: false, error: 'Internal server error'}); // 500 Internal Server Error
				
				res.status(200).json({success: true, changed: changes}); // 200 OK
			});
		});
	};
};