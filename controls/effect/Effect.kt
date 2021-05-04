package cz.fjerabek.thr.data.controls.effect

import cz.fjerabek.thr.data.controls.IControl
import cz.fjerabek.thr.data.enums.EStatus
import cz.fjerabek.thr.data.enums.effect.EEffect
import cz.fjerabek.thr.data.enums.effect.EEffectType
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Effect settings block
 * @param effectType type of effect
 */
@Serializable
abstract class Effect(
    //Default value is useless since it is specified by inheriting class
    @Transient val effectType: EEffectType = EEffectType.CHORUS
) : IControl {

    /**
     * Power status of settings block
     */
    abstract val status: EStatus

    /**
     * Duplicates this object
     * @return duplicated instance
     */
    abstract fun duplicate(): Effect

    /**
     * Sets correct values in dump array
     * @param dump dump array
     * @return modified dump array
     */
    override fun toDump(dump: ByteArray): ByteArray {
        dump[EEffect.STATUS.dumpPosition] = status.value
        dump[EEffect.TYPE.dumpPosition] = effectType.id
        return dump
    }


    companion object {

        /**
         * Creates new Effect instance from MIDI dump data
         * @param dump dump data
         * @return Effect instance with correct type
         */
        fun fromDump(dump: ByteArray): Effect {

            return when (EEffectType.fromId(dump[EEffect.TYPE.dumpPosition])!!) {
                EEffectType.CHORUS -> Chorus(dump)
                EEffectType.FLANGER -> Flanger(dump)
                EEffectType.TREMOLO -> Tremolo(dump)
                EEffectType.PHASER -> Phaser(dump)
            }
        }
    }
}