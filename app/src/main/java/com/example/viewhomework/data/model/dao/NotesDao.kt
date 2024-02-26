package com.example.viewhomework.data.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.viewhomework.data.model.entityDB.NotesEntity

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewNotes(note: NotesEntity)

    @Query("SELECT * FROM notes")
    fun readAllNotesData(): LiveData<List<NotesEntity>>

    @Query("SELECT * FROM notes WHERE id ==:noteId")
    suspend fun getElement(noteId: Long): NotesEntity

    @Update
    suspend fun updateNote(note: NotesEntity)

    @Query("DELETE FROM notes WHERE id ==:noteId")
    suspend fun deleteNote(noteId: Long)

    @Query("DELETE FROM notes")
    suspend fun deleteAllNotes()
}