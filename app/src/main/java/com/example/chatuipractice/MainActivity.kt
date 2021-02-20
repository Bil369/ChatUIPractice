package com.example.chatuipractice

import android.content.Context
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.emoji.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val msgList = ArrayList<Msg>()
    private val emojiList = ArrayList<Emoji>()

    private var adapter: MsgAdapter? = null

    private var heightDiff = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        initMsg()
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = MsgAdapter(msgList)
        recyclerView.adapter = adapter

        initEmoji()
        val layoutManagerEmoji = GridLayoutManager(this, 8)
        emojiRecyclerView.layoutManager = layoutManagerEmoji
        val adapterEmoji = EmojiAdapter(emojiList)
        adapterEmoji.setOnItemClickListener(object: EmojiAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                val e = emojiList[position]
                inputText.text.append(String(Character.toChars(e.emojiUnicode)))
            }
        })
        emojiRecyclerView.adapter = adapterEmoji

        sendButton.setOnClickListener(this)
        emojiButton.setOnClickListener(this)
        keyboardButton.setOnClickListener(this)

        inputText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sendButton.visibility = View.VISIBLE
                recyclerView.scrollToPosition(msgList.size - 1)
            }

            override fun afterTextChanged(s: Editable?) {
                if(inputText.text.isEmpty())
                {
                    sendButton.visibility = View.GONE
                }
            }
        })

        inputText.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            window.decorView.getWindowVisibleDisplayFrame(r)
            val screenHeight = window.decorView.rootView.height
            heightDiff = screenHeight - r.bottom
        }

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
                    sendButton.visibility = View.GONE
                }
            }
            emojiButton -> {
                keyboardButton.visibility = View.VISIBLE
                emojiButton.visibility = View.GONE

                val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v?.windowToken, 0)
                if(heightDiff > 0)
                    emojiLayout.layoutParams.height = heightDiff
                emojiLayout.visibility = View.VISIBLE
            }
            keyboardButton -> {
                emojiButton.visibility = View.VISIBLE
                keyboardButton.visibility = View.GONE

                emojiLayout.visibility = View.GONE
                inputText.requestFocus()
                val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(inputText, 0)
            }
        }
    }

    private fun initMsg()
    {
        val msg1 = Msg("今天又是啥都没干的一天", Msg.TYPE_RECEIVED, GregorianCalendar(2020, 11, 1, 22, 49))
        msgList.add(msg1)
        val msg2 = Msg("今天又是啥都没干的一天", Msg.TYPE_RECEIVED, GregorianCalendar(2021, 1, 19, 23, 58))
        msgList.add(msg2)
    }

    private fun initEmoji()
    {
        repeat(5)
        {
            val emoji1 = Emoji(Integer.parseInt("1f600", 16))
            emojiList.add(emoji1)
            val emoji2 = Emoji(Integer.parseInt("1f601", 16))
            emojiList.add(emoji2)
            val emoji3 = Emoji(Integer.parseInt("1f602", 16))
            emojiList.add(emoji3)
            val emoji4 = Emoji(Integer.parseInt("1f603", 16))
            emojiList.add(emoji4)
            val emoji5 = Emoji(Integer.parseInt("1f604", 16))
            emojiList.add(emoji5)
            val emoji6 = Emoji(Integer.parseInt("1f605", 16))
            emojiList.add(emoji6)
            val emoji7 = Emoji(Integer.parseInt("1f606", 16))
            emojiList.add(emoji7)
            val emoji8 = Emoji(Integer.parseInt("1f607", 16))
            emojiList.add(emoji8)
            val emoji9 = Emoji(Integer.parseInt("1f608", 16))
            emojiList.add(emoji9)
        }
    }
}