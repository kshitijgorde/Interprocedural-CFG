// 
// Decompiled by Procyon v0.5.30
// 

package seguriSign_Client;

import java.io.FilenameFilter;
import java.awt.FileDialog;
import java.awt.Frame;
import netscape.javascript.JSObject;
import java.security.PrivateKey;
import java.io.InputStream;
import java.security.cert.X509Certificate;
import java.security.NoSuchAlgorithmException;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
import java.security.cert.CertificateFactory;
import java.applet.Applet;

public class seguriSign_Client extends Applet
{
    private boolean empty;
    private int max_size;
    private boolean verbose;
    private CertificateFactory cf;
    
    public seguriSign_Client() {
        this.verbose = false;
        this.cf = null;
        this.max_size = 7340032;
        this.verbose = false;
        try {
            this.cf = CertificateFactory.getInstance("X.509");
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void setMax_size(final int max_size) {
        if (this.verbose) {
            System.out.println("\nmax_size = " + max_size);
        }
        this.max_size = max_size;
    }
    
    public boolean getIsEmptyMessage() {
        return this.empty;
    }
    
    public void setVerboseON() {
        this.verbose = true;
    }
    
    public void setVerboseOFF() {
        this.verbose = false;
    }
    
    private static int byte2Int(final byte b) {
        if (b >= 0) {
            return b;
        }
        return (b & 0x7F) + 128;
    }
    
    private static byte[] getBytesFromFile(final File file) throws IOException {
        final byte[] array = new byte[(int)file.length()];
        final FileInputStream fileInputStream = new FileInputStream(file);
        int read;
        for (int n = 0; n < array.length && (read = fileInputStream.read(array, n, array.length - n)) >= 0; n += read) {}
        fileInputStream.close();
        return array;
    }
    
    private byte[] hashThisArray(final String s, final byte[] array) throws IOException, NoSuchAlgorithmException {
        final MessageDigest instance = MessageDigest.getInstance(s);
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
        final byte[] array2 = new byte[8192];
        int read;
        while ((read = byteArrayInputStream.read(array2)) != -1) {
            instance.update(array2, 0, read);
        }
        final byte[] digest = instance.digest();
        if (this.verbose) {
            Print_Array(instance.getAlgorithm(), digest);
        }
        return digest;
    }
    
    private byte[] getHashFromFile(final String s, final byte[] array) throws IOException, NoSuchAlgorithmException {
        final MessageDigest instance = MessageDigest.getInstance(s);
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
        final byte[] array2 = new byte[8192];
        int read;
        while ((read = byteArrayInputStream.read(array2)) != -1) {
            instance.update(array2, 0, read);
        }
        final byte[] digest = instance.digest();
        if (this.verbose) {
            Print_Array(instance.getAlgorithm(), digest);
        }
        return digest;
    }
    
    private static byte[] getHashFromFile(final String s, final File file) throws IOException, NoSuchAlgorithmException {
        final MessageDigest instance = MessageDigest.getInstance(s);
        final FileInputStream fileInputStream = new FileInputStream(file);
        final byte[] array = new byte[1024];
        int read;
        while ((read = fileInputStream.read(array)) != -1) {
            instance.update(array, 0, read);
        }
        return instance.digest();
    }
    
    public String encodeB64(final String s) {
        final String s2 = new String();
        String s3;
        try {
            s3 = new Base64().encode(Utilities.getBytesFromFile(new File(s)), 0);
        }
        catch (IOException ex) {
            s3 = "Error: " + ex.toString();
        }
        catch (Exception ex2) {
            s3 = "Error: " + ex2.toString();
        }
        return s3;
    }
    
    private static void Print_Array(final String s, final byte[] array) {
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
    
    private static int compare(final byte[] array, final byte[] array2) {
        if (array.length != array2.length) {
            return -1;
        }
        for (int i = 0; i < array.length; ++i) {
            if (byte2Int(array[i]) != byte2Int(array2[i])) {
                return -1;
            }
        }
        return 0;
    }
    
    public String firma(final String s, final String s2, final String s3, final String s4, final String s5) {
        if (this.verbose) {
            System.out.println("Par\u00e1metros recibidos: \n");
            System.out.println("Par\u00e1metro 1: " + s);
            System.out.println("Par\u00e1metro 2: " + s2);
            System.out.println("Par\u00e1metro 3: " + s3);
            System.out.println("Par\u00e1metro 4: " + s4);
            System.out.println("Par\u00e1metro 5: " + s5);
        }
        final long currentTimeMillis = System.currentTimeMillis();
        final String s6 = new String();
        final byte[] array = new byte[0];
        String s9;
        try {
            this.empty = false;
            byte[] array2;
            if (s5.equalsIgnoreCase("1")) {
                final File file = new File(s3);
                if (file.length() > this.max_size) {
                    if (s4.equalsIgnoreCase("MD2withRSA")) {
                        return new String("Error: No se puede firmar un archivo de gran tama\u00f1o con este algoritmo");
                    }
                    if (this.verbose) {
                        System.out.println("PKCS7 Signed Data vacio");
                    }
                    this.empty = true;
                    final String s7 = new String();
                    String s8;
                    if (s4.equalsIgnoreCase("MD5withRSA")) {
                        s8 = "md5";
                    }
                    else {
                        s8 = "sha1";
                    }
                    final Base64 base64 = new Base64();
                    array2 = getHashFromFile(s8, file);
                }
                else {
                    array2 = getBytesFromFile(file);
                }
            }
            else {
                array2 = s3.getBytes();
                if (array2.length > this.max_size) {
                    this.empty = true;
                }
            }
            final byte[] build_pkcs7 = pkcs7.build_pkcs7(s4, array2, s, s2, this.empty);
            if (this.verbose) {
                System.out.println("El arreglo de bytes del pkcs7 tiene una longitud de " + build_pkcs7.length + " bytes.");
            }
            final Base64 base65 = new Base64();
            if (this.verbose) {
                System.out.println("\nInicia proceso de formateo de respuesta final");
            }
            s9 = s6 + base65.encode(build_pkcs7, 0);
            if (this.verbose) {
                System.out.println("\nConcluye proceso de formateo de respuesta final");
            }
        }
        catch (Exception ex) {
            s9 = "Error: " + ex.toString();
        }
        final long n = (System.currentTimeMillis() - currentTimeMillis) / 1000L;
        final long n2 = n / 60L;
        final long n3 = n % 60L;
        if (this.verbose) {
            System.out.println("Total Ellapsed time: " + n2 + " : " + n3 + "\n\n");
        }
        return s9;
    }
    
    public String sign_and_envelope(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        final String s8 = new String();
        final long currentTimeMillis = System.currentTimeMillis();
        String s10;
        try {
            if (this.verbose) {
                System.out.println("Par\u00e1metros recibidos: \n");
                System.out.println("Par\u00e1metro 1: " + s);
                System.out.println("Par\u00e1metro 2: " + s2);
                System.out.println("Par\u00e1metro 3: " + s3);
                System.out.println("Par\u00e1metro 4: " + s4);
                System.out.println("Par\u00e1metro 5: " + s5);
                System.out.println("Par\u00e1metro 6: " + s6);
                System.out.println("Par\u00e1metro 7: " + s7);
            }
            byte[] array;
            if (s7.equalsIgnoreCase("0")) {
                String s9 = s6;
                if (s9.indexOf("-----BEGIN CERTIFICATE-----") == -1) {
                    s9 = "-----BEGIN CERTIFICATE-----\r\n" + s9;
                }
                if (s9.indexOf("-----END CERTIFICATE-----") == -1) {
                    s9 += "\r\n-----END CERTIFICATE-----";
                }
                if (this.verbose) {
                    System.out.println(s9);
                }
                array = s9.getBytes();
            }
            else {
                array = Utilities.getBytesFromFile(new File(s6));
            }
            if (this.verbose) {
                System.out.println("Antes de construir el certificado del destinatario ...");
            }
            final X509Certificate x509Certificate = (X509Certificate)this.cf.generateCertificate(new ByteArrayInputStream(array));
            final boolean b = false;
            if (this.verbose) {
                System.out.println(x509Certificate.toString());
            }
            byte[] array2;
            if (s5.equalsIgnoreCase("1")) {
                if (this.verbose) {
                    System.out.println("Se va a firmar y ensobretar un archivo");
                }
                array2 = pkcs7.build_pkcs7(s4, Utilities.getBytesFromFile(s3), s, s2, b, x509Certificate);
            }
            else {
                if (this.verbose) {
                    System.out.println("Se va a firmar y ensobretar un texto");
                }
                array2 = pkcs7.build_pkcs7(s4, s3.getBytes(), s, s2, b, x509Certificate);
            }
            s10 = new Base64().encode(array2, 0);
        }
        catch (Exception ex) {
            s10 = "Error: " + ex.toString();
        }
        final long n = (System.currentTimeMillis() - currentTimeMillis) / 1000L;
        final long n2 = n / 60L;
        final long n3 = n % 60L;
        if (this.verbose) {
            System.out.println("Total Ellapsed time: " + n2 + " : " + n3 + "\n\n");
        }
        return s10;
    }
    
    public String firma(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        final Utilities utilities = new Utilities();
        final byte[] array = new byte[0];
        this.empty = false;
        String encode;
        try {
            PrivateKey privateKeyFromKeyFile;
            try {
                privateKeyFromKeyFile = utilities.getPrivateKeyFromKeyFile(s, s3);
            }
            catch (IOException ex) {
                final String string = "Error: " + ex.toString();
                if (this.verbose) {
                    ex.printStackTrace();
                }
                return string;
            }
            catch (Exception ex2) {
                final String s7 = "Error: La llave privada est\u00e1 corrupta o la contrase\u00f1a no es v\u00e1lida.";
                if (this.verbose) {
                    ex2.printStackTrace();
                }
                return s7;
            }
            if (privateKeyFromKeyFile == null) {
                return "Error: No fue posible desencriptar la llave privada.";
            }
            X509Certificate x509Certificate;
            try {
                final FileInputStream fileInputStream = new FileInputStream(s2);
                x509Certificate = (X509Certificate)this.cf.generateCertificate(fileInputStream);
                fileInputStream.close();
                if (!utilities.verifica_consistencia(x509Certificate, privateKeyFromKeyFile)) {
                    return "Error: El par de llaves proporcionado no es consistente.";
                }
            }
            catch (Exception ex3) {
                final String string2 = "Error: " + ex3.toString();
                if (this.verbose) {
                    ex3.printStackTrace();
                }
                return string2;
            }
            final byte[] array2 = new byte[0];
            if (s5.equalsIgnoreCase("MD2withRSA")) {
                return new String("Error: No se puede firmar un archivo de gran tama\u00f1o con este algoritmo");
            }
            String s8;
            if (s5.equalsIgnoreCase("MD5withRSA")) {
                s8 = "md5";
            }
            else {
                s8 = "sha1";
            }
            byte[] array3;
            if (s6.equalsIgnoreCase("1")) {
                final File file = new File(s4);
                if (file.length() > this.max_size) {
                    if (this.verbose) {
                        System.out.println("PKCS7 Signed Data vacio");
                    }
                    this.empty = true;
                    final Base64 base64 = new Base64();
                    array3 = getHashFromFile(s8, file);
                }
                else {
                    array3 = getBytesFromFile(file);
                }
            }
            else {
                array3 = s4.getBytes();
                if (array3.length > this.max_size) {
                    array3 = this.hashThisArray(s8, array3);
                    this.empty = true;
                }
            }
            encode = new Base64().encode(pkcs7.build_pkcs7(s5, array3, x509Certificate, privateKeyFromKeyFile, this.empty), 0);
        }
        catch (Exception ex4) {
            final String string3 = "Error: " + ex4.toString();
            if (this.verbose) {
                ex4.printStackTrace();
            }
            return string3;
        }
        return encode;
    }
    
    private void callJSFunc(final String s, final String[] array) {
        final JSObject window = JSObject.getWindow((Applet)this);
        try {
            window.call(s, (Object[])array);
        }
        catch (Exception ex) {
            final String message = ex.getMessage();
            if (this.verbose) {
                System.out.println(message);
            }
        }
    }
    
    public void getFilePath(final String s, final String s2, final String s3, final String s4, final String s5) throws Exception {
        final Utilities utilities = new Utilities();
        final Frame frame = new Frame();
        if (this.verbose) {
            System.out.println("filter=" + s2);
            System.out.println("extension=" + s3);
        }
        final FileDialog fileDialog = new FileDialog(frame, s, 0);
        fileDialog.setFilenameFilter(utilities.getFilter(s3, new String[] { s3 }));
        fileDialog.setVisible(true);
        final String file = fileDialog.getFile();
        if (file == null || file.length() == 0) {
            if (s5 != null && s5.length() > 0) {
                JSObject.getWindow((Applet)this).eval("alert(\"" + new String(s5) + "\")");
            }
        }
        else {
            final String string = new String().concat(fileDialog.getDirectory()).concat(fileDialog.getFile()).toString();
            if (this.verbose) {
                System.out.println(string);
            }
            this.callJSFunc(s4, new String[] { string });
        }
    }
}
