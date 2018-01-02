// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.stream;

import java.io.DataInputStream;

public class b
{
    public long a;
    public short b;
    public short c;
    public CapturedMouse$ButtonAction d;
    public j e;
    public float f;
    
    public b(final long a, final short b, final short c) {
        this.d = CapturedMouse$ButtonAction.a;
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public b(final long a, final short b, final short c, final CapturedMouse$ButtonAction d, final j e) {
        this.d = CapturedMouse$ButtonAction.a;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    public b(final DataInputStream dataInputStream) {
        this.d = CapturedMouse$ButtonAction.a;
        this.a(dataInputStream);
    }
    
    public b a(final float n) {
        if (this.b == -1) {
            return this;
        }
        final b b = new b(this.a, (short)(this.b * n), (short)(this.c * n), this.d, this.e);
        b.f = this.f;
        return b;
    }
    
    public String toString() {
        return "positionMS: " + this.a + " x: " + this.b + " y:" + this.c + " ba:" + this.d;
    }
    
    private void a(final DataInputStream dataInputStream) {
        final byte byte1 = dataInputStream.readByte();
        this.d = CapturedMouse$ButtonAction.values()[dataInputStream.readByte()];
        dataInputStream.readByte();
        dataInputStream.readByte();
        this.a = dataInputStream.readInt();
        this.b = dataInputStream.readShort();
        this.c = dataInputStream.readShort();
        if (byte1 > 0) {
            this.e = new j(dataInputStream);
        }
    }
}
