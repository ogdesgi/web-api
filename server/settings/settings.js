// Settings for database connection, token signing and password encryption

module.exports = {
	port: 8080,
	db: 'mongodb://localhost:27017/MEMDatabase',
	tokenSecret: '75ee24e0671f455f04561db645f83c16',
	tokenTTL: '1h', // 1 hour in ms.js format
	passwordAddOn: 'MASTOKEN'
};