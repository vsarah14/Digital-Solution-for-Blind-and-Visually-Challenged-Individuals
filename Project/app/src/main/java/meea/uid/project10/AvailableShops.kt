package meea.uid.project10

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class AvailableShops : AppCompatActivity(), OnInitListener {

    private lateinit var shopButton: Button
    private lateinit var textToSpeech: TextToSpeech
    // Sample shop names
    private val shopNames = arrayOf("Lidl", "Mega Image", "Carrefour", "Kaufland")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_available_shops)

        // Initialize TextToSpeech
        textToSpeech = TextToSpeech(this, this)

        shopButton = findViewById(R.id.button1)
        shopButton.setOnClickListener {
            speakOut("You chose Lidl. Continuing to creating the shopping list.")
            Handler().postDelayed({
                // Start the next activity
                val intent = Intent(this, EditList::class.java)
                startActivity(intent)
            }, 3000)
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // Set the language for TextToSpeech
            val result = textToSpeech.setLanguage(Locale.getDefault())

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Language is not available or not supported
                // Handle the error or choose a different language
            } else {
                // Read aloud each shop name after TextToSpeech is successfully initialized
                for (shopName in shopNames) {
                    speakOut(shopName)
                }
                speakOut("Which shop do you choose?")
//                Handler().postDelayed({
//                    // Start the next activity
//                    val intent = Intent(this, EditList::class.java)
//                    startActivity(intent)
//                }, 3000)

            }
        } else {
            // Initialization failed, handle the error
        }
    }

    private fun speakOut(text: String) {
        // Speak the provided text
        textToSpeech.speak(text, TextToSpeech.QUEUE_ADD, null, null)
    }

    override fun onDestroy() {
        // Shutdown TextToSpeech when the activity is destroyed
        if (::textToSpeech.isInitialized) {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
        super.onDestroy()
    }
}
