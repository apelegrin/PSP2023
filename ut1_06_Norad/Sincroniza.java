package ut1_06_NADC;

import java.util.concurrent.CountDownLatch;

public class Sincroniza {
	private int maxmisiles;
	private CountDownLatch misilesListosCD;
	private CountDownLatch lanzaMisilesCD;
	
	public Sincroniza(int maxmisiles) {
		this.maxmisiles = maxmisiles;
		this.misilesListosCD = new CountDownLatch(maxmisiles);
		this.lanzaMisilesCD = new CountDownLatch(1);
		
	}

	public void esperarMisilesListos() {
		try {
			misilesListosCD.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public void notificarMisilListos() {
		misilesListosCD.countDown();
		
	}

	public void esperarActivacion() {
		try {
			lanzaMisilesCD.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public void realizarActivacion() {
		lanzaMisilesCD.countDown();
		
	}

}
