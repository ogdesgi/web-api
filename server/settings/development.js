module.exports = {
    port: 8080,
    db: 'mongodb://localhost:27017/coderpower-api-v3',
    sessionDb: 'mongodb://localhost:27017/coderpower-dev-session-db',
    cache: {
        host: '127.0.0.1',
        port: 6379,
        password: '',
        expiration: 50
    },
    logger:{
        target: 'console',
        directory: ''
    }
};