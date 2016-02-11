/* User model
* Represents a user
*	firstname: the user's surname
*	lastname: the user's family name
*	email: the user's email address
*	password: the user's password
*
* NOTE: For security purposes, the user password is encrypted in the database
*
* Nodes implemented: md5 (password encryption)
*/

var md5 = require('md5');

module.exports = function(app) {

	var UserSchema = app.mongoose.Schema({
		firstname: { type: String, required: true },
		lastname: { type: String, required: true },
		email: { type: String, required: true },
		password: { type: String, select: false, required: true }
	});

	// Static method to encrypt the password when creating or modifying a user profile
	UserSchema.statics.encryptPassword = function(pwd) {
		return md5(pwd + app.settings.passwordAddOn);
	};

	// Instance method to compare the given password with the stored one when logging in
	UserSchema.methods.verifyPassword = function(candidate, cb) {
		return this.model('User').findOne({ password: candidate}, cb);
	};

	UserSchema.plugin(require('mongoose-timestamp'));
	var User = app.mongoose.model('User', UserSchema);
	
	return User;
};