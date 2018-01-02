// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.ssh;

import java.math.BigInteger;

public class SshCrypto
{
    private Cipher sndCipher;
    private Cipher rcvCipher;
    
    public SshCrypto(final String type, final byte[] key) {
        this.sndCipher = Cipher.getInstance(type);
        this.rcvCipher = Cipher.getInstance(type);
        this.sndCipher.setKey(key);
        this.rcvCipher.setKey(key);
    }
    
    public byte[] encrypt(final byte[] block) {
        return this.sndCipher.encrypt(block);
    }
    
    public byte[] decrypt(final byte[] block) {
        return this.rcvCipher.decrypt(block);
    }
    
    public static byte[] encrypteRSAPkcs1Twice(byte[] clearData, final byte[] server_key_public_exponent, final byte[] server_key_public_modulus, final byte[] host_key_public_exponent, final byte[] host_key_public_modulus) {
        byte[] key1exp;
        byte[] key1mod;
        byte[] key2exp;
        byte[] key2mod;
        if (server_key_public_modulus.length < host_key_public_modulus.length) {
            key1exp = server_key_public_exponent;
            key1mod = server_key_public_modulus;
            key2exp = host_key_public_exponent;
            key2mod = host_key_public_modulus;
        }
        else {
            key1exp = host_key_public_exponent;
            key1mod = host_key_public_modulus;
            key2exp = server_key_public_exponent;
            key2mod = server_key_public_modulus;
        }
        int offset = 0;
        byte[] EncryptionBlock = new byte[key1mod.length];
        EncryptionBlock[0] = 0;
        EncryptionBlock[1] = 2;
        offset = 2;
        for (int i = 2; i < EncryptionBlock.length - clearData.length - 1; ++i) {
            EncryptionBlock[offset++] = SshMisc.getNotZeroRandomByte();
        }
        EncryptionBlock[offset++] = 0;
        for (int i = 0; i < clearData.length; ++i) {
            EncryptionBlock[offset++] = clearData[i];
        }
        BigInteger m = new BigInteger(1, key1mod);
        BigInteger e = new BigInteger(1, key1exp);
        BigInteger message = new BigInteger(1, EncryptionBlock);
        message = message.modPow(e, m);
        byte[] messageByteTemp = message.toByteArray();
        byte[] messageByte = new byte[key1mod.length];
        int tempOffset;
        for (tempOffset = 0; messageByteTemp[tempOffset] == 0; ++tempOffset) {}
        for (int j = messageByte.length - messageByteTemp.length + tempOffset; j < messageByte.length; ++j) {
            messageByte[j] = messageByteTemp[tempOffset++];
        }
        clearData = messageByte;
        offset = 0;
        EncryptionBlock = new byte[key2mod.length];
        EncryptionBlock[0] = 0;
        EncryptionBlock[1] = 2;
        offset = 2;
        for (int j = 2; j < EncryptionBlock.length - clearData.length - 1; ++j) {
            EncryptionBlock[offset++] = SshMisc.getNotZeroRandomByte();
        }
        EncryptionBlock[offset++] = 0;
        for (int j = 0; j < clearData.length; ++j) {
            EncryptionBlock[offset++] = clearData[j];
        }
        m = new BigInteger(1, key2mod);
        e = new BigInteger(1, key2exp);
        message = new BigInteger(1, EncryptionBlock);
        message = message.modPow(e, m);
        messageByteTemp = message.toByteArray();
        messageByte = new byte[key2mod.length];
        for (tempOffset = 0; messageByteTemp[tempOffset] == 0; ++tempOffset) {}
        for (int j = messageByte.length - messageByteTemp.length + tempOffset; j < messageByte.length; ++j) {
            messageByte[j] = messageByteTemp[tempOffset++];
        }
        final byte[] encrypted_session_key = new byte[key2mod.length + 2];
        encrypted_session_key[1] = (byte)(8 * key2mod.length & 0xFF);
        encrypted_session_key[0] = (byte)(8 * key2mod.length >> 8 & 0xFF);
        for (int k = 0; k < key2mod.length; ++k) {
            encrypted_session_key[k + 2] = messageByte[k];
        }
        return encrypted_session_key;
    }
}
