package com.bhge.orchestration;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class ClientHandler implements Runnable {
	
	Scanner scn = new Scanner(System.in); 
    private String name; 
    DataInputStream dis; 
     DataOutputStream dos; 
     String message;
    Socket s; 
     
	
	public ClientHandler(Socket s, String name, 
            DataInputStream dis, DataOutputStream dos) { 
					this.dis = dis; 
					this.dos = dos; 
					this.name = name; 
					this.s = s; 
					 
			} 
	public ClientHandler( String name, String message , DataOutputStream dos , DataInputStream din) { 
					 
					this.name = name; 
					this.dis = din; 
					this.message = message;
					this.dos = dos;
					 
			} 

	@Override
	public void run() {
		System.out.print("Handler Started");
		String message = "" ; 
		/*try {
			message = this.dis.readUTF();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		while (true) {
			
			try {
				
				/*StringTokenizer st = new StringTokenizer(message, "#"); 
				String MsgToSend = st.nextToken(); 
				String recipient = st.nextToken(); */
				message = this.dis.readUTF();
				HashMap<String, String> hs = SockerServer.activeClList;
				for (Map.Entry<String, String> s : hs.entrySet()) {
					if (s.getKey().equals(name) ) 
					{ 
						this.dos.writeUTF(this.name+" : "+message); 
						break; 
					} 
				}
				
				/*for (String mc : SockerServer.activeClList) 
				{ 
					// if the recipient is found, write on its 
					// output stream 
					
					if (mc.name.equals(name) ) 
					{ 
						mc.dos.writeUTF(this.name+" : "+message); 
						break; 
					} 
				} */
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
