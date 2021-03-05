package cz.fjerabek.thr.data.controls

import cz.fjerabek.thr.data.enums.EStatus
import cz.fjerabek.thr.data.enums.compressor.ECompressorType
import cz.fjerabek.thr.data.enums.effect.EEffectType
import cz.fjerabek.thr.data.enums.mainpanel.EAmpType
import cz.fjerabek.thr.data.enums.mainpanel.ECabinetType
import cz.fjerabek.thr.data.enums.reverb.EReverbType

class UnsupportedType(message: String) : Exception(message)

object TypeConverter {
    inline fun <reified T> convert(value: Int): T {
        return when (T::class) {
            Byte::class -> {
                value.toByte() as T
            }
            Int::class -> {
                value as T
            }
            EStatus::class -> {
                EStatus.fromValue(value.toByte())!! as T
            }
            EReverbType::class -> {
                EReverbType.fromId(value.toByte())!! as T
            }
            EEffectType::class -> {
                EEffectType.fromId(value.toByte())!! as T
            }
            ECabinetType::class -> {
                ECabinetType.fromId(value.toByte())!! as T
            }
            EAmpType::class -> {
                EAmpType.fromId(value.toByte())!! as T
            }
            ECompressorType::class -> {
                ECompressorType.fromId(value.toByte())!! as T
            }
            else -> throw UnsupportedType("Unsupported type for conversion")
        }
    }

    @JvmStatic
    fun convert(value: Any): Int {
        return when (value) {
            is Byte -> value.toInt()
            is Int -> value
            is EStatus -> if (value.value == 0.toByte()) 1 else 0
            is EReverbType -> value.id.toInt()
            is EEffectType -> value.id.toInt()
            is ECabinetType -> value.id.toInt()
            is EAmpType -> value.id.toInt()
            is ECompressorType -> value.id.toInt()
            else -> throw UnsupportedType("Unsupported type for conversion")

        }
    }
}