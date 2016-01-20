var path = require('path');

module.exports = function(app) {
    return function(req, res, next) {
        app.models.User.findById(req.session.userId, function(err, user) {
            if (err)
                return res.status(500).send(err);

            if (!req.file)
                return res.status(403).send();

            user.avatarUrl = req.avatarUrl;
            user.save(function(err, instance) {
                if (err)
                    return res.status(500).send(err);
                res.send(instance);
            })
        })
    };
};
