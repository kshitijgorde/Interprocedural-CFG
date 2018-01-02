// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1event;

import java.util.EventObject;

public class f2 extends EventObject
{
    private String a;
    private int b;
    private int c;
    
    public f2(final Object o, final int b, final int c, final String a) {
        super(o);
        this.b = b;
        this.a = a;
        this.c = c;
    }
    
    public int a() {
        return this.b;
    }
    
    public String b() {
        return this.a;
    }
    
    public int c() {
        return this.c;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(20);
        switch (this.b) {
            case 0: {
                sb.append("OK");
                break;
            }
            case 1: {
                sb.append("Failure");
                break;
            }
            case 2: {
                sb.append("result");
                break;
            }
            case 3: {
                sb.append("Status");
                break;
            }
        }
        sb.append(":");
        sb.append(String.valueOf(this.c));
        sb.append(":");
        sb.append(this.a);
        return sb.toString();
    }
}
