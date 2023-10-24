package ut1_07_parking;

import java.util.Random;

public class Coche extends Thread{

	private Sincro sincro;
	private int id;
	private Parking parking;
	
	public Coche(int id, Sincro sincro, Parking parking) {
		this.id = id;
		this.sincro = sincro;
		this.parking = parking;
		this.start();
	}

	public void run() {
		Random aleatorio = new Random();
		//Esperamos que todos los hilos estén listos
		sincro.esperarInicioConcurrenteCoches();
		//Esperamos aleatoriamente antes de entrar
		try {
			int intentos = 0;
			//intentamos hasta 3 veces aparcar si no lo conseguimos
			//finalizamos
			while (intentos < 3) {
				Thread.sleep(aleatorio.nextInt(100));
				if (parking.esDentroParking()) {
					System.out.println("El coche "+id+" entra al parking");
					//simulamos un tiempo de estancia
					Thread.sleep(aleatorio.nextInt(400));
					parking.salirParking();
					System.out.println("El coche "+id+" sale del parking");
					intentos = 3;
				}
				else {
					System.out.println("El coche "+id+" no ha podido entrar al parking");
					intentos++;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//Notificamos al padre que hemos terminado
		sincro.notificarFinCoches();
		System.out.println("Coche "+id+" finalizado");
	}
}
