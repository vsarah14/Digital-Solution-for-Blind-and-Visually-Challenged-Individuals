package meea.uid.project10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import java.util.Locale

class EditList : AppCompatActivity(), TextToSpeech.OnInitListener{
    private lateinit var finishOrder: Button
    private lateinit var delete: Button
    private lateinit var addItem: Button
    private lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_list)

        finishOrder = findViewById(R.id.finishOrder)
        delete = findViewById(R.id.delete)
        addItem = findViewById(R.id.addItem)
        textToSpeech = TextToSpeech(this, this)

        finishOrder.setOnClickListener{
            Handler().postDelayed({
                speak("You finished your shopping list.")
            }, 4000)
            intent = Intent(this, ConfirmationFinishOrder::class.java)
            startActivity(intent)
        }

        addItem.setOnClickListener {
            Handler().postDelayed({
                speak("What do you want to add to the list?")
            }, 3000)
            Handler().postDelayed({
                speak("We have green apples or red apples.")
            }, 7000)
            Handler().postDelayed({
                speak("Specify quantity.")
            }, 11000)
            Handler().postDelayed({
                speak("This total price is 3 dollars.")
            }, 15000)
        }


        delete.setOnClickListener{
            Handler().postDelayed({
                speak("The item was deleted.")
            }, 3000)
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
                speak("You can add items, delete items, or finish your order.")
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