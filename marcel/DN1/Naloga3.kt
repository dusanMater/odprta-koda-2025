fun main() {

    // 1. if-else za preverjanje, ali je uporabnik polnoleten
    val starost = 20
    if (starost >= 18) {
        println("3.1 - Uporabnik je polnoleten.")
    } else {
        println("3.1 - Uporabnik NI polnoleten.")
    }

    // 2. when za izpis dneva v tednu glede na številko
    val dan = 3
    val imeDneva = when (dan) {
        1 -> "Ponedeljek"
        2 -> "Torek"
        3 -> "Sreda"
        4 -> "Četrtek"
        5 -> "Petek"
        6 -> "Sobota"
        7 -> "Nedelja"
        else -> "Neveljavna številka"
    }
    println("3.2 - Dan v tednu: $imeDneva")

    // 3. preveri, ali je številka pozitivna, negativna ali 0
    val stevilo = -5
    if (stevilo > 0) {
        println("3.3 - Število je pozitivno")
    } else if (stevilo < 0) {
        println("3.3 - Število je negativno")
    } else {
        println("3.3 - Število je 0")
    }

    // 4. for zanka, ki izpiše števila od 1 do 10
    println("3.4 - Števila od 1 do 10:")
    for (i in 1..10) {
        println(i)
    }

    // 5. while zanka, ki šteje do 5
    println("3.5 - Štejem do 5 z while:")
    var j = 1
    while (j <= 5) {
        println(j)
        j++
    }

    // 6. seštej vsa števila med 1 in 100
    var vsota = 0
    for (i in 1..100) {
        vsota += i
    }
    println("3.6 - Vsota od 1 do 100 je $vsota")

    // 7. Preštej števila deljiva s 3 v intervalu 1..50
    var stevec = 0
    for (i in 1..50) {
        if (i % 3 == 0) {
            stevec++
        }
    }
    println("3.7 - Število deljivih s 3 med 1 in 50: $stevec")

    // 8. break v zanki, prekini pri i = 5
    println("3.8 - Prekinitev zanke pri 5:")
    for (i in 1..10) {
        if (i == 5) {
            break
        }
        println(i)
    }

    // 9. continue v zanki, preskoči 5
    println("3.9 - Preskok števila 5:")
    for (i in 1..10) {
        if (i == 5) {
            continue
        }
        println(i)
    }

    // 10. for-each zanka za seznam imen
    val imena = listOf("Ana", "Blaž", "Cene")
    println("3.10 - Imena v seznamu:")
    for (ime in imena) {
        println(ime)
    }
}