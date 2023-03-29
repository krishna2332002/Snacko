package com.example.snacko.Activities.Model

data class Food(
    val title: String? = null,
    val id:String?=null,
    val pic: String? = null,
    val description: String? = null,
    val fee: Double=0.00,
    val star:Int=0,
    val timer:Int=0,
    val calories:Int=0,
    var numberInCart:Int=1
)