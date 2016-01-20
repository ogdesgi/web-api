var multer = require('multer');
var path = require('path');

var storage = multer.diskStorage({
    destination: function (req, file, cb) {
        cb(null, path.join(__dirname, '../storage'));
    },
    filename: function (req, file, cb) {
        var components = file.originalname.split('.');
        req.avatarUrl = 'avatar_' + req.session.userId.toString() + '.' + components[components.length - 1];
        cb(null, req.avatarUrl);
    }
});

module.exports = function(app){
    app.middlewares = app.middlewares || {};
    app.middlewares.upload = multer({ storage: storage });
};
