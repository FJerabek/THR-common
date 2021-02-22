package cz.fjerabek.thr.data.enums.effect

import cz.fjerabek.thr.data.enums.IControlProperty

enum class EEffect(override val propertyId: Byte, val max : Byte, val min : Byte, val dumpPosition : Int) : IControlProperty {
    STATUS(0x2F, 0x7F, 0x00, 192),
    TYPE(0x20, 0x03, 0x00, 177);

    override fun getMaximumValue(): Int {
        return this.max.toInt()
    }

    override fun getMinimumValue(): Int {
        return this.min.toInt()
    }
}