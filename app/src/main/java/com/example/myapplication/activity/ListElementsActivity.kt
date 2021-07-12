package com.example.myapplication.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.MonumentAdapter
import com.example.myapplication.model.Monument
import kotlinx.android.synthetic.main.item_list_element.*

class ListElementsActivity : AppCompatActivity() {
    var monuments: List<Monument> = listOf(
        Monument(
            "Nôtre-Dame", "48.852966", "2.349902",
            "https://es.parisinfo.com/var/otcp/sites/images/node_43/node_51/node_77884/node_77888/cathédrale-notre-dame-de-paris-vue-depuis-le-parvis-%7C-630x405-%7C-©-leiflinding/11884072-6-fre-FR/Cathédrale-Notre-Dame-de-Paris-Vue-depuis-le-parvis-%7C-630x405-%7C-©-LeifLinding.jpg",
            "This is the description for Nôtre-Dame. this is going to be a description provided by an external API.\n" +
                    "\n" +
                    "Lorem ipsum dolor sit amet consectetur adipiscing elit pretium venenatis himenaeos molestie posuere quis, placerat urna feugiat eu facilisis nibh curae fermentum risus commodo volutpat. Pretium dapibus nisi hac augue montes fusce at eleifend varius curabitur aenean, potenti fermentum aliquam ad dis a integer rhoncus diam ultrices suspendisse, nullam torquent pulvinar sodales ridiculus malesuada blandit viverra nam gravida. Quisque sem faucibus erat suspendisse imperdiet risus iaculis commodo pulvinar tincidunt, velit felis ullamcorper dis dignissim per integer porta mollis quis eleifend, non ultrices morbi odio ridiculus arcu senectus eros ultricies.\n" +
                    "\n" +
                    "Dis et lectus diam rutrum natoque pretium cras phasellus, vulputate sagittis faucibus feugiat blandit id convallis, nulla suscipit taciti ultricies eu cursus turpis. Sodales tempor leo dis tempus lobortis cum fringilla, parturient nibh ultrices ad turpis montes diam, magnis porta eros class urna consequat. Platea sollicitudin dignissim porttitor etiam ultrices luctus vehicula integer proin mattis, penatibus nostra dapibus enim risus magnis facilisi dui. "
        ),
        Monument(
            "Colosseum of Rome",
            "41.890251",
            "12.492373",
            "https://colosseumrometickets.com/wp-content/uploads/2018/05/Where-is-the-Colosseum-Located-2.jpg",
            "this is the description for Colosseum of Rome. this is going to be a description provided by an external API"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_list_element)
        initRecycler()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_go_back -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun initRecycler() {
        rvMonument.layoutManager = LinearLayoutManager(this)
        val adapter = MonumentAdapter(monuments)
        rvMonument.adapter = adapter
    }
}



