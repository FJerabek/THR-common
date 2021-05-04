package cz.fjerabek.thr.data.enums.reverb


/**
 * Reverb types
 * @param id id of reverb type
 */
enum class EReverbType(val id : Byte) {
    HALL(0x00),
    ROOM(0x01),
    PLATE(0x02),
    SPRING(0x03);

    companion object {
        private val map = values().associateBy(EReverbType::id)

        /**
         * Returns instance by value
         * @return instance
         */
        fun fromId(id: Byte) = map[id]
    }

}