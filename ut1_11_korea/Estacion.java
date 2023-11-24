package korea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;

public class Estacion extends Thread {
    private int numeroEstacion;
    private PrintWriter radio;
    private CountDownLatch latch;
    private Sincro sincro;
    private BufferedReader flujoE;

    public Estacion(int numeroEstacion, PipedReader receptorComandoAtacar, PipedWriter emisorAvisoAtaque,
            CountDownLatch latch, Sincro sincro) {
        this.numeroEstacion = numeroEstacion;
        this.radio = new PrintWriter(emisorAvisoAtaque);
        this.latch = latch;
        this.sincro = sincro;
        this.flujoE = new BufferedReader(receptorComandoAtacar);
    }

    @Override
    public void run() {
        try {
            latch.countDown();
            latch.await();
            boolean cont = false;

            while (!cont) {
                int valorAleatorio = (int) (Math.random() * 11);

                if (valorAleatorio >= 8) {
                    sincro.bloquearComunicaciones();
                    System.out.println("Misil " + numeroEstacion + " ha detectado un ataque");
                    radio.write(numeroEstacion);

                    cont = true;
                } else {
                    Thread.sleep(1000);
                }
            }

            try {
                String comando = flujoE.readLine();

                if ("atacar".equals(comando)) {
                    System.out.println("Estación " + numeroEstacion + " atacando");
                } else {
                    System.out.println("Comando no reconocido en la Estación " + numeroEstacion);
                }

                sincro.desbloquearComunicaciones();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                flujoE.close();
                radio.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
