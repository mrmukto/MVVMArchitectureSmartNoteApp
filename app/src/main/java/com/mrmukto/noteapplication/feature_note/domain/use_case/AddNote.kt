package com.mrmukto.noteapplication.feature_note.domain.use_case

import com.mrmukto.noteapplication.feature_note.domain.model.InvalidNoteException
import com.mrmukto.noteapplication.feature_note.domain.model.Note
import com.mrmukto.noteapplication.feature_note.domain.repository.NoteRepository

class AddNote(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note){
        if (note.title.isBlank()){
             throw  InvalidNoteException("The tittle of the note can not  be empty")
        }
        if (note.content.isBlank()){
            throw InvalidNoteException("The content of the note can't be empty")
        }
        repository.insetNote(note)
    }
}