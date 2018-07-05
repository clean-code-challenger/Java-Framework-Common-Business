package common.extern.utils.xml.writer.xni;

public interface NamespaceContext
{

    public abstract String getURI(String s);

    public abstract int getDeclaredPrefixCount();

    public abstract String getDeclaredPrefixAt(int i);

    public abstract NamespaceContext getParentContext();

    public static final String XML_URI = "http://www.w3.org/XML/1998/namespace";
    public static final String XMLNS_URI = "http://www.w3.org/2000/xmlns/";
}