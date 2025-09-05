fun main() {
    //Class & Object
    val student1 = Student("Hy", 20)
    student1.displayInfo()
    StudentRepo.addStudent(student1)
    println(StudentRepo.getAllStudents())

    //Constructor
    val car = Car("Toyota", "Camry")
    car.displayInfo()
    val bike = Bike("Wave", "Alpha")
    val square = Rectangle(5.0)
    println(square.area())

    //Inheritance
    val employee = Employee("Huy", 20, "Intern Android Dev")
    employee.introduce()
    employee.birthday()

    //Interface & Abstract class
    val teacher = Teacher("Toi", 40, "Math")
    println(teacher.getInfo())
    val cat = Cat()
    cat.sound()
    cat.sleep()

    //Class Types
    val room1 = Room(101, 2)
    val room2 = Room(101, 2)
    println(room1)
    println("Equal? ${room1 == room2}")
    println("Room hashCode: ${room1.hashCode()}")
    println("Day: ${Day.MONDAY}")
    val ketQua1: KetQua = KetQua.ThanhCong("Thành công!")
    val ketQua2: KetQua = KetQua.ThatBai("Thất bại!")
    val ketQua3: KetQua = KetQua.KhongXacDinh
    ketQua1.inRa()
    ketQua2.inRa()
    ketQua3.inRa()
    val nested = Outer.Nested()
    nested.nestedFunction()
    val inner = Outer().Inner()
    inner.innerFunction()
    val school = School()
    school.helloStudent("Charlie")

    //Singleton & Companion Object
    println(Department.getDepartmentInfo())
    val university = University.create("HUST")
    println("University: ${university.name}")
}
/* Class & Object */
//Class
class Student(val name: String, var age: Int) {
    fun displayInfo() {
        println("Name: $name, Age: $age")
    }
}
//Object
object StudentRepo {
    private val students = mutableListOf<Student>()
    fun addStudent(student: Student) {
        students.add(student)
    }
    fun getAllStudents(): List<Student> {
        return students
    }
}

/* Constructor */
//Primary Constructor with Default Parameter
class Car(val make: String, val model: String) {
    fun displayInfo() {
        println("$make $model")
    }
}
//Primary Constructor with Initializer Block
class Bike(val brand: String, val type: String) {
    init {
        println("$brand $type")
    }
}
//Secondary Constructor
class Rectangle(val width: Double, val height: Double) {
    constructor(side: Double) : this(side, side)
    fun area(): Double {
        return width * height
    }
}

/* Inheritance */
open class Person(var name: String, var age: Int) {
    open val id: Int = 0
    open fun introduce() {
        println("Hi, I'm $name and I'm $age years old.")
    }
    open fun birthday() {
        age++
        println("Happy Birthday $name! You are now $age.")
    }
}
class Employee(name: String, age: Int, var position: String) : Person(name, age) {
    override var id: Int = 1
    //Ghi đè một phần
    override fun introduce() {
        super.introduce()
        println("I work as a $position.")
    }
    //Ghi đè hoàn toàn
    override fun birthday() {
        println("Congratulations on your new role as $position!")
    }
}

/* Interface & Abstract class */
//Interface
interface PersonInfo {
    val id: Int
    fun getInfo(): String
}
class Teacher(var name: String, var age: Int, var subject: String) : PersonInfo {
    override val id: Int = 2
    override fun getInfo(): String {
        return "Teacher ID: $id, Name: $name, Age: $age, Subject: $subject"
    }
}
//Abstract class
abstract class Animal {
    abstract fun sound()
    fun sleep() {
        println("Zzz")
    }
}
class Cat : Animal() {
    override fun sound() {
        println("Meow meow!")
    }
}

/* Class Types */
// Data Class (lưu trữ dữ liệu)
data class Room(val roomNumber: Int, val capacity: Int){
    //Tự động override toString(), equals(), hashCode() hoặc có thể tự override lại
    override fun toString(): String {
        return "Room(roomNumber=$roomNumber, capacity=$capacity)"
    }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Room) return false
        return roomNumber == other.roomNumber && capacity == other.capacity
    }
    override fun hashCode(): Int {
        var result = roomNumber
        result = 31 * result + capacity
        return result
    }
}
// Enum Class (là tập hợp các hằng số)
enum class Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
// Sealed Class (không thể có lớp con bên ngoài file khai báo)
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
// Nested & Inner Class
class Outer {
    private val outerField = "Outer"
    class Nested { // Là class tĩnh không thể truy cập thành phần của lớp ngoài
        fun nestedFunction() {
            println("Nested")
        }
    }
    inner class Inner { // Là class động có thể truy cập thành phần của lớp ngoài
        fun innerFunction() {
            println("Inner")
            println(outerField)
        }
    }
}
// Local Class
class School {
    fun helloStudent(name: String) {
        class Student(val name: String) {
            fun display() {
                println("Hello Student: $name")
            }
        }
        val student = Student(name)
        student.display()
    }
}

/* Singleton & Companion Object */
//Singleton (chỉ có một instance duy nhất)
object Department {
    val name = "Computer Science"
    fun getDepartmentInfo(): String {
        return "Department: $name"
    }
}
//Companion Object (giống static trong Java)
class University(val name: String) {
    companion object {
        fun create(name: String): University {
            return University(name)
        }
    }
}




