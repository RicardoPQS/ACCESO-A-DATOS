package apartado3;

import java.io.File;
import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LecturaPersistenteXML {

	public static void main(String[] args) {
		File file = new File("xmlPersistente/int@0.xml");

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("xml__json.Coche");
			System.out.println("Número de cafes: " + nList.getLength());

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("matricula: " + eElement.getElementsByTagName("matricula").item(0).getTextContent());
					System.out.println("kilometraje: " + eElement.getElementsByTagName("kilometraje").item(0).getTextContent());
					System.out.println("precio: " + eElement.getElementsByTagName("precio").item(0).getTextContent());
			
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

