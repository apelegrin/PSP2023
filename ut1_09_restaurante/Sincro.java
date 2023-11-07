package ut1_09_Restaurante;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Sincro {

	CyclicBarrier iniciaClientes;
	CountDownLatch esperaFin;
	Semaphore cocinerosLibres;
	Lock miLock;
	
	public Sincro(int maxMesas, int maxCocineros) {
		this.iniciaClientes = new CyclicBarrier(maxMesas);
		this.esperaFin = new CountDownLatch(maxMesas);
		this.cocinerosLibres = new Semaphore(maxCocineros);
		this.miLock = new ReentrantLock();
	}

	public void esperarFin() {
		try {
			esperaFin.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
