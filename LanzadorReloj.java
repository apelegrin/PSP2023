
public class LanzadorReloj {

	public static void main(String[] args) {
		Reloj hs1 = new Reloj("TIC");
		Reloj hs2 = new Reloj("TAC");
		hs1.start();
		hs2.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		hs1.parar();
		hs2.parar();
		//while (hs1.isAlive() || hs2.isAlive()) {
			//System.out.println("thinking !!!!");
		//}
		try {
			hs1.join();
			hs2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Reloj terminado");
	}
}
