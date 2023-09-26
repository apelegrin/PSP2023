import java.io.IOException;

public class EjecutaEdit {

	public static void main(String[] args) {

		String comando = "gedit";
		Runtime runtimeActual = Runtime.getRuntime();
		Process p;
		try {
			p = runtimeActual.exec(comando);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Terminado principal");
	}

}
