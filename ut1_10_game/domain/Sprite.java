package domain;

import topos.vista1.Alarma;

public class Sprite extends Thread {
	private final int ancho;
	private final int alto;
	private final int espera = 800;
	private int avanza;
	private Posicion posi;
	private String rutaSprite;
	private boolean fin = false;
	private Sincroniza sincro;
	
	public Sprite(int ancho, int alto, String rutaSprite, Sincroniza sincro) {
		this.ancho = ancho;
		this.alto = alto;
		int x = (int)(Math.random()*ancho);
		int y = (int)(Math.random()*alto);
		this.posi = new Posicion(x,y);
		this.rutaSprite = rutaSprite;
		this.sincro = sincro;
		this.start();
	}

	public void run() {
		sincro.esperaIniciarSprites();
		while (!fin) {
			avanza = (int) (Math.random() * 4);
			switch (avanza) {
			case 0:
				posi.incY();
				if (posi.getY() == alto) {
					posi.setY(0);
				}
				break;
			case 1:
				posi.decY();
				if (posi.getY() < 0) {
					posi.setY(alto - 1);
				}
				break;
			case 2:
				posi.incX();
				if (posi.getX() == ancho) {
					posi.setX(0);
				}
				break;
			case 3:
				posi.decX();
				if (posi.getX() < 0) {
					posi.setX(ancho - 1);
				}
				break;
			}
			Alarma.dormir(espera);
		}
	}
	
	public void finalizar() {
		this.fin = true;
	}
	
	public int getPosiX() {
		return posi.getX();
	}

	public int getPosiY() {
		return posi.getY();
	}
	
	public String getRutaSprite() {
		return rutaSprite;
	}
	
}
