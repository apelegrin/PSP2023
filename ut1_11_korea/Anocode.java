package korea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Anocode {
    static Scanner teclado = new Scanner(System.in);
    final static int maximo_estaciones = 5;
    static Estacion[] estaciones = new Estacion[maximo_estaciones];
    static PrintWriter[] flujoSAtacar = new PrintWriter[maximo_estaciones];
    
    public static void main(String[] args) {
        try {
            

            CountDownLatch latch = new CountDownLatch(maximo_estaciones);
            Sincro sincro = new Sincro(maximo_estaciones);

            PipedWriter pipedRadioSalida = new PipedWriter();
            PipedReader pipedRadioEntrada = new PipedReader(pipedRadioSalida);
            BufferedReader flujoERadio = new BufferedReader(pipedRadioEntrada);

            PipedWriter[] pipedSalidaAtacar = new PipedWriter[maximo_estaciones];
            

            boolean fin = false;
            
            for (int i = 0; i < maximo_estaciones; i++) {
                pipedSalidaAtacar[i] = new PipedWriter();
                flujoSAtacar[i] = new PrintWriter(pipedSalidaAtacar[i]);
                estaciones[i] = new Estacion(i, new PipedReader(pipedSalidaAtacar[i]), pipedRadioSalida, latch, sincro);
                estaciones[i].start();
            }
            try {
            	//Esperamos a que todas las estaciones estén listas
                latch.await();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            System.out.println("Todas las estaciones operativas");

            while (!fin) {
            	//Leer estación que ha detectado movimiento
            	String numEstacion = flujoERadio.readLine();
            	int aviso = Integer.parseInt(numEstacion);
            	
            	System.out.println(
            			"La estación " + numEstacion + " ha detectado movimiento, envía el comando (atacar) o (fin) para terminar");
            	
            	String comando = teclado.nextLine();

            	if (comando.equals("atacar")) {
            		flujoSAtacar[aviso].println("atacar");
            		flujoSAtacar[aviso].flush();
            	} else if (comando.contains("fin")) {
            		terminarEstaciones();
            		fin = true;
            	}else {
            		System.out.println("La estación " + aviso + " ha abortado");
            		flujoSAtacar[aviso].println(comando);
            		flujoSAtacar[aviso].flush();
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ANOCODE Terminado");
    }

	private static void terminarEstaciones() {
		 for (int i = 0; i < maximo_estaciones; i++) {
			 flujoSAtacar[i].println("fin");
			 flujoSAtacar[i].flush();
		 }
	}
}
