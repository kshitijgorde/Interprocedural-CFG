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
            for (int i = 0; i < 8; ++i) {
                blockOfHeight[i] = (byte)((blockOfHeight[i] & 0xFF) ^ (this.IDEA_Vector_Send[i] & 0xFF & 0xFF));
            }
            for (int i = 0; i < 8; ++i) {
                this.IDEA_Vector_Send[i] = blockOfHeight[i];
            }
            for (int i = 0; i < 8; ++i) {
                block[offsetBis++] = blockOfHeight[i];
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
            for (int i = 0; i < 8; ++i) {
                final int int1 = this.IDEA_Vector_Receive[i] & 0xFF;
                final int int2 = blockOfHeight[i] & 0xFF;
                blockOfHeight[i] = (byte)((int1 ^ int2) & 0xFF);
            }
            for (int i = 0; i < 8; ++i) {
                block[offsetBis++] = blockOfHeight[i];
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
        for (int i = 0; i < clearData.length; ++i) {
            EncryptionBlock[offset++] = clearData[i];
        }
        BigInteger m = new BigInteger(1, server_key_public_modulus);
        BigInteger e = new BigInteger(1, server_key_public_exponent);
        BigInteger message = new BigInteger(1, EncryptionBlock);
        message = message.modPow(e, m);
        byte[] messageByteTemp = message.toByteArray();
        byte[] messageByte = new byte[server_key_public_modulus.length];
        int tempOffset;
        for (tempOffset = 0; messageByteTemp[tempOffset] == 0; ++tempOffset) {}
        for (int j = messageByte.length - messageByteTemp.length + tempOffset; j < messageByte.length; ++j) {
            messageByte[j] = messageByteTemp[tempOffset++];
        }
        clearData = messageByte;
        offset = 0;
        EncryptionBlock = new byte[host_key_public_modulus.length];
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
        m = new BigInteger(1, host_key_public_modulus);
        e = new BigInteger(1, host_key_public_exponent);
        message = new BigInteger(1, EncryptionBlock);
        message = message.modPow(e, m);
        messageByteTemp = message.toByteArray();
        messageByte = new byte[host_key_public_modulus.length];
        for (tempOffset = 0; messageByteTemp[tempOffset] == 0; ++tempOffset) {}
        for (int j = messageByte.length - messageByteTemp.length + tempOffset; j < messageByte.length; ++j) {
            messageByte[j] = messageByteTemp[tempOffset++];
        }
        final byte[] encrypted_session_key = new byte[host_key_public_modulus.length + 2];
        encrypted_session_key[1] = (byte)(8 * host_key_public_modulus.length & 0xFF);
        encrypted_session_key[0] = (byte)(8 * host_key_public_modulus.length >> 8 & 0xFF);
        for (int k = 0; k < host_key_public_modulus.length; ++k) {
            encrypted_session_key[k + 2] = messageByte[k];
        }
        return encrypted_session_key;
    }
}
