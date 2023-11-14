package domain;

import java.awt.event.KeyEvent;

import topos.vista1.Pantalla;

public class Mira extends Thread{
	private static final String TECLA_ARRIBA = "w";
	private static final String TECLA_ABAJO = "s";
	private static final String TECLA_IZQUIERDA = "a";
	private static final String TECLA_DERECHA = "d";
	private static final String TECLA_DISPARO = " ";
	
	private static final String RUTA_IMAGEN_OBJETIVO ="imagenes/objetivo.png";
	private static final String RUTA_IMAGEN_DISPARO ="imagenes/explosion.png";
	
	private String rutaImagen;
	private Posicion actual;
	private boolean isEnJuego;
    private boolean isDisparado;
	private String tecla;
    private Pantalla pantalla;
    private int ancho;
    private int alto;
    
	public Posicion getPosicion() {
		return actual;
	}

	public String getRutaImagen() {
		if (isDisparado) {
			rutaImagen = RUTA_IMAGEN_DISPARO;
			isDisparado = false;
		}
		else {
			rutaImagen = RUTA_IMAGEN_OBJETIVO;
		}
		return rutaImagen;
	}

	public Mira(Pantalla pantalla, Posicion p, int ancho, int alto) {
		this.pantalla = pantalla;
		this.actual = p;
		this.rutaImagen = RUTA_IMAGEN_OBJETIVO;
		this.isEnJuego = true;
		this.isDisparado = false;
		this.ancho = ancho;
		this.alto = alto;
		this.start();
	}

	public void run() {
		 
		while (isEnJuego) {
			System.out.println(""); //apaño para teclado
			if (pantalla.hayTecla()) {
				tecla = pantalla.leerTecla();
				switch (tecla) {
				case TECLA_ARRIBA:
					actual.cambiarPosi(Direccion.ARRIBA);
					if (actual.getY() == alto) {
						actual.setY(0);
					}
					break;
				case TECLA_ABAJO:
					actual.cambiarPosi(Direccion.ABAJO);
					if (actual.getY() < 0) {
						actual.setY(alto - 1);
					}
					break;
				case TECLA_DERECHA:
					actual.cambiarPosi(Direccion.DERECHA);
					if (actual.getX() == ancho) {
						actual.setX(0);
					}
					break;
				case TECLA_IZQUIERDA:
					actual.cambiarPosi(Direccion.IZQUIERDA);
					if (actual.getX() < 0) {
						actual.setX(ancho - 1);
					}
					break;
				case TECLA_DISPARO:
					this.isDisparado = true;
					break;
				}
			}
		}
	}

}
