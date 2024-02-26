package meea.uid.project10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.Locale

class Map : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        textToSpeech = TextToSpeech(this, this)

        Handler().postDelayed({
            // Create an Intent to start the next activity
            val intent = Intent(this, Camera::class.java)
            startActivity(intent)

            // Finish the current activity (optional, depends on your navigation flow)
            finish()
        }, 13000)
    }

    private fun speak(text: String) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech.setLanguage(Locale.getDefault())
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Language is not available or not supported
                Log.e("Error", "Language is not supported.")
            }
            else{
                speak("Pay close attention to the guidance. Walk straight for approximately 10 paces, then make a right turn. On your right, you'll find a place where you can purchase Pepsi.")
            }
        } else {
            // Initialization failed
            Log.e("Error", "Initialization failed.")

        }
    }

    override fun onDestroy() {
        if (textToSpeech.isSpeaking) {
            textToSpeech.stop()
        }
        textToSpeech.shutdown()
        super.onDestroy()
    }
}