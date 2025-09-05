fun main() {
    //Nullable Type
    val a: Int? = null
    println(a)
    //Non-Nullable Type
    val b: Int = 10
    println(b)
    //Safe Call Operator
    println(b?.inc())
    //Elvis Operator
    println(a ?: "a is null")
    //Not-null Assertion Operator
    println(b!!)
    //Safe Cast Operator
    val obj: Any = "Kotlin"
    val str: String? = obj as? String
    println(str)
    //let function
    a?.let { println("a is not null: $it") } ?: println("a is null")
    //Collections with Nullability
    val list: List<String?> = listOf("Hello", null, "World")
}
