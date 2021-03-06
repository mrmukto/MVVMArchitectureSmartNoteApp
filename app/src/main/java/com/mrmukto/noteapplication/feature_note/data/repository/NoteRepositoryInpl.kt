package com.mrmukto.noteapplication.feature_note.data.repository

import com.mrmukto.noteapplication.feature_note.data.data_source.NoteDao
import com.mrmukto.noteapplication.feature_note.domain.model.Note
import com.mrmukto.noteapplication.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val dao : NoteDao
): NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
       return  dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
       return  dao.getNoteById(id)
    }

    override suspend fun insetNote(note: Note) {
  return dao.insetNotes(note)
    }

    override suspend fun deleteNote(note: Note) {
        return dao.deleteNotes(note)
    }
}