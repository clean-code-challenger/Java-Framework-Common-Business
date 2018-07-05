package common.extern.utils.xml.writer.xml;

// Decompiled by DJ v3.0.0.63 Copyright 2002 Atanas Neshkov  Date: 2012-10-09 ���� 12:25:09
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DOMSerializer.java

import java.io.IOException;
import org.w3c.dom.*;

public interface DOMSerializer
{

    public abstract void serialize(Element element)
        throws IOException;

    public abstract void serialize(Document document)
        throws IOException;

    public abstract void serialize(DocumentFragment documentfragment)
        throws IOException;
}