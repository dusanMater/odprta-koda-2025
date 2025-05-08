// 1. Ustvari razred Oseba z ime in starost
class Oseba(val ime: String, val starost: Int)

// 2. Dodaj metodo predstaviSe()
fun Oseba.predstaviSe() {
    println("Zdravo, moje ime je $ime in imam $starost let.")
}

// 3. Ustvari podatkovni razred Avto(marka: String, leto: Int)
data class Avto(val marka: String, val leto: Int)

// 4. Ustvari seznam Avto in izpiši vse
fun izpisiAvte() {
    val avti = listOf(
        Avto("Volkswagen", 2010),
        Avto("BMW", 2020),
        Avto("Fiat", 2015)
    )
    for (avto in avti) {
        println("Marka: ${avto.marka}, Leto: ${avto.leto}")
    }
}

// 5. Uporabi copy() za podatkovni razred
fun primerjajAvto() {
    val avto1 = Avto("Tesla", 2022)
    val avto2 = avto1.copy(marka = "Audi")
    println("Originalni avto: $avto1")
    println("Kopirani avto: $avto2")
}

// 6. Primerjaj dva objekta data class (enakost)
fun primerjajAvte() {
    val avto1 = Avto("Toyota", 2020)
    val avto2 = Avto("Toyota", 2020)
    if (avto1 == avto2) {
        println("Avta sta enaka.")
    } else {
        println("Avta sta različna.")
    }
}

// 7. Dodaj toString() in testiraj izpis
fun testirajToString() {
    val oseba = Oseba("Marcel", 23)
    println(oseba.toString())  // To bo izpisalo "Oseba@hashcode", vendar lahko prilagodimo toString
}

// 8. Uporabi init blok za inicializacijo
class OsebaZInit(val ime: String, val starost: Int) {
    init {
        println("Nov uporabnik je bil ustvarjen: $ime, $starost let.")
    }
}

// 9. Dedišči razred Zival → Pes, Macka
open class Zival(val ime: String) {
    fun sprehod() {
        println("$ime gre na sprehod.")
    }
}

class Pes(ime: String) : Zival(ime) {
    fun lajanje() {
        println("$ime laja!")
    }
}

class Macka(ime: String) : Zival(ime) {
    fun mjanjanje() {
        println("$ime mjaaaaau!")
    }
}

// 10. Razred z metodo, ki izračuna starost iz letnice
class OsebaZLetnico(val ime: String, val letoRojstva: Int) {
    fun izracunajStarost(trenutnoLeto: Int): Int {
        return trenutnoLeto - letoRojstva
    }
}

fun main() {
    // Testiranje razreda Oseba
    val oseba1 = Oseba("Marcel", 23)
    oseba1.predstaviSe()

    // Testiranje podatkovnega razreda Avto
    izpisiAvte()
    primerjajAvto()
    primerjajAvte()

    // Testiranje toString
    testirajToString()

    // Testiranje init blok
    val oseba2 = OsebaZInit("Tomaž", 29)

    // Testiranje dedovanja
    val pes = Pes("Rex")
    pes.sprehod()
    pes.lajanje()

    val macka = Macka("Mici")
    macka.sprehod()
    macka.mjanjanje()

    // Testiranje izračuna starosti
    val oseba3 = OsebaZLetnico("Ana", 1998)
    println("Ana ima ${oseba3.izracunajStarost(2025)} let.")
}