package dgtic.unam.modulosiete.activities

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import dgtic.unam.modulosiete.R
import dgtic.unam.modulosiete.adapters.RecipeAdapterSound
import dgtic.unam.modulosiete.databinding.ActivitySoundBinding
import dgtic.unam.modulosiete.models.ModeloSound
import java.io.File

class SoundActivity : AppCompatActivity() {
    private lateinit var archivos: ArrayList<ModeloSound>
    private lateinit var binding: ActivitySoundBinding
    private lateinit var adap: RecipeAdapterSound
    private var mediaPlayer: MediaPlayer? = null
    private var indice: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySoundBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        val stadoSD: String = Environment.getExternalStorageState()
        if (stadoSD == Environment.MEDIA_MOUNTED) {
            println(
                "sistema=: " +
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).path
            )
            println("sistema2: " + this.getExternalFilesDir(null))
            val ff =
                File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).path)
            archivos = ArrayList()
            ff.listFiles()!!.forEach {
                if (it.isFile) {
                    println(it)
                    archivos.add(ModeloSound(it.name, R.drawable.musica_img, it.path))
                }
            }
            archivos.add(
                ModeloSound(
                    "Magenta_Moon_Part_II.mp3",
                    R.drawable.musica_img,
                    "https://files.freemusicarchive.org/storage-freemusicarchive-org/music/no_curator/Line_Noise/Magenta_Moon/Line_Noise_-_01_-_Magenta_Moon_Part_II.mp3"))
        }
        adap = RecipeAdapterSound(this, archivos)
        binding.list.adapter = adap
        binding.play.setOnClickListener {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(archivos.get(indice).path)
                    prepare()
                    start()
                }
            } else {
                mediaPlayer!!.start()
            }
        }
        binding.stop.setOnClickListener {
            if (mediaPlayer != null) {
                mediaPlayer!!.release()
                mediaPlayer = null
            }
        }
        binding.pause.setOnClickListener {
            if (mediaPlayer != null) {

                mediaPlayer!!.pause()
            }
        }
        binding.path.setOnClickListener {
            Toast.makeText(this, archivos.get(indice).path, Toast.LENGTH_SHORT).show()
        }
        binding.list.setOnItemClickListener { parent, view, position, id ->
            val data: ModeloSound = archivos.get(position)
            indice = position
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(data.path)
                    prepare()
                    start()
                }
            } else {
                mediaPlayer!!.release()
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(data.path)
                    prepare()
                    start()
                }
            }
            Toast.makeText(this, data.namefile, Toast.LENGTH_SHORT).show()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}