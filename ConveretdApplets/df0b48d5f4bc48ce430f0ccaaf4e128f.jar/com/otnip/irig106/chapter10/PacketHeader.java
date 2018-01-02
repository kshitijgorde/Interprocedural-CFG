// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter10;

import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.io.InputStream;

public class PacketHeader
{
    private final byte[] packetHeaderData;
    
    public PacketHeader() {
        this.packetHeaderData = new byte[24];
    }
    
    public PacketHeader(final InputStream is) throws Exception {
        this.packetHeaderData = new byte[24];
        this.read(is);
    }
    
    public PacketHeader(final ByteBuffer byteBuffer) throws Exception {
        this.packetHeaderData = new byte[24];
        this.read(byteBuffer);
    }
    
    public void read(final InputStream is) throws Exception {
        final int N = is.read(this.packetHeaderData);
        if (N != this.packetHeaderData.length) {
            throw new Exception("Error Reading Packet Header");
        }
    }
    
    public void read(final ByteBuffer byteBuffer) throws Exception {
        byteBuffer.get(this.packetHeaderData);
    }
    
    public void read(final RandomAccessFile ras) throws Exception {
        ras.readFully(this.packetHeaderData);
    }
    
    public void write(final OutputStream os) throws Exception {
        os.write(this.packetHeaderData);
    }
    
    public String toString() {
        final StringBuilder string = new StringBuilder();
        final Class thisClass = this.getClass();
        final Field[] fields = thisClass.getDeclaredFields();
        string.append("Packet Header\n");
        string.append("\tSync Pattern:  " + this.getSyncPattern() + "\n");
        string.append("\tChannel ID:  " + this.getChannelID() + " (" + Packet.PacketType.valueOf(this.getChannelID()) + ")\n");
        string.append("\tPacket Length: " + this.getPacketLength() + "\n");
        string.append("\tData Length: " + this.getDataLength() + "\n");
        string.append("\tRelative Time Counter:  " + this.getRelativeTimeCounter() + "\n");
        string.append("\tFlags:  " + this.getFlags() + "\n");
        string.append("\t\tHas Secondary Header:  " + this.hasSecondaryHeader() + "\n");
        string.append("\t\tHas Time Counter Sync Error Header:  " + this.hasTimeCounterSyncError() + "\n");
        string.append("\t\tHas Data CRC:  " + this.hasDataCRC());
        try {
            string.append(" (" + this.getDataCRCLength() + " Bytes)");
        }
        catch (Exception ex) {}
        string.append("\n");
        string.append("\t\tHas Data Overflow:  " + this.hasDataOverflow() + "\n");
        return string.toString();
    }
    
    public int getSyncPattern() {
        return (this.packetHeaderData[1] & 0xFF) << 8 | (this.packetHeaderData[0] & 0xFF);
    }
    
    public int getChannelID() {
        return (this.packetHeaderData[3] & 0xFF) << 8 | (this.packetHeaderData[2] & 0xFF);
    }
    
    public int getPacketLength() {
        return (this.packetHeaderData[7] & 0xFF) << 24 | (this.packetHeaderData[6] & 0xFF) << 16 | (this.packetHeaderData[5] & 0xFF) << 8 | (this.packetHeaderData[4] & 0xFF);
    }
    
    public void setPacketLength(final int packetLength) {
        this.packetHeaderData[7] = (byte)(packetLength >> 24 & 0xFF);
        this.packetHeaderData[6] = (byte)(packetLength >> 16 & 0xFF);
        this.packetHeaderData[5] = (byte)(packetLength >> 8 & 0xFF);
        this.packetHeaderData[4] = (byte)(packetLength & 0xFF);
    }
    
    public int getDataLength() {
        return (this.packetHeaderData[11] & 0xFF) << 24 | (this.packetHeaderData[10] & 0xFF) << 16 | (this.packetHeaderData[9] & 0xFF) << 8 | (this.packetHeaderData[8] & 0xFF);
    }
    
    public void setDataLength(final int dataLength) {
        this.packetHeaderData[11] = (byte)(dataLength >> 24 & 0xFF);
        this.packetHeaderData[10] = (byte)(dataLength >> 16 & 0xFF);
        this.packetHeaderData[9] = (byte)(dataLength >> 8 & 0xFF);
        this.packetHeaderData[8] = (byte)(dataLength & 0xFF);
    }
    
    public int getHeaderVersion() {
        return this.packetHeaderData[12] & 0xFF;
    }
    
    public int getSequenceNumber() {
        return this.packetHeaderData[13] & 0xFF;
    }
    
    public int getFlags() {
        return this.packetHeaderData[14] & 0xFF;
    }
    
    public Packet.PacketType getDataType() {
        return Packet.PacketType.valueOf(this.packetHeaderData[15] & 0xFF);
    }
    
    public int getDataTypeID() {
        return this.packetHeaderData[15] & 0xFF;
    }
    
    public long getRelativeTimeCounter() {
        long result = (this.packetHeaderData[21] & 0xFFL) << 40;
        result |= (this.packetHeaderData[20] & 0xFFL) << 32;
        result |= (this.packetHeaderData[19] & 0xFFL) << 24;
        result |= (this.packetHeaderData[18] & 0xFFL) << 16;
        result |= (this.packetHeaderData[17] & 0xFFL) << 8;
        result |= (this.packetHeaderData[16] & 0xFFL);
        return result;
    }
    
    public int getHeaderChecksum() {
        return (this.packetHeaderData[23] & 0xFF) << 8 | (this.packetHeaderData[22] & 0xFF);
    }
    
    public boolean hasSecondaryHeader() {
        return (this.getFlags() & 0x80) != 0x0;
    }
    
    public boolean hasTimeCounterSyncError() {
        return (this.getFlags() & 0x20) != 0x0;
    }
    
    public boolean hasDataOverflow() {
        return (this.getFlags() & 0x10) != 0x0;
    }
    
    public boolean hasDataCRC() {
        return (this.getFlags() & 0x3) != 0x0;
    }
    
    public int getDataCRC() {
        return this.getFlags() & 0x3;
    }
    
    public void setDataCRC(final int dataCRC) throws Exception {
        final int before = this.getDataCRCLength();
        this.packetHeaderData[14] = (byte)((this.packetHeaderData[14] & 0xFC) | (dataCRC & 0x3));
        final int after = this.getDataCRCLength();
        final int diff = after - before;
        this.setPacketLength(this.getPacketLength() + diff);
    }
    
    public int getDataCRCLength() throws Exception {
        int result = 0;
        switch (this.getDataCRC()) {
            case 0: {
                result = 0;
                break;
            }
            case 1: {
                result = 1;
                break;
            }
            case 2: {
                result = 2;
                break;
            }
            case 3: {
                result = 4;
                break;
            }
            default: {
                throw new Exception("Don't Understand Data CRC Length Of " + this.getDataCRC());
            }
        }
        return result;
    }
    
    public boolean hasValidChecksum() throws Exception {
        final ByteArrayInputStream bais = new ByteArrayInputStream(this.packetHeaderData);
        final DataInputStream dis = new DataInputStream(bais);
        short calculatedChecksum = 0;
        for (int i = 0; i < 11; ++i) {
            calculatedChecksum += Short.reverseBytes(dis.readShort());
        }
        return calculatedChecksum == Short.reverseBytes(dis.readShort());
    }
}
