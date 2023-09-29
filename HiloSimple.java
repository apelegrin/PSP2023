
public class HiloSimple implements Runnable {
    boolean fin = false;

    public void run() {
		while (!fin) {
			Thread t = Thread.currentThread();
			String nombre = t.getName();
			System.out.println("Hola soy HiloSimple "+nombre);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Hilo Simple Terminado");
	}
	public void terminar() {
		fin = true;
		
	}

}
