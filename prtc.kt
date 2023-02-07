fun main() {
    println("Hello Kotlin!")
}

// #변수 선언
// val // 값이 변하지 않는 변수
// var // 값이 변경될 수 있는 변수

// var count: Int = 10 // Int, Byte, Short, Long, Float, Double
// count = 15

// 유형 추론
// val languageName = "Kotlin"
// val upperCaseName = languageName.toUpperCase()

// Null 안전

// val languageName: String? = null

// 조건부
// if-else
if (count == 42) {
    println("I have the answer.")
} else if (count > 35) {
    println("The answer is close.")
} else {
    println("The answer eludes me.")
}

//

val answerString: String = if (count == 42) { // 명확히 하기위해 유형선언을 포함하는 것이 좋음
    "I have the answer."
} else if (count > 35) {
    "The answer is close."
} else {
    "The answer eludes me."
}

println(answerString)

// 마지막 줄의 표현식 결과를 반환하므로 `return`을 사용할 필요가 없음

val answerString = when {
    count == 43 -> "I have the answer."
    count > 35 -> "The answer is close."
    else -> "The answer eludes me."
}

println(answerString)

// *스마트변환
// 안전 호출 연산자 또는 null이 아닌 어설션 연산자를 사용하여 nullable을 처리하는 대신
// 조건문을 사용하여 null값 참조가 있는지 확인 가능

val languageName: String? = null
if (languageName != null) {
    // No need to write languageName?.toUpperCase()
    println(languageName.toUpperCase())
}


// #함수
// 입력 Int, 출력 String
fun generateAnswerString(countThreshold: Int): String {
    val answerString = if (count > countThreshold) {
        "I have the answer."
    } else {
        "The answer eludes me."
    }

    return answerString
}

val answerString = generateAnswerString(42)

// 함수 선언 단순화

fun generateAnswerString(countThreshold: Int): String {
    return if (count > countThreshold) {
        "I have the answer."
    } else {
        "The answer eludes me."
    }
}

//

fun generateAnswerString(countThreshold: Int): String = if (count > countThreshold) {
        "I have the answer"
    } else {
        "The answer eludes me"
    }

// 익명 함수
val stringLengthFunc: (String) -> Int = { input ->
    input.length
}

val stringLength: Int = stringLengthFunc("Android")

// 고차 함수
fun stringMapper(str: String, mapper: (String) -> Int): Int {
    // Invoke function
    return mapper(str)
}

stringMapper("Android", { input ->
    input.length
})

// #클래스
class Car

// 속성
class Car {
    val wheels = ListOf<Wheel>()
}

val car = Car() // Construct a Car
val wheels = car.wheels // retrieve the wheels value from the Car

class Car(val wheels: List<Wheel>)

// 캡슐화

class Car(val wheels: List<Wheel>) {

    private val doolLock: DoorLock = ... // 클래스 외부의 모든 항목에서 비공개로 유지

    fun unlockDoor(key: Key): Boolean {
        // Return true if key is valid for door lock, false otherwise
    }
}

class Car(val wheels: List<Wheel>) {

    private val doorLock: DoorLock = ...

    var gallonsOfFuelInTank: Int = 15
        private set

    fun unlockDoor(key: Key): Boolean {
        // Return true ...
    }
}

// #상호운용성
// JVM 바이트 코드로 컴파일 되기 때문에 자바코드로 직접 호출될 수 있으며, 반대의 경우도 마찬가지로 기존 자바 라이브러리를
// Kotlin에서 직접 활용할 수 있음.





// #안드로이드에서 일반적인 코틀린 패턴 사용

// ## 프래그먼트로 작업하기

class LoginFragment : Fragment()
// LoginFragment는 Fragment의 서브

// 함수 재정의 override
// 수명 주기 콜백을 재정의하여 Fragment의 상태 변경에 응답
override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    return inflater.inflate(R.layout.login_fragment, container, false)
}

// 상위클래스애서 함수를 참조하려면 `super`
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
}

// null허용 여부 및 초기화
// view객체는 onCreateView를 호출하기 전까지는 확장 준비가 되지 않으므로 초기화를 연기해야함
class LoginFragment : Fragment() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var statusTextView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usernameEditText = view.findViewById(R.id.username_edit_text)
        passwordEditText = view.findViewById(R.id.password_edit_text)
        loginButton = view.findViewById(R.id.login_button)
        statusTextView = view.findViewById(r.id.status_text_view)
    }

    ...
// 속성이 초기화되기 전에 속성에 액세스하면 Exception을 발생시킴
}

// SAM 변환
// `onClickListener` 인터페이스를 구현하여 안드로이드에서 클릭 이벤트를 수신 대기할 수 있습니다
// Button객체에 포함되어있습니다
// `onClickListener`에는 반드시 구현해야 하는 단일 추상 메소드 `onClick()`이 있습니다. 이를 Single Abstract Method변환 SAM변환이라 합니다
// setOnClickListener 에 전달된 익명 함수 내의 코드는 사용자가 LoginButton을 클릭할 때 실행됩니다.
loginButton.setOnClickListener {
    val authSuccessful: Boolean = viewModel.authenticate(
        usernameEditText.text.toString(),
        passwordEditText.text.toString()
    )
    if (authSuccessful) {
        // Navigate to next screen
    } else {
        statusTextView.text = requireContext().getString(R.string.auth_failed)
    }
}

// 동반 객체
// 개념적으로 특정 유형에 연결되지만 특정 객체에 연결되지는 않는 변수나 함수를 정의하는 메커니즘을 제공합니다.
// static 키워드와 비슷합니다.
// TAG는 String 상수입니다. LoginFragment의 각 인스턴스에 String의 고유한 인스턴스가 필요하지 않으므로 동반 객체에서 정의
class LoginFragment : Fragment() {

    ...

    companion object {
        private const val TAG = "loginFragment"
    }
}

// 파일의 최상위 수준에서 정의할 수도 있지만, 파일 자체도 최상위 수준에서 정의된 변수, 함수, 클래스를 많이 갖고 있을 수 있습니다.

// 속성 위임
// 속성을 초기화할때 Fragment 내에서 ViewModel에 액세스하는 것과 같이 안드로이드의 더 일반적인 패턴을 반복할 수 있습니다.

private val viewModel: LoginViewModel by viewModels()

// 플랫폼 유형
// 코틀린을 사용하여 자바 클래스에서 정의된 주석 처리되지않은 멤버를 참조한다면 컴파일러는 코틀린에서 String이 String인지 String?인지 알지 못합니다. 이 모호성은 플랫폼 유형 String!을 통해 표시됩니다.
// String!은 String, String?을 표시할 수 있습니다. String으로 표시하고 null값을 할당하면 NullPointerException이 발생할 위험이 있습니다.
// 이 문제를 해결하려면 자바에서 null허용 여부 주석을 사용해야 합니다.
// 예시 자바에서 정의된 Account 클래스
public class Account implements Parcelable {
    public final String name;
    public final String type;
    private final @Nullable String accessId;
    ...
}

// accessId는 @Nullable로 주석 처리되어 있어서 null값을 보유할 수 있다는 것을 표시합니다.
// 코틀린은 String?으로 취급합니다. <-> @NonNull
public class Account implements Parcelable {
    public final @NonNull String name;
    ...
}

// null 허용 여부 처리
// 자바 유형을 잘 모르는 경우에는 null을 허용하는 것으로 간주해야 합니다.
// String?을 안전하게 자르난 방법 중 하나는 null이 아닌 어설션 연산자 `!!`을 사용하는 것
val account = Account("name", "type")
val accountName = account.name!!.trim()

//`!!`연산자는 왼쪽에 있는 모든 것을 null이 아닌 것으로 취급합니다. 왼쪽 표현식의 결과가 null이면 NullPointerException이 발생합니다.
// 더 안전한 방법은 ?.을 사용하는 것입니다.
val account = Account("name", "type")
val accountName = account.name?.trim()
// name이 null이라면 결과는 null입니다. exception이 발생하지는 않지만 다음 문에 null값이 전달됩니다.

// 대신 다음 예와 같이 Elvis 연산자 `?:`를 사용하여 즉시 null 사례를 처리할 수 있습니다.
val account = Account("name", "type")
val accountName = account.name?.trim() ?: "Default name"
// Elvis연산자 왼쪽에 있는 표현식의 결과가 null이면 오른쪽의 값이 accountName에 할당됩니다.
// 이것으로 초기에 반환할 수도 있습니다.
fun validateAccount(account: Account?) {
    val accountName = account?.name?.trim() ?: "Default Name"

    // account cannot be null beyond this point
    account ?: return

    ...
}


// 속성 초기화
// Kotlin의 속성은 기본적으로 초기화되지 않습니다. 인클로징 클래스가 초기화될 때 속성이 초기화되어야 합니다.
// 클래스 선언에서 값을 할당하여 `index` 변수를 초기화하는 방법
class LoginFragment : Fragment() {
    val index: Int

    init {
        index = 12
    }
}

// 객체 구성 중에 초기화할 수 없는 속성이 있을 수 있습니다.
// 한가지 방법으로 뷰를 null을 허용하는 것으로 선언하고 최대한 빨리 초기화하는 것이 있습니다.
class LoginFragment : Fragment() {
    private var statusTextView: TextView = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        statusTextView = view.findViewById(R.id.status_text_view)
        statusTextView?.setText(R.string.auth_failed)
    }
}
// 다만 이 작업이 예상대로 작동하더라도 참조할 때 마다 View의 null허용 여부를 관리해야 합니다.

class LoginFragment : Fragment() {
    private lateinit var statusTextView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        statusTextView = view.findViewById(R.id.status_text_view)
        statusTextView.setText(R.string.auth_failed)
    }
}
// lateinit 키워드를 사용하면 객체가 구성될 때 속성을 초기화하는 것을 방지할 수 있습니다.
// 속성을 초기화하기 전에 참조하면 Kotlin이 `UninitializedPropertyAccessException`을 발생시키므로 최대한 빨리 속성을 초기화해야 합니다.
// `findViewById` 수동 호출을 삭제하는 데이터 결합과 같은 뷰 결합 솔루션을 사용하면 고려해야 할 null 안전성 문제의 수를 줄일 수 있습니다.












// Kotlin 코드와 관련된 안드로이드 코딩 표준

// 모든 소스 파일은 UTF-8로 인코딩되어야 합니다.

// 소스 파일에 최상위 *클래스가 하나 뿐인 경우 파일 이름에 대소문자를 구분하는 이름과 .kt확장자가 반영되어야 합니다.
// 최상위 수준 선언이 여러 개 있는 경우 파일의 콘텐츠를 설명하는 이름을 선택하고 PascalCase를 적용한 다음 .kt확장자를 추가합니다.

// MyClass.kt
class MyClass{ }

// Bar.kt
class Bar { }
fun Runnable.toBar(): Bar = // ...

// Map.kt
fun <T, O> Set<T>.map(func: (T) -> O): List<O> = // ...
fun <T, O> List<T>.map(func: (T) -> O): List<O> = // ...

// .kt 파일은 다음과 같은 순서로 구성됩니다.
// 저작권 및/또는 라이센스 헤더 (선택)
// 파일 수준 주석
// Package 문
// Import 문
// 최상위 수준 선언

// 빈 줄을 하나만 사용하여 각 섹션을 구분합니다.

// 저작권/라이센스 -> 여러줄 주석

/*
 * Copyright
 *
 * ...
 */


// 빈 블록 또는 블록 형식 구문은 K&R 스타일이어야 합니다.

try {
    doSomething()
} catch (e: Exception) {
}



















// Default Parameter Values and Named Arguments

fun printMessage(message: String): Unit {
    println(message)
}

fun printMessageWithPrefix(message: String, prefix: String = "Info") {
    println("[$prefix] $message")
}

fun sum(x: Int, y: Int): Int {
    return x + y
}

fun multiply(x: Int, y: Int) = x * y

fun main() {
    printMessage("Hello")
    printMessageWithPrefix("Hello", "Log")
    printMessageWithPrefix("Hello")
    printMessageWithPrefix(prefix = "Log", message = "Hello")
    println(sum(1, 2))
    println(multiply(2, 4))
}

// Infix Functions **
fun main() {

    infix fun Int.times(str: String) = str.repeat(this)
    println(2 times "Bye ")

    val pair = "Ferrari" to "Katrina"
    println(pair)

    infix fun String.onto(other: String) = Pair(this, other)
    val myPair = "Mclaren" onto "Lucas"
    println(myPair)

    val sophia = Person("Sophia")
    val claudia = Person("Claudia")
    sophia likes claudia
}

class Person(val name: String) {
    val likedPeople = mutableListOf<Person>()
    infix fun likes(other: Person) { likedPeople.add(other) }
}

// Operator functions
operator fun Int.times(str: String) = str.repeat(this)
println(2 * "Bye ")

operator fun String.get(range: IntRange) = substring(range)
val str = "Always forgive your enemies; nothing annoys them so much."
println(str[0..14])
