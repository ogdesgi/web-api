/* Create event script
* Creates a new event, linked to a category and a user
* When created, its creator automatically counts as a participant
*
* Access route: POST /myeventmanager/events
* Middlewares used: authenticated (checks if user is logged in)
* Nodes implemented: NONE
*/

module.exports = function(app) {
	return function(req, res, next) {
		if(!req.body || 
		   !req.body.title || !req.body.description || !req.body.category)
			return res.status(400).json({success: false, error: 'Missing fields are required'}); // 400 Bad Request
		var body = req.body;
		
		// Is this event already existing?
		var Event = app.models.Event;
		Event.findOne({title: body.title}, function(err, found) {
			if(err)
				return res.status(500).json({success: false, error: 'Internal server error'}); // 500 Internal Server Error
			if(found)
				return res.status(403).json({success: false, error: 'This event already exists'}); // 403 Forbidden
			
			var userFullname = req.user.firstname + ' ' + req.user.lastname;
			
			// Does the provided category exist?
			var Category = app.models.Category;
			Category.findOne({name: req.body.category}, function(err, cat) {
				if(err || !cat)
					return res.status(404).json({success: false, error: 'Category was not found'}); // 404 Not Found
				
				var event = new Event({
					title: body.title,
					description: body.description,
					creator: req.user,
					creatorName: userFullname,
					category: cat,
					categoryName: cat.name,
					participants: [req.user],
					participantsNames: [userFullname]
				});
				
				// By default, the event is said to be held at the date of its creation
				if(body.date)
					event.date = body.date;
				
				// By default, an event can be missing a logo
				if(req.file)
					event.logo = req.file.filename;
				
				// Create event
				event.save(function(err, instance) {
					if(err || !instance)
						return res.status(500).json({success: false, error: 'Internal server error'});
					
					res.status(200).json({success: true, id: instance._id}); // 200 OK
				});
			});
		});
	};
};