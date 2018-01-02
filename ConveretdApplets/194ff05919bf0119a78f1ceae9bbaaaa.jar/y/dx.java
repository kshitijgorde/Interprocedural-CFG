// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.io.IOException;

public final class dx implements b
{
    private ew a;
    private dy a;
    private String b;
    public String a;
    private cr a;
    
    public dx(final String b, final cr a, final String s, final int n, final String s2, final long n2) {
        this.a = a(s, n, s2, n2);
        this.b = b;
        this.a = ew.a();
        (this.a = dy.a()).a(this);
        this.a = a;
        this.b(0, this);
        this.a.b("New SlaveWindow:" + this.a);
    }
    
    public static String a(final String s, final int n, final String s2, final long n2) {
        final StringBuffer sb;
        (sb = new StringBuffer("SlaveWindow")).append(':');
        sb.append(s);
        sb.append(':');
        sb.append(n);
        sb.append(':');
        sb.append(s2);
        sb.append(':');
        sb.append(n2);
        return sb.toString();
    }
    
    public final Object b(final int n, final Object o) {
        try {
            this.a.a(this.b, n, o);
        }
        catch (IOException ex) {
            this.a.b("Cmd failed. cmd=" + n + " window=" + this.b, ex);
        }
        return null;
    }
    
    public final String a() {
        return this.a;
    }
    
    public final Object a(final int n, final Object o) {
        switch (n) {
            case 3: {
                this.a.onExitTableApplet();
                break;
            }
            case 4: {
                this.a.onSetWindowTitle((String)o);
                break;
            }
            case 5: {
                this.a.onSetExitComponent(o);
                break;
            }
            case 2: {
                this.a.onRotateAd();
                break;
            }
            case 7: {
                this.a.onRequestWindowClose();
                break;
            }
            default: {
                this.a.c("Slave window received unrecognized command:" + n);
                break;
            }
        }
        return null;
    }
}
