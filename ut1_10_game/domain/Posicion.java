package domain;

public class Posicion {
	private int x;
	private int y;
	
	public Posicion(int objX, int objY) {
		this.x = objX;
		this.y = objY;
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


	public void incY() {
		this.y++;
		
	}

	public void incX() {
		this.x++;
		
	}
	
	public void decY() {
		this.y--;
		
	}

	public void decX() {
		this.x--;
		
	}
}
