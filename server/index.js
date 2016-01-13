require('./global');
var express = require('express');
var api = express();

(function init(){
	require('./settings')(api);
	require('./models')(api);
	require('./middlewares')(api);
	require('./actions')(api);
	require('./routes')(api);
}());

(function start(){
	api.listen(api.settings.port, '0.0.0.0');
	console.log('Server is listening on port :port'.replace(':port', api.settings.port));
	console.log('Server is connecting to database :db'.replace(':db', api.settings.db));
}());
