import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class ci extends a1
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
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;
    
    public void a(final dy dy, final az az, final Hashtable hashtable) throws aw {
        super.a(dy, az, hashtable);
        try {
            super.u.a(this, "sessionRequest", "Requests", "session requests", 1);
            super.u.a(this, "subscribeRequests", "Requests", "subscribe requests", 1);
            super.u.a(this, "unsubscribeRequests", "Requests", "unsubscribe requests", 1);
            super.u.a(this, "updateRequests", "Requests", "update requests", 1);
            super.u.a(this, "byeRequests", "Requests", "bye requests", 1);
            super.u.a(this, "updateLines", "PushObjects", "object updates", 1);
            super.u.a(this, "updateLinesWithDur", "PushObjects", "object updates", 1);
            super.u.a(this, "reInitLines", "PushObjects", "object re-inits", 1);
            super.u.a(this, "updatePkts", "PushComm", "update pkts", 1);
            super.u.a(this, "updatePktsComp", "PushComm", "compressed update pkts", 1);
            super.u.a(this, "updatePktsUncomp", "PushComm", "uncompressed update pkts", 1);
            super.u.a(this, "updatePktsBytesUncomp", "PushComm", "received uncompressed bytes", 1);
            super.u.a(this, "updatePktsBytesComp", "PushComm", "received compressed bytes", 1);
            super.u.a(this, "updatePktsBytesDecomp", "PushComm", "decompressed bytes", 1);
            super.u.b(this, "msPerDiffLine", "PushComm", "time in ms per update line", 1);
            final dz dz = new dz("##0.0");
            final d7 a = dy.a(this, "msPerTick", "PushComm", "time in ms per tick", 1);
            a.h = new ea(dy.b("PushObjects", "updateLinesWithDur"));
            a.g = dz;
            final d7 a2 = dy.a(this, "msPerDiffLine", "PushComm", "time in ms per update line", 1);
            a2.h = new ea(dy.b("PushObjects", "updateLines"));
            a2.g = dz;
            super.u.a(this, "callbacks", "Subscriptions", "callbacks", 1);
            super.u.a(this, "totalNrOfSubscriptions", "Subscriptions", "subscriptions", 1);
            super.u.a(this, "totalNrOfUnsubscriptions", "Subscriptions", "unsubscriptions", 1);
            super.u.a(this, "currentNrOfSubscriptions", "Subscriptions", "unsubscriptions", 1);
        }
        catch (Exception ex) {
            throw new aw("InternalError:PushStatsInit", ex);
        }
    }
}
