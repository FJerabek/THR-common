package cz.fjerabek.thr.data.controls

interface IControl {
    fun toDump(dump : ByteArray) : ByteArray
    fun getPropertyById(id: Byte): Any?
    fun setPropertyById(id: Byte, value: Int)

}

