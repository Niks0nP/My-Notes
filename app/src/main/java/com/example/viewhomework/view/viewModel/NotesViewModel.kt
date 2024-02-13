package com.example.viewhomework.view.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.viewhomework.data.model.appDB.AppDatabase
import com.example.viewhomework.data.model.entityDB.NotesEntity
import com.example.viewhomework.data.repository.TodoItemsRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class NotesViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<NotesEntity>>
    private val repository: TodoItemsRepository

    init {
        val notesDao = AppDatabase.getDatabase(application).getNotesDao()
        repository = TodoItemsRepository(notesDao)
        readAllData = repository.readAllData
    }

    fun insertNewNotes(notesEntity: NotesEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNewNotes(notesEntity)
        }
    }

    suspend fun getElement(noteId: Long): NotesEntity {
        val deferred: Deferred<NotesEntity> = viewModelScope.async {
            repository.getElement(noteId)
        }
        return deferred.await()
    }

    fun updateNote(noteEntity: NotesEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(noteEntity)
        }
    }
}