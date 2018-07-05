package common.extern.utils.xml.writer.xml;

// Decompiled by DJ v3.0.0.63 Copyright 2002 Atanas Neshkov  Date: 2012-10-09 ���� 12:27:48
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   IndentPrinter.java

import java.io.*;

// Referenced classes of package org.apache.xml.serialize:
//            Printer, OutputFormat

public class IndentPrinter extends Printer
{

    public IndentPrinter(Writer writer, OutputFormat outputformat)
    {
        super(writer, outputformat);
        _line = new StringBuffer(80);
        _text = new StringBuffer(20);
        _spaces = 0;
        _thisIndent = _nextIndent = 0;
    }

    public void enterDTD()
    {
        if(super._dtdWriter == null)
        {
            _line.append(_text);
            _text = new StringBuffer(20);
            flushLine(false);
            super._dtdWriter = new StringWriter();
            super._docWriter = super._writer;
            super._writer = super._dtdWriter;
        }
    }

    public String leaveDTD()
    {
        if(super._writer == super._dtdWriter)
        {
            _line.append(_text);
            _text = new StringBuffer(20);
            flushLine(false);
            super._writer = super._docWriter;
            return super._dtdWriter.toString();
        } else
        {
            return null;
        }
    }

    public void printText(String s)
    {
        _text.append(s);
    }

    public void printText(StringBuffer stringbuffer)
    {
        _text.append(stringbuffer);
    }

    public void printText(char c)
    {
        _text.append(c);
    }

    public void printText(char ac[], int i, int j)
    {
        _text.append(ac, i, j);
    }

    public void printSpace()
    {
        if(_text.length() > 0)
        {
            if(super._format.getLineWidth() > 0 && _thisIndent + _line.length() + _spaces + _text.length() > super._format.getLineWidth())
            {
                flushLine(false);
                try
                {
                    super._writer.write(super._format.getLineSeparator());
                }
                catch(IOException ioexception)
                {
                    if(super._exception == null)
                        super._exception = ioexception;
                }
            }
            for(; _spaces > 0; _spaces--)
                _line.append(' ');

            _line.append(_text);
            _text = new StringBuffer(20);
        }
        _spaces++;
    }

    public void breakLine()
    {
        breakLine(false);
    }

    public void breakLine(boolean flag)
    {
        if(_text.length() > 0)
        {
            for(; _spaces > 0; _spaces--)
                _line.append(' ');

            _line.append(_text);
            _text = new StringBuffer(20);
        }
        flushLine(flag);
        try
        {
            super._writer.write(super._format.getLineSeparator());
        }
        catch(IOException ioexception)
        {
            if(super._exception == null)
                super._exception = ioexception;
        }
    }

    public void flushLine(boolean flag)
    {
        if(_line.length() > 0)
            try
            {
                if(super._format.getIndenting() && !flag)
                {
                    int i = _thisIndent;
                    if(2 * i > super._format.getLineWidth() && super._format.getLineWidth() > 0)
                        i = super._format.getLineWidth() / 2;
                    for(; i > 0; i--)
                        super._writer.write(32);

                }
                _thisIndent = _nextIndent;
                _spaces = 0;
                super._writer.write(_line.toString());
                _line = new StringBuffer(40);
            }
            catch(IOException ioexception)
            {
                if(super._exception == null)
                    super._exception = ioexception;
            }
    }

    public void flush()
    {
        if(_line.length() > 0 || _text.length() > 0)
            breakLine();
        try
        {
            super._writer.flush();
        }
        catch(IOException ioexception)
        {
            if(super._exception == null)
                super._exception = ioexception;
        }
    }

    public void indent()
    {
        _nextIndent += super._format.getIndent();
    }

    public void unindent()
    {
        _nextIndent -= super._format.getIndent();
        if(_nextIndent < 0)
            _nextIndent = 0;
        if(_line.length() + _spaces + _text.length() == 0)
            _thisIndent = _nextIndent;
    }

    public int getNextIndent()
    {
        return _nextIndent;
    }

    public void setNextIndent(int i)
    {
        _nextIndent = i;
    }

    public void setThisIndent(int i)
    {
        _thisIndent = i;
    }

    private StringBuffer _line;
    private StringBuffer _text;
    private int _spaces;
    private int _thisIndent;
    private int _nextIndent;
}