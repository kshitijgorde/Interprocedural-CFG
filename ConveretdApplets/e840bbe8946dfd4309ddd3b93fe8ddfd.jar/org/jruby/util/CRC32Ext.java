// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.lang.reflect.Field;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class CRC32Ext implements Checksum
{
    private int crc;
    private final CRC32 intern;
    private static final Field intern_crc;
    
    public CRC32Ext() {
        this(1);
    }
    
    public CRC32Ext(final int crc) {
        this.crc = crc;
        this.intern = new CRC32();
        this.setCRCRef(this.crc);
    }
    
    public void setAdler(final int crc) {
        this.setCRCRef(this.crc = crc);
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
        this.crc = 1;
    }
    
    public long getValue() {
        return this.intern.getValue();
    }
    
    private void setCRCRef(final int val) {
        try {
            CRC32Ext.intern_crc.setInt(this.intern, val);
        }
        catch (IllegalAccessException e) {
            throw new IllegalStateException(e.toString());
        }
    }
    
    static {
        try {
            (intern_crc = CRC32.class.getDeclaredField("crc")).setAccessible(true);
        }
        catch (NoSuchFieldException nsfe) {
            throw new RuntimeException("This class have stopped working, it should be updated and FIXED now.");
        }
    }
}
