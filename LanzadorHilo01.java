
public class LanzadorHilo01 {

	public static void main(String[] args) {
		HiloSimple hs1 = new HiloSimple();
		HiloSimple hs2 = new HiloSimple();
		Thread t1 = new Thread(hs1);
		Thread t2 = new Thread(hs2);
		t1.start();
		t2.start();
		System.out.println("Lanzador terminado");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Parando hilo");
		hs1.terminar();
		System.out.println("Hilo Parado");
	}

}
