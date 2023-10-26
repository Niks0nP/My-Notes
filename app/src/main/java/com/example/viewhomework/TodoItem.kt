package com.example.viewhomework

data class TodoItem(
    val id: String,
    val caseText: String,
    val importanceText: String,
    val deadline: String,
    val execVal: Boolean,
    val dateCreate: String,
    val dateChange: String
)