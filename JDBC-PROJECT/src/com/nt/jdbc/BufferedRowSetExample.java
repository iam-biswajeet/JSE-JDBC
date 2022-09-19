package com.nt.jdbc;

import java.sql.SQLException;

import oracle.jdbc.rowset.OracleCachedRowSet;
import oracle.jdbc.rowset.OracleJDBCRowSet;

public class BufferedRowSetExample {
	public static void main(String[] args) {
		try(OracleCachedRowSet rowset=new OracleCachedRowSet();){
			rowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			rowset.setUsername("c##biswajit");
			rowset.setPassword("2538");
			rowset.setCommand("SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP");
			rowset.execute();
			while(rowset.next()) {
				System.out.println(rowset.getInt(1)+"  "+rowset.getString(2)+"  "+rowset.getString(3)+"  "+rowset.getFloat(4)+"  "+rowset.getInt(5));
			}
			System.out.println("db stop");
			Thread.sleep(5000);
			rowset.absolute(4);
			rowset.updateFloat(4,4000);
			Thread.sleep(5000);
			System.out.println("db start");
			rowset.updateRow();
			rowset.acceptChanges();
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
