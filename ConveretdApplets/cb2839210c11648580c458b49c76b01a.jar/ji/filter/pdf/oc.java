// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.pdf;

import ji.v1event.a9;
import ji.io.h;
import ji.util.i;
import ji.util.ba;
import ji.v1event.a2;
import ji.v1event.ak;

class oc implements ak
{
    int a;
    private String b;
    String c;
    String d;
    ha e;
    a2 f;
    ba g;
    Object h;
    int i;
    public String j;
    static int k;
    
    public oc() {
        this.a = 0;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = new ba();
        this.h = null;
        this.i = 0;
        this.i = oc.k++;
    }
    
    public final int a() {
        return this.i;
    }
    
    private void a(final String s, final od od) {
        if (ji.util.i.c(284)) {
            ji.io.h.d(this.d, String.valueOf(String.valueOf(new StringBuffer("PDF: ").append(s).append(": ").append((od != null) ? od.toString() : "null"))));
        }
    }
    
    public void a(final a9 a9) {
        this.h = null;
        final od od = (od)a9.d();
        this.a("Dispatching Decoder Command", od);
        try {
            synchronized (this.e) {
                final od od2 = od;
                // monitorenter(od2)
                try {
                    this.a("Synchronized Dispatching Decoder Command", od);
                    try {
                        switch (od.b) {
                            case 0: {
                                if (od.a()) {
                                    od.a(this.e.processHeader(od.c, od.d, od.e));
                                    od.b();
                                    break;
                                }
                                this.h = this.e.processHeader(od.c, od.d, od.e);
                                break;
                            }
                            case 1: {
                                if (od.a()) {
                                    this.e.fillDib(od.c, od.d);
                                    od.a((Object)null);
                                    break;
                                }
                                this.e.fillDib(od.c, od.d);
                                break;
                            }
                            case 2: {
                                if (od.a()) {
                                    od.a(this.e.getAnnotations(od.d, od.g, od.h, od.f));
                                    break;
                                }
                                this.h = this.e.getAnnotations(od.d, od.g, od.h, od.f);
                                break;
                            }
                            case 3: {
                                if (od.a()) {
                                    this.e.closePDFL();
                                    break;
                                }
                                this.e.closePDFL();
                                break;
                            }
                            case 4: {
                                if (od.a()) {
                                    this.e.closePDFLDoc();
                                    break;
                                }
                                this.e.closePDFLDoc();
                                break;
                            }
                            case 5: {
                                if (od.a()) {
                                    this.e.find(od.m, od.n, od.g, this.d, od.o);
                                    break;
                                }
                                this.e.find(od.m, od.n, od.g, this.d, od.o);
                                break;
                            }
                        }
                        od.b();
                    }
                    catch (Throwable t) {
                        od.a(t);
                    }
                    finally {
                        this.a("Finished Synchronized Decoder Command", od);
                    }
                    od.notifyAll();
                }
                // monitorexit(od2)
                finally {}
            }
            // monitorexit(this.e)
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            this.a("Finished Decoder Command", od);
            od.d = null;
            od.e = 0.0;
            od.f = 0;
            od.g = null;
            od.h = null;
        }
        if (!od.a()) {
            this.g.a();
        }
    }
    
    public Object a(final od od) {
        this.a("Issue Command", od);
        try {
            if (od.a()) {
                return this.b(od);
            }
            this.g.a(1);
            this.f.a(new a9(this, od, false));
            this.g.a(2);
            this.g.a();
            return this.h;
        }
        finally {
            this.a("Finished Waiting for Command", od);
        }
    }
    
    private final Object b(final od od) {
        this.f.a(new a9(this, od, false));
        synchronized (od) {
            while (!od.c()) {
                try {
                    od.wait(300L);
                }
                catch (InterruptedException ex) {}
            }
            return od.d();
        }
    }
    
    public void b() {
        this.h = null;
    }
    
    public void a(final String b) {
        if (this.b != null && !this.b.equals(b)) {
            this.a(new od(5));
        }
        this.b = b;
    }
    
    public String c() {
        return this.b;
    }
    
    void d() {
        this.a((String)null);
        this.c = null;
        this.e = null;
        this.f.g();
        this.f = null;
        this.g = null;
        this.h = null;
    }
    
    static {
        oc.k = 1;
    }
}
