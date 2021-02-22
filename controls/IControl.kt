package cz.fjerabek.thr.data.controls

import cz.fjerabek.thr.data.enums.EStatus
import cz.fjerabek.thr.data.enums.compressor.ECompressorType
import cz.fjerabek.thr.data.enums.effect.EEffectType
import cz.fjerabek.thr.data.enums.mainpanel.EAmpType
import cz.fjerabek.thr.data.enums.mainpanel.ECabinetType
import cz.fjerabek.thr.data.enums.reverb.EReverbType
import java.security.InvalidParameterException

interface IControl {
    fun toDump(dump : ByteArray) : ByteArray
    fun getPropertyById(id: Byte): Any?
    fun setPropertyById(id: Byte, value: Int)

}

