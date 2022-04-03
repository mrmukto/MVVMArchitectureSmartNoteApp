package com.mrmukto.noteapplication.feature_note.presentation.notes.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mrmukto.noteapplication.feature_note.domain.util.NoteOrder
import com.mrmukto.noteapplication.feature_note.domain.util.OrderType

@Composable
  fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderchange : (NoteOrder) -> Unit


    ){
      Column(
          modifier = modifier
      )
    {
          Row (
              modifier = modifier.fillMaxWidth()
                  ){
              DefaultRadioButton(
                  text = "Title",
                  selected =noteOrder is NoteOrder.Title ,
                  onSelected = { onOrderchange(NoteOrder.Title(noteOrder.orderType))}
              )

              Spacer(modifier = Modifier.width(8.dp))
              DefaultRadioButton(
                  text = "Date",
                  selected =noteOrder is NoteOrder.Date ,
                  onSelected = { onOrderchange(NoteOrder.Date(noteOrder.orderType))}
              )
              Spacer(modifier = Modifier.width(8.dp))
              DefaultRadioButton(
                  text = "Color",
                  selected =noteOrder is NoteOrder.Color ,
                  onSelected = { onOrderchange(NoteOrder.Color(noteOrder.orderType))}
              )
          }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected =noteOrder.orderType is OrderType.Ascending,
                onSelected = {
                    onOrderchange(noteOrder.copy(OrderType.Ascending))}
            )

            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected =noteOrder.orderType is OrderType.Descending ,
                onSelected = {
                    onOrderchange(noteOrder.copy(OrderType.Descending))}
            )
        }

      }

  }