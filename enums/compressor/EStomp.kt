package cz.fjerabek.thr.data.enums.compressor

import cz.fjerabek.thr.data.enums.IControlProperty

/**
 * Stomp properties
 * @param propertyId ID of property
 * @param max maximal property value
 * @param min minimal property value
 * @param dumpPosition position in MIDI dump array
 */
enum class EStomp(override val propertyId: Byte, val  max : Byte, val min : Byte, val dumpPosition : Int) : IControlProperty {
    SUSTAIN(0x11, 0x64, 0x00, 162),
    OUTPUT(0x12, 0x64, 0x00, 163);

    override fun getMaximumValue(): Int {
        return this.max.toInt()
    }

    override fun getMinimumValue(): Int {
        return this.min.toInt()
    }
}