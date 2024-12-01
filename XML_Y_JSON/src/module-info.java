/**
 * 
 */
/**
 * 
 */
module XML_Y_JSON {
	requires xstream;
	requires gson;
	requires java.sql;
	
	requires jettison;
	requires java.xml;
	
	opens xml_json to xstream;
    opens tema2.ejercicio1 to xstream;
    opens tema2.ejercicio2 to xstream;
    opens tema2.ejercicio4 to xstream;
    opens tema2.ejercicio5 to xstream;
    opens tema2.ejercicio6 to xstream;
    opens tema2.ejercicio7 to xstream;
    opens tema2.ejercicio8 to xstream;
    opens tema2_ejercicios2.ejercicio1 to xstream;
    opens pruebasJson to xstream, gson;
    exports pruebasJson;
	exports xml_json;
}
