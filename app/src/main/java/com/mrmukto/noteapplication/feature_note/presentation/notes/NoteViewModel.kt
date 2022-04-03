package com.mrmukto.noteapplication.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrmukto.noteapplication.feature_note.domain.model.Note
import com.mrmukto.noteapplication.feature_note.domain.use_case.NoteUseCase
import com.mrmukto.noteapplication.feature_note.domain.util.NoteOrder
import com.mrmukto.noteapplication.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val  noteUseCase: NoteUseCase

): ViewModel(){
    private val _state = mutableStateOf(NoteState())
    val state : State<NoteState> = _state
   private var recentlyDeleteNote : Note? = null
    private  var getNoteJob: Job? =null
    init {
        getNotesUseCase(NoteOrder.Date(OrderType.Descending))
    }

  fun onEvent( event: NotesEvent){
      when(event){
          is NotesEvent.Order ->{
              if (state.value.noteOrder::class == event.noteOrder::class &&
                      state.value.noteOrder.orderType == event.noteOrder.orderType
              ){
                  return
              }
              getNotesUseCase(event.noteOrder)
          }
          is NotesEvent.DeleteNote ->{
             viewModelScope.launch {
                 noteUseCase.deleteNote(event.note)
                 recentlyDeleteNote = event.note
             }
          }is NotesEvent.RestoreNote ->{
            viewModelScope.launch {
                noteUseCase.addNote(recentlyDeleteNote?: return@launch)
                recentlyDeleteNote = null
            }
      }is NotesEvent.ToggleOrderSection ->
      {
        _state.value = state.value.copy(
            isOrderSectionVisible = !state.value.isOrderSectionVisible
        )
      }

      }
  }

    private fun getNotesUseCase(noteOrder: NoteOrder) {
        getNoteJob?.cancel()

        getNoteJob =  noteUseCase.getNotesUseCase(noteOrder)
               .onEach { notes ->
                   _state.value = state.value.copy(
                       notes = notes,
                       noteOrder = noteOrder
                   )
               }
               .launchIn(viewModelScope)
    }




}