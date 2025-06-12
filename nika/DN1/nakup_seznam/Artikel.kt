/** Razred Artikel predstavlja artikel v nakupovalnem seznamu */
data class Artikel(var naziv: String, var vKosarici: Boolean = false) {

    /** Spremeni naziv artikla */
    fun spremeniNaziv(noviNaziv: String) {
        naziv = noviNaziv
    }

    /** Nastavi ali je artikel v košarici */
    fun nastaviVKosarici(vKosarici: Boolean) {
        this.vKosarici = vKosarici
    }

    /** Preveri ali je artikel v košarici */
    fun jeVKosarici(): Boolean {
        return vKosarici
    }

    /** Predstavitev artikla kot niz */
    override fun toString(): String {
        val status = if (vKosarici) "✓" else "○"
        return "$status $naziv"
    }
}
