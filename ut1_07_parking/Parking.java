package ut1_07_parking;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Parking {
    private int maxparking;
    private Semaphore estacionados;
    private Semaphore accesoContador;
    private Lock miLock = new ReentrantLock();;
    private int contadorEstacionamientos;
    private long tiempoEstacionamientos;
    
	public Parking(int maxparking) {
		this.maxparking = maxparking;
		this.estacionados = new Semaphore(maxparking);
		this.accesoContador = new Semaphore(1);
		this.contadorEstacionamientos = 0;
	}

	public int getContadorEstacionamientos() {
		int contador = 0;
		try {
			accesoContador.acquire();
			contador = contadorEstacionamientos;
			accesoContador.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return contador;
	}

	public void entrarParking() {
		try {
			estacionados.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public void salirParking() {
		estacionados.release();
		try {
			accesoContador.acquire();
			contadorEstacionamientos++;
			accesoContador.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean esDentroParking() {
		return estacionados.tryAcquire();
	}

	public void addTiempoEstacionado(long tiempoCoche) {
		miLock.lock();
		    tiempoEstacionamientos +=  tiempoCoche;
		miLock.unlock();
	}

	public long getTiempoEstacionamientos() {
		long tiempo = 0;

		miLock.lock();
		    tiempo = tiempoEstacionamientos;
		miLock.unlock();
		return tiempo;
		
	}
}
