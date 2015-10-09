package com.lal.sp.util;
public abstract class Dialect {
	public static enum Type{
		MYSQL,
		ORACLE,
		SQLSERVER2005
	}
	public abstract String getLimitString(String sql, int skipResults, int maxResults);
}
