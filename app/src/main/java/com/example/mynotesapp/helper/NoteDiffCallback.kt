package com.example.mynotesapp.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.mynotesapp.database.Note

class NoteDiffCallback(private val mOldNoteList: List<Note>, private val mNewNoteLis: List<Note>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldNoteList.size
    }

    override fun getNewListSize(): Int {
        return mNewNoteLis.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldNoteList[oldItemPosition].id == mNewNoteLis[newItemPosition].id

    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldeEmployee = mOldNoteList[oldItemPosition]
        val newEmployee = mNewNoteLis[newItemPosition]
        return oldeEmployee.title == newEmployee.title && oldeEmployee.description == newEmployee.description
    }

}