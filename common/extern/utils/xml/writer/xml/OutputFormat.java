package common.extern.utils.xml.writer.xml;

import org.w3c.dom.*;
import org.w3c.dom.html.HTMLDocument;

public class OutputFormat
{
    public static class Defaults
    {

        public static final int Indent = 4;
        public static final String Encoding = "UTF-8";
        public static final int LineWidth = 72;

        public Defaults()
        {
        }
    }

    public static class DTD
    {

        public static final String HTMLPublicId = "-//W3C//DTD HTML 4.0//EN";
        public static final String HTMLSystemId = "http://www.w3.org/TR/WD-html-in-xml/DTD/xhtml1-strict.dtd";
        public static final String XHTMLPublicId = "-//W3C//DTD XHTML 1.0 Strict//EN";
        public static final String XHTMLSystemId = "http://www.w3.org/TR/WD-html-in-xml/DTD/xhtml1-strict.dtd";

        public DTD()
        {
        }
    }


    public OutputFormat()
    {
        _indent = 0;
        _encoding = "UTF-8";
        _encodingInfo = null;
        _omitXmlDeclaration = false;
        _omitDoctype = false;
        _omitComments = false;
        _stripComments = false;
        _standalone = false;
        _lineSeparator = "\n";
        _lineWidth = 72;
        _preserve = false;
        _preserveEmptyAttributes = false;
    }

    public OutputFormat(String method, String encoding, boolean bIndenting)
    {
        _indent = 0;
        _encoding = "UTF-8";
        _encodingInfo = null;
        _omitXmlDeclaration = false;
        _omitDoctype = false;
        _omitComments = false;
        _stripComments = false;
        _standalone = false;
        _lineSeparator = "\n";
        _lineWidth = 72;
        _preserve = false;
        _preserveEmptyAttributes = false;
        setMethod(method);
        setEncoding(encoding);
        setIndenting(bIndenting);
    }

    public OutputFormat(Document document)
    {
        _indent = 0;
        _encoding = "UTF-8";
        _encodingInfo = null;
        _omitXmlDeclaration = false;
        _omitDoctype = false;
        _omitComments = false;
        _stripComments = false;
        _standalone = false;
        _lineSeparator = "\n";
        _lineWidth = 72;
        _preserve = false;
        _preserveEmptyAttributes = false;
        setMethod(whichMethod(document));
        setDoctype(whichDoctypePublic(document), whichDoctypeSystem(document));
        setMediaType(whichMediaType(getMethod()));
    }

    public OutputFormat(Document document, String encoding, boolean bIndenting)
    {
        this(document);
        setEncoding(encoding);
        setIndenting(bIndenting);
    }

    public String getMethod()
    {
        return _method;
    }

    public void setMethod(String method)
    {
        _method = method;
    }

    public String getVersion()
    {
        return _version;
    }

    public void setVersion(String version)
    {
        _version = version;
    }

    public int getIndent()
    {
        return _indent;
    }

    public boolean getIndenting()
    {
        return _indent > 0;
    }

    public void setIndent(int indent)
    {
        if(indent < 0)
            _indent = 0;
        else
            _indent = indent;
    }

    public void setIndenting(boolean bOn)
    {
        if(bOn)
        {
            _indent = 4;
            _lineWidth = 72;
        } else
        {
            _indent = 0;
            _lineWidth = 0;
        }
    }

    public String getEncoding()
    {
        return _encoding;
    }

    public void setEncoding(String encoding)
    {
        _encoding = encoding;
        _encodingInfo = null;
    }

    public void setEncoding(EncodingInfo encodinginfo)
    {
        _encoding = encodinginfo.getName();
        _encodingInfo = encodinginfo;
    }

    public EncodingInfo getEncodingInfo()
    {
        if(_encodingInfo == null)
            _encodingInfo = Encodings.getEncodingInfo(_encoding);
        return _encodingInfo;
    }

    public String getMediaType()
    {
        return _mediaType;
    }

    public void setMediaType(String mediaType)
    {
        _mediaType = mediaType;
    }

    public void setDoctype(String publicId, String systemId)
    {
        _doctypePublic = publicId;
        _doctypeSystem = systemId;
    }

    public String getDoctypePublic()
    {
        return _doctypePublic;
    }

    public String getDoctypeSystem()
    {
        return _doctypeSystem;
    }

    public boolean getOmitComments()
    {
        return _omitComments;
    }

    public void setOmitComments(boolean bOmit)
    {
        _omitComments = bOmit;
    }

    public boolean getOmitDocumentType()
    {
        return _omitDoctype;
    }

    public void setOmitDocumentType(boolean bOmit)
    {
        _omitDoctype = bOmit;
    }

    public boolean getOmitXMLDeclaration()
    {
        return _omitXmlDeclaration;
    }

    public void setOmitXMLDeclaration(boolean bOmit)
    {
        _omitXmlDeclaration = bOmit;
    }

    public boolean getStandalone()
    {
        return _standalone;
    }

    public void setStandalone(boolean bStandalone)
    {
        _standalone = bStandalone;
    }

    public String[] getCDataElements()
    {
        return _cdataElements;
    }

    public boolean isCDataElement(String tagName)
    {
        if(_cdataElements == null)
            return false;
        for(int i = 0; i < _cdataElements.length; i++)
            if(_cdataElements[i].equals(tagName))
                return true;

        return false;
    }

    public void setCDataElements(String cdataElements[])
    {
        _cdataElements = cdataElements;
    }

    public String[] getNonEscapingElements()
    {
        return _nonEscapingElements;
    }

    public boolean isNonEscapingElement(String tagName)
    {
        if(_nonEscapingElements == null)
            return false;
        for(int i = 0; i < _nonEscapingElements.length; i++)
            if(_nonEscapingElements[i].equals(tagName))
                return true;

        return false;
    }

    public void setNonEscapingElements(String nonEscapingElements[])
    {
        _nonEscapingElements = nonEscapingElements;
    }

    public String getLineSeparator()
    {
        return _lineSeparator;
    }

    public void setLineSeparator(String lineSeparator)
    {
        if(lineSeparator == null)
            _lineSeparator = "\n";
        else
            _lineSeparator = lineSeparator;
    }

    public boolean getPreserveSpace()
    {
        return _preserve;
    }

    public void setPreserveSpace(boolean bPreserve)
    {
        _preserve = bPreserve;
    }

    public int getLineWidth()
    {
        return _lineWidth;
    }

    public void setLineWidth(int lineWidth)
    {
        if(lineWidth <= 0)
            _lineWidth = 0;
        else
            _lineWidth = lineWidth;
    }

    public boolean getPreserveEmptyAttributes()
    {
        return _preserveEmptyAttributes;
    }

    public void setPreserveEmptyAttributes(boolean bPreserve)
    {
        _preserveEmptyAttributes = bPreserve;
    }

    public char getLastPrintable()
    {
        return getEncoding() == null || !getEncoding().equalsIgnoreCase("ASCII") ? '\uFFFF' : '\377';
    }

    public static String whichMethod(Document document)
    {
        if(document instanceof HTMLDocument)
            return "html";
        for(Node node = document.getFirstChild(); node != null; node = node.getNextSibling())
        {
            if(node.getNodeType() == 1)
            {
                if(node.getNodeName().equalsIgnoreCase("html"))
                    return "html";
                if(node.getNodeName().equalsIgnoreCase("root"))
                    return "fop";
                else
                    return "xml";
            }
            if(node.getNodeType() == 3)
            {
                String s = node.getNodeValue();
                for(int i = 0; i < s.length(); i++)
                    if(s.charAt(i) != ' ' && s.charAt(i) != '\n' && s.charAt(i) != '\t' && s.charAt(i) != '\r')
                        return "xml";

            }
        }

        return "xml";
    }

    public static String whichDoctypePublic(Document document)
    {
        DocumentType documenttype = document.getDoctype();
        if(documenttype != null)
            try
            {
                return documenttype.getPublicId();
            }
            catch(Error error) { }
        if(document instanceof HTMLDocument)
            return "-//W3C//DTD XHTML 1.0 Strict//EN";
        else
            return null;
    }

    public static String whichDoctypeSystem(Document document)
    {
        DocumentType documenttype = document.getDoctype();
        if(documenttype != null)
            try
            {
                return documenttype.getSystemId();
            }
            catch(Error error) { }
        if(document instanceof HTMLDocument)
            return "http://www.w3.org/TR/WD-html-in-xml/DTD/xhtml1-strict.dtd";
        else
            return null;
    }

    public static String whichMediaType(String method)
    {
        if(method.equalsIgnoreCase("xml"))
            return "text/xml";
        if(method.equalsIgnoreCase("html"))
            return "text/html";
        if(method.equalsIgnoreCase("xhtml"))
            return "text/html";
        if(method.equalsIgnoreCase("text"))
            return "text/plain";
        if(method.equalsIgnoreCase("fop"))
            return "application/pdf";
        else
            return null;
    }

    private String _method;
    private String _version;
    private int _indent;
    private String _encoding;
    private EncodingInfo _encodingInfo;
    private String _mediaType;
    private String _doctypeSystem;
    private String _doctypePublic;
    private boolean _omitXmlDeclaration;
    private boolean _omitDoctype;
    private boolean _omitComments;
    @SuppressWarnings("unused")
	private boolean _stripComments;
    private boolean _standalone;
    private String _cdataElements[];
    private String _nonEscapingElements[];
    private String _lineSeparator;
    private int _lineWidth;
    private boolean _preserve;
    private boolean _preserveEmptyAttributes;
}