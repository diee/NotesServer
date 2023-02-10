## About the project

This is a simple server CRUD created to use be it in other app.
Created using Kotlin and Ktor.


### Getting Started

Download the project and run it, you probably have to install Intellij for this.

It doesn't have a database implemented, so every time you run the app your saved data will be lost.

#### Endpoints

###### Get all notes
```
GET http://0.0.0.0:8080/notes

[
    {
        "id": 1,
        "title": "First note",
        "content": "This is the first note"
    },
    {
        "id": 2,
        "title": "Second note",
        "content": "This is the second note"
    }
]

```

###### Get note by Id
```
GET http://0.0.0.0:8080/notes/1

{
    "id": 1,
    "title": "First note",
    "content": "This is the first note"
}

```

###### Create or update note
```
POST http://0.0.0.0:8080/notes
```


###### Delete note
```
DELETE http://0.0.0.0:8080/notes/1
```
