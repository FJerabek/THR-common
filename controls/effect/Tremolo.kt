package cz.fjerabek.thr.data.controls.effect

import cz.fjerabek.thr.data.controls.TypeConverter
import cz.fjerabek.thr.data.enums.EStatus
import cz.fjerabek.thr.data.enums.effect.*
import cz.fjerabek.thr.data.enums.reverb.EHall
import cz.fjerabek.thr.data.enums.reverb.EReverb
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.security.InvalidParameterException

@Serializable
@SerialName("Tremolo")
class Tremolo (
    override var status: EStatus,
    var freq : Byte,
    var depth : Byte
) : Effect(EEffectType.TREMOLO) {

    override fun setPropertyById(id: Byte, value: Int) {
        when (id) {
            EEffect.STATUS.propertyId -> status = TypeConverter.convert(value)
            ETremolo.DEPTH.propertyId -> depth = TypeConverter.convert(value)
            ETremolo.FREQ.propertyId -> freq = TypeConverter.convert(value)
            else -> throw InvalidParameterException("Invalid id property ID($id)")
        }
    }

    override fun getPropertyById(id: Byte): Any? {
        return when(id){
            EEffect.STATUS.propertyId -> status
            EEffect.TYPE.propertyId -> EEffectType.TREMOLO
            ETremolo.DEPTH.propertyId -> depth
            ETremolo.FREQ.propertyId -> freq
            else -> null
        }
    }

    constructor(dump: ByteArray): this(
            EStatus.fromValue(dump[EEffect.STATUS.dumpPosition])!!,
            dump[ETremolo.FREQ.dumpPosition],
            dump[ETremolo.DEPTH.dumpPosition]
    )

    override fun toDump(dump: ByteArray): ByteArray {
        dump[ETremolo.FREQ.dumpPosition] = freq
        dump[ETremolo.DEPTH.dumpPosition] = depth
        return super.toDump(dump)
    }
}