// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public class vm implements vn, Cloneable
{
    public static boolean a;
    private static long b;
    private static long c;
    private static final String[] d;
    public int e;
    public boolean f;
    
    public long a() {
        return vm.b;
    }
    
    public long b() {
        return vm.c;
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
        if (vm.a) {
            System.out.println("<== " + this.toString());
        }
    }
    
    public void a(final DataInputStream dataInputStream) throws IOException {
        this.e = dataInputStream.readInt();
        if (vm.a) {
            System.out.println("==> " + this.toString());
        }
    }
    
    public String toString() {
        return this.getClass().getName() + " " + vm.d[this.e - 1];
    }
    
    public vm() {
        this.e = 1;
    }
    
    static {
        d = new String[] { "request", "indication", "response", "confirm" };
    }
}
