sealed class KetQua {
    data class ThanhCong(val giaTri: String) : KetQua()
    data class ThatBai(val loiNhan: String) : KetQua()
    object KhongXacDinh : KetQua()
}

fun KetQua.inRa() {
    when (this) {
        is KetQua.ThanhCong -> println(giaTri)
        is KetQua.ThatBai -> println(loiNhan)
        KetQua.KhongXacDinh -> println("Kết quả không xác định")
    }
}