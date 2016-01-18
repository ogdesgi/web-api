module.exports = function(app) {
	return function(req, res, next) {
		var event = new app.models.Event({
			name: req.body.name,
			description: req.body.description,
			logo: req.body.logo,
			category: req.body.categoryName,
			creator: req.session.id,
		});

		// TODO: check existence of a category by its name
		/*
		event.pre('save', function(next) {
			app.models.Category.findOne({
				name: req.body.categoryName
			}, function(err) {
				if(err)
					return res.status(404).send('Category :cat does not exist'.replace(':cat', req.body.categoryName));
			});

			next();
		});
		*/

		event.save(function(err, instance) {
			if(err)
				return res.status(500).send(err);

			res.send(instance);
		});
	}
};