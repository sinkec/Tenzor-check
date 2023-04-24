package tenzor.scan.tenzorcheck

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class PreferenceDatastore(context: Context) {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "TERMINAL_ID")
    var pref = context.dataStore

    companion object{
        var terminalFirstRun = booleanPreferencesKey("TERMINAL_FIRST_RUN")
        var terminalNameID = stringPreferencesKey("TERMINAL_NAME_ID")
        var optionQR = booleanPreferencesKey("OPTION_QR")
        var optionMiFare = booleanPreferencesKey("OPTION_MIFARE")
        var hostAPI = stringPreferencesKey("HOST_API")
        var hostAPIPort = intPreferencesKey("HOST_API_PORT")
        var connectionProtocol = stringPreferencesKey("CONNECTION_PROTOCOL")
    }

    suspend fun setDetails(terminalSettings: TerminalSettings) {
        pref.edit {
            it[terminalFirstRun] = terminalSettings.terminalFirstRun
            it[terminalNameID] = terminalSettings.terminalNameID
            it[optionQR] = terminalSettings.optionQR
            it[optionMiFare] = terminalSettings.optionMiFare
            it[hostAPI] = terminalSettings.hostAPI
            it[hostAPIPort] = terminalSettings.hostAPIPort
            it[connectionProtocol] = terminalSettings.connectionProtocol
        }
    }

    fun getDetails() = pref.data.map{
        TerminalSettings(
            terminalFirstRun = it[terminalFirstRun] ?: true,
            terminalNameID = it[terminalNameID] ?: "",
            optionQR = it[optionQR] ?: true,
            optionMiFare = it[optionMiFare] ?: true,
            hostAPI = it[hostAPI] ?: "",
            hostAPIPort = it[hostAPIPort] ?: 0,
            connectionProtocol = it[connectionProtocol] ?: "HTTP"
        )
    }
}