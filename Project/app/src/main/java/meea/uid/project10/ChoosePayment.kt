package meea.uid.project10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import org.w3c.dom.Text
import java.util.Locale

class ChoosePayment : AppCompatActivity(), TextToSpeech.OnInitListener{
        private lateinit var online: Button
        private lateinit var store: Button
        private lateinit var textToSpeech: TextToSpeech
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_choose_payment)

            online = findViewById(R.id.online)
            store = findViewById(R.id.store)
            textToSpeech = TextToSpeech(this, this)


            online.setOnClickListener {
                speak("You chose to pay online.")
                Handler().postDelayed({
                    val intent = Intent(this, ConfirmationOnline::class.java)
                    startActivity(intent)
                }, 3000)
            }

            store.setOnClickListener {
                speak("You chose to pay at store.")
                Handler().postDelayed({
                    val intent = Intent(this, ConfirmationAtStore::class.java)
                    startActivity(intent)
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
                    speak("Choose your payment method. Press Online or At Store.")
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