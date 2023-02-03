package com.example.models

@kotlinx.serialization.Serializable
data class Note(val id: Long? = 0, val title: String, val content: String)
