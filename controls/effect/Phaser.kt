package cz.fjerabek.thr.data.controls.effect

import cz.fjerabek.thr.data.controls.TypeConverter
import cz.fjerabek.thr.data.enums.EStatus
import cz.fjerabek.thr.data.enums.effect.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.security.InvalidParameterException

@Serializable
@SerialName("Phaser")
class Phaser (
        override var status: EStatus,
        var speed : Byte,
        var manual : Byte,
        var depth : Byte,
        var feedback : Byte) : Effect(EEffectType.PHASER) {


    override fun setPropertyById(id: Byte, value: Int) {
        when (id) {
            EEffect.STATUS.propertyId -> status = TypeConverter.convert(value)
            EPhaser.SPEED.propertyId -> speed = TypeConverter.convert(value)
            EPhaser.DEPTH.propertyId -> depth = TypeConverter.convert(value)
            EPhaser.MANUAL.propertyId -> manual = TypeConverter.convert(value)
            EPhaser.FEEDBACK.propertyId -> feedback = TypeConverter.convert(value)
            else -> throw InvalidParameterException("Invalid id property ID($id)")
        }
    }

    override fun getPropertyById(id: Byte): Any? {
        return when(id){
            EEffect.STATUS.propertyId -> status
            EEffect.TYPE.propertyId -> EEffectType.PHASER
            EPhaser.SPEED.propertyId -> speed
            EPhaser.DEPTH.propertyId -> depth
            EPhaser.MANUAL.propertyId -> manual
            EPhaser.FEEDBACK.propertyId -> feedback
            else -> throw InvalidParameterException("Invalid id property ID($id)")
        }
    }

    constructor(dump: ByteArray): this(
            EStatus.fromValue(dump[EEffect.STATUS.dumpPosition])!!,
            dump[EPhaser.SPEED.dumpPosition],
            dump[EPhaser.MANUAL.dumpPosition],
            dump[EPhaser.DEPTH.dumpPosition],
            dump[EPhaser.FEEDBACK.dumpPosition]
    )

    override fun toDump(dump: ByteArray): ByteArray {
        dump[EPhaser.SPEED.dumpPosition] = speed
        dump[EPhaser.MANUAL.dumpPosition] = manual
        dump[EPhaser.DEPTH.dumpPosition] = depth
        dump[EPhaser.FEEDBACK.dumpPosition] = feedback
        return super.toDump(dump)
    }
}