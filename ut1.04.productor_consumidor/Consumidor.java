package domain;

public class Consumidor extends Thread{
	private Buffer buffer;
	int id;
	
	public Consumidor(Buffer miBuffer, int id) {
		this.buffer = miBuffer;
		this.id = id;
	}

	public void run() {
		char valor;
		for(int i=0; i<10; i++){
			valor=buffer.recoger();
			System.out.println(i+ " Consumidor "+id+": "+valor);
			try{
				sleep(100);
			}catch (InterruptedException e) { }
		}
	}
}
