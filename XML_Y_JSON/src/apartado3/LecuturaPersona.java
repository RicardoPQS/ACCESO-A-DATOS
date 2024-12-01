package apartado3;

import java.io.File;
import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LecuturaPersona {

	public static void main(String[] args) {
		File file = new File("Persona.xml");

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("tema2.ejercicio4.Persona");
			System.out.println("Número de personas: " + nList.getLength());

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("nombre: " + eElement.getElementsByTagName("nombre").item(0).getTextContent());
					System.out.println("apellido: " + eElement.getElementsByTagName("apellido").item(0).getTextContent());
					System.out.println("telefono: " + eElement.getElementsByTagName("telefono").item(0).getTextContent());
					System.out.println("codigo: " + eElement.getElementsByTagName("codigo").item(0).getTextContent());
					System.out.println("numero: " + eElement.getElementsByTagName("numero").item(0).getTextContent());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
