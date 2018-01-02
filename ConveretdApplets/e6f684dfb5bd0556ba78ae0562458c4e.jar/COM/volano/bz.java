// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class bz implements cb, Cloneable
{
    public static boolean w;
    public static final int x = 1;
    public static final int y = 2;
    public static final int z = 3;
    public static final int eb = 4;
    private static final String[] fb;
    private int gb;
    private boolean hb;
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException ex) {
            return null;
        }
    }
    
    protected void p(final int gb) {
        this.gb = gb;
    }
    
    public int o() {
        return this.gb;
    }
    
    public void q() {
        this.hb = true;
    }
    
    public boolean md() {
        return this.hb;
    }
    
    public void og(final DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(this.gb);
        if (bz.w) {
            System.out.println("<== " + this.toString());
        }
    }
    
    public void pg(final DataInputStream dataInputStream) throws IOException {
        this.gb = dataInputStream.readInt();
        if (bz.w) {
            System.out.println("==> " + this.toString());
        }
    }
    
    public String toString() {
        return this.getClass().getName() + " " + bz.fb[this.gb - 1];
    }
    
    public bz() {
        this.gb = 1;
    }
    
    static {
        fb = new String[] { "request", "indication", "response", "confirm" };
    }
}
