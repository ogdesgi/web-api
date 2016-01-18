module.exports = function(app) {
	app.models.Category.findOneAndRemove({
		name: req.body.categoryName
	}, function(err, result) {
		if(err)
			return res.status(500).send(err);

		res.send(result);
	});
};