/* Leave script
* Removes a participant from an event
* The event's creator cannot leave their own event
*
* Access route: POST /myeventmanager/events/{evt_id}/leave
* Middlewares used: authenticated (checks if user is logged in)
* Nodes implemented: NONE
*/

module.exports = function(app) {
	return function(req, res, next) {
		var evtId = req.params.evtid;
		var participant = req.user;
		var part = participant.firstname + ' ' + participant.lastname;
		
		// Participant ID is the connected user ID
		// It was already checked when verifying their token (cf. middleware)
		var Event = app.models.Event;
		Event.findOne({_id: evtId}, function(err, event) {
			if(err || !event)
				return res.status(404).json({success: false, error: 'Event was not found'}); // 404 Not Found
			if(event.creator.equals(participant._id)) // Because equals operator does not work with objects
				return res.status(403).json({success: false, error: 'Event was created by this user'}); // 403 Forbidden
			if(-1 === event.participants.indexOf(participant._id)) // === is deep equality (equal value and equal type)
				return res.status(403).json({success: false, error: 'Not participating in this event'});
			
			event.participants.pull(participant._id);
			event.participantsNames.pull(part);
			
			event.save(function(err, done) {
				if(err || !done)
					return res.status(500).json({success: false, error: 'Internal server error'}); // 500 Internal Server Error
				
				res.status(200).json({success: true, left: part}); // 200 OK
			});
		});
	};
};