// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// Razred Oseba z imenom in starostjo
class Oseba(val ime: String, val starost: Int) {

    // Metoda predstaviSe()
    fun predstaviSe() {
        println("Živjo! Jaz sem $ime in star/-a sem $starost let.")
    }

    // Override toString() metode
    override fun toString(): String {
        return "Oseba(ime='$ime', starost=$starost)"
    }
}

// Podatkovni razred za primerjavo
data class OsebaData(val ime: String, val starost: Int) {
    fun predstaviSe() {
        println("Živjo! Jaz sem $ime in star/-a sem $starost let.")
    }
}

fun main() {
    println("=== DEMONSTRACIJA RAZREDOV IN PODATKOVNIH RAZREDOV ===\n")

    // Ustvarimo tri osebe z različnimi podatki
    val oseba1 = Oseba("Ana", 25)
    val oseba2 = Oseba("Marko", 30)
    val oseba3 = Oseba("Petra", 22)

    // Izpišimo jih z metodo predstaviSe()
    println("1. Predstavitev oseb:")
    oseba1.predstaviSe()
    oseba2.predstaviSe()
    oseba3.predstaviSe()

    println("\n2. Testiranje toString() metode:")
    println("oseba1: $oseba1")
    println("oseba2: $oseba2")
    println("oseba3: $oseba3")

    println("\n3. Primerjava z podatkovnim razredom:")
    val osobaData1 = OsebaData("Luka", 28)
    val osobaData2 = OsebaData("Luka", 28)

    println("Navadni razred - ali sta enaki? ${oseba1 == Oseba("Ana", 25)}")
    println("Podatkovni razred - ali sta enaki? ${osobaData1 == osobaData2}")

    println("\nPodatkovni razred avtomatsko ustvari:")
    println("toString(): $osobaData1")
    println("hashCode(): ${osobaData1.hashCode()}")
    println("copy(): ${osobaData1.copy(starost = 29)}")
}
