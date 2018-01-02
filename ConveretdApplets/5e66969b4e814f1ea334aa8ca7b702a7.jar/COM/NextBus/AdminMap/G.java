// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.util.Iterator;
import java.awt.event.ItemEvent;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import COM.NextBus.Predictor2Comm.StopPrediction;
import COM.NextBus.Predictor2Comm.Stop;
import COM.NextBus.Predictor2Comm.Location;
import java.util.Enumeration;
import COM.NextBus.Predictor2Comm.RouteInfo;
import COM.NextBus.HttpMapClient.ConnectionException;
import java.util.Vector;
import java.awt.event.MouseEvent;
import java.util.concurrent.ConcurrentHashMap;
import COM.NextBus.Predictor2Comm.RouteInfoBase;
import COM.NextBus.Predictor2Comm.PathInfo;
import java.util.Map;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.ItemListener;

public final class G implements ItemListener, MouseListener
{
    private final String a;
    private final String b;
    private final String c;
    private final Color d;
    private final Map e;
    private final O f;
    private final t g;
    private boolean h;
    private boolean i;
    private PathInfo[] j;
    private double[][] k;
    private q[] l;
    private boolean m;
    
    public G(final O f, final String a, final RouteInfoBase routeInfoBase, final t g) {
        this.e = new ConcurrentHashMap();
        this.i = false;
        this.l = null;
        this.m = false;
        this.f = f;
        this.a = a;
        this.b = routeInfoBase.d();
        this.c = routeInfoBase.e();
        this.g = g;
        new StringBuilder().append(a).append("-").append(this.b).toString();
        this.h = false;
        this.d = new Color(routeInfoBase.f());
        this.g.a(this.b, this.d);
    }
    
    public final void a() {
        this.f.f.a(this);
        this.f.f.addMouseListener(this);
    }
    
    public final String b() {
        return this.a;
    }
    
    public final O c() {
        return this.f;
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        this.m = true;
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        this.m = false;
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    private synchronized void j() {
        if (this.i) {
            return;
        }
        try {
            final RouteInfo c2;
            final Enumeration c = (c2 = this.f.e.c(this.a, this.b)).c();
            final Vector<PathInfo> vector = new Vector<PathInfo>();
            while (c.hasMoreElements()) {
                final PathInfo d;
                if ((d = this.f.e.d(this.a, c.nextElement())) != null) {
                    vector.add(d);
                }
            }
            final PathInfo[] j = vector.toArray(new PathInfo[vector.size()]);
            this.j = j;
            this.k = new double[j.length][];
            for (int i = 0; i < j.length; ++i) {
                final Location[] a;
                final int n = (a = j[i].a()).length - 1;
                this.k[i] = new double[n];
                for (int k = 0; k < n; ++k) {
                    final Location location = a[k];
                    final Location location2 = a[k + 1];
                    final double n2 = (k == 0) ? 0.0 : this.k[i][k - 1];
                    final double[] array = this.k[i];
                    final int n3 = k;
                    final Location location3 = location;
                    final Location location4 = location2;
                    final Location location5 = location3;
                    final double n4 = location4.a() - location5.a();
                    final double n5 = (location4.b() - location5.b()) * Math.cos(0.5 * (location5.a() + location4.a()) * 0.017453292519943295);
                    array[n3] = Math.sqrt(n4 * n4 + n5 * n5) * 60.0 * 1851.85185185185 + n2;
                }
            }
            final int a2 = c2.a();
            this.l = new q[a2];
            for (int l = 0; l < a2; ++l) {
                final Stop a3 = c2.a(l);
                this.l[l] = new q(a3.a(), a3.c(), a3.d(), a3.e(), this);
            }
        }
        catch (ConnectionException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        this.i = true;
    }
    
    public final Color d() {
        return this.d;
    }
    
    public final void a(final o o) {
        this.j();
        for (int i = 0; i < this.j.length; ++i) {
            final Location[] a = this.j[i].a();
            for (int j = 0; j < a.length; ++j) {
                final Location location = a[j];
                o.a = Math.min(o.a, location.c());
                o.c = Math.max(o.c, location.c());
                o.b = Math.min(o.b, location.d());
                o.d = Math.max(o.d, location.d());
            }
        }
    }
    
    final void e() {
        this.j();
        this.f.f.b(this);
        for (int i = 0; i < this.l.length; ++i) {
            this.l[i].c();
        }
    }
    
    final String f() {
        return this.c;
    }
    
    final String g() {
        return this.b;
    }
    
    final StopPrediction a(String b, final String s) {
        b = b(b, s);
        final m m;
        if ((m = this.e.get(b)) == null || m.a()) {
            return null;
        }
        return m.a;
    }
    
    public final void a(final Graphics2D graphics2D, final MapCanvas mapCanvas, final boolean b) {
        this.j();
        if (this.h) {
            final Color d = this.d;
            graphics2D.setColor(new Color(d.getRed(), d.getGreen(), d.getBlue(), 210));
            final Stroke stroke = graphics2D.getStroke();
            graphics2D.setStroke(new BasicStroke(4.0f, 1, 1));
            if (!b || !this.m) {
                for (int i = 0; i < this.j.length; ++i) {
                    graphics2D.draw(this.f.f.a(this.j[i].a()));
                }
            }
            graphics2D.setStroke(stroke);
            for (int j = 0; j < this.l.length; ++j) {
                final q q;
                final Point a = (q = this.l[j]).a(mapCanvas);
                if (q.e() || this.f.C().n()) {
                    graphics2D.setColor(this.d);
                    graphics2D.fillRect(a.x - 3, a.y - 3, 7, 7);
                    graphics2D.setColor(Color.white);
                    graphics2D.fillRect(a.x - 1, a.y - 1, 3, 3);
                }
            }
        }
    }
    
    final void a(final StopPrediction stopPrediction) {
        this.e.put(b(stopPrediction.b(), stopPrediction.c()), new m(stopPrediction));
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        final boolean b2;
        final boolean b = b2 = (itemEvent.getStateChange() == 1);
        this.a(b2);
        for (final t t : this.f.c.b()) {
            if (!b2) {
                t.e(this.b);
            }
        }
        if (b && !this.f.O()) {
            this.f.c.e();
        }
        this.f.b();
    }
    
    public final void h() {
        this.j();
        for (int i = 0; i < this.l.length; ++i) {
            this.l[i].d();
        }
    }
    
    public final void a(final boolean h) {
        this.h = h;
        this.f.J().e().a(this.b, h);
        if (h) {
            this.f.f.a(this);
            this.f.f.b();
        }
        else {
            this.f.f.b(this);
        }
        if (!this.f.O()) {
            this.f.e.a(this.a, this.b, h);
        }
    }
    
    public final boolean i() {
        return this.h;
    }
    
    public final double a(final String s, final double n) {
        this.j();
        double n2 = -1.0;
        try {
            int n3 = -1;
            for (int i = 0; i < this.j.length; ++i) {
                if (this.j[i].b().indexOf(s) != -1) {
                    n3 = i;
                    break;
                }
            }
            if (n3 == -1) {
                return n2;
            }
            int n4 = -1;
            for (int j = 0; j < this.k[n3].length; ++j) {
                if (this.k[n3][j] >= n) {
                    n4 = j;
                    break;
                }
            }
            if (n4 == -1) {
                return n2;
            }
            final Location[] a;
            final Location location = (a = this.j[n3].a())[n4];
            final Location location2 = a[n4 + 1];
            final Location location3 = location;
            final Location location4 = location2;
            final Location location5 = location3;
            double atan2;
            if ((atan2 = Math.atan2((location4.b() - location5.b()) * Math.cos(location5.a() * 0.017453292519943295), location4.a() - location5.a())) < 0.0) {
                atan2 += 6.283185307179586;
            }
            n2 = atan2 * 57.29577951308232;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
        return n2;
    }
    
    public final Location b(String s2, final double n) {
        this.j();
        this.j();
        int i = 0;
        while (true) {
            while (i < this.j.length) {
                if (this.j[i].b().indexOf(s) != -1) {
                    final int n2 = i;
                    final int n3;
                    s2 = (String)(n3 = n2);
                    this.j();
                    int n4 = 0;
                    Label_0130: {
                        if (n3 >= 0) {
                            for (int j = 0; j < this.k[n3].length; ++j) {
                                if (this.k[n3][j] >= n) {
                                    n4 = j + 1;
                                    break Label_0130;
                                }
                            }
                        }
                        n4 = -1;
                    }
                    final int n5 = n4;
                    if (s2 < 0 || n5 < 1) {
                        return null;
                    }
                    try {
                        final PathInfo pathInfo;
                        final Location location = (pathInfo = this.j[s2]).a()[n5 - 1];
                        final Location location2 = pathInfo.a()[n5];
                        final int n6 = n5 - 1;
                        final double n7 = this.k[s2][n6];
                        final double n8 = (n6 == 0) ? 0.0 : this.k[s2][n6 - 1];
                        return Location.a(location, location2, (n - n8) / (n7 - n8));
                    }
                    catch (ArrayIndexOutOfBoundsException ex) {
                        ex.printStackTrace();
                        System.out.println("array index out of bounds: locationIndex=" + n5 + " pathIndex=" + (int)s2);
                        return null;
                    }
                }
                else {
                    ++i;
                }
            }
            final int n2 = -1;
            continue;
        }
    }
    
    private static String b(final String s, final String s2) {
        if (s2 == null || s2.equals("")) {
            return s;
        }
        return s + "_compounded_with_direction_" + s2;
    }
    
    public static Color a(final Color color) {
        if (color.getRed() * 0.3 + color.getGreen() * 0.59 + color.getBlue() * 0.11 > 127.0) {
            return Color.black;
        }
        return Color.white;
    }
}
