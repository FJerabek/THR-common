package cz.fjerabek.thr.data.enums

class InvalidPropertyException(message: String): Exception(message)

/**
 * Setttings property
 */
interface IControlProperty {
    /**
     * Property ID
     */
    val propertyId: Byte

    /**
     * Returns minimum property value
     * @return minimum value
     */
    fun getMinimumValue() : Int

    /**
     * Returns maximum property value
     * @return maximum value
     */
    fun getMaximumValue() : Int
}