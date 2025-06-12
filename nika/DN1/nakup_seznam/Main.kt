/** Glavna aplikacija za nakupovalni seznam */
fun main() {
    val nakupovalniSeznam = NakupovalniSeznam()
    var izhod = false

    println("===================================")
    println("   DOBRODOŠLI V NAKUPOVALNEM       ")
    println("           SEZNAMU!               ")
    println("===================================\n")

    while (!izhod) {
        prikaziMeni()

        print("Izbira: ")
        val izbira = readlnOrNull()?.toIntOrNull()

        when (izbira) {
            1 -> dodajArtikel(nakupovalniSeznam)
            2 -> izbrisiArtikel(nakupovalniSeznam)
            3 -> spremeniNazivArtikla(nakupovalniSeznam)
            4 -> nastaviVKosarici(nakupovalniSeznam)
            5 -> nakupovalniSeznam.izpisiSeznam()
            6 -> {
                println("\nHvala za uporabo nakupovalnega seznama!")
                println("Adijo! 👋")
                izhod = true
            }
            else -> println("❌ Napačna izbira! Poskusite znova.\n")
        }

        if (!izhod) {
            println("Pritisnite Enter za nadaljevanje...")
            readlnOrNull()
            println()
        }
    }
}

/** Prikaži glavni meni */
fun prikaziMeni() {
    println("Kaj želite narediti?")
    println("1 - Dodaj artikel")
    println("2 - Izbriši artikel")
    println("3 - Spremeni naziv artikla")
    println("4 - Nastavi ali je artikel v košarici")
    println("5 - Izpiši seznam")
    println("6 - Izhod")
}

/** Dodaj novi artikel na seznam */
fun dodajArtikel(seznam: NakupovalniSeznam) {
    print("Vnesite naziv artikla: ")
    val naziv = readlnOrNull()?.trim()

    if (naziv.isNullOrBlank()) {
        println("❌ Naziv artikla ne sme biti prazen!")
        return
    }

    seznam.dodajArtikel(naziv)
}

/** Izbriši artikel iz seznama */
fun izbrisiArtikel(seznam: NakupovalniSeznam) {
    if (seznam.jePrazen()) {
        println("❌ Seznam je prazen!")
        return
    }

    seznam.izpisiSeznam()
    print("Vnesite številko artikla za brisanje: ")
    val indeks = readlnOrNull()?.toIntOrNull()

    if (indeks != null) {
        seznam.izbrisiArtikel(indeks)
    } else {
        println("❌ Neveljaven vnos!")
    }
}

/** Spremeni naziv artikla */
fun spremeniNazivArtikla(seznam: NakupovalniSeznam) {
    if (seznam.jePrazen()) {
        println("❌ Seznam je prazen!")
        return
    }

    seznam.izpisiSeznam()
    print("Vnesite številko artikla za spreminjanje: ")
    val indeks = readlnOrNull()?.toIntOrNull()

    if (indeks != null) {
        print("Vnesite novi naziv: ")
        val noviNaziv = readlnOrNull()?.trim()

        if (noviNaziv.isNullOrBlank()) {
            println("❌ Naziv artikla ne sme biti prazen!")
            return
        }

        seznam.spremeniNazivArtikla(indeks, noviNaziv)
    } else {
        println("❌ Neveljaven vnos!")
    }
}

/** Nastavi ali je artikel v košarici */
fun nastaviVKosarici(seznam: NakupovalniSeznam) {
    if (seznam.jePrazen()) {
        println("❌ Seznam je prazen!")
        return
    }

    seznam.izpisiSeznam()
    print("Vnesite številko artikla: ")
    val indeks = readlnOrNull()?.toIntOrNull()

    if (indeks != null) {
        println("1 - Dodaj v košarico")
        println("2 - Odstrani iz košarice")
        print("Izbira: ")
        val izbira = readlnOrNull()?.toIntOrNull()

        when (izbira) {
            1 -> seznam.nastaviVKosarici(indeks, true)
            2 -> seznam.nastaviVKosarici(indeks, false)
            else -> println("❌ Neveljaven vnos!")
        }
    } else {
        println("❌ Neveljaven vnos!")
    }
}
