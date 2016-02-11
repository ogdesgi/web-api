/* List categories script
* Shows all categories in the database.
* 
* Access route: GET /myeventmanager/categories
* Middlewares used: NONE
* Nodes implemented: NONE
*/

module.exports = function(app) {
	return function(req, res, next) {
		var Category = app.models.Category;
		
		Category.find(function(err, categories) {
			if(err)
				return res.status(500).json({success: false, error: 'Internal server error'});
			
			res.status(200).json({success: true, list: categories});
		});
	};
};