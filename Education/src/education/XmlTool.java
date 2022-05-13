
package education;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Xml Tool helps to read and write xml files.
 */
public class XmlTool {
    /**
     * @param filepath gets the xml file, reads it and stores in Student Array List
     * @return Student Array List
     */
    public ArrayList<Student> readUsersFromXml(String filepath) {
        ArrayList<Student> students = new ArrayList<>();
        try {
            DocumentBuilderFactory documentBuilderFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder =
                    documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(filepath);

            Element rootElement = document.getDocumentElement();
            /*System.out.println(rootElement.getNodeName());
            System.out.println(rootElement.getNodeType());
            System.out.println("Element node short value: " + Node.ELEMENT_NODE);
            System.out.println("Text node short value: " + Node.TEXT_NODE);*/
            //System.out.println(rootElement.getTextContent());
            NodeList childNodesList = rootElement.getChildNodes();
            /*System.out.println(childNodesList.getLength());
            System.out.println("---------------");*/
            int numberOfElementNodes = 0;
            Node node;
            for (int i = 0; i < childNodesList.getLength(); i++) {
                node = childNodesList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    //System.out.println(node.getNodeName());
                    //System.out.println(node.getTextContent());
                    numberOfElementNodes++;
                    NodeList childNodesOfUserTag = node.getChildNodes();
                    String name = "", number = "", midterm = "", finalgrade = "";
                    for (int j = 0; j < childNodesOfUserTag.getLength(); j++) {
                        /*System.out.println(childNodesOfUserTag.item(j).getNodeType()
                                  + " " + childNodesOfUserTag.item(j).getNodeName());*/
                        if (childNodesOfUserTag.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            switch (childNodesOfUserTag.item(j).getNodeName()) {
                                case "name" -> name = childNodesOfUserTag.item(j).getTextContent();
                                case "number" -> number = childNodesOfUserTag.item(j).getTextContent();
                                case "midterm" -> midterm = childNodesOfUserTag.item(j).getTextContent();
                                case "final" -> finalgrade = childNodesOfUserTag.item(j).getTextContent();
                            }
                        }
                    }
                    students.add(new Student(name, Double.parseDouble(midterm),Double.parseDouble(finalgrade), 
                            Integer.parseInt(number)));
                }
            }
            //System.out.println("Number of element nodes: " + numberOfElementNodes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }


    /**
     * @param filepath Xml file destination
     * @param tagName Tag name
     * @return Number of occurence from same xml tags
     */
    public int numberOfOccurrence(String filepath, String tagName) {
        int numberOfOccurrence = 0;
        try {
            DocumentBuilderFactory documentBuilderFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder =
                    documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(filepath);
            Element rootElement = document.getDocumentElement();
            NodeList tagNameNodeList = rootElement.getElementsByTagName(tagName);
            numberOfOccurrence = tagNameNodeList.getLength();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberOfOccurrence;
    }


    /**
     * @param students Student Array List
     * @param filepath Xml file destination
     *                 Creates an Xml file and saves everything from the Student Array List
     */
    public void saveUsersToXml(ArrayList<Student> students, String filepath) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element rootElement = document.createElement("students");
            document.appendChild(rootElement);

            for (Student student : students) {
                Element studentElement = document.createElement("student");
                rootElement.appendChild(studentElement);
                createChildElement(document, studentElement, "name", student.getName());
                createChildElement(document, studentElement, "number", String.valueOf(student.getNumber()));
                createChildElement(document, studentElement, "midterm", String.valueOf(student.getMidterm()));
                createChildElement(document, studentElement, "final", String.valueOf(student.getFinal()));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(filepath));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void createChildElement(Document document, Element parent, String tagName, String value) {
        Element element = document.createElement(tagName);
        element.setTextContent(value);
        parent.appendChild(element);
    }
    
}
