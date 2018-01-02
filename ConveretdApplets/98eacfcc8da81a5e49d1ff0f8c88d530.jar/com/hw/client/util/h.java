// 
// Decompiled by Procyon v0.5.30
// 

package com.hw.client.util;

import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;

public final class h extends f
{
    private final String a;
    private final Exception b;
    
    public h(final e e, final int n, final String s, final Exception ex) {
        super(e);
        this.a = new String(s);
        if (ex != null) {
            this.b = new Exception(ex.toString());
            return;
        }
        this.b = null;
    }
    
    public final String toString() {
        final StringBuffer sb = new StringBuffer(this.a);
        if (this.b != null) {
            final StringWriter stringWriter = new StringWriter();
            this.b.printStackTrace(new PrintWriter(stringWriter));
            sb.append("\n").append(stringWriter.toString());
        }
        return sb.toString();
    }
}
