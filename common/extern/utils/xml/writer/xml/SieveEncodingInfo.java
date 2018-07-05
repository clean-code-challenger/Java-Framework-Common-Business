package common.extern.utils.xml.writer.xml;

// Decompiled by DJ v3.0.0.63 Copyright 2002 Atanas Neshkov  Date: 2012-10-09 ���� 11:59:00
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   SieveEncodingInfo.java

import java.io.*;

// Referenced classes of package org.apache.xml.serialize:
//            EncodingInfo

public class SieveEncodingInfo extends EncodingInfo
{
    static class BAOutputStream extends ByteArrayOutputStream
    {

        byte[] getBuffer()
        {
            return super.buf;
        }

        BAOutputStream()
        {
        }

        BAOutputStream(int i)
        {
            super(i);
        }
    }


    public SieveEncodingInfo(String s, String s1, int i, String s2)
    {
        super(s, s1, i);
        checkerStream = null;
        checkerWriter = null;
        dangerChars = null;
        dangerChars = s2;
    }

    public SieveEncodingInfo(String s, int i)
    {
        this(s, s, i, null);
    }

    public boolean isPrintable(int i)
    {
        if(dangerChars != null && i <= 65535 && dangerChars.indexOf(i) >= 0)
            return false;
        if(i <= super.lastPrintable)
            return true;
        boolean flag = true;
        synchronized(this)
        {
            try
            {
                if(checkerWriter == null)
                {
                    checkerStream = new BAOutputStream(10);
                    checkerWriter = new OutputStreamWriter(checkerStream, super.javaName);
                }
                if(i > 65535)
                {
                    checkerWriter.write((i - 0x10000 >> 10) + 55296);
                    checkerWriter.write((i - 0x10000 & 0x3ff) + 56320);
                    byte abyte0[] = checkerStream.getBuffer();
                    if(checkerStream.size() == 2 && abyte0[0] == 63 && abyte0[1] == 63)
                        flag = false;
                } else
                {
                    checkerWriter.write(i);
                    checkerWriter.flush();
                    byte abyte1[] = checkerStream.getBuffer();
                    if(checkerStream.size() == 1 && abyte1[0] == 63)
                        flag = false;
                }
                checkerStream.reset();
            }
            catch(IOException ioexception)
            {
                flag = false;
            }
        }
        return flag;
    }

    BAOutputStream checkerStream;
    Writer checkerWriter;
    String dangerChars;
}