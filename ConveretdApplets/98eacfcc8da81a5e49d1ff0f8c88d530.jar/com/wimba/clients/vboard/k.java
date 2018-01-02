// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.vboard;

import java.io.File;
import java.util.Vector;
import javax.swing.filechooser.FileFilter;

public final class k extends FileFilter
{
    private String a;
    private String b;
    private Vector c;
    private long d;
    
    public k(final long n) {
        this.a = null;
        this.c = new Vector();
        this.b = null;
        this.d = 10485760L;
    }
    
    public final void a(final String s) {
        this.c.add(s);
    }
    
    public final void b(final String b) {
        this.b = b;
    }
    
    public k(final String a, final String b) {
        this.a = a;
        this.b = b;
        this.c = null;
        this.d = -1L;
    }
    
    public final boolean accept(final File file) {
        if (file.isDirectory()) {
            return true;
        }
        if (this.d != -1L && file.length() > this.d) {
            return false;
        }
        if (this.a != null) {
            return file.getName().toLowerCase().endsWith(this.a.toLowerCase());
        }
        if (this.c != null && this.c.size() > 0) {
            for (int i = 0; i < this.c.size(); ++i) {
                if (file.getName().toLowerCase().endsWith(((String)this.c.get(i)).toLowerCase())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
    
    public final String getDescription() {
        return this.b;
    }
}
