fun main() {
    // 1. Ustvari List celih števil
    val seznamStevil = listOf(1, 2, 3, 4, 5)
    println("Seznam števil: $seznamStevil")

    // 2. Dodaj element v mutableListOf
    val mutableSeznam = mutableListOf(10, 20, 30)
    mutableSeznam.add(40)
    println("Mutable seznam po dodajanju: $mutableSeznam")

    // 3. Odstrani element iz seznama
    mutableSeznam.remove(20)
    println("Mutable seznam po odstranitvi: $mutableSeznam")

    // 4. Poišči največje število v seznamu
    val maxStevilo = seznamStevil.maxOrNull()
    println("Največje število v seznamu je: $maxStevilo")

    // 5. Uporabi forEach za izpis vseh elementov
    println("Vsi elementi v seznamu:")
    seznamStevil.forEach { println(it) }

    // 6. Filtriraj seznam (npr. samo sode številke)
    val sodeStevilke = seznamStevil.filter { it % 2 == 0 }
    println("Sode številke v seznamu: $sodeStevilke")

    // 7. Sortiraj seznam števil naraščajoče
    val sortiraniSeznam = seznamStevil.sorted()
    println("Seznam števil po sortiranju: $sortiraniSeznam")

    // 8. Uporabi map() za podvojitev vsakega elementa
    val podvojenaStevila = seznamStevil.map { it * 2 }
    println("Seznam z podvojenimi številkami: $podvojenaStevila")

    // 9. Uporabi contains() za preverjanje, ali seznam vsebuje element
    val vsebujeElement = seznamStevil.contains(3)
    println("Seznam vsebuje število 3: $vsebujeElement")

    // 10. Pretvori seznam String v en String z vejico (joinToString())
    val seznamStringov = listOf("Jabolko", "Banana", "Češnja")
    val zdruzenSeznam = seznamStringov.joinToString(", ")
    println("Združen seznam: $zdruzenSeznam")
}