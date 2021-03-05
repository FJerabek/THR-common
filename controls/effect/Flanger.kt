package cz.fjerabek.thr.data.controls.effect

import cz.fjerabek.thr.data.controls.TypeConverter
import cz.fjerabek.thr.data.enums.EStatus
import cz.fjerabek.thr.data.enums.InvalidPropertyException
import cz.fjerabek.thr.data.enums.effect.EChorus
import cz.fjerabek.thr.data.enums.effect.EEffect
import cz.fjerabek.thr.data.enums.effect.EEffectType
import cz.fjerabek.thr.data.enums.effect.EFlanger
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Flanger")
class Flanger(
    override var status: EStatus,
    var speed: Byte,
    var manual: Byte,
    var depth: Byte,
    var feedback: Byte,
    var spread: Byte
) :
    Effect(EEffectType.FLANGER) {


    override fun setPropertyById(id: Byte, value: Int) {
        when (id) {
            EEffect.STATUS.propertyId -> status = TypeConverter.convert(value)
            EFlanger.SPEED.propertyId -> speed = TypeConverter.convert(value)
            EFlanger.DEPTH.propertyId -> depth = TypeConverter.convert(value)
            EFlanger.MANUAL.propertyId -> manual = TypeConverter.convert(value)
            EFlanger.FEEDBACK.propertyId -> feedback = TypeConverter.convert(value)
            EFlanger.SPREAD.propertyId -> spread = TypeConverter.convert(value)
            else -> throw InvalidPropertyException("Invalid id property ID($id)")
        }
    }

    override fun getPropertyById(id: Byte): Any? {
        return when (id) {
            EEffect.STATUS.propertyId -> status
            EEffect.TYPE.propertyId -> EEffectType.FLANGER
            EFlanger.SPEED.propertyId -> speed
            EFlanger.DEPTH.propertyId -> depth
            EFlanger.MANUAL.propertyId -> manual
            EFlanger.FEEDBACK.propertyId -> feedback
            EFlanger.SPREAD.propertyId -> spread
            else -> null
        }
    }

    constructor(dump: ByteArray) : this(
        EStatus.fromValue(dump[EEffect.STATUS.dumpPosition])!!,
        dump[EFlanger.SPEED.dumpPosition],
        dump[EFlanger.MANUAL.dumpPosition],
        dump[EFlanger.DEPTH.dumpPosition],
        dump[EFlanger.FEEDBACK.dumpPosition],
        dump[EFlanger.SPREAD.dumpPosition]
    )

    override fun toDump(dump: ByteArray): ByteArray {
        dump[EFlanger.SPEED.dumpPosition] = speed
        dump[EFlanger.MANUAL.dumpPosition] = manual
        dump[EFlanger.DEPTH.dumpPosition] = depth
        dump[EFlanger.FEEDBACK.dumpPosition] = feedback
        dump[EFlanger.SPREAD.dumpPosition] = spread
        return super.toDump(dump)
    }
}