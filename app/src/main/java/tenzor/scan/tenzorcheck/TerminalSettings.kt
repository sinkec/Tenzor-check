package tenzor.scan.tenzorcheck

class TerminalSettings(
    var terminalFirstRun : Boolean,
    var terminalNameID : String,
    var optionQR : Boolean,
    var optionMiFare : Boolean,
    var hostAPI : String,
    var hostAPIPort : Int,
    var connectionProtocol : String,
    var settingsPass : String){}