package common.extern.utils.xml;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import common.extern.utils.xml.writer.xml.XMLSerializer;

public class TruegardenerXMLSerializer
{
	private static boolean m_bExternalOrgAXSXExist = true;
	static {
		try {
    		if(Class.forName("org.apache.xml.serialize.XMLSerializer")!=null) {
    			m_bExternalOrgAXSXExist = true;
    		}
    		else {
    			m_bExternalOrgAXSXExist = false;
    		}
    	}
    	catch (ClassNotFoundException e) {
    		m_bExternalOrgAXSXExist = false;
		}
	}
	
	public Object xMLSerializer = null;
	public TruegardenerXMLSerializer()
    {
		if(m_bExternalOrgAXSXExist) {
			xMLSerializer = new org.apache.xml.serialize.XMLSerializer();
		}
		else {
			xMLSerializer = new XMLSerializer();
		}
    }

	public TruegardenerXMLSerializer(TruegardenerOutputFormat outputformat) {
		if(m_bExternalOrgAXSXExist) {
			Object output = outputformat.getOutputFormat();
			if(output!=null) {
				if(output instanceof org.apache.xml.serialize.OutputFormat) {
					xMLSerializer = new org.apache.xml.serialize.XMLSerializer((org.apache.xml.serialize.OutputFormat)output);				
				}
			}
		}
		else {
			xMLSerializer = new XMLSerializer((common.extern.utils.xml.writer.xml.OutputFormat)outputformat.getOutputFormat());
		}
    }

    public TruegardenerXMLSerializer(Writer writer, TruegardenerOutputFormat outputformat)
    {
    	if(m_bExternalOrgAXSXExist) {
    		Object output = outputformat.getOutputFormat();
    		if(output!=null) {
    			if(output instanceof org.apache.xml.serialize.OutputFormat) {
    				xMLSerializer = new org.apache.xml.serialize.XMLSerializer(writer, (org.apache.xml.serialize.OutputFormat)output);
    			}
    		}
		}
    	else {
    		xMLSerializer = new XMLSerializer(writer, (common.extern.utils.xml.writer.xml.OutputFormat)outputformat.getOutputFormat());    		
    	}
    }

    public String writeToString(Node node) throws DOMException
	{
    	if(m_bExternalOrgAXSXExist) {
    		return ((org.apache.xml.serialize.XMLSerializer)xMLSerializer).writeToString(node);
		}
    	else {
    		return ((XMLSerializer)xMLSerializer).writeToString(node);
    	}
	}
    
    public void setOutputFormat(TruegardenerOutputFormat outputformat)
    {
    	if(m_bExternalOrgAXSXExist) {
    		Object output = outputformat.getOutputFormat();
    		if(output!=null) {
    			if(output instanceof org.apache.xml.serialize.OutputFormat) {
    				((org.apache.xml.serialize.XMLSerializer)xMLSerializer).setOutputFormat((org.apache.xml.serialize.OutputFormat)output);
    			}
    		}
    		
		}
    	else {
    		((XMLSerializer)xMLSerializer).setOutputFormat((common.extern.utils.xml.writer.xml.OutputFormat)outputformat.getOutputFormat());
    	}
    }
    public void startElement(String namespaceURI, String localName, String rawName, Attributes attributes)
        throws SAXException
    {
    	if(m_bExternalOrgAXSXExist) {
    		((org.apache.xml.serialize.XMLSerializer)xMLSerializer).startElement(namespaceURI, localName, rawName, attributes);
		}
    	else {
    		((XMLSerializer)xMLSerializer).startElement(namespaceURI, localName, rawName, attributes);
    	}
    }

    public void endElement(String namespaceURI, String localName, String rawName)
        throws SAXException
    {
    	if(m_bExternalOrgAXSXExist) {
    		((org.apache.xml.serialize.XMLSerializer)xMLSerializer).endElement(namespaceURI, localName, rawName);
		}
    	else {
    		((XMLSerializer)xMLSerializer).endElement(namespaceURI, localName, rawName);
    	}
    }

    public void endElementIO(String namespaceURI, String localName, String rawName)
        throws IOException
    {
    	if(m_bExternalOrgAXSXExist) {
    		((org.apache.xml.serialize.XMLSerializer)xMLSerializer).endElementIO(namespaceURI, localName, rawName);
		}
    	else {
    		
    	}
    }

    @SuppressWarnings("deprecation")
	public void startElement(String tagName, org.xml.sax.AttributeList attributelist)
        throws SAXException
    {
    	if(m_bExternalOrgAXSXExist) {
    		((org.apache.xml.serialize.XMLSerializer)xMLSerializer).startElement(tagName, attributelist);
		}
    	else {
    		((XMLSerializer)xMLSerializer).startElement(tagName, attributelist);
    	}
    }

    public void endElement(String tagName)
        throws SAXException
    {
    	if(m_bExternalOrgAXSXExist) {
    		((org.apache.xml.serialize.XMLSerializer)xMLSerializer).endElement(tagName);
		}
    	else {
    		((XMLSerializer)xMLSerializer).endElement(tagName);
    	}
    }

    @SuppressWarnings("unchecked")
	public void setFeature(String s, boolean flag)
        throws DOMException
    {
    	if(m_bExternalOrgAXSXExist) {
    		((org.apache.xml.serialize.XMLSerializer)xMLSerializer).setFeature(s, flag);
		}
    	else {
    		((XMLSerializer)xMLSerializer).setFeature(s, flag);
    	}
    }

    public boolean canSetFeature(String s, boolean flag)
    {
    	if(m_bExternalOrgAXSXExist) {
    		return ((org.apache.xml.serialize.XMLSerializer)xMLSerializer).canSetFeature(s, flag);
		}
    	else {
    		return ((XMLSerializer)xMLSerializer).canSetFeature(s, flag);
    	}
    }

    public boolean getFeature(String s)
        throws DOMException
    {
    	if(m_bExternalOrgAXSXExist) {
    		return ((org.apache.xml.serialize.XMLSerializer)xMLSerializer).getFeature(s);
		}
    	else {
    		return ((XMLSerializer)xMLSerializer).getFeature(s);
    	}
    }

    public String getEncoding()
    {
    	if(m_bExternalOrgAXSXExist) {
    		return ((org.apache.xml.serialize.XMLSerializer)xMLSerializer).getEncoding();
		}
    	else {
    		return ((XMLSerializer)xMLSerializer).getEncoding();
    	}
    }

    public void setEncoding(String encoding)
    {
    	if(m_bExternalOrgAXSXExist) {
    		((org.apache.xml.serialize.XMLSerializer)xMLSerializer).setEncoding(encoding);
		}
    	else {
    		((XMLSerializer)xMLSerializer).setEncoding(encoding);
    	}
    }
    

    public String getLastEncoding()
    {
    	if(m_bExternalOrgAXSXExist) {
    		return ((org.apache.xml.serialize.XMLSerializer)xMLSerializer).getLastEncoding();
		}
    	else {
    		return ((XMLSerializer)xMLSerializer).getLastEncoding();
    	}
    }

    public String getNewLine()
    {
    	if(m_bExternalOrgAXSXExist) {
    		return ((org.apache.xml.serialize.XMLSerializer)xMLSerializer).getNewLine();
		}
    	else {
    		return ((XMLSerializer)xMLSerializer).getNewLine();
    	}
    }

    public void setNewLine(String s)
    {
    	if(m_bExternalOrgAXSXExist) {
    		((org.apache.xml.serialize.XMLSerializer)xMLSerializer).setNewLine(s);
		}
    	else {
    		((XMLSerializer)xMLSerializer).setNewLine(s);
    	}
    }

    public boolean writeNode(OutputStream outputstream, Node node) throws Exception
    {
    	if(m_bExternalOrgAXSXExist) {
    		return((org.apache.xml.serialize.XMLSerializer)xMLSerializer).writeNode(outputstream, node);
		}
    	else {
    		return((XMLSerializer)xMLSerializer).writeNode(outputstream, node);
    	}
    }

    public boolean reset()
    {
    	if(m_bExternalOrgAXSXExist) {
    		return((org.apache.xml.serialize.XMLSerializer)xMLSerializer).reset();
		}
    	else {
    		return ((XMLSerializer)xMLSerializer).reset();
    	}
    }
}