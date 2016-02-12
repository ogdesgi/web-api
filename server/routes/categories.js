// Nodes implemented: express.Router (handles request methods), body-parser (handles request body)

var router = require('express').Router();
var bodyParser = require('body-parser').json();

module.exports = function(app) {
	/* categories routes
	*	GET 	/myeventmanager/categories 			=> List all categories
	*	POST 	/myeventmanager/categories 			=> Create a new category
	*	GET 	/myeventmanager/categories/{cat_id} => Retrieve a category
	*	PUT 	/myeventmanager/categories/{cat_id} => Update a category
	*	DELETE 	/myeventmanager/categories/{cat_id} => Delete a category
	*/
	
	router.get('/', app.actions.categories.list);
	router.post('/', bodyParser, app.middlewares.authenticated, app.actions.categories.create);
	router.get('/:catid', app.actions.categories.retrieve);
	router.put('/:catid', bodyParser, app.middlewares.authenticated, app.actions.categories.update);
	router.delete('/:catid', app.middlewares.authenticated, app.actions.categories.delete);
	
	return router;
};