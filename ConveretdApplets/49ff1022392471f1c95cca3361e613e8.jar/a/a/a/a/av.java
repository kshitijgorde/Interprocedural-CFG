// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.util.Vector;

public class av implements Runnable
{
    public static final int try = 1;
    public static final int if = 2;
    public static final int a = 3;
    public static final int do = 4;
    private ac new;
    private Vector for;
    private boolean int;
    
    av(final ac new1) {
        this.int = false;
        this.new = new1;
        this.for = new Vector();
    }
    
    public void if(final ae ae) {
        synchronized (this) {
            this.for.addElement(ae);
            if (!this.int) {
                this.int = true;
                new Thread(this).start();
            }
        }
    }
    
    public void run() {
        while (true) {
            synchronized (this) {
                if (this.for.size() == 0) {
                    this.int = false;
                    // monitorexit(this)
                    return;
                }
            }
            int i = -1;
            int n = -1;
            final int size = this.for.size();
            while (i == -1) {
                for (int j = 0; j < size; ++j) {
                    final ae ae = this.for.elementAt(j);
                    if (ae.e != 0 && n == -1) {
                        n = j;
                    }
                    if (ae.if == ae.e && i == -1) {
                        i = j;
                    }
                }
                if (i == -1 && n != -1) {
                    i = n;
                }
                if (i == -1) {
                    try {
                        Thread.sleep(100L);
                    }
                    catch (Exception ex) {}
                }
            }
            final ae ae2 = this.for.elementAt(i);
            this.for.removeElementAt(i);
            if (!ae2.byte && ae2.a != 0) {
                this.a(ae2);
            }
        }
    }
    
    private void a(ae ae) {
        ae.byte = true;
        if (ae.a == 1) {
            try {
                final a7 a7 = new a7();
                if (a7.a(ae, this.new)) {
                    a7.a();
                }
                ae.h = 0;
                ae.else = false;
                ae.d = false;
                this.new.w.a();
            }
            catch (Exception ex) {}
        }
        if (ae.a == 2) {
            new d().a(ae, this.new);
            this.new.w.a();
            return;
        }
        if (ae.a != 3) {
            if (ae.a != 4) {
                return;
            }
        }
        while (!ae.b) {
            Thread.yield();
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex2) {}
        }
        if (ae.j != 0) {
            ae = ae.new[0];
        }
        while (!ae.b) {
            Thread.yield();
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex3) {}
        }
        final bf bf = new bf();
        try {
            y.a(bf, ae.k);
            final a2 do1 = bf.do;
            for (int i = 0; i < do1.do; ++i) {
                if (do1.try[i].compareTo("xmlns") == 0 && do1.new[i].compareTo("http://www.immervision.com/image") == 0) {
                    try {
                        ae.a = 3;
                        (ae.l = (ad)Class.forName("a.a.a.a.bo").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(do1, this.new, ae);
                    }
                    catch (Exception ex4) {
                        ae.l = null;
                        ae.a = 1;
                    }
                }
                else if (do1.try[i].compareTo("xmlns") == 0 && do1.new[i].compareTo("http://www.immervision.com/anim") == 0) {
                    try {
                        ae.a = 4;
                        (ae.for = (ad)Class.forName("a.a.a.a.r").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(do1, this.new, ae);
                    }
                    catch (Exception ex5) {
                        ae.for = null;
                        ae.a = 1;
                    }
                }
                else if (do1.try[i].compareTo("xmlns") == 0) {
                    return;
                }
            }
            this.new.w.a();
        }
        catch (Exception ex6) {}
    }
}
