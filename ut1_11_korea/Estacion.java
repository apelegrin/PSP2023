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
    private boolean fin = false;

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
    		//Informamos al padre de que estamos listos
    		latch.countDown();
    		//Esperamos que todas las estaciones inicien concurrentemente
    		sincro.inicioConcurrente();

    		while (!fin) {
    			int valorAleatorio = (int) (Math.random() * 11);

    			if (valorAleatorio >= 8) {
    				sincro.bloquearComunicaciones();
    				//System.out.println("Estación " + numeroEstacion + " ha detectado un ataque");
    				radio.println(numeroEstacion);
    				
    				String comando = flujoE.readLine();

    				if (comando.contains("atacar")) {
    					System.out.println("Estación " + numeroEstacion + " atacando");
    				} else if (comando.contains("fin")){
    						this.fin = true;
    						System.out.println(numeroEstacion+" reconoce fin");
    			    } else{
    					System.out.println("Comando no reconocido en la Estación " + numeroEstacion);
    				}
    				sincro.desbloquearComunicaciones();
    			} 
    			Thread.sleep(1000);
    		}
    		System.out.println(numeroEstacion+" terminando");
    	}catch (IOException | InterruptedException e) {
    		System.out.println("Finalizando ejecución "+numeroEstacion);
    	}
    }
}
