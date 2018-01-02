// 
// Decompiled by Procyon v0.5.30
// 

package seguriSign_Client;

import java.io.FilenameFilter;
import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.security.cert.CertificateException;
import java.security.UnrecoverableKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.KeyStoreException;
import java.util.Enumeration;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

public class Utilities
{
    public DynamicFilter getFilter(final String s, final String[] array) {
        return new DynamicFilter(s);
    }
    
    public static byte[] getBytesFromFile(final String s) throws IOException {
        return getBytesFromFile(new File(s));
    }
    
    public static byte[] getBytesFromFile(final File file) throws IOException {
        final byte[] array = new byte[(int)file.length()];
        final FileInputStream fileInputStream = new FileInputStream(file);
        int read;
        for (int n = 0; n < array.length && (read = fileInputStream.read(array, n, array.length - n)) >= 0; n += read) {}
        fileInputStream.close();
        return array;
    }
    
    private static String ByteArraytoHexString(final byte[] array) {
        final StringBuffer sb = new StringBuffer();
        for (int length = array.length, i = 0; i < length; ++i) {
            if (i % 16 == 0) {
                sb.append("\n");
            }
            byte2hex(array[i], sb);
            if (i < length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
    
    public static String PrintBytes(final byte[] array) {
        return ByteArraytoHexString(array);
    }
    
    private static void byte2hex(final byte b, final StringBuffer sb) {
        final char[] array = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        final int n = (b & 0xF0) >> 4;
        final byte b2 = (byte)(b & 0xF);
        sb.append(array[n]);
        sb.append(array[b2]);
    }
    
    public static PrivateKey getPrivateKeyFromPFX(final String s, final char[] array) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException, IOException, CertificateException {
        final FileInputStream fileInputStream = new FileInputStream(s);
        final KeyStore instance = KeyStore.getInstance("PKCS12");
        instance.load(fileInputStream, array);
        final Enumeration<String> aliases = instance.aliases();
        while (aliases.hasMoreElements()) {
            final String s2 = aliases.nextElement();
            if (instance.isKeyEntry(s2)) {
                return (PrivateKey)instance.getKey(s2, array);
            }
        }
        return null;
    }
    
    public static X509Certificate getCertificateFromPFX(final String s, final char[] array) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
        final FileInputStream fileInputStream = new FileInputStream(s);
        final KeyStore instance = KeyStore.getInstance("PKCS12");
        instance.load(fileInputStream, array);
        final Enumeration<String> aliases = instance.aliases();
        while (aliases.hasMoreElements()) {
            final String s2 = aliases.nextElement();
            if (instance.isKeyEntry(s2)) {
                return (X509Certificate)instance.getCertificate(s2);
            }
        }
        return null;
    }
    
    public PrivateKey getPrivateKeyFromKeyFile(final String s, final String s2) throws IOException, Exception {
        return new pkcs5v2().decryptKey(getBytesFromFile(s), s2);
    }
    
    public static void Print_Array(final String s, final byte[] array) {
        System.out.print(s + "\t La longitud del arreglo es: " + array.length + "\t\n ");
        for (int i = 0; i < array.length; ++i) {
            final int n = array[i] & 0xFF;
            if (n < 16) {
                System.out.print("0");
            }
            System.out.print(Integer.toHexString(n) + " ");
        }
        System.out.println(" \n\n");
    }
    
    public boolean verifica_consistencia(final X509Certificate x509Certificate, final PrivateKey privateKey) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        final byte[] bytes = "Texto de prueba".getBytes();
        final Signature instance = Signature.getInstance("SHA1withRSA");
        instance.initSign(privateKey);
        instance.update(bytes);
        final byte[] sign = instance.sign();
        final Signature instance2 = Signature.getInstance("SHA1withRSA");
        instance2.initVerify(x509Certificate);
        instance2.update(bytes);
        return instance2.verify(sign);
    }
    
    public class DynamicFilter implements FilenameFilter
    {
        String ext;
        
        public DynamicFilter(final String ext) {
            this.ext = ext;
        }
        
        public boolean accept(final File file, final String s) {
            System.out.println(s);
            return s.endsWith(this.ext);
        }
    }
    
    public class Utils
    {
        public String[] filestypevector;
        
        public Utils(final String[] filestypevector) {
            this.filestypevector = filestypevector;
        }
        
        public String getExtension(final File file) {
            String lowerCase = null;
            final String name = file.getName();
            final int lastIndex = name.lastIndexOf(46);
            if (lastIndex > 0 && lastIndex < name.length() - 1) {
                lowerCase = name.substring(lastIndex + 1).toLowerCase();
            }
            return lowerCase;
        }
        
        public String getExtension(final String s) {
            String lowerCase = null;
            final int lastIndex = s.lastIndexOf(46);
            if (lastIndex > 0 && lastIndex < s.length() - 1) {
                lowerCase = s.substring(lastIndex + 1).toLowerCase();
            }
            return lowerCase;
        }
    }
}
