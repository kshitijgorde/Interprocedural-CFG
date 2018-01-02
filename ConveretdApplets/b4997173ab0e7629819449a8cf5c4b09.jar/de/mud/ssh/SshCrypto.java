// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.ssh;

import cryptix.crypt.BlockCipher;
import java.math.BigInteger;
import cryptix.crypt.IDEA;

class SshCrypto
{
    private IDEA blockCipherIDEA;
    private byte[] IDEA_Vector_Receive;
    private byte[] IDEA_Vector_Send;
    
    public SshCrypto(final byte[] key) {
        this.IDEA_Vector_Receive = new byte[8];
        this.IDEA_Vector_Send = new byte[8];
        this.blockCipherIDEA = new IDEA(key);
    }
    
    public void encrypt(final byte[] block) {
        final int numberOfRound = block.length / 8;
        int offset = 0;
        final byte[] blockOfHeight = new byte[8];
        for (int round = 0; round < numberOfRound; ++round) {
            int offsetBis = offset;
            for (int i = 0; i < 8; ++i) {
                blockOfHeight[i] = block[offset++];
            }
            ((BlockCipher)this.blockCipherIDEA).encrypt(this.IDEA_Vector_Send);
            for (int j = 0; j < 8; ++j) {
                blockOfHeight[j] = (byte)((blockOfHeight[j] & 0xFF) ^ (this.IDEA_Vector_Send[j] & 0xFF & 0xFF));
            }
            for (int k = 0; k < 8; ++k) {
                this.IDEA_Vector_Send[k] = blockOfHeight[k];
            }
            for (int l = 0; l < 8; ++l) {
                block[offsetBis++] = blockOfHeight[l];
            }
        }
    }
    
    public void decrypt(final byte[] block) {
        final int numberOfRound = block.length / 8;
        int offset = 0;
        final byte[] blockOfHeight = new byte[8];
        for (int round = 0; round < numberOfRound; ++round) {
            int offsetBis = offset;
            ((BlockCipher)this.blockCipherIDEA).encrypt(this.IDEA_Vector_Receive);
            final byte[] next_IDEA_Vector = new byte[8];
            for (int i = 0; i < 8; ++i) {
                blockOfHeight[i] = (next_IDEA_Vector[i] = block[offset++]);
            }
            for (int j = 0; j < 8; ++j) {
                final int int1 = this.IDEA_Vector_Receive[j] & 0xFF;
                final int int2 = blockOfHeight[j] & 0xFF;
                blockOfHeight[j] = (byte)((int1 ^ int2) & 0xFF);
            }
            for (int k = 0; k < 8; ++k) {
                block[offsetBis++] = blockOfHeight[k];
            }
            this.IDEA_Vector_Receive = next_IDEA_Vector;
        }
    }
    
    public static byte[] encrypteRSAPkcs1Twice(byte[] clearData, final byte[] server_key_public_exponent, final byte[] server_key_public_modulus, final byte[] host_key_public_exponent, final byte[] host_key_public_modulus) {
        int offset = 0;
        byte[] EncryptionBlock = new byte[server_key_public_modulus.length];
        EncryptionBlock[0] = 0;
        EncryptionBlock[1] = 2;
        offset = 2;
        for (int i = 2; i < EncryptionBlock.length - clearData.length - 1; ++i) {
            EncryptionBlock[offset++] = SshMisc.getNotZeroRandomByte();
        }
        EncryptionBlock[offset++] = 0;
        for (int j = 0; j < clearData.length; ++j) {
            EncryptionBlock[offset++] = clearData[j];
        }
        BigInteger m = new BigInteger(1, server_key_public_modulus);
        BigInteger e = new BigInteger(1, server_key_public_exponent);
        BigInteger message = new BigInteger(1, EncryptionBlock);
        message = message.modPow(e, m);
        byte[] messageByteTemp = message.toByteArray();
        byte[] messageByte = new byte[server_key_public_modulus.length];
        int tempOffset;
        for (tempOffset = 0; messageByteTemp[tempOffset] == 0; ++tempOffset) {}
        for (int k = messageByte.length - messageByteTemp.length + tempOffset; k < messageByte.length; ++k) {
            messageByte[k] = messageByteTemp[tempOffset++];
        }
        clearData = messageByte;
        offset = 0;
        EncryptionBlock = new byte[host_key_public_modulus.length];
        EncryptionBlock[0] = 0;
        EncryptionBlock[1] = 2;
        offset = 2;
        for (int l = 2; l < EncryptionBlock.length - clearData.length - 1; ++l) {
            EncryptionBlock[offset++] = SshMisc.getNotZeroRandomByte();
        }
        EncryptionBlock[offset++] = 0;
        for (int i2 = 0; i2 < clearData.length; ++i2) {
            EncryptionBlock[offset++] = clearData[i2];
        }
        m = new BigInteger(1, host_key_public_modulus);
        e = new BigInteger(1, host_key_public_exponent);
        message = new BigInteger(1, EncryptionBlock);
        message = message.modPow(e, m);
        messageByteTemp = message.toByteArray();
        messageByte = new byte[host_key_public_modulus.length];
        for (tempOffset = 0; messageByteTemp[tempOffset] == 0; ++tempOffset) {}
        for (int i3 = messageByte.length - messageByteTemp.length + tempOffset; i3 < messageByte.length; ++i3) {
            messageByte[i3] = messageByteTemp[tempOffset++];
        }
        final byte[] encrypted_session_key = new byte[host_key_public_modulus.length + 2];
        encrypted_session_key[1] = (byte)(8 * host_key_public_modulus.length & 0xFF);
        encrypted_session_key[0] = (byte)(8 * host_key_public_modulus.length >> 8 & 0xFF);
        for (int i4 = 0; i4 < host_key_public_modulus.length; ++i4) {
            encrypted_session_key[i4 + 2] = messageByte[i4];
        }
        return encrypted_session_key;
    }
}
