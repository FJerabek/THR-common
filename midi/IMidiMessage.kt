package cz.fjerabek.thr.data.midi

import cz.fjerabek.thr.data.bluetooth.IBluetoothMessage

/**
 * Interface representing MIDI message
 */
interface IMidiMessage: IBluetoothMessage {
    /**
     * System exclusive message representation
     */
    val sysex: ByteArray
}