package com.carterchen247.breakingbadapp

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_character_info.view.*

class CharactersInfoAdapter(
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<CharactersInfoAdapter.CharacterInfoViewHolder>() {

    private val items = mutableListOf<CharacterInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterInfoViewHolder {
        val view = View.inflate(parent.context, R.layout.item_character_info, null)
        val viewHolder = CharacterInfoViewHolder(view)
        view.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                itemClickListener.onItemClick(items[position])
            }
        }
        return CharacterInfoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CharacterInfoViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    fun updateData(charactersInfo: List<CharacterInfo>) {
        items.clear()
        items.addAll(charactersInfo)
        notifyDataSetChanged()
    }

    class CharacterInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(characterInfo: CharacterInfo) {
            itemView.run {
                characterName.text = characterInfo.name
                Glide.with(context).load(characterInfo.img).into(characterImg)
            }
        }
    }

    interface ItemClickListener {
        fun onItemClick(characterInfo: CharacterInfo)
    }
}