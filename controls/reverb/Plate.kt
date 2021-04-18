package cz.fjerabek.thr.data.controls.reverb

import cz.fjerabek.thr.data.controls.TypeConverter
import cz.fjerabek.thr.data.enums.EStatus
import cz.fjerabek.thr.data.enums.InvalidPropertyException
import cz.fjerabek.thr.data.enums.reverb.EPlate
import cz.fjerabek.thr.data.enums.reverb.EReverb
import cz.fjerabek.thr.data.enums.reverb.EReverbType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@SerialName("Plate")
data class Plate(
    override var status: EStatus,
    var time: Int,
    var preDelay: Int,
    var lowCut: Int,
    var highCut: Int,
    var highRatio: Byte,
    var lowRatio: Byte,
    var level: Byte
) : Reverb(EReverbType.PLATE) {

    override fun duplicate() = this.copy()
    override fun setPropertyById(id: Byte, value: Int) {
        when (id) {
            EReverb.STATUS.propertyId -> status = TypeConverter.convert(value)
            EPlate.TIME.propertyId -> time = TypeConverter.convert(value)
            EPlate.PRE_DELAY.propertyId -> preDelay = TypeConverter.convert(value)
            EPlate.LOW_CUT.propertyId -> lowCut = TypeConverter.convert(value)
            EPlate.HIGH_CUT.propertyId -> highCut = TypeConverter.convert(value)
            EPlate.HIGH_RATIO.propertyId -> highRatio = TypeConverter.convert(value)
            EPlate.LOW_RATIO.propertyId -> lowRatio = TypeConverter.convert(value)
            EPlate.LEVEL.propertyId -> level = TypeConverter.convert(value)
            else -> throw InvalidPropertyException("Invalid id property ID($id)")
        }
    }

    override fun getPropertyById(id: Byte): Any? {
        return when (id) {
            EReverb.STATUS.propertyId -> status
            EReverb.TYPE.propertyId -> EReverbType.PLATE
            EPlate.TIME.propertyId -> time
            EPlate.PRE_DELAY.propertyId -> preDelay
            EPlate.LOW_CUT.propertyId -> lowCut
            EPlate.HIGH_CUT.propertyId -> highCut
            EPlate.HIGH_RATIO.propertyId -> highRatio
            EPlate.LOW_RATIO.propertyId -> lowRatio
            EPlate.LEVEL.propertyId -> level
            else -> null
        }
    }

    constructor(dump: ByteArray) : this(
        EStatus.fromValue(dump[EReverb.STATUS.dumpPosition])!!,
        (dump[EPlate.TIME.dumpPosition.first] * 128) + dump[EPlate.TIME.dumpPosition.second],
        (dump[EPlate.PRE_DELAY.dumpPosition.first] * 128) + dump[EPlate.PRE_DELAY.dumpPosition.second],
        (dump[EPlate.LOW_CUT.dumpPosition.first] * 128) + dump[EPlate.LOW_CUT.dumpPosition.second],
        (dump[EPlate.HIGH_CUT.dumpPosition.first] * 128) + dump[EPlate.HIGH_CUT.dumpPosition.second],
        dump[EPlate.HIGH_RATIO.dumpPosition.first],
        dump[EPlate.LOW_RATIO.dumpPosition.first],
        dump[EPlate.LEVEL.dumpPosition.first]
    )

    override fun toDump(dump: ByteArray): ByteArray {
        dump[EPlate.TIME.dumpPosition.first] = (time / 128).toByte()
        dump[EPlate.TIME.dumpPosition.second] = (time % 128).toByte()

        dump[EPlate.PRE_DELAY.dumpPosition.first] = (preDelay / 128).toByte()
        dump[EPlate.PRE_DELAY.dumpPosition.second] = (preDelay % 128).toByte()

        dump[EPlate.LOW_CUT.dumpPosition.first] = (lowCut / 128).toByte()
        dump[EPlate.LOW_CUT.dumpPosition.second] = (lowCut % 128).toByte()

        dump[EPlate.HIGH_CUT.dumpPosition.first] = (highCut / 128).toByte()
        dump[EPlate.HIGH_CUT.dumpPosition.second] = (highCut % 128).toByte()

        dump[EPlate.HIGH_RATIO.dumpPosition.first] = highRatio
        dump[EPlate.LOW_RATIO.dumpPosition.first] = lowRatio
        dump[EPlate.LEVEL.dumpPosition.first] = level

        return super.toDump(dump)
    }
}