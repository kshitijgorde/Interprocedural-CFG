// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.io.IOException;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.DataOutput;

public final class dg implements DataOutput
{
    private DataOutputStream a;
    
    public dg() {
    }
    
    public dg(final OutputStream outputStream) {
        this.a = new DataOutputStream(outputStream);
    }
    
    public final void write(final byte[] array, final int n, final int n2) {
        try {
            this.a.write(array, n, n2);
        }
        catch (IOException ex) {
            try {
                this.a.close();
            }
            catch (IOException ex2) {}
        }
    }
    
    public final void write(final byte[] array) {
        this.write(array, 0, array.length);
    }
    
    public final void write(final int n) {
        try {
            this.a.write(n);
        }
        catch (IOException ex) {
            try {
                this.a.close();
            }
            catch (IOException ex2) {}
        }
    }
    
    public final void writeUTF(final String s) {
        try {
            this.a.writeUTF(s);
        }
        catch (IOException ex) {
            try {
                this.a.close();
            }
            catch (IOException ex2) {}
        }
    }
    
    public final void writeShort(final int n) {
        try {
            this.a.writeShort(n);
        }
        catch (IOException ex) {
            try {
                this.a.close();
            }
            catch (IOException ex2) {}
        }
    }
    
    public final void writeLong(final long n) {
        try {
            this.a.writeLong(n);
        }
        catch (IOException ex) {
            try {
                this.a.close();
            }
            catch (IOException ex2) {}
        }
    }
    
    public final void writeInt(final int n) {
        try {
            this.a.writeInt(n);
        }
        catch (IOException ex) {
            try {
                this.a.close();
            }
            catch (IOException ex2) {}
        }
    }
    
    public final void writeBytes(final String s) {
        try {
            this.a.writeBytes(s);
        }
        catch (IOException ex) {
            try {
                this.a.close();
            }
            catch (IOException ex2) {}
        }
    }
    
    public final void writeBoolean(final boolean b) {
        try {
            this.a.writeBoolean(b);
        }
        catch (IOException ex) {
            try {
                this.a.close();
            }
            catch (IOException ex2) {}
        }
    }
    
    public final void writeChar(final int n) {
        try {
            this.a.writeChar(n);
        }
        catch (IOException ex) {
            try {
                this.a.close();
            }
            catch (IOException ex2) {}
        }
    }
    
    public final void writeByte(final int n) {
        try {
            this.a.writeByte(n);
        }
        catch (IOException ex) {
            try {
                this.a.close();
            }
            catch (IOException ex2) {}
        }
    }
    
    public final void writeChars(final String s) {
        try {
            this.a.writeChars(s);
        }
        catch (IOException ex) {
            try {
                this.a.close();
            }
            catch (IOException ex2) {}
        }
    }
    
    public final void writeFloat(final float n) {
        try {
            this.a.writeFloat(n);
        }
        catch (IOException ex) {
            try {
                this.a.close();
            }
            catch (IOException ex2) {}
        }
    }
    
    public final void writeDouble(final double n) {
        try {
            this.a.writeDouble(n);
        }
        catch (IOException ex) {
            try {
                this.a.close();
            }
            catch (IOException ex2) {}
        }
    }
}
