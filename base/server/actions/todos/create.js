module.exports = function(app) {
    return function(req, res, next){
        console.log(req.session.userId);
        var todo = new app.models.Todo({
            title: req.body.title,
            userId: req.session.userId
        });

        todo.save(function(err, instance){
            if(err)
                return res.status(500).send(err);
            res.send(instance);
        })
    }
};