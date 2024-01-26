package com.example.viewhomework.data.repository

import androidx.lifecycle.LiveData
import com.example.viewhomework.data.model.dao.NotesDao
import com.example.viewhomework.data.model.entityDB.NotesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoItemsRepository(private val notesDao: NotesDao) {

    val readAllData: LiveData<List<NotesEntity>> = notesDao.readAllNotesData()

//    private var cases = mutableListOf<TodoItem>()

//    fun getList(): List<TodoItem> {
//        return cases
//    }

//    fun addCase(textCase: String,
//                importance: String,
//                deadline: String,
//                dateCreate: String) {
//
//        cases.add(
//            TodoItem(
//            caseText = textCase,
//            importanceText = importance,
//            deadline = deadline,
//            execVal = false,
//            dateCreate = dateCreate)
//        )
//    }

    suspend fun insertNewNotes(noteEntity: NotesEntity) {
            notesDao.insertNewNotes(noteEntity)
    }

    suspend fun deleteNoteById(id: Long) {
        withContext(Dispatchers.IO) {
            notesDao.deleteNoteEntityById(id)
        }
    }
}