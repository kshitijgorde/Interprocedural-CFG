import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

public class cc implements e, a0
{
    private az a;
    private long b;
    
    public cc(final az a) {
        this.a = a;
        this.b = a.o;
    }
    
    public final void produce() {
        if (a0.a.k()) {
            a0.a.i(this.a.as() + " XidProducer.produce");
        }
        if (this.a.o > this.b) {
            if (a0.a.i()) {
                a0.a.g(this.a.as() + " XID refetched from somewhere else - rescheduling");
            }
            this.a(this.a.ak());
            return;
        }
        if (a0.a.i()) {
            a0.a.g(this.a.as() + " going to refetch XID ");
        }
        try {
            if (this.a.d != null) {
                if (a0.a.i()) {
                    a0.a.g(this.a.as() + " using XidCallback " + this.a.d);
                }
                this.a.d.getNewXid(this.a);
            }
            else if (this.a.av() != null) {
                final a2 ai = this.a.ai();
                if (a0.a.i()) {
                    a0.a.g(this.a.as() + " got XidObject " + ai);
                }
                final String a = this.a.a.a("AUTH_ID_VALUE");
                if (a0.a.i()) {
                    a0.a.g(this.a.as() + " previous XID " + a);
                    a0.a.g(this.a.as() + " previous XID_EXPIRES: " + this.a.ak());
                }
                final String f = ai.f("XID");
                final String f2 = ai.f("XID_EXPIRES");
                if (a0.a.i()) {
                    a0.a.g(this.a.as() + " new XID " + f);
                    a0.a.g(this.a.as() + " new XID_EXPIRES: " + f2);
                }
                final long n = new Long(f2) * 1000L;
                if (a0.a.i()) {
                    a0.a.g(this.a.as() + " setting xid_expires to " + n + " [is " + new Date(n) + "]");
                }
                if (this.a.o == this.b) {
                    this.a.a(f, new Long(n));
                    if (a0.a.i()) {
                        a0.a.g(this.a.as() + " refetched XID from:" + a + " to:" + this.a.a.a("AUTH_ID_VALUE") + " Expires:" + this.a.ak());
                    }
                }
                else if (a0.a.i()) {
                    a0.a.g(this.a.as() + " XID refetched from somewhere else - performing no action");
                }
            }
            else if (a0.a.g()) {
                a0.a.d(this.a.as() + " couldn't find XidCallback or login data to request new session object.");
            }
        }
        catch (Exception ex) {
            if (a0.a.f()) {
                a0.a.a(this.a.as() + " refetch of xid failed: " + ex, ex);
            }
        }
        if (this.a.ak() <= System.currentTimeMillis()) {
            this.a(System.currentTimeMillis() + 3600000L);
            if (a0.a.g()) {
                a0.a.d(this.a.as() + " refetch of XID failed because expiry is " + new Date(this.a.ak()));
            }
        }
        else {
            this.a(this.a.ak());
        }
    }
    
    private void a(final long n) {
        this.b = this.a.o;
        if (a0.a.i()) {
            a0.a.g(this.a.as() + "scheduling next XID refetch at " + new Date(n));
        }
        this.a.at().a(this, n);
    }
}
