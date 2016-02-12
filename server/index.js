/* General index file to initiate module calls
*
* Nodes implemented: express (framework)
*/

require('./global');

var express = require('express');
var api = express();

(function init() {
	require('./settings')(api);
	require('./models')(api);
	require('./middlewares')(api);
	require('./actions')(api);
	require('./routes')(api);
}());

(function start() {
	api.use(express.static('storage'));
	api.listen(api.settings.port, '0.0.0.0');
	console.log('Server is listening to port :port'.replace(':port', api.settings.port));
	console.log('Server is connecting to :db'.replace(':db', api.settings.db));
}());