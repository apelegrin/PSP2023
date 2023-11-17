package domain;

import java.awt.Color;
import java.util.LinkedList;

import topos.vista1.Alarma;
import topos.vista1.Pantalla;
import topos.vista1.Alarma;
import topos.vista1.Pantalla;
import java.awt.Color;

public class Partida {
	private static final int ANCHO = 11;
	private static final int ALTO = 11;
	private static final int LADO = 50;
	private static final int REDIBUJAR = 150;
	private int disparos;
	private int tiempoJuego;
	
	public Partida(int disparos, int tiempoJuego) {
		this.disparos = disparos;
		this.tiempoJuego = tiempoJuego;
	}

	public void start() {
		int quedanDisparos = disparos;
				
		//rutas sprites
		String rutaFondo = "imagenes/panel-basico.gif";
		String rutaGlobo = "imagenes/globo.png";
		String rutaDiana = "imagenes/diana.png";
		
		//Objeto de sincronización
		Sincroniza sincro = new Sincroniza();
				
		// Creamos la pantalla
		Pantalla pantalla = new Pantalla(ANCHO, ALTO, LADO, Color.BLUE);
		Objetivo objetivo = new Objetivo(pantalla,ANCHO,ALTO,sincro);
		
		//Definir sprites
		Sprite globo01 = new Sprite(ANCHO,ALTO,rutaGlobo,sincro);
		Sprite globo02 = new Sprite(ANCHO,ALTO,rutaGlobo, sincro);
		Sprite diana01 = new Sprite(ANCHO,ALTO,rutaDiana, sincro);
		Sprite diana02 = new Sprite(ANCHO,ALTO,rutaDiana, sincro);
		boolean fin = false;
		LinkedList<Sprite> listaSprites = new LinkedList<Sprite>(); 
		
		listaSprites.add(globo01);
		listaSprites.add(globo02);
		listaSprites.add(diana01);
		listaSprites.add(diana02);
		
		do {
			pantalla.resetear();
			cargarfondo(pantalla, rutaFondo);
			cargarSprites(pantalla,listaSprites);
			objetivo.procesar();
			pantalla.dibujar();
			Alarma.dormir(REDIBUJAR);
		} while (!fin);
	}
	
	private void cargarSprites(Pantalla pantalla, LinkedList<Sprite> listaSprites) {
		for (Sprite s : listaSprites) {
			pantalla.addImagen(s.getPosiX(), s.getPosiY(), s.getRutaSprite());
		}
		
	}

	private void cargarfondo(Pantalla pantalla, String rutaFondo) {
		for (int i = 0; i < ANCHO; i++) {
			for (int j = 0; j < ALTO; j++) {
				pantalla.addImagen(i, j, rutaFondo);
			}
		}
		
	}
}

