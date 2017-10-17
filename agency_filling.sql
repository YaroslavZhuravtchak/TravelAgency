
-- tables

-- Table: country-----------------------------------------------------------------------------------------------------------------------------------

insert into country (country_name, country_name_ua) values ('Germany', 'Німеччина');
insert into country (country_name, country_name_ua) values ('England', 'Англія');
insert into country (country_name, country_name_ua) values ('Greece', 'Греція');
insert into country (country_name, country_name_ua) values ('France', 'Франція');
insert into country (country_name, country_name_ua) values ('Czech Republic', 'Чехія');
insert into country (country_name, country_name_ua) values ('Hungary', 'Угорщина');
insert into country (country_name, country_name_ua) values ('Austria', 'Австрія');

insert into country (country_name, country_name_ua) values ('Turkey', 'Туреччина');



-- Table: city-----------------------------------------------------------------------------------------------------------------------------------


insert into city (country_id, city_name, city_name_ua) values ((select id from country where country_name ='Germany'),'Dresden','Дрезден');
insert into city (country_id, city_name, city_name_ua) values ((select id from country where country_name ='Germany'),'Munich','Мюнхен');
insert into city (country_id, city_name, city_name_ua) values ((select id from country where country_name ='Germany'),'Frankfurt','Франкфурт');
insert into city (country_id, city_name, city_name_ua) values ((select id from country where country_name ='France'),'Paris','Париж');
insert into city (country_id, city_name, city_name_ua) values ((select id from country where country_name ='France'),'Cannes','Канни');
insert into city (country_id, city_name, city_name_ua) values ((select id from country where country_name ='France'),'Marseille','Марсель');
insert into city (country_id, city_name, city_name_ua) values ((select id from country where country_name ='Greece'),'Athens','Афіни');
insert into city (country_id, city_name, city_name_ua) values ((select id from country where country_name ='Greece'),'Kastoria','Касторія');
insert into city (country_id, city_name, city_name_ua) values ((select id from country where country_name ='England'),'London','Лондон');
insert into city (country_id, city_name, city_name_ua) values ((select id from country where country_name ='Czech Republic'),'Prague','Прага');
insert into city (country_id, city_name, city_name_ua) values ((select id from country where country_name ='Austria'),'Vienna','Відень');
insert into city (country_id, city_name, city_name_ua) values ((select id from country where country_name ='Hungary'),'Budapest','Будапешт');

insert into city (country_id, city_name, city_name_ua) values ((select id from country where country_name ='Turkey'),'Antalya','Анталія');

insert into city (country_id, city_name, city_name_ua) values ((select id from country where country_name ='Turkey'),'Kemer','Кемер');

insert into city (country_id, city_name, city_name_ua) values ((select id from country where country_name ='Turkey'),'Alania','Аланія');

-- Table: user---------------------------------------------------------------------------------------------------------------------------------------------


insert into user (login, password, salt, firstname, lastname, phone_number, email, role) 
                   values ('Yaroslav','c6d5d79c88d954c6144b9228fbb5fd3debdedd09c0aab042c4b764ad306c4de0', 
                   '57367eb836d0399167421af26ba0188365708b30adf1c6bcb91f9f09c5aae0b4', 'Yaroslav', 'Zhuravchak', 
                   '+380990243001', 'zhuravchak@gmail.com', 'ADMIN');
                   



-- Table: tour-----------------------------------------------------------------------------------------------------------------------------------

insert into tour (city_from, tour_type, transport_type, name, name_ua, description, description_ua, duration, path_image ) 
values('Kyiv', 'SHOPPING', 'PLANE', 'Fur Kastoria', 'Хутро Касторії', ' A more interesting and useful journey than Fur fashion tour is hardly imaginable: 
picturesque landscapes, unique sights, gorgeous cuisine and sincere hospitality of the inhabitants are complemented by centuries-old traditions and modern 
technologies of fur production, wide assortment, affordable prices and high quality fur coats from Greece.\n\tKastoria is the furry capital of Greece. 
The widest assortment of models from all types of fur is presented here - from the popular mink of all colors and styles to rare sable, magnificent chinchilla, 
rice and other coats made right here, in one of the 3000 factories in the city. <br>! Fur Tour to Greece is a unique 
opportunity to buy a fur coat, at prices 40-60% lower than in Ukraine. The choice and quality of fur products can not be compared with what we have.
<br>The cost of a shop-tour includes: air travel, hotel accommodation, medical insurance, transfers under the program of the tour on a comfortable bus with free 
Wi-Fi, meals, guided tour along with a guide, a guide.', 
'Більш цікаву й корисну подорож, ніж Fur fashion tour навряд чи можна собі уявити: мальовничі пейзажі, унікальні памятки, чудова кухня і щира гостинність
 жителів доповнюються багатовіковими традиціями і сучасними технологіями хутрового виробництва, широким асортиментом, прийнятними цінами і високою якістю
 шуб з Греції!<br> Касторія є хутряною столицею Греції, тут представлений найширший асортимент моделей з усіх видів хутра - від популярної норки всіх забарвлень 
 і фасонів до рідкісного соболя, чудової шиншили, рисі та інших шуб, виготовлених тут же, на одній з 3000 фабрик міста.<br> Шуб-тур у Грецію - це унікальна 
 можливість купити шубу, за цінами на 40-60% нижчими ніж у Україні. Вибір та якість хутряних виробів не можна порівняти з тим, що є у нас. <br>У вартість шоп-туру 
 входить: авіапереліт, проживання в готелі, медстраховка, трансфери по програмі туру на комфортабельному автобусі з безкоштовним Wi-Fi, харчування, екскурсія в 
 супроводі гіда під час, путівник.', 
3, "../images/1.jpg");

insert into tour_city (tour_id, city_id) values ((select id from tour where name = 'Fur Kastoria'), 
												   (select id from city where city_name = 'Kastoria')); 


insert into tour (city_from, tour_type, transport_type, name, name_ua, description, description_ua, duration, path_image ) 
values('Kyiv', 'SHOPPING', 'BUS', 'Dresden Market', 'Дрезденський ринок', 'Designer Outlet 
Berlin is a leader in the European retail market, with more than 80 boutiques with hundreds of designer brands, with prices down 30% 
to 70% year-round than manufacturers recommend. Every year, shop tours to Dresden are becoming more popular, so many shoppers come here 
during the sales. As in every German city in Dresden there are also weekly markets. They are in certain places and function one or several 
days a week. Farmers and traders from the region present their goods here. The quality of the products is very good, but they are somewhat
 more expensive than in supermarkets. The largest weekly market in Dresden operates on Fridays in the city center at Lingner-Allee. You 
 should definitely visit this place if you make a shopping tour to Dresden <br> The price of the trip includes, in addition to travel on the 
 specified route round-trip night in the hotel and breakfast. The cost of the tour does not include trips in public transport along Dresden 
 and sightseeing visits to the cultural and historical sites and attractions of this city.', 
 'Designer Outlet Berlin - це лідер європейського роздрібного ринку. Тут розміщенно
понад 80 бутіків, що представлені сотнею дизайнерських марок. Ціни тут ціний рік менші на 30-70% ніж рекомендують виробники. 
З кожним роком шоп тури в Дрезден стають все більш популярними, тому безліч любителів шопінгу зїжджається сюди в період розпродажів. Як 
і в кожному німецькому місті в Дрездені також є щотижневі ринки. Вони знаходяться в певних місцях і функціонують один або кілька днів на 
тиждень. Фермери і торговці з регіону представляють свої товари тут. Якість продуктів дуже гарне, але вони трохи дорожче, ніж в
 супермаркетах. Найбільший щотижневий ринок в Дрездені функціонує по пятницях в центрі міста на Lingner-Allee. Ви повинні обовязково 
 відвідати це місце, якщо робите шоп тур в Дрезден.<br> В ціну поїздки входять крім проїзду по вказаному маршруту в обидва кінці ніч в готелі і сніданок.
У вартість туру не включені поїздки в громадському транспорті по Дрездену і екскурсійні відвідування культурно-історичних місць і памяток цього міста.' ,
3, "../images/2.jpg");


insert into tour_city (tour_id, city_id) values ((select id from tour where name = 'Dresden Market'), 
												   (select id from city where city_name = 'Dresden'));


insert into tour (city_from, tour_type, transport_type, name, name_ua, description, description_ua, duration, path_image ) 
values('Lviv', 'TRIP', 'BUS', 'In Paris for a dream', 'У Парижі за мрією', 'Paris is a city that is always with you ... Why 
does the magic of this city not let go and attract everyone? Maybe because lovers around the world are eager to visit him to 
reinforce and test their feelings? Maybe because all the mods look back at Paris to compare their outfits with Paris? Or gourmets
 prepare their best dishes for French recipes? Apparently all together - the energy of fashion, cuisine, wine, love, perfumery, cars, 
 streets and squares of Paris makes people come, sneeze, go and come back here again. We will visit the island of Sit, with the Cathedral 
 of the Virgin of Paris, located on it, where possible and now the wandering shadow of Quasimodo Victor Hugo, the Palace of Justice - 
 the place of service of an outstanding detective. Let us visit the Latin Quarter: Sorbonne - the world-famous university. Visit 
 the Luxembourg Garden - the most beautiful garden in Paris; see the Arc de Triomphe, the Champs-Elysees known throughout the world 
 and, of course, the refined lace Eiffel Tower.',  
 'Париж - це місто, яке завжди з тобою ... Чому магія цього міста не відпускає і притягує кожного? Можливо, тому, що закохані з усього 
 світу прагнуть відвідати його, щоб посилити і перевірити свої почуття? Можливо, тому, що всі модники озираються на Париж, щоб порівняти
 свої наряди з паризькими? Або гастрономи готують свої найкращі страви за французькими рецептами? Мабуть все разом - енергія моди, кухні, 
 вина, любові, парфумерії, машин, вулиць і площ Парижу змушує людей приїжджати, зітхати, їхати і знову повертатись сюди. Ми оглянемо острів 
 Сіте, із розташованим на ньому Собором Паризької богоматері, де можливо і зараз блукає тінь Квазимодо Віктора Гюго, палац Правосуддя -
 місце служби видатного детектива. Відвідаємо і Латинський квартал: Сорбонну – всесвітньовідомий університет. Завітаємо до Люксембургського 
 саду – найпрекраснішого саду у Парижі; побачимо Тріумфальну Арку, відомі на весь світ Єлисейські поля і, звичайно, – вишукану мереживну
 Ейфелеву вежу.',
3, "../images/3.jpg");

   
 insert into tour_city (tour_id, city_id) values ((select id from tour where name = 'In Paris for a dream'), 
												   (select id from city where city_name = 'Paris'));  


insert into tour (city_from, tour_type, transport_type, name, name_ua, description, description_ua, duration, path_image ) 
values('Kyiv', 'TRIP', 'PLANE', 'To London for the weekend', 'У Лондон на вихідні', 'If this is your first time in London, we invite you to 
plunge into the unforgettable world of London life and learn its traditions. Great Britain is the only country in the world where the ruling 
monarchy has been almost a thousand years old. We offer you a panoramic hiking and car excursion in the heart of one of the oldest capitals of 
Europe. In the center of London, successfully combined royal residences, ancient ruins, old cathedrals and famous skyscrapers.<br>
During the excursion you will see all the major sights of London: The London Eye is one of the largest observation wheels in the world, 
located in Londons Lambeth area on the south bank of the Thames; The Shakespearean Globe Theater, built according to the Elizabethan plan for
 the first theater. The original theater "Globe" was built in 1599; Residence of the Queen, Prime Minister of Great Britain and Lord of Measures
 of London; The Tower is a fortress, a palace, a storehouse of royal jewelry, which for its millennial history was a mint, a prison and an observatory;
and much more… ',  
 'світ лондонського життя, і дізнатися його традиції. Великобританія - це єдина країна в світі, де правлячої монархії вже майже тисяча років. Ми пропонуємо
 Вам оглядову пішохідну та автомобільну екскурсію в самому серці однієї з найстаріших столиць Європи. У центрі Лондона успішно поєднуються королівські 
 резиденції, стародавні руїни, старі собори і знамениті хмарочоси.<br>
Під час екскурсії Ви побачите всі основні визначні памятки Лондона:
Лондонське око - одне з найбільших коліс огляду у світі, розташоване в лондонському районі Ламбет на південному березі Темзи;
Tеатр "Глобус" Шекспіра, побудований за планом єлизаветинського часу на місці першого театру. Початковий театр "Глобус" був побудований в 1599 році;
Pезиденції королеви, премєр-міністра Великобританії і Лорда-мера Лондона;
Тауер - фортеця, палац, сховище королівських коштовностей, який за свою тисячолітню історію був монетним двором, вязницею і обсерваторією;
і багато іншого…',
4, "../images/4.jpg");

insert into tour_city (tour_id, city_id) values ((select id from tour where name = 'To London for the weekend'),
												   (select id from city where city_name = 'London'));  

insert into tour (city_from, tour_type, transport_type, name, name_ua, description, description_ua, duration, path_image ) 
values('KHARKIV', 'TRIP', 'BUS', 'Weekend in Europe!', 'Вікенд в Європі!', 'Prague - is considered one of the most beautiful cities in Europe, 
which for many centuries admires and does not cease to amaze !!! Old Town Square and the Astronomical Clock, the magnificent Church of the
 Virgin Mary in front of Tyn, the legendary Charles Bridge, Wenceslas Square, Karlova Street. <br> Vienna is the aroma of coffee in the famous 
 Vienna coffee shops, the Viennese strudel and the Viennese ball ... Luxurious fiascas, drowning in the greenery of the parks and ... music!
 "At the word Vienna - the heart jubilantly festivities, beats from love more, at the word Vienna - the music of Chopin, and Mozart, and Strauss, 
 and, and, and ...!" <br>Further - you are in Budapest !!! We will spend our free time in the favorite place of walks of the Hungarian nobility -
 on the Korzo waterfront and the fashionable Vatsy Street. They come here not only for shopping, but take a walk, meet with friends and admire
 the view of the Buda Castle. In the evening, when the lights bloom, against the background of the starry sky, the Royal Palace, the Fishermens 
 Bastion and the statue of Bishop Gellert are displayed.',  
 'Прага - вважається одним з найкрасивіших міст Європи, яке багато століть захоплює і не перестає дивувати !!! Староміська площа та Астрономічний 
 годинник, чудовий храм Діви Марії перед Тином, легендарний Карлів міст, Вацлавська площа, Карлова вулиця. <br> Відень - це аромат кави в знаменитих 
 Віденських кавярнях, Віденський штрудель і Віденський бал ... Розкішні фіакри, що потопають у зелені парки і ... музика!
«При слові Відень - серце святково тріумфує, сильніше бється від любові, при слові Відень - музика Шопена, і Моцарта, і Штрауса, і, і, і ...!»
<br> Далі - Ви в Будапешті !!! Ми проведемо вільний час в улюбленому місці прогулянок угорської знаті - на набережній Корзо і фешенебельній вулиці Ваци. 
Сюди приходять не тільки за покупками, а погуляти, зустрітися з друзями і помилуватися видом на Будайську фортеця. Увечері, коли розцвітають вогні, на 
фоні зоряного неба висвічуються Королівський палац, Рибальський бастіон і статуя єпископа Геллерта.',
6 , "../images/5.jpg");
 
insert into tour_city (tour_id, city_id) values ((select id from tour where name = 'Weekend in Europe!'),
												   (select id from city where city_name = 'Prague'));  
                                                   
 insert into tour_city (tour_id, city_id) values ((select id from tour where name = 'Weekend in Europe!'),
												   (select id from city where city_name = 'Vienna'));  
                                                   
     insert into tour_city (tour_id, city_id) values ((select id from tour where name = 'Weekend in Europe!'),
												   (select id from city where city_name = 'Budapest'));  
                                                                                                 
 
 
 
 insert into tour (city_from, tour_type, transport_type, name, name_ua, description, description_ua, duration, path_image ) 
values('KHARKIV', 'VACATION', 'PLANE', 'Rest in Antalya', 'Відпочинок в Анталії', 'Antalya is a big resort town in Turkey with well-developed tourist 
infrastructure. The weather in Antalya is warm and dry almost all year round. The summer season begins in April, and ends in October. This resort 
attracts tourists from all over the world not only with a lot of entertainment, comfortable hotels, gourmet restaurants and local taverns, but also
 a great historical heritage, an extremely beautiful nature, a clean sea. Unfortunately, hotels that do not have their own sandy beach due to their 
 location in the mountainous terrain, therefore offer platforms and a charming view of the rooms. Antalya is Turkeys main tourist destination and one 
 of the most popular and most visited resorts in the world. In addition to the beautiful coastline and the sea itself, it is characterized by a 
 developed infrastructure and impeccable service. Nature gave Antalya a wonderful climate and picturesque landscapes. While locals were able to use 
 these conditions and arrange the city in such a way that Antalyas holiday fulfilled all modern concepts of comfort  and historical monuments could 
 normally coexist with the developed entertainment industry.',  
 
 'Анталія – велике місто-курорт в Туреччині з добре розвинутою інфраструктурою для туристів. Погода в Анталії тепла та суха майже весь рік. Літній 
 сезон розпочинається в квітні, а закінчується в жовтні. Цей курорт привертає увагу туристів з усього світу не тільки масою розваг, комфортабельними 
 готелями, вишуканими ресторанами та місцевими тавернами, а й великою історичною спадщиною, надзвичайно-красивою природою, чистим морем. Нажаль є 
 готелі, які не мають власного піщаного пляжу через своє розташування на гористій місцевості, тому пропонують платформи та чаруючий вид з номерів.
 Анталія - головний туристичний центр Туреччини і один з найпопулярніших та найбільш відвідуваних курортів світу. Крім прекрасної берегової лінії 
 і власне моря, вона відрізняється розвиненою інфраструктурою і бездоганним сервісом.  Природа подарувала Анталії чудовий клімат та мальовничі пейзажі. 
 У той час, як місцеві мешканці змогли використати дані умови та облаштувати місто таким чином, щоб відпочинок в Анталії відповідав всім сучасним 
 уявлення про комфорт, а історичні пам’ятки могли нормально співіснувати з розвинутою індустрією розваг.',
6 , "../images/6.jpg");
 
insert into tour_city (tour_id, city_id) values ((select id from tour where name = 'Rest in Antalya'),
												   (select id from city where city_name = 'Antalya'));  
 
 
            insert into tour (city_from, tour_type, transport_type, name, name_ua, description, description_ua, duration, path_image ) 
values('LVIV', 'VACATION', 'PLANE', 'Kemer''s Beach', 'Кемерський пляж', 'Kemer - a small resort town in Turkey, located between the 
foot of the mountain ridge Taurus and the Mediterranean Sea. The resort is famous for its nature, clean air, a mirror-clear sea, pine forests, 
and a pebble beach. Kemer has well-developed infrastructure, good hotels, restaurants and entertainment. The beach season in Kemer begins in
 May and ends in October. Tours in Kemer will be of great interest to those who love the cosiness and enjoy the nature and beach holiday as 
 such. To do this, there is a mirrorless Mediterranean Sea, a lot of windless gravelplains, a gentle sun and unique air.
  In addition to the lovers of a relaxing beach holiday, the rest in Kemer will also be interesting for active youth, thirsty entertainments,
 and risky divers. After all, there are well-connected quiet and safe beaches and noisy nightclubs. As for land monuments, then their Kemer
 not to borrow: the remains of the ancient cities of Olympos and Phaselis, the amphitheater and the acropolis, the ruins of marketplaces and 
 water pipes, as well as the burial place of Alexander of Macedon.',  
 
 'Кемер – невелике курортне місто Туреччини розташоване між підніжжям гірського хребта Тавр та Середземним морем. Курорт славиться своєю 
 природою, чистим повітрям, дзеркально-чистим морем, сосновими лісами, гальковим пляжем. В Кемері добре розвинута інфраструктура, хороші
 готелі, ресторани та розважальні програми. Пляжний сезон в Кемері починається в травні та закінчується в жовтні. Тури в Кемерпередовсім 
 будуть цікавими тим, хто любить затишок та отримує насолоду від природи та пляжного відпочинку, як таких.  Для цього тут є дзеркально чисте
 Середземне море, безліч безвітряних гальковихпляжів , лагідне сонечко та неповторне повітря.
  Крім любителів спокійного пляжного відпочинку, відпочинок в Кемері також буде цікавим для активної молоді, спраглої розваг, та ризиковим 
  дайверам. Адже тут добре поєднуються спокійні та безпечні пляжі і галасливі нічні клуби. Щодо наземних пам’яток, то і їх Кемеру не позичати: 
  залишки стародавніх міст Олімпоса і Фазеліса, амфітеатр і акрополь, руїни базарних площ і водопроводів, а також місце поховання Олександра
  Македонського.',
7 , "../images/7.jpg");
 
insert into tour_city (tour_id, city_id) values ((select id from tour where name = 'Kemer''s Beach'),
												   (select id from city where city_name = 'Kemer'));  
                                         


  insert into tour (city_from, tour_type, transport_type, name, name_ua, description, description_ua, duration, path_image ) 
values('KYIV', 'VACATION', 'PLANE', 'Marine Alanya', 'Морська Аланія', 'Alania is a resort town on the Mediterranean coast, donated by
 Cleopatra by Mark Antony. The resort is famous for its sandy beaches, mountain fortresses, fish restaurants and a good rest for all tastes
 and budgets. There are a lot of entertainment facilities for young people and families with children, so considering all of the above - Alanya
 is considered one of the most popular resorts in Turkey. Tours in Alanya - a special offer for those who like quality and at the same time 
 inexpensive vacation in Turkey. Alania is the cheapest Turkish resort on the Antalya coast. Yes, despite this, the city is considered one of 
 the most beautiful and most tourist in Turkey. Hotels in Alanya are mostly new and upscale, shops are open 24 hours a day, and this is where
 the true spa life begins here: dozens of night clubs and discos open their doors to foreign tourists. Rest in Alanya will be equally interesting, 
 both for young people and for couples. The sandy beaches of Alanya are perfect for a relaxing holiday with children, and the gentle, calm and 
 shallow sea will be one of the best places to learn to swim.',  
 
 'Аланія – місто-курорт на березі Середземного моря, яке було подароване Клеопатрі Марком Антонієм. Курорт славиться своїми піщаними пляжами,
 гірськими фортецями, рибними ресторанами та хорошим відпочинком на будь-який смак та бюджет. В місті багато розважальних закладів для молоді 
 та сімей з дітьми, тому враховуючи все вище сказане – Аланія вважається одним з найпопулярніших курортів Туреччини. Тури в Аланію– спеціальна 
 пропозиція для тих, хто любить якісний та водночас недорогий відпочинок в Туреччині. Аланія - найдешевший турецький курорт Анталійського побережжя. 
 Та, незважаючи на це, місто вважається одним з найжвавіших та найбільш туристичних в Туреччині. Готелі Аланії здебільшого нові та висококласні, 
 магазини працюють цілодобово, а вночі тут розпочинається справжнє курортне життя: десятки нічних клубів та дискотек відкривають свої двері для 
 іноземних туристів. Відпочинок в Аланії буде однаково цікавим, як молоді, так і сімейним парам. Піщані пляжі Аланії відмінно підійдуть для відпочинку
 з дітьми, а лагідне, спокійне та неглибоке море стане одним з найкращих місць для того, щоб навчитись плавати.',
7 , "../images/8.jpg");
 
insert into tour_city (tour_id, city_id) values ((select id from tour where name = 'Marine Alanya'),
												   (select id from city where city_name = 'Alania'));  
                                         
 insert into tour (city_from, tour_type, transport_type, name, name_ua, description, description_ua, duration, path_image ) 
values('Lviv', 'TRIP', 'BUS', 'In Munich with a dream!', 'В Мюнхен з мрією!', 'The image of Munich is an image of a true Bavarian:
 a romantic and german romantic, with many ancient cathedrals in the Gothic style, castles and palaces of the Middle Ages, elegant 
 and green, with the magnificence of cozy squares, cheerful and cheerful. The heart of the city is the Marienplatz square and the New
 Town Hall. St. Peter''s Cathedral, the Izar Gate, the Cathedral of Frauenkirche - the main cathedral of Bavaria, the building of the 
 National Opera - this is not a complete list of monuments of the Bavarian capital. The BMW Museum is one of the most exciting museums
 in Munich. And it does not matter whether you like a car or not, in this unusual museum it will be interesting to visit everyone! Cars 
 and motorcycles, from the first models to the up-to-date and conceptual BMW of the future. Oktoberfest Museum. To really feel the Oktoberfest, 
 you have to drink a few liters of foam drink, you also need to embrace the historical spirit that lurks in one of the oldest buildings in Munich,
 where the festival museum is now. While studying the exposition you can find out, as, for example, brewed beer monks, the traditions of making beer 
 by the ancient peoples of Mesopotamia, Egypt and the Celtic tribes. ', 
 'Образ Мюнхена - це образ справжнього баварця: по-німецьки романтичний і манірний, з безліччю старовинних соборів в готичному стилі, замків і
 палаців епохи Середньовіччя, ошатний і зелений, з пишнотою затишних скверів, життєрадісний і веселий. Серце міста - площа Марієнплац і Нова Ратуша.
 Собор Святого Петра, Ізарські ворота, собор Фрауенкірхе - головний собор Баварії, будівля Національної опери - ось далеко не повний перелік пам''яток 
 баварської столиці. Музей BMW - один з найбільш захоплюючих музеїв Мюнхена. І не важливо, любите Ви машини чи ні, в цьому незвичайному музеї буде 
 цікаво побувати всім! Автомобілі та мотоцикли, від перших моделей до суперсучасних та концептуальних BMW майбутнього. <Музей Октоберфесту. Щоб
 по-справжньому відчути Октоберфест, мало випити кілька літрів пінного напою, потрібно ще й перейнятися історичним духом, який витає в одній з
 найстаріших будівель Мюнхена, де зараз знаходиться музей фестивалю. Під час вивчення експозиції можна дізнатися, як, наприклад, варили пиво ченці,
 про традиції приготування пива древніми народами Месопотамії, Єгипту і кельтськими племенами. ',
4, "../images/9.jpg");                                        
                                         

insert into tour_city (tour_id, city_id) values ((select id from tour where name = 'In Munich with a dream!'),
												   (select id from city where city_name = 'Munich'));  

-- Table: pass----------------------------------------------------------------------------------------------------------------------------------


insert into pass (tour_id, leaving_date, quantity_available,  price) values (1, '2017-10-25', 18, 330);
insert into pass (tour_id, leaving_date, quantity_available,  price) values (1, '2017-11-16', 25, 320);
insert into pass (tour_id, leaving_date, quantity_available,  price) values (2, '2017-10-05', 20, 370);
insert into pass (tour_id, leaving_date, quantity_available,  price) values (2, '2017-11-27', 27, 350);


												
											
											
