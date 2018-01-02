// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.util.tunnel3;

import java.io.IOException;
import java.io.InputStream;

public class b4 extends InputStream
{
    private byte[] a;
    private int b;
    private final b3 c;
    
    public int available() {
        return (this.a != null) ? (this.a.length - 1 - this.b) : 0;
    }
    
    public int read() throws IOException {
        while (true) {
            if (this.a != null) {
                if (this.b < this.a.length - 1) {
                    return this.a[this.b++] & 0xFF;
                }
                if (this.a[this.b] != 0) {
                    return -1;
                }
            }
            this.a = this.c.a.a().a();
            this.b = 0;
        }
    }
    
    b4(final b3 c) {
        this.c = c;
    }
}
