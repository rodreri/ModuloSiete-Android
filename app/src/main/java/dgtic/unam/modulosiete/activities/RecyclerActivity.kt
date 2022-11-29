package dgtic.unam.modulosiete.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import dgtic.unam.modulosiete.adapters.AdapterViewHolder
import dgtic.unam.modulosiete.databinding.ActivityRecyclerBinding
import dgtic.unam.modulosiete.models.Source


class RecylerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        initItemp()
    }
    private fun initItemp(){
        val recyclerView=binding.data
        recyclerView.layoutManager=LinearLayoutManager(this)
        val adapter= AdapterViewHolder(this, Source.dataList)
        recyclerView.adapter=adapter
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}