module.exports = function(app) {
	return function(req, res, next) {
		var category = new app.models.Category({
			name: req.body.name
		});

		category.save(function(err, instance) {
			if(err)
				return res.status(500.send(err);

			res.send(instance);
		});
	}
};