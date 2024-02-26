package com.example.viewhomework.data.repository

import androidx.lifecycle.LiveData
import com.example.viewhomework.data.model.dao.NotesDao
import com.example.viewhomework.data.model.entityDB.NotesEntity

class TodoItemsRepository(private val notesDao: NotesDao) {

    val readAllData: LiveData<List<NotesEntity>> = notesDao.readAllNotesData()

    suspend fun insertNewNotes(noteEntity: NotesEntity) {
        notesDao.insertNewNotes(noteEntity)
    }

    suspend fun updateNote(noteEntity: NotesEntity) {
        notesDao.updateNote(noteEntity)
    }

    suspend fun deleteNote(noteId: Long) {
        notesDao.deleteNote(noteId)
    }

    suspend fun deleteAllNotes() {
        notesDao.deleteAllNotes()
    }
}