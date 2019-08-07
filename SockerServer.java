package com.bhge.orchestration;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

public class SockerServer {
	static HashMap<String, String>  activeClList = new HashMap();
	public static void main(String args[])throws Exception{  
		
	ServerSocket ss=new ServerSocket(3333);  
	Socket s;
	 int i=0;
	  
	String str1="";
	String str2="" ;
	
	while(true){ 
		s = ss.accept();  
		DataInputStream din=new DataInputStream(s.getInputStream());  
		DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 
		/*str1=din.readUTF();  */
		System.out.println("******* new request *******");
		//StringTokenizer st = new StringTokenizer(str1 , "#");
		//String message = st.nextToken();
		//System.out.println("Message : "+message);
		//String userName = st.nextToken();
		//System.out.println("User Name : "+userName);
		//ClientHandler mtch = new ClientHandler(userName , message , dout ,din); 
		ClientHandler mtch = new ClientHandler(s , "client " + i , din , dout);
		activeClList.put("client " + i , "tet");
		Thread t = new Thread(mtch); 
		t.start();
		i++; 
		/*str2=br.readLine();  
		dout.writeUTF(str2);  
		dout.flush();  */
	}  
	 
	 
	 
	}
}

