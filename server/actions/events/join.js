/* Join script
* Adds a participant to an event
* Upon creation, an event only has one participant: its creator
*
* Access route: POST /myeventmanager/events/{evt_id}/join
* Middlewares used: authenticated (checks if user is logged in)
* Nodes implemented: NONE
*/

module.exports = function(app) {
	return function(req, res, next) {
		var evtId = req.params.evtid;
		var participant = req.user;
		
		// Participant ID is the connected user ID
		// It was already checked when verifying their token (cf. middleware)
		var Event = app.models.Event;
		Event.findOne({_id: evtId}, function(err, event) {
			if(err || !event)
				return res.status(404).json({success: false, error: 'Event was not found'}); // 404 Not Found
			if(event.creator.equals(participant._id))
				return res.status(403).json({success: false, error: 'Event was created by this user'}); // 403 Forbidden
			if(-1 < event.participants.indexOf(participant._id))
				return res.status(403).json({success: false, error: 'Already participating in this event'});
			
			event.participants.push(participant._id);
			
			event.save(function(err, done) {
				if(err || !done)
					return res.status(500).json({success: false, error: 'Internal server error'}); // 500 Internal Server Error
				
				var part = participant.firstname + ' ' + participant.lastname;
				res.status(200).json({success: true, joined: part}); // 200 OK
			});
		});
	};
};