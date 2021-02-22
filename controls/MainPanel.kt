package cz.fjerabek.thr.data.controls

import cz.fjerabek.thr.data.enums.mainpanel.EAmpType
import cz.fjerabek.thr.data.enums.mainpanel.ECabinetType
import cz.fjerabek.thr.data.enums.mainpanel.EMainPanel
import kotlinx.serialization.Serializable
import java.security.InvalidParameterException

@Serializable
class MainPanel(
    var amp: EAmpType,
    var gain: Byte,
    var master: Byte,
    var bass: Byte,
    var middle: Byte,
    var treble: Byte,
    var cabinet: ECabinetType?
) : IControl {


    override fun toDump(dump: ByteArray): ByteArray {
        dump[EMainPanel.AMP.dumpPosition] = amp.id
        dump[EMainPanel.GAIN.dumpPosition] = gain
        dump[EMainPanel.MASTER.dumpPosition] = master
        dump[EMainPanel.BASS.dumpPosition] = bass
        dump[EMainPanel.MIDDLE.dumpPosition] = middle
        dump[EMainPanel.TREBLE.dumpPosition] = treble
        dump[EMainPanel.CABINET.dumpPosition] = cabinet?.id ?: 0
        return dump
    }

    override fun setPropertyById(id: Byte, value: Int) {
        when (id) {
            EMainPanel.AMP.propertyId -> amp = TypeConverter.convert(value)
            EMainPanel.GAIN.propertyId -> gain = TypeConverter.convert(value)
            EMainPanel.MASTER.propertyId -> master = TypeConverter.convert(value)
            EMainPanel.BASS.propertyId -> bass = TypeConverter.convert(value)
            EMainPanel.MIDDLE.propertyId -> middle = TypeConverter.convert(value)
            EMainPanel.TREBLE.propertyId -> treble = TypeConverter.convert(value)
            EMainPanel.CABINET.propertyId -> cabinet = if(value < 6) TypeConverter.convert(value) else null
            else -> throw InvalidParameterException("Invalid id property ID($id)")
        }
    }

    override fun getPropertyById(id: Byte): Any? {
        return when (id) {
            EMainPanel.AMP.propertyId -> amp
            EMainPanel.GAIN.propertyId -> gain
            EMainPanel.MASTER.propertyId -> master
            EMainPanel.BASS.propertyId -> bass
            EMainPanel.MIDDLE.propertyId -> middle
            EMainPanel.TREBLE.propertyId -> treble
            EMainPanel.CABINET.propertyId -> cabinet
            else -> null
        }
    }

    companion object {
        fun fromDump(dump: ByteArray): MainPanel {

            val amp = EAmpType.fromId(dump[EMainPanel.AMP.dumpPosition])!!
            var cab: ECabinetType? = null
            if (amp.id < EAmpType.BASS.id) {
                cab = ECabinetType.fromId(dump[EMainPanel.CABINET.dumpPosition])
            }

            return MainPanel(
                amp,
                dump[EMainPanel.GAIN.dumpPosition],
                dump[EMainPanel.MASTER.dumpPosition],
                dump[EMainPanel.BASS.dumpPosition],
                dump[EMainPanel.MIDDLE.dumpPosition],
                dump[EMainPanel.TREBLE.dumpPosition],
                cab
            )
        }
    }
}