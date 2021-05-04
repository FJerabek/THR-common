package cz.fjerabek.thr.data.controls.compressor

import cz.fjerabek.thr.data.controls.IControl
import cz.fjerabek.thr.data.enums.EStatus
import cz.fjerabek.thr.data.enums.compressor.ECompressor
import cz.fjerabek.thr.data.enums.compressor.ECompressorType
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Class representing compressor effect in combo
 * @param compressorType type of compressor used
 */
@Serializable
abstract class Compressor(
    //Default value is useless since it is specified by inheriting class but without it compiler complains
    @Transient
    val compressorType: ECompressorType = ECompressorType.STOMP
) : IControl {

    /**
     * Compressor status. If it is turned on or off
     */
    abstract val status : EStatus

    /**
     * Creates a duplicate object with same values
     */
    abstract fun duplicate(): Compressor

    /**
     * Inserts compressor values into dump array
     * @return modified dump array with current values
     */
    override fun toDump(dump: ByteArray): ByteArray {
        dump[ECompressor.STATUS.dumpPosition] = status.value
        dump[ECompressor.TYPE.dumpPosition] = compressorType.id

        return dump
    }

    companion object {
        /**
         * Creates a compressor object with values from dump array
         * @param dump dump array for compressor creation
         */
        fun fromDump(dump : ByteArray) : Compressor {
            return when(ECompressorType.fromId(dump[ECompressor.TYPE.dumpPosition])!!){
                ECompressorType.STOMP -> Stomp(dump)
                ECompressorType.RACK -> Rack(dump)
            }
        }
    }

}