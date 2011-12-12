package pms.utils;
import java.util.*;

import org.w3c.dom.*;


public class ResourceInformation {
	public static String tagName = "ResourceInformation";
	
	public Resource resource = new Resource();;
	public HashMap<String, QueueInformation> queueInformationMap = new HashMap<String, QueueInformation>();
	
	boolean loadElement(Element resourceInfoElement){
		if(resourceInfoElement == null) return false;
		
		queueInformationMap.clear();
		
		NodeList childNodeList = resourceInfoElement.getChildNodes();
		for(int i=0; i < childNodeList.getLength(); i++){
			Node childNode = childNodeList.item(i);
			if(childNode.getNodeType() == Node.ELEMENT_NODE){
				Element childElement = (Element)childNode;
				
				if(childElement.getTagName().compareTo(Resource.tagName) == 0){
					resource.loadElement(childElement);					
				}else if(childElement.getTagName().compareTo(QueueInformation.tagName) == 0){
					String priority = childElement.getAttribute("priority");
					
					QueueInformation qi = new QueueInformation();
					if(qi.loadElement(childElement)){
						queueInformationMap.put(priority, qi);
					}
				}
			}
		}
		
		return true;
	}
}
