// 
// Decompiled by Procyon v0.5.30
// 

public class bu extends bq
{
    private az a;
    private bc b;
    
    public bu(final az a) {
        this.a = a;
    }
    
    public void a() {
    }
    
    public void a(final t t) {
        if (a0.a.l()) {
            a0.a.j(this.a.as() + " RequestHandlerModule.loadConfig " + a0.a.a(t));
        }
        if (a0.a.i()) {
            a0.a.g(this.a.as() + " creating new request handler");
        }
        Integer d = t.d("MAX_GET_REQUEST_LENGTH");
        if (d == null) {
            d = new Integer(4000);
            if (a0.a.g()) {
                a0.a.d(this.a.as() + " no valid " + "MAX_GET_REQUEST_LENGTH" + " found: " + t.d("MAX_GET_REQUEST_LENGTH") + " taking " + d);
            }
        }
        final int intValue = d;
        if (intValue < 1900 && a0.a.g()) {
            a0.a.d(this.a.as() + " new MAX_GET_REQUEST_LENGTH value is less than recommended: [" + intValue + "] < [" + 1900 + "]");
        }
        else if (a0.a.i()) {
            a0.a.g(this.a.as() + " setting MAX_GET_REQUEST_LENGTH to [" + intValue + "]");
        }
        Integer d2 = t.d("MAX_NESTED_POST_REQUEST_LENGTH");
        if (d2 == null) {
            d2 = new Integer(64000);
            if (a0.a.g()) {
                a0.a.d(this.a.as() + " no valid " + "MAX_NESTED_POST_REQUEST_LENGTH" + " found: " + t.d("MAX_NESTED_POST_REQUEST_LENGTH") + " taking " + d2);
            }
        }
        if (a0.a.i()) {
            a0.a.g(this.a.as() + " setting MAX_NESTED_POST_REQUEST_LENGTH to [" + d2 + "]");
        }
        this.b = new dw(t.b("PROTOCOL_LIST", 0), t.b("CHOST_LIST", 0), t.a("CPORT_LIST", 0), this.a.j, this.a.aq(), t.b("VHOST_LIST", 0), t.f("REQUEST_TIMEOUT_FACTOR"), intValue, d2, t.h("AUTH_STICKY_PARAMS"));
        final Boolean c;
        if ((c = t.c("ENABLE_USE_CACHES")) != null && !c) {
            if (a0.a.i()) {
                a0.a.g(this.a.as() + " detected ENABLE_USE_CACHES == false");
            }
            ((dw)this.b).a(true);
        }
        if (a0.a.i()) {
            a0.a.g(this.a.as() + " RequestHandlerModule: providing single request handler " + this.b);
        }
    }
    
    public final void c() {
        this.a = null;
    }
    
    public final String d() {
        return "REQUESTHANDLER_MODULE";
    }
    
    public final bc b() {
        return this.b;
    }
}
