// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import kotlin.math.PI
import kotlin.random.Random

// 1. Funkcija pozdravi(ime: String), ki izpiše "Pozdravljen, ime"
fun pozdravi(ime: String) {
    println("Pozdravljen, $ime")
}

// 2. Funkcija, ki vrne kvadrat števila
fun kvadrat(stevilo: Int): Int {
    return stevilo * stevilo
}

// Preobložena verzija za Double
fun kvadrat(stevilo: Double): Double {
    return stevilo * stevilo
}

// 3. Funkcija, ki izračuna obseg kroga
fun obsegKroga(polmer: Double): Double {
    return 2 * PI * polmer
}

// 4. Funkcija, ki vrne true, če je število sodo
fun jeSodo(stevilo: Int): Boolean {
    return stevilo % 2 == 0
}

// 5. Funkcija z dvema parametroma, ki vrne večjega
fun vecje(a: Int, b: Int): Int {
    return if (a > b) a else b
}

// Preobložena verzija za Double
fun vecje(a: Double, b: Double): Double {
    return if (a > b) a else b
}

// 6. Funkcija, ki vrne največje število izmed treh
fun najvecje(a: Int, b: Int, c: Int): Int {
    return maxOf(a, b, c)
}

// Preobložena verzija za Double
fun najvecje(a: Double, b: Double, c: Double): Double {
    return maxOf(a, b, c)
}

// 7. Funkcija brez parametrov, ki vrne naključno število
fun naklucnoStevilo(): Int {
    return Random.nextInt(1, 101) // Naključno število med 1 in 100
}

// 8. Funkcija, ki združi dva niza
fun zdruziFI(niz1: String, niz2: String): String {
    return niz1 + niz2
}

// 9. Funkcija z neobveznim parametrom
fun pozdrav(ime: String, ura: Int = 12): String {
    return when (ura) {
        in 6..11 -> "Dobro jutro, $ime! Ura je $ura."
        in 12..17 -> "Dober dan, $ime! Ura je $ura."
        in 18..22 -> "Dober večer, $ime! Ura je $ura."
        else -> "Dobro noč, $ime! Ura je $ura."
    }
}

fun main() {
    println("=== DEMONSTRACIJA FUNKCIJ V KOTLINU ===\n")

    // 1. Pozdravljanje
    println("1. Funkcija pozdravi:")
    pozdravi("Ana")
    pozdravi("Marko")
    println()

    // 2. Kvadrat števila
    println("2. Funkcija kvadrat:")
    println("Kvadrat števila 5: ${kvadrat(5)}")
    println("Kvadrat števila 3.5: ${kvadrat(3.5)}")
    println()

    // 3. Obseg kroga
    println("3. Funkcija obseg kroga:")
    println("Obseg kroga s polmerom 5.0: ${String.format("%.2f", obsegKroga(5.0))}")
    println("Obseg kroga s polmerom 10.0: ${String.format("%.2f", obsegKroga(10.0))}")
    println()

    // 4. Preverjanje sodosti
    println("4. Funkcija jeSodo:")
    println("Je 4 sodo? ${jeSodo(4)}")
    println("Je 7 sodo? ${jeSodo(7)}")
    println("Je 12 sodo? ${jeSodo(12)}")
    println()

    // 5. Večje od dveh
    println("5. Funkcija vecje:")
    println("Večje med 10 in 5: ${vecje(10, 5)}")
    println("Večje med 3.7 in 8.2: ${vecje(3.7, 8.2)}")
    println()

    // 6. Največje od treh
    println("6. Funkcija najvecje:")
    println("Največje med 3, 7, 5: ${najvecje(3, 7, 5)}")
    println("Največje med 2.1, 1.9, 2.5: ${najvecje(2.1, 1.9, 2.5)}")
    println()

    // 7. Naključno število
    println("7. Funkcija naklucnoStevilo:")
    println("Naključno število: ${naklucnoStevilo()}")
    println("Drugo naključno število: ${naklucnoStevilo()}")
    println("Tretje naključno število: ${naklucnoStevilo()}")
    println()

    // 8. Združevanje nizov
    println("8. Funkcija zdruziFI:")
    println("Združi 'Hello' + ' World': '${zdruziFI("Hello", " World")}'")
    println("Združi 'Kotlin' + ' je odličen!': '${zdruziFI("Kotlin", " je odličen!")}'")
    println()

    // 9. Funkcija z neobveznim parametrom
    println("9. Funkcija pozdrav z neobveznim parametrom:")
    println(pozdrav("Ana")) // Uporabi privzeto vrednost (12)
    println(pozdrav("Ana", ura = 8))
    println(pozdrav("Marko", ura = 15))
    println(pozdrav("Petra", ura = 20))
    println(pozdrav("Jan", ura = 2))
    println()

    println("=== INTERAKTIVNI DEL ===")
    println("Vnesite vaše ime:")
    val uporabniskoIme = readLine() ?: "Gost"
    pozdravi(uporabniskoIme)

    println("Vnesite število za kvadriranje:")
    val stevilo = readLine()?.toIntOrNull() ?: 0
    println("Kvadrat števila $stevilo je: ${kvadrat(stevilo)}")

    println("\nVnesite polmer kroga:")
    val polmer = readLine()?.toDoubleOrNull() ?: 1.0
    println("Obseg kroga s polmerom $polmer je: ${String.format("%.2f", obsegKroga(polmer))}")

    println("\n=== KONEC DEMONSTRACIJE ===")
}
