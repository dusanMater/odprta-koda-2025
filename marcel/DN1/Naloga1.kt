fun main() {
    // 1. Ustvari spremenljivko ime tipa String in ji dodeli svoje ime.
    var ime: String = "Marcel"

    // 2. Ustvari celo številsko spremenljivko letoRojstva in izračunaj svojo starost.
    val letoRojstva: Int = 1997
    val starost = 2025 - letoRojstva
    println("Tvoja starost je $starost let.")

    // 3. Pretvori starost v Double.
    val starostDouble = starost.toDouble()
    println("Starost kot decimalno število: $starostDouble")

    // 4. Uporabi val in poskusi spremeniti vrednost
    val x = 10
    // x = 20 če to odkomentiram bo napaka ker val ne dovoli sprememb

    // 5. Razlika med val in var
    val a = 5
    // a = 6 napaka
    var b = 5
    b = 6  // deluje

    // 6. Ustvari spremenljivko cena tipa Float in jo zaokroži.
    val cena: Float = 19.99f
    val zaokrozena = Math.round(cena)
    println("Zaokrožena cena: $zaokrozena")

    // 7. Izračunaj povprečje treh števil.
    val s1 = 10
    val s2 = 15
    val s3 = 20
    val povprecje = (s1 + s2 + s3) / 3.0
    println("Povprečje: $povprecje")

    // 8. Interpolacija nizov
    println("Pozdravljen, $ime!")

    // 9. Uporabi .length na spremenljivki String
    println("Dolžina imena: ${ime.length}")

    // 10. Pridobi prvi znak iz niza
    val prviZnak = ime[0]
    println("Prvi znak tvojega imena: $prviZnak")
}