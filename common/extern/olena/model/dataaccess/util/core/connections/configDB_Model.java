package common.extern.olena.model.dataaccess.util.core.connections;

public class configDB_Model
{
	private String m_strDBKindName = null;
	private String m_strConnClass = null;
	private String m_strConnRoot = null;
	private String m_strConnFormat = null;
	private String m_strConnFormatNopass = null;
	private String m_strConnFormatNodb = null;
	private String m_strConnFormatNodbNopass = null;
	
	public String getDBKindName() { return m_strDBKindName; }
	public void setDBKindName(String kindName) { m_strDBKindName = kindName; }
	
	public String getConnClass() { return m_strConnClass; }
	public void setConnClass(String connClass) { m_strConnClass = connClass; }
	
	public String getConnRoot() { return m_strConnRoot; }
	public void setConnRoot(String connRoot) { m_strConnRoot= connRoot; }
	
	public String getConnFormat() { return m_strConnFormat; }
	public void setConnFormat(String connFormat) { m_strConnFormat = connFormat; }
	
	public String getConnFormatNopass() { return m_strConnFormatNopass; }
	public void setConnFormatNopass(String connFormatSimple) { m_strConnFormatNopass = connFormatSimple; }
	
	public String getConnFormatNodb() { return m_strConnFormatNodb; }
	public void setConnFormatNodb(String connFormatNodb) { m_strConnFormatNodb = connFormatNodb; }
	
	public String getConnFormatNodbNopass() { return m_strConnFormatNodbNopass; }
	public void setConnFormatNodbNopass(String connFormatNodbNopass) { m_strConnFormatNodbNopass = connFormatNodbNopass; }
}

