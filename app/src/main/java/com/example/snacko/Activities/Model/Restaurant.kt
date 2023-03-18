package com.example.snacko.Activities.Model

data class Restaurant(
    val restName: String? = null,
    val resPic: String? = null,
    val restDescription: String? = null,
    val restAddress: String? = null,
    val restFoodList:ArrayList<Food>?=null,
    val restIsOpen:Boolean?=null,
    val restRating:Float?=null,
    val restUid:String?=null
)