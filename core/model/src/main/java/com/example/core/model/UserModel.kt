package com.example.core.model

/** * WARNING: This is mock object model */
data class UserModel(val id: String, val name: String, val email: String ) {
    companion object {
        val default = UserModel(id = "0", name = "Default User", email = "")
    }
}