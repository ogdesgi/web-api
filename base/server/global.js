global._ = require('lodash');
global.Promise = require('bluebird');
global.PromisifyAll = function(obj){ Promise.promisifyAll(obj, {suffix: "P"}); return obj};