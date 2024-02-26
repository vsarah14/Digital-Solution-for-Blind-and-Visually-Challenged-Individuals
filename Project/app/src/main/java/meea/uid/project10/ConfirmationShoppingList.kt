package meea.uid.project10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import java.util.Locale

class ConfirmationShoppingList : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var yesButton: Button
    private lateinit var noButton: Button
    private lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation_shopping_list)

        // Instantiate buttons
        yesButton = findViewById(R.id.yesButton2)
        noButton = findViewById(R.id.noButton2)
        textToSpeech = TextToSpeech(this, this)

        // Add onClick listeners
        yesButton.setOnClickListener {
            speak("You pressed Yes. Here are the available shops.")
            Handler().postDelayed({
                // Start the next activity
                val intent = Intent(this, AvailableShops::class.java)
                startActivity(intent)
            }, 5000)
        }

        noButton.setOnClickListener {
            speak("You pressed no. Returning to the main screen.")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
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
                speak("Do you want to create a new shopping list? Press Yes or No.")
            }
        } else {
            // Initialization failed
            Log.e("Error", "Initialization failed.")

        }
    }
}