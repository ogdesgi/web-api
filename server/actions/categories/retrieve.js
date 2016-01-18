module.exports = function(app) {
	return function(req, res, next) {
		app.models.Category.findOne({
			name: req.body.categoryName
		}, function(err, instance) {
			if(err)
				return res.status(500).send(err);

			res.send(instance);
		});
	}
};