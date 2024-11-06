package infor.c14220016.listview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import infor.c14220016.listview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        val binding = MainActivity.

        var data = mutableListOf<String>()
        data.addAll(listOf("1", "2", "3", "4", "5"))

        val lvlAdapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            data
        )

        binding.lv1.adapter = lvlAdapter

        val _lv1 = findViewById<ListView>(R.id.lv1)
        _lv1.adapter = lvlAdapter

        _lv1.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(
                this, "${data[position]}",
                Toast.LENGTH_LONG
            ).show()
        }

        val _btnTambah = findViewById<Button>(R.id.btnTambah)
        _btnTambah.setOnClickListener {
            var dtAkhir = Integer.parseInt(data.get(data.size - 1)) + 1
            data.add(dtAkhir.toString())
            lvlAdapter.notifyDataSetChanged()
        }

        val _btnHapus = findViewById<Button>(R.id.btnHapus)
        _btnHapus.setOnClickListener {
            data.removeFirst()
            lvlAdapter.notifyDataSetChanged()
        }

        binding.svw.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                lvlAdapter.getFilter().filter(query)
                return  false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                lvlAdapter.getFilter().filter(newText)
                return false
            }
        })



    }
}