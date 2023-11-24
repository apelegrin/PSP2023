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

    public static void main(String[] args) {
        try {
            Estacion[] estaciones = new Estacion[maximo_estaciones];

            CountDownLatch latch = new CountDownLatch(maximo_estaciones);
            Sincro sincro = new Sincro();

            PipedWriter pipedRadioSalida = new PipedWriter();
            PipedReader pipedRadioEntrada = new PipedReader(pipedRadioSalida);
            BufferedReader flujoERadio = new BufferedReader(pipedRadioEntrada);

            PipedWriter[] pipedSalidaAtacar = new PipedWriter[maximo_estaciones];
            PrintWriter[] flujoSAtacar = new PrintWriter[maximo_estaciones];

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

            while (true) {
                for (int i = 0; i < maximo_estaciones; i++) {
                    int aviso = flujoERadio.read();
                    String numEstacion = String.valueOf(aviso);
                    System.out.println(
                            "El misil " + numEstacion + " está preparado para ser lanzado, envía el comando (atacar)");
                    String comando = teclado.nextLine();

                    if (comando.equals("atacar")) {
                        flujoSAtacar[aviso].println("atacar");
                        flujoSAtacar[aviso].flush();
                    } else {
                        System.out.println("El misil " + aviso + " ha abortado");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
