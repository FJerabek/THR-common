package cz.fjerabek.thr.data.controls.effect

import cz.fjerabek.thr.data.controls.TypeConverter
import cz.fjerabek.thr.data.enums.EStatus
import cz.fjerabek.thr.data.enums.InvalidPropertyException
import cz.fjerabek.thr.data.enums.effect.EEffect
import cz.fjerabek.thr.data.enums.effect.EEffectType
import cz.fjerabek.thr.data.enums.effect.ETremolo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Tremolo type of effect settings
 * @param freq frequency value
 * @param depth depth value
 * @param status effect power status
 */
@Serializable
@SerialName("Tremolo")
data class Tremolo(
    override var status: EStatus,
    var freq: Byte,
    var depth: Byte
) : Effect(EEffectType.TREMOLO) {

    override fun duplicate() = this.copy()
    override fun setPropertyById(id: Byte, value: Int) {
        when (id) {
            EEffect.STATUS.propertyId -> status = TypeConverter.convert(value)
            ETremolo.DEPTH.propertyId -> depth = TypeConverter.convert(value)
            ETremolo.FREQ.propertyId -> freq = TypeConverter.convert(value)
            else -> throw InvalidPropertyException("Invalid id property ID($id)")
        }
    }

    override fun getPropertyById(id: Byte): Any? {
        return when (id) {
            EEffect.STATUS.propertyId -> status
            EEffect.TYPE.propertyId -> EEffectType.TREMOLO
            ETremolo.DEPTH.propertyId -> depth
            ETremolo.FREQ.propertyId -> freq
            else -> null
        }
    }

    /**
     * Creates instance from MIDI dump data
     * @param dump MIDI dump data
     */
    constructor(dump: ByteArray) : this(
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