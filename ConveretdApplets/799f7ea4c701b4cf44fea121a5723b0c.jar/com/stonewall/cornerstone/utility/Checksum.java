// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import org.xmodel.Element;
import java.io.IOException;
import java.util.zip.CRC32;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import org.xmodel.log.Log;

public class Checksum
{
    private String value;
    protected static final Log log;
    
    static {
        log = Log.getLog(Checksum.class);
    }
    
    public Checksum(final String input) {
        this(new ByteArrayInputStream(input.getBytes()));
    }
    
    public Checksum(final InputStream input) {
        this.generate(input);
    }
    
    public Checksum(final IModelObject e) {
        this.setValue(Xlate.get(e, (String)null));
    }
    
    private void generate(final InputStream is) {
        try {
            final java.util.zip.Checksum checksum = new CRC32();
            final byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = is.read(bytes)) >= 0) {
                checksum.update(bytes, 0, len);
            }
            is.close();
            this.value = String.valueOf(checksum.getValue());
        }
        catch (IOException ioe) {
            Checksum.log.error(this, ioe);
        }
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(final String s) {
        this.value = s;
    }
    
    public IModelObject getRoot() {
        final IModelObject e = new Element("en:checksum");
        e.setValue(String.valueOf(this.value));
        return e;
    }
    
    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
