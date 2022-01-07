package be.samysah.elpanini.restApi


data class Gebruiker (
    val id: Int = Int.MIN_VALUE,
    val username: String,
    val password: String,
    val email: String
)


