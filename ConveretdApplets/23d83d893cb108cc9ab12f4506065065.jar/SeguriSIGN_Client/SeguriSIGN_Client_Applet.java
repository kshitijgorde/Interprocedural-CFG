// 
// Decompiled by Procyon v0.5.30
// 

package SeguriSIGN_Client;

import java.security.UnrecoverableKeyException;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.io.FileInputStream;
import java.security.InvalidAlgorithmParameterException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.SignatureException;
import java.security.InvalidKeyException;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.io.File;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.applet.Applet;

public class SeguriSIGN_Client_Applet extends Applet
{
    private static final long serialVersionUID = 8455997407617291092L;
    private X509Certificate[] certs;
    private PrivateKey private_key;
    private String[] cert_name;
    private myCert[] cert;
    
    public String parseaCertificados(final String paramString) {
        String str = "";
        try {
            final File localFile = new File(paramString);
            final byte[] arrayOfByte1 = getBytesFromFile(localFile);
            int j = 0;
            int k = 0;
            final int[] arrayOfInt1 = new int[250];
            for (int i = 0; i < arrayOfByte1.length; ++i) {
                if (arrayOfByte1[i] == 7 && arrayOfByte1[i + 1] == 1 && arrayOfByte1[i + 2] == 0 && arrayOfByte1[i + 3] == 0 && arrayOfByte1[i + 4] == 64 && arrayOfByte1[i + 5] == 0 && arrayOfByte1[i + 6] == 64 && arrayOfByte1[i + 7] == 0) {
                    if (arrayOfByte1[i + 8] == 64) {
                        ++j;
                        arrayOfInt1[k] = i;
                        ++k;
                    }
                }
            }
            final String[] arrayOfString = new String[j];
            this.cert_name = new String[j];
            final int[] arrayOfInt2 = new int[j];
            final int[] arrayOfInt3 = new int[j];
            this.cert = new myCert[j];
            this.certs = new X509Certificate[j];
            final CertificateFactory localCertificateFactory = CertificateFactory.getInstance("X.509");
            for (int l = 0; l < j; ++l) {
                try {
                    arrayOfString[l] = String.valueOf(Integer.toHexString(byte2Int(arrayOfByte1[arrayOfInt1[l]]))) + " " + Integer.toHexString(byte2Int(arrayOfByte1[arrayOfInt1[l] + 1])) + " " + Integer.toHexString(byte2Int(arrayOfByte1[arrayOfInt1[l] + 2])) + " " + Integer.toHexString(byte2Int(arrayOfByte1[arrayOfInt1[l] + 3])) + " " + Integer.toHexString(byte2Int(arrayOfByte1[arrayOfInt1[l] + 4])) + " " + Integer.toHexString(byte2Int(arrayOfByte1[arrayOfInt1[l] + 5])) + " " + Integer.toHexString(byte2Int(arrayOfByte1[arrayOfInt1[l] + 6])) + " " + Integer.toHexString(byte2Int(arrayOfByte1[arrayOfInt1[l] + 7])) + " " + Integer.toHexString(byte2Int(arrayOfByte1[arrayOfInt1[l] + 8]));
                    arrayOfInt2[l] = Integer.decode("0x" + Integer.toHexString(byte2Int(arrayOfByte1[arrayOfInt1[l] + 9])) + Integer.toHexString(byte2Int(arrayOfByte1[arrayOfInt1[l] + 10])));
                    arrayOfInt3[l] = Integer.decode("0x" + Integer.toHexString(byte2Int(arrayOfByte1[arrayOfInt1[l] + 11])) + Integer.toHexString(byte2Int(arrayOfByte1[arrayOfInt1[l] + 12])));
                    final int m = arrayOfInt1[l] + 13 + arrayOfInt2[l];
                    final byte[] arrayOfByte2 = new byte[arrayOfInt3[l]];
                    System.arraycopy(arrayOfByte1, m, arrayOfByte2, 0, arrayOfInt3[l]);
                    this.cert_name[l] = new String(arrayOfByte2);
                    final int i2 = arrayOfInt1[l] + 13;
                    final byte[] arrayOfByte3 = new byte[arrayOfInt2[l]];
                    System.arraycopy(arrayOfByte1, i2, arrayOfByte3, 0, arrayOfInt2[l]);
                    this.cert[l] = new myCert(arrayOfByte3);
                    if (this.cert[l].valid != -1) {
                        final ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(arrayOfByte3);
                        this.certs[l] = (X509Certificate)localCertificateFactory.generateCertificate(localByteArrayInputStream);
                        str = String.valueOf(str) + this.cert_name[l].substring(0, this.cert_name[l].length() - 1) + "---";
                    }
                }
                catch (Exception ex) {}
            }
        }
        catch (CertificateException localCertificateException) {
            str = "Se produjo una excepcion " + localCertificateException.toString();
        }
        catch (IOException localIOException) {
            str = "Se produjo una excepcion " + localIOException.toString();
        }
        return str;
    }
    
    public String firma(final String paramString1, final String paramString2, final String paramString3, final String paramString4, final String paramString5, final String paramString6) {
        String str1 = new String();
        if (!paramString6.equalsIgnoreCase("-1")) {
            final long l1 = System.currentTimeMillis();
            System.out.println(paramString3);
            byte[] arrayOfByte1 = new byte[0];
            try {
                final File localFile = new File(paramString1);
                final byte[] arrayOfByte2 = getBytesFromFile(localFile);
                final MainEntry localMainEntry = parseMainEntry(arrayOfByte2);
                final byte[] arrayOfByte3 = localMainEntry.getGlobalSalt();
                if (localMainEntry.checkPassword(paramString2) == 0) {
                    final entry[] arrayOfentry = parsePrivateKeys(arrayOfByte2);
                    for (int i = 0; i < arrayOfentry.length; ++i) {
                        if (arrayOfentry[i].cert_name.length == 1) {
                            for (int j = 0; j < this.cert.length; ++j) {
                                if (compare(arrayOfentry[i].common_region, this.cert[j].common) == 0) {
                                    arrayOfentry[i].cert_name_length = this.cert_name[j].length();
                                    arrayOfentry[i].cert_name = this.cert_name[j].getBytes();
                                }
                            }
                        }
                    }
                    for (int k = 0; k < arrayOfentry.length; ++k) {
                        final String localObject1 = new String(arrayOfentry[k].cert_name);
                        if (localObject1.equalsIgnoreCase(this.cert_name[Integer.parseInt(paramString6)])) {
                            final byte[] arrayOfByte4 = arrayOfentry[k].getEntrySalt();
                            final byte[] localObject2 = arrayOfentry[k].decode(paramString2, arrayOfByte4, arrayOfByte3);
                            final Object localObject3 = new PrivKey(localObject2);
                            this.private_key = ((PrivKey)localObject3).buildRSAPrivateKey();
                            break;
                        }
                    }
                    if (paramString5.equalsIgnoreCase("1")) {
                        final File localObject4 = new File(paramString3);
                        if (localObject4.length() > 8912896L) {
                            if (paramString4.equalsIgnoreCase("MD2withRSA")) {
                                return new String("Se produjo una excepcion: No se puede firmar un archivo de gran tama\u00f1o con este algoritmo");
                            }
                            String localObject5 = new String();
                            if (paramString4.equalsIgnoreCase("MD5withRSA")) {
                                localObject5 = "md5";
                            }
                            else {
                                localObject5 = "sha1";
                            }
                            final Object localObject3 = new Base64();
                            final String str2 = ((Base64)localObject3).encode(getBytesFromFile(localObject4), 0);
                            arrayOfByte1 = getHashFromFile(localObject5, str2.getBytes());
                        }
                        else {
                            arrayOfByte1 = getBytesFromFile(localObject4);
                        }
                    }
                    else {
                        arrayOfByte1 = paramString3.getBytes();
                    }
                    final byte[] arrayOfByte5 = PKCS7_2.build_PKCS7(paramString4, arrayOfByte1, this.certs[Integer.parseInt(paramString6)], this.private_key);
                    final Object localObject6 = new Base64();
                    str1 = String.valueOf(str1) + ((Base64)localObject6).encode(arrayOfByte5, 0);
                    System.out.println("El arreglo de bytes del pkcs7 tiene una longitud de " + str1.length() + " bytes.");
                    final long l2 = System.currentTimeMillis();
                    final long l3 = (l2 - l1) / 1000L;
                    final long l4 = l3 / 60L;
                    final long l5 = l3 % 60L;
                    System.out.println("Total Ellapsed time: " + l4 + " : " + l5);
                    System.out.println("\n");
                }
                else {
                    str1 = "Se produjo una excepcion  Wrong password ";
                }
            }
            catch (InvalidKeyException localInvalidKeyException) {
                str1 = "Se produjo una excepcion " + localInvalidKeyException.toString();
            }
            catch (SignatureException localSignatureException) {
                str1 = "Se produjo una excepcion " + localSignatureException.toString();
            }
            catch (CertificateEncodingException localCertificateEncodingException) {
                str1 = "Se produjo una excepcion " + localCertificateEncodingException.toString();
            }
            catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
                str1 = "Se produjo una excepcion " + localNoSuchAlgorithmException.toString();
            }
            catch (InvalidKeySpecException localInvalidKeySpecException) {
                str1 = "Se produjo una excepcion " + localInvalidKeySpecException.toString();
            }
            catch (IOException localIOException) {
                str1 = "Se produjo una excepcion " + localIOException.toString();
            }
            catch (NoSuchPaddingException localNoSuchPaddingException) {
                str1 = "Se produjo una excepcion " + localNoSuchPaddingException.toString();
            }
            catch (IllegalBlockSizeException localIllegalBlockSizeException) {
                str1 = "Se produjo una excepcion " + localIllegalBlockSizeException.toString();
            }
            catch (BadPaddingException localBadPaddingException) {
                str1 = "Se produjo una excepcion " + localBadPaddingException.toString();
            }
            catch (InvalidAlgorithmParameterException localInvalidAlgorithmParameterException) {
                str1 = "Se produjo una excepcion " + localInvalidAlgorithmParameterException.toString();
            }
        }
        else {
            str1 = "Se produjo una excepcion certificate not chosen.";
        }
        return str1;
    }
    
    private static MainEntry parseMainEntry(final byte[] paramArrayOfByte) {
        int j = 0;
        int k = 0;
        final int[] arrayOfInt = new int[50];
        MainEntry localMainEntry = new MainEntry();
        for (int i = 0; i < paramArrayOfByte.length; ++i) {
            if (paramArrayOfByte[i] == 3 && paramArrayOfByte[i + 1] == 16) {
                if (paramArrayOfByte[i + 2] == 1) {
                    ++j;
                    arrayOfInt[k] = i;
                    ++k;
                }
            }
        }
        for (int l : arrayOfInt) {
            final int i2 = byte2Int(paramArrayOfByte[l + 1]);
            final byte[] arrayOfByte1 = { paramArrayOfByte[l], paramArrayOfByte[l + 1], paramArrayOfByte[l + 2] };
            l += 3;
            final byte[] arrayOfByte2 = new byte[i2];
            for (int i3 = 0; i3 < i2; ++i3) {
                arrayOfByte2[i3] = paramArrayOfByte[l + i3];
            }
            l += i2;
            ++l;
            final byte[] arrayOfByte3 = new byte[12];
            for (int i4 = 0; i4 < 12; ++i4) {
                arrayOfByte3[i4] = paramArrayOfByte[l + i4];
            }
            l += 12;
            final byte[] arrayOfByte4 = new byte[16];
            for (int i5 = 0; i5 < 16; ++i5) {
                arrayOfByte4[i5] = paramArrayOfByte[l + i5];
            }
            l += 16;
            final byte[] arrayOfByte5 = new byte[14];
            for (int i6 = 0; i6 < 14; ++i6) {
                arrayOfByte5[i6] = paramArrayOfByte[l + i6];
            }
            l += 14;
            final byte[] arrayOfByte6 = new byte[16];
            for (int i7 = 0; i7 < 16; ++i7) {
                arrayOfByte6[i7] = paramArrayOfByte[l + i7];
            }
            l += 16;
            final byte[] arrayOfByte7 = new byte[11];
            for (int i8 = 0; i8 < 11; ++i8) {
                arrayOfByte7[i8] = paramArrayOfByte[l + i8];
            }
            l += 11;
            final byte[] arrayOfByte8 = new byte[7];
            for (int i9 = 0; i9 < arrayOfByte8.length; ++i9) {
                arrayOfByte8[i9] = paramArrayOfByte[l + i9 + 1];
            }
            localMainEntry = new MainEntry(arrayOfByte1, arrayOfByte2, arrayOfByte3, arrayOfByte4, paramArrayOfByte[l], arrayOfByte6);
        }
        return localMainEntry;
    }
    
    private static entry[] parsePrivateKeys(final byte[] paramArrayOfByte) {
        int j = 0;
        int k = 0;
        final int[] arrayOfInt = new int[50];
        for (int i = 0; i < paramArrayOfByte.length; ++i) {
            if (paramArrayOfByte[i] == 3) {
                if (paramArrayOfByte[i + 1] == 8) {
                    ++j;
                    arrayOfInt[k] = i;
                    ++k;
                }
            }
        }
        final entry[] arrayOfentry = new entry[j];
        for (int l = 0; l < j; ++l) {
            int m = arrayOfInt[l];
            final int i2 = byte2Int(paramArrayOfByte[m + 1]);
            final int i3 = byte2Int(paramArrayOfByte[m + 2]);
            m += 3;
            final byte[] arrayOfByte1 = new byte[i2];
            for (int i4 = 0; i4 < i2; ++i4) {
                arrayOfByte1[i4] = paramArrayOfByte[m + i4];
            }
            m += i2;
            final byte[] arrayOfByte2 = new byte[i3];
            for (int i5 = 0; i5 < i3; ++i5) {
                arrayOfByte2[i5] = paramArrayOfByte[m + i5];
            }
            m += i3;
            final byte[] arrayOfByte3 = new byte[678];
            for (int i6 = 0; i6 < arrayOfByte3.length; ++i6) {
                arrayOfByte3[i6] = paramArrayOfByte[m + i6];
            }
            m += 679;
            final byte[] arrayOfByte4 = new byte[128];
            for (int i7 = 0; i7 < arrayOfByte4.length; ++i7) {
                arrayOfByte4[i7] = paramArrayOfByte[m + i7];
            }
            arrayOfentry[l] = new entry(paramArrayOfByte[arrayOfInt[l]], i2, i3, arrayOfByte1, arrayOfByte2, arrayOfByte3, arrayOfByte4);
        }
        return arrayOfentry;
    }
    
    private static int byte2Int(final byte paramByte) {
        if (paramByte >= 0) {
            return paramByte;
        }
        return (paramByte & 0x7F) + 128;
    }
    
    private static byte[] getBytesFromFile(final File paramFile) throws IOException {
        final long l = paramFile.length();
        final byte[] arrayOfByte = new byte[(int)l];
        final FileInputStream localFileInputStream = new FileInputStream(paramFile);
        int i = 0;
        int j = 0;
        do {
            i += j;
            if (i >= arrayOfByte.length) {
                break;
            }
        } while ((j = localFileInputStream.read(arrayOfByte, i, arrayOfByte.length - i)) >= 0);
        localFileInputStream.close();
        return arrayOfByte;
    }
    
    private static byte[] getHashFromFile(final String paramString, final byte[] paramArrayOfByte) throws IOException, NoSuchAlgorithmException {
        final MessageDigest localMessageDigest = MessageDigest.getInstance(paramString);
        final ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
        final byte[] arrayOfByte1 = new byte[8192];
        int i;
        while ((i = localByteArrayInputStream.read(arrayOfByte1)) != -1) {
            localMessageDigest.update(arrayOfByte1, 0, i);
        }
        final byte[] arrayOfByte2 = localMessageDigest.digest();
        Print_Array(localMessageDigest.getAlgorithm(), arrayOfByte2);
        return arrayOfByte2;
    }
    
    public String encodeB64(final String paramString) {
        String str = new String();
        try {
            final Base64 localBase64 = new Base64();
            str = localBase64.encode(getBytesFromFile(new File(paramString)), 0);
        }
        catch (IOException localIOException) {
            str = "Se produjo una excepcion " + localIOException.toString();
        }
        return str;
    }
    
    private static void Print_Array(final String paramString, final byte[] paramArrayOfByte) {
        System.out.print(String.valueOf(paramString) + "\t La longitud del arreglo es: " + paramArrayOfByte.length + "\t\n ");
        for (int i = 0; i < paramArrayOfByte.length; ++i) {
            final int j = paramArrayOfByte[i] & 0xFF;
            if (j < 16) {
                System.out.print("0");
            }
            System.out.print(String.valueOf(Integer.toHexString(j)) + " ");
        }
        System.out.println(" \n\n");
    }
    
    private static int compare(final byte[] paramArrayOfByte1, final byte[] paramArrayOfByte2) {
        if (paramArrayOfByte1.length != paramArrayOfByte2.length) {
            return -1;
        }
        for (int i = 0; i < paramArrayOfByte1.length; ++i) {
            if (byte2Int(paramArrayOfByte1[i]) != byte2Int(paramArrayOfByte2[i])) {
                return -1;
            }
        }
        return 0;
    }
    
    public String firma(final String paramString1, final String paramString2, final String paramString3, final String paramString4, final String paramString5) {
        final long l1 = System.currentTimeMillis();
        System.out.println(paramString3);
        String str = new String();
        byte[] arrayOfByte = new byte[0];
        try {
            final File localFile = new File(paramString3);
            boolean bool = false;
            if (paramString5.equalsIgnoreCase("1")) {
                if (localFile.length() > 8912896L) {
                    if (paramString4.equalsIgnoreCase("MD2withRSA")) {
                        return new String("Se produjo una excepcion: No se puede firmar un archivo de gran tamanio con este algoritmo");
                    }
                    bool = true;
                    String localObject1 = new String();
                    if (paramString4.equalsIgnoreCase("MD5withRSA")) {
                        localObject1 = "md5";
                    }
                    else {
                        localObject1 = "sha1";
                    }
                    final Base64 localObject2 = new Base64();
                    final String localObject3 = localObject2.encode(getBytesFromFile(localFile), 0);
                    arrayOfByte = getHashFromFile(localObject1, localObject3.getBytes());
                }
                else {
                    arrayOfByte = getBytesFromFile(localFile);
                }
            }
            else {
                arrayOfByte = paramString3.getBytes();
            }
            final byte[] localObject4 = pkcs7.build_pkcs7(paramString4, arrayOfByte, paramString1, paramString2, bool);
            System.out.println("El arreglo de bytes del pkcs7 tiene una longitud de " + localObject4.length + " bytes.");
            final Object localObject5 = new Base64();
            str = String.valueOf(str) + ((Base64)localObject5).encode(localObject4, 0);
        }
        catch (InvalidKeyException localInvalidKeyException) {
            str = "Se produjo una excepcion " + localInvalidKeyException.toString();
        }
        catch (SignatureException localSignatureException) {
            str = "Se produjo una excepcion " + localSignatureException.toString();
        }
        catch (CertificateEncodingException localCertificateEncodingException) {
            str = "Se produjo una excepcion " + localCertificateEncodingException.toString();
        }
        catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
            str = "Se produjo una excepcion " + localNoSuchAlgorithmException.toString();
        }
        catch (KeyStoreException localKeyStoreException) {
            str = "Se produjo una excepcion " + localKeyStoreException.toString();
        }
        catch (IOException localIOException) {
            str = "Se produjo una excepcion " + localIOException.toString();
        }
        catch (CertificateException localCertificateException) {
            str = "Se produjo una excepcion " + localCertificateException.toString();
        }
        catch (UnrecoverableKeyException localUnrecoverableKeyException) {
            str = "Se produjo una excepcion " + localUnrecoverableKeyException.toString();
        }
        final long l2 = System.currentTimeMillis();
        final long l3 = (l2 - l1) / 1000L;
        final long l4 = l3 / 60L;
        final long l5 = l3 % 60L;
        System.out.println("Total Ellapsed time: " + l4 + " : " + l5);
        System.out.println("\n");
        return str;
    }
    
    public static void main(final String[] paramArrayOfString) {
        final SeguriSIGN_Client_Applet localSeguriSIGN_Client_Applet = new SeguriSIGN_Client_Applet();
        System.out.println(localSeguriSIGN_Client_Applet.parseaCertificados("C:\\cert7.db"));
    }
}
