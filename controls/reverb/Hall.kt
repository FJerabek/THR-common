package cz.fjerabek.thr.data.controls.reverb

import cz.fjerabek.thr.data.controls.TypeConverter
import cz.fjerabek.thr.data.enums.EStatus
import cz.fjerabek.thr.data.enums.InvalidPropertyException
import cz.fjerabek.thr.data.enums.reverb.EHall
import cz.fjerabek.thr.data.enums.reverb.EReverb
import cz.fjerabek.thr.data.enums.reverb.EReverbType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Hall")
data class Hall(
    override var status: EStatus,
    var time: Int,
    var preDelay: Int,
    var lowCut: Int,
    var highCut: Int,
    var highRatio: Byte,
    var lowRatio: Byte,
    var level: Byte
) : Reverb(EReverbType.SPRING) {

    override fun duplicate() = this.copy()
    override fun setPropertyById(id: Byte, value: Int) {
        when (id) {
            EReverb.STATUS.propertyId -> status = TypeConverter.convert(value)
            EHall.TIME.propertyId -> time = TypeConverter.convert(value)
            EHall.PRE_DELAY.propertyId -> preDelay = TypeConverter.convert(value)
            EHall.LOW_CUT.propertyId -> lowCut = TypeConverter.convert(value)
            EHall.HIGH_CUT.propertyId -> highCut = TypeConverter.convert(value)
            EHall.HIGH_RATIO.propertyId -> highRatio = TypeConverter.convert(value)
            EHall.LOW_RATIO.propertyId -> lowRatio = TypeConverter.convert(value)
            EHall.LEVEL.propertyId -> level = TypeConverter.convert(value)
            else -> throw InvalidPropertyException("Invalid id property ID($id)")
        }
    }

    override fun getPropertyById(id: Byte): Any? {
        return when (id) {
            EReverb.STATUS.propertyId -> status
            EReverb.TYPE.propertyId -> EReverbType.HALL
            EHall.TIME.propertyId -> time
            EHall.PRE_DELAY.propertyId -> preDelay
            EHall.LOW_CUT.propertyId -> lowCut
            EHall.HIGH_CUT.propertyId -> highCut
            EHall.HIGH_RATIO.propertyId -> highRatio
            EHall.LOW_RATIO.propertyId -> lowRatio
            EHall.LEVEL.propertyId -> level
            else -> null
        }
    }

    constructor(dump: ByteArray) : this(
        EStatus.fromValue(dump[EReverb.STATUS.dumpPosition])!!,
        (dump[EHall.TIME.dumpPosition.first] * 128) + dump[EHall.TIME.dumpPosition.second],
        (dump[EHall.PRE_DELAY.dumpPosition.first] * 128) + dump[EHall.PRE_DELAY.dumpPosition.second],
        (dump[EHall.LOW_CUT.dumpPosition.first] * 128) + dump[EHall.LOW_CUT.dumpPosition.second],
        (dump[EHall.HIGH_CUT.dumpPosition.first] * 128) + dump[EHall.HIGH_CUT.dumpPosition.second],
        dump[EHall.HIGH_RATIO.dumpPosition.first],
        dump[EHall.LOW_RATIO.dumpPosition.first],
        dump[EHall.LEVEL.dumpPosition.first]
    )

    override fun toDump(dump: ByteArray): ByteArray {
        dump[EHall.TIME.dumpPosition.first] = (time / 128).toByte()
        dump[EHall.TIME.dumpPosition.second] = (time % 128).toByte()

        dump[EHall.PRE_DELAY.dumpPosition.first] = (preDelay / 128).toByte()
        dump[EHall.PRE_DELAY.dumpPosition.second] = (preDelay % 128).toByte()

        dump[EHall.LOW_CUT.dumpPosition.first] = (lowCut / 128).toByte()
        dump[EHall.LOW_CUT.dumpPosition.second] = (lowCut % 128).toByte()

        dump[EHall.HIGH_CUT.dumpPosition.first] = (highCut / 128).toByte()
        dump[EHall.HIGH_CUT.dumpPosition.second] = (highCut % 128).toByte()

        dump[EHall.HIGH_RATIO.dumpPosition.first] = highRatio
        dump[EHall.LOW_RATIO.dumpPosition.first] = lowRatio
        dump[EHall.LEVEL.dumpPosition.first] = level

        return super.toDump(dump)
    }
}