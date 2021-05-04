package cz.fjerabek.thr.data.enums.mainpanel

/**
 * Cabinet types
 * @param id type ID
 */
enum class ECabinetType(val id : Byte) {
    AMERICAN_4X12(0x00),
    AMERICAN_2X12(0x01),
    BRITISH_4X12(0x02),
    BRITISH_2X12(0x03),
    CAB_1X12(0x04),
    CAB_4X10(0x05);

    companion object {
        private val map = values().associateBy(ECabinetType::id)

        /**
         * Gets instance by id
         * @return instance
         */
        fun fromId(id: Byte) = map[id]
    }
}