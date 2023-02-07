# Kotlin-practice

## Kotlin for server side

- Expressiveness.
- Scalability.
- Interoperability.
- Migration.
- Tooling.
- Learning Curve.

## Kotlin for Android

- Less code combined with greater readability.
- Mature language and environment.
- Kotlin support in Android Jetpack and other libraries.
- Interoperability with Java.
- Support for multiplatform development.
- Code safety.
- Easy learning.
- Big community.

## 패키지 정의

- Package specification should be at the top of the source file
```kotlin
package my.demo

import kotlin.text.*
```

## Program entry point

```kotlin
fun main() {
    println("Hello world!")
}

fun main(args: Array<String>) {
    println(args.contentToString())
}
```

## 함수 정의

- `fun` 키워드로 정의
    ```kotlin
    fun sum(a: Int, b: Int): Int { return a + b }
    ```
- return type 추론 가능
    ```kotlin
    fun sum(a: Int, b: Int) = a + b
    ```
- 리턴 값이 없는 경우 Unit(Object)로 리턴 ( void )
    ```kotlin
    fun printKotlin(): Unit { println("Hello Kotlin")}
    ```
- Unit은 생략 가능
    ```kotlin
    fun printKotlin() {
        println("Hello Kotlin")
    }
    ```

## 변수 정의

- `val`: 읽기 전용 변수
    - 값의 할당이 1회만 가능, 자바의 `final`과 유사
    ```kotlin
    val a: Int = 1
    val b = 2 // int type 추론
    val c: Int // 컴파일 오류, 초기화 필요
    c = 3 // 컴파일 오류, 읽기 전용이라 추후에 할당 불가
    ```
- `var`: Mutable 변수
    ```kotlin
    var x = 5
    x += 1
    ```

## 문자열 템플릿

- String Interpolation
    ```kotlin
    var a = 1
    val s1 = "a is $a"

    a = 2
    val s2 = "${s1.replace('is', 'was')}, but now is $a"

## 조건문

```kotlin
fun maxOf(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}
```

- 조건식으로 사용 가능
    ```kotlin
    fun maxOf(a: Int, b: Int) = if (a>b) a else b
    ```

## nullable

- 값이 null일 수 있는 경우 타입에 nullable 마크를 명시
    ```kotlin
    fun parseInt(str: String): Int? {
        // 정수가 아니면 null 리턴
    }
- nullable 타입의 변수를 접근할 땐 반드시 null 체크를 해야 함
    ```kotlin
    fun printProduct(arg1: String, arg2: String) {
        val x: Int? = parseInt(arg1)
        val y: Int? = parseInt(arg2)

        if (x != null && y != null) {
            println(x*y)
        } else {
            println("either '$arg1' or '$arg2' is not a number")
        }
    }

## 자동 타입 변환

- 타입 체크만 해도 자동으로 타입 변환
    - `obj`: Any는 object의 최상위 객체
    ```kotlin
    fun getStringLength(obj: Any): Int? {
        if (obj is String) {
            // obj가 자동으로 string타입으로 변환
            return obj.length
        }
        return null
    }

## while loop

- jumps
    - return, break, continue
    ```kotlin
    val s = person.name ?: return
    ```
- labels
    ```kotlin
    loop@ for (i in 1..100) {
        for (j in 1..100) {
            if (...) break@loop
        }
    }
    ```



```kotlin
val items = listOf("apple", "banana", "kiwi")
var index = 0
whils (index < items.size) {
    println("item at $index is ${items[index]}")
    index ++
}
```

## when expression

```kotlin
fun describe(obj: Any): String =
    when (obj) {
        1 -> "One"
        "Hello" -> "Greeting"
        is Long -> "Long"
        !is String -> "Not a string"
        else -> "Unknown"
    }
```

## ranges

- `in` 연산자를 이용해 숫자 범위 체크 가능
    ```kotlin
    val x = 3
    if (x in 1..10) {
        println("fits in range")
    }
    ```

- range를 이용한 for loop
    ```kotlin
    for (x in 1..5) {
        println(x)
    }
    ```

## collections

- 컬렉션도 in 으로 loop 가능
    ```kotlin
    val items = listOf("apple", "banana", "kiwi")
    for (item in items) {
        println(item)
    }
    ```

- in으로 해당 값이 collection에 포함되는지 체크 가능
    ```kotlin
    val items = setOf("apple", "banana", "kiwi")
    when {
        "orange" in items -> println("juicy")
        "apple" in items -> println("apple is fine too")
    }
    ```

- 람다식을 이용해 컬렉션에 filter, map 연산 가능
    ```kotlin
    val fruits = listOf("banana", "avocado", "apple", "kiwi")
    fruits
        .filter { it.startsWith("a") }
        .sortedBy { it }
        .map { it.toUpperCase() }
        .forEach { println(it) }
    ```

# Basic Types

## 기본 타입

- 코틀린은 모두 객체
- 모든 것에 멤버 함수나 프로퍼티를 호출 가능

## 숫자

- Java의 숫자와 비슷
- Kotlin에서 Number는 클래스
- Java에서 숫자형이던 char가 kotlin에선 숫자형이 아님
- Literal
    - 10진수 (Int, Short) (123)
    - Long (123L)
    - Double (123.5, 123.5e10)
    - Float (123.5f, 123.5F)
    - 2진수 (0b00001011)
    - 16진수(0x0F)
    - 8진수는 미지원
- Byte(8), Short(16), Int(32), Long(64)
- Float(32), Double(64)
- Underscore in numeric literals
    ```kotlin
    val oneMillion = 1_000_000
    val creditCartNumber = 1234_5678_9012_3456L
    ```
- Unsigned
    - UByte, UShort, UInt, ULong

## Representation

- Java 플랫폼에서 숫자형은 JVM primitive type으로 저장
- Nullable이나 제네릭인 경우엔 박싱
- 박싱된 경우엔 identity를 유지하지 않음
- show bytecode -> decompile하면 자바 코드로 변환

## Explicit Conversions

- 작은 타입은 큰 타입의 하위 타입이 아님
- 즉, 작은 타입에서 큰 타입으로 대입이 안됨
    - 숫자끼리 변화할 땐 명시적으로 변환해야 함
        ```kotlin
        val i: Int = b.toInt() // toByte(), toShort(), toInt(), ...
        ```

## 문자

- char는 숫자로 취급되지 않음
    ```kotlin
    fun check(c: Char) { if (c == 'a') }
    ```
- `\t` - tab
- `\b` - backspace
- `\n` - newline
- `\r` = carriage return
- `\'`, `\"`, `\\`, `\$`, `\uFF00`

## 배열

- 배열은 Array 클래스로 표현
- `get`, `set` ([] 연산자 오버로딩됨)
- size 등 유용한 멤버 함수 포함
    ```kotlin
    var array: Array<String> = arrayOf("코틀린", "강좌")
    println(array.get(0))
    println(array[0])
    println(array.size)
    ```

- 배열 생성
    - Array의 팩토리 함수 사용
    - `arrayOf()` 등의 라이브러리 함수 이용
        ```kotlin
        val b = Array(5, { i -> i.toString() })

        val a = arrayOf("0", "1", "2", "3", "4")
        ```

- 특별한 Array 클래스
    - primitive 타입의 박싱 오버헤드를 없애기 위한 배열
    - `IntArray`, `ShortArray`
    - Array를 상속한 클래스는 아니지만 Array와 같은 메소드, 프로퍼티를 가짐
    - size 등 유용한 멤버 함수 포함
        ```kotlin
        val x: IntArray = intArrayOf(1, 2, 3)
        x[0] = 7
        println(x.get(0))
        println(x[0])
        println(x.size)
        ```

## 문자열

- 문자열은 String 클래스로 구현
- String은 character로 구성
- `s[i]` 같은 방식으로 접근 가능
- immutable이므로 변경은 불가
```kotlin
var x: String = "Kotlin"
println(x.get(0))
println(x.length)
```

- concatenate strings
```kotlin
val s = "abc" + 1
println(s + "def")
```

- 문자열 리터럴
    - escape string ("Kotlin")
        - 전통적인 방식으로 Java String과 비슷
        - Backslash를 사용해 escaping 처리

    - raw string ("Kotlin")
        - escaping 처리 필요 없음
        - 개행이나 어떤 문자 포함 가능
    ```kotlin
    val s = "Hello, world₩n"

    val text = """
    |Tell me and I forget.
    |Teach me and I remember.
    |Involve me and I learn.
    |(Benjamin Franklin)
    """.trimMargin()
    ```

## Bitwise Operations

- can be applied only to `Int` and `Long`
```kotlin
val x = (1 shl 2) and 0x000FF000
```

- shl(bits) - signed shift left
- shr(bits) - signed shift right
- ushr(bits) - unsigned shift rignt
- and(bits) - bitwise `AND`
- or(bits) - bitwise `OR`
- xor(bits) - bitwise `XOR`
- inv() - bitwise inversion
```
