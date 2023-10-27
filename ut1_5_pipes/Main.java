package ut1_05_pipes;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class Main {

	public static void main(String[] args) {
		try {
			//Crearmos la tubería
			PipedWriter emisor   = new PipedWriter();
			PipedReader receptor = new PipedReader(emisor);
			
			//Creamos Productor y Consumidor
			Productor  pHilo1 = new Productor(1,emisor);
			//Productor  pHilo2 = new Productor(2,emisor);
			Consumidor cHilo1 = new Consumidor(1,receptor);
			//Consumidor cHilo2 = new Consumidor(2,receptor);
			pHilo1.start();
			cHilo1.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
