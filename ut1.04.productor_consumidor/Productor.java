package domain;

public class Productor extends Thread{
	private Buffer buffer;
	private final String letras="abcdefghijklmnopqrstuvxyz";
	
	public Productor(Buffer miBuffer) {
		this.buffer = miBuffer;
	}
	
	public void run() {
		for(int i=0; i<10; i++){
			int posi = (int)(Math.random()*letras.length());
			char c=letras.charAt(posi);
			buffer.poner(c);
			System.out.println(i+" Productor: " +c);
			try {
				sleep(400);
			} catch (InterruptedException e) { }
		}
	}

}
