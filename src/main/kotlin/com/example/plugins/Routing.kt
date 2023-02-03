package com.example.plugins

import com.example.models.Note
import com.example.repositories.NotesRepository
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*

fun Application.configureRouting() {
    routing {
        route("/notes") {
            get {
                call.respond(NotesRepository.getAll())
            }
            get("/{id}") {
                val id = call.parameters["id"]?.toLongOrNull()
                if (id != null) {
                    val note = NotesRepository.getById(id)
                    if (note != null) {
                        call.respond(note)
                    } else {
                        call.respondText(
                            "Note not found",
                            status = io.ktor.http.HttpStatusCode.NotFound
                        )
                    }
                } else {
                    call.respondText("Invalid id", status = io.ktor.http.HttpStatusCode.BadRequest)
                }
            }
            post {
                try {
                    val note = call.receive<Note>()
                    NotesRepository.upsert(note)
                    call.respondText("Note saved correctly", status = io.ktor.http.HttpStatusCode.Accepted)
                } catch (ex: Exception) {
                    call.respondText(
                        "Invalid note format",
                        status = io.ktor.http.HttpStatusCode.BadRequest
                    )
                }
            }
            delete {
                val id = call.parameters["id"]?.toLongOrNull()
                if (id != null) {
                    if (NotesRepository.delete(id)) {
                        call.respondText("Note deleted correctly", status = io.ktor.http.HttpStatusCode.Accepted)
                    } else {
                        call.respondText("Note not found", status = io.ktor.http.HttpStatusCode.NotFound)
                    }
                } else {
                    call.respondText("Invalid id", status = io.ktor.http.HttpStatusCode.BadRequest)
                }
            }
        }
    }
}
