package pms.utils;
import java.io.IOException;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;


public class Catalog {
	public static String tagName = "Catalog";
	
	String category = "";
	private HashMap<String, ResourceInformation> resourceInformationMap = new HashMap<String, ResourceInformation>();
	
	public boolean loadElement(Element catalogElement){
		if(catalogElement == null) return false;
		
		category = "";
		resourceInformationMap.clear();
		
		NodeList childNodeList = catalogElement.getChildNodes();
		for(int i=0; i< childNodeList.getLength(); i++){
			Node childNode = childNodeList.item(i);
			
			if(childNode.getNodeType() == Node.ELEMENT_NODE){
				Element childElement = (Element)childNode;
				if(childElement.getTagName().compareTo("Category") == 0){
					category = childElement.getTextContent();					
				}else if(childElement.getTagName().compareTo(ResourceInformation.tagName) == 0){
					String resourceID = childElement.getAttribute("resourceID");
					ResourceInformation rif = new ResourceInformation();
					if(rif.loadElement(childElement)){
						resourceInformationMap.put(resourceID, rif);
					}
				}
			}
		}
		
		return true;
	}

	public static Catalog load(String url){
		Catalog catalog = new Catalog();
		
		try {
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = docBuilder.parse(url);

			NodeList catalogNodeList = doc.getDocumentElement().getElementsByTagName("Catalog");
			for(int i=0; i < catalogNodeList.getLength(); i++){
				Node catalogNode = catalogNodeList.item(i);
				if(catalogNode.getNodeType() == Node.ELEMENT_NODE){
					catalog.loadElement((Element) catalogNode);
					break;
				}
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SAXException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return catalog;
	}
	
	public QueueInformation getQueueInformation(String resourceID, String priority){
		if(!resourceInformationMap.containsKey(resourceID)){
			return null;
		}
		
		ResourceInformation rif = resourceInformationMap.get(resourceID);
		if(!rif.queueInformationMap.containsKey(priority)){
			return null;
		}
				
		return rif.queueInformationMap.get(priority);
	} 
	
	public ResourceInformation getResourceInformation(String resourceID){
		if(!resourceInformationMap.containsKey(resourceID)){
			return null;
		}
		
		return resourceInformationMap.get(resourceID);
	}
}
