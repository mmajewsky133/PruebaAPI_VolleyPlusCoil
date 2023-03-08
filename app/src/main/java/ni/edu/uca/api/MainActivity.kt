package ni.edu.uca.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import coil.load
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Agregar un listener al botón para generar una imagen aleatoria al hacer clic
        val generateButton: Button = findViewById(R.id.button_generate)
        generateButton.setOnClickListener { generarImagenAleatoria() }
    }

    private fun generarImagenAleatoria() {

        val queue = Volley.newRequestQueue(this)
        try {
            val request = JsonObjectRequest(
                Request.Method.GET, "https://dog.ceo/api/breeds/image/random", null,
                { response ->
                    val imageUrl = response.getString("message")
                    val imageView: ImageView = findViewById(R.id.image_view)

                    // Cargar la imagen desde la URL utilizando Coil
                    imageView.load(imageUrl)
                },
                { error ->
                    Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
                })

            queue.add(request)
        } catch (e: Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
        }
    }
}


