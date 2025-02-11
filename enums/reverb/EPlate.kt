package cz.fjerabek.thr.data.enums.reverb

import cz.fjerabek.thr.data.enums.IControlProperty


/**
 * Plate properties
 * @param propertyId ID of property
 * @param max maximal property value
 * @param min minimal property value
 * @param dumpPosition position in MIDI dump array
 */
enum class EPlate(override val propertyId: Byte, val max : Short, val  min : Short, val dumpPosition: Pair<Int, Int>) :
    IControlProperty {
    TIME(0x41, 0xC8, 0x03, Pair(210, 211)),
    PRE_DELAY(0x43, 2000,  1, Pair(212, 213)),
    LOW_CUT(0x45, 8000,  21, Pair(214, 215)),
    HIGH_CUT(0x47, 16001,  1000, Pair(216, 217)),
    HIGH_RATIO(0x49, 10,  1, Pair(218, -1)),
    LOW_RATIO(0x4A, 14,  1, Pair(219, -1)),
    LEVEL(0x4B, 0x64,  0x00, Pair(220, -1));

    override fun getMaximumValue(): Int {
        return this.max.toInt()
    }

    override fun getMinimumValue(): Int {
        return this.min.toInt()
    }
}