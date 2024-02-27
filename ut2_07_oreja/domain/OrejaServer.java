package domain;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;

public class OrejaServer {
	private int maxClientes;
	private final int serverPort;
	private int contadorClientes;
	private ServerSocket server;
	//Lock que controla que solo se lanza una instancia de OrejaUDPReceiver
	private Semaphore lock = new Semaphore(1);

	public OrejaServer(int maxClientes) {
		this.maxClientes = maxClientes;
		this.contadorClientes = 0;
		this.serverPort = 9933;
	}

	public void iniciarServidor() {

		boolean fin = false;

		try {
			server = new ServerSocket(serverPort);
			System.out.println("Oreja, esperando conexiones de clientes en "+serverPort);
			while (!fin){
				if (contadorClientes < maxClientes){
					Socket s = server.accept();
					contadorClientes++;
					System.out.println("Cliente  "+contadorClientes+" conectado");
					OrejaService service = new OrejaService(s,contadorClientes,lock);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
