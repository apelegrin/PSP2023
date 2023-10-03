
public class HiloSincro02 extends Thread {
    
    public static int contador = 0;
    private boolean fin = false;
    private int idHilo;
    private Object candado;
    
	public HiloSincro02(int idHilo, Object candado) {
		this.idHilo = idHilo;
		this.candado = candado;
		this.start();
	}
	
	public void run() {
		while (!fin) {
			cambiarContador();
		}//end_while
	}//end_function

	private void cambiarContador() {
		synchronized(this.candado){
			if (this.idHilo == contador) {
				System.out.println("Hola soy "+this.idHilo);
				contador++;
			}
			else if (contador == 100) {
				contador = 0;
			}
		}//end_syncro
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}//end_function
}
