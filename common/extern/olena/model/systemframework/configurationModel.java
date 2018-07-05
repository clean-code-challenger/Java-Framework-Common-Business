package common.extern.olena.model.systemframework;

import java.net.InetAddress;
/**
 *
 * @author Olena.Zagreba in Truegardener TEAM
 * @version ModelWeb 1.0, 2011. 12. 25 PM 1:42:29
 * @since ModelWeb 1.0
 */
public class configurationModel
{
	private String ProjectRootProperty = "tomcat.Appdir";
	private String ProjectPortProperty = "tomcat.Port";
	
	private static String BusinessFacadeLayer = "businessfacade";
	private static String BusinessRuleLayer = "businessrole";
	private static String DataAccessLayer = "dataaccess";
	private static String WebLayer = "WebServlet";
	private static String CommonLayer = "common";
	private static String SystemFrameworkLayer = "systemframework";
	public String m_strProjectName = "";
	
	public String getProjectName() {	return m_strProjectName; }
	public void setProjectName(String projectName) { m_strProjectName = projectName; }
	
	/**
	 * @param strAbsPath
	 * @param strPort
	 * 
	 * @author Olena.Zagreba in Truegardener TEAM
	 * @version ModelWeb 1.0, 2011. 12. 25 PM 1:44:39
	 * @since ModelWeb 1.0
	 */
	public void SetConfiguration(String strAbsPath, String strPort, String strProject)
	{
		if(m_strProjectName==null){
			m_strProjectName = strProject;
			if(System.getProperty(m_strProjectName + "." + ProjectRootProperty)==null)
			{
				System.setProperty(m_strProjectName + "." + ProjectRootProperty, strAbsPath);
			}
			if(System.getProperty(m_strProjectName + "." + ProjectPortProperty)==null)
			{
				System.setProperty(m_strProjectName + "." + ProjectPortProperty, strPort);
			}
		}
		else if(m_strProjectName.trim().length() == 0)
		{
			m_strProjectName = strProject;
			if(System.getProperty(m_strProjectName + "." + ProjectRootProperty)==null)
			{
				System.setProperty(m_strProjectName + "." + ProjectRootProperty, strAbsPath);
			}
			if(System.getProperty(m_strProjectName + "." + ProjectPortProperty)==null)
			{
				System.setProperty(m_strProjectName + "." + ProjectPortProperty, strPort);
			}
		}
	}
	public static String getLocalHostIp() {
		String result = null;
		try {
			result = InetAddress.getLocalHost().getHostAddress();
		}
		catch(Exception ex) { }
		return result;
	}
	public String getWebLayer() { return m_strProjectName + WebLayer; }
	public String getBusinessFacadeLayer() { return m_strProjectName + "." + BusinessFacadeLayer; }
	public String getBusinessRuleLayer() { return m_strProjectName + "." + BusinessRuleLayer; }
	public String getCommonLayer() { return m_strProjectName + "." + CommonLayer; }
	public String getDataAccessLayer() { return m_strProjectName + "." + DataAccessLayer; }
	
	public String getSystemFrameworkLayer() { return m_strProjectName + "." + SystemFrameworkLayer; }
	public String getProjectRootProperty() { return System.getProperty(m_strProjectName + "." + ProjectRootProperty); }
	public String getProjectPortProperty() { return System.getProperty(m_strProjectName + "." + ProjectPortProperty); }
}