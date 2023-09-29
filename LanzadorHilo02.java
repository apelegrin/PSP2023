
public class LanzadorHilo02 {

	public static void main(String[] args) {
		HiloSimple2 hs1 = new HiloSimple2();
		HiloSimple2 hs2 = new HiloSimple2();
		hs1.start();
		hs2.start();
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
