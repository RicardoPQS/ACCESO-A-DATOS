package apartado3;

import java.io.File;
import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LecturaCoche {

	public static void main(String[] args) {
		File file = new File("XStream.txt");

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();


			NodeList nList = doc.getElementsByTagName("xml__json.Coche");
			for (int i = 0; i < nList.getLength(); i++) {
				Node node = nList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					if (eElement.hasChildNodes()) {
						NodeList nl = node.getChildNodes();
						for (int j = 0; j < nl.getLength(); j++) {
							Node nd = nl.item(j);
							System.out.println(nd.getTextContent());
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
