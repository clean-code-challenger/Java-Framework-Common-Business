package common.extern.utils.xml.writer.xerces.xni.parser;

// Decompiled by DJ v3.0.0.63 Copyright 2002 Atanas Neshkov  Date: 2012-10-09 ���� 12:18:48
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   XMLParseException.java

import common.extern.utils.xml.writer.xerces.xni.*;

public class XMLParseException extends XNIException
{

    /****/
	private static final long serialVersionUID = -5175927998711258323L;
	public XMLParseException(XMLLocator xmllocator, String s)
    {
        super(s);
        fLineNumber = -1;
        fColumnNumber = -1;
        if(xmllocator != null)
        {
            fPublicId = xmllocator.getPublicId();
            fLiteralSystemId = xmllocator.getLiteralSystemId();
            fExpandedSystemId = xmllocator.getExpandedSystemId();
            fBaseSystemId = xmllocator.getBaseSystemId();
            fLineNumber = xmllocator.getLineNumber();
            fColumnNumber = xmllocator.getColumnNumber();
        }
    }

    public XMLParseException(XMLLocator xmllocator, String s, Exception exception)
    {
        super(s, exception);
        fLineNumber = -1;
        fColumnNumber = -1;
        fPublicId = xmllocator.getPublicId();
        fLiteralSystemId = xmllocator.getLiteralSystemId();
        fExpandedSystemId = xmllocator.getExpandedSystemId();
        fBaseSystemId = xmllocator.getBaseSystemId();
        fLineNumber = xmllocator.getLineNumber();
        fColumnNumber = xmllocator.getColumnNumber();
    }

    public String getPublicId()
    {
        return fPublicId;
    }

    public String getExpandedSystemId()
    {
        return fExpandedSystemId;
    }

    public String getLiteralSystemId()
    {
        return fLiteralSystemId;
    }

    public String getBaseSystemId()
    {
        return fBaseSystemId;
    }

    public int getLineNumber()
    {
        return fLineNumber;
    }

    public int getColumnNumber()
    {
        return fColumnNumber;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        if(fPublicId != null)
            stringbuffer.append(fPublicId);
        stringbuffer.append(':');
        if(fPublicId != null)
            stringbuffer.append(fPublicId);
        stringbuffer.append(':');
        if(fLiteralSystemId != null)
            stringbuffer.append(fLiteralSystemId);
        stringbuffer.append(':');
        if(fExpandedSystemId != null)
            stringbuffer.append(fExpandedSystemId);
        stringbuffer.append(':');
        if(fBaseSystemId != null)
            stringbuffer.append(fBaseSystemId);
        stringbuffer.append(':');
        stringbuffer.append(fLineNumber);
        stringbuffer.append(':');
        stringbuffer.append(fColumnNumber);
        stringbuffer.append(':');
        String s = getMessage();
        if(s == null)
        {
            Exception exception = getException();
            if(exception != null)
                s = exception.getMessage();
        }
        if(s != null)
            stringbuffer.append(s);
        return stringbuffer.toString();
    }

    protected String fPublicId;
    protected String fLiteralSystemId;
    protected String fExpandedSystemId;
    protected String fBaseSystemId;
    protected int fLineNumber;
    protected int fColumnNumber;
}