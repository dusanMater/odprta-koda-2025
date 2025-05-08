fun main() {
    // Aritmetični operatorji

    // 1. Seštej dve števili.
    val a = 7
    val b = 3
    println("Seštevek: ${a + b}")

    // 2. Določi, ali je število sodo ali liho.
    val stevilo = 10
    if (stevilo % 2 == 0) {
        println("$stevilo je sodo.")
    } else {
        println("$stevilo je liho.")
    }

    // 3. Pretvori Int v Double in izračunaj povprečje.
    val x = 5
    val y = 8
    val povprecje = (x.toDouble() + y.toDouble()) / 2
    println("Povprečje: $povprecje")

    // 4. Izračunaj površino pravokotnika.
    val sirina = 4
    val visina = 6
    val povrsina = sirina * visina
    println("Površina pravokotnika: $povrsina")

    // 5. Pretvori Celzija v Fahrenheite.
    val celzija = 25.0
    val fahrenheit = celzija * 9 / 5 + 32
    println("$celzija°C = $fahrenheit°F")

    // 6. Deli število in izpiši rezultat z 2 decimalkama.
    val rezultat = 10.0 / 3
    println("Rezultat: %.2f".format(rezultat))

    // 7. Uporabi inkrement in dekrement.
    var stevilo2 = 5
    println("Inkrement: ${++stevilo2}")  // najprej poveča, potem izpiše
    println("Dekrement: ${--stevilo2}")  // najprej zmanjša, potem izpiše

    // 8. Izračunaj kvadrat števila.
    val kvadrat = x * x
    println("Kvadrat števila $x je $kvadrat")

    // 9. Izračunaj ostanek pri deljenju.
    val ostanek = 17 % 4
    println("Ostanek pri 17 / 4 je $ostanek")

    // 10. Pretvori Double rezultat nazaj v Int.
    val c = 8.6
    val cInt = c.toInt()
    println("Double $c kot Int je $cInt")

    // Primerjalni operatorji

    // 1. Primerjaj dve številki – katera je večja?
    val m = 12
    val n = 9
    if (m > n) println("$m je večje od $n") else println("$n je večje ali enako $m")

    // 2. Ali je x == y?
    println("Ali je x enako y? ${x == y}")

    // 3. Če je starost >= 18, izpiši "polnoleten".
    val starost = 19
    if (starost >= 18) println("Polnoleten")

    // 4. Primerjaj dolžini dveh nizov.
    val niz1 = "Ana"
    val niz2 = "Marcel"
    if (niz1.length > niz2.length) {
        println("niz1 je daljši")
    } else {
        println("niz2 je daljši ali enak")
    }

    // 5. Ali je podatek različen od nič?
    val podatek = 3
    println("Različen od 0? ${podatek != 0}")

    // 6. Ali sta dva niza enaka?
    val s1 = "hi"
    val s2 = "hi"
    println("Sta enaka? ${s1 == s2}")

    // 7. Če je a < b in b < c, izpiši true.
    val a1 = 2
    val b1 = 4
    val c1 = 6
    println("Je a < b < c? ${a1 < b1 && b1 < c1}")

    // 8. Če je score med 90 in 100, izpiši “Odlično”.
    val score = 95
    if (score in 90..100) println("Odlično")

    // 9. Primerjaj dva znaka ('a' < 'z').
    println("'a' < 'z'? ${'a' < 'z'}")

    // 10. Primerjaj datuma (kot String).
    val datum1 = "2023-01-01"
    val datum2 = "2025-01-01"
    println("Ali je datum1 pred datum2? ${datum1 < datum2}")

    // Logični operatorji

    // 1. && za preverjanje dveh pogojev.
    val jePolnoleten = true
    val imaDovoljenje = true
    if (jePolnoleten && imaDovoljenje) println("Lahko voziš")

    // 2. || za preverjanje vsaj enega pogoja.
    val imaKljuč = false
    val imaKodo = true
    if (imaKljuč || imaKodo) println("Lahko vstopiš")

    // 3. ! za negacijo
    val jeDan = false
    println("Je noč? ${!jeDan}")

    // 4. Če je starost med 18 in 65
    val st = 30
    if (st in 18..65) println("Delovno aktiven")

    // 5. Kombinacija && in ||
    val a2 = 7
    if ((a2 > 5 && a2 < 10) || a2 == 20) {
        println("Pogoj izpolnjen")
    }

    // 6. Preveri ali znak NI črka.
    val znak = '1'
    val niCrka = !znak.isLetter()
    println("Ni črka? $niCrka")

    // 7. Ali je vsaj eden izmed dveh nizov prazen?
    val nizA = ""
    val nizB = "neprazen"
    println("Vsaj eden prazen? ${nizA.isEmpty() || nizB.isEmpty()}")

    // 8. Funkcija: število je pozitivno in sodo.
    fun jePozitivnoInSodo(stev: Int): Boolean {
        return stev > 0 && stev % 2 == 0
    }
    println("Je 4 pozitivno in sodo? ${jePozitivnoInSodo(4)}")

    // 9. Logični pogoji z Boolean spremenljivkami.
    val jeVpisan = true
    val imaPlacano = false
    if (jeVpisan && imaPlacano) {
        println("Dostop omogočen")
    } else {
        println("Dostop zavrnjen")
    }

    // 10. if (!(a > b))
    val a3 = 3
    val b3 = 5
    if (!(a3 > b3)) {
        println("a NI večji od b")
    }
    if (!(a3 < b3)) {
        println("a JE večji od b")
    }
}