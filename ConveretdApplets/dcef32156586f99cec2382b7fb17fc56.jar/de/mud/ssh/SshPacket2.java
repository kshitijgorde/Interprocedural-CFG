// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.ssh;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.math.BigInteger;

public class SshPacket2 extends SshPacket
{
    private static final boolean debug = true;
    private byte[] packet_length_array;
    private int packet_length;
    private int padlen;
    private byte[] crc_array;
    private int position;
    private int phase_packet;
    private final int PHASE_packet_length = 0;
    private final int PHASE_block = 1;
    private SshCrypto crypto;
    private byte[] block;
    
    public SshPacket2(final SshCrypto _crypto) {
        this.packet_length_array = new byte[5];
        this.packet_length = 0;
        this.padlen = 0;
        this.crc_array = new byte[4];
        this.position = 0;
        this.phase_packet = 0;
        this.crypto = null;
        this.position = 0;
        this.phase_packet = 0;
        this.crypto = _crypto;
    }
    
    public SshPacket2(final byte newType) {
        this.packet_length_array = new byte[5];
        this.packet_length = 0;
        this.padlen = 0;
        this.crc_array = new byte[4];
        this.position = 0;
        this.phase_packet = 0;
        this.crypto = null;
        this.setType(newType);
    }
    
    public BigInteger getMpInt() {
        return new BigInteger(1, this.getBytes(this.getInt32()));
    }
    
    public void putMpInt(final BigInteger bi) {
        byte[] mpbytes;
        int i;
        for (mpbytes = bi.toByteArray(), i = 0; i < mpbytes.length && mpbytes[i] == 0; ++i) {}
        final byte[] xbytes = new byte[mpbytes.length - i];
        System.arraycopy(mpbytes, i, xbytes, 0, mpbytes.length - i);
        this.putInt32(mpbytes.length - i);
        this.putBytes(xbytes);
    }
    
    public byte[] getPayLoad(final SshCrypto xcrypt, final long seqnr) throws IOException {
        final byte[] data = this.getData();
        final int blocksize = 8;
        this.packet_length = 6;
        if (data != null) {
            this.packet_length += data.length;
        }
        int padlen = blocksize - this.packet_length % blocksize;
        if (padlen < 4) {
            padlen += blocksize;
        }
        final byte[] padding = new byte[padlen];
        System.out.println("packet length is " + this.packet_length + ", padlen is " + padlen);
        if (xcrypt == null) {
            for (int i = 0; i < padlen; ++i) {
                padding[i] = 0;
            }
        }
        else {
            for (int i = 0; i < padlen; ++i) {
                padding[i] = SshMisc.getNotZeroRandomByte();
            }
        }
        byte[] block = new byte[this.packet_length + padlen];
        final int xlen = padlen + this.packet_length - 4;
        block[3] = (byte)(xlen & 0xFF);
        block[2] = (byte)(xlen >> 8 & 0xFF);
        block[1] = (byte)(xlen >> 16 & 0xFF);
        block[0] = (byte)(xlen >> 24 & 0xFF);
        block[4] = (byte)padlen;
        block[5] = this.getType();
        System.arraycopy(data, 0, block, 6, data.length);
        System.arraycopy(padding, 0, block, 6 + data.length, padlen);
        byte[] md5sum;
        if (xcrypt != null) {
            MessageDigest md5 = null;
            try {
                md5 = MessageDigest.getInstance("MD5");
            }
            catch (NoSuchAlgorithmException e) {
                System.err.println("SshPacket2: unable to load message digest algorithm: " + e);
            }
            final byte[] seqint = { (byte)(seqnr >> 24 & 0xFFL), (byte)(seqnr >> 16 & 0xFFL), (byte)(seqnr >> 8 & 0xFFL), (byte)(seqnr & 0xFFL) };
            md5.update(seqint, 0, 4);
            md5.update(block, 0, block.length);
            md5sum = md5.digest();
        }
        else {
            md5sum = new byte[0];
        }
        if (xcrypt != null) {
            block = xcrypt.encrypt(block);
        }
        final byte[] sendblock = new byte[block.length + md5sum.length];
        System.arraycopy(block, 0, sendblock, 0, block.length);
        System.arraycopy(md5sum, 0, sendblock, block.length, md5sum.length);
        return sendblock;
    }
    
    public byte[] addPayload(final byte[] buff) {
        int boffset = 0;
        byte[] newbuf = null;
        int hmaclen = 0;
        if (this.crypto != null) {
            hmaclen = 16;
        }
        System.out.println("addPayload2 " + buff.length);
        while (boffset < buff.length) {
            switch (this.phase_packet) {
                case 0: {
                    this.packet_length_array[this.position++] = buff[boffset++];
                    if (this.position == 5) {
                        this.packet_length = (this.packet_length_array[3] & 0xFF) + ((this.packet_length_array[2] & 0xFF) << 8) + ((this.packet_length_array[1] & 0xFF) << 16) + ((this.packet_length_array[0] & 0xFF) << 24);
                        this.padlen = this.packet_length_array[4];
                        this.position = 0;
                        System.out.println("SSH2: packet length " + this.packet_length);
                        System.out.println("SSH2: padlen " + this.padlen);
                        this.packet_length += hmaclen;
                        this.block = new byte[this.packet_length - 1];
                        ++this.phase_packet;
                        continue;
                    }
                    continue;
                }
                case 1: {
                    if (this.position < this.block.length) {
                        int amount = buff.length - boffset;
                        if (amount > 0) {
                            if (amount > this.block.length - this.position) {
                                amount = this.block.length - this.position;
                            }
                            System.arraycopy(buff, boffset, this.block, this.position, amount);
                            boffset += amount;
                            this.position += amount;
                        }
                    }
                    if (this.position == this.block.length) {
                        if (buff.length > boffset) {
                            newbuf = new byte[buff.length - boffset];
                            System.arraycopy(buff, boffset, newbuf, 0, buff.length - boffset);
                        }
                        byte[] decryptedBlock = new byte[this.block.length - hmaclen];
                        this.packet_length -= hmaclen;
                        System.arraycopy(this.block, 0, decryptedBlock, 0, this.block.length - hmaclen);
                        if (this.crypto != null) {
                            decryptedBlock = this.crypto.decrypt(decryptedBlock);
                        }
                        for (int i = 0; i < decryptedBlock.length; ++i) {
                            System.out.print(" " + decryptedBlock[i]);
                        }
                        System.out.println("");
                        this.setType(decryptedBlock[0]);
                        System.err.println("Packet type: " + this.getType());
                        System.err.println("Packet len: " + this.packet_length);
                        if (this.packet_length > this.padlen + 1 + 1) {
                            final byte[] data = new byte[this.packet_length - 1 - this.padlen - 1];
                            System.arraycopy(decryptedBlock, 1, data, 0, data.length);
                            this.putData(data);
                        }
                        else {
                            this.putData(null);
                        }
                        return newbuf;
                    }
                    continue;
                }
            }
        }
        return null;
    }
}
