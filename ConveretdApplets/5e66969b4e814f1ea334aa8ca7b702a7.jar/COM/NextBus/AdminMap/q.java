// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.Point;
import java.awt.Image;
import java.awt.Color;
import java.util.Iterator;
import java.util.Enumeration;
import COM.NextBus.Predictor2Comm.RouteInfo;
import COM.NextBus.Predictor2Comm.StopPredictionInfo;
import COM.NextBus.Predictor2Comm.StopPrediction;
import COM.NextBus.util.e;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import COM.NextBus.Predictor2Comm.Stop;
import COM.NextBus.util.c;
import java.util.ArrayList;
import COM.NextBus.Predictor2Comm.Location;

final class q implements f
{
    private final G a;
    private final int b;
    private final int c;
    private final ao d;
    
    q(final Location location, final String s, final String s2, final boolean b, final G a) {
        this.a = a;
        this.b = location.c();
        this.c = location.d();
        ao d = null;
        if (s != null && !s.equals("")) {
            d = new ao(s, s2, b, "stop-" + this.a.b() + "-" + this.a.g() + "-" + s + "-" + s2);
        }
        this.d = d;
        if (this.e()) {
            this.a.c().f.a(this);
        }
    }
    
    public final g b() {
        return new g(this.a.b(), this.a.g(), this.d.a, this.d.b);
    }
    
    final void c() {
        this.a.c().f.b(this);
    }
    
    public final void d() {
        if (this.e()) {
            this.a.c().f.a(this);
            return;
        }
        this.a.c().f.b(this);
    }
    
    final boolean e() {
        return this.d != null && (!this.d.d || this.a.c().C().n());
    }
    
    public final String[] f() {
        if (this.d == null) {
            return new String[0];
        }
        final ArrayList<String> list = new ArrayList<String>();
        final StringBuffer sb = new StringBuffer(COM.NextBus.AdminMap.a.b("Stop") + ": ");
        final String b = this.a.b();
        sb.append(this.a.c().c(b, this.d.a));
        if (this.a.c().C().x()) {
            sb.append(" (" + this.d.a + "-" + this.d.b + ")");
        }
        list.add(sb.toString());
        final StringBuffer sb2;
        (sb2 = new StringBuffer(COM.NextBus.AdminMap.a.b(this.a.c().k()) + ": ")).append(this.a.f());
        if (this.a.c().C().x()) {
            sb2.append(" (" + this.a.g() + ")");
        }
        list.add(sb2.toString());
        if (this.a.c().t()) {
            final StopPrediction a = this.a.a(this.d.a, this.d.b);
            StopPredictionInfo[] b2 = null;
            final long b3 = COM.NextBus.util.c.b();
            if (a != null) {
                b2 = a.b(b3);
            }
            if (a == null || b2 == null || b2.length == 0) {
                final RouteInfo a2;
                final String[] b4 = (a2 = this.a.c().e.a(b, this.a.g())).b();
                int length = 0;
                String s = null;
                for (int i = 0; i < b4.length; ++i) {
                    final Enumeration a3 = a2.a(b4[i]);
                    final int[] b5 = a2.b(b4[i]);
                    while (a3.hasMoreElements()) {
                        if (a3.nextElement().c().equals(this.d.a) && b5.length > length) {
                            length = b5.length;
                            s = b4[i];
                        }
                    }
                }
                String s2 = this.a.c().d(b, s);
                if (this.a.c().C().x()) {
                    s2 = s2 + " (" + s + ")";
                }
                list.add(COM.NextBus.AdminMap.a.b("Direction") + ": " + s2);
                list.add(COM.NextBus.AdminMap.a.b("Prediction") + ": " + "                                                         ");
            }
            else {
                final HashMap<String, Set<String>> hashMap = (HashMap<String, Set<String>>)new HashMap<Object, Object>();
                for (int j = 0; j < b2.length; ++j) {
                    final String d = b2[j].d();
                    String d2;
                    if ((d2 = this.a.c().d(b, d)) == null) {
                        d2 = "None";
                    }
                    Set<String> set;
                    if ((set = hashMap.get(d2)) == null) {
                        set = new HashSet<String>();
                        hashMap.put(d2, set);
                    }
                    set.add(d);
                }
                final Set<Object> keySet;
                final Iterator<Object> iterator = (keySet = hashMap.keySet()).iterator();
                while (iterator.hasNext()) {
                    if (keySet.size() > 1) {
                        list.add("");
                    }
                    final StringBuffer sb3 = new StringBuffer(COM.NextBus.AdminMap.a.b("Direction") + ": ");
                    final String s3 = iterator.next();
                    sb3.append(s3);
                    list.add(sb3.toString());
                    final String a4;
                    if ((a4 = this.a(a, hashMap.get(s3))) != null) {
                        list.add(a4);
                    }
                }
            }
            if (!this.a.c().o()) {
                String a5 = "";
                String a6 = "";
                final Long d3;
                if (a != null && (d3 = a.d()) != null && b2.length > 0) {
                    long n;
                    if ((n = COM.NextBus.util.c.b() - d3) <= 0L) {
                        n = 0L;
                    }
                    a5 = e.a(n + b2[0].i() - COM.NextBus.util.c.b(), true);
                    a6 = e.a(n, true);
                }
                list.add(COM.NextBus.AdminMap.a.b("Headway") + ": " + a5);
                list.add(COM.NextBus.AdminMap.a.b("Last Passing") + ": " + a6);
            }
        }
        return list.toArray(new String[list.size()]);
    }
    
    public final Color g() {
        return G.a(this.a.d());
    }
    
    public final Color h() {
        return this.a.d();
    }
    
    public final String i() {
        return this.a.g() + "-Stop";
    }
    
    public final Image j() {
        return this.a.c().y().c();
    }
    
    public final Point a(final MapCanvas mapCanvas) {
        if (this.a.i()) {
            return new Point(mapCanvas.a(this.b, this.c));
        }
        return null;
    }
    
    public final String a() {
        if (this.d == null) {
            return "lat=" + this.b + ",lon=" + this.c;
        }
        return this.d.c;
    }
    
    private String a(final StopPrediction stopPrediction, final Set set) {
        final boolean o = this.a.c().o();
        final StopPredictionInfo[] array = new StopPredictionInfo[100];
        int n = 0;
        final long b = COM.NextBus.util.c.b();
        for (final String s : set) {
            if (stopPrediction != null) {
                final String s2 = s;
                final long n2 = b;
                final String s3 = s2;
                final List a = stopPrediction.a(n2);
                int i = 0;
                while (i < a.size()) {
                    if (!a.get(i).d().equals(s3)) {
                        a.remove(i);
                    }
                    else {
                        ++i;
                    }
                }
                final StopPredictionInfo[] array2 = a.toArray(new StopPredictionInfo[a.size()]);
                for (int j = 0; j < array2.length; ++j) {
                    array[n + j] = array2[j];
                }
                n += array2.length;
            }
        }
        for (int k = 1; k != 0; k = 1) {
            k = 0;
            for (int l = 1; l < n; ++l) {
                if (array[l].i() < array[l - 1].i()) {
                    final StopPredictionInfo stopPredictionInfo = array[l - 1];
                    array[l - 1] = array[l];
                    array[l] = stopPredictionInfo;
                    break;
                }
            }
        }
        int n3 = n;
        if (o) {
            if (n3 > 3) {
                n3 = 3;
            }
        }
        else if (n3 > 4) {
            n3 = 3;
        }
        final StringBuffer sb;
        (sb = new StringBuffer()).append(COM.NextBus.AdminMap.a.b("Predictions") + ": ");
        if (n3 != 0) {
            for (int n4 = 0; n4 < n3; ++n4) {
                final StopPredictionInfo stopPredictionInfo2;
                final long m = (stopPredictionInfo2 = array[n4]).i();
                final String a2 = stopPredictionInfo2.a();
                final String d = stopPredictionInfo2.d();
                final boolean l2 = stopPredictionInfo2.l();
                final long n5 = m;
                final boolean b2 = l2;
                final String s4 = a2;
                final String s5 = d;
                final boolean b3 = o;
                final String s6 = s5;
                final String s7 = s4;
                final boolean b4 = b2;
                final long n6 = n5;
                final StringBuffer sb2 = new StringBuffer();
                if (n6 != 2147483647L) {
                    if (this.a.c().C().o()) {
                        final SimpleDateFormat simpleDateFormat;
                        (simpleDateFormat = new SimpleDateFormat("h:mm:ssa")).setTimeZone(this.a.c().q());
                        sb2.append(simpleDateFormat.format(new Date(n6)));
                    }
                    else {
                        final long n7;
                        if ((n7 = n6 - COM.NextBus.util.c.b()) <= 0L) {
                            sb2.append("arrived");
                        }
                        else if (b3) {
                            if (n7 < 60000L) {
                                if (b4) {
                                    sb2.append(this.a.c().n());
                                }
                                else {
                                    sb2.append(this.a.c().m());
                                }
                            }
                            else {
                                sb2.append(e.a(n7 / 1000L));
                            }
                        }
                        else {
                            sb2.append(e.a(n7, true));
                        }
                    }
                }
                if (b4) {
                    sb2.append(" Dep.");
                }
                if (this.a.c().C().y()) {
                    sb2.append(" (" + s7 + ")");
                }
                if (this.a.c().C().x()) {
                    sb2.append(" (" + s6 + ")");
                }
                final String string = sb2.toString();
                if (n4 != 0) {
                    sb.append(" / ");
                }
                sb.append(string);
            }
        }
        return sb.toString();
    }
}
