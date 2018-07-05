package common.extern.utils.xml.writer.xerces.xni;

// Decompiled by DJ v3.0.0.63 Copyright 2002 Atanas Neshkov  Date: 2012-10-09 ���� 12:20:38
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   XNIException.java

public class XNIException extends RuntimeException
{
	/***generator key*/
	private static final long serialVersionUID = -339383420539554980L;

	public XNIException(String s)
    {
        super(s);
    }

    public XNIException(Exception exception)
    {
        super(exception.getMessage());
        fException = exception;
    }

    public XNIException(String s, Exception exception)
    {
        super(s);
        fException = exception;
    }

    public Exception getException()
    {
        return fException;
    }

    private Exception fException;
}