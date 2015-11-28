module.exports = function(app) {
    return function(req, res){
        app.models.Todo.findById(req.params.id, function(err, instance){
            if (err)
                return res.status(500).send(instance);

            res.send(instance);
        })
    }
};
