package com.example.location.dummy;

import com.example.location.R;
import com.example.location.model.Imagen;

import java.util.ArrayList;
import java.util.List;

public class ImagenesLocales {
    List<Imagen> imagenList = new ArrayList<>();

    public ImagenesLocales(int type) {
        if (type == 1) {
            //                      indice,Nombre,          url(Modelo en carpetas),          Desc en pantalla,        grupo al que pertenece,     1=no esta en favoritos,desc para cuando se ve el ar
            imagenList.add(new Imagen(1, "Carnotaurus", "dinosaur/carnotaurus.sfb", "Carnotaurus sastrei", 1, R.drawable.d_carnotaurus, 1, "Theropoda vivió en América del Sur a finales del Cretácico, hace entre 72 y 69,9 millones de años"));
            imagenList.add(new Imagen(2, "Ceratosaurus", "dinosaur/ceratosaurus.sfb", "Terópodo ceratosáurido", 1, R.drawable.d_ceratosaurus, 1, "Gran carnívoro del Jurásico tardío (Kimmeridgia a Tithonia), descubierto en la Formación Morrison en América del Norte, y la Formación Lourinhian en Portugal. [1] Tiene grandes mandíbulas, dientes en forma de cuchilla y un gran cuerno en el hocico. Extremidades delanteras fuertes pero bastante cortas"));
            imagenList.add(new Imagen(3, "Cryolophosaurus", "dinosaur/cryolophosaurus.sfb", "Cryolophosaurus ellioti", 1, R.drawable.d_cryolophosaurus, 1,"Cryolophosaurus ellioti, vivió en el período Jurásico temprano en lo que hoy es la Antártida. Mide unos 6,5 metros (21 pies) de largo y pesa 465 kilogramos (1.025 libras), lo que lo convierte en uno de los Therapodas más grandes de su tiempo. Dado que el único espécimen parece inmaduro, el Cryolophosaurus probablemente sea aún más grande."));
            imagenList.add(new Imagen(4, "Diabloceratops", "dinosaur/diabloceratops.sfb", "Ornitisquio ceratópsido", 1, R.drawable.d_diabloceratops, 1,"dinosaurio extinto de la familia Ceratopsidae vivió hace unos 79 millones de años durante el período Cretácico Tardío en lo que hoy es Utah, EE. UU. [1] Los diabloceratops son de tamaño mediano, vegetativos, de cuatro patas y tienen una longitud "));
            imagenList.add(new Imagen(5, "Diplodocus", "dinosaur/diplodocus.sfb", "saurópodo diplodócido", 1, R.drawable.d_diplodocus, 1,"Un GOocus (/ dɪˈplɒdəkəs /, [1] [2] / daɪˈplɒdəkəs /, [2] o / ˌdɪploʊˈdoʊkəs / [1]) es un género de dinosaurios de la subfamilia Sauropoda y la familia GOocidae, que vive en el noroeste actual Período jurásico de América. El Trinidad Focus es uno de los fósiles de dinosaurios más comunes que se encuentran en las capas media y superior de la Formación Morrison, hace aproximadamente 154 a 152 millones de años, durante el período Kimmeridgiano tardío."));
            imagenList.add(new Imagen(6, "Gorgosaurus", "dinosaur/gorgosaurus.sfb", " Daspletosaurus", 1, R.drawable.d_gorgosaurus, 1,"Gorgosaurus (/ ˌɡɔːrɡ [entrada inválida: 'ɵ'] ˈsɔːrəs / GOR-go-SOR-əs) es un género de mamuts de la familia Tyrannosauridae que vivió en el período Cretácico tardío en lo que hoy es el oeste de América del Norte, que va desde 76,6 a 75,1 millones de años."));
            imagenList.add(new Imagen(7, "Skeletondiplodocus", "dinosaur/skeletondiplodocus.sfb", "saurópodo diplodócido", 1, R.drawable.s_diplodocus, 1));
            imagenList.add(new Imagen(8, "Skeletonteratophoneus", "dinosaur/skeletonteratophoneus.sfb", "dinosaurio terópodo tiranosáurid", 1, R.drawable.s_teratophoneus, 1));
            imagenList.add(new Imagen(9, "Spinosaurus", "dinosaur/spinosaurus.sfb", "Sigilmassasaurus", 1, R.drawable.d_spinosaurus, 1,"Spinosaurus (que significa \"lagarto espinoso\") es un género de dinosaurios carnívoros que habita en el norte de África, que vivió en los períodos Alba y Cenoman del período Cretácico, hace alrededor de 112 [1] -97 [2] millones de años. Este género se conoció por primera vez a partir de un fósil en Egipto en 1912 y fue descrito por el paleontólogo alemán Ernst Stromer en 1915. El fósil original fue destruido en la Segunda Guerra Mundial, pero los datos adicionales se han anunciado en los últimos "));
            //pez
            imagenList.add(new Imagen(10, "Angel", "fish/Angle1/AngelFish3.gltf", "Pterophyllum scalare", 2, R.drawable.angel1, 1,"Popularmente se le conoce como escalar, escalare o pez ángel"));
            imagenList.add(new Imagen(11, "Angel Negro", "fish/AngelFish/AngelFish.gltf", "Pterophyllum sp", 2, R.drawable.angelfish, 1," Es una especie de pez de agua dulce perteneciente a la familia de los cíclidos."));
            imagenList.add(new Imagen(12, "Angler", "fish/Angler/Anglerfish.gltf", "Lophiiformes", 2, R.drawable.pezangler, 1,"Los Lophiiformes son un orden de peces teleósteos de enorme boca, piel desnuda, aletas carentes de espinas, y aletas pectorales sostenidas por un corto brazo"));
            imagenList.add(new Imagen(13, "Pez Payaso", "fish/ClownFish/ClownFish.gltf", "Amphiprioninae", 2, R.drawable.clown_fish, 1,"Treinta especies son reconocidas: una en el género Premnas,mientras que las restantes están en el género Amphiprion. En la naturaleza, todos forman mutualismos simbióticos con anémonas de mar."));
            imagenList.add(new Imagen(14, "Atun", "fish/fish/Fish.gltf", "Thunnus", 2, R.drawable.fish, 1,"El grupo Thunnus es un género de peces óseos marinos con menos de diez especies incluidas en él. "));
            imagenList.add(new Imagen(15, "Carpa Koi", "fish/Koi/Koifish.gltf", "Cyprinus carpio", 2, R.drawable.koi, 1,"Carpa koi  o más específicamente Carpa de brocado es una carpa común (Cyprinus carpio) que ha sido domesticada, criada para uso ornamental en pequeños estanques, criada popularmente en Japón. Están estrechamente relacionados con los peces de colores y, de hecho, el estilo de cría y acuario es bastante similar a la forma en que se crían los peces de colores, probablemente debido al los esfuerzos de los criadores japoneses para cuidarlos"));
            //pájaros
            imagenList.add(new Imagen(16, "Reina de Canada", "bird/Canada/Canada.gltf", "Cardellina canadensis", 3, R.drawable.reina, 1,"La reinita canadiense,  bijirita del Canadá o chipe de collar antes clasificada como Wilsonia canadensis, es una especie de aves del orden de los Passeriformes y la familia de los parúlidos. Anida en Nortemérica e inverna principalmente en el norte de Suramérica."));
            imagenList.add(new Imagen(17, "Flamenco", "bird/Flamingo/Flamingo.gltf", "hoenicopteridae", 3, R.drawable.flamenco, 1,"son un tipo de ave vadeando en la familia Phoenicopteridae,la única familia de aves en el orden Phoenicopteriformes. Cuatro especies de flamencos se distribuyen por todas las Américas, incluido el Caribe, y dos especies son nativas de África, Asia y Europa."));
            imagenList.add(new Imagen(18, "Gaviota", "bird/Seagull/Seagull.gltf", "Laridae", 3, R.drawable.gaviota, 1,"Las gaviotas son un grupo de aves clasificadas dentro del orden Charadriiformes y familia Laridae, pertenecientes al suborden Lari. Están estrechamente relacionados con los charranes, los cuales eran considerados una subfamilia de las gaviota"));
            //plantas
            imagenList.add(new Imagen(19, "Arbol", "tree/tree/Tree02.gltf", "Quercus", 4, R.drawable.cay, 1,"Un árbol es una planta de gran porte, de tronco único leñoso y que se ramifica a cierta altura del suelo."));
            //Huesos
            imagenList.add(new Imagen(20, "Craneo T-Rex", "bone/Dinosaur/DinosaurSkull.gltf", "Craneo de T-Rex", 5, R.drawable.craneotrex, 1));
            imagenList.add(new Imagen(21, "Espina T-REX", "bone/SpineBase/SpineBase.gltf", "Espina T-REX", 5, R.drawable.espina, 1));
            imagenList.add(new Imagen(22, "Cucaracha Marina", "bone/Trilobite/TrilobiteA.gltf", "Cucaracha Marina", 5, R.drawable.cmarina, 1));

            //Pinturas
            imagenList.add(new Imagen(23, "Van gogh Girasoles", "tranh/vangoghmuseum/vangoghmuseum.gltf", "Girasoles", 6, R.drawable.h1, 1,"Los girasoles son los nombres de dos tipos de pinturas de bodegones pintadas por el pintor holandés Vincent van Gogh. La primera serie, realizada en París en 1887, muestra flores tendidas en el suelo, mientras que la segunda serie, realizada un año después en Arles, muestra un ramo de girasoles en un tarro"));
            imagenList.add(new Imagen(24, "Van gogh La noche Estrellada", "tranh/b2/b2.gltf", "La noche Estrellada", 6, R.drawable.h4, 1,"La noche estrellada es un óleo sobre lienzo del pintor posimpresionista neerlandés Vincent van Gogh . Pintado en junio de 1889"));
            imagenList.add(new Imagen(25, "Van gogh Monalisa", "tranh/b3/b3.gltf", "Monalisa", 6, R.drawable.h3, 1,"Es una pintura de retrato de medio cuerpo del artista italiano Leonardo da Vinci. Considerada una obra maestra arquetípica del Renacimiento italiano, ha sido descrita como la obra de arte más conocida, la más visitada, la más escrita, la más cantada, la obra de arte más parodiada del mundo"));
            imagenList.add(new Imagen(26, "Van gogh El nacimiento de Venus", "tranh/b4/b4.gltf", "El nacimiento de Venus", 6, R.drawable.h2, 1,"es un cuadro realizado por el pintor renacentista Sandro Botticelli, una de las obras cumbre del maestro florentino y del Quattrocento italiano"));
        }


    }

    public List<Imagen> List() {
        return imagenList;
    }
}
