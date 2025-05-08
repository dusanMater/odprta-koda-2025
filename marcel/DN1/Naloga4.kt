// 1. Funkcija pozdravi(ime: String) ki izpiše "Pozdravljen, ime"
fun pozdravi(ime: String) {
    println("Pozdravljen, $ime")
}

// 2. Funkcija ki vrne kvadrat števila
fun kvadrat(stevilo: Int): Int {
    return stevilo * stevilo
}

// 3. Funkcija ki izračuna obseg kroga
fun obsegKroga(polmer: Double): Double {
    return 2 * Math.PI * polmer
}

// 4. Funkcija ki vrne true če je število sodo
fun jeSodo(stevilo: Int): Boolean {
    return stevilo % 2 == 0
}

// 5. Funkcija z dvema parametroma, ki vrne večjega
fun vecjeStevilo(a: Int, b: Int): Int {
    return if (a > b) a else b
}

// 6. Funkcija ki vrne največje število izmed treh
fun najvecjeOdTreh(a: Int, b: Int, c: Int): Int {
    return maxOf(a, b, c)
}

// 7. Funkcija brez parametrov ki vrne naključno število
fun nakljucnoStevilo(): Int {
    return (1..100).random()
}

// 8. Funkcija ki združi dva niza
fun zdruziNiza(a: String, b: String): String {
    return a + b
}

// 9. Rekurzivna funkcija za fakulteto
fun fakulteta(n: Int): Int {
    return if (n <= 1) 1 else n * fakulteta(n - 1)
}

// 10. Funkcija z neobveznim parametrom
fun pozdrav(ime: String, ura: Int = 12) {
    println("Pozdravljen, $ime! Trenutna ura je $ura.")
}


fun main() {
    pozdravi("Marcel")
    println("Kvadrat števila 4: ${kvadrat(4)}")
    println("Obseg kroga s polmerom 3: ${obsegKroga(3.0)}")
    println("Ali je 6 sodo? ${jeSodo(6)}")
    println("Večje število med 5 in 9: ${vecjeStevilo(5, 9)}")
    println("Največje število: ${najvecjeOdTreh(3, 7, 5)}")
    println("Naključno število: ${nakljucnoStevilo()}")
    println("Združena niza: ${zdruziNiza("Zdravo, ", "svet!")}")
    println("Fakulteta števila 5: ${fakulteta(5)}")
    pozdrav("Ana")
    pozdrav("Tomaž", 18)
}