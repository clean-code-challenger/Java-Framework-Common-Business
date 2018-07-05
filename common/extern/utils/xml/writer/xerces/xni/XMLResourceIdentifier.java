package common.extern.utils.xml.writer.xerces.xni;

// Decompiled by DJ v3.0.0.63 Copyright 2002 Atanas Neshkov  Date: 2012-10-09 ���� 12:22:34
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   XMLResourceIdentifier.java


public interface XMLResourceIdentifier
{

    public abstract String getPublicId();

    public abstract String getExpandedSystemId();

    public abstract String getLiteralSystemId();

    public abstract String getBaseSystemId();
}