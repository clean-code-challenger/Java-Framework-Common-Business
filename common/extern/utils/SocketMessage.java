package common.extern.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

import common.extern.olena.model.systemframework.Applicationlog;

public class SocketMessage 
{
	private static final int BUFFER_ONCE_BYTE_SIZE = 1024;

	public Socket m_serverSocket = null; 
	public String m_strServer = null;
	public int m_nPort = -1;
	private OutputStream m_writesStream = null;
	public SocketMessage(String server, int port) throws IOException 
	{
		if(this.m_serverSocket!=null)
		{
			this.m_serverSocket.close();
		}
		this.m_strServer = server;
		this.m_nPort = port;
		this.m_serverSocket = new Socket(this.m_strServer, this.m_nPort); 
	}

	// Performs a GET request to the previously given servlet
	// with no query string.
	public InputStream sendMessage(String writes) throws IOException 
	{
		InputStream result = null;
		try
		{
			if(m_serverSocket==null)
			{
				return null;
			}
			m_writesStream = m_serverSocket.getOutputStream();
			
			byte[] buf = (writes.getBytes("UTF-8"));
			int nBufferLen = buf.length;		
			String strLen = String.valueOf(nBufferLen);
			int nLenStrlen = strLen.length();
			int i;
			for(i = 0; i < 10 - nLenStrlen; i++)
			{
				strLen = "0" + strLen; 
			}
			m_writesStream.write(strLen.getBytes(), 0, 10);
			m_writesStream.write(buf ,0, nBufferLen);
			
			byte bufEOS[] = new byte[4];
			bufEOS[0] = bufEOS[1] = bufEOS[2] = bufEOS[3] = (byte) 0xFF;
			m_writesStream.write(bufEOS, 0, 4);
			m_writesStream.flush();
			result = m_serverSocket.getInputStream();
		}
		catch(IOException ex)
		{
			Applicationlog.setLastError(null, "mineCommon", ex);
		}
		return result;
	}
	
	public String sendMessageAndReceive(String writes) throws IOException {
		return getUTF8StringFromInputStream(sendMessage(writes));
	}
	
	public String getUTF8StringFromInputStream(InputStream stream)
	{
		String result = null;
		try{
			if(stream!=null)
			{
				int nResult;
				int nBufferLen;
				String strLen;
				byte bufEOS[] = new byte[4];
				bufEOS[0] = bufEOS[1] = bufEOS[2] = bufEOS[3] = (byte) 0xFF;
				
				byte[] reBuf;
				reBuf = new byte[10];
				
				stream.read(reBuf, 0, 10);
				strLen = new String(reBuf, "utf-8");
				nBufferLen = NumeralConvert.GetInt(strLen);
				
				reBuf = new byte[BUFFER_ONCE_BYTE_SIZE + 1];
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				int read_len = 0;

				do 
				{										
					nResult = stream.read(reBuf, 0, BUFFER_ONCE_BYTE_SIZE);
					
					if(reBuf[nResult-1]==-1 &&
							   reBuf[nResult-2]==-1 &&
							   reBuf[nResult-3]==-1 &&
							   reBuf[nResult-4]==-1
							  )
					{
						read_len += nResult;			
						baos.write(reBuf, 0, nResult - 4);
						break;
					}
					else 
					{
						read_len += nResult;
						baos.write(reBuf, 0, nResult);
					}
				}										
				while(read_len < nBufferLen);
				result=new String(baos.toByteArray(), "utf-8");
				baos.close();
				stream.close();
			}
		}
		catch(Throwable ex)
		{
			Applicationlog.setLastError(null, "mineCommon", ex);
		}
		return result;
	}
	
	public void close()throws IOException
	{
		if(m_serverSocket!=null)
		{
			m_serverSocket.close();
			m_serverSocket = null;
		}
		
		if(m_writesStream!=null)
		{
			m_writesStream.close();
			m_writesStream = null;
		}
	}
}
