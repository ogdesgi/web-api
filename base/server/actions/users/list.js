module.exports = function(app){
  return function(req, res, next){
      app.models.User.find(function(err, users){
          if(err)
            return res.status(500).send(err);

          res.send(users);
      });
  }
};