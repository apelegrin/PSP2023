package ut1_05_carreraCaballos;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CHipodromo {
	final static int num_participantes = 4;
	
	public static void main(String[] args) {
		boolean corriendo = true;
		CyclicBarrier inicioConcurrente = new CyclicBarrier(num_participantes);
		CountDownLatch finHilos = 
				new CountDownLatch(num_participantes+1);
				//Sumamos 1 por el hilo del tablon
		Tablon tablon = new Tablon(num_participantes,finHilos);
		CCaballo[] caballo = new CCaballo[num_participantes];
		
		for (int i = 0; i < caballo.length; i++) {
			caballo[i] = new CCaballo(i,tablon,inicioConcurrente,finHilos);
		}
		//Espera que los caballos le notifiquen el fin
		try {
			finHilos.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*
		while (corriendo) {
			corriendo = false;
			for (int i = 0; i < caballo.length; i++) {
				if (caballo[i].isAlive()) {
					corriendo = true;}
			}
		}
		*/
		System.out.println("Carrera terminada");
		System.out.println("El ganador es "+tablon.getGanador());
	}

}
