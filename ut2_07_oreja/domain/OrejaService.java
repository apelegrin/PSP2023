package domain;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class OrejaService extends Thread{


	private Socket sTCP;
	private Scanner flujoE;
	private PrintWriter flujoS;
	private int cliente;
	private boolean recibiendo;
	private OrejaUDPReceiver broad;
	private String netUDP;
	private int portUDP;
	private Semaphore lock;
	
	public OrejaService(Socket sTCP, int cliente, Semaphore lock){
		this.sTCP = sTCP;
		this.cliente = cliente;
		this.recibiendo = false;
		this.netUDP = "224.0.0.1";
		this.portUDP = 8080;
		this.lock = lock;
		this.broad = new OrejaUDPReceiver(sTCP,netUDP,portUDP);	
		this.start();
	}//contructor

	public void run(){
		try{
			try{
				flujoE  = new Scanner(sTCP.getInputStream());
				flujoS = new PrintWriter(sTCP.getOutputStream());
				doService();
			}
			finally{
				sTCP.close();
			}
		}
		catch (IOException exception){
			exception.printStackTrace();
		}
	} //run
	
	public void doService() {
		boolean fin = false;

		flujoS.println("Conectado con el servicio de Mensajería OREJA");
		flujoS.flush();
		
		while (!fin) {
			if (!flujoE.hasNext()) { return; }
			String command = flujoE.nextLine();
			if (command.contains("QUIT")) { 
				broad.finalizar();
				if (recibiendo) {
					lock.release();
				}
				return; 
			}
			executeCommand(command);
		}
	} // doService
	
	public void executeCommand(String command) {
		String mensa;
		if (command.contains("START")){
			if (!recibiendo) {
				if (lock.tryAcquire()) {
					mensa = "Cliente "+cliente+" suscrito a los mensajes";
					flujoS.println(mensa);
					recibiendo = true;
					broad.startSending();
				}else {
					mensa = "Oreja UDP ocupado intenda después";
					flujoS.println(mensa);
				}
			}
			else {
				flujoS.println("Ya está suscrito");
			}
		}
		else if (command.contains("STOP")){
			if (recibiendo) {
				mensa = "Cliente "+cliente+" NO suscrito a los mensajes";
				flujoS.println(mensa);
				recibiendo = false;
				broad.stopSending();
				lock.release();
			}
			else {
				flujoS.println("NO está suscrito");
			}
		}
		else {
			System.out.println("Comando erroneo");
			flujoS.flush();
			return;
		}
		flujoS.flush();
	}   

}
