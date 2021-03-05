package cz.fjerabek.thr.data.enums

class InvalidPropertyException(message: String): Exception(message)

interface IControlProperty {
    val propertyId: Byte
    fun getMinimumValue() : Int
    fun getMaximumValue() : Int
}