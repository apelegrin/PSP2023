package domain;

public class Bufferv1 {
	private char contenido;
	private boolean disponible;
	
	public Bufferv1() {
		this.contenido = '#';
		this.disponible = false;
	}
	
	public char recoger(){
		if(disponible){
			disponible=false;
			return contenido;
		}
		return ('#');
	}
	public void poner(char c){
		if(!disponible) {
			contenido=c;
			disponible=true;
		}
	}
}
