package inca.jesus.trajesya.Clases;

import java.util.ArrayList;
import java.util.List;

import inca.jesus.trajesya.R;

/**
 * Created by Jesus on 21/06/2017.
 */

public class Segmento_SubCategorias {
    public int id;
    public int id_subcategoria;
    public int id_categoria;
    private String nombre;
    private int idDrawable;

    public static final List<Segmento_SubCategorias> SUBCATEGORIA_SUB = new ArrayList<Segmento_SubCategorias>();

    static{
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(1,1,1,"4K Ultra HDTVs", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(2,1,1,"Smart TVs", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(3,1,1,"LED TVs", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(4,1,1,"LCD TVs", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(5,1,1,"3D TVs", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(6,1,1,"TV Curvo", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(7,1,1,"OLED TVs", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(8,1,1,"SUHD", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(9,1,1,"TV Plasma", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(10,1,1,"Accesorios TV", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(11,1,1,"DVD & Blu-ray", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(12,1,1,"Proyectores", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(13,1,1,"Monitores", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(14,1,1,"Streaming", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(15,2,1,"Home Theater", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(16,2,1,"Equipos de Sonido", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(17,2,1,"Audífonos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(18,2,1,"Parlantes Y Docks", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(19,2,1,"Autoradios y parlantes", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(20,2,1,"Accesorios de Audio", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(21,2,1,"Reproductores MP3 y MP4", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(22,2,1,"Radios", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(23,2,1,"Sound Bar", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(24,2,1,"Radiograbadoras y Despertadores", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(25,2,1,"Audio Profesional", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(26,2,1,"Micrófonos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(27,2,1,"Tornamesas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(28,2,1,"Karaoke", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(29,3,1,"PlayStation", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(30,3,1,"Nintendo", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(31,3,1,"Xbox", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(32,3,1,"PC", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(33,3,1,"Videojuegos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(34,3,1,"Accesorios", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(35,3,1,"Lectores Electrónicos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(36,3,1,"Control Remoto", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(37,4,1,"Cámaras Fotográficas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(38,4,1,"Videocamaras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(39,4,1,"Cámaras Semi y Profesionales", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(40,4,1,"Accesorios Cámaras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(41,4,1,"Cámaras de Acción", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(42,4,1,"Telescopios y Binoculares", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(43,4,1,"Drones", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(44,5,1,"Kits de seguridad", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(45,5,1,"Cámaras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(46,5,1,"Videoporteros", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(47,5,1,"Accesorios de Espionaje", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(48,5,1,"Alarmas de seguridad", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(49,5,1,"Sensores de movimiento", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(50,6,1,"Teléfonos Alámbricos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(51,6,1,"Teléfonos Inalámbricos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(52,6,1,"Equipos de Conferencia", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(53,6,1,"Centrales Telefóncas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(54,6,1,"Teléfonos IP", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(55,6,1,"Radio", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(56,6,1,"Adaptadores VoIP", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(57,6,1,"Accesorios", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(58,7,1,"GPS", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(59,7,1,"Lectores Electrónicos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(60,7,1,"Calculadoras", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(61,8,2,"Laptops", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(62,8,2,"Noteboks", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(63,8,2,"Netbooks", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(64,8,2,"Gamers", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(65,8,2,"2 en 1", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(66,9,2,"All in One", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(67,9,2,"Torre", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(68,9,2,"Mini", R.drawable.cat_3));


        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(69,10,2,"Teclados", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(70,10,2,"Cargadores", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(71,10,2,"Mouse", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(72,10,2,"Mochilas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(73,10,2,"Parlantes", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(74,10,2,"Fundas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(75,10,2,"Cables y Adaptadores", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(76,10,2,"WebCam", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(77,10,2,"Monitores y accesorios", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(78,10,2,"Micrófonos y Headset", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(79,10,2,"Coolers", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(80,10,2,"Lectoras y Grabadoras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(81,10,2,"USB Hub", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(82,10,2,"Mantenimiento", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(83,10,2,"UPS y Estabilizadores", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(84,10,2,"Almacenamiento", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(85,10,2,"Lectoras y Quemadores", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(86,10,2,"Otros", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(87,11,2,"Impresoras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(88,11,2,"Multifuncionales", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(89,11,2,"Escaners", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(90,11,2,"Tintas y toner", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(91,11,2,"Accesorios", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(92,11,2,"Impresoras 3D", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(93,12,2,"Routers", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(94,12,2,"Access Points, Hubs & Switches", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(95,12,2,"Modems", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(96,12,2,"Servidores", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(97,12,2,"Repetidores y extensores de rango", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(98,12,2,"Almacenamiento", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(99,12,2,"USB Inalambrico", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(100,12,2,"Cables y accesorios", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(101,12,2,"Firewall", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(102,13,2,"Antivirus", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(103,13,2,"Sistema Operativo", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(104,13,2,"Programas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(105,13,2,"Juegos", R.drawable.cat_3));


        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(106,14,3,"Desbloqueados", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(107,14,3,"Prepago", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(108,14,3,"Postpago", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(109,16,3,"Fundas y Carcasas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(110,16,3,"Selfie Stick", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(111,16,3,"Cargadores", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(112,16,3,"Cables", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(113,16,3,"Audifonos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(114,16,3,"Batería externa", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(115,16,3,"Bluetooth", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(116,16,3,"Protectores de pantalla", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(117,16,3,"Teclados", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(118,16,3,"Lapiz", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(119,16,3,"Adaptadores", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(120,16,3,"Soporte", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(121,16,3,"Otros", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(122,17,3,"Smartwaches", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(123,17,3,"Accesorios Smartwaches", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(124,17,3,"Realidad Virtual", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(125,17,3,"Otros", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(126,18,4,"Refrigeradores", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(127,18,4,"Frigobares", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(128,18,4,"Vineras y cavas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(129,18,4,"Congeladoras", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(130,19,4,"Cocinas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(131,19,4,"Hornos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(132,19,4,"Empotrables", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(133,19,4,"Campanas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(134,19,4,"Cocinas + campanas", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(135,20,4,"Lavadoras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(136,20,4,"Secadoras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(137,20,4,"Centros de lavado", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(138,20,4,"Lavasecas", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(139,21,4,"Ventiladores", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(140,21,4,"Aire acondicionado", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(141,21,4,"Termas y Rapiduchas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(142,21,4,"Deshumedecedores y purificadores", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(143,21,4,"Estufas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(144,21,4,"Ambientadores", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(145,22,4,"Licuadoras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(146,22,4,"Microondas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(147,22,4,"Ollas arroceras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(148,22,4,"Aspiradoras y lustradoras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(149,22,4,"Planchas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(150,22,4,"Batidoras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(151,22,4,"Hervidores", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(152,22,4,"Cafeteras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(153,22,4,"Tostadoras y grill", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(154,22,4,"Extractores y exprimidores", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(155,22,4,"Cafeteras eléctricas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(156,22,4,"Hervidores eléctricos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(157,22,4,"Máquinas de Coser", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(158,22,4,"Parrillas eléctricas y freidoras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(159,22,4,"Picadoras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(160,22,4,"Sandwicheras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(161,22,4,"Ambientadores", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(162,23,4,"Afeitadoras y cortadoras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(163,23,4,"Alisadoras y rizadores", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(164,23,4,"Secadoras de cabello", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(165,23,4,"Depiladoras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(166,23,4,"Balanzas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(167,23,4,"Salud y Bienestar", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(168,24,5,"Juegos de Olla", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(169,24,5,"Ollas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(170,24,5,"Sartenes y Woks", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(171,24,5,"Cuchillos y Tablas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(172,24,5,"Vajillas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(173,24,5,"Teteras, Termos y Tomatodos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(174,24,5,"Tapers y envases", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(175,24,5,"Repostería", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(176,24,5,"Accesorios y Utensilios de Cocina", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(177,24,5,"Organizadores de Cocina", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(178,24,5,"Canisters y Contenedores", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(179,24,5,"Textil de Mesa y Cocina", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(180,24,5,"Grifería Cocina", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(181,24,5,"Lavaderos Cocina", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(182,25,5,"Accesorios de Bar", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(183,25,5,"Bandeja y Fuentes", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(184,25,5,"Cubiertos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(185,25,5,"Juegos de Vajilla", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(186,25,5,"Vajilla Suelta", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(187,25,5,"Vasos, Copas y Jarras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(188,25,5,"Mantelería y Accesorios", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(189,25,5,"Decoración", R.drawable.cat_3));


        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(190,26,5,"Parrillas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(191,26,5,"Cajas Chinas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(192,26,5,"Cilindros", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(193,26,5,"Utensilios", R.drawable.cat_3));


        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(194,27,5,"Sanitarios", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(195,27,5,"Tinas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(196,27,5,"Extractores de aire", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(197,27,5,"Termas y Calentadores", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(198,27,5,"Decoración Baños", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(199,27,5,"Duchas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(200,27,5,"Organizadores de Baño", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(201,27,5,"Espejos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(202,27,5,"Seguridad Baño", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(203,27,5,"Grifería Baños", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(204,28,5,"Artículos Decorativos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(205,28,5,"Cuadros", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(206,28,5,"Biombos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(207,28,5,"Marcos de Foto", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(208,28,5,"Velas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(209,28,5,"Alfombras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(210,28,5,"Felpudos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(211,28,5,"Antideslizantes para Alfombras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(212,28,5,"Cortinas y Persianas", R.drawable.cat_3));


        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(213,29,5,"Iluminación Interior", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(214,29,5,"Iluminación para Niños", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(215,29,5,"Iluminación Exterior", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(216,29,5,"Iluminación Comercial", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(217,29,5,"Focos, Fluorescentes y Leds", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(218,29,5,"Ventiladores De Techo", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(219,29,5,"Repuestos y accesorios ventiladores", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(220,30,5,"Productos de Limpieza", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(221,30,5,"Utensilios de Aseo", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(222,30,5,"Lavandería", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(223,30,5,"Dispensadores", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(224,30,5,"Contenedores y Basureros", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(225,30,5,"Detergentes y Suavizantes", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(226,30,5,"Artículos de embalaje", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(227,31,5,"Herramientas Eléctricas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(228,31,5,"Accesorios de Herramientas Eléctricas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(229,31,5,"Herramientas Manuales", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(230,31,5,"Maquinarias Especializadas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(231,31,5,"Cajas de Herramientas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(232,31,5,"Seguridad", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(233,31,5,"Herramientas para Construcción", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(234,31,5,"Maquinaria y Herram. Jardín", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(235,31,5,"Otros", R.drawable.cat_3));


        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(236,32,6,"Juegos de Sala", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(237,32,6,"Seccionales", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(238,32,6,"Sofás", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(239,32,6,"Sillones y Reclinables", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(240,32,6,"Mesas de Centro", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(241,32,6,"Puffs", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(242,32,6,"Sofa cama y futones", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(243,32,6,"Centros de Entretenimiento y TV", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(244,32,6,"Estantes y Libreros", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(245,33,6,"Juegos de comedor", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(246,33,6,"Mesas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(247,33,6,"Sillas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(248,33,6,"Comedores de Cocina", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(249,33,6,"Vitrinas, Consolas y Espejos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(250,33,6,"Muebles de Bar", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(251,33,6,"Taburetes, bancos y sillas de bar", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(252,34,6,"Accesorios Cocina", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(253,34,6,"Alacenas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(254,34,6,"Cocinas Integrales", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(255,35,6," Cómodas y Tocadores", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(256,35,6," Roperos ", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(257,35,6," Cabeceras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(258,35,6," Veladores", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(259,35,6," Zapatera", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(260,36,6," Escritorios", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(261,36,6," Sillas De Oficina", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(262,36,6," Archiveros", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(263,36,6," Mesas de Oficina", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(264,36,6," Estantes", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(265,37,6,"Juegos de Sala", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(266,37,6,"Seccionales", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(267,37,6,"Sofás", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(268,37,6,"Sillones y Reclinables", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(269,37,6,"Mesas de Centro", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(270,37,6,"Puffs", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(271,37,6,"Pergolas y Quitasoles", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(272,38,6,"Centros de entretenimiento", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(273,38,6,"Estantes y Libreros", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(274,38,6,"Mesas para Tv", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(275,38,6,"Repisas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(276,38,6,"Reclinables", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(277,38,6,"Roperos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(278,38,6,"Racks", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(279,38,6,"Muebles de Baño", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(280,38,6,"Muebles de cocina", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(281,39,7,"1 Plaza", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(282,39,7,"1,5 Plazas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(283,39,7,"2 Plazas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(284,39,7,"Queen", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(285,39,7,"King", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(286,40,7,"1 Plaza", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(287,40,7,"1,5 Plazas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(288,40,7,"2 Plazas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(289,40,7,"Queen", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(290,40,7,"King", R.drawable.cat_3));


        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(291,41,7,"1 Plaza", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(292,41,7,"1,5 Plazas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(293,41,7,"2 Plazas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(294,41,7,"Queen", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(295,41,7,"King", R.drawable.cat_3));



        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(296,42,7,"1 Plaza", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(297,42,7,"1,5 Plazas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(298,42,7,"2 Plazas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(299,42,7,"Queen", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(300,42,7,"King", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(301,43,7,"Muebles de Dormitorio", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(302,43,7,"Camarotes", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(303,43,7,"Divanes", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(304,43,7,"Boxets", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(305,43,7,"Cómodas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(306,43,7,"Veladores", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(307,43,7,"Cómodas y Tocadores", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(308,43,7,"Roperos ", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(309,43,7,"Cabeceras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(310,43,7,"Zapatera", R.drawable.cat_3));


        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(311,44,7,"Muebles de Dormitorio", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(312,44,7,"Camarotes", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(313,44,7,"Cómodas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(314,44,7,"Veladores", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(315,44,7,"Cómodas y Tocadores", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(316,44,7,"Cabeceras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(317,44,7,"Cunas y Camas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(318,44,7,"Colchones", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(319,44,7,"Ropa de Cama", R.drawable.cat_3));


        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(320,45,7,"Sábanas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(321,45,7,"Frazadas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(322,45,7,"Mantas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(323,45,7,"Almohadas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(324,45,7,"Fundas de almohada", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(325,45,7,"Cojines y Complementos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(326,45,7,"Cubrecamas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(327,45,7,"Edredones", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(328,45,7,"Ropa de Cama Niños", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(329,45,7,"Juegos de Cama", R.drawable.cat_3));


        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(330,46,7,"Accesorios de Baño", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(331,46,7,"Cortinas de Baño", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(332,46,7,"Pisos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(333,46,7,"Toallas de Playa", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(334,46,7,"Toallas de Baño", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(335,47,8,"Polos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(336,47,8,"Poleras y Chompas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(337,47,8,"Blazers y Abrigos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(338,47,8,"Casacas y Chalecos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(339,47,8,"Tops", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(340,47,8,"Blusas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(341,47,8,"Vestidos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(342,47,8,"Faldas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(343,47,8,"Jeans", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(344,47,8,"Pantalones y Leggings", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(345,47,8,"Shorts", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(346,47,8,"Bikini y Ropa de Baño", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(347,47,8,"Enterizos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(348,47,8,"Ropa Interior", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(349,47,8,"Pijama", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(350,47,8,"Ropa deportiva", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(351,47,8,"Bufandas y Pashminas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(352,47,8,"Cinturones", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(353,47,8,"Sombreros", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(354,48,8,"Polos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(355,48,8,"Camisas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(356,48,8,"Poleras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(357,48,8,"Blazers y Abrigos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(358,48,8,"Chompas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(359,48,8,"Pantalones", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(360,48,8,"Shorts y bermudas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(361,48,8,"Jeans", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(362,48,8,"Sacos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(363,48,8,"Ternos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(364,48,8,"Ropa Deportiva", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(365,48,8,"Ropa Interior", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(366,48,8,"Pijamas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(367,48,8,"Trajes de Baño", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(368,48,8,"Cinturones", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(369,48,8,"Corbatas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(370,48,8,"Gorros", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(371,49,8,"Zapatos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(372,49,8,"Botas y Botines", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(373,49,8,"Ballerinas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(374,49,8,"Sandalias", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(375,49,8,"Zapatillas Urbanas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(376,49,8,"Zapatillas Deportivas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(377,49,8,"Pantuflas", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(378,50,8,"Calzado Casual", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(379,50,8,"Calzado de Vestir", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(380,50,8,"Botines", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(390,50,8,"Sandalias", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(391,50,8,"Zapatillas Deportivas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(392,50,8,"Zapatillas Urbanas", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(393,51,8,"Relojes Hombre", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(394,51,8,"Relojes Mujer", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(395,51,8,"Lentes Hombre", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(396,51,8,"Lentes mujer", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(397,51,8,"Accesorios Lentes", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(398,52,8,"Carteras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(399,52,8,"Morrales", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(400,52,8,"Billeteras mujer", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(401,52,8,"Billeteras hombre", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(402,52,8,"Monederos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(403,52,8,"Mochilas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(404,52,8,"Maletas de viaje", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(405,52,8,"Maletas deportivas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(406,52,8,"Portafolios y Maletines", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(407,53,8,"Anillos", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(408,53,8,"Anillos de compromiso", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(409,53,8,"Aretes", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(410,53,8,"Collares", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(411,53,8,"Pulseras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(412,53,8,"Joyería fina", R.drawable.cat_3));

        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(413,54,8,"Loncheras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(414,54,8,"Cartucheras", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(415,54,8,"Fundas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(416,54,8,"Canguros", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(417,54,8,"Paraguas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(418,54,8,"Portatarjetas", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(419,54,8,"Llaveros", R.drawable.cat_3));
        SUBCATEGORIA_SUB.add(new Segmento_SubCategorias(420,54,8,"Disfraces", R.drawable.cat_3));


    }



    public Segmento_SubCategorias(int id, int id_subcategoria, int id_categoria, String nombre, int idDrawable) {
        this.id = id;
        this.id_subcategoria = id_subcategoria;
        this.id_categoria = id_categoria;
        this.nombre = nombre;
        this.idDrawable = idDrawable;
    }
    public Segmento_SubCategorias(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_subcategoria() {
        return id_subcategoria;
    }

    public void setId_subcategoria(int id_subcategoria) {
        this.id_subcategoria = id_subcategoria;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(int idDrawable) {
        this.idDrawable = idDrawable;
    }
}
