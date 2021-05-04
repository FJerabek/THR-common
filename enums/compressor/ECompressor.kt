package cz.fjerabek.thr.data.enums.compressor

import cz.fjerabek.thr.data.enums.IControlProperty

/**
 * Compressor properties
 * @param propertyId ID of property
 * @param max maximal property value
 * @param min minimal property value
 * @param dumpPosition position in MIDI dump array
 */
enum class ECompressor(override val propertyId: Byte, val max : Byte, val min : Byte, val dumpPosition : Int) : IControlProperty {
    STATUS(0x1F, 0x7F, 0x00, 176),
    TYPE(0x10, 0x01,0x00, 161);

    override fun getMaximumValue(): Int {
        return this.max.toInt()
    }

    override fun getMinimumValue(): Int {
        return this.min.toInt()
    }
}