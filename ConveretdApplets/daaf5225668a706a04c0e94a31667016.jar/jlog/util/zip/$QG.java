// 
// Decompiled by Procyon v0.5.30
// 

package jlog.util.zip;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.util.Dictionary;
import java.io.OutputStream;

class $QG extends OutputStream
{
    String $NE;
    Dictionary $FG;
    ByteArrayOutputStream $RG;
    
    $QG(final Dictionary $fg, final String $ne) {
        this.$FG = $fg;
        this.$NE = $ne;
        this.$RG = new ByteArrayOutputStream(8192);
    }
    
    public void close() throws IOException {
        if (this.$FG != null && this.$RG != null) {
            this.$FG.put(this.$NE, this.$RG.toByteArray());
            return;
        }
        throw new IOException();
    }
    
    public void flush() {
    }
    
    public void write(final int n) throws IOException {
        this.$RG.write(n);
    }
    
    public void write(final byte[] array) throws IOException {
        this.$RG.write(array);
    }
    
    public void write(final byte[] array, final int n, final int n2) throws IOException {
        this.$RG.write(array, n, n2);
    }
}
