package common.extern.utils.xml.writer.xerces.dom;

// Decompiled by DJ v3.0.0.63 Copyright 2002 Atanas Neshkov  Date: 2012-10-09 ���� 12:13:44
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DOMErrorImpl.java

import common.extern.utils.xml.writer.xerces.dom3.DOMError;
import common.extern.utils.xml.writer.xerces.dom3.DOMLocator;
import common.extern.utils.xml.writer.xerces.xni.parser.XMLParseException;

// Referenced classes of package org.apache.xerces.dom:
//            DOMLocatorImpl

public class DOMErrorImpl implements DOMError
{

    public DOMErrorImpl()
    {
        fSeverity = 0;
        fMessage = null;
        fLocation = null;
        fException = null;
    }

    public DOMErrorImpl(short word0, XMLParseException xmlparseexception)
    {
        fSeverity = 0;
        fMessage = null;
        fLocation = null;
        fException = null;
        fSeverity = word0;
        fException = xmlparseexception;
        fMessage = xmlparseexception.getMessage();
        fLocation = createDOMLocator(xmlparseexception);
    }

    public short getSeverity()
    {
        return fSeverity;
    }

    public String getMessage()
    {
        return fMessage;
    }

    public Object getException()
    {
        return fException;
    }

    public DOMLocator getLocation()
    {
        return fLocation;
    }

    private DOMLocator createDOMLocator(XMLParseException xmlparseexception)
    {
        return new DOMLocatorImpl(xmlparseexception.getLineNumber(), xmlparseexception.getColumnNumber(), xmlparseexception.getExpandedSystemId());
    }

    public void setSeverity(short word0)
    {
        fSeverity = word0;
    }

    public void setMessage(String s)
    {
        fMessage = s;
    }

    public void setLocator(DOMLocator domlocator)
    {
        fLocation = domlocator;
    }

    public void setException(Exception exception)
    {
        fException = exception;
    }

    public void reset()
    {
        fSeverity = 0;
        fMessage = null;
        fLocation = null;
        fException = null;
    }

    short fSeverity;
    String fMessage;
    DOMLocator fLocation;
    Exception fException;
}