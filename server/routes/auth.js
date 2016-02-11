// Nodes implemented: express.Router (handles request methods), body-parser (handles request body)

var router = require('express').Router();
var bodyParser = require('body-parser').json();

module.exports = function(app) {
	/* authentication routes
	*	POST	/myeventmanager/auth/login				=> Log in a user
	*	(Logout is determined by the tokenTTL parameter in server settings)
	*/
	
	router.post('/login', bodyParser, app.actions.auth.login);
	
	return router;
};