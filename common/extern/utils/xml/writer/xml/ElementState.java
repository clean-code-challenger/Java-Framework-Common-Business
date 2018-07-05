package common.extern.utils.xml.writer.xml;

// Decompiled by DJ v3.0.0.63 Copyright 2002 Atanas Neshkov  Date: 2012-10-09 ���� 12:26:44
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ElementState.java

import java.util.Hashtable;

public class ElementState
{

    public ElementState()
    {
    }

    public String rawName;
    public String localName;
    public String namespaceURI;
    public boolean preserveSpace;
    public boolean empty;
    public boolean afterElement;
    public boolean afterComment;
    public boolean doCData;
    public boolean unescaped;
    public boolean inCData;
    public Hashtable prefixes;
}