import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class a2 extends a3
{
    private bc a;
    private j b;
    
    public a2(final az az, final x x) {
        super(az, x.toString(), 2, null, false);
        if (a0.a.k()) {
            a0.a.i("ctor AuthObject " + a0.a.a(az, x));
        }
    }
    
    public void a(final az az) {
    }
    
    public bk a(final String s) {
        if (a0.a.l()) {
            a0.a.j("XidObject.createMetaDescriptor " + a0.a.a((Object)s));
        }
        return new bk(super.k, s, this.k()) {
            {
                if (a0.a.l()) {
                    a0.a.j("ctor XidDescriptor" + a0.a.a(az, s, s2));
                }
            }
            
            public void a(final az az) {
            }
        };
    }
    
    public static final a2 a(final az az, final x x) {
        if (a0.a.l()) {
            a0.a.j("XidObject.getInstance " + a0.a.a(az, x));
        }
        return a(az, new a2(az, x));
    }
    
    public static final a2 a(final az az, final a2 a2) {
        final a9 a3 = new a9(1);
        a3.addElement(a2);
        a2.a(new j(1, 4, "Xid"));
        final bd bd = new bd(az, a3, a2.a(), az.aj().d("XID_REQ_TIMEOUT"), null);
        final j ab = a2.ab();
        if (ab != null) {
            ab.a();
        }
        else if (n.a()) {
            n.a("found uninitialized nonblockingfactory!");
        }
        return a2;
    }
    
    private void a(final j b) {
        this.b = b;
    }
    
    private j ab() {
        return this.b;
    }
    
    public bc a() {
        if (this.a == null) {
            final Hashtable<String, String> hashtable = new Hashtable<String, String>();
            hashtable.put("JMDG-Version", "2.1.12");
            try {
                final x x = (x)super.k.a.b("XID_HTTP_REQUEST_HEADER");
                if (x != null) {
                    final Enumeration d = x.d();
                    while (d.hasMoreElements()) {
                        final String s = d.nextElement();
                        final String a = x.a(s);
                        if (s != null && a != null) {
                            hashtable.put(s, a);
                        }
                    }
                }
            }
            catch (Exception ex) {
                if (a0.a.j()) {
                    a0.a.f(super.k.as() + " could not find additional headers for config request", ex);
                }
            }
            this.a = new ba(super.k.a.a("XID_PROTOCOL"), super.k.a.a("XID_HOST"), super.k.a.d("XID_PORT"), hashtable, this.ab(), super.k.a.a("XID_VIRTUAL_HOST"), 64000, super.k.a.h("AUTH_STICKY_PARAMS"));
        }
        return this.a;
    }
}
