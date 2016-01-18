var path = require('path');

module.exports = function(app) {
	return function(req, res, next) {
		app.models.Event.findById(req.params.eventName, function(err, event) {
			if(err)
				return res.status(500).send(err);

			if(!req.file)
				return res.status(403).send();

			event.logo = req.logo;
			event.save(function(err, instance) {
				if(err)
					return res.status(500).send(err);

				res.send(instance);
			});
		});
	}
};