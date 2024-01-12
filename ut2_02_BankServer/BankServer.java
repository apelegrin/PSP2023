package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BankServer {

	public static void main(String[] args) {
		try {
			int port = 8888;
			ServerSocket miServer = new ServerSocket(port);
			Banco miBanco = new Banco(100);
			System.out.println("Server running... at "+port);
			boolean fin = false;
			int id = 0;
			while (!fin) {
				//Acceptamos la conexión
				Socket s = miServer.accept();
				//Iniciamos el servicio que gestiona la conexión
				id++;
				BankService servicio = new BankService(id,s,miBanco);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
