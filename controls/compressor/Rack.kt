package cz.fjerabek.thr.data.controls.compressor

import cz.fjerabek.thr.data.controls.TypeConverter
import cz.fjerabek.thr.data.enums.EStatus
import cz.fjerabek.thr.data.enums.InvalidPropertyException
import cz.fjerabek.thr.data.enums.compressor.ECompressor
import cz.fjerabek.thr.data.enums.compressor.ECompressorType
import cz.fjerabek.thr.data.enums.compressor.ERack
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Rack")
data class Rack(
    override var status: EStatus,
    var threshold: Int,
    var attack: Byte,
    var release: Byte,
    var ratio: Byte,
    var knee: Byte,
    var output: Int
) : Compressor(ECompressorType.RACK) {

    override fun duplicate() = this.copy()
    override fun setPropertyById(id: Byte, value: Int) {
        when (id) {
            ECompressor.STATUS.propertyId -> status = TypeConverter.convert(value)
            ERack.THRESHOLD.propertyId -> threshold = TypeConverter.convert(value)
            ERack.ATTACK.propertyId -> attack = TypeConverter.convert(value)
            ERack.RELEASE.propertyId -> release = TypeConverter.convert(value)
            ERack.RATIO.propertyId -> ratio = TypeConverter.convert(value)
            ERack.KNEE.propertyId -> knee = TypeConverter.convert(value)
            ERack.OUTPUT.propertyId -> output = TypeConverter.convert(value)
            else -> throw InvalidPropertyException("Invalid id property ID($id)")
        }
    }

    override fun getPropertyById(id: Byte): Any? {
        return when (id) {
            ECompressor.STATUS.propertyId -> status
            ECompressor.TYPE.propertyId -> ECompressorType.RACK
            ERack.THRESHOLD.propertyId -> threshold
            ERack.ATTACK.propertyId -> attack
            ERack.RELEASE.propertyId -> release
            ERack.RATIO.propertyId -> ratio
            ERack.KNEE.propertyId -> knee
            ERack.OUTPUT.propertyId -> output
            else -> null
        }
    }

    constructor(dump: ByteArray) : this(
        EStatus.fromValue(dump[ECompressor.STATUS.dumpPosition])!!,
        (dump[ERack.THRESHOLD.dumpPosition.first] * 128) + dump[ERack.THRESHOLD.dumpPosition.second],
        dump[ERack.ATTACK.dumpPosition.first],
        dump[ERack.RELEASE.dumpPosition.first],
        dump[ERack.RATIO.dumpPosition.first],
        dump[ERack.KNEE.dumpPosition.first],
        (dump[ERack.OUTPUT.dumpPosition.first] * 128) + dump[ERack.OUTPUT.dumpPosition.second]
    )

    override fun toDump(dump: ByteArray): ByteArray {
        dump[ERack.THRESHOLD.dumpPosition.first] = (threshold / 128).toByte()
        dump[ERack.THRESHOLD.dumpPosition.second] = (threshold % 128).toByte()

        dump[ERack.ATTACK.dumpPosition.first] = attack
        dump[ERack.RELEASE.dumpPosition.first] = release
        dump[ERack.RATIO.dumpPosition.first] = ratio

        dump[ERack.KNEE.dumpPosition.first] = knee
        dump[ERack.OUTPUT.dumpPosition.first] = (output / 128).toByte()
        dump[ERack.OUTPUT.dumpPosition.second] = (output % 128).toByte()

        return super.toDump(dump)
    }
}