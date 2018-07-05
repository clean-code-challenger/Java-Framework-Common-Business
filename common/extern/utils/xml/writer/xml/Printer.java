package common.extern.utils.xml.writer.xml;

// Decompiled by DJ v3.0.0.63 Copyright 2002 Atanas Neshkov  Date: 2012-10-09 ���� 12:27:11
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Printer.java

import java.io.*;

// Referenced classes of package org.apache.xml.serialize:
//            OutputFormat

public class Printer
{

    public Printer(Writer writer, OutputFormat outputformat)
    {
        _pos = 0;
        _writer = writer;
        _format = outputformat;
        _exception = null;
        _dtdWriter = null;
        _docWriter = null;
        _pos = 0;
    }

    public IOException getException()
    {
        return _exception;
    }

    public void enterDTD()
        throws IOException
    {
        if(_dtdWriter == null)
        {
            flushLine(false);
            _dtdWriter = new StringWriter();
            _docWriter = _writer;
            _writer = _dtdWriter;
        }
    }

    public String leaveDTD()
        throws IOException
    {
        if(_writer == _dtdWriter)
        {
            flushLine(false);
            _writer = _docWriter;
            return _dtdWriter.toString();
        } else
        {
            return null;
        }
    }

    public void printText(String s)
        throws IOException
    {
        try
        {
            int i = s.length();
            for(int j = 0; j < i; j++)
            {
                if(_pos == 4096)
                {
                    _writer.write(_buffer);
                    _pos = 0;
                }
                _buffer[_pos] = s.charAt(j);
                _pos++;
            }

        }
        catch(IOException ioexception)
        {
            if(_exception == null)
                _exception = ioexception;
            throw ioexception;
        }
    }

    public void printText(StringBuffer stringbuffer)
        throws IOException
    {
        try
        {
            int i = stringbuffer.length();
            for(int j = 0; j < i; j++)
            {
                if(_pos == 4096)
                {
                    _writer.write(_buffer);
                    _pos = 0;
                }
                _buffer[_pos] = stringbuffer.charAt(j);
                _pos++;
            }

        }
        catch(IOException ioexception)
        {
            if(_exception == null)
                _exception = ioexception;
            throw ioexception;
        }
    }

    public void printText(char ac[], int i, int j)
        throws IOException
    {
        try
        {
            while(j-- > 0) 
            {
                if(_pos == 4096)
                {
                    _writer.write(_buffer);
                    _pos = 0;
                }
                _buffer[_pos] = ac[i];
                i++;
                _pos++;
            }
        }
        catch(IOException ioexception)
        {
            if(_exception == null)
                _exception = ioexception;
            throw ioexception;
        }
    }

    public void printText(char c)
        throws IOException
    {
        try
        {
            if(_pos == 4096)
            {
                _writer.write(_buffer);
                _pos = 0;
            }
            _buffer[_pos] = c;
            _pos++;
        }
        catch(IOException ioexception)
        {
            if(_exception == null)
                _exception = ioexception;
            throw ioexception;
        }
    }

    public void printSpace()
        throws IOException
    {
        try
        {
            if(_pos == 4096)
            {
                _writer.write(_buffer);
                _pos = 0;
            }
            _buffer[_pos] = ' ';
            _pos++;
        }
        catch(IOException ioexception)
        {
            if(_exception == null)
                _exception = ioexception;
            throw ioexception;
        }
    }

    public void breakLine()
        throws IOException
    {
        try
        {
            if(_pos == 4096)
            {
                _writer.write(_buffer);
                _pos = 0;
            }
            _buffer[_pos] = '\n';
            _pos++;
        }
        catch(IOException ioexception)
        {
            if(_exception == null)
                _exception = ioexception;
            throw ioexception;
        }
    }

    public void breakLine(boolean flag)
        throws IOException
    {
        breakLine();
    }

    public void flushLine(boolean flag)
        throws IOException
    {
        try
        {
            _writer.write(_buffer, 0, _pos);
        }
        catch(IOException ioexception)
        {
            if(_exception == null)
                _exception = ioexception;
        }
        _pos = 0;
    }

    public void flush()
        throws IOException
    {
        try
        {
            _writer.write(_buffer, 0, _pos);
            _writer.flush();
        }
        catch(IOException ioexception)
        {
            if(_exception == null)
                _exception = ioexception;
            throw ioexception;
        }
        _pos = 0;
    }

    public void indent()
    {
    }

    public void unindent()
    {
    }

    public int getNextIndent()
    {
        return 0;
    }

    public void setNextIndent(int i)
    {
    }

    public void setThisIndent(int i)
    {
    }

    protected final OutputFormat _format;
    protected Writer _writer;
    protected StringWriter _dtdWriter;
    protected Writer _docWriter;
    protected IOException _exception;
    @SuppressWarnings("unused")
	private static final int BufferSize = 4096;
    private final char _buffer[] = new char[4096];
    private int _pos;
}