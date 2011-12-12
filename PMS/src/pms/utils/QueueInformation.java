package pms.utils;

import org.w3c.dom.*;

public class QueueInformation {
	public static String tagName = "QueueInformation";
	
	public String timeStamp = "";
	public String queueDelayTime = "";
	public String averageProcessingTime = "";
	
	boolean loadElement(Element queueInfoElement){
		if(queueInfoElement == null) return false;
		
		NodeList childNodeList = queueInfoElement.getChildNodes();
		for(int i=0; i < childNodeList.getLength(); i++){
			Node childNode = childNodeList.item(i);
			if(childNode.getNodeType() == Node.ELEMENT_NODE){
				Element childElement = (Element)childNode;
				
				if(childElement.getTagName().compareTo("TimeStamp") == 0){
					timeStamp = childElement.getTextContent();
				}else if(childElement.getTagName().compareTo("QueueDelayTime") == 0){
					queueDelayTime =  childElement.getTextContent();
				}else if(childElement.getTagName().compareTo("AverageProcessingTime") == 0){
					averageProcessingTime =  childElement.getTextContent();					
				}
			}
		}
		
		return true;
	}
}
