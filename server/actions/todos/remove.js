module.exports = function(app) {
    return function(req, res, next){
        app.models.Todo.findOneAndRemove({
                title: req.params.title
            }, function(err, result){
                if(err)
                    return res.status(500).send(err);

                res.send(result);
            });
    }
};