package common.extern.olena.model.model.page.obj;

public class Page {
	private int m_nLimit = 0;
	private int m_nPageSize = 0;
	private int m_nPageCount = 0;
	private int m_nPageIndex = 0;
	
	public int getLimit() {	return m_nLimit; }
	public void setLimit(int limit) { m_nLimit = limit; }
	public int getPageCount() { return m_nPageCount; }
	public void setPageCount(int pageCount) { m_nPageCount = pageCount; }
	public int getPageIndex() { return m_nPageIndex; }
	public void setPageIndex(int pageIndex) { m_nPageIndex = pageIndex; }
	public int getPageSize() { return m_nPageSize; }
	public void setPageSize(int pageSize) { m_nPageSize = pageSize; }
}
