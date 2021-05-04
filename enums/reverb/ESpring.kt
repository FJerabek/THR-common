package cz.fjerabek.thr.data.enums.reverb

import cz.fjerabek.thr.data.enums.IControlProperty

/**
 * Spring properties
 * @param propertyId ID of property
 * @param max maximal property value
 * @param min minimal property value
 * @param dumpPosition position in MIDI dump array
 */
enum class ESpring(override val propertyId : Byte, val max : Byte, val min : Byte, val dumpPosition : Int) : IControlProperty {
    REVERB(0x41, 0x64, 0x00, 210),
    FILTER(0x42, 0x64, 0x00, 211);


    override fun getMaximumValue(): Int {
        return this.max.toInt()
    }

    override fun getMinimumValue(): Int {
        return this.min.toInt()
    }
}