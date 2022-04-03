package com.mrmukto.noteapplication.di

import android.app.Application
import androidx.room.Room
import com.mrmukto.noteapplication.feature_note.data.data_source.NoteDatabase
import com.mrmukto.noteapplication.feature_note.data.repository.NoteRepositoryImpl
import com.mrmukto.noteapplication.feature_note.domain.repository.NoteRepository
import com.mrmukto.noteapplication.feature_note.domain.use_case.AddNote
import com.mrmukto.noteapplication.feature_note.domain.use_case.DeleteNote
import com.mrmukto.noteapplication.feature_note.domain.use_case.GetNotesUseCase
import com.mrmukto.noteapplication.feature_note.domain.use_case.NoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app : Application) : NoteDatabase{
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }
    @Provides
    @Singleton
    fun provideNoteRepository(db : NoteDatabase): NoteRepository{
        return NoteRepositoryImpl(db.noteDao)
    }
    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCase{
        return NoteUseCase(
            getNotesUseCase =  GetNotesUseCase(repository),
            deleteNote =  DeleteNote(repository),
           addNote = AddNote(repository)
        )
    }


}