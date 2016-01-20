module.exports = {
    port: 8080,
    db: 'mongodb://localhost:27017/todo-list-api',
    sessionDb: 'mongodb://localhost:27017/todo-list-session-db',
    sessionTTL: 2 * 24 * 60 * 60
};