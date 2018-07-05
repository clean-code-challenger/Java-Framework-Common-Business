package common.extern.utils.xml.writer.xerces.dom3.ls;

import java.io.OutputStream;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

import common.extern.utils.xml.writer.xerces.dom3.DOMErrorHandler;

public interface DOMWriter
{

 public abstract void setFeature(String s, boolean flag)
     throws DOMException;

 public abstract boolean canSetFeature(String s, boolean flag);

 public abstract boolean getFeature(String s)
     throws DOMException;

 public abstract String getEncoding();

 public abstract void setEncoding(String s);

 public abstract String getLastEncoding();

 public abstract String getNewLine();

 public abstract void setNewLine(String s);

 public abstract DOMErrorHandler getErrorHandler();

 public abstract void setErrorHandler(DOMErrorHandler domerrorhandler);

 public abstract boolean writeNode(OutputStream outputstream, Node node)
     throws Exception;

 public abstract String writeToString(Node node)
     throws DOMException;
}