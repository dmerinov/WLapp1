package com.example.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.model.Monument
import com.example.myapplication.adapter.MonumentAdapter
import kotlinx.android.synthetic.main.item_list_element.*

class ListElementsActivity : AppCompatActivity(){
    var monuments: List<Monument> = listOf(
        Monument("Nôtre-Dame","48.852966","2.349902",
        "https://es.parisinfo.com/var/otcp/sites/images/node_43/node_51/node_77884/node_77888/cathédrale-notre-dame-de-paris-vue-depuis-le-parvis-%7C-630x405-%7C-©-leiflinding/11884072-6-fre-FR/Cathédrale-Notre-Dame-de-Paris-Vue-depuis-le-parvis-%7C-630x405-%7C-©-LeifLinding.jpg",
        "this is the description for Nôtre-Dame. this is going to be a description provided by an external API"),
            Monument("Colosseum of Rome","41.890251","12.492373",
        "https://colosseumrometickets.com/wp-content/uploads/2018/05/Where-is-the-Colosseum-Located-2.jpg","this is the description for Colosseum of Rome. this is going to be a description provided by an external API"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_list_element)
        initRecycler()
    }

    fun initRecycler(){
         rvMonument.layoutManager=LinearLayoutManager(this)
        val adapter = MonumentAdapter(monuments)
        rvMonument.adapter = adapter
    }
}



