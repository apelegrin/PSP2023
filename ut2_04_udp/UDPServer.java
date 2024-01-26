package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {

	public static void main(String[] args) {
		final int port = 7777;
		boolean fin = false;
		
		try {
			DatagramSocket sUDP = new DatagramSocket(port);
			byte[] buffer = new byte[1024];
			System.out.println("Servidor UDP funcionando...");
			while (!fin) {
				//Construimos un datagrama para recibir
				//las peticiones
				DatagramPacket peticion = 
						new DatagramPacket(buffer,buffer.length);
				//Leemos las peticiones
				sUDP.receive(peticion);
				
				System.out.println("Datagrama recibido desde "+
									peticion.getAddress());
				System.out.println("Puerto :"+
						peticion.getPort());
				System.out.println("Datos :"+new String(peticion.getData()));
				
				//Enviamos un echo como respuesta
				DatagramPacket respuesta = 
						new DatagramPacket(peticion.getData(),peticion.getLength(),
								peticion.getAddress(),peticion.getPort());
				sUDP.send(respuesta);
				
			}
			
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

