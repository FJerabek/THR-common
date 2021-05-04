package cz.fjerabek.thr.data.controls.reverb

import cz.fjerabek.thr.data.controls.TypeConverter
import cz.fjerabek.thr.data.enums.EStatus
import cz.fjerabek.thr.data.enums.InvalidPropertyException
import cz.fjerabek.thr.data.enums.reverb.EReverb
import cz.fjerabek.thr.data.enums.reverb.EReverbType
import cz.fjerabek.thr.data.enums.reverb.ESpring
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * Spring reverb type
 * @param reverb reverb value
 * @param filter filter value
 * @param status reverb status
 */
@Serializable
@SerialName("Spring")
data class Spring(
    override var status: EStatus,
    var reverb: Byte,
    var filter: Byte
) : Reverb(EReverbType.SPRING) {

    override fun duplicate() = this.copy()
    override fun setPropertyById(id: Byte, value: Int) {
        when (id) {
            EReverb.STATUS.propertyId -> status = TypeConverter.convert(value)
            ESpring.REVERB.propertyId -> reverb = TypeConverter.convert(value)
            ESpring.FILTER.propertyId -> filter = TypeConverter.convert(value)
            else -> throw InvalidPropertyException("Invalid id property ID($id)")
        }
    }

    override fun getPropertyById(id: Byte): Any? {
        return when (id) {
            EReverb.STATUS.propertyId -> status
            EReverb.TYPE.propertyId -> EReverbType.HALL
            ESpring.REVERB.propertyId -> reverb
            ESpring.FILTER.propertyId -> filter
            else -> null
        }
    }

    /**
     * Creates Spring instance from dump data
     */
    constructor(dump: ByteArray) : this(
        EStatus.fromValue(dump[EReverb.STATUS.dumpPosition])!!,
        dump[ESpring.REVERB.dumpPosition],
        dump[ESpring.FILTER.dumpPosition]
    )

    override fun toDump(dump: ByteArray): ByteArray {
        dump[ESpring.REVERB.dumpPosition] = reverb
        dump[ESpring.FILTER.dumpPosition] = filter
        return super.toDump(dump)
    }
}