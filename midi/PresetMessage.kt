package cz.fjerabek.thr.data.midi

import cz.fjerabek.thr.data.controls.*
import cz.fjerabek.thr.data.controls.compressor.Compressor
import cz.fjerabek.thr.data.controls.compressor.Rack
import cz.fjerabek.thr.data.controls.compressor.Stomp
import cz.fjerabek.thr.data.controls.effect.*
import cz.fjerabek.thr.data.controls.reverb.*
import cz.fjerabek.thr.data.enums.InvalidPropertyException
import cz.fjerabek.thr.data.enums.compressor.ECompressor
import cz.fjerabek.thr.data.enums.compressor.ECompressorType
import cz.fjerabek.thr.data.enums.compressor.ERack
import cz.fjerabek.thr.data.enums.effect.EEffect
import cz.fjerabek.thr.data.enums.effect.EEffectType
import cz.fjerabek.thr.data.enums.reverb.EReverb
import cz.fjerabek.thr.data.enums.reverb.EReverbType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Preset dump message. Contains all values from combo
 * @param name preset name
 * @param mainPanel main panel settings
 * @param compressor compressor settings
 * @param effect effect settings
 * @param delay delay settings
 * @param reverb reverb settings
 * @param gate gate settings
 */
@Serializable
@SerialName("Dump")
data class PresetMessage(
    var name: String,
    var mainPanel: MainPanel,
    var compressor: Compressor? = null,
    var effect: Effect? = null,
    var delay: Delay? = null,
    var reverb: Reverb? = null,
    var gate: Gate? = null
) : IMidiMessage {

    constructor(dump: ByteArray) : this(
        dump.sliceArray(prefix.size..80)
            .dropLastWhile { it.toInt() == 0x0 }
            .toByteArray()
            .decodeToString(),
        MainPanel.fromDump(dump),
        Compressor.fromDump(dump),
        Effect.fromDump(dump),
        Delay.fromDump(dump),
        Reverb.fromDump(dump),
        Gate.fromDump(dump)
    )

    /**
     * Returns value of property by its id
     * @param id property id
     * @return property value
     */
    fun getByControlPropertyId(id: Byte): Any? {
        return getControlById(id)?.getPropertyById(id)
    }

    /**
     * Gets settings block representation by property ID
     * @param id property ID
     * @return Property representation
     */
    private fun getControlById(id: Byte): IControl? {
        return when (id) {
            in 0x00..0x06 -> {
                mainPanel
            }
            in 0x10..0x1F -> {
                compressor
            }
            in 0x20..0x2F -> {
                effect
            }
            in 0x30..0x3F -> {
                delay
            }
            in 0x40..0x4F -> {
                reverb
            }
            in 0x50..0x5F -> {
                gate
            }
            else -> null
        }
    }

    /**
     * Sets value to property by its ID
     * @param id property ID
     * @param value property new value
     */
    fun setByControlPropertyId(id: Byte, value: Int) {
        val dumpArray = prefix + ByteArray(257) { 0 }
        when (id) {
            ECompressor.TYPE.propertyId -> {
                compressor = compressor!!.toDump(dumpArray).let {
                    when (value.toByte()) {
                        ECompressorType.STOMP.id -> {
                            Stomp(it)
                        }
                        ECompressorType.RACK.id -> {
                            Rack(it)
                        }
                        else -> throw InvalidPropertyException("Compressor type change property value invalid")
                    }
                }
            }
            EEffect.TYPE.propertyId -> {
                effect = effect!!.toDump(dumpArray).let {
                    when (value.toByte()) {
                        EEffectType.PHASER.id -> {
                            Phaser(it)
                        }
                        EEffectType.TREMOLO.id -> {
                            Tremolo(it)
                        }
                        EEffectType.FLANGER.id -> {
                            Flanger(it)
                        }
                        EEffectType.CHORUS.id -> {
                            Chorus(it)
                        }
                        else -> throw InvalidPropertyException("Effect type change property value invalid")
                    }
                }
            }
            EReverb.TYPE.propertyId -> {
                reverb = reverb!!.toDump(dumpArray).let {
                    when (value.toByte()) {
                        EReverbType.ROOM.id -> {
                            Room(it)
                        }
                        EReverbType.PLATE.id -> {
                            Plate(it)
                        }
                        EReverbType.HALL.id -> {
                            Hall(it)
                        }
                        EReverbType.SPRING.id -> {
                            Spring(it)
                        }
                        else -> throw InvalidPropertyException("Reverb type change property value invalid")
                    }
                }
            }
            else -> getControlById(id)?.setPropertyById(id, value)
        }

    }

    /**
     * Calculates checksum for provided data
     * @param data data for checksum calculation
     * @return calculated checksum
     */
    private fun calculateChecksum(data: ByteArray): Byte {
        var count = 0x71
        for (i in data) {
            count += i
        }
        return ((count.inv() + 1) and 0x7f).toByte()
    }

    companion object {
        val prefix =
            byteArrayOf(67, 125, 0, 2, 12, 68, 84, 65, 49, 65, 108, 108, 80, 0, 0, 127, 127)
    }

    /**
     * System exclusive representation
     */
    override val sysex: ByteArray
        get() {
            val dumpArray = prefix + ByteArray(257) { 0 }
            var nameDump = name.encodeToByteArray()
            if (nameDump.size > 64) {
                nameDump = nameDump.sliceArray(0..63)
            }

            nameDump.copyInto(dumpArray, prefix.size)
            mainPanel.toDump(dumpArray)
            compressor?.toDump(dumpArray)
            effect?.toDump(dumpArray)
            delay?.toDump(dumpArray)
            reverb?.toDump(dumpArray)
            gate?.toDump(dumpArray)

            dumpArray[dumpArray.lastIndex] =
                calculateChecksum(dumpArray.sliceArray(prefix.size..dumpArray.lastIndex))
            return dumpArray
        }

    /**
     * Duplicates this object recursively
     * @return duplicated object
     */
    fun duplicate(): PresetMessage {
        return PresetMessage(
            name,
            mainPanel.copy(),
            compressor?.duplicate(),
            effect?.duplicate(),
            delay?.copy(),
            reverb?.duplicate(),
            gate?.copy()
        )
    }

}