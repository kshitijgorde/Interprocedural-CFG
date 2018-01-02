// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.ssh;

import java.io.IOException;

class SshPacket
{
    private static final boolean debug = false;
    private byte[] packet_length_array;
    private int packet_length;
    private byte[] padding;
    private byte packet_type;
    private short last_packet_type;
    private byte[] data;
    private byte[] crc_array;
    private byte[] block;
    private byte[] encryptedBlock;
    private byte[] decryptedBlock;
    public boolean toBeFinished;
    public byte[] unfinishedBuffer;
    public int positionInUnfinishedBuffer;
    private int position;
    private int phase_packet;
    private final int PHASE_packet_length = 0;
    private final int PHASE_block = 1;
    
    public SshPacket(final byte newType, final byte[] newData, final boolean encryption, final SshCrypto crypto) throws IOException {
        this.packet_length_array = new byte[4];
        this.packet_length = 0;
        this.padding = null;
        this.data = null;
        this.crc_array = new byte[4];
        this.block = null;
        this.encryptedBlock = null;
        this.decryptedBlock = null;
        this.toBeFinished = false;
        this.position = 0;
        this.phase_packet = 0;
        this.data = newData;
        this.packet_type = newType;
        if (this.data != null) {
            this.packet_length = this.data.length + 5;
        }
        else {
            this.packet_length = 5;
        }
        this.packet_length_array[3] = (byte)(this.packet_length & 0xFF);
        this.packet_length_array[2] = (byte)(this.packet_length >> 8 & 0xFF);
        this.packet_length_array[1] = (byte)(this.packet_length >> 16 & 0xFF);
        this.packet_length_array[0] = (byte)(this.packet_length >> 24 & 0xFF);
        this.padding = new byte[8 - this.packet_length % 8];
        if (!encryption) {
            for (int i = 0; i < this.padding.length; ++i) {
                this.padding[i] = 0;
            }
        }
        else {
            for (int j = 0; j < this.padding.length; ++j) {
                this.padding[j] = SshMisc.getNotZeroRandomByte();
            }
        }
        final byte[] tempByte = new byte[this.packet_length + this.padding.length - 4];
        int offset = 0;
        for (int k = 0; k < this.padding.length; ++k) {
            tempByte[offset++] = this.padding[k];
        }
        tempByte[offset++] = this.packet_type;
        if (this.packet_length > 5) {
            for (int l = 0; l < this.data.length; ++l) {
                tempByte[offset++] = this.data[l];
            }
        }
        long crc = 0L;
        crc = SshMisc.crc32(tempByte, tempByte.length);
        this.crc_array[3] = (byte)(crc & 0xFFL);
        this.crc_array[2] = (byte)(crc >> 8 & 0xFFL);
        this.crc_array[1] = (byte)(crc >> 16 & 0xFFL);
        this.crc_array[0] = (byte)(crc >> 24 & 0xFFL);
        this.setBlock();
        if (encryption) {
            crypto.encrypt(this.block);
        }
        this.encryptedBlock = this.block;
    }
    
    private void setBlock() throws IOException {
        this.block = new byte[this.packet_length + this.padding.length];
        int blockOffset = 0;
        for (int i = 0; i < this.padding.length; ++i) {
            this.block[blockOffset++] = this.padding[i];
        }
        this.block[blockOffset++] = this.packet_type;
        if (this.packet_length > 5) {
            for (int j = 0; j < this.data.length; ++j) {
                this.block[blockOffset++] = this.data[j];
            }
        }
        for (int k = 0; k < this.crc_array.length; ++k) {
            this.block[blockOffset++] = this.crc_array[k];
        }
    }
    
    public byte[] getBytes() throws IOException {
        return SshMisc.addArrayOfBytes(this.packet_length_array, this.encryptedBlock);
    }
    
    public byte[] getData() throws IOException {
        return this.data;
    }
    
    public byte getType() throws IOException {
        return this.packet_type;
    }
    
    public SshPacket getPacketfromBytes(final byte[] buff, final int offset, final int count, final boolean encryption, final SshCrypto crypto) throws IOException {
        int boffset = offset;
        while (boffset < count) {
            final byte b = buff[boffset++];
            switch (this.phase_packet) {
                case 0: {
                    this.packet_length_array[this.position] = b;
                    if (++this.position >= 4) {
                        this.packet_length = (this.packet_length_array[3] & 0xFF) + ((this.packet_length_array[2] & 0xFF) << 8) + ((this.packet_length_array[1] & 0xFF) << 16) + ((this.packet_length_array[0] & 0xFF) << 24);
                        this.position = 0;
                        ++this.phase_packet;
                        continue;
                    }
                    continue;
                }
                case 1: {
                    if (this.position == 0) {
                        final int lengthBlock = 8 * (this.packet_length / 8 + 1);
                        this.block = new byte[lengthBlock];
                    }
                    this.block[this.position] = b;
                    if (++this.position < this.block.length) {
                        continue;
                    }
                    if (count > boffset) {
                        this.toBeFinished = true;
                        this.unfinishedBuffer = buff;
                        this.positionInUnfinishedBuffer = boffset;
                    }
                    else {
                        this.toBeFinished = false;
                    }
                    this.position = 0;
                    this.phase_packet = 0;
                    if (encryption) {
                        crypto.decrypt(this.block);
                    }
                    this.decryptedBlock = this.block;
                    this.setPacketFromDecryptedBlock();
                    if (!this.checkCrc()) {
                        System.err.println("SshPacket: Crc Error !!");
                        return null;
                    }
                    return this;
                }
                default: {
                    continue;
                }
            }
        }
        this.toBeFinished = false;
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
    
    private void setPacketFromDecryptedBlock() throws IOException {
        int blockOffset = 0;
        final int padding_length = 8 - this.packet_length % 8;
        this.padding = new byte[padding_length];
        if (this.decryptedBlock.length != padding_length + this.packet_length) {
            System.out.println("???");
        }
        for (int i = 0; i < this.padding.length; ++i) {
            this.padding[i] = this.decryptedBlock[blockOffset++];
        }
        this.packet_type = this.decryptedBlock[blockOffset++];
        if (this.packet_length > 5) {
            this.data = new byte[this.packet_length - 5];
            for (int j = 0; j < this.data.length; ++j) {
                this.data[j] = this.decryptedBlock[blockOffset++];
            }
        }
        else {
            this.data = null;
        }
        for (int j = 0; j < this.crc_array.length; ++j) {
            this.crc_array[j] = this.decryptedBlock[blockOffset++];
        }
    }
}
