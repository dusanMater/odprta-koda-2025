
# Kotlin vaje

Reši programske naloge v programskem jeziku Kotlin. Uporabi razvojno okolje **Intellij IDEA Community Edition** – [link].

Za začetnike predlagam pomoč s tem [vodnikom za Kotlin](#).

Za programerje z izkušnjami predlagam tale [vodnik](#).

Pri vseh nalogah izpiši rezultat na ekran.

---

## 🔹 1. Delo s spremenljivkami

- Ustvari spremenljivko `ime` tipa `String` in ji dodeli svoje ime.
- Ustvari celoštevilsko spremenljivko `letoRojstva` in izračunaj svojo starost.
- Pretvori starost v `Double`.
- Uporabi `val` za nespremenljivo spremenljivko in poskusi spremeniti njeno vrednost. Kaj se zgodi?
- Razlika med `val` in `var` – napiši primer.
- Ustvari spremenljivko `cena` tipa `Float` in jo zaokroži na celo vrednost.
- Izračunaj povprečje treh števil.
- Vpiši ime in izpiši rezultat v obliki:
  ```
  Vpiši ime: Tilen
  Tvoje ime je Tilen.
  ```
- S funkcijo `length` izpiši dolžino vpisane besede (stavka).
- Pridobi prvi znak iz niza `ime`.

---

## 🔹 2. Operatorji

### 🧮 Aritmetični operatorji (+, -, *, /, %)
- Seštej dve števili.
- Določi, ali je število sodo ali liho z uporabo `%`.
- Pretvori `Int` v `Double` in izračunaj povprečje.
- Izračunaj površino pravokotnika (dolžino stranic vpiše uporabnik).
- Pretvori stopinje Celzija v Fahrenheite.
- Deli število in izpiši rezultat z dvema decimalnima mestoma.
- Uporabi inkrement (`++`) in dekrement (`--`).
- Izračunaj kvadrat števila.
- Izračunaj ostanek pri deljenju dveh števil.
- Pretvori `Double` rezultat nazaj v `Int`.

### 🔁 Primerjalni operatorji (==, !=, <, >, <=, >=)
- Primerjaj dve številki – izpiši katera je večja.
- Ali je `x == y`?
- Če je starost >= 18, izpiši "polnoleten".
- Primerjaj dolžini dveh nizov.
- Ali je podatek različen od nič?
- Ali sta dva niza enaka?
- Če je `a < b` in `b < c`, izpiši `true`.
- Če je `score` med 90 in 100, izpiši "Odlično".
- Primerjaj dva znaka ('a' < 'z').
- Primerjaj datuma (kot `String`, primerjaj po dolžini ali znakovno).

### 🔁 Logični operatorji (&&, ||, !)
- Uporabi `&&` za preverjanje dveh pogojev.
- Uporabi `||` za preverjanje vsaj enega pogoja.
- Uporabi `!` za negacijo pogoja.
- Če je starost med 18 in 65, izpiši "Delovno aktiven".
- Kombiniraj `&&` in `||` za več pogojev.
- Preveri, ali znak **ni** črka.
- Ali je vsaj eden izmed dveh nizov prazen?
- Funkcija, ki vrne `true`, če je število pozitivno in sodo.
- Logični pogoji z `Boolean` spremenljivkami.
- Razloži `if (!(a > b))` in preizkusi.

---

## 🔹 3. Pogojne stavke in zanke

- Napiši `if-else`, ki preveri, ali je uporabnik polnoleten.
- Uporabi `when` za izpis dneva v tednu glede na številko.
- Preveri, ali je številka pozitivna, negativna ali 0.
- `for` zanka, ki izpiše števila od 1 do 10.
- `while` zanka, ki šteje do 5.
- Seštej vsa števila med 1 in 100.
- Program, ki prešteje števila deljiva s 3 v danem intervalu.
- Uporabi `break` za prekinitev zanke pri določenem pogoju.
- Uporabi `continue` za preskok določenih vrednosti v zanki.
- Pretvori `for-each` zanko za seznam imen.

---

## 🔹 4. Funkcije (metode, podprogrami)

- Funkcija `pozdravi(ime: String)`, ki izpiše "Pozdravljen, ime".
- Funkcija, ki vrne kvadrat števila.
- Funkcija, ki izračuna obseg kroga.
- Funkcija, ki vrne `true`, če je število sodo.
- Funkcija z dvema parametroma, ki vrne večjega.
- Funkcija, ki vrne največje število izmed treh.
- Funkcija brez parametrov, ki vrne naključno število.
- Funkcija, ki združi dva niza.
- Funkcija z neobveznim parametrom (npr. `pozdrav("Ana", ura = 10)`).

---

## 🔹 5. Razredi in podatkovni razredi

- Ustvari razred `Oseba` z `imenom` in `starostjo`.
- Dodaj metodo `predstaviSe()`.
- Ustvari tri osebe z različnimi podatki in izpiši.
- Dodaj `toString()` in testiraj izpis.

---

## 🔹 6. Polja (listi, seznami)

- Ustvari `List` celih števil.
- Dodaj element v `mutableListOf`.
- Odstrani element iz seznama.
- Poišči največje število v seznamu.
- Uporabi `forEach` za izpis vseh elementov.
- Filtriraj seznam (npr. samo sode številke).
- Sortiraj seznam števil naraščajoče.
- Uporabi `contains()` za preverjanje, ali seznam vsebuje element.
- Pretvori seznam `String` v en `String` z vejico (`joinToString()`).

---

## 🔹 Projekt: Nakupovalni seznam

Izdelaj program, ki bo hranil nakupovalni seznam.

Seznam je sestavljen iz **polja objektov `Artikel`**.

**Razred `Artikel`** ima:
- `naziv` (String)
- `vKosarici` (Boolean) – ali je artikel že v košarici

**Zahteve:**
- Razred naj ima konstruktor in potrebne metode.
- Izdelaj uporabniški vmesnik za:
  - Dodajanje na seznam
  - Brisanje iz seznama
  - Spreminjanje naziva artikla
  - Izpis seznama
  - Določanje, če je artikel že v košarici
- Lahko narediš še **wrapper razred**, ki bo imel metode za dodajanje, brisanje, spreminjanje itd.

**Primer uporabniškega menija:**
```
Kaj želite narediti?
1 - Dodaj artikel
2 - Izbriši artikel
3 - Spremeni naziv artikla
4 - Nastavi ali je artikel v košarici
5 - Izpiši seznam
6 - Izhod
Izbira: 1
...
```
