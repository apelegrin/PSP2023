package domain;

import topos.vista1.*;
import java.awt.Color;

public class Game {
    private int ancho;
    private int alto;
    private int lado;
    private int redibujar;
    private boolean finJuego;
    private Pantalla pantalla;
    private Mira mira;
    private Posicion posicionObjetivo;
    
	public Game(int ancho, int alto, int lado, int redibujar) {
		this.ancho = ancho;
		this.alto = alto;
		this.lado = lado;
		this.redibujar = redibujar ;
		this.finJuego = false;
	}

	public void iniciar() {
		
		//Rutas sprites
		String rutaImagenFondo ="imagenes/panel-basico.gif";
		
        
		//Creamos la pantalla
		pantalla = new Pantalla(ancho,alto,lado,Color.BLUE);
		posicionObjetivo = new Posicion(5,5);
        mira = new Mira(pantalla,posicionObjetivo,ancho,alto);
        
        String tecla;
        
		while (!finJuego) {
			pantalla.resetear();
			//Recorrer todos los objetos de pantalla y agregar su imagen
			cargarFondo(rutaImagenFondo);
			//Cargar objetivo
			pantalla.addImagen(mira.getPosicion().getX(),mira.getPosicion().getY(),mira.getRutaImagen());
			pantalla.dibujar();
			Alarma.dormir(redibujar);
		}
	}
	
	private void cargarFondo(String rutaImagenFondo) {
		for (int i = 0; i < this.ancho; i++) {
			for (int j = 0; j < this.alto; j++) {
			    pantalla.addImagen(i, j, rutaImagenFondo);
			}
		}
		
	}

}
