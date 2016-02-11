/* Delete event script
* Removes an event from the database
* Only the event's creator can do it
* 
* Access route: DELETE /myeventmanager/event/{evt_id}
* Middlewares used: authenticated (checks if user is logged in)
* Nodes implemented: NONE
*/

module.exports = function(app) {
	return function(req, res, next) {
		var evtId = req.params.evtid;
		
		var Event = app.models.Event;
		Event.findById({_id: evtId}, function(err, event) {
			if(err || !event)
				return res.status(404).json({success: false, error: 'Event was not found'}); // 404 Not Found
			if(!event.creator.equals(req.user._id))
				return res.status(403).json({success: false, error: 'Event can only be deleted by its creator'}); // 403 Forbidden
			
			event.remove(function(err, done) {
				if(err || !done)
					return res.status(500).json('Internal server error'); // 500 Internal Server Error
				
				res.status(204).json({success: true}); // 204 No Content
			});
		});
	};
};