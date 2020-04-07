package com.example

import io.ktor.application.call
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route

fun Routing.userApi() {
    route("/users", HttpMethod.Get) {
        handle {
            call.respond(users)
        }
    }

    route("/users/{id}", HttpMethod.Get) {
        handle {
            val id = call.parameters["id"]!!
            try {
                val user: UserData = users[id.toInt()]
                call.respond(user)
            } catch (e: Throwable) {
                call.respond(HttpStatusCode.NotFound)
            }

        }
    }

    route("/users", HttpMethod.Post){
        handle{
            val user = call.receive<UserData>()
            val newUser = UserData(user.name, user.email)
            users += newUser
            call.respond(HttpStatusCode.Created, users )
        }
    }

    route("/users/{id}", HttpMethod.Delete) {
        handle {
            val id = call.parameters["id"]!!
            try {
                val foundUser = users.getOrNull(id.toInt())
                if(foundUser == null) {
                    call.respond(HttpStatusCode.NotFound)
                }
            } catch (e: Throwable) {
                call.respond(HttpStatusCode.BadRequest)
            }

        }
    }



}