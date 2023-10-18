package ut1_06_NADC;

import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

public class Norad {
	final static int MAXMISILES = 100;
	
	public static void main(String[] args) {
		//objeto para la sincronización
		Sincroniza sincro = new Sincroniza(MAXMISILES);
		
		Misil [] misil = new Misil[MAXMISILES];
		
		PipedWriter [] emisor = new PipedWriter[MAXMISILES];
		PrintWriter [] flujoS = new PrintWriter[MAXMISILES];
		
		for (int i = 0; i < misil.length; i++) {
		    emisor[i] = new PipedWriter();
		    flujoS[i] = new PrintWriter(emisor[i]);
			misil[i] = new Misil(i,sincro,emisor[i]);
		}
		
		sincro.esperarMisilesListos();
		System.out.println("Todos los misiles preparados");
		Scanner sc = new Scanner(System.in);
		boolean fin = false;
		while(!fin) {
			System.out.println("Orden > ");
			String comando = sc.nextLine();
			if (comando.contains("atacar")) {
				fin = true;
				//lanzamos todos los misiles
				sincro.realizarActivacion();
				dobleVerificacion(flujoS);
			}
		}
	}

	private static void dobleVerificacion(PrintWriter[] flujoS) {
		for (int i = 0; i < MAXMISILES; i++) {
			try {
				Thread.sleep(1000);
				flujoS[i].println("atacar");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	private static void sleep(int i) {
		// TODO Auto-generated method stub
		
	}

}
