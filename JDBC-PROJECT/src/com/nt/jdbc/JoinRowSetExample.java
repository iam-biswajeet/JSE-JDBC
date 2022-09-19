package com.nt.jdbc;

import java.io.FileWriter;
import java.sql.SQLException;

import oracle.jdbc.rowset.OracleCachedRowSet;
import oracle.jdbc.rowset.OracleJDBCRowSet;
import oracle.jdbc.rowset.OracleJoinRowSet;
import oracle.jdbc.rowset.OracleWebRowSet;

public class JoinRowSetExample {
	public static void main(String[] args) {
		try(OracleCachedRowSet rowset1=new OracleCachedRowSet();
			OracleCachedRowSet rowset2=new OracleCachedRowSet();
				OracleJoinRowSet rowset=new OracleJoinRowSet()){
			rowset1.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			rowset1.setUsername("c##biswajit");
			rowset1.setPassword("2538");
			rowset1.setCommand("SELECT EMPNO,ENAME,DEPTNO FROM EMP");
			rowset1.setMatchColumn(3);
			rowset1.execute();
			rowset2.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			rowset2.setUsername("c##biswajit");
			rowset2.setPassword("2538");
			
			rowset2.setCommand("SELECT DEPTNO,DNAME,LOC FROM DEPT");
			rowset2.setMatchColumn(1);
			rowset2.execute();
			rowset.addRowSet(rowset1);
			rowset.addRowSet(rowset2);
			
			while(rowset.next()) {
				
				System.out.println(rowset.getString(1)+"  "+rowset.getString(2)+"  "+rowset.getString(3)+"  "+rowset.getString(5));
				System.out.println();
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
