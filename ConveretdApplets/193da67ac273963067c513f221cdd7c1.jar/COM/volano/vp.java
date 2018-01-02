// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class vp implements vq, Cloneable
{
    public static boolean a;
    private static long b;
    private static long c;
    private static final String[] d;
    public int e;
    public boolean f;
    
    public long a() {
        return vp.b;
    }
    
    public long b() {
        return vp.c;
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException ex) {
            return null;
        }
    }
    
    public int c() {
        return 101;
    }
    
    public void d() {
        this.f = true;
    }
    
    public void a(final DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(this.e);
        if (vp.a) {
            System.out.println("<== " + this.toString());
        }
    }
    
    public void a(final DataInputStream dataInputStream) throws IOException {
        this.e = dataInputStream.readInt();
        if (vp.a) {
            System.out.println("==> " + this.toString());
        }
    }
    
    public String toString() {
        return String.valueOf(this.getClass().getName()) + " " + vp.d[this.e - 1];
    }
    
    public vp() {
        this.e = 1;
    }
    
    static {
        d = new String[] { "request", "indication", "response", "confirm" };
    }
}
