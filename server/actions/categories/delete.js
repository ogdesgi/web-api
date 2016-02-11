/* Delete category script
* Removes a category from the database.
* 
* Access route: DELETE /myeventmanager/categories/{cat_id}
* Middlewares used: authenticated (checks if user is logged in)
* Nodes implemented: NONE
*/

module.exports = function(app) {
	return function(req, res, next) {
		var catId = req.params.catid;
		
		var Category = app.models.Category;
		Category.findById({_id: catId}, function(err, category) {
			if(err || !category)
				return res.status(404).json({success: false, error: 'Category was not found'}); // 404 Not Found
			
			// Before removing, make sure no event is using this category
			var Event = app.models.Event;
			Event.findOne({category: category._id}, function(err, event) {
				if(err)
					return res.status(500).json({success: false, error: 'Internal server error'}); // 500 Internal Server Error
				if(event)
					return res.status(403).json({success: false, error: 'One or more events are linked to this category'}); // 403 Forbidden
				
				category.remove(function(err, done) {
					if(err || !done)
						return res.status(500).json({success: false, error: 'Internal server error'});
					
					res.status(204).json({success: true}); // 204 No Content
				});
			});
		});
	};
};