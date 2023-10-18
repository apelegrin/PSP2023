package ut1_06_NADC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class Misil extends Thread{
	private int id;
	private Sincroniza sincro;
	private PipedReader receptor;
	private BufferedReader flujoE;
	
	public Misil(int id, Sincroniza sincro, PipedWriter emisor) {
		try {
			this.id = id;
			this.receptor = new PipedReader(emisor);
			this.flujoE = new BufferedReader(receptor);
			this.sincro = sincro;
			this.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void run() {
		//Informa a NORAD que está listo
		System.out.println("Misil "+id+" armado");
		sincro.notificarMisilListos();
		//Esperamos que el NORAD nos active
		sincro.esperarActivacion();
		System.out.println("Misil "+id+" esperando doble verificación");
		try {
			String comando = flujoE.readLine();
			if (comando.contains("atacar")){
				System.out.println("Misil "+id+" lanzado");
			}
			else {
				System.out.println("Misil "+id+" abortado");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
