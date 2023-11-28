package ut_12_bank_simulation;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Sincro {

	private int maxClientes;
	private CyclicBarrier cbClientesListos;
	private CountDownLatch countFinCLientes;
	
	public Sincro(int maxClientes) {
		this.cbClientesListos = new CyclicBarrier(maxClientes);
		this.countFinCLientes = new CountDownLatch(maxClientes);
		this.maxClientes = maxClientes;
	}

	public void awaitFinClientes() {
		try {
			this.countFinCLientes.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void countFinClientes() {
		this.countFinCLientes.countDown();
	}

	public void awaitClientesListos() {
		try {
			this.cbClientesListos.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		
	}

}
