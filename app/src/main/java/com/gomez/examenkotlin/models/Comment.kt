package com.gomez.examenkotlin.models

data class Comment (
    val user_id : Int,
    val username : String,
    val user_image : String,
    val comment : String
)