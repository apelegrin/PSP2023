import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

public class FirmaDigital {
		private KeyPair parClaves;
		private PrivateKey clavePriv;
		private PublicKey clavePub;
		
		public FirmaDigital() {
			 
			try {
				KeyPairGenerator keyGen;
				keyGen = KeyPairGenerator.getInstance("DSA");
				//SE INICIALIZA EL GENERADOR DE CLAVES
				SecureRandom numero = SecureRandom.getInstance("SHA1PRNG");
				keyGen.initialize (1024, numero);
				//SE CREA EL PAR DE CLAVES PRIVADA Y PÚBLICA
				parClaves = keyGen.generateKeyPair();
				clavePriv = parClaves.getPrivate();
				clavePub = parClaves.getPublic();

			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
		}

		public PrivateKey getClavePriv() {
			return clavePriv;
		}

		public PublicKey getClavePub() {
			return clavePub;
		}
}
