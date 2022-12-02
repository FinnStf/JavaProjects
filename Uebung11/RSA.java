package Uebung11;
import java.math.BigInteger;


public class RSA {

    /**
     * Bitte geeignete Attribute für die Klasse definieren.
     * Jede Instanz von RSA soll sein eigenes Schluesselpaar besitzen.
     */
    
    
    /**
     * Builds a new key-pair for RSA using the given two prime numbers.
     */
    private RSA(BigInteger prime1, BigInteger prime2) {
        //TODO Bitte implementieren
    }
    
    
    /**
     * Builds a new key-pair for RSA the length of which is given by bitLength,
     * i.e. bitLength determines the length of the module of the system.
     * The key is chosen randomly using a pseudo-random number generator.
     */
    public RSA(int bitLength) {
        //TODO Bitte implementieren
    }
    
    
    /**
     * Encodes the given message using the public key and returns the 
     * corresponding cipher text.
     */
    public BigInteger encode(BigInteger message) {
        //TODO Bitte implementieren
    }
    

    /**
     * Decodes the given cipher text using the private key and returns the 
     * corresponding message.
     */
    public BigInteger decode(BigInteger chiffre) {
        //TODO Bitte implementieren
    }
    
    /**
     * Returns a human-readable version of public and private key.
     */
    public String toString() {
    }
    
    
    public static void main(String[] args) {
        //TODO Bitte geeignete Tests implementieren
    }
    
}
