/* Retrieve user script
* Shows a user in the database
* 
* Access route: GET /myeventmanager/users/{user_id}
* Middlewares used: NONE
* Nodes implemented: NONE
*/

module.exports = function(app) {
	return function(req, res, next) {
		var userId = req.params.userid;
		var options = 'firstname lastname email';
		var User = app.models.User;
		
		User.findOne({_id: userId}, options, function(err, user) {
			if(err)
				return res.status(500).json({success: false, error: 'Internal server error'}); // 500 Internal Server Error
			if(!user)
				return res.status(404).json({success: false, error: 'User was not found'}); // 404 Not Found
			
			res.status(200).json({success: true, profile: user}); // 200 OK
		});
	};
};