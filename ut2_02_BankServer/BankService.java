package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BankService extends Thread {
	private int id;
	private Socket s;
	private PrintWriter flujoS;
	private Scanner flujoE;
	
	public BankService(int id, Socket s) {
		this.id = id;
		this.s = s;
		this.start();
	}

	public void run() {
		boolean fin = false;
		System.out.println("Conexión desde "+s.getInetAddress().getHostAddress());
		
		try {
			//Enlazamos los flujos
			flujoS = new PrintWriter(s.getOutputStream());
			flujoE = new Scanner(s.getInputStream());
			flujoS.println("Bienvenido a la banca online. Le atiende "+id);
			flujoS.flush();
			while (!fin) {
				String comando = flujoE.nextLine();
				if (comando.contains("quit")) {
					fin = true;
				}
				else {
					procesaComando(comando);
				}
			}
			flujoS.close();
			flujoE.close();
			s.close();
		} catch (IOException |NoSuchElementException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("El cliente cierra la terminal");
		}
		
	}

	private void procesaComando(String comando) {
		
		
	}
}
