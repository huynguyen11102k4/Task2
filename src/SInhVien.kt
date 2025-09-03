class SinhVien(
    override var ma: Int,
    override var ten: String,
    override var tuoi: Int? = null,
    val dsMonHoc: MutableSet<MonHoc> = mutableSetOf()
) : Nguoi(ma, ten, tuoi), DiemTB {

    constructor(ma: Int, ten: String) : this(ma, ten, null)

    override fun tinhDiemTB(): Double {
        if (dsMonHoc.isEmpty()) {
            return 0.0
        }
        val tongTinChi = dsMonHoc.sumOf { it.soTinChi }
        val tong = dsMonHoc.sumOf { it.diem * it.soTinChi }
        return (tong / tongTinChi).let { String.format("%.2f", it).toDouble() }
    }

    override fun xuatThongTin() {
        super.xuatThongTin()
        if (dsMonHoc.isEmpty()) {
            println("Môn học: (chưa có)")
        } else {
            println("Môn học:")
            dsMonHoc.forEachIndexed { i, mh ->
                println("  ${i + 1}. ${mh.ten}: ${mh.diem}")
            }
        }
        println("Điểm TB: ${tinhDiemTB()}")
        println("-----")
    }

    companion object {
        private var AUTO_ID = 1000
        fun getAutoId(): Int {
            return AUTO_ID++
        }
    }
}