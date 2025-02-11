package cz.fjerabek.thr.data.enums.compressor

import cz.fjerabek.thr.data.enums.IControlProperty

/**
 * Rack properties
 * @param propertyId ID of property
 * @param max maximal property value
 * @param min minimal property value
 * @param dumpPosition position in MIDI dump array
 */
enum class ERack(override val propertyId: Byte, val max : Int, val min : Byte, val dumpPosition: Pair<Int, Int>) : IControlProperty {
    THRESHOLD(0x11, 0x258, 0x00, Pair(162, 163)),
    ATTACK(0x13, 0x64, 0x00, Pair(164, -1)),
    RELEASE(0x14, 0x64, 0x00, Pair(165, -1)),
    RATIO(0x15, 0x05, 0x00, Pair(166, -1)),
    KNEE(0x16, 0x02, 0x00, Pair(167, -1)),
    OUTPUT(0x17, 0x258, 0x00, Pair(168, 169));

    override fun getMaximumValue(): Int {
        return this.max
    }

    override fun getMinimumValue(): Int {
        return this.min.toInt()
    }
}