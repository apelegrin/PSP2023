package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashSimpleRead {

	public static void main(String[] args) {
		MessageDigest md;
		try {
			FileInputStream fileIn = new FileInputStream("datos.txt");
			ObjectInputStream dataIn = new ObjectInputStream(fileIn);
			String rMensa = (String) dataIn.readObject();
			byte rResumen[] = (byte[]) dataIn.readObject();
			md = MessageDigest.getInstance("SHA-1");
			String texto = rMensa;
			byte dataBytes[] = texto.getBytes();
			md.update(dataBytes);
			byte resumen[] = md.digest();
			System.out.println("Mensaje original "+texto);
			System.out.println("Algoritmo :"+md.getAlgorithm());
			System.out.println("Resumen:"+new String(resumen));
			System.out.println("Hexadecimal Calc:"+Hexadecimal(resumen));
			System.out.println("Hexadecimal Read:"+Hexadecimal(rResumen));
			FileOutputStream fileOut = new FileOutputStream("datos.txt");
			ObjectOutputStream dataOut = new ObjectOutputStream(fileOut);
			
		} catch (NoSuchAlgorithmException | IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String Hexadecimal(byte[] resumen) {
		String hex = "";
		for (int i=0;i< resumen.length;i++) {
			String h = Integer.toHexString(resumen[i] & 0xFF);
			if (h.length() == 1) {
				hex += "0";
			}
			hex += h;
		}
		return hex.toLowerCase();
	}

}
