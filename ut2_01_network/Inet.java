package ut2_01_Inet;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;

public class Inet {

	public static void main(String[] args) throws UnknownHostException, SocketException {
		InetAddress equipov4;
		InetAddress um;

		equipov4 = InetAddress.getLocalHost();
        um = InetAddress.getByName("www.um.es");
		
		System.out.println("Mi equipo es: "+equipov4);
		System.out.println("Mi dirección IP es: "+equipov4.getHostAddress());
		System.out.println("Mi nombre es: "+equipov4.getHostName());
		System.out.println("Mi nombre canónico es: "+equipov4.getCanonicalHostName());
	
		System.out.println("Mi equipo es: "+um);
		System.out.println("Mi dirección IP es: "+um.getHostAddress());
		System.out.println("Mi nombre es: "+um.getHostName());
		System.out.println("Mi nombre canónico es: "+um.getCanonicalHostName());
		
		Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
		for (NetworkInterface netint : Collections.list(nets)) {
			System.out.printf("Display name: %s\n", netint.getDisplayName());
			System.out.printf("Name: %s\n", netint.getName());
			Enumeration<InetAddress> inetAddress = netint.getInetAddresses();
			for (InetAddress ia : Collections.list(inetAddress)) {
				System.out.println("InetAddress "+ia);
			}
		}
		
	}

}
