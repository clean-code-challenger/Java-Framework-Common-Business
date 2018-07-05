package common.extern.utils.xml.writer.xerces.dom3;

// Decompiled by DJ v3.0.0.63 Copyright 2002 Atanas Neshkov  Date: 2012-10-09 ���� 12:17:44
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DOMError.java

// Referenced classes of package org.apache.xerces.dom3:
//            DOMLocator

public interface DOMError
{

    public abstract short getSeverity();

    public abstract String getMessage();

    public abstract Object getException();

    public abstract DOMLocator getLocation();

    public static final short SEVERITY_WARNING = 0;
    public static final short SEVERITY_ERROR = 1;
    public static final short SEVERITY_FATAL_ERROR = 2;
}