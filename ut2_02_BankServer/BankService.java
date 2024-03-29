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
	private Banco miBanco;
	private int cuenta;
	
	public BankService(int id, Socket s, Banco miBanco) {
		this.id = id;
		this.s = s;
		this.miBanco = miBanco;
		this.cuenta = -1;
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
				String comando = flujoE.next();
				System.out.println(comando);
				if (comando.contains("quit")) {
					fin = true;
				}
				else {
					if (comando.contains("cuenta")) {
						cuenta = flujoE.nextInt();
						if (cuenta >= 0) {
							flujoS.println("Seleccionada la cuenta "+cuenta);
						}
						else {
							flujoS.println("Error de comando ");
						}
					}
					else{ 
						procesaComando(comando);
					};
				}
				flujoS.flush();
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
		int importe = 0;
		if (cuenta == -1) {
			flujoS.println("Para operar activa una cuenta");
		}
		else {
			if (comando.contains("ingreso")) {
				importe = flujoE.nextInt();
				miBanco.setIngreso(cuenta,importe);
				flujoS.println("Ingresado en cuenta "+cuenta);
				flujoS.println("Saldo "+miBanco.getSaldo(cuenta));
			}
			else if (comando.contains("reintegro")) {
				importe = flujoE.nextInt();
				int cantidad = miBanco.getReintegro(cuenta,importe);
				if (cantidad != -1) {
					flujoS.println("Retirado de cuenta "+cuenta+" importe "+cantidad);
					flujoS.println("Saldo "+miBanco.getSaldo(cuenta));
				}else {
					flujoS.println("Saldo insuficiente");
				}
			}
			else if (comando.contains("saldo")) {
				flujoS.println("La cuenta "+cuenta+" tiene un saldo de "+miBanco.getSaldo(cuenta));
			}
			else if (comando.contains("ingreso")) {
				importe = flujoE.nextInt();
				miBanco.setIngreso(cuenta,importe);
				flujoS.println("Ingresado en cuenta "+cuenta);
				flujoS.println("Saldo "+miBanco.getSaldo(cuenta));
			}
			else if (comando.contains("reintegro")) {
				importe = flujoE.nextInt();
				int cantidad = miBanco.getReintegro(cuenta,importe);
				if (cantidad != -1) {
					flujoS.println("Retirado de cuenta "+cuenta+" importe "+cantidad);
					flujoS.println("Saldo "+miBanco.getSaldo(cuenta));
				}else {
					flujoS.println("Saldo insuficiente");
				}
			}
			else if (comando.contains("saldo")) {
				flujoS.println("La cuenta "+cuenta+" tiene un saldo de "+miBanco.getSaldo(cuenta));
			}
		}//end_else
	}
}
