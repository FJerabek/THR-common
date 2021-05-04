package cz.fjerabek.thr.data.uart

/**
 * Charging status
 */
enum class ECharging(val value : String) {
    DISCHARGING("dis"),
    CHARGING("chg"),
    FULL("end");

    companion object {
        private val map = values().associateBy(ECharging::value)

        /**
         * Creates value from string
         */
        fun fromValue(value : String) = map[value]
    }
}