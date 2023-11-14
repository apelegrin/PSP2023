package domain;

import java.util.concurrent.CountDownLatch;

public class Sincro {

	private CountDownLatch inicioSprites;
	private int maxSprites;
	
	public Sincro(int maxSprites) {
		this.inicioSprites = new CountDownLatch(1);
		this.maxSprites = maxSprites;
	}

	public void spriteCountDown() {
		this.inicioSprites.countDown();
	}
	
	public void spriteAwait() {
		try {
			this.inicioSprites.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
