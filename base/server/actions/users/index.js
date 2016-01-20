module.exports = function(app){
    return {
        create: require('./create')(app),
        list: require('./list')(app),
        uploadAvatar: require('./uploadAvatar')(app)
    };
};