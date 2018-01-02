// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.security.PublicKey;
import java.security.SignatureException;
import java.security.InvalidKeyException;
import java.security.Signature;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import java.io.ByteArrayOutputStream;
import com.hw.client.util.a;
import java.io.File;

public final class ce
{
    private static final int[] a;
    
    public static boolean a(final File file, final File file2) {
        if (file == null || !file.exists()) {
            com.hw.client.util.a.d("archive file is invalid, zip_file => " + file);
            cx.a(161);
            return false;
        }
        if (file2 == null || !file2.exists()) {
            com.hw.client.util.a.d("signature file is invalid, sig_file => " + file2);
            cx.a(162);
            return false;
        }
        com.hw.client.util.a.b("verifying door archive, zip_file => " + file + ", sig_file => " + file2);
        PublicKey generatePublic;
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(ce.a.length);
            for (int i = 0; i < ce.a.length; ++i) {
                byteArrayOutputStream.write(ce.a[i]);
            }
            final X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(byteArrayOutputStream.toByteArray());
            com.hw.client.util.a.b("successfully created archive encoded key spec");
            final KeyFactory instance = KeyFactory.getInstance("RSA");
            com.hw.client.util.a.b("successfully retrieved key factory, algorithm => " + instance.getAlgorithm());
            generatePublic = instance.generatePublic(x509EncodedKeySpec);
            com.hw.client.util.a.b("successfully generate public key, algorithm => " + generatePublic.getAlgorithm());
            byteArrayOutputStream.close();
        }
        catch (NoSuchAlgorithmException ex) {
            com.hw.client.util.a.a("unable to get key factory", ex);
            cx.a(163, ex);
            return false;
        }
        catch (InvalidKeySpecException ex2) {
            com.hw.client.util.a.a("unable to generate public key", ex2);
            cx.a(164, ex2);
            return false;
        }
        catch (IOException ex3) {
            com.hw.client.util.a.a("unable to close byte array output stream", ex3);
            cx.a(165, ex3);
            return false;
        }
        com.hw.client.util.a.b("attempting to read signature data, sig_file => " + file2);
        final ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        try {
            final FileInputStream fileInputStream = new FileInputStream(file2);
            final byte[] array = new byte[1024];
            int read;
            while ((read = fileInputStream.read(array, 0, array.length)) != -1) {
                byteArrayOutputStream2.write(array, 0, read);
            }
        }
        catch (IOException ex4) {
            com.hw.client.util.a.a("exception while processing signature data", ex4);
            cx.a(166, ex4);
            return false;
        }
        com.hw.client.util.a.b("attempting to read archvie data, zip_file => " + file);
        final ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
        try {
            final FileInputStream fileInputStream2 = new FileInputStream(file);
            final byte[] array2 = new byte[1024];
            int read2;
            while ((read2 = fileInputStream2.read(array2, 0, array2.length)) != -1) {
                byteArrayOutputStream3.write(array2, 0, read2);
            }
        }
        catch (IOException ex5) {
            com.hw.client.util.a.a("exception while processing archvie data", ex5);
            cx.a(167, ex5);
            return false;
        }
        try {
            final Signature instance2;
            (instance2 = Signature.getInstance("SHA1withRSA")).initVerify(generatePublic);
            instance2.update(byteArrayOutputStream3.toByteArray());
            if (!instance2.verify(byteArrayOutputStream2.toByteArray())) {
                com.hw.client.util.a.c("unable to verify door archive");
                cx.a(160);
                return false;
            }
            byteArrayOutputStream3.close();
            byteArrayOutputStream2.close();
        }
        catch (NoSuchAlgorithmException ex6) {
            com.hw.client.util.a.a("unable to get signature instance", ex6);
            cx.a(163, ex6);
            return false;
        }
        catch (InvalidKeyException ex7) {
            com.hw.client.util.a.a("unable to initialize archive verification", ex7);
            cx.a(168, ex7);
            return false;
        }
        catch (SignatureException ex8) {
            com.hw.client.util.a.a("unable to verify archive", ex8);
            cx.a(169, ex8);
            return false;
        }
        catch (IOException ex9) {
            com.hw.client.util.a.a("unable to close byte array output stream", ex9);
            cx.a(165, ex9);
            return false;
        }
        com.hw.client.util.a.c("successfully verified door archive");
        return true;
    }
    
    static {
        a = new int[] { 48, 130, 1, 34, 48, 13, 6, 9, 42, 134, 72, 134, 247, 13, 1, 1, 1, 5, 0, 3, 130, 1, 15, 0, 48, 130, 1, 10, 2, 130, 1, 1, 0, 208, 149, 206, 87, 35, 46, 66, 48, 67, 64, 137, 209, 209, 203, 115, 195, 76, 5, 23, 75, 232, 174, 172, 164, 4, 191, 209, 39, 8, 44, 26, 159, 216, 216, 127, 6, 235, 6, 208, 124, 85, 83, 209, 173, 123, 254, 51, 190, 66, 6, 199, 71, 117, 202, 37, 214, 241, 207, 250, 32, 1, 122, 73, 25, 46, 232, 60, 51, 17, 97, 254, 140, 154, 22, 64, 79, 61, 183, 11, 93, 202, 105, 181, 171, 72, 71, 49, 183, 114, 25, 145, 30, 146, 82, 174, 246, 251, 67, 42, 241, 203, 168, 154, 190, 160, 178, 22, 84, 53, 51, 169, 149, 250, 38, 200, 192, 238, 21, 206, 145, 130, 201, 208, 86, 167, 198, 71, 158, 192, 84, 76, 71, 228, 154, 53, 4, 113, 171, 164, 102, 107, 222, 200, 166, 119, 8, 213, 180, 14, 161, 155, 245, 124, 31, 2, 184, 163, 93, 30, 192, 172, 120, 36, 171, 4, 168, 79, 248, 80, 22, 244, 235, 211, 199, 234, 246, 189, 3, 62, 134, 186, 223, 144, 191, 6, 251, 137, 248, 29, 237, 234, 35, 152, 208, 18, 82, 175, 231, 54, 76, 98, 175, 211, 121, 9, 209, 29, 194, 14, 13, 157, 81, 213, 143, 208, 243, 214, 141, 50, 172, 51, 114, 214, 143, 108, 65, 52, 89, 243, 198, 202, 132, 181, 164, 97, 242, 190, 93, 15, 97, 240, 191, 77, 1, 13, 178, 146, 144, 221, 131, 83, 109, 237, 235, 84, 29, 2, 3, 1, 0, 1 };
        final int[] array = { 48, 130, 1, 34, 48, 13, 6, 9, 42, 134, 72, 134, 247, 13, 1, 1, 1, 5, 0, 3, 130, 1, 15, 0, 48, 130, 1, 10, 2, 130, 1, 1, 0, 153, 40, 7, 70, 139, 109, 227, 138, 50, 251, 66, 245, 254, 151, 127, 157, 162, 182, 195, 130, 34, 153, 130, 246, 1, 160, 222, 126, 210, 193, 0, 93, 14, 70, 232, 195, 71, 82, 0, 11, 197, 193, 27, 220, 254, 212, 209, 161, 68, 123, 200, 243, 65, 82, 113, 222, 249, 112, 124, 129, 30, 143, 21, 233, 113, 252, 38, 215, 0, 22, 222, 191, 232, 191, 7, 167, 236, 240, 183, 50, 105, 175, 76, 84, 190, 53, 170, 35, 94, 188, 164, 216, 182, 242, 118, 237, 146, 167, 252, 53, 92, 114, 135, 10, 19, 227, 128, 169, 30, 184, 155, 147, 106, 76, 69, 98, 81, 31, 139, 119, 88, 42, 57, 223, 181, 150, 220, 3, 252, 203, 229, 168, 21, 25, 164, 227, 1, 251, 134, 73, 57, 205, 146, 57, 194, 10, 37, 21, 25, 159, 120, 251, 189, 248, 239, 217, 223, 51, 205, 187, 123, 203, 56, 75, 81, 46, 86, 249, 36, 190, 176, 191, 232, 223, 149, 26, 166, 242, 131, 147, 60, 165, 0, 40, 241, 147, 183, 75, 242, 3, 18, 5, 69, 67, 134, 13, 27, 192, 103, 21, 63, 201, 221, 208, 85, 252, 235, 208, 51, 23, 62, 15, 25, 49, 84, 59, 68, 244, 253, 224, 53, 216, 118, 12, 78, 13, 113, 176, 116, 100, 147, 105, 248, 97, 35, 129, 223, 30, 68, 203, 158, 158, 190, 3, 188, 163, 149, 211, 236, 127, 127, 31, 199, 173, 174, 117, 2, 3, 1, 0, 1 };
    }
}
