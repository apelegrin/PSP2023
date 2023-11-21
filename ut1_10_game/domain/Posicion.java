package domain;

public class Posicion {
	private int x;
	private int y;
	
	public Posicion(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Posicion(Posicion actual) {
		this.x = actual.getX();
		this.y = actual.getY();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void cambiarPosi(Direccion posi) {
		switch (posi) {
		case ARRIBA:
			this.y++;
			break;
		case ABAJO:
			this.y--;
			break;
		case IZQUIERDA:
			this.x--;
			break;
		case DERECHA:
			this.x++;
			break;
		default:
			break;
		}
		
	}

	public void incY() {
		this.y++;
	}

	public void decY() {
		this.y--;
		
	}

	public void incX() {
		this.x++;
		
	}

	public void decX() {
		this.x--;
		
	}



}
