package gt.uvg.eatify

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class Login : AppCompatActivity() {

    var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        button = findViewById<View>(R.id.button) as Button?
        button?.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View?) {
                    val i = Intent(this@Login, MainActivity::class.java)
                    startActivity(i)
                }
            }
        )

    }
}