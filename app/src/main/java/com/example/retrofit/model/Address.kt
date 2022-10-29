package com.example.retrofit.model

data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo:Geo
)

// "address": {
// "street": "Kulas Light",
// "suite": "Apt. 556",
// "city": "Gwenborough",
// "zipcode": "92998-3874",
// "geo": {
// "lat": "-37.3159",
// "lng": "81.1496"
// }