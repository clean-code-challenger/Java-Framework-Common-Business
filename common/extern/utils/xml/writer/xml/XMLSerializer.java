package common.extern.utils.xml.writer.xml;

// Decompiled by DJ v3.0.0.63 Copyright 2002 Atanas Neshkov  Date: 2012-10-09 ���� 12:33:53
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   XMLSerializer.java

import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;
import common.extern.utils.xml.writer.xerces.dom3.DOMErrorHandler;
import common.extern.utils.xml.writer.xerces.dom3.ls.DOMWriter;
import common.extern.utils.xml.writer.xerces.util.NamespaceSupport;
import common.extern.utils.xml.writer.xerces.util.SymbolTable;
import org.w3c.dom.*;
import org.xml.sax.*;
import org.xml.sax.helpers.AttributesImpl;

// Referenced classes of package org.apache.xml.serialize:
//            BaseMarkupSerializer, OutputFormat, ElementState, Printer

public class XMLSerializer extends BaseMarkupSerializer
    implements DOMWriter
{

    public XMLSerializer()
    {
        super(new OutputFormat("xml", null, false));
        fNSBinder = new NamespaceSupport();
        fLocalNSBinder = new NamespaceSupport();
        fSymbolTable = new SymbolTable();
        fDOML1 = false;
        fNamespaceCounter = 1;
        super.fFeatures = new Hashtable();
        initFeatures();
    }

    public XMLSerializer(OutputFormat outputformat)
    {
        super(outputformat == null ? new OutputFormat("xml", null, false) : outputformat);
        fNSBinder = new NamespaceSupport();
        fLocalNSBinder = new NamespaceSupport();
        fSymbolTable = new SymbolTable();
        fDOML1 = false;
        fNamespaceCounter = 1;
        super._format.setMethod("xml");
        super.fFeatures = new Hashtable();
        initFeatures();
    }

    public XMLSerializer(Writer writer, OutputFormat outputformat)
    {
        super(outputformat == null ? new OutputFormat("xml", null, false) : outputformat);
        fNSBinder = new NamespaceSupport();
        fLocalNSBinder = new NamespaceSupport();
        fSymbolTable = new SymbolTable();
        fDOML1 = false;
        fNamespaceCounter = 1;
        super._format.setMethod("xml");
        setOutputCharStream(writer);
        super.fFeatures = new Hashtable();
        initFeatures();
    }

    public XMLSerializer(OutputStream outputstream, OutputFormat outputformat)
    {
        super(outputformat == null ? new OutputFormat("xml", null, false) : outputformat);
        fNSBinder = new NamespaceSupport();
        fLocalNSBinder = new NamespaceSupport();
        fSymbolTable = new SymbolTable();
        fDOML1 = false;
        fNamespaceCounter = 1;
        super._format.setMethod("xml");
        setOutputByteStream(outputstream);
        super.fFeatures = new Hashtable();
        initFeatures();
    }

    public void setOutputFormat(OutputFormat outputformat)
    {
        super.setOutputFormat(outputformat == null ? new OutputFormat("xml", null, false) : outputformat);
    }

    public void startElement(String s, String s1, String s2, Attributes attributes)
        throws SAXException
    {
        @SuppressWarnings("unused")
		boolean flag1 = false;
        try
        {
            if(super._printer == null)
                throw new IllegalStateException("SER002 No writer supplied for serializer");
            ElementState elementstate = getElementState();
            if(isDocumentState())
            {
                if(!super._started)
                    startDocument(s1 != null && s1.length() != 0 ? s1 : s2);
            } else
            {
                if(elementstate.empty)
                    super._printer.printText('>');
                if(elementstate.inCData)
                {
                    super._printer.printText("]]>");
                    elementstate.inCData = false;
                }
                if(super._indenting && !elementstate.preserveSpace && (elementstate.empty || elementstate.afterElement || elementstate.afterComment))
                    super._printer.breakLine();
            }
            boolean flag = elementstate.preserveSpace;
            attributes = extractNamespaces(attributes);
            if(s2 == null || s2.length() == 0)
            {
                if(s1 == null)
                    throw new SAXException("No rawName and localName is null");
                if(s != null && !s.equals(""))
                {
                    String s8 = getPrefix(s);
                    if(s8 != null && s8.length() > 0)
                        s2 = s8 + ":" + s1;
                    else
                        s2 = s1;
                } else
                {
                    s2 = s1;
                }
                @SuppressWarnings("unused")
				boolean flag2 = true;
            }
            super._printer.printText('<');
            super._printer.printText(s2);
            super._printer.indent();
            if(attributes != null)
            {
                for(int i = 0; i < attributes.getLength(); i++)
                {
                    super._printer.printSpace();
                    String s3 = attributes.getQName(i);
                    if(s3 != null && s3.length() == 0)
                    {
                        s3 = attributes.getLocalName(i);
                        String s10 = attributes.getURI(i);
                        if(s10 != null && s10.length() != 0 && (s == null || s.length() == 0 || !s10.equals(s)))
                        {
                            String s9 = getPrefix(s10);
                            if(s9 != null && s9.length() > 0)
                                s3 = s9 + ":" + s3;
                        }
                    }
                    String s6 = attributes.getValue(i);
                    if(s6 == null)
                        s6 = "";
                    super._printer.printText(s3);
                    super._printer.printText("=\"");
                    printEscaped(s6);
                    super._printer.printText('"');
                    if(s3.equals("xml:space"))
                        if(s6.equals("preserve"))
                            flag = true;
                        else
                            flag = super._format.getPreserveSpace();
                }

            }
            if(super._prefixes != null)
            {
                for(Enumeration enumeration = super._prefixes.keys(); enumeration.hasMoreElements();)
                {
                    super._printer.printSpace();
                    String s7 = (String)enumeration.nextElement();
                    String s4 = (String)super._prefixes.get(s7);
                    if(s4.length() == 0)
                    {
                        super._printer.printText("xmlns=\"");
                        printEscaped(s7);
                        super._printer.printText('"');
                    } else
                    {
                        super._printer.printText("xmlns:");
                        super._printer.printText(s4);
                        super._printer.printText("=\"");
                        printEscaped(s7);
                        super._printer.printText('"');
                    }
                }

            }
            elementstate = enterElementState(s, s1, s2, flag);
            String s5 = s1 != null && s1.length() != 0 ? s + "^" + s1 : s2;
            elementstate.doCData = super._format.isCDataElement(s5);
            elementstate.unescaped = super._format.isNonEscapingElement(s5);
        }
        catch(IOException ioexception)
        {
            throw new SAXException(ioexception);
        }
    }

    public void endElement(String s, String s1, String s2)
        throws SAXException
    {
        try
        {
            endElementIO(s, s1, s2);
        }
        catch(IOException ioexception)
        {
            throw new SAXException(ioexception);
        }
    }

    public void endElementIO(String s, String s1, String s2)
        throws IOException
    {
        super._printer.unindent();
        ElementState elementstate = getElementState();
        if(elementstate.empty)
        {
            super._printer.printText("/>");
        } else
        {
            if(elementstate.inCData)
                super._printer.printText("]]>");
            if(super._indenting && !elementstate.preserveSpace && (elementstate.afterElement || elementstate.afterComment))
                super._printer.breakLine();
            super._printer.printText("</");
            super._printer.printText(elementstate.rawName);
            super._printer.printText('>');
        }
        elementstate = leaveElementState();
        elementstate.afterElement = true;
        elementstate.afterComment = false;
        elementstate.empty = false;
        if(isDocumentState())
            super._printer.flush();
    }

    @SuppressWarnings("deprecation")
	public void startElement(String s, AttributeList attributelist)
        throws SAXException
    {
        try
        {
            if(super._printer == null)
                throw new IllegalStateException("SER002 No writer supplied for serializer");
            ElementState elementstate = getElementState();
            if(isDocumentState())
            {
                if(!super._started)
                    startDocument(s);
            } else
            {
                if(elementstate.empty)
                    super._printer.printText('>');
                if(elementstate.inCData)
                {
                    super._printer.printText("]]>");
                    elementstate.inCData = false;
                }
                if(super._indenting && !elementstate.preserveSpace && (elementstate.empty || elementstate.afterElement || elementstate.afterComment))
                    super._printer.breakLine();
            }
            boolean flag = elementstate.preserveSpace;
            super._printer.printText('<');
            super._printer.printText(s);
            super._printer.indent();
            if(attributelist != null)
            {
                for(int i = 0; i < attributelist.getLength(); i++)
                {
                    super._printer.printSpace();
                    String s1 = attributelist.getName(i);
                    String s2 = attributelist.getValue(i);
                    if(s2 != null)
                    {
                        super._printer.printText(s1);
                        super._printer.printText("=\"");
                        printEscaped(s2);
                        super._printer.printText('"');
                    }
                    if(s1.equals("xml:space"))
                        if(s2.equals("preserve"))
                            flag = true;
                        else
                            flag = super._format.getPreserveSpace();
                }

            }
            elementstate = enterElementState(null, null, s, flag);
            elementstate.doCData = super._format.isCDataElement(s);
            elementstate.unescaped = super._format.isNonEscapingElement(s);
        }
        catch(IOException ioexception)
        {
            throw new SAXException(ioexception);
        }
    }

    public void endElement(String s)
        throws SAXException
    {
        endElement(null, null, s);
    }

    protected void startDocument(String s)
        throws IOException
    {
        String s1 = super._printer.leaveDTD();
        if(!super._started)
        {
            if(!super._format.getOmitXMLDeclaration())
            {
                StringBuffer stringbuffer = new StringBuffer("<?xml version=\"");
                if(super._format.getVersion() != null)
                    stringbuffer.append(super._format.getVersion());
                else
                    stringbuffer.append("1.0");
                stringbuffer.append('"');
                if(super._format.getEncoding() != null)
                {
                    stringbuffer.append(" encoding=\"");
                    stringbuffer.append(super._format.getEncoding());
                    stringbuffer.append('"');
                }
                if(super._format.getStandalone() && super._docTypeSystemId == null && super._docTypePublicId == null)
                    stringbuffer.append(" standalone=\"yes\"");
                stringbuffer.append("?>");
                super._printer.printText(stringbuffer);
                super._printer.breakLine();
            }
            if(!super._format.getOmitDocumentType())
                if(super._docTypeSystemId != null)
                {
                    super._printer.printText("<!DOCTYPE ");
                    super._printer.printText(s);
                    if(super._docTypePublicId != null)
                    {
                        super._printer.printText(" PUBLIC ");
                        printDoctypeURL(super._docTypePublicId);
                        if(super._indenting)
                        {
                            super._printer.breakLine();
                            for(int i = 0; i < 18 + s.length(); i++)
                                super._printer.printText(" ");

                        } else
                        {
                            super._printer.printText(" ");
                        }
                        printDoctypeURL(super._docTypeSystemId);
                    } else
                    {
                        super._printer.printText(" SYSTEM ");
                        printDoctypeURL(super._docTypeSystemId);
                    }
                    if(s1 != null && s1.length() > 0)
                    {
                        super._printer.printText(" [");
                        printText(s1, true, true);
                        super._printer.printText(']');
                    }
                    super._printer.printText(">");
                    super._printer.breakLine();
                } else
                if(s1 != null && s1.length() > 0)
                {
                    super._printer.printText("<!DOCTYPE ");
                    super._printer.printText(s);
                    super._printer.printText(" [");
                    printText(s1, true, true);
                    super._printer.printText("]>");
                    super._printer.breakLine();
                }
        }
        super._started = true;
        serializePreRoot();
    }

    protected void serializeElement(Element element)
        throws IOException
    {
        fLocalNSBinder.reset(fSymbolTable);
        fLocalNSBinder.pushContext();
        fNSBinder.pushContext();
        String s2 = element.getTagName();
        ElementState elementstate = getElementState();
        if(isDocumentState())
        {
            fDOML1 = element.getLocalName() == null;
            if(!super._started)
                startDocument(s2);
        } else
        {
            if(elementstate.empty)
                super._printer.printText('>');
            if(elementstate.inCData)
            {
                super._printer.printText("]]>");
                elementstate.inCData = false;
            }
            if(super._indenting && !elementstate.preserveSpace && (elementstate.empty || elementstate.afterElement || elementstate.afterComment))
                super._printer.breakLine();
        }
        fPreserveSpace = elementstate.preserveSpace;
        NamedNodeMap namednodemap = element.getAttributes();
        @SuppressWarnings("unused")
		boolean flag = false;
        String s6 = element.getNamespaceURI();
        String s3 = element.getPrefix();
        if(s6 != null && s3 != null && s6.length() == 0 && s3.length() != 0)
        {
            s3 = null;
            super._printer.printText('<');
            super._printer.printText(element.getLocalName());
            super._printer.indent();
        } else
        {
            super._printer.printText('<');
            super._printer.printText(s2);
            super._printer.indent();
        }
        if(s6 != null)
        {
            s6 = fSymbolTable.addSymbol(s6);
            s3 = s3 != null ? fSymbolTable.addSymbol(s3) : fEmptySymbol;
            if(fNSBinder.getURI(s3) != s6)
            {
                printNamespaceAttr(s3, s6);
                fLocalNSBinder.declarePrefix(s3, s6);
                fNSBinder.declarePrefix(s3, s6);
            }
        } else
        {
            int j = s2.indexOf(':');
            if(j > -1)
            {
                int k = s2.lastIndexOf(':');
                if(j != k)
                {
                    if(super.fDOMErrorHandler != null)
                    {
                        modifyDOMError("Element's name is not a QName: " + s2, (short)1);
                        @SuppressWarnings("unused")
						boolean flag1 = super.fDOMErrorHandler.handleError(super.fDOMError);
                    }
                } else
                if(super.fDOMErrorHandler != null)
                {
                    modifyDOMError("Element <" + s2 + "> does not belong to any namespace: prefix could be undeclared or bound to some namespace", (short)0);
                    @SuppressWarnings("unused")
					boolean flag2 = super.fDOMErrorHandler.handleError(super.fDOMError);
                }
            } else
            {
                String s7 = fNSBinder.getURI(fEmptySymbol);
                if(s7 != null && s7.length() > 0)
                {
                    printNamespaceAttr(fEmptySymbol, fEmptySymbol);
                    fLocalNSBinder.declarePrefix(fEmptySymbol, fEmptySymbol);
                    fNSBinder.declarePrefix(fEmptySymbol, fEmptySymbol);
                }
            }
        }
        if(namednodemap != null)
        {
            for(int i = 0; i < namednodemap.getLength(); i++)
            {
                Attr attr = (Attr)namednodemap.item(i);
                String s1 = attr.getValue();
                String s = attr.getNodeName();
                String s8 = attr.getNamespaceURI();
                if(s8 != null && s8.length() == 0)
                {
                    s8 = null;
                    s = attr.getLocalName();
                }
                if(s1 == null)
                    s1 = fEmptySymbol;
                if(s8 != null)
                {
                    String s4 = attr.getPrefix();
                    s4 = s4 != null ? fSymbolTable.addSymbol(s4) : fEmptySymbol;
                    String s15 = fSymbolTable.addSymbol(attr.getLocalName());
                    if(s4 == fXmlnsSymbol)
                    {
                        s8 = fNSBinder.getURI(s15);
                        String s11 = fLocalNSBinder.getURI(s15);
                        s1 = fSymbolTable.addSymbol(s1);
                        if((s8 == null || s11 == null) && s1.length() != 0)
                        {
                            printNamespaceAttr(s15, s1);
                            fNSBinder.declarePrefix(s15, s1);
                            fLocalNSBinder.declarePrefix(s15, s1);
                        }
                    } else
                    if(s15 == fXmlnsSymbol && s4 == fEmptySymbol)
                    {
                        s8 = fNSBinder.getURI(fEmptySymbol);
                        String s12 = fLocalNSBinder.getURI(fEmptySymbol);
                        s1 = fSymbolTable.addSymbol(s1);
                        if(s12 == null)
                        {
                            printNamespaceAttr(fEmptySymbol, s1);
                            fLocalNSBinder.declarePrefix(fEmptySymbol, s1);
                            fNSBinder.declarePrefix(fEmptySymbol, s1);
                        }
                    } else
                    {
                        s8 = fSymbolTable.addSymbol(s8);
                        String s16 = fNSBinder.getURI(s4);
                        if(s4 == fEmptySymbol || s16 != s8)
                        {
                            s = attr.getNodeName();
                            String s17 = fNSBinder.getPrefix(s8);
                            if(s17 == null || s17 == fEmptySymbol)
                            {
                                if(s4 == fEmptySymbol)
                                    s4 = "NS" + fNamespaceCounter++;
                                printNamespaceAttr(s4, s8);
                                s1 = fSymbolTable.addSymbol(s1);
                                fLocalNSBinder.declarePrefix(s4, s1);
                                fNSBinder.declarePrefix(s4, s8);
                            } else
                            {
                                s4 = s17;
                            }
                            s = s4 + ":" + s15;
                        }
                        printAttribute(s, s1 != null ? s1 : fEmptySymbol, attr.getSpecified());
                    }
                    continue;
                }
                int l = s.indexOf(':');
                int i1 = s.lastIndexOf(':');
                if(s.startsWith(fXmlnsSymbol))
                {
                    if(l < 0)
                    {
                        @SuppressWarnings("unused")
						String s9 = fNSBinder.getURI(fEmptySymbol);
                        String s13 = fLocalNSBinder.getURI(fEmptySymbol);
                        if(s13 == null)
                        {
                            s1 = fSymbolTable.addSymbol(s1);
                            fNSBinder.declarePrefix(fEmptySymbol, s1);
                            fLocalNSBinder.declarePrefix(fEmptySymbol, s1);
                            printAttribute(s, s1, attr.getSpecified());
                        }
                        continue;
                    }
                    if(l == i1)
                    {
                        String s5 = s.substring(6);
                        s5 = s5.length() != 0 ? fSymbolTable.addSymbol(s5) : fEmptySymbol;
                        if(s5.length() == 0)
                        {
                            if(super.fDOMErrorHandler != null)
                            {
                                modifyDOMError("Namespace declaration syntax is incorrect " + s, (short)1);
                                @SuppressWarnings("unused")
								boolean flag3 = super.fDOMErrorHandler.handleError(super.fDOMError);
                            }
                        } else
                        if(s1.length() == 0 && super.fDOMErrorHandler != null)
                        {
                            modifyDOMError("Namespace declaration syntax is incorrect " + s, (short)1);
                            @SuppressWarnings("unused")
							boolean flag4 = super.fDOMErrorHandler.handleError(super.fDOMError);
                        }
                        String s10 = fNSBinder.getURI(s5);
                        String s14 = fLocalNSBinder.getURI(s5);
                        if((s10 == null || s14 == null) && s1.length() != 0)
                        {
                            s1 = fSymbolTable.addSymbol(s1);
                            fNSBinder.declarePrefix(s5, s1);
                            fLocalNSBinder.declarePrefix(s5, s1);
                        }
                    }
                }
                if(l > -1)
                {
                    if(l != i1)
                    {
                        if(super.fDOMErrorHandler != null)
                        {
                            modifyDOMError("Attribute's name is not a QName: " + s, (short)1);
                            @SuppressWarnings("unused")
							boolean flag5 = super.fDOMErrorHandler.handleError(super.fDOMError);
                        }
                    } else
                    if(super.fDOMErrorHandler != null)
                    {
                        modifyDOMError("Attribute '" + s + "' does not belong to any namespace: prefix could be undeclared or bound to some namespace", (short)0);
                        @SuppressWarnings("unused")
						boolean flag6 = super.fDOMErrorHandler.handleError(super.fDOMError);
                    }
                    printAttribute(s, s1, attr.getSpecified());
                } else
                {
                    printAttribute(s, s1, attr.getSpecified());
                }
            }

        }
        if(element.hasChildNodes())
        {
            elementstate = enterElementState(null, null, s2, fPreserveSpace);
            elementstate.doCData = super._format.isCDataElement(s2);
            elementstate.unescaped = super._format.isNonEscapingElement(s2);
            for(Node node = element.getFirstChild(); node != null; node = node.getNextSibling())
                serializeNode(node);

            fNSBinder.popContext();
            endElementIO(null, null, s2);
        } else
        {
            fNSBinder.popContext();
            super._printer.unindent();
            super._printer.printText("/>");
            elementstate.afterElement = true;
            elementstate.afterComment = false;
            elementstate.empty = false;
            if(isDocumentState())
                super._printer.flush();
        }
    }

    private void printNamespaceAttr(String s, String s1)
        throws IOException
    {
        super._printer.printSpace();
        if(s == fEmptySymbol)
            super._printer.printText(fXmlnsSymbol);
        else
            super._printer.printText(fXmlnsSymbol + ":" + s);
        super._printer.printText("=\"");
        printEscaped(s1);
        super._printer.printText('"');
    }

    private void printAttribute(String s, String s1, boolean flag)
        throws IOException
    {
        if(flag || !getFeature("discard-default-content"))
        {
            super._printer.printSpace();
            super._printer.printText(s);
            super._printer.printText("=\"");
            printEscaped(s1);
            super._printer.printText('"');
        }
        if(s.equals("xml:space"))
            if(s1.equals("preserve"))
                fPreserveSpace = true;
            else
                fPreserveSpace = super._format.getPreserveSpace();
    }

    protected String getEntityRef(int i)
    {
        switch(i)
        {
        case 60: // '<'
            return "lt";

        case 62: // '>'
            return "gt";

        case 34: // '"'
            return "quot";

        case 39: // '\''
            return "apos";

        case 38: // '&'
            return "amp";
        }
        return null;
    }

    private Attributes extractNamespaces(Attributes attributes)
        throws SAXException
    {
        if(attributes == null)
            return null;
        int j = attributes.getLength();
        AttributesImpl attributesimpl = new AttributesImpl(attributes);
        for(int i = j - 1; i >= 0; i--)
        {
            String s = attributesimpl.getQName(i);
            if(s.startsWith("xmlns"))
                if(s.length() == 5)
                {
                    startPrefixMapping("", attributes.getValue(i));
                    attributesimpl.removeAttribute(i);
                } else
                if(s.charAt(5) == ':')
                {
                    startPrefixMapping(s.substring(6), attributes.getValue(i));
                    attributesimpl.removeAttribute(i);
                }
        }

        return attributesimpl;
    }

    @SuppressWarnings("unchecked")
	private void initFeatures()
    {
        super.fFeatures.put("normalize-characters", new Boolean(false));
        super.fFeatures.put("split-cdata-sections", new Boolean(true));
        super.fFeatures.put("validation", new Boolean(false));
        super.fFeatures.put("expand-entity-references", new Boolean(false));
        super.fFeatures.put("whitespace-in-element-content", new Boolean(true));
        super.fFeatures.put("discard-default-content", new Boolean(true));
        super.fFeatures.put("format-canonical", new Boolean(false));
        super.fFeatures.put("format-pretty-print", new Boolean(false));
    }

    private void checkAllFeatures()
    {
        if(getFeature("whitespace-in-element-content"))
            super._format.setPreserveSpace(true);
        else
            super._format.setPreserveSpace(false);
    }

    @SuppressWarnings("unchecked")
	public void setFeature(String s, boolean flag)
        throws DOMException
    {
        if(s != null && super.fFeatures.containsKey(s))
        {
            if(canSetFeature(s, flag))
                super.fFeatures.put(s, new Boolean(flag));
            else
                throw new DOMException((short)9, "Feature " + s + " cannot be set as " + flag);
        } else
        {
            throw new DOMException((short)8, "Feature " + s + " not found");
        }
    }

    public boolean canSetFeature(String s, boolean flag)
    {
        if(s.equals("normalize-characters") && flag)
            return false;
        if(s.equals("validation") && flag)
            return false;
        if(s.equals("whitespace-in-element-content") && !flag)
            return false;
        if(s.equals("format-canonical") && flag)
            return false;
        return !s.equals("format-pretty-print") || !flag;
    }

    public boolean getFeature(String s)
        throws DOMException
    {
        Boolean boolean1 = (Boolean)super.fFeatures.get(s);
        if(boolean1 == null)
            throw new DOMException((short)8, "Feature " + s + " not found");
        else
            return boolean1.booleanValue();
    }

    public String getEncoding()
    {
        return fEncoding;
    }

    public void setEncoding(String s)
    {
        super._format.setEncoding(s);
        fEncoding = super._format.getEncoding();
    }

    public String getLastEncoding()
    {
        return fLastEncoding;
    }

    public String getNewLine()
    {
        return super._format.getLineSeparator();
    }

    public void setNewLine(String s)
    {
        super._format.setLineSeparator(s);
    }

    public DOMErrorHandler getErrorHandler()
    {
        return super.fDOMErrorHandler;
    }

    public void setErrorHandler(DOMErrorHandler domerrorhandler)
    {
        super.fDOMErrorHandler = domerrorhandler;
    }

    public boolean writeNode(OutputStream outputstream, Node node)
        throws Exception
    {
        checkAllFeatures();
        try
        {
            setOutputByteStream(outputstream);
            if(node == null)
                return false;
            if(node.getNodeType() == 9)
                serialize((Document)node);
            else
            if(node.getNodeType() == 11)
                serialize((DocumentFragment)node);
            else
            if(node.getNodeType() == 1)
                serialize((Element)node);
            else
                return false;
        }
        catch(NullPointerException nullpointerexception)
        {
            throw nullpointerexception;
        }
        catch(IOException ioexception)
        {
            throw ioexception;
        }
        fLastEncoding = getEncoding();
        return true;
    }

    public String writeToString(Node node)
        throws DOMException
    {
        checkAllFeatures();
        StringWriter stringwriter = new StringWriter();
        try
        {
            setOutputCharStream(stringwriter);
            if(node == null)
                return null;
            if(node.getNodeType() == 9)
                serialize((Document)node);
            else
            if(node.getNodeType() == 11)
                serialize((DocumentFragment)node);
            else
            if(node.getNodeType() == 1)
                serialize((Element)node);
            else
                return null;
        }
        catch(IOException ioexception)
        {
            throw new DOMException((short)2, "The resulting string is too long to fit in a DOMString: " + ioexception.getMessage());
        }
        fLastEncoding = getEncoding();
        return stringwriter.toString();
    }

    public boolean reset()
    {
        super.reset();
        fNSBinder.reset(fSymbolTable);
        fNSBinder.declarePrefix(fEmptySymbol, fEmptySymbol);
        fNamespaceCounter = 1;
        fXmlSymbol = fSymbolTable.addSymbol("xml");
        fXmlnsSymbol = fSymbolTable.addSymbol("xmlns");
        fEmptySymbol = fSymbolTable.addSymbol("");
        return true;
    }

    protected static final boolean DEBUG = false;
    protected final NamespaceSupport fNSBinder;
    protected final NamespaceSupport fLocalNSBinder;
    protected final SymbolTable fSymbolTable;
    protected String fEmptySymbol;
    protected String fXmlSymbol;
    protected String fXmlnsSymbol;
    protected boolean fDOML1;
    protected int fNamespaceCounter;
    private boolean fPreserveSpace;
    private String fEncoding;
    private String fLastEncoding;
}