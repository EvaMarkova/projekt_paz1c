SELECT * FROM zahradne_sluzby;
select * from reklamne_sluzby;
select * from zakaznici;
select * from polozky_kosika;
select * from kosik;


delete from reklamne_sluzby;
delete from zahradne_sluzby;
SET SQL_SAFE_UPDATES = 0;
use projekt;
delete from zahradne_sluzby;
insert into zahradne_sluzby value(1,"Jar", "Orezávanie stromov, kríkov a kerov", "Vaše stromy môžu vyzerať krásne po celý rok. My sa o to postaráme.", "6.80");
insert into zahradne_sluzby value(2,"Jar", "Príprava pôdy", "Vašu pôdu pripravíme tak, aby sa Vám na nej urodilo, čo najviac úrody.", "4.30");
insert into zahradne_sluzby value(3,"Jar", "Sadenie a sejba", "Môžete sedieť v pohodlí domova, kým my za Vás zasadíme a zasejeme.", "2.50");
insert into zahradne_sluzby value(4, "Jar","Valcovanie pôdy", "Nemáte valec, ktorý by Vám vyvalcoval pôdu? Donesieme ten náš a vyvalcujeme ju za vás.", "1.50");
insert into zahradne_sluzby value(5,"Jar", "Údržba trávnika po zime", "Chcete, aby Váš trávnik bol po zime svieži a hlavne zelený? Prevzdušníme a prehnojíme Vám trávnik.", "3.50");
insert into zahradne_sluzby value(6,"Leto","Polievanie", "Polejeme Vám celú Vašu záhradu.", "2.50");
insert into zahradne_sluzby value(7,"Leto", "Postrekovanie", "Našimi výrobkami Vám môžeme postriekať celú vašu záhradu.", "3.10");
insert into zahradne_sluzby value(8,"Leto", "Okopávanie", "Okopávanie záhrady vlastnými rukami je už minulosť. Nechajte našich pracovníkov spraviť to za Vás!", "2.90");
insert into zahradne_sluzby value(9,"Leto", "Čistenie trávnika od burín", "Nikto nechce na svojej záhrade vidieť burinu. My ju za Vás odstránime.", "3.10");
insert into zahradne_sluzby value(10,"Leto", "Zber úrody", "Nemusíte si špiniť ruky pôdou. My ju za Vás zozbierame, budeme to robiť s radosťou.", "4.50");
insert into zahradne_sluzby value(11,"Leto", "Kosenie trávnika", "Nikomu sa nepáči trávnik, ktorý je až príliš vysoký. My sa Vám oň postaráme, pokosíme ho.", "2.50");
insert into zahradne_sluzby value(12,"Leto", "Zber rastlinného odpadu", "Chcete mať poriadok na Vašej záhrade? My za Vás pozbierame rastlinný odpad. ", "4.80");
insert into zahradne_sluzby value(13,"Leto", "Likvidácia rastlinného odpadu", "Rastlinný odpad sme ochotní za Vás zlikvidovať bez toho, aby ste sa museli namáhať.", "6.50");
insert into zahradne_sluzby value(14,"Jeseň", "Rýľovanie", "Rýľovanie je hĺbkové prekyprenie pôdy po úrodnom období a zároveň príprava pôdy pre ďalšiu sezónu. Rýľovanie je zároveň dôležitým krokom v prevencii proti škodcom v pôde a MY to za Vás spravíme!", "8.20");
insert into zahradne_sluzby value(15,"Jeseň", "Hnojenie prírodným hnojom", "Pohnojíme Vám Vašu pôdu našim 100% prírodným hnojom. ", "4.50");
insert into zahradne_sluzby value(16,"Jeseň", "Hnojenie umelými hnojivami", "Pohnojíme Vám Vašu pôdu umelým hnojivom.", "6.10");
insert into zahradne_sluzby value(17,"Jeseň", "Príprava dreva na zimu", "Narúbeme za Vás drevo, rozštiepime na malé kúsky a pripravíme ho na použitie do krbu.", "7.30");
insert into zahradne_sluzby value(18,"Jeseň", "Sejba ozimín", "Zasejeme za Vás oziminy. Taktiež predtým vhodne pripravíme pôdu na sejbu. ", "2.50");
insert into zahradne_sluzby value(19, "Jeseň", "Zber úrody", "Aj na jeseň môžeme za Vás zozbierať úrodu. Nemusíte si špiniť ruky pôdou. My ju za Vás zozbierame, budeme to robiť s radosťou.", "4.50" );
insert into zahradne_sluzby value(20,"Zima", "Odhadzovanie snehu", "Nasnežilo Vám pred dom a nechce sa Vám ho odhrabať? My ho za Vás odhrabeme.", "8.20");
insert into zahradne_sluzby value(21,"Zima", "Vývoz snehu fúrikom", "Nechcete mať odhrabaný sneh okolo domu? Odvezieme ho za Vás mimo Váš dom.", "8.80");
insert into zahradne_sluzby value(22,"Zima", "Posypovanie chodníkov a prístupových ciest soľou", "Cesty je potrebné udržiavať aj v zime, preto sme ochotní za Vás posypať ho soľou.", "4.50");
insert into zahradne_sluzby value(23,"Zima", "Odstraňovanie cencúľov zo striech a ľadu", "Chceme, aby ste sa mohli bezpečne pohybovať okolo Vášho domu, dovoľte nám odstrániť cencúle z Vašej strechy.", "7.50");
insert into zahradne_sluzby value(24, "Zima","Odstraňovanie ľadu","Túto namáhavú prácu spravíme za Vás. Vy môžte spokojne sedieť v teple Vášho domova.","9.20");

ALTER TABLE zahradne_sluzby AUTO_INCREMENT = 25;

delete from zakaznici;
alter table zakaznici auto_increment = 1;

delete from kosik;
alter table kosik auto_increment = 1;

alter table polozky_kosika auto_increment = 1;

delete from reklamne_sluzby;
ALTER TABLE reklamne_sluzby AUTO_INCREMENT = 11;

insert into reklamne_sluzby values
(1,"Bezfarebná tlač / kopírovanie - A4", "Vytlačíme / Okopírujeme Vám ľubovoľný počet strán o rozmere A4 - čiernobielo.",0.10),
(2,"Bezfarebná tlač / kopírovanie - A3", "Vytlačíme / Okopírujeme Vám ľubovoľný počet strán o rozmere A3 - čiernobielo.",0.18),
(3,"Bezfarebná tlač / kopírovanie - A2", "Vytlačíme / Okopírujeme Vám ľubovoľný počet strán o rozmere A2 - čiernobielo.",0.27),
(4,"Farebná tlač / kopírovanie - A4", "Vytlačíme / Okopírujeme Vám ľubovoľný počet strán o rozmere A4 - farebne.",0.50),
(5,"Farebná tlač / kopírovanie - A3", "Vytlačíme / Okopírujeme Vám ľubovoľný počet strán o rozmere A3 - farebne.",0.90),
(6,"Farebná tlač / kopírovanie - A2", "Vytlačíme / Okopírujeme Vám ľubovoľný počet strán o rozmere A2 - farebne.",1.70),
(7,"Výroba vizitiek (100 kusov v balení) ", "Vizitky majú zvyčajne rozmer 5x9cm, ale my Vám vyrobíme vizitky také, aké chcete vy.",8),
(8,"Výroba vizitiek (200 kusov v balení) ", "Vizitky majú zvyčajne rozmer 5x9cm, ale my Vám vyrobíme vizitky také, aké chcete vy.",15),
(9,"Výroba vizitiek (300 kusov v balení) ", "Vizitky majú zvyčajne rozmer 5x9cm, ale my Vám vyrobíme vizitky také, aké chcete vy.",21),
(10,"Výroba vizitiek (500 kusov v balení) ", "Vizitky majú zvyčajne rozmer 5x9cm, ale my Vám vyrobíme vizitky také, aké chcete vy.",26);

insert into reklamne_sluzby values (10,"Výroba vizitiek (500 kusov v balení) ", "Vizitky majú zvyčajne rozmer 5x9cm, ale my Vám vyrobíme vizitky také, aké chcete vy.",26);
delete from reklamne_sluzby;