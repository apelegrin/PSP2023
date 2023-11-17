package domain;

import java.util.Random;
import topos.vista1.*;

public class Sprite extends Thread{

	private Posicion p;
	private int ancho;
	private int alto;
	private String rutaImagenGlobo;
	private Sincro sincro;
	private boolean fin;
	private int espera;
	public Sprite(int ancho, int alto, String rutaImagenGlobo, Sincro sincro) {
		Random aleatorio = new Random();
		this.ancho = ancho;
		this.alto = alto;
		this.rutaImagenGlobo = rutaImagenGlobo;
		this.sincro = sincro;
		this.p = new Posicion(aleatorio.nextInt(ancho), aleatorio.nextInt(alto));
		this.fin = false;
		this.espera = 300;
		this.start();
	}

	public Posicion getPosicion() {
		Posicion actual = new Posicion(p);
		return actual;
	}

	public String getRutaSprite() {
		return rutaImagenGlobo;
	}

	public void run() {
		int avanza;
		//this.sincro.spriteAwait();
		System.out.println("Inicia Sprite");
		while (!fin) {
			avanza = (int) (Math.random() * 4);
			switch (avanza) {
			case 0:
				p.incY();
				if (p.getY() == alto) {
					p.setY(0);
				}
				break;
			case 1:
				p.decY();
				if (p.getY() < 0) {
					p.setY(alto - 1);
				}
				break;
			case 2:
				p.incX();
				if (p.getX() == ancho) {
					p.setX(0);
				}
				break;
			case 3:
				p.decX();
				if (p.getX() < 0) {
					p.setX(ancho - 1);
				}
				break;
			}
			Alarma.dormir(espera);
		}
	}
}
