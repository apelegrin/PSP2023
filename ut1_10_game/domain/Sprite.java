package domain;

public class Sprite extends Thread{

	private Posicion p;
	private int ancho;
	private int alto;
	private String rutaImagenGlobo;
	private Sincro sincro;
	
	public Sprite(int ancho, int alto, String rutaImagenGlobo, Sincro sincro) {
		this.ancho = ancho;
		this.alto = alto;
		this.rutaImagenGlobo = rutaImagenGlobo;
		this.sincro = sincro;
		this.start();
	}

	public Posicion getPosicion() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRutaSprite() {
		// TODO Auto-generated method stub
		return null;
	}

	public void run() {
		this.sincro.spriteAwait();
	}
}
