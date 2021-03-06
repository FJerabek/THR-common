package cz.fjerabek.thr.data.controls.effect

import cz.fjerabek.thr.data.controls.TypeConverter
import cz.fjerabek.thr.data.enums.EStatus
import cz.fjerabek.thr.data.enums.InvalidPropertyException
import cz.fjerabek.thr.data.enums.effect.EChorus
import cz.fjerabek.thr.data.enums.effect.EEffect
import cz.fjerabek.thr.data.enums.effect.EEffectType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Chorus")
data class Chorus(
    override var status: EStatus,
    var speed: Byte,
    var depth: Byte,
    var mix: Byte
) :
    Effect(EEffectType.CHORUS) {

    override fun duplicate() = this.copy()
    override fun setPropertyById(id: Byte, value: Int) {
        when (id) {
            EEffect.STATUS.propertyId -> status = TypeConverter.convert(value)
            EChorus.SPEED.propertyId -> speed = TypeConverter.convert(value)
            EChorus.DEPTH.propertyId -> depth = TypeConverter.convert(value)
            EChorus.MIX.propertyId -> mix = TypeConverter.convert(value)
            else -> throw InvalidPropertyException("Invalid id property ID($id)")
        }
    }

    override fun getPropertyById(id: Byte): Any? {
        return when (id) {
            EEffect.STATUS.propertyId -> status
            EEffect.TYPE.propertyId -> EEffectType.CHORUS
            EChorus.SPEED.propertyId -> speed
            EChorus.DEPTH.propertyId -> depth
            EChorus.MIX.propertyId -> mix
            else -> null
        }
    }

    constructor(dump: ByteArray) : this(
        EStatus.fromValue(dump[EEffect.STATUS.dumpPosition])!!,
        dump[EChorus.SPEED.dumpPosition],
        dump[EChorus.DEPTH.dumpPosition],
        dump[EChorus.MIX.dumpPosition]
    )

    override fun toDump(dump: ByteArray): ByteArray {
        dump[EChorus.SPEED.dumpPosition] = speed
        dump[EChorus.DEPTH.dumpPosition] = depth
        dump[EChorus.MIX.dumpPosition] = mix
        return super.toDump(dump)
    }
}