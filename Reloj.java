import java.util.Random;

public class Reloj extends Thread {
    String mensaje;
    boolean fin = false;
    
	public Reloj(String mensaje) {
		this.mensaje = mensaje;
	}

	public void run() {
		Random aleatorio = new Random();
		while (!fin) {
			System.out.println(mensaje);
			int espera = aleatorio.nextInt(1000);
			try {
				Thread.sleep(espera);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}//end_while
		
		int espera = aleatorio.nextInt(1000)+500;
		try {
			Thread.sleep(espera);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Parado "+mensaje);
	}//end_run
	
	public void parar() {
		fin = true;
	}
}
