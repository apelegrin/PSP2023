package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import domain.MesaService;

public class CitasServer {

	public static void main(String[] args) {
		try {
			//Lista de Sockets remotos
			List<Socket> clientesRemotos = new LinkedList<>();
					
			int port = 8888;
			ServerSocket miServer = new ServerSocket(port);
			
			System.out.println("Servicio de aTención al Alumno Numérico (SATAN)... at "+port);
			boolean fin = false;
			int id = 0;
			while (!fin) {
				//Acceptamos la conexión
				Socket s = miServer.accept();
				clientesRemotos.add(s);
				String ipCliente = s.getRemoteSocketAddress().toString();
				System.out.println("Funcionario "+id+" conectado desde "+ipCliente);
				
				//Iniciamos el servicio que gestiona la conexión
			    MesaService mesaService = new MesaService(id,s);
			    id++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
