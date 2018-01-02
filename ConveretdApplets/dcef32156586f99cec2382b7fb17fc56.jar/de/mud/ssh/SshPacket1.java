// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.ssh;

import java.math.BigInteger;

public class SshPacket1 extends SshPacket
{
    private static final boolean debug = false;
    private byte[] packet_length_array;
    private int packet_length;
    private byte[] padding;
    private byte[] crc_array;
    private byte[] block;
    private byte[] encryptedBlock;
    private byte[] decryptedBlock;
    private SshCrypto crypto;
    private int position;
    private int phase_packet;
    private final int PHASE_packet_length = 0;
    private final int PHASE_block = 1;
    
    public SshPacket1(final SshCrypto _crypto) {
        this.packet_length_array = new byte[4];
        this.packet_length = 0;
        this.padding = null;
        this.crc_array = new byte[4];
        this.block = null;
        this.encryptedBlock = null;
        this.decryptedBlock = null;
        this.crypto = null;
        this.position = 0;
        this.phase_packet = 0;
        this.position = 0;
        this.phase_packet = 0;
        this.crypto = _crypto;
    }
    
    public SshPacket1(final byte newType) {
        this.packet_length_array = new byte[4];
        this.packet_length = 0;
        this.padding = null;
        this.crc_array = new byte[4];
        this.block = null;
        this.encryptedBlock = null;
        this.decryptedBlock = null;
        this.crypto = null;
        this.position = 0;
        this.phase_packet = 0;
        this.setType(newType);
    }
    
    public byte[] getMpInt() {
        return this.getBytes((this.getInt16() + 7) / 8);
    }
    
    public void putMpInt(final BigInteger bi) {
        byte[] mpbytes;
        int i;
        for (mpbytes = bi.toByteArray(), i = 0; i < mpbytes.length && mpbytes[i] == 0; ++i) {}
        final byte[] xbytes = new byte[mpbytes.length - i];
        System.arraycopy(mpbytes, i, xbytes, 0, mpbytes.length - i);
        this.putInt16(xbytes.length * 8);
        this.putBytes(xbytes);
    }
    
    byte[] getPayLoad(final SshCrypto crypto) {
        final byte[] data = this.getData();
        if (data != null) {
            this.packet_length = data.length + 5;
        }
        else {
            this.packet_length = 5;
        }
        this.packet_length_array[3] = (byte)(this.packet_length & 0xFF);
        this.packet_length_array[2] = (byte)(this.packet_length >> 8 & 0xFF);
        this.packet_length_array[1] = (byte)(this.packet_length >> 16 & 0xFF);
        this.packet_length_array[0] = (byte)(this.packet_length >> 24 & 0xFF);
        this.padding = new byte[8 - this.packet_length % 8];
        if (crypto == null) {
            for (int i = 0; i < this.padding.length; ++i) {
                this.padding[i] = 0;
            }
        }
        else {
            for (int i = 0; i < this.padding.length; ++i) {
                this.padding[i] = SshMisc.getNotZeroRandomByte();
            }
        }
        this.block = new byte[this.packet_length + this.padding.length];
        System.arraycopy(this.padding, 0, this.block, 0, this.padding.length);
        int offset = this.padding.length;
        this.block[offset++] = this.getType();
        if (this.packet_length > 5) {
            System.arraycopy(data, 0, this.block, offset, data.length);
            offset += data.length;
        }
        final long crc = SshMisc.crc32(this.block, offset);
        this.crc_array[3] = (byte)(crc & 0xFFL);
        this.crc_array[2] = (byte)(crc >> 8 & 0xFFL);
        this.crc_array[1] = (byte)(crc >> 16 & 0xFFL);
        this.crc_array[0] = (byte)(crc >> 24 & 0xFFL);
        System.arraycopy(this.crc_array, 0, this.block, offset, 4);
        if (crypto != null) {
            this.block = crypto.encrypt(this.block);
        }
        final byte[] full = new byte[this.block.length + 4];
        System.arraycopy(this.packet_length_array, 0, full, 0, 4);
        System.arraycopy(this.block, 0, full, 4, this.block.length);
        return full;
    }
    
    public byte[] addPayload(final byte[] buff) {
        int boffset = 0;
        byte[] newbuf = null;
        while (boffset < buff.length) {
            switch (this.phase_packet) {
                case 0: {
                    this.packet_length_array[this.position++] = buff[boffset++];
                    if (this.position >= 4) {
                        this.packet_length = (this.packet_length_array[3] & 0xFF) + ((this.packet_length_array[2] & 0xFF) << 8) + ((this.packet_length_array[1] & 0xFF) << 16) + ((this.packet_length_array[0] & 0xFF) << 24);
                        this.position = 0;
                        ++this.phase_packet;
                        this.block = new byte[8 * (this.packet_length / 8 + 1)];
                        continue;
                    }
                    continue;
                }
                case 1: {
                    if (this.block.length > this.position && boffset < buff.length) {
                        int amount = buff.length - boffset;
                        if (amount > this.block.length - this.position) {
                            amount = this.block.length - this.position;
                        }
                        System.arraycopy(buff, boffset, this.block, this.position, amount);
                        boffset += amount;
                        this.position += amount;
                    }
                    if (this.position == this.block.length) {
                        if (buff.length > boffset) {
                            newbuf = new byte[buff.length - boffset];
                            System.arraycopy(buff, boffset, newbuf, 0, buff.length - boffset);
                        }
                        int blockOffset = 0;
                        final int padding_length = 8 - this.packet_length % 8;
                        this.padding = new byte[padding_length];
                        if (this.crypto != null) {
                            this.decryptedBlock = this.crypto.decrypt(this.block);
                        }
                        else {
                            this.decryptedBlock = this.block;
                        }
                        if (this.decryptedBlock.length != padding_length + this.packet_length) {
                            System.out.println("???");
                        }
                        for (int i = 0; i < this.padding.length; ++i) {
                            this.padding[i] = this.decryptedBlock[blockOffset++];
                        }
                        this.setType(this.decryptedBlock[blockOffset++]);
                        byte[] data;
                        if (this.packet_length > 5) {
                            data = new byte[this.packet_length - 5];
                            System.arraycopy(this.decryptedBlock, blockOffset, data, 0, this.packet_length - 5);
                            blockOffset += this.packet_length - 5;
                        }
                        else {
                            data = null;
                        }
                        this.putData(data);
                        for (int j = 0; j < this.crc_array.length; ++j) {
                            this.crc_array[j] = this.decryptedBlock[blockOffset++];
                        }
                        if (!this.checkCrc()) {
                            System.err.println("SshPacket1: CRC wrong in received packet!");
                        }
                        return newbuf;
                    }
                    continue;
                }
            }
        }
        return null;
    }
    
    private boolean checkCrc() {
        final byte[] crc_arrayCheck = new byte[4];
        final long crcCheck = SshMisc.crc32(this.decryptedBlock, this.decryptedBlock.length - 4);
        crc_arrayCheck[3] = (byte)(crcCheck & 0xFFL);
        crc_arrayCheck[2] = (byte)(crcCheck >> 8 & 0xFFL);
        crc_arrayCheck[1] = (byte)(crcCheck >> 16 & 0xFFL);
        crc_arrayCheck[0] = (byte)(crcCheck >> 24 & 0xFFL);
        return crc_arrayCheck[3] == this.crc_array[3] && crc_arrayCheck[2] == this.crc_array[2] && crc_arrayCheck[1] == this.crc_array[1] && crc_arrayCheck[0] == this.crc_array[0];
    }
}
