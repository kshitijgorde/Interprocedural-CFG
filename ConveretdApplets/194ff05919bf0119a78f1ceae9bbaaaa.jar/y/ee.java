// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.io.OutputStream;
import java.io.DataOutputStream;

public final class ee implements bq
{
    private DataOutputStream a;
    
    public ee(final OutputStream outputStream) {
        this.a = new DataOutputStream(outputStream);
    }
    
    public final OutputStream a() {
        return this.a;
    }
    
    public final void write(final byte[] array) {
        this.a.write(array);
    }
    
    public final void write(final byte[] array, final int n, final int n2) {
        this.a.write(array, n, n2);
    }
    
    public final void write(final int n) {
        this.a.write(n);
    }
    
    public final void writeUTF(final String s) {
        this.a.writeUTF(s);
    }
    
    public final void writeShort(final int n) {
        this.a.writeShort(n);
    }
    
    public final void writeLong(final long n) {
        this.a.writeLong(n);
    }
    
    public final void writeInt(final int n) {
        this.a.writeInt(n);
    }
    
    public final void writeBytes(final String s) {
        this.a.writeBytes(s);
    }
    
    public final void writeBoolean(final boolean b) {
        this.a.writeBoolean(b);
    }
    
    public final void writeChar(final int n) {
        this.a.writeChar(n);
    }
    
    public final void writeByte(final int n) {
        this.a.writeByte(n);
    }
    
    public final void writeChars(final String s) {
        this.a.writeChars(s);
    }
    
    public final void writeFloat(final float n) {
        this.a.writeFloat(n);
    }
    
    public final void writeDouble(final double n) {
        this.a.writeDouble(n);
    }
    
    public final void a() {
        this.a.close();
    }
}
