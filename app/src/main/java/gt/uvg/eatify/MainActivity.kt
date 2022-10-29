package gt.uvg.eatify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    var card: CardView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        card = findViewById<View>(R.id.cardView2) as CardView?
        card?.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View?) {
                    val i = Intent(this@MainActivity, Detalle::class.java)
                    startActivity(i)
                }
            }
        )


    }
}