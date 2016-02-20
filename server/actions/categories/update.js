/* Update category script
* Edits a category in the database
* 
* Access route: PUT /myeventmanager/category/{cat_id}
* Middlewares used: authenticated (checks if user is logged in)
* Nodes implemented: NONE
*/

module.exports = function(app) {
	return function(req, res, next) {
		if(!req.body || !req.body.name)
			return res.status(400).json({success: false, error: 'Nothing to update'}); // 400 Bad Request
		
		var body = req.body;
		var catId = req.params.catid;
		
		// Is there a Category with this ID?
		var Category = app.models.Category;
		Category.findById({_id: catId}, function(err, category) {
			if(err || !category)
				return res.status(404).json({success: false, error: 'Category was not found'}); // 404 Not Found
			
			// Is the name in the request body already given to another category?
			Category.findOne({name: body.name}, function(err, cat) {
				if(err)
					return res.status(500).json({success: false, error: 'Internal server error'}); // 500 Internal Server Error
				if(cat)
					return res.status(403).json({success: false, error: 'Category with this name already exists'}); // 403 Forbidden
				
				// Update category
				category.name = body.name;
				category.save(function(err, done) {
					if(err || !done)
						return res.status(500).json({success: false, error: 'Internal server error'});
					
					res.status(200).json({success: true, changed: category.name}); // 200 OK
				});				
			});
		});
	};
};