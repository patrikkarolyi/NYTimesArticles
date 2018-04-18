# NYTimesArticles

## Implementálás:
A feladatot igyekeztem részenként megoldani, és az egész kicsivel kevesebb mint az elvárt időt vette igénybe. Rögtön a retrofit használatával kezdtem és a model package-ban lévő POJO osztályok kialakításával. A későbbi feature-öknek nagyvonalakban csináltam külön brach-eket. A bracnh-ek neve egy esetben nem fedi a tartalmat, mert amit room adat kezelővel terveztem megoldani a 'room' bracnh-ben, azt később egy egyszerű gson+sharedpreferences-szel jóval egyszerűbben, és áttekinthetőbben sikerült megvalósítani. A UI szerintem ügyel a felhasználói élmény biztosítására. A projekt során nem használtam olyan technológiát amit ne ismertem volna korábbról, csupán az MVP architektúra jelentett részben újdonságot. A dummy adat összerakásán kívűl egy sor kódban írtam olyat, ami nem feltétlen val jóízlésre, de ennek megtalálását az olvasóra bízom.



## Használt libek:

#### Körképek megjelenítésére:
-picasso
-hdodenhof/circleimageview

#### A REST API használatára és a letöltött tartalom szérializálására:
-retrofit, gson

#### View kezelésre:
-butterknife

#### Snackbar:
-android.design

#### Pull-to-refresh recyclerview:
-recyclerview, SwipeRefreshLayout

## Funkciók:
Az app első indításkor egy általam létrehozott dummy adatokból álló listát tölti be, de amint hozzáfér az internetről származó tartalomhoz, azzal váltja fel. Az első betöltés után a dummy adatok helyett ezek kerülnek tárolásra. Ha nincs internet kapcsolat akkor felugrik egy snackbar ami közli ezt a tényt a felhasználóval. Akkor próbálkozik újra ha vagy a snackbaron megjelenő piros "RETRY" feliratra kattintunk, vagy a lista lehúzásával frissíteni próbáljuk a behozott cikkeket. A jobb oldalon található gombokra kattintva, internet kapcsolat megléte mellett a Chrome böngésző, vagy ha nincs akkor másik tölti be a cikkhez tartozó honlapot, másik esetben ha nincs internet kapcsolat, akkor egy  rázkodó animáció jelenik meg a gombok képein. 

Összefogalalva a funkciók: cikkek betöltése, internet kapcsolat észlelés, honlaok nyitása, pull-to-refresh,perzisztencia 

![alt text](https://i.imgur.com/4ZYiGi2.png "kep1")
![GitHub Logo](https://i.imgur.com/1jaRZcl.png "kep2")
![GitHub Logo](https://i.imgur.com/w94tzAl.png "kep3")



## Futtatás
Én a giten keresztül klónoztam, és a letöltött projektet tudtam telepíteni Android stúdión keresztül. Egyedül az .iml fájlra volt panasza, de az semmin nem változtatott.
