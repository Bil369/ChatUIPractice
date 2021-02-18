package com.example.chatuipractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val msgList = ArrayList<Msg>()

    private var adapter: MsgAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        initMsg()
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = MsgAdapter(msgList)
        recyclerView.adapter = adapter
        sendButton.setOnClickListener(this)
        emojiButton.setOnClickListener(this)
        keyboardButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v)
        {
            sendButton -> {
                val content = inputText.text.toString()
                if(content.isNotEmpty())
                {
                    val msg = Msg(content, Msg.TYPE_SENT, GregorianCalendar())
                    msgList.add(msg)
                    adapter?.notifyItemInserted(msgList.size - 1)
                    recyclerView.scrollToPosition(msgList.size - 1)
                    inputText.setText("")
                }
            }
            emojiButton -> {
                keyboardButton.visibility = View.VISIBLE
                emojiButton.visibility = View.GONE
            }
            keyboardButton -> {
                emojiButton.visibility = View.VISIBLE
                keyboardButton.visibility = View.GONE
            }
        }
    }

    private fun initMsg()
    {
        val msg1 = Msg("今天又是啥都没干的一天", Msg.TYPE_RECEIVED, GregorianCalendar(2020, 11, 1, 22, 49))
        msgList.add(msg1)
        val msg2 = Msg("今天又是啥都没干的一天", Msg.TYPE_RECEIVED, GregorianCalendar(2021, 1, 17, 23, 58))
        msgList.add(msg2)
    }
}