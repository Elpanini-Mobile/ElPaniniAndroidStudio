package be.samysah.elpanini.restApi


data class Broodjes (
    val id: String,
    val title: String,
    val description: String,
    val category_id: String,
    val category_name: String,
    val price: String,
)

