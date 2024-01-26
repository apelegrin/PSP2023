package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.UnknownHostException;

public class UDPMultiClient {

	public static void main(String[] args) {
		final String serverIP = "224.0.0.1";
		final int port = 7777;
		
		try {
			InetAddress mcastAddr = InetAddress.getByName(serverIP);
		    InetSocketAddress group = 
		    		new InetSocketAddress(mcastAddr,port);
		    MulticastSocket sUDPMulti = new MulticastSocket(port);
		    NetworkInterface netIf = NetworkInterface.getByName("enx00e04c36020a");
		    sUDPMulti.joinGroup(group, netIf);
		    System.out.println("Conectado a "+serverIP+":"+port);
		    boolean fin = false;
		    byte [] buffer = new byte[1024];
		    
		    while (!fin) {
		    	DatagramPacket recibido = new 
		    			DatagramPacket(buffer,buffer.length);
		    	System.out.println("Esperando..");
		    	sUDPMulti.receive(recibido);
		    	System.out.println("Recibido :"+new 
		    			String(recibido.getData()));
		    }
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
