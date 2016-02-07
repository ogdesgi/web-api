module.exports = function(app){
    return function(req, res, next){
        app.models.User.findOne({
            username: req.body.username,
            password: req.body.password
        }, function(err, instance){
            if(err)
                return res.status(500).send(err);

            if(!instance)
                return res.status(404).send('account not found.');

            req.session.userId = instance.id;
            res.send(instance);
        })
    };
};