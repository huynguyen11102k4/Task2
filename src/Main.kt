fun main() {
    println("Quan ly sinh vien")
    for (lc in LuaChon.values()) {
        println("${lc.giaTri}. ${lc.name.replace('_', ' ')}")
    }
    while (true) {
        print("Nhap lua chon: ")
        val lc = readInt("")
        val kq: KetQua = when (lc) {
            1 -> themSV()
            2 -> inThongTinSV()
            3 -> timKiemSV()
            4 -> xoaSV()
            5 -> inTop10()
            else -> {
                println("Thoat chuong trinh")
                break
            }
        }
        kq.inRa()
    }
}

fun readInt(p: String): Int {
    while (true) {
        print(p)
        val input = readLine()
        try {
            if (input != null && input.toIntOrNull() != null) {
                return input.toInt()
            }
        } catch (e: NumberFormatException) {
            println("Vui long nhap so nguyen hop le.")
        }
    }
}

fun List<SinhVien>.top10(): List<SinhVien> = this.sortedByDescending { it.tinhDiemTB() }.take(10)

fun themSV(): KetQua {
    print("Nhap ma sinh vien: ")
    val maSV = readLine()?.toInt() ?: SinhVien.getAutoId()
    print("Nhap ten sinh vien: ")
    val ten = readLine()!!
    print("Nhap tuoi: ")
    val tuoi = readLine()!!.toInt()
    print("Nhap so mon hoc: ")
    val soMonHoc = readLine()!!.toInt()
    val dsMonHoc = mutableSetOf<MonHoc>()
    repeat(soMonHoc) {
        println("Nhap mon hoc thu ${it + 1}")
        print("Nhap ma mon hoc: ")
        val maMonHoc = readLine()!!.toInt()
        print("Nhap ten mon hoc: ")
        val tenMonHoc = readLine()!!
        print("Nhap diem mon hoc: ")
        val diemMonHoc = readLine()!!.toDouble()
        print("Nhap so tin chi mon hoc: ")
        val soTinChi = readLine()!!.toInt()
        val mh = MonHoc(maMonHoc, tenMonHoc, diemMonHoc, soTinChi)
        dsMonHoc.add(mh)
    }
    val sv = SinhVien(maSV, ten, tuoi, dsMonHoc)
    StudentRepo.themSV(sv)
    return KetQua.ThanhCong("Them sinh vien thanh cong")
}

fun inThongTinSV(): KetQua {
    StudentRepo.inDS()
    return KetQua.ThanhCong("In danh sach sinh vien thanh cong")
}

fun timKiemSV(): KetQua {
    println("Nhap ma sinh vien can tim:")
    val ma = readLine()!!.toInt()
    if (StudentRepo.timSV(ma) != null) {
        val sv = StudentRepo.timSV(ma)!!
        sv.xuatThongTin()
        return KetQua.ThanhCong("Tim sinh vien thanh cong sinh vien co ma $ma")
    } else {
        return KetQua.ThatBai("Khong tim thay sinh vien co ma $ma")
    }
}

fun xoaSV(): KetQua {
    println("Nhap ma sinh vien can xoa:")
    val ma = readLine()!!.toInt()
    if (StudentRepo.xoaSV(ma)) {
        return KetQua.ThanhCong("Xoa sinh vien thanh cong sinh vien co ma $ma")
    } else {
        return KetQua.ThatBai("Khong tim thay sinh vien co ma $ma")
    }
}

fun inTop10(): KetQua {
    val dsSV = StudentRepo.getAllSV()
    dsSV.top10().forEach { it.xuatThongTin() }
    return KetQua.ThanhCong("In top 10 sinh vien thanh cong")
}
