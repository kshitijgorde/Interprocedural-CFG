// 
// Decompiled by Procyon v0.5.30
// 

package anon.mixminion.message;

import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.io.InputStream;
import java.util.zip.ZipInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.io.OutputStream;
import java.util.zip.ZipOutputStream;
import java.io.ByteArrayOutputStream;
import anon.util.ZLibTools;
import anon.util.ByteArrayUtil;
import anon.crypto.MyAES;
import java.util.Vector;
import org.bouncycastle.crypto.digests.SHA1Digest;
import java.security.SecureRandom;

public class MixMinionCryptoUtil
{
    public static byte[] randomArray(final int n) {
        final byte[] array = new byte[n];
        new SecureRandom().nextBytes(array);
        return array;
    }
    
    static byte[] xor(final byte[] array, final byte[] array2) {
        if (array.length != array2.length) {
            return null;
        }
        final byte[] array3 = new byte[array.length];
        for (int i = 0; i < array.length; ++i) {
            array3[i] = (byte)(array[i] ^ array2[i]);
        }
        return array3;
    }
    
    public static byte[] hash(final byte[] array) {
        final SHA1Digest sha1Digest = new SHA1Digest();
        sha1Digest.update(array, 0, array.length);
        final byte[] array2 = new byte[sha1Digest.getDigestSize()];
        sha1Digest.doFinal(array2, 0);
        return array2;
    }
    
    public static Vector subVector(final Vector vector, final int n, final int n2) {
        final Vector<Object> vector2 = new Vector<Object>();
        for (int i = n; i < n + n2; ++i) {
            vector2.addElement(vector.elementAt(i));
        }
        return vector2;
    }
    
    public static byte[] Encrypt(final byte[] array, final byte[] array2) {
        return xor(array2, createPRNG(array, array2.length));
    }
    
    static byte[] createPRNG(final byte[] array, int i) {
        final MyAES myAES = new MyAES();
        final byte[] array2 = new byte[i];
        final byte[] array3 = new byte[16];
        final byte[] array4 = new byte[16];
        try {
            myAES.init(true, array);
            int n;
            for (n = 0; i >= 16; i -= 16, n += 16) {
                myAES.processBlockECB(array3, array4);
                System.arraycopy(array4, 0, array2, n, 16);
                int n2 = 1;
                for (int j = array3.length - 1; j >= 0; --j) {
                    final int n3 = (array3[j] & 0xFF) + n2;
                    if (n3 > 255) {
                        n2 = 1;
                    }
                    else {
                        n2 = 0;
                    }
                    array3[j] = (byte)n3;
                }
            }
            if (i > 0) {
                myAES.processBlockECB(array3, array4);
                System.arraycopy(array4, 0, array2, n, i);
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
        return array2;
    }
    
    public static byte[] SPRP_Encrypt(final byte[] array, final byte[] array2) {
        final byte[] array3 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
        final byte[] xor = xor(array, array3);
        array3[19] = 2;
        final byte[] xor2 = xor(array, array3);
        array3[19] = 3;
        final byte[] xor3 = xor(array, array3);
        final byte[] copy = ByteArrayUtil.copy(array2, 0, 20);
        final byte[] encrypt = Encrypt(ByteArrayUtil.copy(hash(ByteArrayUtil.conc(array, copy, array)), 0, 16), ByteArrayUtil.copy(array2, 20, array2.length - 20));
        final byte[] xor4 = xor(copy, hash(ByteArrayUtil.conc(xor, encrypt, xor)));
        final byte[] encrypt2 = Encrypt(ByteArrayUtil.copy(hash(ByteArrayUtil.conc(xor2, xor4, xor2)), 0, 16), encrypt);
        return ByteArrayUtil.conc(xor(xor4, hash(ByteArrayUtil.conc(xor3, encrypt2, xor3))), encrypt2);
    }
    
    public static byte[] SPRP_Decrypt(final byte[] array, final byte[] array2) {
        final byte[] array3 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
        final byte[] xor = xor(array, array3);
        array3[19] = 2;
        final byte[] xor2 = xor(array, array3);
        array3[19] = 3;
        final byte[] xor3 = xor(array, array3);
        final byte[] copy = ByteArrayUtil.copy(array2, 0, 20);
        final byte[] copy2 = ByteArrayUtil.copy(array2, 20, array2.length - 20);
        final byte[] xor4 = xor(copy, hash(ByteArrayUtil.conc(xor3, copy2, xor3)));
        final byte[] encrypt = Encrypt(ByteArrayUtil.copy(hash(ByteArrayUtil.conc(xor2, xor4, xor2)), 0, 16), copy2);
        final byte[] xor5 = xor(xor4, hash(ByteArrayUtil.conc(xor, encrypt, xor)));
        return ByteArrayUtil.conc(xor5, Encrypt(ByteArrayUtil.copy(hash(ByteArrayUtil.conc(array, xor5, array)), 0, 16), encrypt));
    }
    
    static byte[] compressData(final byte[] array) {
        final byte[] compress = ZLibTools.compress(array);
        if (compress[0] != 120 || compress[1] + 256 != 218) {
            throw new RuntimeException("The Compressed Messege didn't start with 0x78DA");
        }
        return compress;
    }
    
    static byte[] decompressData(final byte[] array) {
        return ZLibTools.decompress(array);
    }
    
    private static byte[] ZIPcompressData(final byte[] array) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            final ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
            zipOutputStream.setLevel(9);
            zipOutputStream.setMethod(8);
            zipOutputStream.putNextEntry(new ZipEntry("MixMinionZip"));
            zipOutputStream.write(array);
            zipOutputStream.flush();
            zipOutputStream.close();
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        final byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (!ByteArrayUtil.equal(array, ZIPextractData(byteArray))) {
            throw new RuntimeException("Something with Compression/Decompression was wrong!");
        }
        return byteArray;
    }
    
    private static byte[] ZIPextractData(final byte[] array) {
        byte[] conc = null;
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
        try {
            final ZipInputStream zipInputStream = new ZipInputStream(byteArrayInputStream);
            zipInputStream.getNextEntry();
            boolean b = true;
            int n = -1;
            while (b) {
                ++n;
                final int read = zipInputStream.read();
                final byte[] array2 = { (byte)read };
                if (read != -1) {
                    conc = ByteArrayUtil.conc(conc, array2);
                }
                else {
                    b = false;
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return conc;
    }
    
    private static byte[] GZIPcompressData(final byte[] array) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            final GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(array);
            gzipOutputStream.flush();
            gzipOutputStream.close();
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    private static byte[] GZIPextractData(final byte[] array) {
        byte[] conc = null;
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
        try {
            final GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
            boolean b = true;
            int n = -1;
            while (b) {
                ++n;
                final int read = gzipInputStream.read();
                final byte[] array2 = { (byte)read };
                if (read != -1) {
                    conc = ByteArrayUtil.conc(conc, array2);
                }
                else {
                    b = false;
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return conc;
    }
}
