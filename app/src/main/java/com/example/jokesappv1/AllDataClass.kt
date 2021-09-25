package com.example.jokesappv1


data class JokesCategory(
    val n : Category? = null
)

data class Category(
    val s : MutableList<String>
)