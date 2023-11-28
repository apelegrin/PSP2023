package ut_12_bank_simulation;

import java.util.Random;

public class Cliente extends Thread {
	private int id;
	private Cuenta cuenta;
	private Sincro sincro;
	private Operacion operacion;
	private int cantidad;
	
	public Cliente(int id, Cuenta cuenta, Sincro sincro, Operacion operacion, int cantidad) {
		this.id = id;
		this.cuenta = cuenta;
		this.sincro = sincro;
		this.operacion = operacion;
		this.cantidad = cantidad;
		this.start();
	}
	
	public void run() {
		//Iniciamos todos los hilos concurrentemente
		sincro.awaitClientesListos();
		Random aleatorio = new Random();
		try {
			Thread.sleep(aleatorio.nextInt(500)+500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Cliente: "+this.id+" operando");
		//Realizamos la operacion
		switch (operacion) {
		case INGRESO:
			this.cuenta.ingreso(cantidad);
			break;
		case REINTEGRO:
			this.cuenta.reintegro(cantidad);
			break;
		}
		//Comunicamos al padre que hemos terminado
		sincro.countFinClientes();
	}



}
