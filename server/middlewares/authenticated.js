module.exports = function(app){
  return function(req, res, next){
      var userId = req.session.userId;

      app.models.User.findOne({
          _id: userId
      }, function(err, instance){
          if (err)
            return res.status(500).send(err);

          if(!instance)
            return res.status(401).send('you must be authenticated');

          next();
      });
  }
};