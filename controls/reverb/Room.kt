package cz.fjerabek.thr.data.controls.reverb

import cz.fjerabek.thr.data.controls.TypeConverter
import cz.fjerabek.thr.data.enums.EStatus
import cz.fjerabek.thr.data.enums.InvalidPropertyException
import cz.fjerabek.thr.data.enums.reverb.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Room")
class Room(
        override var status: EStatus,
        var time: Int,
        var preDelay: Int,
        var lowCut: Int,
        var highCut: Int,
        var highRatio: Byte,
        var lowRatio: Byte,
        var level: Byte
) : Reverb(EReverbType.ROOM) {

    override fun setPropertyById(id: Byte, value: Int) {
        when (id) {
            EReverb.STATUS.propertyId -> status = TypeConverter.convert(value)
            ERoom.TIME.propertyId -> time = TypeConverter.convert(value)
            ERoom.PRE_DELAY.propertyId -> preDelay = TypeConverter.convert(value)
            ERoom.LOW_CUT.propertyId -> lowCut = TypeConverter.convert(value)
            ERoom.HIGH_CUT.propertyId -> highCut = TypeConverter.convert(value)
            ERoom.HIGH_RATIO.propertyId -> highRatio = TypeConverter.convert(value)
            ERoom.LOW_RATIO.propertyId -> lowRatio = TypeConverter.convert(value)
            ERoom.LEVEL.propertyId -> level = TypeConverter.convert(value)
            else -> throw InvalidPropertyException("Invalid id property ID($id)")
        }
    }

    override fun getPropertyById(id: Byte): Any? {
        return when(id){
            EReverb.STATUS.propertyId -> status
            EReverb.TYPE.propertyId -> EReverbType.ROOM
            ERoom.TIME.propertyId -> time
            ERoom.PRE_DELAY.propertyId -> preDelay
            ERoom.LOW_CUT.propertyId -> lowCut
            ERoom.HIGH_CUT.propertyId -> highCut
            ERoom.HIGH_RATIO.propertyId -> highRatio
            ERoom.LOW_RATIO.propertyId -> lowRatio
            ERoom.LEVEL.propertyId -> level
            else -> null
        }
    }


    constructor(dump: ByteArray): this(
            EStatus.fromValue(dump[EReverb.STATUS.dumpPosition])!!,
            (dump[ERoom.TIME.dumpPosition.first] * 128) + dump[ERoom.TIME.dumpPosition.second],
            (dump[ERoom.PRE_DELAY.dumpPosition.first] * 128) + dump[ERoom.PRE_DELAY.dumpPosition.second],
            (dump[ERoom.LOW_CUT.dumpPosition.first] * 128) + dump[ERoom.LOW_CUT.dumpPosition.second],
            (dump[ERoom.HIGH_CUT.dumpPosition.first] * 128) + dump[ERoom.HIGH_CUT.dumpPosition.second],
            dump[ERoom.HIGH_RATIO.dumpPosition.first],
            dump[ERoom.LOW_RATIO.dumpPosition.first],
            dump[ERoom.LEVEL.dumpPosition.first]
    )

    override fun toDump(dump: ByteArray): ByteArray {
        dump[ERoom.TIME.dumpPosition.first] = (time / 128).toByte()
        dump[ERoom.TIME.dumpPosition.second] = (time % 128).toByte()

        dump[ERoom.PRE_DELAY.dumpPosition.first] = (preDelay / 128).toByte()
        dump[ERoom.PRE_DELAY.dumpPosition.second] = (preDelay % 128).toByte()

        dump[ERoom.LOW_CUT.dumpPosition.first] = (lowCut / 128).toByte()
        dump[ERoom.LOW_CUT.dumpPosition.second] = (lowCut % 128).toByte()

        dump[ERoom.HIGH_CUT.dumpPosition.first] = (highCut / 128).toByte()
        dump[ERoom.HIGH_CUT.dumpPosition.second] = (highCut % 128).toByte()

        dump[ERoom.HIGH_RATIO.dumpPosition.first] = highRatio
        dump[ERoom.LOW_RATIO.dumpPosition.first] = lowRatio
        dump[ERoom.LEVEL.dumpPosition.first] = level

        return super.toDump(dump)
    }
}