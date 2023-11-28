package ut_12_bank_simulation;

import java.util.concurrent.Semaphore;

public class Cuenta {
    private int saldo;
    private Semaphore lock; 
    
	public Cuenta(int cantidad) {
		this.saldo = cantidad;
		this.lock = new Semaphore(1);
	}

	public int getSaldo() {
		return saldo;
	}

	public void ingreso(int cantidad) {
		this.saldo += cantidad;
	}

	public void reintegro(int cantidad) {
			this.saldo -= cantidad;
			if (this.saldo < 0) {
				System.out.println("Saldo negativo >"+this.saldo);
			}
	}

}
