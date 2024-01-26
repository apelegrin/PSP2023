package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPCliente {

	public static void main(String[] args) {
		final int port = 7777;
		boolean fin = false;
		String datos;
		String serverIP = "127.0.0.1";
		BufferedReader consola = 
				new BufferedReader(new InputStreamReader(System.in));
		try {
			DatagramSocket sUDP = new DatagramSocket();
			InetAddress host = InetAddress.getByName(serverIP);
			
			byte[] buffer = new byte[1024];
			System.out.println("Cliente UDP funcionando...");
			
			while (!fin) {
				//Leer la entrada
				datos = consola.readLine();
				buffer = datos.getBytes();
				//Creamos el datagrama
				DatagramPacket mensaje = 
						new DatagramPacket(buffer,buffer.length,host,port);
				sUDP.send(mensaje);
				//Leemos la respuesta del servidor
				byte [] bufferR = new byte[1024];
				DatagramPacket respuesta =  
						new DatagramPacket(bufferR,bufferR.length);
				sUDP.receive(respuesta);
				//Imprimimos la respuesta
				System.out.println("Respuesta :"+new String(respuesta.getData()));
			}
			
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

