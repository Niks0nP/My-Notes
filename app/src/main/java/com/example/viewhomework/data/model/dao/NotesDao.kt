package com.example.viewhomework.data.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.viewhomework.data.model.entityDB.NotesEntity

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewNotes(note: NotesEntity)

    @Query("SELECT * FROM notes")
    fun readAllNotesData(): LiveData<List<NotesEntity>>

    @Query("SELECT * FROM notes WHERE id ==:noteId")
    suspend fun updateElement(noteId: Long): NotesEntity

    @Query("DELETE FROM notes WHERE id = :notesId")
    suspend fun deleteNoteEntityById(notesId: Long)
}