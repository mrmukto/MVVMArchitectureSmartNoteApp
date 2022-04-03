package com.mrmukto.noteapplication.feature_note.data.data_source

import androidx.room.*
import com.mrmukto.noteapplication.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface  NoteDao {
    @Query("SELECT *FROM note")
   fun getNotes(): Flow<List<Note>>
   @Query("SELECT *FROM note WHERE id= :id")
   suspend fun getNoteById(id: Int): Note?
   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insetNotes(note:Note)
   @Delete
   suspend fun deleteNotes(note:Note)
}