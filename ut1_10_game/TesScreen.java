package main;

import topos.vista1.*;
import java.awt.Color;

public class TesScreen {
	static final int ANCHO = 11;
	static final int ALTO = 11;
	static final int LADO = 50;
	static final int REDIBUJAR = 100;
	
	public static void main(String[] args) {

		String rutaImagenFondo ="imagenes/panel-basico.gif";
		String rutaImagenObjetivo ="imagenes/objetivo.png";
        Pantalla pantalla = new Pantalla(ANCHO,ALTO,LADO,Color.BLUE);
        int objX = 5;
        int objY = 5;
        String tecla;

        boolean fin = false;
        do {
        	pantalla.resetear();
        	cargarFondo(pantalla,rutaImagenFondo);
        	if (pantalla.hayTecla()) {
        		tecla = pantalla.leerTecla();
        		System.out.println(tecla);
        		if (tecla.contains("w")) {
        			objY++;
        		}
        		if (tecla.contains("s")) {
        			objY--;
        		}
        		if (tecla.contains("a")) {
        			objX--;
        		}
        		if (tecla.contains("d")) {
        			objX++;
        		}
        	}
        	pantalla.addImagen(objX, objY, rutaImagenObjetivo);
        	pantalla.dibujar();
        	Alarma.dormir(REDIBUJAR);
        }while(!fin);
        
	}

	private static void moverObjetivo(Pantalla pantalla, int objX, int objY) {
		String tecla;
		
	}

	private static void cargarFondo(Pantalla pantalla, String rutaImagenFondo) {
		for (int i = 0; i < ANCHO; i++) {
			for (int j = 0; j < ALTO; j++) {
			    pantalla.addImagen(i, j, rutaImagenFondo);
			}
		}
		
	}

}
