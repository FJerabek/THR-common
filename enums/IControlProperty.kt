package cz.fjerabek.thr.data.enums

interface IControlProperty {
    val propertyId: Byte
    fun getMinimumValue() : Int
    fun getMaximumValue() : Int
}