package cz.fjerabek.thr.data.controls.compressor

import cz.fjerabek.thr.data.controls.TypeConverter
import cz.fjerabek.thr.data.enums.EStatus
import cz.fjerabek.thr.data.enums.compressor.ECompressor
import cz.fjerabek.thr.data.enums.compressor.ECompressorType
import cz.fjerabek.thr.data.enums.compressor.ERack
import cz.fjerabek.thr.data.enums.compressor.EStomp
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.security.InvalidParameterException

@Serializable
@SerialName("Stomp")
class Stomp (
        override var status: EStatus,
        var sustain : Byte,
        var output : Byte):
        Compressor(ECompressorType.STOMP) {


    override fun setPropertyById(id: Byte, value: Int) {
        when(id){
            ECompressor.STATUS.propertyId -> status = TypeConverter.convert(value)
            EStomp.SUSTAIN.propertyId -> sustain = TypeConverter.convert(value)
            EStomp.OUTPUT.propertyId -> output = TypeConverter.convert(value)
            else -> throw InvalidParameterException("Invalid id property ID($id)")
        }
    }

    override fun getPropertyById(id: Byte): Any? {
        return when(id){
            ECompressor.STATUS.propertyId -> status
            ECompressor.TYPE.propertyId -> ECompressorType.STOMP
            EStomp.SUSTAIN.propertyId -> sustain
            EStomp.OUTPUT.propertyId -> output
            else -> throw InvalidParameterException("Invalid id property ID($id)")
        }
    }

    constructor(dump: ByteArray): this(
            EStatus.fromValue(dump[ECompressor.STATUS.dumpPosition])!!,
            dump[EStomp.SUSTAIN.dumpPosition],
            dump[EStomp.OUTPUT.dumpPosition]
    )

    override fun toDump(dump: ByteArray): ByteArray {
        dump[EStomp.SUSTAIN.dumpPosition] = sustain
        dump[EStomp.OUTPUT.dumpPosition] = output
        return super.toDump(dump)
    }
}