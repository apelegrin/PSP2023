package ut1_08_filosofos;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {
		int nFilosofos = 5;
		CyclicBarrier inicio = new CyclicBarrier(nFilosofos);
		Filosofo [] filosofo = new Filosofo[nFilosofos];
		Semaphore [] palilloEspera = new Semaphore[nFilosofos];
        int [][] palillos = {{4,0},{0,1},{1,2},{2,3},{3,4}};
        
        //Inicializamos los semáforos asociados a los palillos
        for (int i=0;i < palilloEspera.length; i++) {
        	palilloEspera[i] = new Semaphore(1);
        }
        
        int id=0;
        for (Filosofo f: filosofo) {
        	f = new Filosofo(id,palilloEspera,palillos,inicio);
        	id++;
        }
	}

}





