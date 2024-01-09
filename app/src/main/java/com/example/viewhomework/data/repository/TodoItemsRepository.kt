package com.example.viewhomework.data.repository

import com.example.viewhomework.data.model.TodoItem

class TodoItemsRepository {

    private var cases = mutableListOf<TodoItem>()
    private var index : Int = -1

    fun getList(): List<TodoItem> {
        return cases
    }

    fun addCase(textCase: String,
                importance: String,
                deadline: String,
                dateCreate: String) {
        index += 1

        cases.add(
            TodoItem(
            id = index,
            caseText = textCase,
            importanceText = importance,
            deadline = deadline,
            execVal = false,
            dateCreate = dateCreate)
        )
    }

// Класс сохранения данных
}