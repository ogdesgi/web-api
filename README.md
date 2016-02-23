#Event Manager API

##Requirements
MongoDB - Database Manager

##How to start
1. Clone this repository in your desktop
2. Start your MongoDB server using `mongod`
3. Start the cloned API by going to its directory and using `node server`

##Presentation

This API is meant to manage user-created events. Any user can join an event, and also leave it. An event has a category so as to better identify the kind of event the user is participating in (or creating).

This API is said to be RESTful, as it respects the REST architecture. REST stands for REpresentational State Transfer and allows for modularity inside an API. This means there is a separation between the models (entities) used in the application and the network routes needed to execute actions on them.

##Models

For all the following models, their properties are listed according to this pattern:

* NAME (TYPE) : DESCRIPTION

The types are related to the Javascript language.

###User

A User represents a person who can be connected to the API to manage events.

* firstname (String) : the User's surname.
* lastname (String) : the User's family name.
* email (String) : the User's email address.
* password (String) : the User's password. It has no particular server-side limitations.

###Category

A type that applies to an event when it is created. For example, Sports, Games, etc.

* name (String) : the category's name.

###Event

An event is created by a user, who becomes its creator and only participant by default. It can be joined by other users, who also have the choice to leave, except its creator.

* title (String) : the event's designation.
* description (String) : a short explanation of the nature of the event.
* date (Date) : the date the event is said to be hold.
* logo (String) : an image accompanying the event. (Optionnal)
* category (Category) : the category ID the event belongs to.
* categoryName (Category) : the name of the category the event belongs to.
* creator (User) : the event's creator ID.
* creatorName (String) : the name of the event's creator.
* participants ([User]) : the IDs of all the participants to the event. By default, there is only the creator's ID.
* participantsNames ([String]) : the names of the participants to the event. By default, there is only the creator's full name.

The ODM Mongoose manages the models. When a new instance of one of them is created, there is automatically a _id property of type ObjectId created.

##How to use

The API receives HTTP requests from a client and executes actions based on the URL it was given.

For all actions, the base URL is: `http://host:8080/myeventmanager`, with host being the name or the IP of your server.

The requests are testable using a HTTP client like Postman for Chrome, or Firefox Developper Tools.

Here are listed all actions available at the moment:

###Authentication route
#####`POST /auth/login`
Connects a user to the application.
An authentication middleware checks the user's credentials, comparing them in the database. If they are correctly associated with the user, they are given a 1-hour access token from the server to access all its functionalities.

The following requests (except the GET ones) all use the authentication middleware. Consequently, in order to use them, a user must be logged in first and have a valid token. As such, every request requires a 'Authorization' header in which is stored the access token in this format: `Bearer ACCESS_TOKEN`

###Users routes
#####`POST /users`
Creates a new user.

#####`GET /users/{user_id}`
Retrieves information on a user from their ID. The user's _id property is added to the URL as {user_id}.

#####`PUT /users/{user_id}`
Updates information on a user from their ID. All their properties are modifyable.

#####`DELETE /users/{user_id}`
Removes a user from the database. To avoid security issues, only the owner of the user account can delete it.

###Categories routes
#####`GET /categories`
Lists all categories created in the application.

#####`POST /categories`
Creates a new category.

#####`GET /categories/{cat_id}`
Retrieves information on a category from its ID. The category's _id property is added to the URL as {user_id}.

#####`PUT /categories/{cat_id}`
Updates a category from its ID.

#####`DELETE /categories/{cat_id}`
Removes a category from the database. This is only possible if NO EVENTS are using it.

###Events routes
#####`GET /events`
Lists all events created in the application.

#####`POST /events`
Creates a new event. The user must give it a title, a description and a category. Optionnaly, they can provide a logo and a date for the event. By default, an event has no logo, and is said to be held at the date of its creation. The list of participants of a newly-created event will only include its creator (ID and full name).

#####`GET /events/{evt_id}`
Retrieves information on an event from its ID.

#####`PUT /events/{evt_id}`
Updates an event from its ID. Its title, description, logo and date are the only properties that are modifyable.

#####`DELETE /events/{evt_id}`
Removes an event from the database. This has the effect of automatically removing every user's participation to this event. This action is only possible for the creator of the targeted event.

#####`POST /events/{evt_id}/join`
Registers the connected user to the event of ID {evt_id}. This is automatic for the creators of an event. A user has no limitations regarding the number of events they can join.

#####`POST /events/{evt_id}/leave`
Unregisters the connected user to the event of ID {evt_id}. This is not possible for the creator of said event.
