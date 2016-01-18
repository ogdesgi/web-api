module.exports = {
	port: 8080,
	db: 'mongodb://192.168.x.x:27017/dev-api',
	sessionDb: 'mongodb://192.168.x.x:27017/dev-api-session',
	sessionTTL: 2 * 24 * 60 * 60
};
