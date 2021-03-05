package cz.fjerabek.thr.data.controls

import cz.fjerabek.thr.data.enums.EStatus
import cz.fjerabek.thr.data.enums.InvalidPropertyException
import cz.fjerabek.thr.data.enums.delay.EDelay
import kotlinx.serialization.Serializable

@Serializable
class Delay(
    var status : EStatus,
    var time : Int,
    var feedback : Byte,
    var highCut : Int,
    var lowCut : Int,
    var level : Byte
) : IControl {

    override fun setPropertyById(id: Byte, value: Int) {
        when (id) {
            EDelay.STATUS.propertyId -> status = TypeConverter.convert(value)
            EDelay.TIME.propertyId -> time = TypeConverter.convert(value)
            EDelay.FEEDBACK.propertyId -> feedback = TypeConverter.convert(value)
            EDelay.HIGH_CUT.propertyId -> highCut = TypeConverter.convert(value)
            EDelay.LOW_CUT.propertyId -> lowCut = TypeConverter.convert(value)
            EDelay.LEVEL.propertyId -> level = TypeConverter.convert(value)
            else -> throw InvalidPropertyException("Invalid id property ID($id)")
        }
    }

    override fun getPropertyById(id: Byte): Any? {
        return when(id){
            EDelay.STATUS.propertyId -> status
            EDelay.TIME.propertyId -> time
            EDelay.FEEDBACK.propertyId -> feedback
            EDelay.HIGH_CUT.propertyId -> highCut
            EDelay.LOW_CUT.propertyId -> lowCut
            EDelay.LEVEL.propertyId -> level
            else -> null
        }
    }

    override fun toDump(dump: ByteArray): ByteArray {
        dump[EDelay.STATUS.dumpPosition.first] = status.value
        dump[EDelay.TIME.dumpPosition.first] = (time / 128).toByte()
        dump[EDelay.TIME.dumpPosition.second] = (time % 128).toByte()
        dump[EDelay.FEEDBACK.dumpPosition.first] = feedback
        dump[EDelay.HIGH_CUT.dumpPosition.first] = (highCut / 128).toByte()
        dump[EDelay.HIGH_CUT.dumpPosition.second] = (highCut % 128).toByte()
        dump[EDelay.LOW_CUT.dumpPosition.first] = (lowCut / 128).toByte()
        dump[EDelay.LOW_CUT.dumpPosition.second] = (lowCut % 128).toByte()
        dump[EDelay.LEVEL.dumpPosition.first] = level

        return dump
    }

    companion object {
        fun fromDump(dump : ByteArray) : Delay {

            return Delay(
                EStatus.fromValue(dump[EDelay.STATUS.dumpPosition.first])!!,
                (dump[EDelay.TIME.dumpPosition.first] * 128) + dump[EDelay.TIME.dumpPosition.second],
                dump[EDelay.FEEDBACK.dumpPosition.first],
                (dump[EDelay.HIGH_CUT.dumpPosition.first] * 128) + dump[EDelay.HIGH_CUT.dumpPosition.second],
                (dump[EDelay.LOW_CUT.dumpPosition.first] * 128) + dump[EDelay.LOW_CUT.dumpPosition.second],
                dump[EDelay.LEVEL.dumpPosition.first]
            )
        }
    }
}