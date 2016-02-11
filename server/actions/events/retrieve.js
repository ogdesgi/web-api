/* Retrieve event script
* Shows an event from the database
* 
* Access route: GET /myeventmanager/events/{evt_id}
* Middlewares used: NONE
* Nodes implemented: NONE
*/

module.exports = function(app) {
	return function(req, res, next) {
		var evtId = req.params.evtid;
		
		var Event = app.models.Event;
		Event.findOne({_id: evtId}, function(err, event) {
			if(err || !event)
				return res.status(404).json({success: false, error: 'Event was not found'}); // 404 Not Found
			
			res.status(200).json({success: true, profile: event}); // 200 OK
		});
	};
};