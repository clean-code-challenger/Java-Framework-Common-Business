package common.extern.olena.model.model.page.info;

import common.extern.olena.model.model.page.obj.Page;

/**
 * Contains Page Object Info.
 */
public class pageModel {
	private String m_strLimit = "limit";
	private String m_strPageSize = "pageSize";
	private String m_strPageCount = "pageCount";
	private String m_strPageIndex = "pageIndex";
	private Class m_pageClass = Page.class;
	public pageModel() {
	}
	
	public pageModel(Class classPage, String strLimit, String strPageSize, String strPageCount, String strPageIndex) {
		m_pageClass = classPage;
		m_strLimit = strLimit;
		m_strPageSize = strPageSize;
		m_strPageCount = strPageCount;
		m_strPageIndex = strPageIndex;
	}

	public String getLimit() { return m_strLimit; }
	public void setLimit(String limit) { m_strLimit = limit; }

	public String getPageCount() { return m_strPageCount; }
	public void setPageCount(String pageCount) { m_strPageCount = pageCount; }

	public String getPageIndex() { return m_strPageIndex; }
	public void setPageIndex(String pageIndex) { m_strPageIndex = pageIndex; }

	public String getPageSize() { return m_strPageSize; }
	public void setPageSize(String pageSize) { m_strPageSize = pageSize; }

	public Class getPageClass() { return m_pageClass; }
	public void setPageClass(Class page) { m_pageClass = page; }
}
