package meea.uid.project10

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class ShopsWithFinishedLists : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shops_with_finished_lists)

        textToSpeech = TextToSpeech(this, this)

        Handler().postDelayed({
            // Create an Intent to start the next activity
            val intent = Intent(this, RequestUber::class.java)
            startActivity(intent)

            // Finish the current activity (optional, depends on your navigation flow)
            finish()
        }, 7000)

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
                speak("You have finished shopping lists at Lidl and Kaufland. Choose your desired shop.")
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