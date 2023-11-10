package main;

import domain.Game;

public class Main {
	static final int ANCHO = 11;
	static final int ALTO = 11;
	static final int LADO = 50;
	static final int REDIBUJAR = 100;
	
	public static void main(String[] args) {
       Game miJuego = new Game(ANCHO,ALTO,LADO,REDIBUJAR);
       miJuego.iniciar();
	}
}
