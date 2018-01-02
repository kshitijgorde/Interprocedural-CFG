// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.ssh;

import java.math.BigInteger;

abstract class SshPacket
{
    protected byte[] byteArray;
    protected int offset;
    private boolean finished;
    private byte packet_type;
    
    public SshPacket() {
        this.byteArray = new byte[0];
        this.finished = false;
    }
    
    public byte[] getData() {
        return this.byteArray;
    }
    
    public void putData(final byte[] data) {
        this.byteArray = data;
        this.offset = 0;
        this.finished = true;
    }
    
    public boolean isFinished() {
        return this.finished;
    }
    
    public abstract byte[] addPayload(final byte[] p0);
    
    public byte getType() {
        return this.packet_type;
    }
    
    public void setType(final byte ntype) {
        this.packet_type = ntype;
    }
    
    public abstract void putMpInt(final BigInteger p0);
    
    public int getInt32() {
        short d0 = this.byteArray[this.offset++];
        short d2 = this.byteArray[this.offset++];
        short d3 = this.byteArray[this.offset++];
        short d4 = this.byteArray[this.offset++];
        if (d0 < 0) {
            d0 += 256;
        }
        if (d2 < 0) {
            d2 += 256;
        }
        if (d3 < 0) {
            d3 += 256;
        }
        if (d4 < 0) {
            d4 += 256;
        }
        return (d0 << 24) + (d2 << 16) + (d3 << 8) + d4;
    }
    
    public int getInt16() {
        short d0 = this.byteArray[this.offset++];
        short d2 = this.byteArray[this.offset++];
        if (d0 < 0) {
            d0 += 256;
        }
        if (d2 < 0) {
            d2 += 256;
        }
        return (d0 << 8) + d2;
    }
    
    public String getString() {
        final int length = this.getInt32();
        String str = "";
        for (int i = 0; i < length; ++i) {
            if (this.byteArray[this.offset] >= 0) {
                str = String.valueOf(str) + (char)this.byteArray[this.offset++];
            }
            else {
                str = String.valueOf(str) + (char)(256 + this.byteArray[this.offset++]);
            }
        }
        return str;
    }
    
    public byte getByte() {
        return this.byteArray[this.offset++];
    }
    
    public byte[] getBytes(final int cnt) {
        final byte[] bytes = new byte[cnt];
        System.arraycopy(this.byteArray, this.offset, bytes, 0, cnt);
        this.offset += cnt;
        return bytes;
    }
    
    private void grow(final int howmuch) {
        final byte[] value = new byte[this.byteArray.length + howmuch];
        System.arraycopy(this.byteArray, 0, value, 0, this.byteArray.length);
        this.byteArray = value;
    }
    
    public void putInt16(final int xint) {
        final int boffset = this.byteArray.length;
        this.grow(2);
        this.byteArray[boffset + 1] = (byte)(xint & 0xFF);
        this.byteArray[boffset] = (byte)(xint >> 8 & 0xFF);
    }
    
    public void putInt32(final int xint) {
        final int boffset = this.byteArray.length;
        this.grow(4);
        this.byteArray[boffset + 3] = (byte)(xint & 0xFF);
        this.byteArray[boffset + 2] = (byte)(xint >> 8 & 0xFF);
        this.byteArray[boffset + 1] = (byte)(xint >> 16 & 0xFF);
        this.byteArray[boffset + 0] = (byte)(xint >> 24 & 0xFF);
    }
    
    public void putByte(final byte xbyte) {
        this.grow(1);
        this.byteArray[this.byteArray.length - 1] = xbyte;
    }
    
    public void putBytes(final byte[] bytes) {
        final int oldlen = this.byteArray.length;
        this.grow(bytes.length);
        System.arraycopy(bytes, 0, this.byteArray, oldlen, bytes.length);
    }
    
    public void putString(final String str) {
        this.putInt32(str.length());
        this.putBytes(str.getBytes());
    }
}
