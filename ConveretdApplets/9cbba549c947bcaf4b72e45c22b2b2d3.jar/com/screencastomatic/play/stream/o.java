// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.stream;

import java.io.DataInputStream;
import java.util.HashMap;
import java.util.Map;

public class o
{
    long a;
    long b;
    long c;
    Map d;
    
    public o(final long a, final long b, final long c) {
        this.d = new HashMap();
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public static o a(final DataInputStream dataInputStream) {
        final o o = new o(dataInputStream.readLong(), dataInputStream.readLong(), dataInputStream.readLong());
        for (int int1 = dataInputStream.readInt(), i = 0; i < int1; ++i) {
            o.d.put(new h(dataInputStream.readByte(), dataInputStream.readByte()), dataInputStream.readLong());
        }
        return o;
    }
}
