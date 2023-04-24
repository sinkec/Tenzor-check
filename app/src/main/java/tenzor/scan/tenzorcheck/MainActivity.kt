package tenzor.scan.tenzorcheck


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.widget.Toolbar
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
        val toolbar:Toolbar = findViewById(R.id.materialToolbar)
        setSupportActionBar(toolbar)

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
                    connectionProtocol = "HTTP",
                    settingsPass = "Tenzor10"
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
                        println(it.settingsPass)
                    }
                }
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_item_settings -> {

            }
        }
        return true
    }

}