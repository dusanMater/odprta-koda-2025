/** Razred NakupovalniSeznam upravlja z nakupovalnim seznamom */
class NakupovalniSeznam {
    private val seznam: MutableList<Artikel> = mutableListOf()

    /** Dodaj artikel na seznam */
    fun dodajArtikel(naziv: String): Boolean {
        // Preveri, ali artikel že obstaja
        if (seznam.any { it.naziv.lowercase() == naziv.lowercase() }) {
            println("Artikel '$naziv' že obstaja na seznamu!")
            return false
        }

        val artikel = Artikel(naziv)
        seznam.add(artikel)
        println("Artikel '$naziv' je bil dodan na seznam.")
        return true
    }

    /** Izbriši artikel iz seznama */
    fun izbrisiArtikel(indeks: Int): Boolean {
        return if (indeks in 1..seznam.size) {
            val artikel = seznam.removeAt(indeks - 1)
            println("Artikel '${artikel.naziv}' je bil izbrisan iz seznama.")
            true
        } else {
            println("Neveljaven indeks!")
            false
        }
    }

    /** Spremeni naziv artikla */
    fun spremeniNazivArtikla(indeks: Int, noviNaziv: String): Boolean {
        return if (indeks in 1..seznam.size) {
            // Preveri, ali novi naziv že obstaja
            if (seznam.any { it.naziv.lowercase() == noviNaziv.lowercase() }) {
                println("Artikel '$noviNaziv' že obstaja na seznamu!")
                return false
            }

            val stariNaziv = seznam[indeks - 1].naziv
            seznam[indeks - 1].spremeniNaziv(noviNaziv)
            println("Naziv artikla '$stariNaziv' je bil spremenjen v '$noviNaziv'.")
            true
        } else {
            println("Neveljaven indeks!")
            false
        }
    }

    /** Nastavi ali je artikel v košarici */
    fun nastaviVKosarici(indeks: Int, vKosarici: Boolean): Boolean {
        return if (indeks in 1..seznam.size) {
            seznam[indeks - 1].nastaviVKosarici(vKosarici)
            val status = if (vKosarici) "v košarici" else "ni v košarici"
            println("Artikel '${seznam[indeks - 1].naziv}' je sedaj $status.")
            true
        } else {
            println("Neveljaven indeks!")
            false
        }
    }

    /** Izpiši celoten seznam */
    fun izpisiSeznam() {
        if (seznam.isEmpty()) {
            println("Seznam je prazen.")
            return
        }

        println("\n=== NAKUPOVALNI SEZNAM ===")
        seznam.forEachIndexed { index, artikel -> println("${index + 1}. $artikel") }
        println("========================\n")

        val vKosarici = seznam.count { it.vKosarici }
        val skupaj = seznam.size
        println("Skupaj: $skupaj artikel(ov), $vKosarici v košarici\n")
    }

    /** Preveri ali je seznam prazen */
    fun jePrazen(): Boolean = seznam.isEmpty()

    /** Vrni velikost seznama */
    fun velikost(): Int = seznam.size
}
