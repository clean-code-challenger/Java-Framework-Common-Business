package common.extern.utils.xml.writer.xml;

// Decompiled by DJ v3.0.0.63 Copyright 2002 Atanas Neshkov  Date: 2012-10-09 ���� 11:57:21
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   EncodingInfo.java

import java.io.*;

public class EncodingInfo
{

    public EncodingInfo(String s, String s1, int i)
    {
        name = s;
        javaName = s1 != null ? s1 : s;
        lastPrintable = i;
    }

    public EncodingInfo(String s, int i)
    {
        this(s, s, i);
    }

    public String getName()
    {
        return name;
    }

    public Writer getWriter(OutputStream outputstream)
        throws UnsupportedEncodingException
    {
        if(javaName == null)
            return new OutputStreamWriter(outputstream);
        else
            return new OutputStreamWriter(outputstream, javaName);
    }

    public boolean isPrintable(int i)
    {
        return i <= lastPrintable;
    }

    String name;
    String javaName;
    int lastPrintable;
}