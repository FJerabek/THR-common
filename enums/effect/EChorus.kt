package cz.fjerabek.thr.data.enums.effect

import cz.fjerabek.thr.data.enums.IControlProperty

enum class EChorus(override val propertyId: Byte, val max : Byte, val min : Byte, val dumpPosition : Int) : IControlProperty {
    SPEED(0x21, 0x64, 0x00, 178),
    DEPTH(0x22, 0x64, 0x00, 179),
    MIX(0x23, 0x64, 0x00, 180);

    override fun getMaximumValue(): Int {
        return this.max.toInt()
    }

    override fun getMinimumValue(): Int {
        return this.min.toInt()
    }
}