package Hospital.Managment.System;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {
    Connection connection;
    Statement statement;
    public Conn(){
    try{
        FileReader fr = new FileReader(("D://Hospital Managment//Hospital Managment System//src//Hospital//Managment//System//FileReader.txt"));
        BufferedReader br=new BufferedReader(fr);
        String line=br.readLine();
        String [] arr=new String[3];
        int i=0;
        while (line!=null){
            arr[i]=line;
            i++;
            line=br.readLine();
        }

        connection = DriverManager.getConnection(arr[0], arr[1], arr[2]);

        if (connection != null)
            System.out.println("Connection Successfully");
        else
            System.out.println("Connection Failed");





        statement=connection.createStatement();
    }
    catch(Exception e){
       System.out.println(e);
    }
    }
    public static void main(String[] args) {

    }
}
