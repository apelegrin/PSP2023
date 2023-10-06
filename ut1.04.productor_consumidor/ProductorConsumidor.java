package main;

import domain.Buffer;
import domain.Consumidor;
import domain.Productor;
import domain.ProductorU;

public class ProductorConsumidor {

	public static void main(String[] args) {
		Buffer miBuffer = new Buffer();
		Productor productor   = new Productor(miBuffer);
		ProductorU productorU   = new ProductorU(miBuffer);
		Consumidor consumidor  = new Consumidor(miBuffer,1);
		Consumidor consumidor2 = new Consumidor(miBuffer,2);
		System.out.println("Iniciamos hilos");
		productor.start();
		productorU.start();
		consumidor.start();
		consumidor2.start();
		
		
		try {
			//Espera hasta el productor termine
			productor.join(); //while (productor.isAlive());
			productorU.join();
			//Espera hasta el consumidor termine
			consumidor.join(); //while (consumidor.isAlive());
			consumidor2.join(); //while (consumidor.isAlive());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Productor-Consumidor Finalizado");
	}

}



