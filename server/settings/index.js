var yargs = require('yargs');

module.exports = function(app){
    var DEFAULT_ENV = 'development';
    var env = yargs
            .alias('e', 'env')
            .argv
            .env || DEFAULT_ENV;

    app.settings = require('./' + env);
};
