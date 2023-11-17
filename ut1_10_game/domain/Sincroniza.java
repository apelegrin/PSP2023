package domain;

import java.util.concurrent.CountDownLatch;

public class Sincroniza {

	private long tiempoInicial;
	
	private CountDownLatch iniciaSprites = new CountDownLatch(1);
	
	public void iniciarSprites() {
		iniciaSprites.countDown();
		tiempoInicial = System.currentTimeMillis();
	}

	public void esperaIniciarSprites() {
		try {
			iniciaSprites.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
