package ut1_09_Restaurante;

import java.io.BufferedReader;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;

public class Restaurante {

	public static void main(String[] args) {
		// variable para el control del número de clientes a simular
		final int maxMesas = 200;
		final int maxCocineros = 10;

		//array de mesas que deben ser atendidas
		Mesa [] mesa = new Mesa[maxMesas];

		//variables para calcular el tiempo de la simulación
		long inicioSimulacion = 0;
		long finSimulacion = 0;
		long tiempoSimulacion = 0;

		//pipe de comunicaciones
		PipedWriter emisor = new PipedWriter();
		PipedReader receptor = new PipedReader(emisor);
		PrintWriter flujoS = new PrintWriter(emisor);
		BufferedReader flujoE = new BufferedReader(receptor);

		// Definimos el metre
		Metre metre = new Metre(flujoE);
		metre.start();
		
		//Definimos la clase de sincronización
		Sincro sincro = new Sincro(maxMesas,maxCocineros);
		
		// inicializar los hilos de los puestos
		for (int i = 0; i < maxMesas; i++) {
			mesa[i] = new Mesa(i,sincro,flujoS);
		}

		//inicio simulación
		inicioSimulacion = System.currentTimeMillis();

		// espera a que todos los puestos sean atendidos
		sincro.esperarFin();

		//fin simulacion 
		finSimulacion = System.currentTimeMillis();
		tiempoSimulacion = finSimulacion - inicioSimulacion; 
		System.out.println("La simulación ha tardado "+tiempoSimulacion+" ms");
	}	

}
