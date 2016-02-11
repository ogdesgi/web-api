/* Update category script
* Edits a category in the database.
* 
* Access route: PUT /myeventmanager/category/{cat_id}
* Middlewares used: authenticated (checks if user is logged in)
* Nodes implemented: fs (handle OS filesystem)
*/

module.exports = function(app) {
	return function(req, res, next) {
		if(!req.body || !req.body.name)
			return res.status(400).json({success: false, error: 'Nothing to update'});
		
		var body = req.body;
		var catId = req.params.catid;
		
		var Category = app.models.Category;
		Category.findById({_id: catId}, function(err, category) {
			if(err || !category)
				return res.status(404).json({success: false, error: 'Category was not found'});
			
			category.name = body.name;
			category.save(function(err, done) {
				if(err || !done)
					return res.status(500).json({success: false, error: 'Internal server error'});
				
				res.json({success: true, changed: category.name});
			});
		});
		
	};
};