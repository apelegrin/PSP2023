import java.io.File;
import java.io.IOException;

public class LanzadorFicheros {

	public void lanzarSumador(Integer n1, Integer n2, String fileName) {
		String[] comando = {"java","Sumador",n1.toString(),n2.toString()};
		ProcessBuilder pb = new ProcessBuilder(comando);
		File fout = new File(fileName);
		File ferr = new File("err_"+fileName);
		pb.redirectOutput(fout);
		pb.redirectError(ferr);
		try {
			pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
