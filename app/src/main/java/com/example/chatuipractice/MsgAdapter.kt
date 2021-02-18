package com.example.chatuipractice

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.IllegalArgumentException
import java.util.*

class MsgAdapter(val msgList: List<Msg>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class LeftViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val leftMsg: TextView = view.findViewById(R.id.leftMsg)
        val leftTime: TextView = view.findViewById(R.id.leftTime)
    }

    inner class RightViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val rightMsg: TextView = view.findViewById(R.id.rightMsg)
        val rightTime: TextView = view.findViewById(R.id.rightTime)
    }

    override fun getItemViewType(position: Int): Int {
        val msg = msgList[position]
        return msg.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == Msg.TYPE_RECEIVED)
        {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_left_item, parent, false)
            return LeftViewHolder(view)
        }
        else
        {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_right_item, parent, false)
            return RightViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = msgList[position]
        when(holder)
        {
            is LeftViewHolder ->
            {
                holder.leftMsg.text = msg.content

                val cal: GregorianCalendar = msg.msgTime
                val now: GregorianCalendar = GregorianCalendar()
                now.set(GregorianCalendar.HOUR_OF_DAY, 0)
                now.set(GregorianCalendar.MINUTE, 0)
                now.set(GregorianCalendar.SECOND, 0)
                val yesterday: GregorianCalendar = GregorianCalendar()
                yesterday.set(GregorianCalendar.HOUR_OF_DAY, 0)
                yesterday.set(GregorianCalendar.MINUTE, 0)
                yesterday.set(GregorianCalendar.SECOND, 0)
                yesterday.add(GregorianCalendar.DAY_OF_MONTH, -1)
                if(position == 0 || cal.timeInMillis - msgList[position-1].msgTime.timeInMillis > 60*1000)
                {
                    if(cal.after(now))
                    {
                        holder.leftTime.text = String.format("%d:%02d", cal.get(GregorianCalendar.HOUR_OF_DAY),
                                cal.get(GregorianCalendar.MINUTE))
                    }
                    else if(cal.after(yesterday))
                    {
                        holder.leftTime.text = String.format("昨天 %d:%02d", cal.get(GregorianCalendar.HOUR_OF_DAY),
                                cal.get(GregorianCalendar.MINUTE))
                    }
                    else
                    {
                        holder.leftTime.text = String.format("%04d年%d月%d日 %d:%02d", cal.get(GregorianCalendar.YEAR),
                                cal.get(GregorianCalendar.MONTH),
                                cal.get(GregorianCalendar.DAY_OF_MONTH)+1,
                                cal.get(GregorianCalendar.HOUR_OF_DAY),
                                cal.get(GregorianCalendar.MINUTE))
                    }
                }
                else
                {
                    holder.leftTime.visibility = View.GONE
                }
            }
            is RightViewHolder ->
            {
                holder.rightMsg.text = msg.content
                val cal: GregorianCalendar = msg.msgTime
                val now: GregorianCalendar = GregorianCalendar()
                now.set(GregorianCalendar.HOUR_OF_DAY, 0)
                now.set(GregorianCalendar.MINUTE, 0)
                now.set(GregorianCalendar.SECOND, 0)
                val yesterday: GregorianCalendar = GregorianCalendar()
                yesterday.set(GregorianCalendar.HOUR_OF_DAY, 0)
                yesterday.set(GregorianCalendar.MINUTE, 0)
                yesterday.set(GregorianCalendar.SECOND, 0)
                yesterday.add(GregorianCalendar.DAY_OF_MONTH, -1)
                if(position == 0 || cal.timeInMillis - msgList[position-1].msgTime.timeInMillis > 60*1000)
                {
                    if(cal.after(now))
                    {
                        holder.rightTime.text = String.format("%d:%02d", cal.get(GregorianCalendar.HOUR_OF_DAY),
                                cal.get(GregorianCalendar.MINUTE))
                    }
                    else if(cal.after(yesterday))
                    {
                        holder.rightTime.text = String.format("昨天 %d:%02d", cal.get(GregorianCalendar.HOUR_OF_DAY),
                                cal.get(GregorianCalendar.MINUTE))
                    }
                    else
                    {
                        holder.rightTime.text = String.format("%04d年%d月%d日 %d:%02d", cal.get(GregorianCalendar.YEAR),
                                cal.get(GregorianCalendar.MONTH),
                                cal.get(GregorianCalendar.DAY_OF_MONTH)+1,
                                cal.get(GregorianCalendar.HOUR_OF_DAY),
                                cal.get(GregorianCalendar.MINUTE))
                    }
                }
                else
                {
                    holder.rightTime.visibility = View.GONE
                }
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount() = msgList.size
}