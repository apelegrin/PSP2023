package ut_12_bank_simulation;

public class Main {

	static final int MAX_CLIENTES = 240;
	
	public static void main(String[] args) {
		Cliente [] cliente = new Cliente[MAX_CLIENTES];
		Sincro sincro = new Sincro(MAX_CLIENTES);
		Cuenta cuenta = new Cuenta(100);
		long inicioSimulacion = System.currentTimeMillis();
		System.out.println("Inicio de la simulación");
		long finSimulacion;
		long tiempoSimulacion;
		//Creamos hilos de ingreso/reintegro 100€
		for (int i=0; i < 80; i++) {
			if (i < 40) {
				cliente[i] = new Cliente(i,cuenta,sincro,Operacion.INGRESO,100);
			}else {
				cliente[i] = new Cliente(i,cuenta,sincro,Operacion.REINTEGRO,100);
			}
		}
		//Creamos hilos de ingreso/reintegro 50€
		for (int i=80; i < 120; i++) {
			if (i < 100) {
				cliente[i] = new Cliente(i,cuenta,sincro,Operacion.INGRESO,50);
			}else {
				cliente[i] = new Cliente(i,cuenta,sincro,Operacion.REINTEGRO,50);
			}
		}
		//Creamos hilos de ingreso/reintegro 20€
		for (int i=120; i < 240; i++) {
			if (i < 180) {
				cliente[i] = new Cliente(i,cuenta,sincro,Operacion.INGRESO,20);
			}else {
				cliente[i] = new Cliente(i,cuenta,sincro,Operacion.REINTEGRO,20);
			}
		}
		/* Esperamos que termine la ejecución de hilos */
		sincro.awaitFinClientes();
		finSimulacion = System.currentTimeMillis();
		tiempoSimulacion = finSimulacion - inicioSimulacion;
		System.out.println("Tiempo de simulacion "+tiempoSimulacion);
		System.out.println("Saldo de la cuenta "+cuenta.getSaldo());
	}
}
