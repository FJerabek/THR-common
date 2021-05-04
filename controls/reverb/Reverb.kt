package cz.fjerabek.thr.data.controls.reverb

import cz.fjerabek.thr.data.controls.IControl
import cz.fjerabek.thr.data.enums.EStatus
import cz.fjerabek.thr.data.enums.reverb.EReverb
import cz.fjerabek.thr.data.enums.reverb.EReverbType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Reverb settings block
 * @param reverbType type of reverb
 */
@Serializable
@SerialName("Reverb")
abstract class Reverb(
    //Default value is useless since it is specified by inheriting class
    @Transient
    val reverbType: EReverbType = EReverbType.HALL
) : IControl {

    /**
     * Power status of settings block
     */
    abstract val status: EStatus

    /**
     * Duplicates this object
     * @return duplicated instance
     */
    abstract fun duplicate(): Reverb

    /**
     * Sets correct values in dump array
     * @param dump dump array
     * @return modified dump array
     */
    override fun toDump(dump: ByteArray): ByteArray {
        dump[EReverb.STATUS.dumpPosition] = status.value
        dump[EReverb.TYPE.dumpPosition] = reverbType.id
        return dump
    }

    companion object {
        /**
         * Creates new Reverb instance from MIDI dump data
         * @param dump dump data
         * @return Reverb instance with correct type
         */
        fun fromDump(dump: ByteArray): Reverb {

            return when (EReverbType.fromId(dump[EReverb.TYPE.dumpPosition])!!) {
                EReverbType.HALL -> Hall(dump)
                EReverbType.ROOM -> Room(dump)
                EReverbType.PLATE -> Plate(dump)
                EReverbType.SPRING -> Spring(dump)
            }
        }
    }
}