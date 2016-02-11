/* List events script
* Shows all events in the database
* 
* Access route: GET /myeventmanager/events
* Middlewares used: NONE
* Nodes implemented: NONE
*/

module.exports = function(app) {
	return function(req, res, next) {
		var Event = app.models.Event;
		
		Event.find(function(err, events) {
			if(err)
				return res.status(500).json({success: false, error: 'Internal server error'}); // 500 Internal Server Error
			
			res.status(200).json({success: true, list: events}); // 200 OK
		});
	};
};