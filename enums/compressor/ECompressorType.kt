package cz.fjerabek.thr.data.enums.compressor

/**
 * Compressor type
 * @param id type ID
 */
enum class ECompressorType (val id : Byte) {
    STOMP(0x00),
    RACK(0x01);

    companion object {
        private val map = values().associateBy(ECompressorType::id)

        /**
         * Returns instance by ID
         * @return instance
         */
        fun fromId(id: Byte) = map[id]
    }
}