package com.nt.jdbc;

import java.io.FileWriter;
import java.sql.SQLException;

import oracle.jdbc.rowset.OracleCachedRowSet;
import oracle.jdbc.rowset.OracleJDBCRowSet;
import oracle.jdbc.rowset.OracleWebRowSet;

public class WebRowSetExample {
	public static void main(String[] args) {
		try(OracleWebRowSet rowset=new OracleWebRowSet();){
			rowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			rowset.setUsername("c##biswajit");
			rowset.setPassword("2538");
			rowset.setCommand("SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP");
			rowset.execute();
			rowset.writeXml(new FileWriter("emp.xml"));
			
			
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
