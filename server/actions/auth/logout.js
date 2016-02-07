module.exports = function(app){
    return function(req, res, next){
        delete req.session.userId;
        res.send();
    };
};