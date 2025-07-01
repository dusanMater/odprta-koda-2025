// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    println("=== POGOJNE STAVKE IN ZANKE ===\n")

    // 1. If-else preverjanje polnoletnosti
    println("1. Preverjanje polnoletnosti:")
    print("Vnesite svojo starost: ")
    val starost = readLine()?.toIntOrNull() ?: 0

    if (starost >= 18) {
        println("Ste polnoletni!")
    } else {
        println("Niste še polnoletni.")
    }
    println()

    // 2. When za izpis dneva v tednu
    println("2. Dan v tednu:")
    print("Vnesite številko dneva (1-7): ")
    val danStevilka = readLine()?.toIntOrNull() ?: 1

    val dan =
            when (danStevilka) {
                1 -> "Ponedeljek"
                2 -> "Torek"
                3 -> "Sreda"
                4 -> "Četrtek"
                5 -> "Petek"
                6 -> "Sobota"
                7 -> "Nedelja"
                else -> "Neveljavna številka dneva"
            }
    println("Dan v tednu: $dan")
    println()

    // 3. Preverjanje pozitivnega, negativnega ali 0
    println("3. Preverjanje predznaka številke:")
    print("Vnesite številko: ")
    val stevilo = readLine()?.toIntOrNull() ?: 0

    when {
        stevilo > 0 -> println("Številka $stevilo je pozitivna")
        stevilo < 0 -> println("Številka $stevilo je negativna")
        else -> println("Številka je enaka 0")
    }
    println()

    // 4. For zanka - števila od 1 do 10
    println("4. For zanka - števila od 1 do 10:")
    for (i in 1..10) {
        print("$i ")
    }
    println("\n")

    // 5. While zanka - šteje do 5
    println("5. While zanka - šteje do 5:")
    var stevec = 1
    while (stevec <= 5) {
        print("$stevec ")
        stevec++
    }
    println("\n")

    // 6. Seštej vsa števila med 1 in 100
    println("6. Vsota števil med 1 in 100:")
    var vsota = 0
    for (i in 1..100) {
        vsota += i
    }
    println("Vsota = $vsota")
    println()

    // 7. Števila deljiva s 3 v danem intervalu
    println("7. Števila deljiva s 3:")
    print("Vnesite začetek intervala: ")
    val zacetek = readLine()?.toIntOrNull() ?: 1
    print("Vnesite konec intervala: ")
    val konec = readLine()?.toIntOrNull() ?: 20

    var stDeljivihS3 = 0
    println("Števila deljiva s 3 med $zacetek in $konec:")
    for (i in zacetek..konec) {
        if (i % 3 == 0) {
            print("$i ")
            stDeljivihS3++
        }
    }
    println("\nŠtevilo števil deljivih s 3: $stDeljivihS3")
    println()

    // 8. Uporaba break - prekinitev zanke
    println("8. Uporaba break - iskanje prvega števila večjega od 50:")
    for (i in 1..100) {
        if (i > 50) {
            println("Prvo število večje od 50 je: $i")
            break
        }
    }
    println()

    // 9. Uporaba continue - preskok sodb števil
    println("9. Uporaba continue - izpis le lihih števil od 1 do 20:")
    for (i in 1..20) {
        if (i % 2 == 0) {
            continue // preskoči soda števila
        }
        print("$i ")
    }
    println("\n")

    // 10. For-each zanka za seznam imen
    println("10. For-each zanka za seznam imen:")
    val imena = listOf("Ana", "Janez", "Maja", "Peter", "Sara", "Luka")

    println("Seznam vseh imen:")
    for (ime in imena) {
        println("- $ime")
    }

    println("\nImena z uporabo forEach:")
    imena.forEach { ime -> println("Pozdrav, $ime!") }

    println("\nImena, ki se začnejo z veliko 'A' ali 'M':")
    for (ime in imena) {
        if (ime.startsWith("A") || ime.startsWith("M")) {
            println("- $ime")
        }
    }

    println("\n=== KONEC PROGRAMA ===")
}
