package main;

public class Banco {
    private Cuenta [] cuenta;
	public Banco(int numCuentas) {
		
		cuenta = new Cuenta[numCuentas];
		for (int i=0; i < numCuentas; i++) {
			cuenta[i] = new Cuenta(0);
		}
	}
	public void setIngreso(int cuentaId, int importe) {
		cuenta[cuentaId].setIngreso(importe);
		
	}
	public int getSaldo(int cuentaId) {
		return cuenta[cuentaId].getSaldo();
	}
	public int getReintegro(int cuentaId, int importe) {
		return cuenta[cuentaId].getReintegro(importe);
	}

}
