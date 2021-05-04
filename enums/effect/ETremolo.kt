package cz.fjerabek.thr.data.enums.effect

import cz.fjerabek.thr.data.enums.IControlProperty

/**
 * Tremolo properties
 * @param propertyId ID of property
 * @param max maximal property value
 * @param min minimal property value
 * @param dumpPosition position in MIDI dump array
 */
enum class ETremolo(override val propertyId: Byte, val max : Byte, val min : Byte, val dumpPosition : Int) : IControlProperty {
    FREQ(0x21, 0x64, 0x00, 178),
    DEPTH(0x22, 0x64, 0x00, 179);

    override fun getMaximumValue(): Int {
        return this.max.toInt()
    }

    override fun getMinimumValue(): Int {
        return this.min.toInt()
    }
}