package com.example.chatuipractice

import android.app.Instrumentation
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.emoji_item.view.*

class EmojiAdapter(val emojiList: List<Emoji>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class EmojiViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val emojiText: TextView = view.findViewById(R.id.emojiText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.emoji_item, parent, false)
        return EmojiViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val e = emojiList[position]
        when(holder)
        {
            is EmojiViewHolder ->
            {
                holder.emojiText.text = String(Character.toChars(e.emojiUnicode))
                holder.emojiText.setOnClickListener{
                    mOnItemClickListener?.onItemClick(position)
                }
            }
        }
    }

    override fun getItemCount() = emojiList.size

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    private var mOnItemClickListener: OnItemClickListener? = null

    public fun setOnItemClickListener(onItemClickListener: OnItemClickListener?)
    {
        mOnItemClickListener = onItemClickListener
    }

}