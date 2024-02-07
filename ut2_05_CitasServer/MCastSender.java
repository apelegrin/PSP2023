package domain;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class MCastSender {

	private InetAddress mCastInet;
	private int mCastPort;
	
	
	public MCastSender(InetAddress mCastInet, int mCastPort) {
		this.mCastInet = mCastInet;
		this.mCastPort = mCastPort;
	}

	public void mCastSend(String mensa) {
		DatagramSocket sUDP;
		try {
			
			sUDP = new DatagramSocket();
			DatagramPacket msgPantalla = new
					DatagramPacket(mensa.getBytes(),
								   mensa.getBytes().length,mCastInet,mCastPort);
			sUDP.send(msgPantalla);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
