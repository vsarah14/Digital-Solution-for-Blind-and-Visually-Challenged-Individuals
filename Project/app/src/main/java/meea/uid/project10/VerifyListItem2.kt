package meea.uid.project10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import java.util.Locale

class VerifyListItem2 : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var yesButton: Button
    private lateinit var noButton: Button
    private lateinit var textToSpeech: TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_list_item2)
        yesButton = findViewById(R.id.yesButton12)
        noButton = findViewById(R.id.noButton12)
        textToSpeech = TextToSpeech(this, this)


        yesButton.setOnClickListener {
            speak("You pressed Yes. You verified the shopping list.")
            Handler().postDelayed({
                val intent = Intent(this, AddAnotherItem::class.java)
                startActivity(intent)
            }, 4000)
        }

        noButton.setOnClickListener {
            speak("You pressed No. Pepsi is removed from the list.")
            Handler().postDelayed({
                val intent = Intent(this, AddAnotherItem::class.java)
                startActivity(intent)
            }, 4000)
        }
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
                speak("Do you want to keep the Pepsi?")
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