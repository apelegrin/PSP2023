import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class EjecutaEditProcessBuilder {

	public static void main(String[] args) {

		String[] comando = {"ls","-l"};
		ProcessBuilder pb = new ProcessBuilder(comando);
		try {
			Process p = pb.start();
			
			InputStream is = p.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader buffer  = new BufferedReader(isr);
			String linea;
			linea = buffer.readLine();
			while ( linea != null){
				System.out.println(linea);
				linea = buffer.readLine();
			}
			buffer.close();
			int estado = p.waitFor();
			System.out.println("Proceso fin con "+estado);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Terminado principal ProcessBuilder");
	}

}
