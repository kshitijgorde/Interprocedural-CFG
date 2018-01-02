// 
// Decompiled by Procyon v0.5.30
// 

package SeguriSIGN_Client;

import java.security.InvalidAlgorithmParameterException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;

public class entry
{
    public byte first_byte;
    public int entry_salt_length;
    public int cert_name_length;
    public byte[] entry_salt;
    public byte[] cert_name;
    PKCS12 pkcs12;
    public byte[] common_region;
    
    public entry(final byte first_byte, final int entry_salt_length, final int cert_name_length, final byte[] array, final byte[] array2, final byte[] array3, final byte[] array4) {
        this.common_region = new byte[128];
        this.first_byte = first_byte;
        this.entry_salt_length = entry_salt_length;
        this.cert_name_length = cert_name_length;
        this.entry_salt = new byte[entry_salt_length];
        for (int i = 0; i < array.length; ++i) {
            this.entry_salt[i] = array[i];
        }
        this.cert_name = new byte[cert_name_length];
        for (int j = 0; j < array2.length; ++j) {
            this.cert_name[j] = array2[j];
        }
        this.pkcs12 = new PKCS12(array3);
        for (int k = 0; k < array4.length; ++k) {
            this.common_region[k] = array4[k];
        }
    }
    
    public byte[] decode(final String s, final byte[] array, final byte[] array2) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        return this.pkcs12.decode(s.getBytes(), array, array2, this.pkcs12.getEncryptedPrivateKey());
    }
    
    public byte[] getEntrySalt() {
        return this.entry_salt;
    }
    
    public void print() {
        System.out.print(Integer.toHexString(byte2Int(this.first_byte)) + " " + Integer.toHexString(this.entry_salt_length) + " " + Integer.toHexString(this.cert_name_length) + " ");
        System.out.print("\t entry salt: ");
        for (int i = 0; i < this.entry_salt_length; ++i) {
            System.out.print(Integer.toHexString(byte2Int(this.entry_salt[i])) + " ");
        }
        System.out.print("\n\t cert name: " + new String(this.cert_name));
        System.out.print("\n\t PKCS#12: ");
        this.pkcs12.print();
        System.out.println("\n");
        System.out.print("\n\t common region: ");
        for (int j = 0; j < this.common_region.length; ++j) {
            if (j % 16 == 0) {
                System.out.println();
            }
            System.out.print(Integer.toHexString(byte2Int(this.common_region[j])) + " ");
        }
    }
    
    public static int byte2Int(final byte b) {
        if (b >= 0) {
            return b;
        }
        return (b & 0x7F) + 128;
    }
}
