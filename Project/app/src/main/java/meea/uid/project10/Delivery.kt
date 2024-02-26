package meea.uid.project10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import java.util.Locale

class Delivery : AppCompatActivity(), TextToSpeech.OnInitListener {
        private lateinit var delivery: Button
        private lateinit var pickup: Button
        private lateinit var textToSpeech: TextToSpeech

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_delivery)

            delivery = findViewById(R.id.delivery)
            pickup = findViewById(R.id.pickup)
            textToSpeech = TextToSpeech(this, this)

            delivery.setOnClickListener {
                speak("You chose delivery.")
                Handler().postDelayed({
                    val intent = Intent(this, ConfirmationDelivery::class.java)
                    startActivity(intent)
                }, 3000)
            }

            pickup.setOnClickListener {
                speak("You chose pick-up.")
                Handler().postDelayed({
                    val intent = Intent(this, ConfirmationPickUp::class.java)
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
                    speak("You have 2 options for delivery. Press delivery or pick-up.")
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