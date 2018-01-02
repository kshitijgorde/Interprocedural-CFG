// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.Enumeration;
import java.util.Vector;

public final class bG
{
    private String a;
    private Vector b;
    private Vector c;
    private Vector d;
    
    public bG() {
        this.a = null;
        this.b = new Vector();
        this.c = new Vector();
        this.d = new Vector();
    }
    
    public final synchronized String a() {
        return this.a;
    }
    
    public final synchronized Vector b() {
        return this.c;
    }
    
    public final synchronized Vector c() {
        return this.d;
    }
    
    private synchronized void a(final String s) {
        if (s == null || !this.b(s)) {
            return;
        }
        this.d.removeElement(s);
        this.c.removeElement(s);
        this.c.insertElementAt(s, 0);
        if (s.equals(this.a)) {
            this.a = null;
        }
    }
    
    private synchronized boolean b(final String s) {
        return this.b.contains(s);
    }
    
    public final synchronized void a(final an an) {
        final af af;
        final String a = (af = (af)an).a();
        final byte b;
        if ((b = af.b()) == 3) {
            this.c.removeElement(a);
            if (!this.d.contains(a)) {
                this.d.addElement(a);
            }
            if (!this.b.contains(a)) {
                this.b.addElement(a);
            }
            if (a.equals(this.a)) {
                this.a = null;
            }
        }
        else if (b == 2) {
            this.d.removeElement(a);
            if (!this.c.contains(a)) {
                this.c.addElement(a);
            }
            if (!this.b.contains(a)) {
                this.b.addElement(a);
            }
            if (a.equals(this.a)) {
                this.a = null;
            }
        }
        else if (b == 1) {
            this.d.removeElement(a);
            this.c.removeElement(a);
            this.a = a;
            if (!this.b.contains(a)) {
                this.b.addElement(a);
            }
        }
        else if (b == 4) {
            this.d.removeElement(a);
            this.c.removeElement(a);
            this.b.removeElement(a);
            if (a.equals(this.a)) {
                this.a = null;
            }
        }
        else if (b == 5) {
            this.a(a);
        }
    }
    
    public final synchronized String toString() {
        final StringBuffer sb = new StringBuffer();
        if (this.a() != null) {
            sb.append("= Speaker: " + this.a() + "\n");
        }
        sb.append("= Request to talk:\n");
        final Enumeration<Object> elements = this.b().elements();
        while (elements.hasMoreElements()) {
            sb.append(elements.nextElement().toString());
            sb.append("\n");
        }
        sb.append("= Participants\n");
        final Enumeration<Object> elements2 = this.c().elements();
        while (elements2.hasMoreElements()) {
            sb.append(elements2.nextElement().toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
