package com.carterchen247.breakingbadapp

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_character_info.view.*
import timber.log.Timber

class CharactersInfoAdapter(private val listener: ItemClickListener) :
    RecyclerView.Adapter<CharactersInfoAdapter.NumbersViewHolder>() {

    private val items = mutableListOf<CharacterInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumbersViewHolder {
        val view = View.inflate(parent.context, R.layout.item_character_info, null)
        val viewHolder = NumbersViewHolder(view)
        view.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClicked(items[position])
            }
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NumbersViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    fun updateData(charactersInfo: List<CharacterInfo>) {
        items.clear()
        items.addAll(charactersInfo)
        notifyDataSetChanged()
    }

    inner class NumbersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(characterInfo: CharacterInfo) {
            itemView.run {
                characterName.text = characterInfo.name
                Glide.with(context).load(characterInfo.img).into(characterImg)
            }
        }
    }

    interface ItemClickListener {
        fun onItemClicked(characterInfo: CharacterInfo)
    }
}