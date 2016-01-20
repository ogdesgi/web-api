module.exports = function(app){
    var UserSchema = app.mongoose.Schema({
        username: {
            type: String,
            required: true
        },
        password: {
            type: String,
            required: true
        },
        avatarUrl: {
            type: String,
            default: '/default_avatar.png'
        }
    });

    UserSchema.plugin(require('mongoose-timestamp'));

    var User = app.mongoose.model('User', UserSchema);
    return User;
};