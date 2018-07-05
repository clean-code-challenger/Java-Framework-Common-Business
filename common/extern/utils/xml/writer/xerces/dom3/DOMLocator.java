package common.extern.utils.xml.writer.xerces.dom3;

// Decompiled by DJ v3.0.0.63 Copyright 2002 Atanas Neshkov  Date: 2012-10-09 ���� 12:16:42
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DOMLocator.java
import org.w3c.dom.Node;

public interface DOMLocator
{

    public abstract int getLineNumber();

    public abstract int getColumnNumber();

    public abstract int getOffset();

    public abstract Node getErrorNode();

    public abstract String getUri();
}