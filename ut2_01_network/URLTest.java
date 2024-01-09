package ut2_01_Inet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLTest {

	public static void main(String[] args) {
		try {
			URL miURL = new URL("http://ansat.es:80/index.htm");
			InputStreamReader entrada = new InputStreamReader(miURL.openStream());
			BufferedReader flujoE = new BufferedReader(entrada);
			
			String linea;
			while ((linea = flujoE.readLine()) != null) {
				System.out.println(linea);
			}
			flujoE.close();
			
			URLConnection miURLCon =  miURL.openConnection();
			InputStreamReader entrada2 = new InputStreamReader(miURLCon.getInputStream());
			BufferedReader flujoE2 = new BufferedReader(entrada2);
			
			while ((linea = flujoE2.readLine()) != null) {
				System.out.println(linea);
			}
			flujoE2.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
