package main;

public class Banco {
    private Cuenta [] cuenta;
	public Banco(int numCuentas) {
		
		cuenta = new Cuenta[numCuentas];
		for (int i=0; i < numCuentas; i++) {
			cuenta[i] = new Cuenta(0);
		}
	}

}
