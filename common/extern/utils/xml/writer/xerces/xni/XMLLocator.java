package common.extern.utils.xml.writer.xerces.xni;

// Decompiled by DJ v3.0.0.63 Copyright 2002 Atanas Neshkov  Date: 2012-10-09 ���� 12:21:28
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   XMLLocator.java

// Referenced classes of package org.apache.xerces.xni:
//            XMLResourceIdentifier

public interface XMLLocator extends XMLResourceIdentifier
{

    public abstract int getLineNumber();

    public abstract int getColumnNumber();
}