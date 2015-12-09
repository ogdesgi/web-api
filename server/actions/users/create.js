module.exports = function(app){
    return function(req, res, next){
        var user = new app.models.User({
            username: req.body.username,
            password: req.body.password
        });

        user.save(function(err, instance){
            if (err)
                return res.status(500).send(err);

            res.send(instance);
        });
    }
};