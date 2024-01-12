package main;

public class Cuenta {

	private int saldo;
	public Cuenta(int saldo) {
		this.saldo = saldo;
	}
	public void setIngreso(int importe) {
		saldo += importe;
	}
	public int getSaldo() {
		// TODO Auto-generated method stub
		return saldo;
	}
	public int getReintegro(int importe) {
		int reintegro = -1;
		if (saldo >= importe) {
			saldo -= importe;
			reintegro = importe;
		}
		return reintegro;
	}


}
