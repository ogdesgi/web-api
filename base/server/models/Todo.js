module.exports = function(app){
    var TodoSchema = app.mongoose.Schema({
        title: {
            type: String,
            required: true
        },
        userId: {
            type: app.mongoose.Schema.ObjectId,
            ref: 'User',
            required: true
        }
    });

    TodoSchema.plugin(require('mongoose-timestamp'));

    var Todo = app.mongoose.model('Todo', TodoSchema);
    return Todo;
};