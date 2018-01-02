// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.util.Vector;

public class a9 implements Runnable
{
    public static final int try = 1;
    public static final int if = 2;
    public static final int a = 3;
    public static final int do = 4;
    private an new;
    private Vector for;
    private boolean int;
    
    public void a() {
        if (!this.int) {
            this.new = null;
            if (this.for != null) {
                this.for.removeAllElements();
            }
            this.for = null;
        }
    }
    
    a9(final an new1) {
        this.int = false;
        this.new = new1;
        this.for = new Vector();
    }
    
    public void if(final aq aq) {
        synchronized (this) {
            this.for.addElement(aq);
            if (!this.int) {
                this.int = true;
                new Thread(this).start();
            }
        }
    }
    
    public void run() {
        while (!this.new.G) {
            synchronized (this) {
                if (this.for.size() == 0) {
                    this.int = false;
                    if (this.new.G) {
                        this.a();
                    }
                    // monitorexit(this)
                    return;
                }
            }
            int i = -1;
            int n = -1;
            final int size = this.for.size();
            while (i == -1) {
                for (int j = 0; j < size; ++j) {
                    final aq aq = this.for.elementAt(j);
                    if (aq.e != 0 && n == -1) {
                        n = j;
                    }
                    if (aq.do == aq.e && i == -1) {
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
            aq aq2 = this.for.elementAt(i);
            if (aq2.k > 0) {
                aq2 = aq2.try[0];
            }
            this.for.removeElementAt(i);
            if ((!aq2.byte && aq2.a != 0) || aq2.a == 4) {
                this.a(aq2);
            }
        }
        this.int = false;
        this.a();
    }
    
    private void a(aq aq) {
        aq.byte = true;
        if (aq.a == 1) {
            try {
                final bn bn = new bn();
                if (bn.a(aq, this.new)) {
                    bn.a();
                }
                aq.i = 0;
                aq.else = false;
                aq.d = false;
                this.new.B.if();
                return;
            }
            catch (Exception ex) {}
        }
        if (aq.a == 2) {
            new e().a(aq, this.new);
            this.new.B.if();
            return;
        }
        if (aq.a != 3) {
            if (aq.a != 4) {
                return;
            }
        }
        while (!aq.b) {
            if (aq.k != 0) {
                aq = aq.try[0];
            }
            Thread.yield();
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex2) {}
        }
        if (aq.k != 0) {
            aq = aq.try[0];
        }
        while (!aq.b) {
            Thread.yield();
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex3) {}
        }
        final bw bw = new bw();
        try {
            aj.a(bw, aq.l, aq.do);
            final bh do1 = bw.do;
            for (int i = 0; i < do1.do; ++i) {
                if (do1.try[i].compareTo("xmlns") == 0 && do1.new[i].compareTo("http://www.immervision.com/image") == 0) {
                    try {
                        aq.a = 3;
                        (aq.m = (ap)Class.forName("a.a.a.a.b7").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(do1, this.new, aq);
                    }
                    catch (Exception ex4) {
                        aq.m = null;
                        aq.a = 1;
                    }
                }
                else if (do1.try[i].compareTo("xmlns") == 0 && do1.new[i].compareTo("http://www.immervision.com/anim") == 0) {
                    try {
                        aq.a = 4;
                        (aq.int = (ap)Class.forName("a.a.a.a.y").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(do1, this.new, aq);
                    }
                    catch (Exception ex5) {
                        aq.int = null;
                        aq.a = 1;
                    }
                }
                else if (do1.try[i].compareTo("xmlns") == 0) {
                    return;
                }
            }
            this.new.B.if();
        }
        catch (Exception ex6) {}
    }
}
