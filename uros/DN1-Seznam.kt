enum class stAtrikla(val i: Int) {
    ADD(i = 0),
    GET(i = 1),
    REMOVE(i = 2),
    CLEAR(i = 3),
    ISEMPTY(i = 4),
    SIZE(i = 5),
    EDIT(i = 6);
    companion object {
        fun fromInt(value: Int) = entries.firstOrNull { it.i == value }
    }
}

data class Value (
    val stevilo: Int?,
    val vKosarici: Boolean
)

class mojiArtikli {
    constructor()
    companion object {
        private val storage = mutableMapOf<String, Value>()
        fun add(imeArtikla: String, kolicina: Int) {
            storage.set(imeArtikla, Value(stevilo = kolicina, vKosarici = false))
            val item = storage.get(imeArtikla) as Value
            println("Dodano $imeArtikla $item")
        }
        fun get(imeArtikla: String): Value? {
            return storage.get(imeArtikla)
        }
        fun remove(imeArtikla: String) {
            storage.remove(key = imeArtikla)
        }
        fun clear() {
            storage.clear()
        }
        fun isEmpty() {
            storage.isEmpty()
        }
        fun size(): Int {
            return storage.size
        }
        fun edit(imeArtikla: String, newKey: String) {
            val items = storage.get(imeArtikla) as Value
            storage.remove(imeArtikla)
            storage.set(newKey, items)
        }
    }
}

fun main() {
    val artikl = mojiArtikli()
    println("kaj želiš da funkcija naredi :")
    println("1: dodaj:")
    println("2: izpiši artikel:")
    println("3: odstrani artikel:")
    println("4: počisti listo")
    println("5: ali je seznam prazen?")
    println("6: velikost seznama")
    println("7: uredi artikel z novim imenom prvo vnesi ime artikla, nato novo ime:")
    var st: Number
    while (true) {
        println("Vnesi številko funkcije (ena številka):")
        var input = readln()
        if (input.length == 1 && input[0].isDigit()) {
            st = input.toInt()
            break
        }
        println("Napaka: Vnesite točno eno številko (0-9)!")
    }

    println("Vnesi ime artikla:")
    var imeArtikla = readln()

    println("Vnesi količino (pritisni Enter za preskok):")
    val kolicina: Int? = when (val input = readln()) {
        "" -> null
        else -> input.toIntOrNull() ?: run {
            println("Napačen vnos - ni število. Količina bo null.")
            null
        }
    }
    if(stAtrikla.fromInt(value = st.toInt()) == stAtrikla.ADD ){
        if(kolicina != null){
            mojiArtikli.add(imeArtikla, kolicina)
        }
        else {
            mojiArtikli.add(imeArtikla, kolicina = 1)
        }
    }
    else if(stAtrikla.fromInt(value = st.toInt()) == stAtrikla.GET){
        val stevilo = mojiArtikli.get(imeArtikla)
        println("artikel: " +imeArtikla + " količina: " + stevilo)
    }
    else if(stAtrikla.fromInt(value = st.toInt()) == stAtrikla.REMOVE){
        if(mojiArtikli.get(imeArtikla) != null){
            mojiArtikli.remove(imeArtikla);
        }
        else println("Artikl ne obstaja.");
    }
    else if (stAtrikla.fromInt(value = st.toInt()) == stAtrikla.CLEAR ){
        mojiArtikli.clear()
    }
    else if(stAtrikla.fromInt(value = st.toInt()) == stAtrikla.ISEMPTY){
        println(mojiArtikli.isEmpty())
    }
    else if(stAtrikla.fromInt(value = st.toInt()) == stAtrikla.SIZE){
        println(mojiArtikli.size())
    }
    else if(stAtrikla.fromInt(value = st.toInt()) == stAtrikla.EDIT){
        println("Vnesi novo ime artikla:")
        var novoIme = readln();
        mojiArtikli.edit(imeArtikla, newKey = novoIme);
    }
}
