package cz.fjerabek.thr.data.bluetooth

import cz.fjerabek.thr.data.midi.PresetMessage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

interface IBluetoothMessage

/**
 * Request for firmware version
 */
@Serializable
@SerialName("FwVersionRq")
class FwVersionRq: IBluetoothMessage

/**
 * Request for hardware status info
 */
@Serializable
@SerialName("HwStatusRq")
class HwStatusRq: IBluetoothMessage

/**
 * Request for all loaded presets
 */
@Serializable
@SerialName("PresetsRq")
class PresetsRq: IBluetoothMessage

/**
 * Request for current combo preset
 */
@Serializable
@SerialName("CurrentPresetRq")
class CurrentPresetRq: IBluetoothMessage

/**
 * Request for current combo preset
 */
@Serializable
@SerialName("CurrentPresetIndexRq")
class CurrentPresetIndexRq: IBluetoothMessage

/**
 * Request if THR is connected
 */
@Serializable
@SerialName("ConnectedRq")
class ConnectedRq: IBluetoothMessage

/**
 * Response if THR is connected
 */
@Serializable
@SerialName("Connected")
data class Connected(val connected: Boolean): IBluetoothMessage

/**
 * Request for removal of presets
 * @property index preset index to remove
 */
@Serializable
@SerialName("RemovePresetRq")
data class RemovePresetRq(val index: Int): IBluetoothMessage

/**
 * Preset addition request
 * @property preset preset to add
 */
@Serializable
@SerialName("AddPresetRq")
data class AddPresetRq(val preset: PresetMessage): IBluetoothMessage

/**
 * Request for replacing all presets with custom ones
 * @property presets custom presets
 */
@Serializable
@SerialName("SetPresetsRq")
data class SetPresetsRq(val presets: List<PresetMessage>): IBluetoothMessage

/**
 * Response message with all loaded presets
 * @property presets loaded presets
 */
@Serializable
@SerialName("PresetsResponse")
data class PresetsResponse(val presets: List<PresetMessage>): IBluetoothMessage

/**
 * Request for setting preset on combo
 * @property index preset index
 */
@Serializable
@SerialName("PresetSelect")
data class PresetSelect(val index: Int): IBluetoothMessage

/**
 * Turn on/off combo lamp
 * @property on is lamp on
 */
@Serializable
@SerialName("Lamp")
data class Lamp(val on: Boolean): IBluetoothMessage

/**
 * Turn on/off wide stereo
 * @param on is wide stereo on
 */
@Serializable
@SerialName("WideStereo")
data class WideStereo(val on: Boolean): IBluetoothMessage

