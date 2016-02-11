/* Retrieve category script
* Shows a category from the database
* 
* Access route: GET /myeventmanager/categories/{cat_id}
* Middlewares used: NONE
* Nodes implemented: NONE
*/

module.exports = function(app) {
	return function(req, res, next) {
		var catId = req.params.catid;
		
		var Category = app.models.Category;
		Category.findOne({_id: catId}, function(err, category) {
			if(err || !category)
				return res.status(404).json({success: false, error: 'Category was not found'})
			
			res.status(200).json({success: true, profile: category});
		});
	};
};