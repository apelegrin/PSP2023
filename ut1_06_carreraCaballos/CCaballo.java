package ut1_05_carreraCaballos;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CCaballo extends Thread {
	private int id;
	private Tablon tablon;
	private CyclicBarrier inicioConcurrente;
	private int posicion;
	private final int longitudPista = 100;
	private CountDownLatch finHilos;
	
	public CCaballo(int id, Tablon tablon, CyclicBarrier inicioConcurrente, CountDownLatch finHilos) {
		this.id = id;
		this.tablon = tablon;
		this.inicioConcurrente = inicioConcurrente;
		this.finHilos = finHilos;
		this.start();
	}

	public void run() {
		Random aleatorio = new Random();
		int avanza = 0;
		posicion = 0;
		try {
			inicioConcurrente.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		//corremos mientras no llegamos al final
		while (posicion < longitudPista) {
			avanza = aleatorio.nextInt(10)+1;
			posicion+= avanza;
			tablon.setPosicion(id,posicion);
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		tablon.setGanador(id);
		//notificamos al padre el fin
		finHilos.countDown();
	}
}







