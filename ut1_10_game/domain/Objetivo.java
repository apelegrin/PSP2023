package domain;

import topos.vista1.Pantalla;
/**
 * La clase objetivo controla el objetivo con las teclas
 * puede desplazarse por la pantalla
 * 
 * @author app
 *
 */
public class Objetivo {
	private static final String TECLA_ARRIBA = "i";
	private static final String TECLA_ABAJO = "k";
	private static final String TECLA_IZQUIERDA = "j";
	private static final String TECLA_DERECHA = "l";
	private static final String TECLA_DISPARO = " ";
	
	private String rutaObjetivo = "imagenes/objetivo.png";
	private int objX = 5;
	private int objY = 5;
	private int ancho, alto;
	private String tecla;
	private Pantalla pantalla;
	private Posicion posi; 
	private Sincroniza sincro;
	private boolean iniciaSprite = false;
	private boolean disparado;
	
	/**
	 * EL constructor inicializa el objetivo tomando una pantalla
	 * con sus características 
	 * @param pantalla pantalla sobre la que el objetivo se visualiza
	 * @param ancho de la pantalla
	 * @param alto de la pantalla
	 * @param sincro objeto con todos los métodos de sincronización necesarios
	 */
	public Objetivo(Pantalla pantalla,int ancho, int alto, Sincroniza sincro) {
		this.pantalla = pantalla;
		this.ancho = ancho;
		this.alto = alto;
		this.posi = new Posicion(objX,objY);
		this.sincro = sincro;
		this.disparado = false;
	}
	
	public void procesar() {
		String rutaObjetivo ="imagenes/objetivo.png";
		String rutaExplosion ="imagenes/explosion.png";
		String tecla;
		disparado = false;
		
		if (pantalla.hayTecla()) {
			tecla = pantalla.leerTecla();
			switch (tecla) {
			case TECLA_ARRIBA:
				iniciarSprite();
				posi.incY();
				if (posi.getY() == alto) {
					posi.setY(0);
				}
				break;
			case TECLA_ABAJO:
				iniciarSprite();
				posi.decY();
				if (posi.getY() < 0) {
					posi.setY(alto - 1);
				}
				break;
			case TECLA_DERECHA:
				iniciarSprite();
				posi.incX();
				if (posi.getX() == ancho) {
					posi.setX(0);
				}
				break;
			case TECLA_IZQUIERDA:
				iniciarSprite();
				posi.decX();
				if (posi.getX() < 0) {
					posi.setX(ancho - 1);
				}
				break;
			case TECLA_DISPARO:
				iniciarSprite();
				disparado = true;
				break;
			}
		}
		if (disparado) {
			pantalla.addImagen(posi.getX(), posi.getY(), rutaExplosion);	
		}
		else
			pantalla.addImagen(posi.getX(), posi.getY(), rutaObjetivo);		
	}

	private void iniciarSprite() {
		if (!iniciaSprite) {
			iniciaSprite = true;
			sincro.iniciarSprites();
		}
		
	}
}
