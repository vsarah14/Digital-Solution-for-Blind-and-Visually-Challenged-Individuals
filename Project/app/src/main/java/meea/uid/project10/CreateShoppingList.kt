package meea.uid.project10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech

class CreateShoppingList : AppCompatActivity(), TextToSpeech.OnInitListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_shopping_list)
    }

    override fun onInit(status: Int) {
        TODO("Not yet implemented")
    }
}