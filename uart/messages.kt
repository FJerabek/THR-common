package cz.fjerabek.thr.data.uart

import cz.fjerabek.thr.data.bluetooth.IBluetoothMessage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Class representing generic UART message
 */
abstract class UartMessage: IBluetoothMessage {
    companion object {
        const val CMD_FW = "\$fwv\r\n"
        const val CMD_STATUS = "\$sta\r\n"
        const val CMD_HBT = "\$hbt\r\n"
        const val CMD_SHUTDOWN = "\$off\r\n"
    }
}

/**
 * Button press UART message
 * @param id button id
 * @param pressed if button is pressed
 * @param pressedTime pressed time. Only in release messages
 */
@Serializable
data class ButtonMessage(
    var id : Int,
    var pressed : Boolean,
    var pressedTime : Long
): UartMessage()

/**
 * Firmware Version UART message
 * @param major major version
 * @param minor minor version
 * @param patch patch version
 */
@Serializable
@SerialName("FwVersion")
data class FWVersionMessage(
    var major : Int,
    var minor : Int,
    var patch : Int
): UartMessage()

/**
 * Hardware status UART message
 * @param uptime device uptime
 * @param battery battery percentage
 * @param charging charging state
 * @param current current in mA
 */
@Serializable
@SerialName("HwStatus")
data class StatusMessage(
    var uptime : Long,
    var battery : Int,
    var charging : ECharging,
    var current : Int
) : UartMessage()

/**
 * Shutdown UART message
 * @param ok if message is confirmed
 */
@Serializable
data class ShutdownMessage(
    val ok: Boolean
) : UartMessage()

/**
 * Heart beat UART message
 */
class HbtMessage : UartMessage()