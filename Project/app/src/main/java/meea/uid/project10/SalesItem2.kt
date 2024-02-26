package meea.uid.project10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import java.util.Locale
import kotlin.collections.Map

class SalesItem2 : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var yesButton: Button
    private lateinit var noButton: Button
    private lateinit var textToSpeech: TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sales_item2)

        yesButton = findViewById(R.id.yesButton9)
        noButton = findViewById(R.id.noButton9)
        textToSpeech = TextToSpeech(this, this)

        yesButton.setOnClickListener{
            speak("You pressed Yes. We are adding the item to the list.")
            Handler().postDelayed({
                // Start the next activity
                val intent = Intent(this, EditList::class.java)
                startActivity(intent)
            }, 5000)
        }

        noButton.setOnClickListener{
            speak("You pressed No.")
            Handler().postDelayed({
                // Start the next activity
                val intent = Intent(this, EditList::class.java)
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
                speak("Do you want to add Pepsi to your list? For only 3 dollars. Press Yes or No.")
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