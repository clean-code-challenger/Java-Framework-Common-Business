package common.extern.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.*;


/**
 * @author Olena.Zagreba in Truegardener TEAM
 * @version ModelWeb 1.0, 2011. 12. 25 PM 12:37:27
 * @since ModelWeb 1.0
 */
public class HttpMessage {
	private static final int BUFFER_ONCE_BYTE_SIZE = 1024;
	URL m_urlServlet = null;
	String m_args = null;

	public HttpMessage(URL servlet) {
		this.m_urlServlet = servlet;
	}
	/**
	 * Performs a GET request to the previously given servlet with no query string.
	 * 
	 * @return
	 * @throws IOException
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 1:28:19
	 * @since ModelWeb 1.0
	 */
	public InputStream sendGetMessage() throws IOException{
		return sendGetMessage(null);
	}
	
	public String sendGetMessageAndReceive() throws IOException{
		return getUTF8StringFromInputStream(sendGetMessage(null));
	}
	
	/**
	 * Performs a GET request to the previously given servlet. 
	 * Builds a query string from the supplied Properties list.
	 *  
	 * @param args
	 * @return
	 * @throws IOException
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 1:28:37
	 * @since ModelWeb 1.0
	 */
	public InputStream sendGetMessage(Properties args) throws IOException{
		String argString = ""; // default
		if (args != null){
			argString = "?" + toEncodedString(args);
		}
		URL url = new URL(m_urlServlet.toExternalForm() + argString);
		// Turn off caching
		URLConnection con = url.openConnection();
		con.setUseCaches(false);
		return con.getInputStream();
	}
	
	/**
	 * 
	 * @param args
	 * @return
	 * @throws IOException
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 1:29:13
	 * @since ModelWeb 1.0
	 */
	public String sendGetMessageAndReceive(Properties args) throws IOException{
		String argString = ""; // default
		if (args != null) {
			argString = "?" + toEncodedString(args);
		}
		URL url = new URL(m_urlServlet.toExternalForm() + argString);
		// Turn off caching
		URLConnection con = url.openConnection();
		con.setUseCaches(false);
		return getUTF8StringFromInputStream(con.getInputStream());
	}

	/**
	 * Performs a POST request to the previously given servlet with no query string.
	 * 
	 * @return
	 * @throws IOException
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 5:11:47
	 * @since ModelWeb 1.0
	 */
	public InputStream sendPostMessage() throws IOException{
		return sendPostMessage(null);
	}

	/**
	 * Performs a POST request to the previously given servlet with no query string 
	 * and recevie String by UTF-8 format.
	 * @return
	 * @throws IOException
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 5:12:05
	 * @since ModelWeb 1.0
	 */
	public String sendPostMessageAndReceive() throws IOException {
		return getUTF8StringFromInputStream(sendPostMessage(null));
	}
	
	/**
	 * Performs a POST request to the previously given servlet.
	 * Builds post data from the supplied Properties list.
	 * 
	 * @param args
	 * @return
	 * @throws IOException
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 5:12:43
	 * @since ModelWeb 1.0
	 */
	public InputStream sendPostMessage(Properties args) throws IOException{
		URLConnection con = m_urlServlet.openConnection();
		// Prepare for both input and output
		con.setDoInput(true);
		con.setDoOutput(true);
		// Turn off caching
		con.setUseCaches(false);
		// Work around a Netscape bug
		con.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		// Write the arguments as post data
		DataOutputStream out = new DataOutputStream(con.getOutputStream());
		out.writeBytes( toEncodedString(args) );
		out.flush();
		out.close();
		return con.getInputStream();
	}

	/**
	 * Performs a POST request to the previously given servlet and recevie String by UTF-8 format.
	 * Builds post data from the supplied Properties list.
	 * 
	 * @param args
	 * @return
	 * @throws IOException
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 5:12:58
	 * @since ModelWeb 1.0
	 */
	public String sendPostMessageAndReceive(Properties args) throws IOException{
		URLConnection con = m_urlServlet.openConnection();
		// Prepare for both input and output
		con.setDoInput(true);
		con.setDoOutput(true);
		// Turn off caching
		con.setUseCaches(false);
		// Work around a Netscape bug
		con.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		// Write the arguments as post data
		DataOutputStream out = new DataOutputStream(con.getOutputStream());
		out.writeBytes( toEncodedString(args) );
		out.flush();
		out.close();
		return getUTF8StringFromInputStream(con.getInputStream());
	}
	
	/**
	 * Converts a Properties list to a URL-encoded query string
	 * @param args
	 * @return
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 5:13:45
	 * @since ModelWeb 1.0
	 */
	@SuppressWarnings("deprecation")
	private String toEncodedString(Properties args){
		StringBuffer buf = new StringBuffer();
		Enumeration names = args.propertyNames();
		while (names.hasMoreElements()){
			String name = (String) names.nextElement();
			String value = args.getProperty(name);
			buf.append(URLEncoder.encode(name) + "=" + URLEncoder.encode(value));
			if (names.hasMoreElements())
				buf.append("&");
		}
		return buf.toString();
	}
	
	/**
	 * 
	 * @param stream
	 * @return
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 5:13:52
	 * @since ModelWeb 1.0
	 */
	private String getUTF8StringFromInputStream(InputStream stream)
	{
		String result = null;
		try{
			if(stream!=null){
				byte[] reBuf = new byte[BUFFER_ONCE_BYTE_SIZE + 1];
				result = "";
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				int nResult;
				do{										
					nResult = stream.read(reBuf, 0, BUFFER_ONCE_BYTE_SIZE);
					if(nResult!=-1)
					baos.write(reBuf, 0, nResult);
				}										
				while(nResult != -1);		
				result=new String(baos.toByteArray(), "utf-8");
				baos.close();
				stream.close();
			}
		}
		catch(Throwable ex){
		}
		return result;
	}
	
	
}