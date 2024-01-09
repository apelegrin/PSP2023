package ut2_01_Inet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class miServer {

	public static void main(String[] args) {
		try {
			ServerSocket miServer = new ServerSocket(8888);
			System.out.println("Server running...");
			boolean fin = false;
			while (!fin) {
				Socket s = miServer.accept();
				System.out.println("Conexión desde "+s.getInetAddress().getHostAddress());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
