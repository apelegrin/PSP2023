package korea;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Sincro {

    Semaphore comunicaciones = new Semaphore(1);
    CyclicBarrier inicioEstacionesConcurrente;
    
    public Sincro(int maximoEstaciones) {
		this.inicioEstacionesConcurrente = new CyclicBarrier(maximoEstaciones);
	}

	public void bloquearComunicaciones() {
        try {
            comunicaciones.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void desbloquearComunicaciones() {
        comunicaciones.release();
    }

	public void inicioConcurrente() {
		try {
			this.inicioEstacionesConcurrente.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		
	}
}
