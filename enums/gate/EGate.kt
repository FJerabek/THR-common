package cz.fjerabek.thr.data.enums.gate

import cz.fjerabek.thr.data.enums.IControlProperty

/**
 * Gate properties
 * @param propertyId ID of property
 * @param max maximal property value
 * @param min minimal property value
 * @param dumpPosition position in MIDI dump array
 */
enum class EGate(override val propertyId: Byte, val max : Byte, val min : Byte, val dumpPosition : Int) : IControlProperty {
    STATUS (0x5F, 0x7F, 0x00, 240),
    THRESHOLD(0x51, 0x64, 0x00, 226),
    RELEASE(0x52, 0x64, 0x00, 227);


    override fun getMaximumValue(): Int {
        return this.max.toInt()
    }

    override fun getMinimumValue(): Int {
        return this.min.toInt()
    }
}