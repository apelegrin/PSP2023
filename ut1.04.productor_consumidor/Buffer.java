package domain;

public class Buffer {
	private char contenido;
	private boolean disponible;
	
	public Buffer() {
		this.contenido = '#';
		this.disponible = false;
	}
	
	public synchronized char recoger(){
		//mientras que no hay nada que recoger esperamos
		while(!disponible){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		disponible=false;
		notifyAll();
		return contenido;
	}
	public synchronized void poner(char c){
		//si no se ha consumido esperamos
		while(disponible){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		contenido=c;
		disponible=true;
		notifyAll();
	}
}
