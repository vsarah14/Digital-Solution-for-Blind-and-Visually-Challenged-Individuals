package meea.uid.project10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var createShoppingListButton: Button
    lateinit var shopAssistanceButton: Button
    lateinit var myShopsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Instantiate buttons
        createShoppingListButton = findViewById(R.id.createShoppingListButton)
        shopAssistanceButton = findViewById(R.id.shopAssistanceButton)
        myShopsButton = findViewById(R.id.myShopsButton)

        // Add onClick listeners to each button
        createShoppingListButton.setOnClickListener{
            val intent = Intent(this, ConfirmationShoppingList::class.java)
            startActivity(intent)
        }

        shopAssistanceButton.setOnClickListener{
            val intent = Intent(this, ConfirmationShopAssistance::class.java)
            startActivity(intent)
        }

        myShopsButton.setOnClickListener {
            val intent = Intent(this, ConfirmationMyShops::class.java)
            startActivity(intent)
        }
    }
}