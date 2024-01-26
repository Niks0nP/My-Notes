package com.example.viewhomework.data.model.objectDB

import android.content.Context
import androidx.room.Room
import com.example.viewhomework.data.model.appDB.AppDatabase
import com.example.viewhomework.data.repository.TodoItemsRepository

object Dependencies {

    private lateinit var applicationContext: Context

//    fun init(context: Context) {
//        applicationContext = context
//    }
//
//    private val appDatabase: AppDatabase by lazy {
//        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database.db")
//            .createFromAsset("room_article.db")
//            .build()
//    }
//
//    val todoItemRepository: TodoItemsRepository by lazy { TodoItemsRepository(appDatabase.getNotesDao()) }
}