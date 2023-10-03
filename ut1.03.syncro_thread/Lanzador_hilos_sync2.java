
public class Lanzador_hilos_sync2 {

	public static void main(String[] args) {
		
		Object candado = new Object();
		
		int maxHilos = 100;
		HiloSincro02 [] misHilos = new HiloSincro02[maxHilos];
		synchronized (candado) {
			//Inicializamos los Hilos
			for (int i=0;i < maxHilos;i++) {
				misHilos[i] = new HiloSincro02(i,candado);
			}
			System.out.println("Uff cuanto zagal...");
		}
	}

}
