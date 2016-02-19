/* Create user script
* Creates a new user
*
* Access route: POST /myeventmanager/users
* Middlewares used: NONE
* Nodes implemented: NONE
*/

module.exports = function(app) {
	return function(req, res, next) {
		if(!req.body || 
		   !req.body.firstname || !req.body.lastname || !req.body.email || !req.body.password)
		   return res.status(400).json({success: false, error: 'Missing fields are required'}); // 400 Bad Request
		var body = req.body;
		
		var User = app.models.User;
		
		// Is another user already subscribed with this email?
		User.findOne({email: body.email}, function(err, found) {
			if(err)
				return res.status(500).json({success: false, error: 'Internal server error'}); // 500 Internal Server Error
			if(found)
				return res.status(403).json({success: false, error: 'Email already in use'}); // 403 Forbidden
			
			// Create new user, encrypting their password, save in database
			var encPass = User.encryptPassword(body.password);
			var user = new User({
				firstname: body.firstname,
				lastname: body.lastname,
				email: body.email,
				password: encPass
			});
			
			// Create user
			user.save(function(err, done) {
				if(err || !done)
					return res.status(500).json({success: false, error: 'Internal server error'});
				
				res.status(200).json({success: true, id: user._id}); // 200 OK
			});
		});
	};
};