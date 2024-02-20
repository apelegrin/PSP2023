package main;

import java.security.Provider;
import java.security.Security;
import java.util.Enumeration;

public class SecurityProviders {

	public static void main(String[] args) {
		System.out.println("Proveedores de seguridad instalados");
		Provider[] lista = Security.getProviders();
		for (int i=0; i < lista.length; i++) {
			System.out.println("Número:"+(i+1));
			System.out.println("Nombre:"+lista[i].getName());
			System.out.println("Version"+lista[i].getVersionStr());
			System.out.println("Información"+lista[i].getInfo());
			System.out.println("Propiedades");
			Enumeration propiedades = lista[i].propertyNames();
			while (propiedades.hasMoreElements()) {
				String clave = (String) propiedades.nextElement();
				String valor = lista[i].getProperty(clave);
				System.out.println("Clave:"+clave+"  "+valor);
			}
			System.out.println("-------------------------------");
		}
	}

}
