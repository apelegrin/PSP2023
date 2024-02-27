package domain;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.Socket;

public class OrejaUDPReceiver extends Thread {
	private Socket sTCP;
	private final String netUDP;
	private final int portUDP;
	private boolean recibiendo;
	private PrintWriter flujoS;
	private boolean fin;
	private InetAddress mcastAddr;
	private MulticastSocket sUDPMulti;
	
	public OrejaUDPReceiver(Socket sTCP, String netUDP, int portUDP) {
		this.sTCP = sTCP;
		this.netUDP = netUDP;
		this.portUDP = portUDP;
		this.fin = false;
		this.start();
	}

	public void run() {
		try {
			this.flujoS = new PrintWriter(sTCP.getOutputStream());
			InetAddress mcastAddr = InetAddress.getByName(netUDP);
			InetSocketAddress group = 
		    		new InetSocketAddress(mcastAddr,portUDP);
			this.sUDPMulti = new MulticastSocket(portUDP);
		    NetworkInterface netIf = NetworkInterface.getByName("wlp1s0");
			sUDPMulti.joinGroup(group, netIf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Conectado a "+netUDP+":"+portUDP);
		byte [] buffer = new byte[1024];
		while (!fin) {
			DatagramPacket recibido = new 
	    			DatagramPacket(buffer,buffer.length);
	    	try {
				sUDPMulti.receive(recibido);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	if (recibiendo) {
	    		flujoS.println("Recibido :"+new String(recibido.getData()));
	    		flujoS.flush();
	    	}
		}
	}
	
	public void finalizar() {
		this.fin = true;
		
	}

	public void startSending() {
		this.recibiendo = true;
		
	}

	public void stopSending() {
		this.recibiendo = false;
		
	}

}
