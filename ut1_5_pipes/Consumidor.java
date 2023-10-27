package ut1_05_pipes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;

public class Consumidor extends Thread{

	private BufferedReader flujoE;
	private boolean fin = false;
	private int id;
	
	public Consumidor(int id, PipedReader receptor) {
		this.id = id;
		flujoE = new BufferedReader(receptor);
	}

	public void run() {
		while (!fin) {
			try {
				String mensa = flujoE.readLine();
				System.out.println("Recibido por "+id+" "+mensa);
			} catch (IOException e) {
				fin = true;
			}
		}
		System.out.println("Consumidor cerrado");
	}
}
