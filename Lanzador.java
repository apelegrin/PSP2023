
public class Lanzador {

	public static void main(String[] args) {
		LanzadorFicheros l = new LanzadorFicheros();
		l.lanzarSumador(01,2000000000,"r1.txt");
		l.lanzarSumador(2000000000,2100000000,"r2.txt");
		System.out.println("Sumadores lanzados");
	}

}
