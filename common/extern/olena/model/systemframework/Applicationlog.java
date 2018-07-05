package common.extern.olena.model.systemframework;

import java.io.StringReader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;

/**
 *
 * @author Olena.Zagreba in Truegardener TEAM
 * @version ModelWeb 1.0, 2011. 12. 25 PM 2:51:07
 * @since ModelWeb 1.0
 */
public class Applicationlog
{
	protected configurationModel m_confService = null;
	
	public final static String exception_root = "exception_list";			
	public final static String exception_count = "count";					
	public final static String exception_exception = "exception";			
	public final static String exception_kind = "kind";						
	public final static String exception_title = "title";					
	public final static String exception_no = "no";							
	public final static String exception_filename = "filename";				
	public final static String exception_class = "class";					
	public final static String exception_memer = "memer";					
	public final static String exception_line = "line";						
	public final static String exception_content = "birth";					
	
	private static String lastErrorMsg;
	private static String lastErrorLayer;
	
	public static String getLastErrorMsg() { return lastErrorMsg; }
	public static String getLastErrorLayer() { return lastErrorLayer; }
	
	public static boolean IS_DEBUG = false;			//root
	/**
	 * 
	 * @param msg 
	 * @param layer 
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 3:03:33
	 * @since ModelWeb 1.0
	 */
	public static void setLastError(String msg, String layer) {
		try {
			lastErrorMsg = msg;
			lastErrorLayer = layer;
			printLog(msg + " at " + layer);
		} catch (Exception ex) {
		}
	}
	/**
	 * 
	 * @param msg 
	 * @param layer 
	 * @param ex 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 3:03:35
	 * @since ModelWeb 1.0
	 */
	public static void setLastError(String msg, String layer, Throwable ex) {
		StringBuffer buffer = new StringBuffer("");
		try {
			lastErrorMsg = msg; lastErrorLayer = layer;
			if(ex.getStackTrace() != null) {
				if(ex.getStackTrace().length > 0) {
					int nCount;				
					nCount = ex.getStackTrace().length;
					
					buffer.append("Error occur:\r\n");
					buffer.append("[Error title] " + msg + "\r\n");
					buffer.append("[Error occur layer] "  + layer + "layer" + "\r\n");
					buffer.append("[Error occur count] "  + nCount + "" + "\r\n");
					buffer.append("[Error]" + ex.getMessage() + "\r\n");
					
					buffer.append("\r\n");
					for(int i = 0; i < nCount; i++)
					{
						buffer.append("[오유] " + (i + 1) + "" + "\r\n");
						buffer.append("[Error occured file] " + ex.getStackTrace()[i].getFileName() + "\r\n");
						buffer.append("[Error occured class] " + ex.getStackTrace()[i].getClassName() + "\r\n");
						buffer.append("[Error occured function] " + ex.getStackTrace()[i].getMethodName() + "\r\n");
						buffer.append("[Error occured layer] " + ex.getStackTrace()[i].getLineNumber() + "  " + "\r\n");
						buffer.append("\r\n");
					}
				}
				else {
					buffer.append("Log : " + msg +  " at " + layer + "\r\n");
				}
			}
		}
		catch(Exception ext) { }
		printLog(buffer.toString());
	}
	/**
	 * @param ex 
	 * @return String
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 3:00:32
	 * @since ModelWeb 1.0
	 */
	public static String getErrorTrace(Throwable ex) {
		StringBuffer buffer = new StringBuffer("");
		try {
			if(ex.getStackTrace() != null) {
				if(ex.getStackTrace().length > 0) {
					int i;
					int nCount;				
					nCount = ex.getStackTrace().length;
					
					buffer.append("Error occur:\r\n");
					buffer.append("[Error occur] "  + nCount + "\r\n");
					buffer.append("[Error]" + ex.getMessage());
					
					buffer.append("\r\n\r\n");
					for(i = 0; i < nCount; i++)
					{
						buffer.append("[Error] " + (i + 1) + "");
						buffer.append("\r\n[Error occured file] " + ex.getStackTrace()[i].getFileName());
						buffer.append("\r\n[Error occured class] " + ex.getStackTrace()[i].getClassName());
						buffer.append("\r\n[Error occured function] " + ex.getStackTrace()[i].getMethodName());
						buffer.append("\r\n[Error occured line] " + ex.getStackTrace()[i].getLineNumber() + "  ");
						buffer.append("\r\n\r\n");
					}
				}
			}
		}
		catch(Exception ext) { }
		buffer.append("====================================\r\n\r\n");
		return buffer.toString();
	}
	
	/**
	 * @param kind
	 * @param layer Error occur
	 * @param ex 
	 * @return xml
	 * @throws Throwable
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 3:04:42
	 * @since ModelWeb 1.0
	 */
	public static Document getLastErrorXML(int kind, String layer, Throwable ex)throws Throwable {
		Document doc = null;
		try {
			DOMParser dom = new DOMParser();
			StringReader readerStr;
			readerStr = new StringReader("<" + exception_root+ "/>");
			InputSource source = new InputSource(readerStr);
			dom.parse(source);
			doc = dom.getDocument();
			Element eleDoc = doc.getDocumentElement();
			Element elemNode;
			Element elemNodeChild;
			if(ex.getStackTrace() != null) {
				if(ex.getStackTrace().length > 0) {
					int nCount;				
					nCount = ex.getStackTrace().length;
					elemNode = doc.createElement(exception_title);
					elemNode.setTextContent(ex.getMessage());
					eleDoc.appendChild(elemNode);
					elemNode = doc.createElement(exception_kind);
					elemNode.setTextContent(String.valueOf(kind));				
					eleDoc.appendChild(elemNode);
					elemNode = doc.createElement(exception_count);
					elemNode.setTextContent(String.valueOf(nCount));				
					eleDoc.appendChild(elemNode);
					elemNode = doc.createElement(exception_content);
					elemNode.setTextContent(ex.getMessage());				
					eleDoc.appendChild(elemNode);
					for(int i = 0; i < nCount; i++) {
						elemNode = doc.createElement(exception_exception);
						elemNodeChild = doc.createElement(exception_no);
						elemNodeChild.setTextContent(String.valueOf(i + 1));				
						elemNode.appendChild(elemNodeChild);
						elemNodeChild = doc.createElement(exception_filename);
						elemNodeChild.setTextContent(ex.getStackTrace()[i].getFileName());				
						elemNode.appendChild(elemNodeChild);
						
						elemNodeChild = doc.createElement(exception_class);
						elemNodeChild.setTextContent(ex.getStackTrace()[i].getClassName());				
						elemNode.appendChild(elemNodeChild);
						
						elemNodeChild = doc.createElement(exception_memer);
						elemNodeChild.setTextContent(ex.getStackTrace()[i].getMethodName());				
						elemNode.appendChild(elemNodeChild);
						
						elemNodeChild = doc.createElement(exception_line);
						elemNodeChild.setTextContent(String.valueOf(ex.getStackTrace()[i].getLineNumber()));				
						elemNode.appendChild(elemNodeChild);
						
						eleDoc.appendChild(elemNode);
					}
				}
			}
		}
		catch (Throwable th) {
			throw th;
		} finally {
		}
		return doc;
	}
	/**
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 3:05:04
	 * @since ModelWeb 1.0
	 */
	public static void clearError() {
		lastErrorMsg = "";
		lastErrorLayer = "";
	}
	/*************************TruegardenerModelLog**************************************/
	/**
	 * @return String
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 3:00:32
	 * @since ModelWeb 1.0
	 */
	public static void LogTruegardener(String strMessage) {
		StringBuffer buffer = new StringBuffer("");
		try {
			buffer.append("===========================Truegardener==========================");
			buffer.append("[Error message]" + strMessage);
			buffer.append("\r\n");
		} catch (Exception ext) {
		}
		buffer.append("====================================\r\n\r\n");
		printLog(buffer.toString());
	}
	/**
	 * @param ex 
	 * @return String
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 3:00:32
	 * @since ModelWeb 1.0
	 */
	public static void LogTruegardenerError(String strMessage) {
		StringBuffer buffer = new StringBuffer("");
		try {
			buffer.append("===========================Truegardener==========================");
			buffer.append("Error occur:\r\n");
			buffer.append("[Error]" + strMessage);
			buffer.append("\r\n\r\n");
		} catch (Exception ext) {
		}
		buffer.append("====================================\r\n\r\n");
		printLog(buffer.toString());
	}
	/**
	 * @param ex 
	 * @return String
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 3:00:32
	 * @since ModelWeb 1.0
	 */
	public static void LogTruegardenerError(Throwable ex) {
		StringBuffer buffer = new StringBuffer("");
		try {
			buffer.append("===========================Truegardener==========================");
			if (ex.getStackTrace() != null) {
				if(ex.getStackTrace().length > 0) {
					int i;
					int nCount;				
					nCount = ex.getStackTrace().length;
					
					buffer.append("Error occur:\r\n");
					buffer.append("[Error occur] "  + nCount + "\r\n");
					buffer.append("[Error]" + ex.getMessage());
					
					buffer.append("\r\n\r\n");
					for(i = 0; i < nCount; i++)
					{
						buffer.append("[Error] " + (i + 1) + "");
						buffer.append("\r\n[Error occured file] " + ex.getStackTrace()[i].getFileName());
						buffer.append("\r\n[Error occured class] " + ex.getStackTrace()[i].getClassName());
						buffer.append("\r\n[Error occured function] " + ex.getStackTrace()[i].getMethodName());
						buffer.append("\r\n[Error occured line] " + ex.getStackTrace()[i].getLineNumber() + "  ");
						buffer.append("\r\n\r\n");
					}
				}
			} 
			else {
			}
		} catch (Exception ext) {
		}
		buffer.append("====================================\r\n\r\n");
		printLog(buffer.toString());
	}
	
	/**
	 * 
	 * @param ex
	 * @return String
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 3:00:32
	 * @since ModelWeb 1.0
	 */
	public static void LogTruegardenerError(Throwable ex, String strSql) {
		StringBuffer buffer = new StringBuffer("");
		try {
			buffer.append("===========================Truegardener==========================");
			buffer.append("\r\n\r\n");
			buffer.append("SQL query:\r\n\r\n");
			buffer.append(strSql);
			buffer.append("\r\n");
			if(ex!=null) {
				if(ex.getStackTrace() != null) {
					if(ex.getStackTrace().length > 0) {
						int nCount;				
						nCount = ex.getStackTrace().length;
						buffer.append(strSql);
						
						buffer.append("Error occur:\r\n");
						buffer.append("[Error occur] "  + nCount + "\r\n");
						buffer.append("[Error ]" + ex.getMessage());
						
						buffer.append("\r\n\r\n");
						for(int i = 0; i < nCount; i++) {
							buffer.append("[Error] " + (i + 1) + "");
							buffer.append("\r\n[Error occured file] " + ex.getStackTrace()[i].getFileName());
							buffer.append("\r\n[Error occured class] " + ex.getStackTrace()[i].getClassName());
							buffer.append("\r\n[Error occured function] " + ex.getStackTrace()[i].getMethodName());
							buffer.append("\r\n[Error occured line] " + ex.getStackTrace()[i].getLineNumber() + "  ");
							buffer.append("\r\n\r\n");
						}
					}
				}
			}
			else { }
		}
		catch(Exception ext) { }
		buffer.append("====================================\r\n\r\n");
		printLog(buffer.toString());
	}
	public static void LogTruegardenerError(String strErrorMsg, String strSql) {
		StringBuffer buffer = new StringBuffer("");
		try {
			buffer
					.append("===========================Truegardener==========================");
			buffer.append("\r\n\r\n");
			buffer.append("SQL query:\r\n\r\n");
			buffer.append(strSql);
			buffer.append("\r\n");
			if (strErrorMsg != null) {
				buffer.append("[Error] - " + strErrorMsg);
			}
		} catch (Exception ext) {
		}
		buffer.append("====================================\r\n\r\n");
		printLog(buffer.toString());
	}	
	/**
	 * 
	 * @param strLog
	 * @author Olena.Zagreba
	 * @version ModelWeb 2013. 05. 05 PM 3:22:14
	 * @since ModelWeb
	 */
	protected static void printLog(String strLog) {
		System.out.println(strLog);
	}
}