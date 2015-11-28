module.exports = function(app) {
    return function(req, res, next){
        var todo = new app.models.Todo({
            title: req.body.title
        });

        todo.save(function(err, instance){
            if(err)
                return res.status(500).send(err);
            res.send(instance);
        })
    }
};