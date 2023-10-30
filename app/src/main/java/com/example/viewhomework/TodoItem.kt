package com.example.viewhomework

data class TodoItem(
    val id: String,
    val caseText: String, // Текст дела
    val importanceText: String, // Важность дела
    val deadline: String, // Дедлайн выполнения
    val execVal: Boolean, // Флаг выполнения
    val dateCreate: String
)