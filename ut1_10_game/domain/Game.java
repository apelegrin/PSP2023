package domain;

import topos.vista1.*;
import java.awt.Color;
import java.util.LinkedList;

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
		String rutaImagenGlobo ="imagenes/globo.png";
        
		//Creamos la pantalla
		pantalla = new Pantalla(ancho,alto,lado,Color.BLUE);
		posicionObjetivo = new Posicion(5,5);
        mira = new Mira(pantalla,posicionObjetivo,ancho,alto);
        
        //Definir Objeto Sincronización
        int MaxSprites = 2;
        Sincro sincro = new Sincro(MaxSprites);
        
        //Definir Sprites
        Sprite globo01 = new Sprite(ancho,alto,rutaImagenGlobo,sincro);
        Sprite globo02 = new Sprite(ancho,alto,rutaImagenGlobo,sincro);
        
        LinkedList<Sprite> listaSprites = new LinkedList<Sprite>();
        listaSprites.add(globo01);
        listaSprites.add(globo02);
        
		while (!finJuego) {
			pantalla.resetear();
			//Recorrer todos los objetos de pantalla y agregar su imagen
			cargarFondo(rutaImagenFondo);
			cargarSprites(listaSprites);
			pantalla.dibujar();
			Alarma.dormir(redibujar);
		}
	}
	
	
	
	private void cargarSprites(LinkedList<Sprite> listaSprites) {
		for (Sprite s: listaSprites) {
			pantalla.addImagen(s.getPosicion().getX(),
					s.getPosicion().getY(), s.getRutaSprite());
		}
		//Cargar objetivo
		pantalla.addImagen(mira.getPosicion().getX(),mira.getPosicion().getY(),mira.getRutaImagen());
		
	}

	private void cargarFondo(String rutaImagenFondo) {
		for (int i = 0; i < this.ancho; i++) {
			for (int j = 0; j < this.alto; j++) {
			    pantalla.addImagen(i, j, rutaImagenFondo);
			}
		}
		
	}

}
