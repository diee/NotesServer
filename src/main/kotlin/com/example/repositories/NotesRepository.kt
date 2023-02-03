package com.example.repositories

import com.example.models.Note

object NotesRepository {

    private val notes = mutableListOf<Note>()

    init {
        notes.add(Note(1, "First note", "This is the first note"))
        notes.add(Note(2, "Second note", "This is the second note"))
    }

    fun getAll() = notes.toList()

    fun getById(id: Long) = notes.find { it.id == id }

    fun upsert(note: Note) {
        if (note.id == 0L) {
            notes.add(note.copy(id = notes.size + 1L))
        } else {
            val index = notes.indexOfFirst { it.id == note.id }
            if (index != -1) {
                notes[index] = note
            }
        }
    }

    fun delete(id: Long): Boolean {
        val index = notes.indexOfFirst { it.id == id }
        if (index != -1) {
            notes.removeAt(index)
            return true
        }
        return false
    }
}