package domain;

import java.util.Random;
import java.util.concurrent.Semaphore;

import topos.vista1.*;

public class Sprite extends Thread{

	private Posicion p;
	private int ancho;
	private int alto;
	private String rutaImagenGlobo;
	private Sincro sincro;
	private boolean fin;
	private int espera;
	private Semaphore lock;
	
	public Sprite(int ancho, int alto, String rutaImagenGlobo, Sincro sincro) {
		Random aleatorio = new Random();
		this.ancho = ancho;
		this.alto = alto;
		this.rutaImagenGlobo = rutaImagenGlobo;
		this.sincro = sincro;
		this.p = new Posicion(aleatorio.nextInt(ancho), aleatorio.nextInt(alto));
		this.fin = false;
		this.espera = 500;
		this.lock = new Semaphore(1);
		this.start();
	}

	public Posicion getPosicion() {
		try {
			this.lock.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Posicion actual = new Posicion(p);
		this.lock.release();
		return actual;
	}

	public String getRutaSprite() {
		return rutaImagenGlobo;
	}

	public void run() {
		int avanza;
		Random aleatorio = new Random();
		this.sincro.spriteAwait();
		while (!fin) {
			try {
				this.lock.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			avanza = aleatorio.nextInt(4);
			//System.out.println(Thread.currentThread().getName()+" "+avanza);
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
			//Apaño
			//if (p.getX() == ancho) {
			//	p.decX();
			//}
	
			this.lock.release();
			Alarma.dormir(espera);
		}
	}
}
