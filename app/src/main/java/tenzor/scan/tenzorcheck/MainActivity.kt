package tenzor.scan.tenzorcheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSet = findViewById<Button>(R.id.btnSet)
        val btnGet = findViewById<Button>(R.id.btnGet)

        val preferenceDatastore = PreferenceDatastore(this)

        btnSet.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val terminalSettings = TerminalSettings(
                    terminalFirstRun = false,
                    terminalNameID = "ST_012",
                    optionQR = true,
                    optionMiFare = true,
                    hostAPI = "192.168.1.117",
                    hostAPIPort = 8080,
                    connectionProtocol = "HTTP"
                )
                preferenceDatastore.setDetails(terminalSettings)
            }
        }

        btnGet.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                preferenceDatastore.getDetails().collect{
                    withContext(Dispatchers.Main){
                        println(it.terminalFirstRun.toString())
                        println(it.terminalNameID)
                        println(it.optionQR.toString())
                        println(it.optionMiFare.toString())
                        println(it.hostAPI)
                        println(it.hostAPIPort.toString())
                        println(it.connectionProtocol)
                    }
                }
            }
        }

    }

}