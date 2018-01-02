// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.lang.reflect.Field;
import java.util.zip.Adler32;
import java.util.zip.Checksum;

public class Adler32Ext implements Checksum
{
    private int adler;
    private final Adler32 intern;
    private static final Field intern_adler;
    
    public Adler32Ext() {
        this(1);
    }
    
    public Adler32Ext(final int adler) {
        this.adler = adler;
        this.intern = new Adler32();
        this.setAdlerRef(this.adler);
    }
    
    public void setAdler(final int adler) {
        this.setAdlerRef(this.adler = adler);
    }
    
    public void update(final int b) {
        this.intern.update(b);
    }
    
    public void update(final byte[] b, final int off, final int len) {
        this.intern.update(b, off, len);
    }
    
    public void update(final byte[] b) {
        this.intern.update(b);
    }
    
    public void reset() {
        this.intern.reset();
        this.adler = 1;
    }
    
    public long getValue() {
        return this.intern.getValue();
    }
    
    private void setAdlerRef(final int val) {
        try {
            Adler32Ext.intern_adler.setInt(this.intern, val);
        }
        catch (IllegalAccessException e) {
            throw new IllegalStateException(e.toString());
        }
    }
    
    static {
        try {
            (intern_adler = Adler32.class.getDeclaredField("adler")).setAccessible(true);
        }
        catch (NoSuchFieldException nsfe) {
            throw new RuntimeException("This class have stopped working, it should be updated and FIXED now.");
        }
    }
}
