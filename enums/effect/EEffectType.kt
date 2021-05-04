package cz.fjerabek.thr.data.enums.effect

/**
 * Effect types
 * @param id effect type ID
 */
enum class EEffectType(val id : Byte) {
    CHORUS(0x00),
    FLANGER(0x01),
    TREMOLO(0x02),
    PHASER(0x03);

    companion object {
        private val map = values().associateBy(EEffectType::id)

        /**
         * Gets instance by ID
         * @return instance
         */
        fun fromId(id: Byte) = map[id]
    }
}