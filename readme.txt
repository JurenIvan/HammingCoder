	Hammingov koder
Ovaj projekt je implementacija Hammingovog kodera.
Rješenje omogućava izračuna proizvoljnog hammingovog koda K zadanog parametrima n i k te proizvoljnog slijeda bitova d.

Program se može pokrenuti putem naredbenog retka na dva načina;
	1)zadajući paramentre n k d kao argumente prilikom pokretanja 
	2)bez zadanih argumenata, no program pri pokretanju očekuje unos tih parametara u konzolu u istom formatu (naprimjer: "15 11 10101010101" (bez navodnika)).

Ulazna točka u programsko rješenje nalazi se u EntryPoint.java.
EntryPoint kreira instancu Hammingovog kodera sa parametrima n i k, nakon toga dolazi do provjere unosa parametara te ukoliko je kreiranje uspješno kreira se logBuilder.
HammingCoder klasa modelirana je imajući na umu maksimalnu ponovnu upotrebljivost pa tako metoda run hammingovog kodera vraća listu int nizova.
Svaki int niz drži jednu kodnu riječ, a lista tih nizova korištena je u slučaju da kodiranjem ulaznog niza nastaje više kodnih riječi.
Instanca HammingCoder-a ima metodu getLogs() koja vraća povijest kodiranja.

Programsko rješenje potkrijepljeno je JUnit testovima koji provjeravaju implementaciju programskog rješenja. 
Dio testova odnosi se na efektivno, no nečitljivo, napisanu metodu za određivanje jeli broj potencija broja 2.
Veći dio testova odnosi se na implementaciju i dokumentaciju implementacije(očekivano ponašanje) Hammingovog kodera.

Projekt se može pokrenuti importanjem u neki moderni IDE(Razvijano i testirano u InteliJ-u),potom je potrebno instalirati Gradle ovisnosti nakon čega se kod i testovi mogu pokrenuti.

Alternativno može se pokrenuti iz naredbenog redka na dva načina:
1)
(pozicioniranje u korijenski folder projekta)
gradlew clean build
java -cp build\libs\HammingCoder-1.0-SNAPSHOT.jar hr.fer.ztel.tinf.hamming.EntryPoint 
15 11 10101010101

2)
(pozicioniranje u korijenski folder projekta)
gradlew clean build
java -cp build\libs\HammingCoder-1.0-SNAPSHOT.jar hr.fer.ztel.tinf.hamming.EntryPoint 15 11 10101010101

Razlika između ta dva načina jest da prvi očekuje upisivanje sa ekrana u toku izvođenja, dok drugi prima parametre kao argumente pomoću kojih se pokreće

Primjer pokretanja i ispisa jest:

C:\Users\Juren\IdeaProjects\HammingCoder>gradlew clean build

BUILD SUCCESSFUL in 2s
5 actionable tasks: 5 executed
C:\Users\Juren\IdeaProjects\HammingCoder>java -cp build\libs\HammingCoder-1.0-SNAPSHOT.jar hr.fer.ztel.tinf.hamming.EntryPoint 15 11 10101010101
[1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1]
R(K)=0.7333333333333333

        __Hamming coder logs__
Created hammingCoder with n = 15, k = 11 .
Requested coding of [1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1].
Runtime checks:
1) Is inputted length in line bits that require coding.
2) Is inputted array consisting of 0's and 1's only.
Checks Passed.
Copying inputted bits into new array.
Original Array :
 [1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1]
1 is exponent of 2 and this place is reserved for parity bit.
2 is exponent of 2 and this place is reserved for parity bit.
4 is exponent of 2 and this place is reserved for parity bit.
8 is exponent of 2 and this place is reserved for parity bit.
Coding array (1-indexed):
 [0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1]
Calculating parity bits.
Calculating parity for position 1
Taking into considuration data at
 pos    value
  1              0
  3              1
  5              0
  7              0
  9              1
 11              1
 13              1
 15              1
Parity bit for position 1 is 1.
Calculating parity for position 2
Taking into considuration data at
 pos    value
  2              0
  3              1
  6              1
  7              0
 10              0
 11              1
 14              0
 15              1
Parity bit for position 2 is 0.
Calculating parity for position 4
Taking into considuration data at
 pos    value
  4              0
  5              0
  6              1
  7              0
 12              0
 13              1
 14              0
 15              1
Parity bit for position 4 is 1.
Calculating parity for position 8
Taking into considuration data at
 pos    value
  8              0
  9              1
 10              0
 11              1
 12              0
 13              1
 14              0
 15              1
Parity bit for position 8 is 0.
Final result is: [1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1]
Requested calculation of R(K), R(K) = 0.7333333333333333

