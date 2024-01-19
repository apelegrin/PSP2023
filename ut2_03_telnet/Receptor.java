package ut2_03_telnet;

import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Receptor extends Thread{
	private Socket s;
	private JTextArea txtRecibido;
	private JScrollPane scrollPane;
	private Scanner flujoE;
	private boolean fin;
	public Receptor(Socket s, JTextArea txtRecibido, JScrollPane scrollPane) {
		this.s = s;
		this.txtRecibido = txtRecibido;
		this.scrollPane = scrollPane;
		this.fin = false;
		this.start();
	}
	
	public void run() {
		String recibido;
		int posi;
		//Enlazamos con el socket
		try {
			flujoE = new Scanner(s.getInputStream());
			while (!fin) {
				recibido = flujoE.nextLine();
				txtRecibido.append(recibido+"\n");
				posi = scrollPane.getVerticalScrollBar().getMaximum();
				scrollPane.getVerticalScrollBar().setValue(posi);
			}
		} catch (IOException |NoSuchElementException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}	
	
    public void finalizar() {
    	flujoE.reset();
    	fin = true;
    }

}
