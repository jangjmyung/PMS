package pms.utils;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Resource{
	public static String tagName = "Resource";
	
	public String category = "";
	public String resourceID = "";
	public String address = "";
	public String port = "";
	
	boolean loadElement(Element resourceElement){
		if(resourceElement == null) return false;
		
		NodeList childNodeList = resourceElement.getChildNodes();
		for(int i=0; i < childNodeList.getLength(); i++){
			Node childNode = childNodeList.item(i);
			if(childNode.getNodeType() == Node.ELEMENT_NODE){
				Element childElement = (Element)childNode;
				
				if(childElement.getTagName().compareTo("Category") == 0){
					category = childElement.getTextContent();
				}else if(childElement.getTagName().compareTo("ResourceID") == 0){
					resourceID =  childElement.getTextContent();
				}else if(childElement.getTagName().compareTo("Address") == 0){
					address =  childElement.getTextContent();					
				}else if(childElement.getTagName().compareTo("Port") == 0){
					port =  childElement.getTextContent();					
				}
			}
		}
		
		return true;
	}
}