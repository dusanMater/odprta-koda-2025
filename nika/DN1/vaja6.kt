// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    println("=== KOTLIN POLJA (LISTI, SEZNAMI) DEMONSTRACIJA ===\n")

    // 1. Ustvari List celih števil
    println("1. Ustvarjanje List celih števil:")
    val immutableList = listOf(5, 2, 8, 1, 9, 3, 7, 4, 6)
    println("Nespremenljiv seznam: $immutableList")

    // 2. Dodaj element v mutableListOf
    println("\n2. Dodajanje elementa v mutableListOf:")
    val mutableNumbers = mutableListOf(10, 20, 30)
    println("Prvotni mutableList: $mutableNumbers")
    mutableNumbers.add(40)
    mutableNumbers.add(50)
    println("Po dodajanju elementov: $mutableNumbers")

    // 3. Odstrani element iz seznama
    println("\n3. Odstranjevanje elementa iz seznama:")
    println("Pred odstranjevanjem: $mutableNumbers")
    mutableNumbers.remove(30) // odstrani po vrednosti
    mutableNumbers.removeAt(0) // odstrani po indeksu
    println("Po odstranjevanju (vrednost 30 in element na indeksu 0): $mutableNumbers")

    // 4. Poišči največje število v seznamu
    println("\n4. Iskanje največjega števila v seznamu:")
    val allNumbers = immutableList + mutableNumbers
    println("Vsi števili: $allNumbers")
    val maxNumber = allNumbers.maxOrNull()
    println("Največje število: $maxNumber")

    // 5. Uporabi forEach za izpis vseh elementov
    println("\n5. Uporaba forEach za izpis vseh elementov:")
    print("Elementi z forEach: ")
    allNumbers.forEach { number -> print("$number ") }
    println()

    // 6. Filtriraj seznam (npr. samo sode številke)
    println("\n6. Filtriranje seznama - samo sode številke:")
    val evenNumbers = allNumbers.filter { it % 2 == 0 }
    println("Sode številke: $evenNumbers")

    val oddNumbers = allNumbers.filter { it % 2 != 0 }
    println("Lihe številke: $oddNumbers")

    // 7. Sortiraj seznam števil naraščajoče
    println("\n7. Sortiranje seznama naraščajoče:")
    println("Neurejen seznam: $allNumbers")
    val sortedNumbers = allNumbers.sorted()
    println("Urejen naraščajoče: $sortedNumbers")
    val sortedDescending = allNumbers.sortedDescending()
    println("Urejen padajoče: $sortedDescending")

    // 8. Uporabi contains() za preverjanje, ali seznam vsebuje element
    println("\n8. Preverjanje z contains():")
    val searchNumber = 5
    val containsNumber = allNumbers.contains(searchNumber)
    println("Ali seznam vsebuje število $searchNumber? $containsNumber")

    val searchNumber2 = 100
    val containsNumber2 = allNumbers.contains(searchNumber2)
    println("Ali seznam vsebuje število $searchNumber2? $containsNumber2")

    // 9. Pretvori seznam String v en String z vejico (joinToString())
    println("\n9. Pretvarjanje seznama String v en String z vejico:")
    val stringList = listOf("Kotlin", "Java", "Python", "JavaScript", "C++")
    println("Seznam nizov: $stringList")
    val joinedString = stringList.joinToString(", ")
    println("Združeni niz z vejico: $joinedString")

    // Dodatni primeri joinToString()
    val numbersAsString = allNumbers.joinToString(" -> ")
    println("Številke z puščico: $numbersAsString")

    val customJoin = stringList.joinToString(separator = " | ", prefix = "[", postfix = "]")
    println("Prilagojen format: $customJoin")

    println("\n=== KONEC DEMONSTRACIJE ===")
}
