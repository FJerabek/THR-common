package cz.fjerabek.thr.data.enums

/**
 * Represents power status
 * @param value value in MIDI dump
 */
enum class EStatus (val value : Byte){
    ON(0x00),
    OFF(0x7F);

    companion object {
        private val map = values().associateBy(EStatus::value)

        /**
         * Creates instance from value
         */
        fun fromValue(value: Byte) = map[value]
    }
}