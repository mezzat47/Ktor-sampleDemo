package com.example

data class UserData(
    val name:String,
    val email:String
)

val user1 = UserData(
    "Mohamed Ezzat",
    "mezzat@sample.com"
)

val user2 = UserData(
    "Hassan",
    "abiAli@sample.com"
)

var users = listOf(user1, user2)