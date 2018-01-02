// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.net.ProtocolException;
import java.io.IOException;
import java.util.Hashtable;

final class bi implements bk
{
    private static Hashtable a;
    private static Hashtable b;
    private int c;
    private bf d;
    private boolean e;
    private q f;
    
    bi() {
        this.c = 0;
        this.d = null;
        this.f = null;
    }
    
    public final int a(final q q) {
        final aj a = q.a();
        final cK f;
        if ((f = q.f()) != null && bi.b.get(f) != null) {
            final bi bi = VT_6_1_0_11.bi.b.remove(f);
            this.c = bi.c;
            this.d = bi.d;
            q.a(this.f = bi.f);
            if (this.e) {
                return 5;
            }
            return 1;
        }
        else {
            bf bf;
            try {
                bf = new bf(new bf(a.b(), a.c(), a.d(), null), q.c());
            }
            catch (dh dh) {
                throw new Error("HTTPClient Internal Error: unexpected exception '" + dh + "'");
            }
            final bf bf2;
            if ((bf2 = bz.a(bi.a, q.a().g()).get(bf)) == null) {
                return 0;
            }
            final String d = bf2.d();
            q.b(d);
            try {
                this.d = new bf(bf2, d);
            }
            catch (dh dh2) {}
            new StringBuffer().append("RdirM: matched request in permanent redirection list - redoing request to ").append(this.d.e()).toString();
            if (!a.a(bf2)) {
                aj aj;
                try {
                    aj = new aj(bf2);
                }
                catch (Exception ex) {
                    throw new Error("HTTPClient Internal Error: unexpected exception '" + ex + "'");
                }
                aj.a(q.a().g());
                q.a(aj);
                return 5;
            }
            return 1;
        }
    }
    
    public final void a(final bd bd) {
        final int a;
        if (((a = bd.a()) < 301 || a > 307 || a == 304) && this.d != null) {
            bd.a(this.d);
        }
    }
    
    public final int a(final bd bd, final q q) {
        int a;
        switch (a = bd.a()) {
            case 302: {
                if (q.b().equals("POST") || q.b().equals("PUT")) {
                    new StringBuffer().append("RdirM: Received status: ").append(a).append(" ").append(bd.b()).append(" - treating as 303").toString();
                    a = 303;
                }
            }
            case 301:
            case 303:
            case 307: {
                new StringBuffer().append("RdirM: Handling status: ").append(a).append(" ").append(bd.b()).toString();
                if (!q.b().equals("GET") && !q.b().equals("HEAD") && a != 303) {
                    if (a == 301 && bd.a("Location") != null) {
                        a(q, a(bd.a("Location"), q));
                    }
                    bd.a(this.d);
                    return 10;
                }
            }
            case 305:
            case 306: {
                if (a == 305 || a == 306) {
                    new StringBuffer().append("RdirM: Handling status: ").append(a).append(" ").append(bd.b()).toString();
                }
                if (a == 305 && q.a().e() != null) {
                    bd.a(this.d);
                    return 10;
                }
                if (this.c >= 15 || bd.a("Location") == null) {
                    bd.a(this.d);
                    return 10;
                }
                ++this.c;
                final bf a2 = a(bd.a("Location"), q);
                this.e = false;
                aj a3;
                String s;
                if (a == 305) {
                    (a3 = new aj(q.a().b(), q.a().c(), q.a().d())).a(a2.b(), a2.c());
                    a3.a(q.a().g());
                    this.e = true;
                    s = q.c();
                    q.a("GET");
                    q.a((byte[])null);
                    q.a((cK)null);
                }
                else {
                    if (a == 306) {
                        return 10;
                    }
                    if (q.a().a(a2)) {
                        a3 = q.a();
                        s = a2.d();
                    }
                    else {
                        try {
                            a3 = new aj(a2);
                            s = a2.d();
                        }
                        catch (Exception ex) {
                            if (q.a().e() == null || !a2.a().equalsIgnoreCase("ftp")) {
                                return 10;
                            }
                            (a3 = new aj("http", q.a().e(), q.a().f())).a((String)null, 0);
                            s = a2.e();
                        }
                        a3.a(q.a().g());
                        this.e = true;
                    }
                    if (a == 303) {
                        if (!q.b().equals("HEAD")) {
                            q.a("GET");
                        }
                        q.a((byte[])null);
                        q.a((cK)null);
                    }
                    else {
                        if (q.f() != null) {
                            if (!aj.d) {
                                new StringBuffer().append("RdirM: status ").append(a).append(" not handled - request ").append("has an output stream").toString();
                                return 10;
                            }
                            this.f = (q)q.clone();
                            bi.b.put(q.f(), this);
                            q.f().c();
                            bd.a(true);
                        }
                        if (a == 301) {
                            try {
                                a(q, new bf(a2, s));
                            }
                            catch (dh dh) {
                                throw new Error("HTTPClient Internal Error: unexpected exception '" + dh + "'");
                            }
                        }
                    }
                    final cU[] d = q.d();
                    for (int i = 0; i < d.length; ++i) {
                        if (d[i].a().equalsIgnoreCase("Referer")) {
                            d[i] = new cU("Referer", q.a() + q.c());
                            break;
                        }
                    }
                }
                q.a(a3);
                q.b(s);
                try {
                    bd.f().close();
                }
                catch (IOException ex2) {}
                if (a != 305 && a != 306) {
                    try {
                        this.d = new bf(a2, s);
                    }
                    catch (dh dh2) {}
                    new StringBuffer().append("RdirM: request redirected to ").append(this.d.e()).append(" using method ").append(q.b()).toString();
                }
                else {
                    new StringBuffer().append("RdirM: resending request using proxy ").append(a3.e()).append(":").append(a3.f()).toString();
                }
                if (q.f() != null) {
                    return 10;
                }
                if (this.e) {
                    return 15;
                }
                return 13;
            }
            default: {
                return 10;
            }
        }
    }
    
    private static void a(final I i, final bf bf) {
        final aj a = i.a();
        bf bf2 = null;
        try {
            bf2 = new bf(new bf(a.b(), a.c(), a.d(), null), i.c());
        }
        catch (dh dh) {}
        if (!bf2.equals(bf)) {
            bz.a(bi.a, a.g()).put(bf2, bf);
        }
    }
    
    private static bf a(final String s, final I i) {
        try {
            final bf bf;
            if ((bf = new bf(new bf(new bf(i.a().b(), i.a().c(), i.a().d(), null), i.c()), s)).b() == null) {
                throw new ProtocolException("Malformed URL in Location header: `" + s + "' - missing host field");
            }
            return bf;
        }
        catch (dh dh) {
            throw new ProtocolException("Malformed URL in Location header: `" + s + "' - exception was: " + dh.getMessage());
        }
    }
    
    static {
        bi.a = new Hashtable();
        bi.b = new Hashtable();
    }
}
