# Stop The Squirrels (Ustavite veverice)

### Dinamiko igre:
* sezona lešnikov je, uporabite košaro, da naberete lešnike, preden jih veverice pograbijo.

**Pozor: v košaro ne dovolite veveric.**

### Mehaniko igre:
* manveri s košaro, prikradanje veverice, pad lešnikov, obiranje lešnikov in konec igre.

### Elemente igre:
* [slika: ozadje](https://icons8.com/photos/photo/bark-of-an-old-tree--5a1e30588b6588000131a119) (dodano 09.10.2021)
* [slika: košara](https://www.flaticon.com/premium-icon/picnic-basket_1135718?term=basket&page=1&position=2&page=1&position=2&related_id=1135718&origin=search) (dodano 09.10.2021)
* [slika: veverica](https://www.flaticon.com/free-icon/squirrel_1864534?term=squirrel&page=1&position=1&page=1&position=1&related_id=1864534&origin=search) (dodano 09.10.2021)
* [slika: lešnik](https://www.flaticon.com/free-icon/hazelnut_3439365?term=hazelnut&page=1&position=19&page=1&position=19&related_id=3439365&origin=tag) (dodano 09.10.2021)
* pisavo: rezultat (število lešnikov v košari, dodano 09.10.2021)
* [zvok: lešnik](https://freesound.org/s/344518/) (dodano 09.10.2021) in
* [zvok: konec](https://freesound.org/s/519986/) (dodano 09.10.2021).

**Opozorilo: Sredstva so bila na določene datume dodana in na voljo na spletu na navedenih povezavah. Žal mi je, če pri prihodnjem dostopu do njih pride do nevšečnosti.**

### Struktura projekta:
###### StopTheSquirrels (implementacija v strukturirano programiranje):
* StopTheSquirrels.java (glavni razred) vsebuje celotno implementacijo igre.
* DesktopLauncher.java (razred zaslona) vsebuje osnovne nastavitve okna igre.

###### StopTheSquirrelsV2 (implementacija v objektno usmerjeno programiranje):
* StopTheSquirrelsV2.java (glavni razred) vstopna točka v igri.
* DesktopLauncherV2.java (razred zaslona) vsebuje osnovne nastavitve okna igre.
* Assets.java - razred za upodabljanje in odstranjevanje elementi igre.
* Background.java - razred za risanje ozadja.
* Basket.java - razred za risanje košare in premikanje košare.
* DynamicGameObject.java - abstraktni razred, ki ga podedujejo razredi: košara, veverica in lešnik.
* End.java - razred označuje, da je igre konec.
* GameObject.java - abstraktni razred, iz katerega podedujejo vsi razredi, razen glavnega razreda in razreda zaganjalnika. 
* Hazelnut.java - razred za inicializacijo in risanje lešnikov.
* Score.java - razred za posodobitev in risanje rezultata.
* Squirrel.java - razred za inicializacijo in risanje veveric.

### Koristne povezave:
* [Brezplačno spreminjanje velikosti slike.](https://resizeimage.net/)
* [Brezplačna pretvorba datotek na spletu.](https://www.freeconvert.com/)

### Faze razvoja iger:
![osnutek](images/draft.png?raw=true "Osnutek igre.")
###### Slika 1: Osnutek igre.

![pregledSP](images/previewSP.PNG?raw=true "Pregled implementacij v strukturirano programiranje.")
###### Slika 2: Pregled implementacij v strukturirano programiranje.

![pregledOOP](images/previewOOP.PNG?raw=true "Pregled implementacij v objektno usmerjenem programiranju.")
###### Slika 3: Pregled implementacij v objektno usmerjenem programiranju.