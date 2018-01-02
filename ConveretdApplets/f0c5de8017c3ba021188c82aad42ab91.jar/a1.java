import java.util.Enumeration;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class a1
{
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public long m;
    public long n;
    public long o;
    public long p;
    public long q;
    public long r;
    public int s;
    public int t;
    public dy u;
    
    public void a(final dy u, final az az, final Hashtable hashtable) throws aw {
        try {
            u.a(this, "apiRequests", "Requests", "API object requests", 1);
            u.a(this, "totalRequests", "Requests", "total object requests", 2);
            u.a(this, "cachedRequests", "Requests", "cached requests", 2);
            u.a(this, "netRequests", "Requests", "network requests", 3);
            u.a(this, "dupRequests", "Requests", "duplicate requests", 4);
            final d1 d1 = new d1();
            u.a(this, "nrReceivedComp", "Comm", "compressed response messages", 2);
            u.a(this, "nrReceivedUncomp", "Comm", "uncompressed response messages", 2);
            u.a(this, "msgBytesReceivedUncomp", "Comm", "received uncompressed msg-bytes", 3).g = d1;
            u.a(this, "msgBytesReceivedComp", "Comm", "received compressed msg-bytes", 3).g = d1;
            final d7 a = u.a(this, "msgBytesReceivedDecomp", "Comm", "decompressed bytes", 3);
            a.g = d1;
            u.a(new ea(a), "Comm", "compRatio", "compression ratio", 3);
            u.a(this, "networkBytes", "Comm", "received bytes", 1).g = d1;
            final dz dz = new dz("##0.0");
            final d7 a2 = u.a(this, "msPerRequest", "Timings", "time in ms per request", 2);
            a2.h = new ea(u.b("Requests", "totalRequests"));
            a2.g = dz;
            final d7 a3 = u.a(this, "msPerNetRequest", "Timings", "time in ms per network request", 3);
            a3.h = new ea(u.b("Requests", "totalRequests"));
            a3.g = dz;
            u.a(this, "okObjects", "Objects", "ok requests", 1);
            u.a(this, "errorObjects", "Objects", "requests with errors", 1);
            u.a(this, "mdgErrors", "Objects", "objets with mdg errors", 2);
            u.a(this, "noDescrObjects", "Objects", "getting descriptor errors", 4);
            u.a(this, "serverIOErrors", "Objects", "ServerIOException errors", 3);
            u.a(this, "httpErrors", "Objects", "HttpError errors", 3);
            u.a(this, "unreachableErrors", "Objects", "ServerUnreachbale errors", 3);
            final Enumeration<String> keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                u.a(hashtable.get(s), "value", "ObjRequests", s, "number of " + s + " requests", 4);
            }
            Object b = null;
            final bp bp = (bp)az.e("CACHE_MODULE");
            if (bp != null) {
                b = bp.b();
            }
            u.b(b, "currentSize", "Cache", "cache size in kByte", 2).g = u.c;
            u.b(b, "currentElements", "Cache", "number of elements in cache", 2);
            final d7 a4 = u.a(b, "nrRequests", "Cache", "number of requests for cache elements", 3);
            final d7 a5 = u.a(b, "nrHits", "Cache", "number of hits for cahce elements", 3);
            u.a(b, "nrMisses", "Cache", "number of misses for cache elements", 3);
            u.a(b, "nrInserts", "Cache", "number of inserts of cache elements", 3);
            u.a(b, "nrRemoves", "Cache", "number of removes of cache elements", 3);
            u.a(b, "nrForcedRemoves", "Cache", "number of forced removes of cache elements", 3);
            u.a(new ed(a4, a5), "Cache", "%Hits", "percent of cache hits", 2);
            final b9 b2 = (b9)az.e("NON_BLOCKING_MODULE");
            if (b2 != null) {
                final j b3 = b2.b;
                u.b(b3, "nrAlive", "Creators", "number of alive threads", 3);
                u.b(b3, "nrIdle", "Creators", "number of idle threads", 3);
                u.a(b3, "nrCreated", "Creators", "number of created threads", 3);
            }
            final d7 b4 = u.b(this, "memory", "Memory", "UsedMemory", "Used Memory", 4);
            b4.g = u.c;
            b4.i = new ee();
        }
        catch (Exception ex) {
            throw new aw("InternalError:StatsInit", ex);
        }
        this.u = u;
    }
}
