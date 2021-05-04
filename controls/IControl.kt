package cz.fjerabek.thr.data.controls

/**
 * Interface for settings blocks
 */
interface IControl {
    /**
     * Converts setting block to midi dump data
     * @param dump current dump array
     * @return modified dump array with values from setting block
     */
    fun toDump(dump : ByteArray) : ByteArray

    /**
     * Gets property value by its ID
     * @param id property ID
     */
    fun getPropertyById(id: Byte): Any?

    /**
     * Sets property value by its ID
     */
    fun setPropertyById(id: Byte, value: Int)

}

