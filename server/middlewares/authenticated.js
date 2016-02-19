/* Authentication middleware script
* Checks existence of an access token normally given to a user upon logging in
* An access token can last for 1 hour until it expires and becomes invalid
*
* All POST, PUT and DELETE requests ask for user authentication to be used
* The user must provide an Authorization header in the request
* The header must contain "Bearer ACCESS_TOKEN" where ACCESS_TOKEN is a hexadecimal string
*
* Nodes implemented: jsonwebtoken (handles token verification)
*/

var jwt = require('jsonwebtoken');

module.exports = function(app) {
	return function(req, res, next) {
		if(!req.get('Authorization')) {
			return res.status(400).json({success: false, error: 'Request needs Authorization header'}); // 400 Bad Request
		}
		
		// Parse the header into an array (split)
		// Point of separation will be the whitespace
		var header = req.get('Authorization').split(' ');
		if(!header || 'Bearer' !== header[0])
			return res.status(401).json({success: false, error: 'Header format is invalid'}); // 401 Unauthorized
		var accessToken = header[1];
		
		try {
			// Decode the access token to retrieve the user ID used to sign it (cf. login script)
			var decoded = jwt.verify(accessToken, app.settings.tokenSecret);
		} catch(err) {
			// An error happens if the decoded token is not linked to a valid user ID or if it has expired
			return res.status(401).json({success: false, error: 'Invalid token'});
		}
		
		// Check if retrieved ID belongs to a User
		var User = app.models.User;
		User.findOne({_id: decoded._id}, function(err, found) {
			if(err || !found)
				return res.status(401).json({success: false, error: 'Invalid token'});
			
			// Giving found user to 'req.user' object is useful for later routes
			req.user = found;
			next(); // Await next call
		});
	};
};