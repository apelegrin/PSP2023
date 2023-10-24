package ut1_07_parking;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Sincro {
	private int maxparking;
	private int simulacoches;
	private CountDownLatch esperaFinCoches;
	private CyclicBarrier inicioConcurrenteCoches;
	
	public Sincro(int maxparking, int simulacoches) {
		this.maxparking = maxparking;
		this.simulacoches = simulacoches;
		this.esperaFinCoches = new CountDownLatch(simulacoches);
		this.inicioConcurrenteCoches = new CyclicBarrier(simulacoches);
	}

	public void esperarInicioConcurrenteCoches() {
		try {
			inicioConcurrenteCoches.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
	public void esperarFinCoches() {
		try {
			esperaFinCoches.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void notificarFinCoches() {
		esperaFinCoches.countDown();
	}

}
