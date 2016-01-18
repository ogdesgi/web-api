module.exports = function(app) {
	return function(req, res, next) {
		app.models.Category.findOneAndUpdate({
			name: req.body.name
		}, function(err, category) {
			if(err)
				return res.status(500).send(err);

			res.send(category);
		})
	}
};