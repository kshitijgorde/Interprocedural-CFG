// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net.tftp;

import java.net.DatagramPacket;
import java.net.InetAddress;

public abstract class TFTPRequestPacket extends TFTPPacket
{
    static final String[] _modeStrings;
    static final byte[][] _modeBytes;
    int _mode;
    String _filename;
    
    TFTPRequestPacket(final InetAddress destination, final int port, final int type, final String filename, final int mode) {
        super(type, destination, port);
        this._filename = filename;
        this._mode = mode;
    }
    
    TFTPRequestPacket(final int type, final DatagramPacket datagram) throws TFTPPacketException {
        super(type, datagram.getAddress(), datagram.getPort());
        final byte[] data = datagram.getData();
        if (this.getType() != data[1]) {
            throw new TFTPPacketException("TFTP operator code does not match type.");
        }
        final StringBuffer buffer = new StringBuffer();
        int index;
        int length;
        for (index = 2, length = datagram.getLength(); index < length && data[index] != 0; ++index) {
            buffer.append((char)data[index]);
        }
        this._filename = buffer.toString();
        if (index >= length) {
            throw new TFTPPacketException("Bad filename and mode format.");
        }
        buffer.setLength(0);
        ++index;
        while (index < length && data[index] != 0) {
            buffer.append((char)data[index]);
            ++index;
        }
        final String mode = buffer.toString().toLowerCase();
        for (length = TFTPRequestPacket._modeStrings.length, index = 0; index < length; ++index) {
            if (mode.equals(TFTPRequestPacket._modeStrings[index])) {
                this._mode = index;
                break;
            }
        }
        if (index >= length) {
            throw new TFTPPacketException("Unrecognized TFTP transfer mode: " + mode);
        }
    }
    
    final DatagramPacket _newDatagram(final DatagramPacket datagram, final byte[] data) {
        final int fileLength = this._filename.length();
        final int modeLength = TFTPRequestPacket._modeBytes[this._mode].length;
        data[0] = 0;
        data[1] = (byte)this._type;
        System.arraycopy(this._filename.getBytes(), 0, data, 2, fileLength);
        data[fileLength + 2] = 0;
        System.arraycopy(TFTPRequestPacket._modeBytes[this._mode], 0, data, fileLength + 3, modeLength);
        datagram.setAddress(this._address);
        datagram.setPort(this._port);
        datagram.setData(data);
        datagram.setLength(fileLength + modeLength + 3);
        return datagram;
    }
    
    public final DatagramPacket newDatagram() {
        final int fileLength = this._filename.length();
        final int modeLength = TFTPRequestPacket._modeBytes[this._mode].length;
        final byte[] data = new byte[fileLength + modeLength + 4];
        data[0] = 0;
        data[1] = (byte)this._type;
        System.arraycopy(this._filename.getBytes(), 0, data, 2, fileLength);
        data[fileLength + 2] = 0;
        System.arraycopy(TFTPRequestPacket._modeBytes[this._mode], 0, data, fileLength + 3, modeLength);
        return new DatagramPacket(data, data.length, this._address, this._port);
    }
    
    public final int getMode() {
        return this._mode;
    }
    
    public final String getFilename() {
        return this._filename;
    }
    
    static {
        _modeStrings = new String[] { "netascii", "octet" };
        _modeBytes = new byte[][] { { 110, 101, 116, 97, 115, 99, 105, 105, 0 }, { 111, 99, 116, 101, 116, 0 } };
    }
}
