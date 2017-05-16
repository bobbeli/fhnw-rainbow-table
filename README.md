# fhnw-rainbow-table
Programmieraufgabe Kryptographie und Informationssicherheit

Erstellen Sie eine Rainbow-Table fuer MD5 ausgehend von den ersten 2.000 Passwortern der
Laenge 7 bestehend aus Kleinbuchstaben und Ziern. (Also von 0000000; : : : 0000009; 000000a,
000000b : : : ; 000000z; 0000010; : : :). Die Kettenlaenge soll 2000 betragen, es soll also jeweils 2000
mal die Hashfunktion und die entsprechende Reduktionsfunktion anwendet werden. Verwenden
Sie als Reduktionsfunktion die auf Folie 3.26 angegebene Konstruktion mit der Menge Z =
f0; 1; : : : ; 9; a; b; : : : ; zg.
Ermitteln Sie damit den Klartext zu dem in Hexadezimal-Schreibweise angegebenen Hashwert
1d56a37fb6b08aa709fe90e12ca59e12 oder begruenden Sie, dass dies mit der zu konstruierenden
Rainbow-Table nicht moglich ist.
