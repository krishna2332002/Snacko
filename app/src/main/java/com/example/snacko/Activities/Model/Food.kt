package com.example.snacko.Activities.Model

data class Food(
    val title: String? = null,
    val pic: String? = null,
    val description: String? = null,
    val fee: Double? = null,
    val star:Int?=null,
    val timer:Int?=null,
    val calories:Int?=null,
    val numberInCart:Int?= null
)