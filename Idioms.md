# Idioms

## Create DTOs (POJOs/POCOs)

```kotlin
data class Customer(val name: String, val email:String)
```

provides a `Customer` class with the following functionality:
- getters(and setters in case of `var`s) for all properties
- `equals()`
- `hashCode()`
- `toString()`
- `copy()`
- `component1()`, `component2()`, ..., for all properties

## Default values for function parameters

```kotlin
fun foo(a: Int = 0, b: String = "") { ... }
```

## Filter a list

```kotlin
val positives = list.filter { x -> x > 0 }

val positives = list.filter { it > 0 }

## Check the presence of an element in a collection

```kotlin
if ("john@example.com" in emailsList) { ... }
if ("jane@example.com" !in emailsList) { ... }
```

## String interpolation

```kotlin
println("Name $name")
```

## Instance checks

```kotlin
when (x) {
    is Foo -> ...
    is Bar -> ...
    else   -> ...
}
```

## Read-only list

```kotlin
val list = listOf("a", "b", "c")
```

## Read-only map

```kotlin
val map = mapOf("a" to 1, "b" to 2, "c" to 3)
```

## Access a map entry

```kotlin
println(map["key"])
map["key"] = value
```

## Traverse a map or a list of pairs

```kotlin
for ((k, v) in map) {
    println("$k -> $v")
}
```

## Iterate over a range

```kotlin
for (i in 1..100) { ... } // closed range: includes 100
for (i in 1 until 100) { ... } // half-open range: does not include 100
for (x in 2..10 step 2) { ... }
for (x in 10 downTo 1) { ... }
(1..10).forEach { ... }
```

## Lazy property

```kotlin
val p: String by lazy {
    // the value is computed only on first access
    // compute the string
}
```

## Extension functions

```kotlin
fun String.spaceToCamelCase() { ... }
"Convert this to camelcase".spaceToCamelCase()
```

## Create a singleton

```kotlin
object Resource {
    val name = "Name"
}
```

## Instantiate an abstract class

```kotlin
abstract class MyAbstractClass {
    abstract fun doSomething()
    abstract fun sleep()
}

fun main() {
    val myObject = object: MyAbstractClass() {
        override fun doSomething() {
            // ...
        }

        override fun sleep() { // ...
        }
    }
    myObject.doSomething()
}
```

## If-not-null-else shorthand

```kotlin
val files = File("Test").listFiles()

println(files?.size ?: "empty")

val filesSize - files?.size ?: run {
    return someSize
}
println(fileSize)
```

## Execute a statement if null

```kotlin
val values = ...
val email = values["email"] ?: throw IllegalStateException("Email is missing!")
```

## Get first item of a possibly empty collection

```kotlin
val emails = ... // might be empty
val mainEmail - emails.firstOrNull() ?: ""
```

## Execute if not null

```kotlin
val value = ...

value?.let {
    ... // execute this block if not null
}
```

## Map nullable value if not null

```kotlin
val value = ...

val mapped = value?.let { transformValue(it) } ?: defaultValue
// defaultValue is returned if the value or the transform result is null
```

## Return on when statement

```kotlin
fun transform(color: String): Int {
    return when (color) {
        "Red" -> 0
        "Green" -> 1
        "Blue" -> 2
        else -> throw IllegalArgumentException("Invalid color param value")
    }
}
```

## try-catch expression

```kotlin
fun test() {
    val result = try {
        count()
    } catch (e:ArithmeticException) {
        throw IllegalStateException(e)
    }

    // Working with result
}
```

## if expression

```kotlin
val y = if (x == 1) {
    "one"
} else if (x == 2) {
    "two"
} else {
    "other"
}
```

## Builder-style usage of methods that return Unit

```kotlin
fun arrayOfMinusOnes(size: Int): IntArray {
    return IntArray(size).apply { fill(-1) }
}
```

## Single-expression functions

```kotlin
fun theAnswer() = 42

fun theAnswer(): Int {
    return 42
}
```

## Call multiple methods on an object instance (with)

```kotlin
class Turtle {
    fun penDown()
    fun penUp()
    fun turn(degrees: Double)
    fun forward(pixels: Double)
}

val myTurtle = Turtle()
with(myTurtle) { // draw a 100 pix square
    penDown()
    for (i in 1..4) {
        forward(100.0)
        turn(90.0)
    }
    penUp()
}
```

## Configure properties of an object (apply)

```kotlin
val myRectangle = Rectangle().apply {
    length = 4
    breadth = 5
    color = 0xFAFAFA
}
```

## Java7's try-with-resources

```kotlin
val stream = Files.newInputStream(Paths.get("/some/file.txt"))
stream.buffered().reader().use { reader ->
    println(reader.readText())
}
```

## Generic function that requires the generic type information

```kotlin
// public final class Gson {
//     ...
//     public <T> T fromJson(JsonElement json, Class<T> classOfT) throws JsonSyntaxException {
//     ...

inline fun <reified T: Any> Gson.fromJson(json: JsonElement): T = this.fromJson(json, T::class.java)
```

## Nullable Boolean

```kotlin
val b: Boolean? = ...
if (b == true) {
    ...
} else {
    // 'b' is false or null
}
```

## Swap two variables

```kotlin
var a = 1
var b = 2
a = b.also { b = a }
```
