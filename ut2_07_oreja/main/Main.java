package main;

import domain.OrejaServer;

public class Main {

	public static void main(String[] args) {
		
		OrejaServer server = new OrejaServer(5);
		server.iniciarServidor();
		
	}

}
