fun main() {
    // 🔹 1. Delo s spremenljivkami

    // Ustvari spremenljivko ime tipa String in ji dodeli svoje ime.
    print("vpiši ime: ")
    val ime = readLine() ?: "Uporabnik" // če uporabnik ne vpiše nič, uporabi "Uporabnik"
    println("ime je $ime.")

    // Ustvari celoštevilsko spremenljivko letoRojstva in izračunaj svojo starost.
    val letoRojstva = 1989
    val trenutnoLeto = 2025
    var starost = trenutnoLeto - letoRojstva
    println("starost je $starost let")

    // Pretvori starost v Double.
    val starostDouble = starost.toDouble()
    println("starost kot Double $starostDouble")

    // Uporabi val za nespremenljivo spremenljivko in poskusi spremeniti njeno vrednost.
    val konstanta = 5
    // konstanta = 10 // <- To bo povzročilo napako, ker val ne dovoljuje spremembe vrednosti
    println("val spremenljivke ni mogoče spremeniti po inicializaciji")

    // Razlika med val in var – napiši primer.
    val konstantaPrimer = "konstanta" // val - nespremenljiva
    var spremenljivkaPrimer = "spremenljivka" // var - spremenljiva
    spremenljivkaPrimer = "spremenjena vrednost"
    println("val = $konstantaPrimer")
    println("var = $spremenljivkaPrimer")

    // Ustvari spremenljivko cena tipa Float in jo zaokroži na celo vrednost.
    val cena: Float = 12.7f
    val cenaZaokrozena = Math.round(cena)
    println("cena: $cena; zaokrožena cena: $cenaZaokrozena")

    // Izračunaj povprečje treh števil.
    val a = 10
    val b = 20
    val c = 30
    val povprecje = (a + b + c) / 3.0
    println("povprečje števil $a, $b in $c je $povprecje")

    // S funkcijo length izpiši dolžino vpisane besede (stavka).
    println("dolžina imena je ${ime.length}")

    // Pridobi prvi znak iz niza ime.
    if (ime.isNotEmpty()) {
        println("prva črka imena je ${ime[0]}")
    } else {
        println("ime ni iblo vpisano")
    }
}
