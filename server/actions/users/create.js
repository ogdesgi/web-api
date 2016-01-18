module.exports = function(app) {
	return function(req, res, next) {
		var user = new app.models.User({
			surname: req.body.surname,
			firstName: req.body.firstName,
			email: req.body.email,
			password: req.body.password
		});

		user.password = app.middlewares.secure(app, user.password);

		user.save(function(err, insatance) {
			if(err)
				return res.status(500).send(err);

			res.send(instance);
		});
	}
};