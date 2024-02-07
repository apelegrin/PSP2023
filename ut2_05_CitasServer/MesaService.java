package domain;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MesaService extends Thread {
	private int id;
	private int mesaId;
	private Socket s;
	private PrintWriter flujoS;
	private Scanner flujoE;
	private ContadorMesas contadorMesas;
	
	public MesaService(int id, Socket s, ContadorMesas contadorMesas) {
		this.s = s;
		this.id = id;
		this.mesaId = 0;
		this.contadorMesas = contadorMesas;
		this.start();
	}

	public void run() {
		boolean fin = false;
		System.out.println("Conexión desde "+s.getInetAddress().getHostAddress());
		
		try {
			//Enlazamos los flujos
			flujoS = new PrintWriter(s.getOutputStream());
			flujoE = new Scanner(s.getInputStream());
			flujoS.println("Bienvenido a SATA");
			flujoS.println("Puesto "+id);
			flujoS.println("Indica la mesa que quieres atender");
			flujoS.flush();
			while (!fin) {
				String comando = flujoE.next();
				System.out.println(comando);
				if (comando.contains("quit")) {
					fin = true;
				}
				else {
					if (comando.contains("mesa")) {
						mesaId = flujoE.nextInt();
						if (mesaId > 0) {
							flujoS.println("Seleccionada la mesa "+mesaId);
						}
						else {
							flujoS.println("Error de comando ");
						}
					}
					else{ 
						//Para cualquier comando no mesa
						//Primero tenemo que tener asignado una mese
						if (mesaId > 0) {
							procesaComando(comando);
						}
						else {
							flujoS.println("Asigna primero una mesa ");
						}
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
		int siguienteAlumno;
		int pasaSiguienteAlumno;
		//Pedimos número como alumno
		if (comando.contains("numero")) {
			siguienteAlumno = contadorMesas.nextAlumno();
			String mensa = "Número: "+siguienteAlumno;
			flujoS.println(mensa+" espere ser atendido");
			flujoS.flush();
		}
		else if (comando.contains("next")) {
			pasaSiguienteAlumno = contadorMesas.nextContador();
			//Si hay peticiones pendientes
			if (pasaSiguienteAlumno != 0) {
				String mensa = "NÚMERO : "+ pasaSiguienteAlumno+ " Pase a la Mesa "+mesaId;
				flujoS.println(mensa);
				flujoS.flush();
			}else {
				String mensa = "No hay alumnos que atender";
				flujoS.println(mensa);
				flujoS.flush();
			}
		}
	}
}
