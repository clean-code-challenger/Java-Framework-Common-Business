package common.extern.utils.xml.writer.xerces.util;

// Decompiled by DJ v3.0.0.63 Copyright 2002 Atanas Neshkov  Date: 2012-10-09 ���� 12:32:33
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   NamespaceContext.java

public interface NamespaceContext
{

    public abstract String getURI(String s);

    public abstract int getDeclaredPrefixCount();

    public abstract String getDeclaredPrefixAt(int i);

    public abstract NamespaceContext getParentContext();

    public static final String XML_URI = "http://www.w3.org/XML/1998/namespace";
    public static final String XMLNS_URI = "http://www.w3.org/2000/xmlns/";
}