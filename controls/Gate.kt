package cz.fjerabek.thr.data.controls

import cz.fjerabek.thr.data.enums.EStatus
import cz.fjerabek.thr.data.enums.InvalidPropertyException
import cz.fjerabek.thr.data.enums.gate.EGate
import kotlinx.serialization.Serializable

/**
 * Gate settings block
 * @param status gate power statu
 * @param threshold threshold value
 * @param release release value
 */
@Serializable
data class Gate(
    var status: EStatus,
    var threshold: Byte,
    var release: Byte
) : IControl {

    override fun setPropertyById(id: Byte, value: Int) {
        when (id) {
            EGate.STATUS.propertyId -> status = TypeConverter.convert(value)
            EGate.THRESHOLD.propertyId -> threshold = TypeConverter.convert(value)
            EGate.RELEASE.propertyId -> release = TypeConverter.convert(value)
            else -> throw InvalidPropertyException("Invalid id property ID($id)")
        }
    }

    override fun getPropertyById(id: Byte): Any? {
        return when (id) {
            EGate.STATUS.propertyId -> status
            EGate.THRESHOLD.propertyId -> threshold
            EGate.RELEASE.propertyId -> release
            else -> null
        }
    }

    override fun toDump(dump: ByteArray): ByteArray {
        dump[EGate.STATUS.dumpPosition] = status.value
        dump[EGate.THRESHOLD.dumpPosition] = threshold
        dump[EGate.RELEASE.dumpPosition] = release
        return dump
    }

    companion object {
        /**
         * Creates Gate settings object from midi dump data
         * @param dump data for gate creation
         * @return gate instance
         */
        fun fromDump(dump: ByteArray): Gate {
            return Gate(
                EStatus.fromValue(dump[EGate.STATUS.dumpPosition])!!,
                dump[EGate.THRESHOLD.dumpPosition],
                dump[EGate.RELEASE.dumpPosition]
            )
        }
    }
}