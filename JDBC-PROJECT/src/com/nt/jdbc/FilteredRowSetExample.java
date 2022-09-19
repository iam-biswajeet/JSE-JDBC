package com.nt.jdbc;

import java.io.FileWriter;
import java.sql.SQLException;

import javax.sql.RowSet;
import javax.sql.rowset.Predicate;

import oracle.jdbc.rowset.OracleFilteredRowSet;

public class FilteredRowSetExample {
	private static class MyFilter implements Predicate{
		String s;
		MyFilter(String s){
			this.s=s;
			
		}
		@Override
		public boolean evaluate(RowSet rs) {
			try {
			if(rs.getString(2).startsWith(s))
				return true;
			
			return false;
			}
			catch(SQLException se) {
				return false;
			}
		}
		@Override
		public boolean evaluate(Object value, int column) throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public boolean evaluate(Object value, String columnName) throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}
		
	}
	public static void main(String[] args) {
		
		try(OracleFilteredRowSet rowset=new OracleFilteredRowSet();){
			rowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			rowset.setUsername("c##biswajit");
			rowset.setPassword("2538");
			rowset.setCommand("SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP");
			rowset.setFilter(new MyFilter("A"));
			rowset.execute();
			while(rowset.next()) {
				System.out.println(rowset.getInt(1)+"  "+rowset.getString(2)+"  "+rowset.getString(3)+"  "+rowset.getFloat(4)+"  "+rowset.getInt(5));
			}
			
			
			
			rowset.close();
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
