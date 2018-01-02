// 
// Decompiled by Procyon v0.5.30
// 

package ru.zhuk.graphics.codec;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Enumeration;

class d implements Enumeration
{
    InputStream c;
    i a;
    boolean b;
    
    public d(final i a) {
        this.b = true;
        this.a = a;
        this.c = a.B;
    }
    
    public Object nextElement() {
        this.b = false;
        return new h(this.c, this.a.H);
    }
    
    public boolean hasMoreElements() {
        final DataInputStream dataInputStream = new DataInputStream(this.c);
        if (!this.b) {
            try {
                dataInputStream.readInt();
                this.a.L = false;
                this.a.H = dataInputStream.readInt();
                this.a.z = dataInputStream.readInt();
            }
            catch (IOException ex) {
                return false;
            }
        }
        return this.a.z == 1229209940;
    }
}
