package com.example.mynotesapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapp.database.Note
import com.example.mynotesapp.databinding.ItemNoteBinding
import com.example.mynotesapp.helper.NoteDiffCallback
import com.example.mynotesapp.ui.insert.NoteAddUpdateActivity

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private val listNotes = ArrayList<Note>()

    inner class NoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            with(binding) {
                tvItemTitle.text = note.title
                tvItemData.text = note.date
                tvItemDescription.text = note.description
                cvItemNote.setOnClickListener {
                    val intent = Intent(it.context, NoteAddUpdateActivity::class.java)
                    intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note)
                    it.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }

    override fun getItemCount(): Int {
        return listNotes.size
    }

    fun setListNotes(lisNotes: List<Note>) {
        val diffCallback = NoteDiffCallback(this.listNotes, lisNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listNotes.clear()
        this.listNotes.addAll(lisNotes)
        diffResult.dispatchUpdatesTo(this)
    }
}