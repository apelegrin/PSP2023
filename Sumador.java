
public class Sumador {

	//sumador (n1,n2)
	
	public static void main(String[] args) {
		int n1 = Integer.parseInt(args[0]);
		int n2 = Integer.parseInt(args[1]);
		long suma = sumar(n1,n2);
		System.out.println(suma);
		System.out.flush();
	}

	private static long sumar(int n1, int n2) {
		long suma = 0;
		if (n1 > n2) {
			int aux = n1;
			n1 = n2;
			n2 = aux;
		}
		for (int i=n1; i <= n2; i++) {
			suma=suma+i;
		}
		return suma;
	}

}
