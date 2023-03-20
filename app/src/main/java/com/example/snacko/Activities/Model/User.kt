package com.example.snacko.Activities.Model

data class User(
    val userName: String? = null,
    val name: String? = null,
    val state: String?= null,
    val district: String? = null,
    val cartList:ArrayList<Food>?=null
)