package common.extern.utils.xml.writer.xerces.util;

// Decompiled by DJ v3.0.0.63 Copyright 2002 Atanas Neshkov  Date: 2012-10-09 ���� 12:31:25
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   NamespaceSupport.java


// Referenced classes of package org.apache.xerces.util:
//            SymbolTable

public class NamespaceSupport implements NamespaceContext
{
    final class Context implements NamespaceContext
    {

        public void setCurrentContext(int i)
        {
            fCurrentContext = i;
        }

        public String getURI(String s)
        {
            for(int i = fNamespaceSize; i > 0; i -= 2)
                if(fNamespace[i - 2] == s)
                    return fNamespace[i - 1];

            return null;
        }

        public int getDeclaredPrefixCount()
        {
            return (fNamespaceSize - fContext[fCurrentContext]) / 2;
        }

        public String getDeclaredPrefixAt(int i)
        {
            return fNamespace[fContext[fCurrentContext] + i * 2];
        }

        public NamespaceContext getParentContext()
        {
            if(fCurrentContext == 1)
            {
                return null;
            } else
            {
                setCurrentContext(fCurrentContext - 1);
                return this;
            }
        }

        private int fCurrentContext;

        public Context(int i)
        {
            setCurrentContext(i);
        }
    }


    public NamespaceSupport()
    {
        fNamespace = new String[32];
        fContext = new int[8];
    }

    public NamespaceSupport(NamespaceContext namespacecontext)
    {
        fNamespace = new String[32];
        fContext = new int[8];
        pushContext();
        for(; namespacecontext != null; namespacecontext = namespacecontext.getParentContext())
        {
            int i = namespacecontext.getDeclaredPrefixCount();
            for(int j = 0; j < i; j++)
            {
                String s = namespacecontext.getDeclaredPrefixAt(j);
                String s1 = getURI(s);
                if(s1 == null)
                {
                    String s2 = namespacecontext.getURI(s);
                    declarePrefix(s, s2);
                }
            }

        }

    }

    public void reset(SymbolTable symboltable)
    {
        fXmlSymbol = symboltable.addSymbol("xml");
        fXmlnsSymbol = symboltable.addSymbol("xmlns");
        fNamespaceSize = 0;
        fCurrentContext = 0;
        fContext[fCurrentContext] = fNamespaceSize;
        fNamespace[fNamespaceSize++] = fXmlSymbol;
        fNamespace[fNamespaceSize++] = symboltable.addSymbol("http://www.w3.org/XML/1998/namespace");
        fCurrentContext++;
    }

    public void pushContext()
    {
        if(fCurrentContext + 1 == fContext.length)
        {
            int ai[] = new int[fContext.length * 2];
            System.arraycopy(fContext, 0, ai, 0, fContext.length);
            fContext = ai;
        }
        fContext[++fCurrentContext] = fNamespaceSize;
    }

    public void popContext()
    {
        fNamespaceSize = fContext[fCurrentContext--];
    }

    public boolean declarePrefix(String s, String s1)
    {
        if(s == fXmlSymbol || s == fXmlnsSymbol)
            return false;
        for(int i = fNamespaceSize; i > fContext[fCurrentContext]; i -= 2)
            if(fNamespace[i - 2] == s)
            {
                fNamespace[i - 1] = s1;
                return true;
            }

        if(fNamespaceSize == fNamespace.length)
        {
            String as[] = new String[fNamespaceSize * 2];
            System.arraycopy(fNamespace, 0, as, 0, fNamespaceSize);
            fNamespace = as;
        }
        fNamespace[fNamespaceSize++] = s;
        fNamespace[fNamespaceSize++] = s1;
        return true;
    }

    public String getURI(String s)
    {
        for(int i = fNamespaceSize; i > 0; i -= 2)
            if(fNamespace[i - 2] == s)
                return fNamespace[i - 1];

        return null;
    }

    public String getPrefix(String s)
    {
        for(int i = fNamespaceSize; i > 0; i -= 2)
            if(fNamespace[i - 1] == s)
                return fNamespace[i - 2];

        return null;
    }

    public int getDeclaredPrefixCount()
    {
        return (fNamespaceSize - fContext[fCurrentContext]) / 2;
    }

    public String getDeclaredPrefixAt(int i)
    {
        return fNamespace[fContext[fCurrentContext] + i * 2];
    }

    public NamespaceContext getParentContext()
    {
        if(fCurrentContext == 1)
            return null;
        else
            return new Context(fCurrentContext - 1);
    }

    protected String fNamespace[];
    protected int fNamespaceSize;
    protected int fContext[];
    protected int fCurrentContext;
    protected String fXmlSymbol;
    protected String fXmlnsSymbol;
}