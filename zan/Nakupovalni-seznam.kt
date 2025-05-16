//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
class Artikel(private var naziv: String, private var vKosarici: Boolean = false) {

    fun getNaziv(): String = naziv

    fun setNaziv(noviNaziv: String) {
        naziv = noviNaziv
    }

    fun jeVKosarici(): Boolean = vKosarici

    fun nastaviVKosarici(vrednost: Boolean) {
        vKosarici = vrednost
    }

    override fun toString(): String {
        return "$naziv (${if (vKosarici) "v košarici" else "ni v košarici"})"
    }
}

class NakupovalniSeznam {
    private val seznam = mutableListOf<Artikel>()

    fun dodajArtikel(naziv: String) {
        if (!jeNaSeznamu(naziv)) {
            seznam.add(Artikel(naziv))
        } else {
            println("Artikel že obstaja na seznamu.")
        }
    }

    fun izbrisiArtikel(naziv: String) {
        seznam.removeIf { it.getNaziv() == naziv }
    }

    fun spremeniNaziv(stariNaziv: String, noviNaziv: String) {
        val artikel = seznam.find { it.getNaziv() == stariNaziv }
        artikel?.setNaziv(noviNaziv)
    }

    fun izpisiSeznam() {
        if (seznam.isEmpty()) {
            println("Seznam je prazen.")
        } else {
            println("Nakupovalni seznam:")
            seznam.forEach { println("- ${it}") }
        }
    }

    fun jeNaSeznamu(naziv: String): Boolean {
        return seznam.any { it.getNaziv() == naziv }
    }

    fun nastaviVKosarici(naziv: String, vKosarici: Boolean) {
        val artikel = seznam.find { it.getNaziv() == naziv }
        artikel?.nastaviVKosarici(vKosarici)
    }
}

fun main() {
    val seznam = NakupovalniSeznam()
    while (true) {
        println("""
            |Izberi možnost:
            |1 - Dodaj artikel
            |2 - Izbriši artikel
            |3 - Spremeni naziv artikla
            |4 - Izpiši seznam
            |5 - Označi kot v košarici
            |6 - Preveri, če je artikel na seznamu
            |0 - Izhod
        """.trimMargin())

        when (readLine()?.toIntOrNull()) {
            1 -> {
                print("Vnesi naziv novega artikla: ")
                val naziv = readLine().orEmpty()
                seznam.dodajArtikel(naziv)
            }
            2 -> {
                print("Vnesi naziv artikla za brisanje: ")
                val naziv = readLine().orEmpty()
                seznam.izbrisiArtikel(naziv)
            }
            3 -> {
                print("Stari naziv: ")
                val stari = readLine().orEmpty()
                print("Novi naziv: ")
                val novi = readLine().orEmpty()
                seznam.spremeniNaziv(stari, novi)
            }
            4 -> seznam.izpisiSeznam()
            5 -> {
                print("Naziv artikla za označitev: ")
                val naziv = readLine().orEmpty()
                println("Ali je v košarici? (da/ne): ")
                val odgovor = readLine().orEmpty().lowercase()
                seznam.nastaviVKosarici(naziv, odgovor == "da")
            }
            6 -> {
                print("Vnesi naziv za preverjanje: ")
                val naziv = readLine().orEmpty()
                println(if (seznam.jeNaSeznamu(naziv)) "Artikel je na seznamu." else "Artikel NI na seznamu.")
            }
            0 -> break
            else -> println("Neveljavna izbira.")
        }
    }
}
