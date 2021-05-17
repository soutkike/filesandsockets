
package com.mycompany.clienteservidor1;

import java.io.*;
import static java.lang.Compiler.command;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Scanner;

public class Servidor {
    
    
    public static void main(String[] args) {
     ServerSocket serverSocket = null; 
     String recieved;
     DataInputStream is;
     DataOutputStream os;
     Socket clientSocket = null;
     Boolean connected;
     File myFile;
     Scanner scan;
     
     
     try{
         serverSocket= new ServerSocket(10001);
         System.out.println("Server created");
     }catch(IOException e){
         e.printStackTrace();
     }
     try{
         clientSocket = serverSocket.accept();
         is= new DataInputStream(clientSocket.getInputStream());
         os= new DataOutputStream(clientSocket.getOutputStream());
         connected=true;
         System.out.println("Conectado");
         
         while(connected){
             recieved=is.readUTF();
             System.out.println(recieved);
             System.out.println(findOnMyFile(recieved));
             os.writeUTF(findOnMyFile(recieved));
                 
         }
     }catch(IOException e){
         e.printStackTrace();
         
     }
     
     
      
    }
    
        public static String findOnMyFile(String keyword){
            String retorno="";
            try{
            File myFile = new File("/Users/enriquecastillo/profesores.txt"); 
            FileReader fr=new FileReader(myFile);     
            BufferedReader br=new BufferedReader(fr);  
            StringBuffer sb=new StringBuffer();     
            String line;
            
            while((line=br.readLine())!=null)  
                {  
                 if (line.contains(keyword)){
                   sb.append(line);
                   retorno=sb.toString();
                 }
              
                }  
                fr.close();  
                return sb.toString();
                }  catch(Exception ex){
                    ex.printStackTrace();
                    }
            
            return retorno;
            }
 
    
}
