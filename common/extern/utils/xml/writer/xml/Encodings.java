package common.extern.utils.xml.writer.xml;

// Decompiled by DJ v3.0.0.63 Copyright 2002 Atanas Neshkov  Date: 2012-10-09 ���� 11:58:22
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Encodings.java



// Referenced classes of package org.apache.xml.serialize:
//            EncodingInfo, SieveEncodingInfo

public class Encodings
{

    public Encodings()
    {
    }

    static EncodingInfo getEncodingInfo(String s)
    {
        if(s == null)
            return new EncodingInfo(null, 127);
        for(int i = 0; i < _encodings.length; i++)
            if(_encodings[i].name.equalsIgnoreCase(s))
                return _encodings[i];

        return new SieveEncodingInfo(s, 127);
    }

    static final int DefaultLastPrintable = 127;
    static final String JIS_DANGER_CHARS = "\\~\177\242\243\245\254\u2014\u2015\u2016\u2026\u203E\u203E\u2225\u222F\u301C\uFF3C\uFF5E\uFFE0\uFFE1\uFFE2\uFFE3";
    private static final EncodingInfo _encodings[] = {
        new EncodingInfo("ASCII", 127), new EncodingInfo("US-ASCII", 127), new EncodingInfo("ISO-8859-1", 255), new EncodingInfo("ISO-8859-2", 255), new EncodingInfo("ISO-8859-3", 255), new EncodingInfo("ISO-8859-4", 255), new EncodingInfo("ISO-8859-5", 255), new EncodingInfo("ISO-8859-6", 255), new EncodingInfo("ISO-8859-7", 255), new EncodingInfo("ISO-8859-8", 255), 
        new EncodingInfo("ISO-8859-9", 255), new EncodingInfo("UTF-8", "UTF8", 0x10ffff), new SieveEncodingInfo("Shift_JIS", "SJIS", 127, "\\~\177\242\243\245\254\u2014\u2015\u2016\u2026\u203E\u203E\u2225\u222F\u301C\uFF3C\uFF5E\uFFE0\uFFE1\uFFE2\uFFE3"), new SieveEncodingInfo("Windows-31J", "MS932", 127, "\\~\177\242\243\245\254\u2014\u2015\u2016\u2026\u203E\u203E\u2225\u222F\u301C\uFF3C\uFF5E\uFFE0\uFFE1\uFFE2\uFFE3"), new SieveEncodingInfo("EUC-JP", null, 127, "\\~\177\242\243\245\254\u2014\u2015\u2016\u2026\u203E\u203E\u2225\u222F\u301C\uFF3C\uFF5E\uFFE0\uFFE1\uFFE2\uFFE3"), new SieveEncodingInfo("ISO-2022-JP", null, 127, "\\~\177\242\243\245\254\u2014\u2015\u2016\u2026\u203E\u203E\u2225\u222F\u301C\uFF3C\uFF5E\uFFE0\uFFE1\uFFE2\uFFE3")
    };

}