package common.extern.utils.xml.writer.xerces.dom;
//package common.extern.utils.xml.writer.xerces.dom;

// Decompiled by DJ v3.0.0.63 Copyright 2002 Atanas Neshkov  Date: 2012-10-09 ���� 12:16:04
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DOMLocatorImpl.java

import org.w3c.dom.Node;

import common.extern.utils.xml.writer.xerces.dom3.DOMLocator;

public class DOMLocatorImpl implements DOMLocator{

    public DOMLocatorImpl(int i, int j, String s)
    {
        fColumnNumber = -1;
        fErrorNode = null;
        fLineNumber = -1;
        fOffset = -1;
        fUri = null;
        fLineNumber = i;
        fColumnNumber = j;
        fUri = s;
    }

    public DOMLocatorImpl(int i, int j, int k, Node node, String s)
    {
        fColumnNumber = -1;
        fErrorNode = null;
        fLineNumber = -1;
        fOffset = -1;
        fUri = null;
        fLineNumber = i;
        fColumnNumber = j;
        fOffset = k;
        fErrorNode = node;
        fUri = s;
    }

    public int getLineNumber()
    {
        return fLineNumber;
    }

    public int getColumnNumber()
    {
        return fColumnNumber;
    }

    public int getOffset()
    {
        return fOffset;
    }

    public Node getErrorNode()
    {
        return fErrorNode;
    }

    public String getUri()
    {
        return fUri;
    }

    int fColumnNumber;
    Node fErrorNode;
    int fLineNumber;
    int fOffset;
    String fUri;
}