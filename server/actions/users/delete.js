/* Delete user script
* Removes a user from the database
* Only the user themselves can do it
* 
* Access route: DELETE /myeventmanager/users/{user_id}
* Middlewares used: authenticated (checks if user is logged in)
* Nodes implemented: NONE
*/

module.exports = function(app) {
	return function(req, res, next) {
		var userId = req.params.userid;
		
		var User = app.models.User;
		User.findById({_id: userId}, function(err, user) {
			if(err)
				return res.status(500).json({success: false, error: 'Internal server error'}); // 500 Internal Server Error
			if(!user)
				return res.status(404).json({success: false, error: 'User was not found'});
			if(userId != req.user._id) // != is standard inequality (not equal value, regardless of type)
				return res.status(403).json({success: false, error: 'Not logged in as this user'}); // 403 Forbidden
			
			user.remove(function(err, done) {
				if(err || !done)
					return res.status(500).json('Internal server error');
				
				res.status(204).json({success: true}); // 204 No Content
			});
		});
	};
};