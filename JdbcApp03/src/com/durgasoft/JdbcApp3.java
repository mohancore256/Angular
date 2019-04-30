//JdbcApp3
package com.durgasoft;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcApp3 {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement st = null;
		BufferedReader br = null;
		try {
			//Loading the Driver Details
		Class.forName("oracle.jdbc.OracleDriver");
		   //establish the connection
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "mohan");
		   //Creating the statement
		st= con.createStatement();
		br = new BufferedReader(new InputStreamReader(System.in));
		int primary_key_cols_count = 0;
		String primary_key_cols ="";
		
		System.out.print("Enter Table Name : ");
		String table_Name = br.readLine();
		
		String query = "Create table " + table_Name + "(";
		
		while(true) {
			System.out.print("Enter the column name :");
            String column_Name = br.readLine();
            query =query + column_Name+" ";
            
            System.out.print("Enter the column Type :");
            String column_Type = br.readLine();
            query =query + column_Type;
             
            System.out.print("Is this column a primary key?(yes/no)");
            String primary_Key_Option = br.readLine();
            if(primary_Key_Option.equals("yes")) 
            {
            	if (primary_key_cols_count == 0) 
            	    {
            	  primary_key_cols = primary_key_cols + column_Name;	
            	      }
            	else {
            	primary_key_cols = primary_key_cols + "," + column_Name;           	
            	primary_key_cols_count =primary_key_cols_count +1;
            	     }
              }
            System.out.print("Do you want to add one more Column?(yes/no)");
            String column_Option=br.readLine();
            if (column_Option.equals("yes")) {
            query =query + ",";
            continue;
            }
            else {
            	query = query + ", primary key (" +primary_key_cols +"))";
            	break;
            }   
            
		}
		
		//System.out.println("query");
		
		//Executing the statement
		st.executeUpdate(query);
		System.out.println("Table " + table_Name + " Succesfully Created" );
		} 
		catch (Exception e) { 
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				st.close();
				br.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		

	}

}

