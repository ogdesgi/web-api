// Nodes implemented: express.Router (handles request methods), body-parser (handles request body)

var router = require('express').Router();
var bodyParser = require('body-parser').json();

module.exports = function(app) {
	/* users routes
	*	POST 	/myeventmanager/users			=> Create a new user
	*	GET 	/myeventmanager/users/{user_id} => Retrieve user info
	*	PUT 	/myeventmanager/users/{user_id} => Update user info
	*	DELETE 	/myeventmanager/users/{user_id} => Delete user
	*/
	
	router.post('/', bodyParser, app.actions.users.create);
	router.get('/:userid', app.actions.users.retrieve);
	router.put('/:userid', bodyParser, app.middlewares.authenticated, app.actions.users.update);
	router.delete('/:userid', app.middlewares.authenticated, app.actions.users.delete);
	
	return router;
};