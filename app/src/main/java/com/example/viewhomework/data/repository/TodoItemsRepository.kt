package com.example.viewhomework.data.repository

import androidx.lifecycle.LiveData
import com.example.viewhomework.data.model.dao.NotesDao
import com.example.viewhomework.data.model.entityDB.NotesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoItemsRepository(private val notesDao: NotesDao) {

    val readAllData: LiveData<List<NotesEntity>> = notesDao.readAllNotesData()

    suspend fun insertNewNotes(noteEntity: NotesEntity) {
        notesDao.insertNewNotes(noteEntity)
    }

    suspend fun getElement(noteId: Long): NotesEntity {
        return notesDao.getElement(noteId)
    }

    suspend fun updateNote(noteEntity: NotesEntity) {
        notesDao.updateNote(noteEntity)
    }

    suspend fun deleteNoteById(id: Long) {
        withContext(Dispatchers.IO) {
            notesDao.deleteNoteEntityById(id)
        }
    }
}