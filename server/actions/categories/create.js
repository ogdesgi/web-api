/* Create category script
* Creates a new category, shared among all registered users
*
* Access route: POST /myeventmanager/categories
* Middlewares used: authenticated (checks if user is logged in)
* Nodes implemented: NONE
*/

module.exports = function(app) {
	return function(req, res, next) {
		if(!req.body || !req.body.name)
			res.status(400).json({success: false, error: 'Missing fields are required'}); // 400 Bad Request
		var body = req.body;
		
		// Is this category already existing?
		var Category = app.models.Category;
		Category.findOne({name: body.name}, function(err, found) {
			if(err)
				return res.status(500).json({success: false, error: 'Internal server error'}); // 500 Internal Server Error
			if(found)
				return res.status(403).json({success: false, error: 'This category already exists'}); // 403 Forbidden
			
			// Create category
			var category = new Category({ name: body.name });
			category.save(function(err, instance) {
				if(err || !instance)
					return res.status(500).json({success: false, error: 'Internal server error'});
				
				res.status(200).json({success: true, id: instance._id}); // 200 OK
			});
		});
	};
};