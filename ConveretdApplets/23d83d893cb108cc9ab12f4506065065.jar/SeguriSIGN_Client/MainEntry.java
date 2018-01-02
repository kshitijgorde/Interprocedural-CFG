// 
// Decompiled by Procyon v0.5.30
// 

package SeguriSIGN_Client;

import java.security.InvalidAlgorithmParameterException;
import javax.crypto.NoSuchPaddingException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import javax.crypto.IllegalBlockSizeException;

public class MainEntry
{
    public byte[] header;
    public byte[] entry_salt;
    public byte[] OID;
    public byte[] pw_ch_encrypted;
    public byte version;
    public byte[] global_salt;
    
    public MainEntry() {
        this.header = new byte[3];
        this.entry_salt = new byte[16];
        this.OID = new byte[12];
        this.pw_ch_encrypted = new byte[16];
        this.global_salt = new byte[16];
    }
    
    public MainEntry(final byte[] array, final byte[] array2, final byte[] array3, final byte[] array4, final byte version, final byte[] array5) {
        this.header = new byte[3];
        this.entry_salt = new byte[16];
        this.OID = new byte[12];
        this.pw_ch_encrypted = new byte[16];
        this.global_salt = new byte[16];
        for (int i = 0; i < array.length; ++i) {
            this.header[i] = array[i];
        }
        for (int j = 0; j < this.entry_salt.length; ++j) {
            this.entry_salt[j] = array2[j];
        }
        for (int k = 0; k < this.OID.length; ++k) {
            this.OID[k] = array3[k];
        }
        for (int l = 0; l < this.pw_ch_encrypted.length; ++l) {
            this.pw_ch_encrypted[l] = array4[l];
        }
        this.version = version;
        for (int n = 0; n < this.global_salt.length; ++n) {
            this.global_salt[n] = array5[n];
        }
    }
    
    public int checkPassword(final String s) throws IllegalBlockSizeException, InvalidKeyException, NoSuchAlgorithmException, BadPaddingException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException {
        return new String(new PKCS12().decode(s.getBytes(), this.entry_salt, this.global_salt, this.pw_ch_encrypted)).substring(0, 14).compareTo("password-check");
    }
    
    public byte[] getGlobalSalt() {
        return this.global_salt;
    }
    
    public void print() {
        System.out.print("\nHeader: \t");
        for (int i = 0; i < this.header.length; ++i) {
            System.out.print(Integer.toHexString(byte2Int(this.header[i])) + " ");
        }
        System.out.print("\nEntry Salt: \t");
        for (int j = 0; j < this.entry_salt.length; ++j) {
            System.out.print(Integer.toHexString(byte2Int(this.entry_salt[j])) + " ");
        }
        System.out.print("\nOID: \t\t");
        for (int k = 0; k < this.OID.length; ++k) {
            System.out.print(Integer.toHexString(byte2Int(this.OID[k])) + " ");
        }
        System.out.print("\nPassword-Check encrypted: \t");
        for (int l = 0; l < this.pw_ch_encrypted.length; ++l) {
            System.out.print(Integer.toHexString(byte2Int(this.pw_ch_encrypted[l])) + " ");
        }
        System.out.print("\nVersion: \t" + Integer.toHexString(byte2Int(this.version)));
        System.out.print("\nGlobal Salt: \t");
        for (int n = 0; n < this.global_salt.length; ++n) {
            System.out.print(Integer.toHexString(byte2Int(this.global_salt[n])) + " ");
        }
        System.out.println("\n\n\n");
    }
    
    public static int byte2Int(final byte b) {
        if (b >= 0) {
            return b;
        }
        return (b & 0x7F) + 128;
    }
}
