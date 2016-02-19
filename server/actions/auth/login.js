/* Login script
* Gives the user a 1-hour access token if they are registered in the database.
* A valid token must be used to create, modify or delete categories and events.
* 
*
* Access route : POST /myeventmanager/auth/login
* Middlewares used : NONE
* Nodes implemented : jsonwebtoken (handles token creation)
*/

var jwt = require('jsonwebtoken');

module.exports = function(app) {
	return function(req, res, next) {
		if(!req.body || 
		   !req.body.email || !req.body.password)
		   return res.status(400).json({success: false, error: 'Missing fields are required'}); // 400 Bad Request
		var body = req.body;
		
		var settings = app.settings;
		
		var User = app.models.User;
		
		// Check user credentials with verify static method
		var encPass = User.encryptPassword(body.password);
		User.verify(body.email, encPass, function(err, user) {
			if(err || !user)
				return res.status(404).json({success: false, error: 'Invalid email or password'}); // 404 Bad Request
			
			// If user was found, then apply token on user ID, expires in 1 hour
			var token = jwt.sign({_id: user._id}, settings.tokenSecret, {expiresIn: settings.tokenTTL});
			
			res.status(200).json({success: true, token: token, expires: settings.tokenTTL, id: user._id}); // 200 OK
		});
	};
};