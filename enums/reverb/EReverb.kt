package cz.fjerabek.thr.data.enums.reverb

import cz.fjerabek.thr.data.enums.IControlProperty

/**
 * Reverb properties
 * @param propertyId ID of property
 * @param max maximal property value
 * @param min minimal property value
 * @param dumpPosition position in MIDI dump array
 */
enum class EReverb (override val propertyId: Byte, val max : Byte, val min : Byte, val dumpPosition: Int) : IControlProperty {
    STATUS(0x4F, 0x7F, 0x00, 224),
    TYPE(0x40, 0x03, 0x00, 209);

    override fun getMaximumValue(): Int {
        return this.max.toInt()
    }

    override fun getMinimumValue(): Int {
        return this.min.toInt()
    }
}