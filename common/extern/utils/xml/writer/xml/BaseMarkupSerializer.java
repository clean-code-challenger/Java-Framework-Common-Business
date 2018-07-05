package common.extern.utils.xml.writer.xml;

import java.io.*;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Vector;
import common.extern.utils.xml.writer.xerces.dom.DOMErrorImpl;
import common.extern.utils.xml.writer.xerces.dom.DOMLocatorImpl;
import common.extern.utils.xml.writer.xerces.dom3.DOMError;
import common.extern.utils.xml.writer.xerces.dom3.DOMErrorHandler;
import org.w3c.dom.*;
import org.xml.sax.*;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;

@SuppressWarnings("deprecation")
public abstract class BaseMarkupSerializer implements ContentHandler, DocumentHandler, LexicalHandler, DTDHandler, DeclHandler, DOMSerializer, Serializer
{

    protected BaseMarkupSerializer(OutputFormat outputformat)
    {
        fCurrentNode = null;
        _elementStates = new ElementState[10];
        for(int i = 0; i < _elementStates.length; i++)
            _elementStates[i] = new ElementState();

        _format = outputformat;
    }

    public DocumentHandler asDocumentHandler()
        throws IOException
    {
        prepare();
        return this;
    }

    public ContentHandler asContentHandler()
        throws IOException
    {
        prepare();
        return this;
    }

    public DOMSerializer asDOMSerializer()
        throws IOException
    {
        prepare();
        return this;
    }

    public void setOutputByteStream(OutputStream outputstream)
    {
        if(outputstream == null)
        {
            throw new NullPointerException("SER001 Argument 'output' is null.");
        } else
        {
            _output = outputstream;
            _writer = null;
            reset();
            return;
        }
    }

    public void setOutputCharStream(Writer writer)
    {
        if(writer == null)
        {
            throw new NullPointerException("SER001 Argument 'writer' is null.");
        } else
        {
            _writer = writer;
            _output = null;
            reset();
            return;
        }
    }

    public void setOutputFormat(OutputFormat outputformat)
    {
        if(outputformat == null)
        {
            throw new NullPointerException("SER001 Argument 'format' is null.");
        } else
        {
            _format = outputformat;
            reset();
            return;
        }
    }

    public boolean reset()
    {
        if(_elementStateCount > 1)
        {
            throw new IllegalStateException("Serializer reset in the middle of serialization");
        } else
        {
            _prepared = false;
            fCurrentNode = null;
            fStrBuffer.setLength(0);
            return true;
        }
    }

    protected void prepare()
        throws IOException
    {
        if(_prepared)
            return;
        if(_writer == null && _output == null)
            throw new IOException("SER002 No writer supplied for serializer");
        _encodingInfo = _format.getEncodingInfo();
        if(_output != null)
            _writer = _encodingInfo.getWriter(_output);
        if(_format.getIndenting())
        {
            _indenting = true;
            _printer = new IndentPrinter(_writer, _format);
        } else
        {
            _indenting = false;
            _printer = new Printer(_writer, _format);
        }
        _elementStateCount = 0;
        ElementState elementstate = _elementStates[0];
        elementstate.namespaceURI = null;
        elementstate.localName = null;
        elementstate.rawName = null;
        elementstate.preserveSpace = _format.getPreserveSpace();
        elementstate.empty = true;
        elementstate.afterElement = false;
        elementstate.afterComment = false;
        elementstate.doCData = elementstate.inCData = false;
        elementstate.prefixes = null;
        _docTypePublicId = _format.getDoctypePublic();
        _docTypeSystemId = _format.getDoctypeSystem();
        _started = false;
        _prepared = true;
    }

    public void serialize(Element element)
        throws IOException
    {
        reset();
        prepare();
        serializeNode(element);
        _printer.flush();
        if(_printer.getException() != null)
            throw _printer.getException();
        else
            return;
    }

    public void serialize(DocumentFragment documentfragment)
        throws IOException
    {
        reset();
        prepare();
        serializeNode(documentfragment);
        _printer.flush();
        if(_printer.getException() != null)
            throw _printer.getException();
        else
            return;
    }

    public void serialize(Document document)
        throws IOException
    {
        reset();
        prepare();
        serializeNode(document);
        serializePreRoot();
        _printer.flush();
        if(_printer.getException() != null)
            throw _printer.getException();
        else
            return;
    }

    public void startDocument()
        throws SAXException
    {
        try
        {
            prepare();
        }
        catch(IOException ioexception)
        {
            throw new SAXException(ioexception.toString());
        }
    }

    public void characters(char ac[], int i, int j)
        throws SAXException
    {
        try
        {
            ElementState elementstate = content();
            if(elementstate.inCData || elementstate.doCData)
            {
                if(!elementstate.inCData)
                {
                    _printer.printText("<![CDATA[");
                    elementstate.inCData = true;
                }
                int k = _printer.getNextIndent();
                _printer.setNextIndent(0);
                for(int i1 = 0; i1 < j; i1++)
                    if(i1 + 2 < j && ac[i1] == ']' && ac[i1 + 1] == ']' && ac[i1 + 2] == '>')
                    {
                        printText(ac, i, i1 + 2, true, true);
                        _printer.printText("]]><![CDATA[");
                        i += i1 + 2;
                        j -= i1 + 2;
                        i1 = 0;
                    }

                if(j > 0)
                    printText(ac, i, j, true, true);
                _printer.setNextIndent(k);
            } else
            if(elementstate.preserveSpace)
            {
                int l = _printer.getNextIndent();
                _printer.setNextIndent(0);
                printText(ac, i, j, true, elementstate.unescaped);
                _printer.setNextIndent(l);
            } else
            {
                printText(ac, i, j, false, elementstate.unescaped);
            }
        }
        catch(IOException ioexception)
        {
            throw new SAXException(ioexception);
        }
    }

    public void ignorableWhitespace(char ac[], int i, int j)
        throws SAXException
    {
        try
        {
            content();
            if(_indenting)
            {
                _printer.setThisIndent(0);
                for(int k = i; j-- > 0; k++)
                    _printer.printText(ac[k]);

            }
        }
        catch(IOException ioexception)
        {
            throw new SAXException(ioexception);
        }
    }

    public final void processingInstruction(String s, String s1)
        throws SAXException
    {
        try
        {
            processingInstructionIO(s, s1);
        }
        catch(IOException ioexception)
        {
            throw new SAXException(ioexception);
        }
    }

    @SuppressWarnings("unchecked")
	public void processingInstructionIO(String s, String s1)
        throws IOException
    {
        ElementState elementstate = content();
        int i = s.indexOf("?>");
        if(i >= 0)
            fStrBuffer.append("<?").append(s.substring(0, i));
        else
            fStrBuffer.append("<?").append(s);
        if(s1 != null)
        {
            fStrBuffer.append(' ');
            int j = s1.indexOf("?>");
            if(j >= 0)
                fStrBuffer.append(s1.substring(0, j));
            else
                fStrBuffer.append(s1);
        }
        fStrBuffer.append("?>");
        if(isDocumentState())
        {
            if(_preRoot == null)
                _preRoot = new Vector();
            _preRoot.addElement(fStrBuffer.toString());
        } else
        {
            _printer.indent();
            printText(fStrBuffer.toString(), true, true);
            _printer.unindent();
            if(_indenting)
                elementstate.afterElement = true;
        }
        fStrBuffer.setLength(0);
    }

    public void comment(char ac[], int i, int j)
        throws SAXException
    {
        try
        {
            comment(new String(ac, i, j));
        }
        catch(IOException ioexception)
        {
            throw new SAXException(ioexception);
        }
    }

    @SuppressWarnings("unchecked")
	public void comment(String s)
        throws IOException
    {
        if(_format.getOmitComments())
            return;
        ElementState elementstate = content();
        int i = s.indexOf("-->");
        if(i >= 0)
            fStrBuffer.append("<!--").append(s.substring(0, i)).append("-->");
        else
            fStrBuffer.append("<!--").append(s).append("-->");
        if(isDocumentState())
        {
            if(_preRoot == null)
                _preRoot = new Vector();
            _preRoot.addElement(fStrBuffer.toString());
        } else
        {
            if(_indenting && !elementstate.preserveSpace)
                _printer.breakLine();
            _printer.indent();
            printText(fStrBuffer.toString(), true, true);
            _printer.unindent();
            if(_indenting)
                elementstate.afterElement = true;
        }
        fStrBuffer.setLength(0);
        elementstate.afterComment = true;
        elementstate.afterElement = false;
    }

    public void startCDATA()
    {
        ElementState elementstate = getElementState();
        elementstate.doCData = true;
    }

    public void endCDATA()
    {
        ElementState elementstate = getElementState();
        elementstate.doCData = false;
    }

    public void startNonEscaping()
    {
        ElementState elementstate = getElementState();
        elementstate.unescaped = true;
    }

    public void endNonEscaping()
    {
        ElementState elementstate = getElementState();
        elementstate.unescaped = false;
    }

    public void startPreserving()
    {
        ElementState elementstate = getElementState();
        elementstate.preserveSpace = true;
    }

    public void endPreserving()
    {
        ElementState elementstate = getElementState();
        elementstate.preserveSpace = false;
    }

    public void endDocument()
        throws SAXException
    {
        try
        {
            serializePreRoot();
            _printer.flush();
        }
        catch(IOException ioexception)
        {
            throw new SAXException(ioexception);
        }
    }

    public void startEntity(String s)
    {
    }

    public void endEntity(String s)
    {
    }

    public void setDocumentLocator(Locator locator)
    {
    }

    public void skippedEntity(String s)
        throws SAXException
    {
        try
        {
            endCDATA();
            content();
            _printer.printText('&');
            _printer.printText(s);
            _printer.printText(';');
        }
        catch(IOException ioexception)
        {
            throw new SAXException(ioexception);
        }
    }

    @SuppressWarnings("unchecked")
	public void startPrefixMapping(String s, String s1)
        throws SAXException
    {
        if(_prefixes == null)
            _prefixes = new Hashtable();
        _prefixes.put(s1, s != null ? ((Object) (s)) : "");
    }

    public void endPrefixMapping(String s)
        throws SAXException
    {
    }

    public final void startDTD(String s, String s1, String s2)
        throws SAXException
    {
        try
        {
            _printer.enterDTD();
            _docTypePublicId = s1;
            _docTypeSystemId = s2;
        }
        catch(IOException ioexception)
        {
            throw new SAXException(ioexception);
        }
    }

    public void endDTD()
    {
    }

    public void elementDecl(String s, String s1)
        throws SAXException
    {
        try
        {
            _printer.enterDTD();
            _printer.printText("<!ELEMENT ");
            _printer.printText(s);
            _printer.printText(' ');
            _printer.printText(s1);
            _printer.printText('>');
            if(_indenting)
                _printer.breakLine();
        }
        catch(IOException ioexception)
        {
            throw new SAXException(ioexception);
        }
    }

    public void attributeDecl(String s, String s1, String s2, String s3, String s4)
        throws SAXException
    {
        try
        {
            _printer.enterDTD();
            _printer.printText("<!ATTLIST ");
            _printer.printText(s);
            _printer.printText(' ');
            _printer.printText(s1);
            _printer.printText(' ');
            _printer.printText(s2);
            if(s3 != null)
            {
                _printer.printText(' ');
                _printer.printText(s3);
            }
            if(s4 != null)
            {
                _printer.printText(" \"");
                printEscaped(s4);
                _printer.printText('"');
            }
            _printer.printText('>');
            if(_indenting)
                _printer.breakLine();
        }
        catch(IOException ioexception)
        {
            throw new SAXException(ioexception);
        }
    }

    public void internalEntityDecl(String s, String s1)
        throws SAXException
    {
        try
        {
            _printer.enterDTD();
            _printer.printText("<!ENTITY ");
            _printer.printText(s);
            _printer.printText(" \"");
            printEscaped(s1);
            _printer.printText("\">");
            if(_indenting)
                _printer.breakLine();
        }
        catch(IOException ioexception)
        {
            throw new SAXException(ioexception);
        }
    }

    public void externalEntityDecl(String s, String s1, String s2)
        throws SAXException
    {
        try
        {
            _printer.enterDTD();
            unparsedEntityDecl(s, s1, s2, null);
        }
        catch(IOException ioexception)
        {
            throw new SAXException(ioexception);
        }
    }

    public void unparsedEntityDecl(String s, String s1, String s2, String s3)
        throws SAXException
    {
        try
        {
            _printer.enterDTD();
            if(s1 == null)
            {
                _printer.printText("<!ENTITY ");
                _printer.printText(s);
                _printer.printText(" SYSTEM ");
                printDoctypeURL(s2);
            } else
            {
                _printer.printText("<!ENTITY ");
                _printer.printText(s);
                _printer.printText(" PUBLIC ");
                printDoctypeURL(s1);
                _printer.printText(' ');
                printDoctypeURL(s2);
            }
            if(s3 != null)
            {
                _printer.printText(" NDATA ");
                _printer.printText(s3);
            }
            _printer.printText('>');
            if(_indenting)
                _printer.breakLine();
        }
        catch(IOException ioexception)
        {
            throw new SAXException(ioexception);
        }
    }

    public void notationDecl(String s, String s1, String s2)
        throws SAXException
    {
        try
        {
            _printer.enterDTD();
            if(s1 != null)
            {
                _printer.printText("<!NOTATION ");
                _printer.printText(s);
                _printer.printText(" PUBLIC ");
                printDoctypeURL(s1);
                if(s2 != null)
                {
                    _printer.printText(' ');
                    printDoctypeURL(s2);
                }
            } else
            {
                _printer.printText("<!NOTATION ");
                _printer.printText(s);
                _printer.printText(" SYSTEM ");
                printDoctypeURL(s2);
            }
            _printer.printText('>');
            if(_indenting)
                _printer.breakLine();
        }
        catch(IOException ioexception)
        {
            throw new SAXException(ioexception);
        }
    }

    protected void serializeNode(Node node)
        throws IOException
    {
        fCurrentNode = node;
        switch(node.getNodeType())
        {
        case 2: // '\002'
        case 6: // '\006'
        case 10: // '\n'
        default:
            break;

        case 3: // '\003'
            String s = node.getNodeValue();
            if(s != null && (!_indenting || getElementState().preserveSpace || s.replace('\n', ' ').trim().length() != 0))
                characters(s);
            break;

        case 4: // '\004'
            String s1 = node.getNodeValue();
            if(s1 != null)
            {
                startCDATA();
                characters(s1);
                endCDATA();
            }
            break;

        case 8: // '\b'
            if(_format.getOmitComments())
                break;
            String s2 = node.getNodeValue();
            if(s2 != null)
                comment(s2);
            break;

        case 5: // '\005'
            endCDATA();
            content();
            Node node1 = node.getFirstChild();
            if(node1 == null || !((Boolean)fFeatures.get("expand-entity-references")).booleanValue())
            {
                _printer.printText("&");
                _printer.printText(node.getNodeName());
                _printer.printText(";");
                break;
            }
            for(; node1 != null; node1 = node1.getNextSibling())
                serializeNode(node1);

            break;

        case 7: // '\007'
            processingInstructionIO(node.getNodeName(), node.getNodeValue());
            break;

        case 1: // '\001'
            serializeElement((Element)node);
            break;

        case 9: // '\t'
            DocumentType documenttype = ((Document)node).getDoctype();
            if(documenttype != null)
            {
                @SuppressWarnings("unused")
				org.w3c.dom.DOMImplementation domimplementation = ((Document)node).getImplementation();
                try
                {
                    _printer.enterDTD();
                    _docTypePublicId = documenttype.getPublicId();
                    _docTypeSystemId = documenttype.getSystemId();
                    String s3 = documenttype.getInternalSubset();
                    if(s3 != null && s3.length() > 0)
                        _printer.printText(s3);
                    endDTD();
                }
                catch(NoSuchMethodError nosuchmethoderror)
                {
                    Class class1 = documenttype.getClass();
                    String s4 = null;
                    String s5 = null;
                    try
                    {
                        Method method = class1.getMethod("getPublicId", null);
                        if(method.getReturnType().equals(java.lang.String.class))
                            s4 = (String)method.invoke(documenttype, null);
                    }
                    catch(Exception exception) { }
                    try
                    {
                        Method method1 = class1.getMethod("getSystemId", null);
                        if(method1.getReturnType().equals(java.lang.String.class))
                            s5 = (String)method1.invoke(documenttype, null);
                    }
                    catch(Exception exception1) { }
                    _printer.enterDTD();
                    _docTypePublicId = s4;
                    _docTypeSystemId = s5;
                    endDTD();
                }
            }
            // fall through

        case 11: // '\013'
            for(Node node2 = node.getFirstChild(); node2 != null; node2 = node2.getNextSibling())
                serializeNode(node2);

            break;
        }
    }

    protected ElementState content()
        throws IOException
    {
        ElementState elementstate = getElementState();
        if(!isDocumentState())
        {
            if(elementstate.inCData && !elementstate.doCData)
            {
                _printer.printText("]]>");
                elementstate.inCData = false;
            }
            if(elementstate.empty)
            {
                _printer.printText('>');
                elementstate.empty = false;
            }
            elementstate.afterElement = false;
            elementstate.afterComment = false;
        }
        return elementstate;
    }

    protected void characters(String s)
        throws IOException
    {
        ElementState elementstate = content();
        if(elementstate.inCData || elementstate.doCData)
        {
            if(!elementstate.inCData)
            {
                fStrBuffer.append("<![CDATA[");
                elementstate.inCData = true;
            }
            int i = s.indexOf("]]>");
            if(i >= 0 && !((Boolean)fFeatures.get("split-cdata-sections")).booleanValue())
            {
                if(fDOMErrorHandler != null)
                {
                    modifyDOMError("The character sequence \"]]>\" must not appear in content unless used to mark the end of a CDATA section.", (short)2);
                    boolean flag = fDOMErrorHandler.handleError(fDOMError);
                    if(!flag)
                        throw new IOException();
                }
            } else
            if(i >= 0 && ((Boolean)fFeatures.get("split-cdata-sections")).booleanValue() && fDOMErrorHandler != null)
            {
                modifyDOMError("Spliting a CDATA section containing the CDATA section termination marker ']]>' ", (short)0);
                fDOMErrorHandler.handleError(fDOMError);
            }
            for(; i >= 0; i = s.indexOf("]]>"))
            {
                fStrBuffer.append(s.substring(0, i + 2)).append("]]><![CDATA[");
                s = s.substring(i + 2);
            }

            fStrBuffer.append(s);
            int k = _printer.getNextIndent();
            _printer.setNextIndent(0);
            printText(fStrBuffer.toString(), true, true);
            fStrBuffer.setLength(0);
            _printer.setNextIndent(k);
        } else
        if(elementstate.preserveSpace)
        {
            int j = _printer.getNextIndent();
            _printer.setNextIndent(0);
            printText(s, true, elementstate.unescaped);
            _printer.setNextIndent(j);
        } else
        {
            printText(s, false, elementstate.unescaped);
        }
    }

    protected abstract String getEntityRef(int i);

    protected abstract void serializeElement(Element element)
        throws IOException;

    protected void serializePreRoot()
        throws IOException
    {
        if(_preRoot != null)
        {
            for(int i = 0; i < _preRoot.size(); i++)
            {
                printText((String)_preRoot.elementAt(i), true, true);
                if(_indenting)
                    _printer.breakLine();
            }

            _preRoot.removeAllElements();
        }
    }

    protected final void printText(char ac[], int i, int j, boolean flag, boolean flag1)
        throws IOException
    {
        if(flag)
            while(j-- > 0) 
            {
                char c = ac[i];
                i++;
                if(c == '\n' || c == '\r' || flag1)
                    _printer.printText(c);
                else
                    printEscaped(c);
            }
        else
            while(j-- > 0) 
            {
                char c1 = ac[i];
                i++;
                if(c1 == ' ' || c1 == '\f' || c1 == '\t' || c1 == '\n' || c1 == '\r')
                    _printer.printSpace();
                else
                if(flag1)
                    _printer.printText(c1);
                else
                    printEscaped(c1);
            }
    }

    protected final void printText(String s, boolean flag, boolean flag1)
        throws IOException
    {
        if(flag)
        {
            for(int i = 0; i < s.length(); i++)
            {
                char c = s.charAt(i);
                if(c == '\n' || c == '\r' || flag1)
                    _printer.printText(c);
                else
                    printEscaped(c);
            }

        } else
        {
            for(int j = 0; j < s.length(); j++)
            {
                char c1 = s.charAt(j);
                if(c1 == ' ' || c1 == '\f' || c1 == '\t' || c1 == '\n' || c1 == '\r')
                    _printer.printSpace();
                else
                if(flag1)
                    _printer.printText(c1);
                else
                    printEscaped(c1);
            }

        }
    }

    protected void printDoctypeURL(String s)
        throws IOException
    {
        _printer.printText('"');
        for(int i = 0; i < s.length(); i++)
            if(s.charAt(i) == '"' || s.charAt(i) < ' ' || s.charAt(i) > '\177')
            {
                _printer.printText('%');
                _printer.printText(Integer.toHexString(s.charAt(i)));
            } else
            {
                _printer.printText(s.charAt(i));
            }

        _printer.printText('"');
    }

    protected void printEscaped(int i)
        throws IOException
    {
        String s = getEntityRef(i);
        if(s != null)
        {
            _printer.printText('&');
            _printer.printText(s);
            _printer.printText(';');
        } else
        if(i >= 32 && _encodingInfo.isPrintable(i) && i != 247 || i == 10 || i == 13 || i == 9)
        {
            if(i < 0x10000)
            {
                _printer.printText((char)i);
            } else
            {
                _printer.printText((char)((i - 0x10000 >> 10) + 55296));
                _printer.printText((char)((i - 0x10000 & 0x3ff) + 56320));
            }
        } else
        {
            _printer.printText("&#x");
            _printer.printText(Integer.toHexString(i));
            _printer.printText(';');
        }
    }

    protected void printEscaped(String s)
        throws IOException
    {
        for(int i = 0; i < s.length(); i++)
        {
            int j = s.charAt(i);
            if((j & 0xfc00) == 55296 && i + 1 < s.length())
            {
                char c = s.charAt(i + 1);
                if((c & 0xfc00) == 56320)
                {
                    j = (0x10000 + (j - 55296 << 10) + c) - 56320;
                    i++;
                }
            }
            printEscaped(j);
        }

    }

    protected ElementState getElementState()
    {
        return _elementStates[_elementStateCount];
    }

    protected ElementState enterElementState(String s, String s1, String s2, boolean flag)
    {
        if(_elementStateCount + 1 == _elementStates.length)
        {
            ElementState aelementstate[] = new ElementState[_elementStates.length + 10];
            for(int i = 0; i < _elementStates.length; i++)
                aelementstate[i] = _elementStates[i];

            for(int j = _elementStates.length; j < aelementstate.length; j++)
                aelementstate[j] = new ElementState();

            _elementStates = aelementstate;
        }
        _elementStateCount++;
        ElementState elementstate = _elementStates[_elementStateCount];
        elementstate.namespaceURI = s;
        elementstate.localName = s1;
        elementstate.rawName = s2;
        elementstate.preserveSpace = flag;
        elementstate.empty = true;
        elementstate.afterElement = false;
        elementstate.afterComment = false;
        elementstate.doCData = elementstate.inCData = false;
        elementstate.unescaped = false;
        elementstate.prefixes = _prefixes;
        _prefixes = null;
        return elementstate;
    }

    protected ElementState leaveElementState()
    {
        if(_elementStateCount > 0)
        {
            _prefixes = null;
            _elementStateCount--;
            return _elementStates[_elementStateCount];
        } else
        {
            throw new IllegalStateException("Internal error: element state is zero");
        }
    }

    protected boolean isDocumentState()
    {
        return _elementStateCount == 0;
    }

    protected String getPrefix(String s)
    {
        if(_prefixes != null)
        {
            String s1 = (String)_prefixes.get(s);
            if(s1 != null)
                return s1;
        }
        if(_elementStateCount == 0)
            return null;
        for(int i = _elementStateCount; i > 0; i--)
            if(_elementStates[i].prefixes != null)
            {
                String s2 = (String)_elementStates[i].prefixes.get(s);
                if(s2 != null)
                    return s2;
            }

        return null;
    }

    protected DOMError modifyDOMError(String s, short word0)
    {
        fDOMError.reset();
        fDOMError.setMessage(s);
        fDOMError.setSeverity(word0);
        fDOMError.setLocator(new DOMLocatorImpl(-1, -1, -1, fCurrentNode, null));
        return fDOMError;
    }

    public abstract void endElement(String s, String s1, String s2)
        throws SAXException;

    public abstract void startElement(String s, String s1, String s2, Attributes attributes)
        throws SAXException;

    public abstract void endElement(String s)
        throws SAXException;

    public abstract void startElement(String s, AttributeList attributelist)
        throws SAXException;

    protected Hashtable fFeatures;
    protected DOMErrorHandler fDOMErrorHandler;
    protected final DOMErrorImpl fDOMError = new DOMErrorImpl();
    private EncodingInfo _encodingInfo;
    private ElementState _elementStates[];
    private int _elementStateCount;
    private Vector _preRoot;
    protected boolean _started;
    private boolean _prepared;
    protected Hashtable _prefixes;
    protected String _docTypePublicId;
    protected String _docTypeSystemId;
    protected OutputFormat _format;
    protected Printer _printer;
    protected boolean _indenting;
    protected final StringBuffer fStrBuffer = new StringBuffer(40);
    private Writer _writer;
    private OutputStream _output;
    private Node fCurrentNode;
}