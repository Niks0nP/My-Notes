package com.example.viewhomework

import android.app.Application

class App : Application() {
    val todoItemsRepository = TodoItemsRepository()
}