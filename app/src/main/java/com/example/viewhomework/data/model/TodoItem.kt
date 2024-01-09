package com.example.viewhomework.data.model

data class TodoItem(
    val id: Int,
    val caseText: String, // Текст дела
    val importanceText: String, // Важность дела
    val deadline: String, // Дедлайн выполнения
    val execVal: Boolean, // Флаг выполнения
    val dateCreate: String
)