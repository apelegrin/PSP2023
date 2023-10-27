package ut1_05_pipes;

import java.io.PipedWriter;
import java.io.PrintWriter;

public class Productor extends Thread{

	private PrintWriter flujoS;
	private int id;
	
	public Productor(int id, PipedWriter emisor) {
		this.id = id;
		flujoS = new PrintWriter(emisor);
	}

	public void run() {
		for (int i=0;i<10;i++) {
			String mensa = "Hola "+i+" hilo "+id;
			flujoS.println(mensa);
			System.out.println("Emisor "+id+" manda "+mensa);
			
		}
	}
}
