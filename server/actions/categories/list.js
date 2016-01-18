module.exports = function(app) {
	return function(req, res, next) {
		app.models.Category.find(function(err, categories) {
			if(err)
				return res.status(500).send(err);

			res.send(categories);
		});	
	}
};