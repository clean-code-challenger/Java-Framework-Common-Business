package common.extern.utils.xml;

import org.w3c.dom.*;
import common.extern.utils.xml.writer.xml.OutputFormat;

public class TruegardenerOutputFormat
{
	private static boolean m_bExternalOrgAXSOExist = true;
	static {
		try {
    		if(Class.forName("org.apache.xml.serialize.OutputFormat")!=null) {
    			m_bExternalOrgAXSOExist = true;
    		}
    		else {
    			m_bExternalOrgAXSOExist = false;
    		}
    	}
    	catch (ClassNotFoundException e) {
    		m_bExternalOrgAXSOExist = false;
		}
	}
	public Object outputFormat = null;
	public Object getOutputFormat() {
		return outputFormat;
	}
	
    public TruegardenerOutputFormat() {
    	if(m_bExternalOrgAXSOExist) {
    		outputFormat = new org.apache.xml.serialize.OutputFormat();
    	}
    	else {
    		outputFormat = new OutputFormat();
    	}
    }
    
    public TruegardenerOutputFormat(String method, String encoding, boolean bIndenting) {
    	if(m_bExternalOrgAXSOExist) {
    		outputFormat = new org.apache.xml.serialize.OutputFormat(method, encoding, bIndenting);
    	}
    	else {
    		outputFormat = new OutputFormat(method, encoding, bIndenting);
    	}
    }

    public TruegardenerOutputFormat(Document document) {
    	if(m_bExternalOrgAXSOExist) {
    		outputFormat = new org.apache.xml.serialize.OutputFormat(document);
    	}
    	else {
    		outputFormat = new OutputFormat(document);
    	}
    }

    public TruegardenerOutputFormat(Document document, String encoding, boolean bIndenting) {
    	if(m_bExternalOrgAXSOExist) {
    		outputFormat = new org.apache.xml.serialize.OutputFormat(document, encoding, bIndenting);
    	}
    	else{
    		outputFormat = new OutputFormat(document, encoding, bIndenting);
    	}
    }
    
    public void setOmitXMLDeclaration(boolean bOmit)
    {
    	if(m_bExternalOrgAXSOExist) {
    		((org.apache.xml.serialize.OutputFormat)outputFormat).setOmitXMLDeclaration(bOmit);
    	}
    	else {
    		((OutputFormat)outputFormat).setOmitXMLDeclaration(bOmit);;
    	}
    }
    
    public String getMethod()
    {
    	if(m_bExternalOrgAXSOExist) {
    		return ((org.apache.xml.serialize.OutputFormat)outputFormat).getMethod();
    	}
    	else {
    		return ((OutputFormat)outputFormat).getMethod();
    	}
    }

    public void setMethod(String method)
    {
    	if(m_bExternalOrgAXSOExist) {
    		((org.apache.xml.serialize.OutputFormat)outputFormat).setMethod(method);
    	}
    	else {
    		((OutputFormat)outputFormat).setMethod(method);
    	}
    }

    public String getVersion()
    {
    	if(m_bExternalOrgAXSOExist) {
    		return ((org.apache.xml.serialize.OutputFormat)outputFormat).getVersion();
    	}
    	else {
    		return ((OutputFormat)outputFormat).getVersion();
    	}
    }

    public void setVersion(String version)
    {
    	if(m_bExternalOrgAXSOExist) {
    		((org.apache.xml.serialize.OutputFormat)outputFormat).setVersion(version);
    	}
    	else {
    		((OutputFormat)outputFormat).setVersion(version);
    	}
    }

    public int getIndent()
    {
    	if(m_bExternalOrgAXSOExist) {
    		return ((org.apache.xml.serialize.OutputFormat)outputFormat).getIndent();
    	}
    	else {
    		return ((OutputFormat)outputFormat).getIndent();
    	}
    }

    public boolean getIndenting()
    {
    	if(m_bExternalOrgAXSOExist) {
    		return ((org.apache.xml.serialize.OutputFormat)outputFormat).getIndenting();
    	}
    	else {
    		return ((OutputFormat)outputFormat).getIndenting();
    	}
    }

    public void setIndent(int indent)
    {
    	if(m_bExternalOrgAXSOExist) {
    		((org.apache.xml.serialize.OutputFormat)outputFormat).setIndent(indent);
    	}
    	else {
    		((OutputFormat)outputFormat).setIndent(indent);
    	}
    }

    public void setIndenting(boolean bOn)
    {
    	if(m_bExternalOrgAXSOExist) {
    		((org.apache.xml.serialize.OutputFormat)outputFormat).setIndenting(bOn);
    	}
    	else {
    		((OutputFormat)outputFormat).setIndenting(bOn);
    	}
    }

    public String getEncoding()
    {
    	if(m_bExternalOrgAXSOExist) {
    		return ((org.apache.xml.serialize.OutputFormat)outputFormat).getEncoding();
    	}
    	else {
    		return ((OutputFormat)outputFormat).getEncoding();
    	}
    }

    public void setEncoding(String encoding)
    {
    	if(m_bExternalOrgAXSOExist) {
    		((org.apache.xml.serialize.OutputFormat)outputFormat).setEncoding(encoding);
    	}
    	else {
    		((OutputFormat)outputFormat).setEncoding(encoding);
    	}
    }

    public String getMediaType()
    {
    	if(m_bExternalOrgAXSOExist) {
    		return ((org.apache.xml.serialize.OutputFormat)outputFormat).getMediaType();
    	}
    	else {
    		return ((OutputFormat)outputFormat).getMediaType();
    	}
    }

    public void setMediaType(String mediaType)
    {
    	if(m_bExternalOrgAXSOExist) {
    		((org.apache.xml.serialize.OutputFormat)outputFormat).setMediaType(mediaType);
    	}
    	else {
    		((OutputFormat)outputFormat).setMediaType(mediaType);
    	}
    }

    public void setDoctype(String publicId, String systemId)
    {
    	if(m_bExternalOrgAXSOExist) {
    		((org.apache.xml.serialize.OutputFormat)outputFormat).setDoctype(publicId, systemId);
    	}
    	else {
    		((OutputFormat)outputFormat).setDoctype(publicId, systemId);
    	}
    }

    public String getDoctypePublic()
    {
    	if(m_bExternalOrgAXSOExist) {
    		return ((org.apache.xml.serialize.OutputFormat)outputFormat).getDoctypePublic();
    	}
    	else {
    		return ((OutputFormat)outputFormat).getDoctypePublic();
    	}
    }

    public String getDoctypeSystem()
    {
    	if(m_bExternalOrgAXSOExist) {
    		return ((org.apache.xml.serialize.OutputFormat)outputFormat).getDoctypeSystem();
    	}
    	else {
    		return ((OutputFormat)outputFormat).getDoctypeSystem();
    	}
    }

    public boolean getOmitComments()
    {
    	if(m_bExternalOrgAXSOExist) {
    		return ((org.apache.xml.serialize.OutputFormat)outputFormat).getOmitComments();
    	}
    	else {
    		return ((OutputFormat)outputFormat).getOmitComments();
    	}
    }

    public void setOmitComments(boolean bOmit)
    {
    	if(m_bExternalOrgAXSOExist) {
    		((org.apache.xml.serialize.OutputFormat)outputFormat).setOmitComments(bOmit);
    	}
    	else {
    		((OutputFormat)outputFormat).setOmitComments(bOmit);
    	}
    }

    public boolean getOmitDocumentType()
    {
    	if(m_bExternalOrgAXSOExist) {
    		return ((org.apache.xml.serialize.OutputFormat)outputFormat).getOmitDocumentType();
    	}
    	else {
    		return ((OutputFormat)outputFormat).getOmitDocumentType();
    	}
    }

    public void setOmitDocumentType(boolean bOmit)
    {
    	if(m_bExternalOrgAXSOExist) {
    		((org.apache.xml.serialize.OutputFormat)outputFormat).setOmitDocumentType(bOmit);
    	}
    	else {
    		((OutputFormat)outputFormat).setOmitDocumentType(bOmit);
    	}
    }

    public boolean getOmitXMLDeclaration()
    {
    	if(m_bExternalOrgAXSOExist) {
    		return ((org.apache.xml.serialize.OutputFormat)outputFormat).getOmitXMLDeclaration();
    	}
    	else {
    		return ((OutputFormat)outputFormat).getOmitXMLDeclaration();
    	}
    }

    public boolean getStandalone()
    {
    	if(m_bExternalOrgAXSOExist) {
    		return ((org.apache.xml.serialize.OutputFormat)outputFormat).getStandalone();
    	}
    	else {
    		return ((OutputFormat)outputFormat).getStandalone();
    	}
    }

    public void setStandalone(boolean bStandalone)
    {
    	if(m_bExternalOrgAXSOExist) {
    		((org.apache.xml.serialize.OutputFormat)outputFormat).setStandalone(bStandalone);
    	}
    	else {
    		((OutputFormat)outputFormat).setStandalone(bStandalone);
    	}
    }

    public String[] getCDataElements()
    {
    	if(m_bExternalOrgAXSOExist) {
    		return ((org.apache.xml.serialize.OutputFormat)outputFormat).getCDataElements();
    	}
    	else {
    		return ((OutputFormat)outputFormat).getCDataElements();
    	}
    }

    public boolean isCDataElement(String tagName)
    {
    	if(m_bExternalOrgAXSOExist) {
    		return ((org.apache.xml.serialize.OutputFormat)outputFormat).isCDataElement(tagName);
    	}
    	else {
    		return ((OutputFormat)outputFormat).isCDataElement(tagName);
    	}
    }

    public void setCDataElements(String cdataElements[])
    {
    	if(m_bExternalOrgAXSOExist) {
    		((org.apache.xml.serialize.OutputFormat)outputFormat).setCDataElements(cdataElements);
    	}
    	else {
    		((OutputFormat)outputFormat).setCDataElements(cdataElements);
    	}
    }

    public String[] getNonEscapingElements()
    {
    	if(m_bExternalOrgAXSOExist) {
    		return ((org.apache.xml.serialize.OutputFormat)outputFormat).getNonEscapingElements();
    	}
    	else {
    		return ((OutputFormat)outputFormat).getNonEscapingElements();
    	}
    }

    public boolean isNonEscapingElement(String tagName)
    {
    	if(m_bExternalOrgAXSOExist) {
    		return ((org.apache.xml.serialize.OutputFormat)outputFormat).isNonEscapingElement(tagName);
    	}
    	else {
    		return ((OutputFormat)outputFormat).isNonEscapingElement(tagName);
    	}
    }

    public void setNonEscapingElements(String nonEscapingElements[])
    {
    	if(m_bExternalOrgAXSOExist) {
    		((org.apache.xml.serialize.OutputFormat)outputFormat).setNonEscapingElements(nonEscapingElements);
    	}
    	else {
    		((OutputFormat)outputFormat).setNonEscapingElements(nonEscapingElements);
    	}
    }

    public String getLineSeparator()
    {
    	if(m_bExternalOrgAXSOExist) {
    		return ((org.apache.xml.serialize.OutputFormat)outputFormat).getLineSeparator();
    	}
    	else {
    		return ((OutputFormat)outputFormat).getLineSeparator();
    	}
    }

    public void setLineSeparator(String lineSeparator)
    {
    	if(m_bExternalOrgAXSOExist) {
    		((org.apache.xml.serialize.OutputFormat)outputFormat).setLineSeparator(lineSeparator);
    	}
    	else {
    		((OutputFormat)outputFormat).setLineSeparator(lineSeparator);
    	}
    }

    public boolean getPreserveSpace()
    {
    	if(m_bExternalOrgAXSOExist) {
    		return ((org.apache.xml.serialize.OutputFormat)outputFormat).getPreserveSpace();
    	}
    	else {
    		return ((OutputFormat)outputFormat).getPreserveSpace();
    	}
    }

    public void setPreserveSpace(boolean bPreserve)
    {
    	if(m_bExternalOrgAXSOExist) {
    		((org.apache.xml.serialize.OutputFormat)outputFormat).setPreserveSpace(bPreserve);
    	}
    	else {
    		((OutputFormat)outputFormat).setPreserveSpace(bPreserve);
    	}
    }

    public int getLineWidth()
    {
    	if(m_bExternalOrgAXSOExist) {
    		return ((org.apache.xml.serialize.OutputFormat)outputFormat).getLineWidth();
    	}
    	else {
    		return ((OutputFormat)outputFormat).getLineWidth();
    	}
    }

    public void setLineWidth(int lineWidth)
    {
    	if(m_bExternalOrgAXSOExist) {
    		((org.apache.xml.serialize.OutputFormat)outputFormat).setLineWidth(lineWidth);
    	}
    	else {
    		((OutputFormat)outputFormat).setLineWidth(lineWidth);
    	}
    }

    public boolean getPreserveEmptyAttributes()
    {
    	if(m_bExternalOrgAXSOExist) {
    		return ((org.apache.xml.serialize.OutputFormat)outputFormat).getPreserveEmptyAttributes();
    	}
    	else {
    		return ((OutputFormat)outputFormat).getPreserveEmptyAttributes();
    	}
    }

    public void setPreserveEmptyAttributes(boolean bPreserve)
    {
    	if(m_bExternalOrgAXSOExist) {
    		((org.apache.xml.serialize.OutputFormat)outputFormat).setPreserveEmptyAttributes(bPreserve);
    	}
    	else {
    		((OutputFormat)outputFormat).setPreserveEmptyAttributes(bPreserve);
    	}
    }

    public char getLastPrintable()
    {
    	if(m_bExternalOrgAXSOExist) {
    		return ((org.apache.xml.serialize.OutputFormat)outputFormat).getLastPrintable();
    	}
    	else {
    		return ((OutputFormat)outputFormat).getLastPrintable();
    	}
    }

    public static String whichMethod(Document document)
    {
    	if(m_bExternalOrgAXSOExist) {
    		return org.apache.xml.serialize.OutputFormat.whichMethod(document);
    	}
    	else {
    		return OutputFormat.whichMethod(document);
    	}
    }

    public static String whichDoctypePublic(Document document)
    {
    	if(m_bExternalOrgAXSOExist) {
    		return org.apache.xml.serialize.OutputFormat.whichDoctypePublic(document);
    	}
    	else {
    		return OutputFormat.whichDoctypePublic(document);
    	}
    }

    public static String whichDoctypeSystem(Document document)
    {
    	if(m_bExternalOrgAXSOExist) {
    		return org.apache.xml.serialize.OutputFormat.whichDoctypeSystem(document);
    	}
    	else {
    		return OutputFormat.whichDoctypeSystem(document);
    	}
    }

    public static String whichMediaType(String method)
    {
    	if(m_bExternalOrgAXSOExist) {
    		return org.apache.xml.serialize.OutputFormat.whichMediaType(method);
    	}
    	else {
    		return OutputFormat.whichMediaType(method);
    	}
    }
}