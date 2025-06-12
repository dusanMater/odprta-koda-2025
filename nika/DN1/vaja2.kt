// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import kotlin.math.pow

fun main() {
    println("=== OPERATORJI V KOTLINU ===\n")

    // 🧮 ARITMETIČNI OPERATORJI
    println("🧮 ARITMETIČNI OPERATORJI")
    println("-".repeat(30))

    arithmeticOperators()

    println("\n" + "=".repeat(50) + "\n")

    // 🔁 PRIMERJALNI OPERATORJI
    println("🔁 PRIMERJALNI OPERATORJI")
    println("-".repeat(30))

    comparisonOperators()

    println("\n" + "=".repeat(50) + "\n")

    // 🔁 LOGIČNI OPERATORJI
    println("🔁 LOGIČNI OPERATORJI")
    println("-".repeat(30))

    logicalOperators()
}

fun arithmeticOperators() {
    // 1. Seštej dve števili
    print("Vnesi prvo število: ")
    val num1 = readLine()?.toIntOrNull() ?: 10
    print("Vnesi drugo število: ")
    val num2 = readLine()?.toIntOrNull() ?: 5
    println("Vsota: $num1 + $num2 = ${num1 + num2}")

    // 2. Določi, ali je število sodo ali liho z uporabo %
    print("Vnesi število za preverjanje sodo/liho: ")
    val number = readLine()?.toIntOrNull() ?: 7
    println("Število $number je ${if (number % 2 == 0) "sodo" else "liho"}")

    // 3. Pretvori Int v Double in izračunaj povprečje
    val intA = 15
    val intB = 20
    val average = (intA.toDouble() + intB.toDouble()) / 2
    println("Povprečje števil $intA in $intB: $average")

    // 4. Izračunaj površino pravokotnika
    print("Vnesi dolžino pravokotnika: ")
    val length = readLine()?.toDoubleOrNull() ?: 8.0
    print("Vnesi širino pravokotnika: ")
    val width = readLine()?.toDoubleOrNull() ?: 5.0
    val area = length * width
    println("Površina pravokotnika: $length × $width = $area")

    // 5. Pretvori stopinje Celzija v Fahrenheite
    print("Vnesi temperaturo v Celzijih: ")
    val celsius = readLine()?.toDoubleOrNull() ?: 25.0
    val fahrenheit = celsius * 9 / 5 + 32
    println("$celsius°C = $fahrenheit°F")

    // 6. Deli število in izpiši rezultat z dvema decimalnima mestoma
    print("Vnesi deljenec: ")
    val dividend = readLine()?.toDoubleOrNull() ?: 22.0
    print("Vnesi delitelj: ")
    val divisor = readLine()?.toDoubleOrNull() ?: 7.0
    val result = dividend / divisor
    println("$dividend ÷ $divisor = %.2f".format(result))

    // 7. Uporabi inkrement (++) in dekrement (--)
    var counter = 5
    println("Začetna vrednost: $counter")
    println("Po incrementu (++): ${++counter}")
    println("Po decrementu (--): ${--counter}")

    // 8. Izračunaj kvadrat števila
    print("Vnesi število za kvadriranje: ")
    val numToSquare = readLine()?.toDoubleOrNull() ?: 4.0
    val square = numToSquare.pow(2)
    println("Kvadrat števila $numToSquare je $square")

    // 9. Izračunaj ostanek pri deljenju dveh števil
    print("Vnesi prvo število za ostanek: ")
    val modNum1 = readLine()?.toIntOrNull() ?: 17
    print("Vnesi drugo število za ostanek: ")
    val modNum2 = readLine()?.toIntOrNull() ?: 5
    println("$modNum1 % $modNum2 = ${modNum1 % modNum2}")

    // 10. Pretvori Double rezultat nazaj v Int
    val doubleResult = 15.7
    val intResult = doubleResult.toInt()
    println("Double $doubleResult pretvorjen v Int: $intResult")
}

fun comparisonOperators() {
    // 1. Primerjaj dve številki – izpiši katera je večja
    print("Vnesi prvo število za primerjavo: ")
    val comp1 = readLine()?.toIntOrNull() ?: 12
    print("Vnesi drugo število za primerjavo: ")
    val comp2 = readLine()?.toIntOrNull() ?: 8
    when {
        comp1 > comp2 -> println("$comp1 je večje od $comp2")
        comp1 < comp2 -> println("$comp1 je manjše od $comp2")
        else -> println("$comp1 je enako $comp2")
    }

    // 2. Ali je x == y?
    val x = 10
    val y = 10
    println("Ali je x($x) == y($y)? ${x == y}")

    // 3. Če je starost >= 18, izpiši "polnoleten"
    print("Vnesi svojo starost: ")
    val age = readLine()?.toIntOrNull() ?: 20
    if (age >= 18) {
        println("Polnoleten")
    } else {
        println("Mladoleten")
    }

    // 4. Primerjaj dolžini dveh nizov
    print("Vnesi prvi niz: ")
    val string1 = readLine() ?: "Hello"
    print("Vnesi drugi niz: ")
    val string2 = readLine() ?: "World"
    when {
        string1.length > string2.length -> println("Prvi niz je daljši")
        string1.length < string2.length -> println("Drugi niz je daljši")
        else -> println("Oba niza sta enako dolga")
    }

    // 5. Ali je podatek različen od nič?
    val data = 42
    println("Ali je podatek ($data) različen od nič? ${data != 0}")

    // 6. Ali sta dva niza enaka?
    val str1 = "Kotlin"
    val str2 = "Kotlin"
    println("Ali sta niza '$str1' in '$str2' enaka? ${str1 == str2}")

    // 7. Če je a < b in b < c, izpiši true
    val a = 5
    val b = 10
    val c = 15
    println("Ali je $a < $b in $b < $c? ${a < b && b < c}")

    // 8. Če je score med 90 in 100, izpiši "Odlično"
    print("Vnesi svojo oceno (0-100): ")
    val score = readLine()?.toIntOrNull() ?: 95
    if (score >= 90 && score <= 100) {
        println("Odlično!")
    } else {
        println("Ocena: $score")
    }

    // 9. Primerjaj dva znaka
    val char1 = 'a'
    val char2 = 'z'
    println("Ali je '$char1' < '$char2'? ${char1 < char2}")

    // 10. Primerjaj datuma (kot String)
    val date1 = "2024-01-15"
    val date2 = "2024-12-25"
    println(
            "Primerjava datumov po dolžini: '${date1}' (${date1.length}) vs '${date2}' (${date2.length})"
    )
    println("Primerjava datumov znakovno: ${date1 < date2}")
}

fun logicalOperators() {
    // 1. Uporabi && za preverjanje dveh pogojev
    print("Vnesi temperaturo: ")
    val temp = readLine()?.toIntOrNull() ?: 22
    if (temp >= 20 && temp <= 30) {
        println("Prijetna temperatura!")
    }

    // 2. Uporabi || za preverjanje vsaj enega pogoja
    print("Vnesi dan v tednu (1-7): ")
    val day = readLine()?.toIntOrNull() ?: 6
    if (day == 6 || day == 7) {
        println("Vikend!")
    } else {
        println("Delovni dan")
    }

    // 3. Uporabi ! za negacijo pogoja
    val isRaining = false
    if (!isRaining) {
        println("Ne dežuje, lahko gremo ven!")
    }

    // 4. Če je starost med 18 in 65, izpiši "Delovno aktiven"
    print("Vnesi starost za preverjanje delovne aktivnosti: ")
    val workAge = readLine()?.toIntOrNull() ?: 35
    if (workAge >= 18 && workAge <= 65) {
        println("Delovno aktiven")
    } else {
        println("Ni delovno aktiven")
    }

    // 5. Kombiniraj && in || za več pogojev
    print("Vnesi uro (0-23): ")
    val hour = readLine()?.toIntOrNull() ?: 14
    val isWeekend = true
    if ((hour >= 9 && hour <= 17) || (isWeekend && hour >= 10 && hour <= 16)) {
        println("Trgovina je odprta")
    } else {
        println("Trgovina je zaprta")
    }

    // 6. Preveri, ali znak ni črka
    val character = '5'
    if (!(character in 'a'..'z' || character in 'A'..'Z')) {
        println("Znak '$character' ni črka")
    }

    // 7. Ali je vsaj eden izmed dveh nizov prazen?
    val text1 = ""
    val text2 = "besedilo"
    if (text1.isEmpty() || text2.isEmpty()) {
        println("Vsaj eden izmed nizov je prazen")
    } else {
        println("Oba niza imata vsebino")
    }

    // 8. Funkcija, ki vrne true, če je število pozitivno in sodo
    fun isPositiveAndEven(num: Int): Boolean {
        return num > 0 && num % 2 == 0
    }

    val testNumber = 8
    println("Ali je $testNumber pozitivno in sodo? ${isPositiveAndEven(testNumber)}")

    // 9. Logični pogoji z Boolean spremenljivkami
    val isLoggedIn = true
    val hasPermission = false
    val canAccess = isLoggedIn && hasPermission
    println("Lahko dostopa do vsebine? $canAccess")

    // 10. Razloži if (!(a > b)) in preizkusi
    val valA = 5
    val valB = 10
    println("a = $valA, b = $valB")
    println("a > b = ${valA > valB}")
    println("!(a > b) = ${!(valA > valB)}")
    if (!(valA > valB)) {
        println("Pogoj !(a > b) je izpolnjen - to pomeni, da a NI večji od b")
    }
}
