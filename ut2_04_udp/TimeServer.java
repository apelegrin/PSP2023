package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeServer {

	public static void main(String[] args) {
		final String serverIP = "224.0.0.1";
		final int port = 7777;
		final int delay = 1000;

		int hh, mm, ss;
		boolean fin = false;
		
        try {
			InetAddress inetServer = InetAddress.getByName(serverIP);
			
			DatagramSocket sUDP = new DatagramSocket();
			String mensa;
			
			while (!fin) {
				Calendar miCalendario = new GregorianCalendar();
				hh = miCalendario.get(Calendar.HOUR_OF_DAY);
				mm = miCalendario.get(Calendar.MINUTE);
				ss = miCalendario.get(Calendar.SECOND);
				mensa = "Clock > "+hh+":"+mm+":"+ss;
				DatagramPacket msgHora = new
						DatagramPacket(mensa.getBytes(),
									   mensa.getBytes().length,
									   inetServer,
									   port);
				sUDP.send(msgHora);
				System.out.println("Sending... "+mensa);
				Thread.sleep(delay);
			}
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        


	}

}
