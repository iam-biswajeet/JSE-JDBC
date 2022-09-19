package com.nt.jdbc;

import java.sql.SQLException;

import oracle.jdbc.rowset.OracleJDBCRowSet;

public class JDBCRowSetExample {
	public static void main(String[] args) {
		try(OracleJDBCRowSet rowset=new OracleJDBCRowSet();){
			rowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			rowset.setUsername("c##biswajit");
			rowset.setPassword("2538");
			rowset.setCommand("SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP");
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
