package ut1_07_parking;

public class Main {
	final static int SIMULACOCHES = 100;
	final static int MAXPARKING = 25;
	
	public static void main(String[] args) {
		Coche[] coche = new Coche[SIMULACOCHES];
        Parking parking = new Parking(MAXPARKING);
        Sincro sincro = new Sincro(MAXPARKING,SIMULACOCHES);
        System.out.println("Iniciando simulación ...");
        long inicioSimulacion = System.currentTimeMillis();
        for (int i = 0; i < coche.length; i++) {
			coche[i] = new Coche(i,sincro,parking);
		}
        sincro.esperarFinCoches();
        long finSimulacion = System.currentTimeMillis();
        long tiempoSimulacion = finSimulacion-inicioSimulacion;
        System.out.println("Tiempo simulación "+tiempoSimulacion);
        System.out.println("Han entrado "+parking.getContadorEstacionamientos());
	}

}
