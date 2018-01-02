// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.awt.event.MouseEvent;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import COM.NextBus.Predictor2.rmi.StopTupleBean;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.awt.geom.GeneralPath;
import COM.NextBus.Predictor2Comm.Location;
import COM.NextBus.HttpMapClient.e;
import java.util.Iterator;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Color;
import COM.NextBus.a.a;
import java.awt.Cursor;
import java.util.concurrent.ConcurrentHashMap;
import java.awt.Dimension;
import java.util.Set;
import java.awt.Point;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.LinkedHashMap;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ComponentListener;
import java.awt.Panel;

class MapCanvas extends Panel implements y, ComponentListener, MouseListener, MouseMotionListener
{
    private static final long serialVersionUID = 7023944877100875461L;
    private final O _mapInfo;
    private Image _offscreenImage;
    private Graphics2D _offscreenGraphics;
    private s _baseTile;
    private int _width;
    private int _height;
    private LinkedHashMap _busCanvasItems;
    private CopyOnWriteArrayList _routeCanvasItems;
    private Map _pointsOfInterest;
    private V _itemIds;
    private boolean _offscreenInvalid;
    private ae _viewport;
    private u _tiler;
    private int _bullseyeRadius;
    private Point _bullseyeCenter;
    private Point _originAtMousePress;
    private Point _pointOfMousePress;
    private int _mouseX;
    private int _mouseY;
    private Set _lastMouseOverStops;
    private boolean _showHiddenStopsLastValue;
    private static final double[] b;
    public static final int a;
    private static final int c;
    private static final double[] d;
    private static final String[] e;
    private static final double[] f;
    private static final String[] g;
    private static final Dimension h;
    
    MapCanvas(final O mapInfo) {
        this._originAtMousePress = null;
        this._pointOfMousePress = null;
        this._mouseX = -1;
        this._mouseY = -1;
        this._lastMouseOverStops = null;
        this._mapInfo = mapInfo;
        this._busCanvasItems = new LinkedHashMap();
        this._routeCanvasItems = new CopyOnWriteArrayList();
        this._pointsOfInterest = new ConcurrentHashMap();
        this._itemIds = new V();
        this._offscreenInvalid = true;
        final Dimension size = this.getSize();
        this._width = size.width;
        this._height = size.height;
        this.setCursor(Cursor.getPredefinedCursor(1));
        this.setBackground(new Color(COM.NextBus.a.a.b));
        if (!this._mapInfo.h()) {
            final Thread thread;
            (thread = new Thread(new ak(this), "Popup Checker")).setDaemon(true);
            thread.start();
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
        }
        this.addComponentListener(this);
        this.setLayout(new FlowLayout(1, 0, 0));
    }
    
    public final void b() {
        this._offscreenInvalid = true;
        this.repaint(100L);
    }
    
    public final void a(final ae viewport) {
        this._viewport = viewport;
        if (this._baseTile != null) {
            this._baseTile.a(MapCanvas.b[this._viewport.b()]);
        }
        this.b();
    }
    
    public boolean isFocusTraversable() {
        return false;
    }
    
    public final void a(final aj aj) {
        synchronized (this._busCanvasItems) {
            this._busCanvasItems.put(aj.x(), aj);
        }
        final MapCanvas mapCanvas;
        mapCanvas.b();
    }
    
    public final void a(final G g) {
        if (!this._routeCanvasItems.contains(g)) {
            this._routeCanvasItems.add(g);
        }
    }
    
    public final void b(final aj aj) {
        synchronized (this._busCanvasItems) {
            this._busCanvasItems.remove(aj.x());
        }
        final MapCanvas mapCanvas;
        mapCanvas.b();
    }
    
    public final void b(final G g) {
        this._routeCanvasItems.remove(g);
        this.b();
    }
    
    public final void a(final String s, final Point point, final int n, final Color color, final Color color2, final f f, final Font font) {
        this._itemIds.a(s, point, n, color, color2, f, font);
    }
    
    final void a(final f f) {
        final String a = f.a();
        if (!this._pointsOfInterest.containsKey(a)) {
            this._pointsOfInterest.put(a, f);
        }
    }
    
    public final void b(final f f) {
        this._pointsOfInterest.remove(f.a());
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(Graphics graphics) {
        if (this._offscreenInvalid) {
            this.f();
        }
        final MapCanvas mapCanvas = this;
        graphics = graphics;
        this = mapCanvas;
        if (mapCanvas._offscreenImage != null) {
            graphics.drawImage(this._offscreenImage, 0, 0, null);
        }
    }
    
    private synchronized void f() {
        if (this._offscreenGraphics == null) {
            return;
        }
        final boolean n;
        if ((n = this._mapInfo.C().n()) != this._showHiddenStopsLastValue) {
            this._showHiddenStopsLastValue = n;
            this._mapInfo.c.f();
            this._mapInfo.a("showHiddenStops changed, value=" + n);
        }
        this._offscreenGraphics.setColor(new Color(COM.NextBus.a.a.b));
        final Dimension size = this.getSize();
        this._offscreenGraphics.fillRect(0, 0, size.width, size.height);
        final MapCanvas mapCanvas2;
        if (this._mapInfo.a) {
            if (this._mapInfo.C().m()) {
                this._tiler.a(this._offscreenGraphics, this._viewport.c(), size);
            }
            synchronized (this._itemIds) {
                this._itemIds.a();
                final int size2 = this._routeCanvasItems.size();
                boolean b = false;
                if (size2 > 10) {
                    b = true;
                }
                final Iterator<G> iterator = (Iterator<G>)this._routeCanvasItems.iterator();
                while (iterator.hasNext()) {
                    iterator.next().a(this._offscreenGraphics, this, b);
                }
                synchronized (this._busCanvasItems) {
                    final Iterator<aj> iterator2 = this._busCanvasItems.values().iterator();
                    while (iterator2.hasNext()) {
                        iterator2.next().a(this._offscreenGraphics, this);
                    }
                }
                final MapCanvas mapCanvas;
                if (mapCanvas._mapInfo.C().g() || mapCanvas._mapInfo.C().h() || mapCanvas._mapInfo.C().i()) {
                    mapCanvas._itemIds.a(mapCanvas._offscreenGraphics);
                }
            }
            if (mapCanvas2._bullseyeRadius > 0) {
                mapCanvas2._offscreenGraphics.setColor(new Color(COM.NextBus.a.a.j));
                mapCanvas2._offscreenGraphics.drawOval(mapCanvas2._bullseyeCenter.x - mapCanvas2._bullseyeRadius, mapCanvas2._bullseyeCenter.y - mapCanvas2._bullseyeRadius, 2 * mapCanvas2._bullseyeRadius + 1, 2 * mapCanvas2._bullseyeRadius + 1);
                mapCanvas2._offscreenGraphics.drawOval(mapCanvas2._bullseyeCenter.x - mapCanvas2._bullseyeRadius + 1, mapCanvas2._bullseyeCenter.y - mapCanvas2._bullseyeRadius + 1, 2 * mapCanvas2._bullseyeRadius - 1, 2 * mapCanvas2._bullseyeRadius - 1);
            }
        }
        mapCanvas2._mapInfo.a(mapCanvas2._offscreenGraphics, mapCanvas2.getSize());
        if (mapCanvas2._baseTile != null) {
            mapCanvas2.a(mapCanvas2._offscreenGraphics);
        }
        mapCanvas2._offscreenInvalid = false;
    }
    
    final synchronized void a(final boolean b) {
        final o o = new o(this);
        this._mapInfo.c.a(false, o);
        if (o.c == Integer.MIN_VALUE && o.a == Integer.MAX_VALUE) {
            this._mapInfo.c.a(o);
            if (o.c == Integer.MIN_VALUE && o.a == Integer.MAX_VALUE) {
                this._mapInfo.c.a(true, o);
            }
        }
        if (b) {
            this._mapInfo.c.a(o);
        }
        final o o3;
        final o o2;
        if ((o2 = (o3 = o)).a == Integer.MAX_VALUE || o2.b == Integer.MAX_VALUE || o2.c == Integer.MAX_VALUE || o2.d == Integer.MIN_VALUE || o2.a == o2.c || o2.b == o2.d) {
            return;
        }
        final int n = o3.c - o3.a;
        final int n2 = o3.d - o3.b;
        int g = this.g();
        while (++g <= this.h()) {
            final double n3 = MapCanvas.b[g];
            if ((int)(this._baseTile.f / n3 * this._width / this._baseTile.e()) >= n2 && (int)(this._baseTile.c / n3 * this._height / this._baseTile.f()) >= n) {
                continue;
            }
            break;
        }
        --g;
        this.a(g, (o3.a + o3.c) / 2, (o3.b + o3.d) / 2, true);
        this._mapInfo.b();
    }
    
    private int g() {
        if (this._mapInfo.o()) {
            return 1;
        }
        return 0;
    }
    
    private int h() {
        if (this._mapInfo.o() && this._mapInfo.w().contains("ttc")) {
            return MapCanvas.c;
        }
        if (this._mapInfo.o()) {
            return MapCanvas.a;
        }
        return MapCanvas.b.length - 1;
    }
    
    final synchronized void c() {
        final e a;
        this._baseTile = new s((int)(((a = this._mapInfo.e.a(this._mapInfo.u())).n() + a.m()) * 6000000.0 / 2.0) / 400000 * 400000, (int)((a.l() + a.k()) * 6000000.0 / 2.0) / 400000 * 400000, 300000, 7.0, 600, 600);
        this._tiler = new u(this._mapInfo.c.a(), this._baseTile, this);
        this.b();
    }
    
    final Point a(Point point) {
        point = new Point(point.x - this._viewport.c().x, point.y - this._viewport.c().y);
        final MapCanvas mapCanvas = this;
        point = point;
        this = mapCanvas;
        return new Point(-1 * (int)(this._baseTile.d() * (point.y / this._baseTile.f() - 0.5)) + this._baseTile.b(), (int)(mapCanvas._baseTile.c() * (point.x / this._baseTile.e() - 0.5)) + this._baseTile.a());
    }
    
    public final Point a(int n, int n2) {
        n2 = (int)(((n2 - this._baseTile.a()) / this._baseTile.c() + 0.5) * this._baseTile.e());
        n = (int)(((this._baseTile.b() - n) / this._baseTile.d() + 0.5) * this._baseTile.f());
        n2 += this._viewport.c().x;
        n += this._viewport.c().y;
        return new Point(n2, n);
    }
    
    final GeneralPath a(final Location[] array) {
        final GeneralPath generalPath = new GeneralPath(0, array.length);
        for (int i = 0; i < array.length; ++i) {
            final Location location;
            final double n = (location = array[i]).d();
            final double n2 = location.c();
            final int n3 = (int)(((n - this._baseTile.a()) / this._baseTile.c() + 0.5) * this._baseTile.e()) + this._viewport.c().x;
            final int n4 = (int)(((this._baseTile.b() - n2) / this._baseTile.d() + 0.5) * this._baseTile.f()) + this._viewport.c().y;
            if (i == 0) {
                generalPath.moveTo(n3, n4);
            }
            else {
                generalPath.lineTo(n3, n4);
            }
        }
        return generalPath;
    }
    
    private void f(final int n) {
        final Point a = this.a(new Point(this._width / 2, this._height / 2));
        this.a(n, a.x, a.y, false);
        this._mapInfo.b();
    }
    
    public final void d() {
        if (this._viewport.b() < this.h()) {
            this.f(this._viewport.b() + 1);
        }
    }
    
    public final void a(final int n) {
        this.f(n);
    }
    
    public final void e() {
        if (this._viewport.b() > this.g()) {
            this.f(this._viewport.b() - 1);
        }
    }
    
    final void a(int n, int p4, final boolean b, final boolean b2) {
        final int n2 = n;
        final int n3 = p4;
        p4 = n2;
        final int n4 = (int)(((n3 - this._baseTile.a()) / this._baseTile.c() + 0.5) * this._baseTile.e());
        n = (int)(((this._baseTile.b() - p4) / this._baseTile.d() + 0.5) * this._baseTile.f());
        final Point point = new Point(n4, n);
        p4 = 0;
        if (b2) {
            p4 = this._mapInfo.P();
        }
        final Point c = this._viewport.c();
        final Point point2 = new Point(-point.x + this._width / 2, -point.y + (p4 + this._height) / 2);
        if (b || Math.abs(point2.x - c.x) > this._width / 2 - 10 || Math.abs(point2.y - c.y) > (p4 + this._height) / 2 - 10) {
            this._viewport.a(point2);
            this.b();
        }
    }
    
    public final void b(final int n) {
        this._baseTile.a(MapCanvas.b[n]);
    }
    
    private void a(final int n, final int n2, final int n3, final boolean b) {
        if (n == this.h()) {
            this._mapInfo.b.a.setEnabled(false);
            if (this._mapInfo.h != null) {
                this._mapInfo.h._zoomToBusButton.setEnabled(false);
            }
        }
        else {
            this._mapInfo.b.a.setEnabled(true);
            if (this._mapInfo.h != null) {
                this._mapInfo.h._zoomToBusButton.setEnabled(true);
            }
        }
        if (n == this.g()) {
            this._mapInfo.b.b.setEnabled(false);
        }
        else {
            this._mapInfo.b.b.setEnabled(true);
        }
        if (this._baseTile != null) {
            this.b(n);
            this._viewport.a(n);
            this.a(n2, n3, true, b);
            aj.s();
            this.b();
        }
    }
    
    final void c(final int bullseyeRadius) {
        this._bullseyeRadius = bullseyeRadius;
        this.b();
    }
    
    final void b(final int n, final int n2) {
        this._bullseyeCenter = this.a(n, n2);
    }
    
    private void c(final int mouseX, final int mouseY) {
        this._mouseX = mouseX;
        this._mouseY = mouseY;
        this.i();
    }
    
    private void i() {
        if (!this._mapInfo.a) {
            return;
        }
        if (this._mouseX >= 0 && this._mouseY >= 0) {
            final LinkedList<q> list = new LinkedList<q>();
            final HashSet<g> lastMouseOverStops = new HashSet<g>();
            final Iterator<f> iterator = this._pointsOfInterest.values().iterator();
            while (iterator.hasNext()) {
                final f f;
                if (!((f = iterator.next()) instanceof aj) || !this._mapInfo.C().g()) {
                    final Point a;
                    final int max;
                    if ((a = f.a(this)) == null || (max = Math.max(Math.abs(a.x - this._mouseX), Math.abs(a.y - this._mouseY))) > 5) {
                        continue;
                    }
                    list.add((q)f);
                    if (!(f instanceof q)) {
                        continue;
                    }
                    lastMouseOverStops.add(((q)f).b());
                }
            }
            if (this._mapInfo.C().g() || this._mapInfo.C().h() || this._mapInfo.C().i()) {
                synchronized (this._itemIds) {
                    final f a2;
                    if ((a2 = this._itemIds.a(new Point(this._mouseX, this._mouseY))) != null) {
                        list.add((q)a2);
                    }
                }
            }
            final MapCanvas mapCanvas;
            if (mapCanvas._mapInfo.C().q()) {
                list.add((q)new as(mapCanvas));
            }
            if (lastMouseOverStops.size() > 0) {
                if (mapCanvas._lastMouseOverStops == null || !mapCanvas._lastMouseOverStops.equals(lastMouseOverStops)) {
                    mapCanvas._lastMouseOverStops = lastMouseOverStops;
                    mapCanvas._mapInfo.c.b(a(lastMouseOverStops));
                    mapCanvas._mapInfo.a("new mouseover stops: " + lastMouseOverStops);
                }
            }
            else if (mapCanvas._lastMouseOverStops != null) {
                mapCanvas._lastMouseOverStops = null;
                mapCanvas._mapInfo.c.b((Map)null);
                mapCanvas._mapInfo.a("no mouseover stops");
            }
            final int size;
            if ((size = list.size()) <= 0) {
                mapCanvas._mapInfo.i.a();
                return;
            }
            final aq[] array = list.toArray(new aq[size]);
            final k i = mapCanvas._mapInfo.i;
            final aq[] array2 = array;
            final Point point = new Point(mapCanvas._mouseX, mapCanvas._mouseY);
            mapCanvas.getSize();
            i.a(array2, point);
        }
        else {
            this._mapInfo.i.a();
        }
    }
    
    private static final Map a(final Set set) {
        final HashMap<Object, Set<Object>> hashMap = new HashMap<Object, Set<Object>>();
        final Iterator<g> iterator = set.iterator();
        while (iterator.hasNext()) {
            final g g;
            final String a = (g = iterator.next()).a();
            final StopTupleBean stopTupleBean = new StopTupleBean(g.b(), g.c(), g.d());
            Object o;
            if ((o = hashMap.get(a)) == null) {
                o = new HashSet<StopTupleBean>();
                hashMap.put(a, (Set<Object>)o);
            }
            ((Set<StopTupleBean>)o).add(stopTupleBean);
        }
        return hashMap;
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        final Dimension size = this.getSize();
        this._width = size.width;
        this._height = size.height;
        final Dimension size2;
        final int width = (size2 = this.getSize()).width;
        final int height = size2.height;
        this._offscreenImage = this.createImage(width, height);
        if (this._offscreenGraphics != null) {
            this._offscreenGraphics.dispose();
        }
        (this._offscreenGraphics = (Graphics2D)this._offscreenImage.getGraphics()).setClip(0, 0, width, height);
        this._offscreenGraphics.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        this.b();
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            final MapCanvas mapCanvas = this;
            final Point point = mouseEvent.getPoint();
            this = mapCanvas;
            final Point a = mapCanvas.a(point);
            this.a(a.x, a.y, true, false);
            this.d();
            this._mapInfo.b();
            return;
        }
        if (!this._mapInfo.o()) {
            synchronized (this._itemIds) {
                final aj aj;
                if ((aj = (aj)this._itemIds.a(new Point(this._mouseX, this._mouseY))) != null) {
                    this._mapInfo.a(aj);
                }
            }
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this._mouseX = -1;
        this._mouseY = -1;
        this.i();
        this._pointOfMousePress = mouseEvent.getPoint();
        this._originAtMousePress = this._viewport.c();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (!this._mapInfo.a) {
            return;
        }
        this._pointOfMousePress = null;
        this._originAtMousePress = this._viewport.c();
        this.setCursor(Cursor.getPredefinedCursor(1));
        final Point point = mouseEvent.getPoint();
        this.c(point.x, point.y);
        this._mapInfo.b();
        this.b();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getPredefinedCursor(13));
        if (this._pointOfMousePress == null) {
            this.mousePressed(mouseEvent);
        }
        final Point point = mouseEvent.getPoint();
        this._viewport.a(new Point(this._originAtMousePress.x + point.x - this._pointOfMousePress.x, this._originAtMousePress.y + point.y - this._pointOfMousePress.y));
        this.b();
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        this.c(point.x, point.y);
    }
    
    private void a(final Graphics graphics) {
        int n = 0;
        double[] array = MapCanvas.d;
        String[] array2 = MapCanvas.e;
        if ("kph".equals(this._mapInfo.e.a(this._mapInfo.u()).o())) {
            array = MapCanvas.f;
            array2 = MapCanvas.g;
        }
        int n2;
        while ((n2 = (int)(array[n] / (111060.0 * Math.cos(this._baseTile.b() / 6000000.0 * 3.141592653589793 / 180.0)) / (this._baseTile.c() / 6000000.0) * this._baseTile.e())) <= 40 && n != array2.length - 1) {
            ++n;
        }
        final String s = array2[n];
        graphics.setColor(Color.black);
        graphics.fillRect(30, this._height - 40, n2, 3);
        graphics.setFont(new Font("SansSerif", 1, 10));
        graphics.drawString(s, 30 + n2 / 2 - graphics.getFontMetrics().charsWidth(s.toCharArray(), 0, s.length()) / 2, this._height - 40 + 17);
    }
    
    public final double d(int d) {
        return 20 * ((d = this._baseTile.d()) / 6000000.0) / this._baseTile.f();
    }
    
    public final double e(int c) {
        return 20 * ((c = this._baseTile.c()) / 6000000.0) / this._baseTile.e();
    }
    
    public Dimension getMinimumSize() {
        return MapCanvas.h;
    }
    
    public final void a() {
        this.b();
    }
    
    public final void a(final String s) {
        Point point;
        if (s.equals("North")) {
            point = new Point(this._viewport.c().x, this._viewport.c().y + 10);
        }
        else if (s.equals("South")) {
            point = new Point(this._viewport.c().x, this._viewport.c().y - 10);
        }
        else if (s.equals("East")) {
            point = new Point(this._viewport.c().x - 10, this._viewport.c().y);
        }
        else {
            if (!s.equals("West")) {
                throw new RuntimeException("Unsupported nudge direction: " + s);
            }
            point = new Point(this._viewport.c().x + 10, this._viewport.c().y);
        }
        this._viewport.a(point);
        this.b();
    }
    
    static {
        a = (b = new double[] { 0.015, 0.04, 0.09, 0.2, 0.34, 0.46, 0.59, 0.77, 1.0, 1.3, 1.7, 2.2, 2.9, 3.8, 4.0, 5.0, 6.5, 9.5, 15.0, 25.0, 50.0, 100.0 }).length - 1;
        c = MapCanvas.b.length - 7;
        d = new double[] { 30.48, 60.96, 152.4, 402.336, 804.672, 1609.344, 3218.688, 8046.72, 16093.44, 32186.88, 80467.2 };
        e = new String[] { "100 feet", "200 feet", "500 feet", "1/4 mile", "1/2 mile", " 1 mile ", "2 miles", "5 miles", "10 miles", "20 miles", "50 miles" };
        f = new double[] { 25.0, 50.0, 100.0, 200.0, 500.0, 1000.0, 2000.0, 5000.0, 10000.0, 20000.0, 50000.0, 100000.0 };
        g = new String[] { "25 meters", "50 meters", "100 meters", "200 meters", "500 meters", "1 kilometer", "2 kilometers ", "5 kilometers", "10 kilometers", "20 kilometers", "50 kilometers", "100 kilometers" };
        h = new Dimension(300, 200);
    }
}
