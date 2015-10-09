package com.lal.sp.util;

public class Sqlserver2005Dialect extends Dialect {
	private String orderKey;
	public Sqlserver2005Dialect() {
	}
	public Sqlserver2005Dialect(String orderKey) {
		this.orderKey=orderKey;
	}
	@Override
	public String getLimitString(String sql, int offset, int limit) {
		sql = sql.trim();
		StringBuffer pageSql = new StringBuffer(sql.length() + 100);
		// 其实这里还是有一点问题的，就是排序问题，指定死了，有解决的提供一下，等复习到Hibernate看看Hibernat内部是如何实现的。
		pageSql.append("select * from(select mya.*,row_number() over (order by "+orderKey+") rownum from( ");
		pageSql.append(sql);
		pageSql.append(") mya )myb where rownum> " + offset + " and rownum <= " + (offset + limit));
		return pageSql.toString();
	}
}
