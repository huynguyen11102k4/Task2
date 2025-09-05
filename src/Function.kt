fun main() {
    // Function
    println("Sum: ${sum(2, 3)}")

    // Default Parameter
    println("Subtract: ${subtract(5)}")
    println("Subtract1: ${subtract1(b = 3)}")

    // Named Argument
    println("Divide: ${divide(b = 10, a = 50)}")

    // Vararg Parameter
    println("Multiply: ${multiply(1, 2, 3, 4)}")

    // Extension Function
    val numbers = listOf(1, 2, 3, 4, 5)
    println("Sum List: ${numbers.sumList()}")

    // Infix Function
    println("Power: ${2 power 3}")

    // Tail Recursion
    println("Factorial: ${factorial(5)}")

    // Local Function
    println("Fibonacci: ${fibonacci(6)}")

    // Lambda Expression
    demoLamda()

    // Higher-Order Function
    val result = demoHigherOrderFunc(10, 5) { x, y -> x - y }
    println("Higher-Order Function Result: $result")

    // Inline Function
    val inlineResult = demoInlineFunc(10, 5) { x, y -> x + y }
    println("Inline Function Result: $inlineResult")

    // Generic Function
    val intList = toList(1, 2, 3)
    printList(intList)
    val strList = toList("a", "b", "c")
    printList(strList)

    // Scope Function
    demoScopeFunc()
}

/* Parameter */
fun sum(a: Int, b: Int): Int {
    return a + b
}

/* Default Parameter */
//Tham số có default đứng sau tham số không default
fun subtract(a: Int, b: Int = 0): Int {
    return a - b
}
//Tham số có default đứng trước tham số không default
fun subtract1(a: Int = 2, b: Int): Int {
    return a - b
}
//Không được ghi đè lại tham số có default khi override
open class A {
    open fun add(a: Int = 1, b: Int = 2): Int {
        return a + b
    }
}
class B : A() {
    override fun add(a: Int, b: Int): Int {
        return a + b
    }
}

/* Named Argument */
fun divide(a: Int, b: Int): Int {
    return a / b
}

/* Vararg Parameter */
fun multiply(vararg nums: Int): Int {
    var res = 1
    for (number in nums) {
        res *= number
    }
    return res
}

/* Extension Function (hàm mở rộng) */
fun List<Int>.sumList(): Int {
    var sum = 0
    for(i in this) {
        sum += i
    }
    return sum
}

/* Infix Function (là member hoặc extension function nhưng chỉ có 1 tham số và không cần dấu . để gọi hàm)*/
infix fun Int.power(exp: Int): Int {
    var res = 1
    for (i in 1..exp) {
        res *= this
    }
    return res
}

/* Tail Recursion (đệ quy) an toàn với n lớn không bị StackOverflowError*/
tailrec fun factorial(n: Int, acc: Int = 1): Int {
    return if (n <= 1) acc else factorial(n - 1, n * acc)
}

/* Local Function (hàm trong hàm) */
fun fibonacci(n: Int): Int {
    fun fib(n: Int, a: Int, b: Int): Int {
        return if (n == 0) a else fib(n - 1, b, a + b)
    }
    return fib(n, 0, 1)
}

/* Lambda Expression (hàm vô danh) */
fun demoLamda() {
    val add = {x: Int, y: Int -> x+y}
    println(add(2, 3))
    val multiply = {x: Int, y: Int -> x * y}
    println(multiply(2, 3))
}

/* Higher-Order Function (nhận hàm khác làm tham số) */
fun demoHigherOrderFunc(a: Int, b: Int, subtract: (Int, Int) -> Int): Int {
    return subtract(a, b)
}

/* Inline Function (tăng hiệu suất compiler sẽ chèn thẳng code của lambda vào chỗ gọi mà không phải tạo object function) */
inline fun demoInlineFunc(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}

/* Generic Function (là hàm có tham số kiểu(T) giúp làm việc với nhiều kiểu dữ liệu khác nhau) */
fun <T> toList(vararg elements: T): List<T> {
    return elements.toList()
}
fun <T> printList(list: List<T>) {
    for (item in list) {
        println(item)
    }
}

/* Scope Function (hàm mở rộng phạm vi) */
data class PersonData(var name: String, var age: Int)
fun demoScopeFunc() {
    val person = Person("Bob", 25)

    // let (trả về giá trị của lambda, tác động lên it)
    person.let {
        println("Name: ${it.name}, Age: ${it.age}")
    }

    // run (trả về giá trị của lambda, tác động lên this khác với with ở chỗ run là hàm mở rộng)
    val info = person.run {
        "Name: $name, Age: $age"
    }
    println(info)

    // with (trả về giá trị của lambda, tác động lên this)
    val details = with(person) {
        "Name: $name, Age: $age"
    }
    println(details)

    // apply (trả về đối tượng ban đầu, tác động lên this)
    val updatedPerson = person.apply {
        age += 1
    }
    println("Updated Age: ${updatedPerson.age}")

    // also (trả về đối tượng ban đầu, tác động lên it)
    person.also {
        it.age += 1
        println("Also Updated Age: ${it.age}")
    }
}


