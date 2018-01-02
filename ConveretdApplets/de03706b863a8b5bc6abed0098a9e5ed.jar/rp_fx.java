import java.awt.event.KeyEvent;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.Component;
import javax.swing.SwingUtilities;
import java.awt.Frame;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.io.InputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.Window;
import java.awt.image.RenderedImage;
import javax.imageio.ImageWriteParam;
import javax.imageio.metadata.IIOMetadata;
import java.util.List;
import javax.imageio.IIOImage;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import java.util.Vector;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.awt.Point;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.ToolTipManager;
import java.util.Date;
import java.util.ArrayList;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_fx extends rp_bO implements ActionListener, KeyListener, MouseListener, MouseMotionListener, rp_dw
{
    public rp_N a;
    public rp_cf a;
    public rp_au a;
    private Image a;
    private Image b;
    public rp_v a;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private boolean a;
    public int a;
    private rp_cw a;
    private Cursor a;
    private Cursor b;
    public rp_dW a;
    public ArrayList a;
    private long a;
    public rp_v b;
    public int b;
    public int c;
    private boolean b;
    private boolean c;
    private rp_db a;
    private int l;
    private long b;
    
    public rp_fx(final rp_au a) {
        super(rp_au.a);
        this.a = null;
        this.a = null;
        this.b = null;
        this.a = null;
        this.a = 0;
        this.a = new rp_cw();
        this.a = null;
        this.b = null;
        this.a = null;
        this.a = new ArrayList();
        this.a = 0L;
        this.b = null;
        this.b = rp_au.a.a("gr_tp");
        this.c = 0;
        this.b = false;
        this.c = false;
        this.a = null;
        this.l = 0;
        this.b = 0L;
        this.a = a;
        (this.a = new rp_N(this, this.a)).a();
        rp_dt.a = this.a;
        this.setDoubleBuffered(true);
        if (this.a.a() != null && this.a.b() != null) {
            this.a.add(new rp_dZ(this.a.a(), this.a.b()));
        }
        this.a = new Date().getTime();
        ToolTipManager.sharedInstance().registerComponent(this);
        (this = this).addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.a(new Dimension(8229600, 8229600));
        this.a = this.a.a().a("res/cur_measure.png", true);
        this.b = this.a.a().a("res/cur_text.png", true);
    }
    
    public final void a(Rectangle a) {
        if (a != null) {
            (a = this.a().a(a)).grow(4, 4);
            this.repaint(a);
        }
    }
    
    public final void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final rp_ew a;
        (a = this.a()).a(rp_aJ.j, rp_aJ.k);
        if (this.b != 0) {
            a.a(this.b, this.a(), rp_aJ.e);
        }
        this.a.a(a);
        if (this.a != null) {
            this.a.a(graphics);
        }
        if (this.a == 3) {
            this.a.a(a, this.a);
        }
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.a(actionEvent.getActionCommand());
    }
    
    public final void a(final String s) {
        rp_C.a(10, "PlaneEP command: " + s);
        if (!this.a.a().b(s, false)) {
            return;
        }
        if (s.equals("cmUndo")) {
            final rp_N a2;
            final rp_ah a;
            int n2;
            int n;
            if ((a = (a2 = (this = this).a).a).c <= 0) {
                n = (n2 = -1);
            }
            else {
                final rp_ah rp_ah = a;
                n2 = (rp_ah.c = (n = rp_ah.c - 1));
            }
            final int n3 = n2;
            if (n != -1) {
                a2.a.a[n3].b();
                a2.c();
                a2.a(3, true);
                a2.a(true);
            }
            return;
        }
        if (s.equals("cmRedo")) {
            final rp_N a4;
            final rp_ah a3;
            final rp_ah rp_ah2 = a3 = (a4 = (this = this).a).a;
            int n4;
            int c;
            if (rp_ah2.c >= rp_ah2.b) {
                c = (n4 = -1);
            }
            else {
                final rp_ah rp_ah3 = a3;
                rp_ah3.c = (n4 = (c = rp_ah3.c)) + 1;
            }
            final int n5 = n4;
            if (c != -1) {
                a4.a.a(n5);
                a4.c();
                a4.a(3, true);
                a4.a(true);
            }
            return;
        }
        if (s.equals("cmArrow")) {
            this.g();
            return;
        }
        if (s.equals("cmTape")) {
            (this = this).a = 3;
            this.a.e = 0;
            return;
        }
        if (s.equals("cmText")) {
            (this = this).a = 5;
            this.a.d(this.a);
            this.a.e = 0;
            return;
        }
        if (s.equals("cmDel")) {
            this.h();
            return;
        }
        if (s.equals("cmStartOver")) {
            (this = this).a.a();
            return;
        }
        if (s.equals("cmDup")) {
            (this = this).a.b((rp_dC[])null);
            return;
        }
        if (s.equals("cmDimAdd")) {
            (this = this).a.c((rp_dC[])null);
            return;
        }
        if (s.equals("cmDimRem")) {
            (this = this).a.d((rp_dC[])null);
            return;
        }
        if (s.equals("cmZoomIn")) {
            (this = this).a((int)(super.a.a / 1.5));
            if (super.a != null) {
                super.a.a();
            }
            return;
        }
        if (s.equals("cmZoomOut")) {
            (this = this).a((int)(super.a.a * 1.5));
            if (super.a != null) {
                super.a.a();
            }
            return;
        }
        if (s.equals("cmZoomRoom")) {
            this.d();
            return;
        }
        if (s.equals("cmRWWL")) {
            this.a.b(s);
            return;
        }
        if (s.equals("cmRoomW")) {
            this.a.b(s);
            return;
        }
        if (s.equals("cmNothing")) {
            return;
        }
        System.out.println("PlaneEP:Unknown command " + s);
    }
    
    public final synchronized void c() {
        final long time = new Date().getTime();
        final int a;
        if ((a = this.a.a("autosave")) > 0 && time - this.a > a * 60000 && this.a.a().a("", true)) {
            if (this.b == null) {
                final rp_v a2;
                if ((a2 = this.a.a().a()) == null) {
                    return;
                }
                rp_v a3;
                if ((a3 = a2.a("-- Autosave --")) == null) {
                    final rp_bg rp_bg;
                    if (!(rp_bg = (rp_bg)rp_au.a.a().a(rp_aw.g, new rp_v[] { a2 }, "-- Autosave --")).a() || rp_bg.a(1) == null) {
                        return;
                    }
                    a3 = new rp_v("-- Autosave --", Integer.parseInt(rp_bg.a(1)), -1);
                    a2.a(a3);
                }
                if (!a3.a()) {
                    return;
                }
                a3.a(this.b = new rp_v(new SimpleDateFormat("yyyyMMdd-HHmm").format(Calendar.getInstance().getTime()), ""));
            }
            rp_au.a(this.b);
            this.a = time;
        }
    }
    
    public final void a(final rp_v a) {
        ((rp_au)(this.a = a)).h();
    }
    
    private BufferedImage a(int b, int n, final boolean b2) {
        final rp_az rp_az = new rp_az(rp_au.a, this.a, "");
        final rp_fK a = rp_au.a.a();
        final int n2 = b2 ? 1 : 2;
        final int n3 = b;
        final int n4 = n;
        final int n5 = n3;
        n = n2;
        final rp_fK rp_fK = a;
        final rp_az rp_az2 = rp_az;
        rp_az.a.width = n5;
        rp_az2.a.height = n4;
        if (n == 1 && n5 > 0 && n4 > 0) {
            rp_az2.a.width = n5;
            rp_az2.a.height = n4;
            rp_aJ.f = true;
            rp_aJ.g = false;
            rp_aJ.i = false;
            rp_aJ.k = false;
            rp_aJ.g = "";
            rp_aJ.l = false;
            rp_aJ.m = false;
            rp_aJ.e = 2;
        }
        if (rp_aJ.e != 2) {
            final Rectangle a2 = rp_az2.a(rp_az2.a);
            int width = rp_az2.a.width;
            int height = rp_az2.a.height;
            if (rp_aJ.e == 1) {
                width = (int)Math.round(a2.width / 100.0 * 2.834645669291339 * 0.020833333333333332);
                height = (int)Math.round(a2.height / 100.0 * 2.834645669291339 * 0.020833333333333332);
            }
            if (rp_aJ.e == 0) {
                width = (int)Math.round(a2.width / 100.0 * 2.834645669291339 * (1000.0 / rp_aJ.f));
                height = (int)Math.round(a2.height / 100.0 * 2.834645669291339 * (1000.0 / rp_aJ.f));
            }
            if (rp_aJ.l) {
                height += 20;
            }
            rp_az2.a.width = width;
            rp_az2.a.height = height;
        }
        rp_az2.a = new Rectangle(0, 0, rp_az2.a.width, rp_az2.a.height);
        final BufferedImage bufferedImage = new BufferedImage(rp_az2.a.width, rp_az2.a.height, 1);
        rp_az.a(rp_az2.a = bufferedImage.getGraphics());
        System.out.println("Blanking out: " + rp_az2.a.width + "," + rp_az2.a.height);
        rp_az2.a.setColor(Color.white);
        rp_az2.a.fillRect(0, 0, rp_az2.a.width, rp_az2.a.height);
        rp_az2.a.setColor(Color.black);
        if (rp_aJ.l) {
            final Rectangle a3 = rp_az2.a;
            a3.height -= 20;
        }
        final Rectangle a4 = rp_az2.a(rp_az2.a);
        final Point point;
        (point = new Point()).setLocation(0, 0);
        (rp_az2.a = new rp_ew(rp_fK, rp_az2.a, point, 1)).a(a4, rp_az2.a.getSize(), point);
        final Point point2 = point;
        point2.y -= rp_az2.a.y * rp_az2.a.a;
        rp_az2.a.a(point);
        rp_az2.a.a = true;
        rp_az2.a.b = rp_aJ.k;
        rp_az2.a.c = rp_aJ.g;
        rp_az2.a.a = rp_az2.a;
        rp_az2.a();
        rp_az2.a.a.setClip(rp_az2.a.x, rp_az2.a.y, rp_az2.a.width, rp_az2.a.height);
        if (rp_aJ.f) {
            if ((b = rp_aJ.b()) == 0) {
                b = 1;
            }
            rp_az2.a.a(b, rp_aJ.c(), rp_aJ.e);
        }
        rp_az2.a.a(rp_az2.a, rp_az2.a, null);
        rp_az2.a.a.setClip(0, 0, rp_az2.a.width, rp_az2.a.height);
        rp_az2.a.dispose();
        rp_az2.a = null;
        return bufferedImage;
    }
    
    public final rp_fU a(final rp_gi rp_gi, final rp_fl rp_fl) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8192);
        final rp_eg rp_eg = new rp_eg(byteArrayOutputStream);
        final Vector<String> vector;
        (vector = new Vector<String>()).addElement(rp_eg.a("timestamp", "" + System.currentTimeMillis()));
        vector.addElement(rp_eg.a("eEP-version", "5.60.36"));
        vector.addElement(rp_eg.a("file-version", "4"));
        rp_eg.a("eEP-Plan", vector);
        if (rp_gi != null) {
            a(rp_gi, rp_eg);
        }
        this.a.a(rp_eg);
        rp_eg.a("plan", (Vector)null);
        if (this.a != null) {
            this.a.a(rp_eg);
        }
        this.a.a(rp_eg);
        rp_eg.a();
        rp_eg.a();
        byteArrayOutputStream.flush();
        RenderedImage renderedImage;
        if (rp_fl == rp_fl.c) {
            renderedImage = null;
        }
        else if (rp_fl == rp_fl.a) {
            renderedImage = this.a(540, 634, false);
        }
        else {
            renderedImage = this.a(200, 150, true);
        }
        byte[] byteArray = null;
        if (renderedImage != null) {
            final ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            final ImageWriter imageWriter = ImageIO.getImageWritersByFormatName("jpg").next();
            final JPEGImageWriteParam jpegImageWriteParam;
            (jpegImageWriteParam = new JPEGImageWriteParam(Locale.getDefault())).setCompressionMode(2);
            jpegImageWriteParam.setCompressionQuality(this.a.a());
            imageWriter.setOutput(ImageIO.createImageOutputStream(byteArrayOutputStream2));
            imageWriter.write(null, new IIOImage(renderedImage, null, null), jpegImageWriteParam);
            byteArray = byteArrayOutputStream2.toByteArray();
        }
        return new rp_fU(byteArrayOutputStream, byteArray);
    }
    
    private static void a(rp_gi rp_gi, final rp_eg rp_eg) {
        final Vector a = rp_gi.a;
        final Vector b = rp_gi.b;
        final String text = (rp_gi = rp_gi).a.getText();
        rp_eg.a("e-mail", (Vector)null);
        rp_eg.a("mailto", (Vector)null);
        if (a != null && a.size() > 0) {
            for (int i = 0; i < a.size(); ++i) {
                rp_eg.a("address", null, a.elementAt(i));
            }
        }
        rp_eg.a();
        if (b != null && b.size() > 0) {
            rp_eg.a("mailcc", (Vector)null);
            for (int j = 0; j < b.size(); ++j) {
                rp_eg.a("address", null, b.elementAt(j));
            }
            rp_eg.a();
        }
        if (text != null && text.length() > 0) {
            rp_eg.a("message", null, text);
        }
        rp_eg.a();
    }
    
    public final boolean a(final rp_v rp_v) {
        if (rp_v == null) {
            return false;
        }
        if (this.a(((rp_aS)this.a.a().a(rp_aw.b, new rp_v[] { rp_v }, null)).a())) {
            this.a(rp_v);
            return true;
        }
        rp_bd.a(this.a.a().a(), this.a.a(), "err", "pe7");
        return false;
    }
    
    public final boolean b() {
        final rp_bZ rp_bZ;
        final String b = (rp_bZ = (rp_bZ)this.a.a()).b("temp-plan", "");
        rp_C.a(4, "loading " + b);
        rp_bZ.a("temp-plan", "");
        final InputStream a = rp_bZ.a("action=plan-load-temp&name=" + rp_C.e(b));
        try {
            final rp_eA rp_eA = new rp_eA();
            rp_eA.a(new BufferedReader(new InputStreamReader(a)));
            if (this.a(rp_eA)) {
                this.d();
                this.a.a(true);
                return true;
            }
            this.a.a("cmRWWL");
        }
        catch (rp_u rp_u) {
            rp_C.a(1, "plan-load-temp XML parsing error: " + rp_u);
        }
        catch (IOException ex) {
            rp_C.a(1, "plan-load-temp XML IO error: " + ex);
            return false;
        }
        return false;
    }
    
    public final boolean a(final rp_eA rp_eA) {
        if (rp_eA == null) {
            return false;
        }
        final Cursor cursor = this.getCursor();
        try {
            this.setCursor(Cursor.getPredefinedCursor(3));
            if (this.b(rp_eA)) {
                this.a.a(false);
                this.d();
                this.a.a(3, true);
                this.repaint();
            }
        }
        finally {
            this.setCursor(cursor);
        }
        return true;
    }
    
    private boolean b(final rp_eA rp_eA) {
        try {
            if (!rp_eA.a.equals("eEP-Plan")) {
                return false;
            }
            this.a = 0;
            this.a = null;
            this.a = null;
            this.b = false;
            this.a.a();
            final int a = rp_eA.a("file-version", 1);
            final String a2 = rp_eA.a("producer", "eEP");
            final Enumeration<rp_eA> elements = (Enumeration<rp_eA>)rp_eA.a.elements();
            while (elements.hasMoreElements()) {
                final rp_eA rp_eA2;
                if ((rp_eA2 = elements.nextElement()).a.equals("plan")) {
                    final rp_eA rp_eA3 = rp_eA2;
                    final int n = a;
                    final Enumeration elements2 = rp_eA3.a.elements();
                    while (elements2.hasMoreElements()) {
                        final rp_eA rp_eA4;
                        if ((rp_eA4 = elements2.nextElement()).a.equals("items")) {
                            this.a.clear();
                            this.a.a(rp_eA4);
                            final Enumeration a3 = this.a.a(3);
                            while (a3.hasMoreElements()) {
                                final rp_dC rp_dC = a3.nextElement();
                                this.a(rp_dC.a(), rp_dC.b());
                            }
                        }
                        final rp_eA rp_eA5;
                        if (rp_eA4.a.equals("roomshape") && rp_eA4.a("type").equals("predef") && (rp_eA5 = rp_eA4.a().elementAt(0)) != null && rp_eA5.a.equals("predef")) {
                            final rp_eA rp_eA6 = rp_eA5;
                            final rp_dW rp_dW;
                            (rp_dW = new rp_dW()).b = rp_eA6.a("shape", -1);
                            rp_dW.c = rp_eA6.a("width", 14080);
                            rp_dW.a = rp_eA6.a("orientation", 0);
                            rp_dW.a = new int[6];
                            final StringTokenizer stringTokenizer = new StringTokenizer(rp_eA6.b, ";");
                            for (int n2 = 0; stringTokenizer.hasMoreTokens() && n2 < 6; ++n2) {
                                rp_dW.a[n2] = rp_C.a(stringTokenizer.nextToken());
                            }
                            final rp_dW a4;
                            if ((a4 = rp_dW) == null) {
                                continue;
                            }
                            this.a = a4;
                        }
                    }
                    if (n < 2 && this.a != null) {
                        this.a.a();
                        final Dimension a5 = this.a();
                        this.a(new Dimension(1000000, 800000));
                        this.a.a(this, true);
                        this.a(new Dimension(a5));
                        this.a.b();
                    }
                    if (n < 3) {
                        final Rectangle a6 = this.a.a(this.getGraphics());
                        final Rectangle a7 = this.a.a(this.getGraphics(), this.a, false);
                        if (a6 != null) {
                            a7.union(a6);
                        }
                        final Dimension dimension;
                        (dimension = new Dimension()).width = Math.max(0, (this.a().width - a7.width) / 2) - a7.x;
                        dimension.height = Math.max(0, (this.a().height - a7.height) / 2) - a7.y;
                        final Enumeration a8 = this.a.a(3);
                        while (a8.hasMoreElements()) {
                            a8.nextElement().b(dimension.width, dimension.height);
                        }
                    }
                    final rp_ah a9;
                    (a9 = this.a.a).c = 0;
                    a9.b = 0;
                }
            }
            if (a2.equalsIgnoreCase("EPImport")) {
                final Rectangle a10 = this.a.a(this.getGraphics(), this.a, false);
                this.a.a(-1, Math.max(0, (this.a().width - a10.width) / 2 - a10.x), Math.max(0, (this.a().height - a10.height) / 2 - a10.y));
                this.a.f(null);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public final void a(final rp_gi rp_gi) {
        final rp_fK a = this.a.a();
        final Vector a2 = rp_gi.a;
        final Vector b = rp_gi.b;
        if (a2 == null || a2.size() <= 0) {
            if (b == null || b.size() <= 0) {
                return;
            }
        }
        rp_fU a3;
        try {
            a3 = this.a(rp_gi, rp_fl.a);
        }
        catch (IOException ex) {
            System.out.println("PlaneEP.email: ");
            return;
        }
        final rp_fq a4;
        if (!(a4 = this.a.a().a(rp_aw.k, new rp_v[] { this.a }, a3)).a() && a4 instanceof rp_bg) {
            ((rp_bg)a4).a(a);
        }
    }
    
    public final void b(final rp_gi rp_gi) {
        rp_fU a;
        try {
            a = this.a(rp_gi, rp_fl.a);
        }
        catch (IOException ex) {
            System.out.println("PlaneEP.requestInfo: ");
            return;
        }
        final rp_fq a2;
        if (!(a2 = this.a.a().a(rp_aw.l, new rp_v[] { this.a }, a)).a() && a2 instanceof rp_bg) {
            ((rp_bg)a2).a(this.a.a());
        }
    }
    
    public final void b(final String s) {
        if (this.a.a.size() == 0) {
            return;
        }
        this.setCursor(Cursor.getPredefinedCursor(3));
        ByteArrayOutputStream a;
        try {
            a = this.a(null, rp_fl.a).a();
        }
        catch (IOException ex) {
            System.out.println("PlaneEP.save: " + ex);
            return;
        }
        try {
            final rp_bZ rp_bZ;
            final rp_bg rp_bg;
            if ((rp_bg = (rp_bg)(rp_bZ = (rp_bZ)this.a.a()).a(a, "plan-save-temp", "&todo=", "text/xml", false)).a()) {
                rp_C.a(10, "plan-save-temp returned: " + rp_bg.toString());
                final String a2;
                if ((a2 = rp_bg.a(2)) != null) {
                    final URL a3 = rp_bZ.a("action=" + s + "&name=" + rp_C.b(a2), rp_aJ.d);
                    System.out.println("Open Print page: " + a3);
                    rp_bZ.a(a3, null);
                }
            }
        }
        catch (MalformedURLException ex2) {
            System.out.println("Plane.print: MalformedURLException");
            System.out.println(ex2);
        }
        catch (IOException ex3) {
            System.out.println("Plane.print: IOException");
            System.out.println(ex3);
        }
    }
    
    public final void a(final Frame frame, final boolean b) {
        try {
            final rp_al rp_al;
            (rp_al = new rp_al(this.a, this.a)).b();
            rp_al.setVisible(true);
            final rp_dW a;
            if (rp_al.d() && (a = rp_al.a.a()) != null) {
                this.a.a(new rp_cg(this.a.a(), a.a(this, true)));
                this.a = a;
            }
            this.d();
            this.d();
            this.repaint();
            this.requestFocus();
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    public final void a(rp_cv rp_cv) {
        try {
            if (!rp_cv.a(131072)) {
                return;
            }
            final rp_fT a;
            if ((a = (rp_cv = rp_cv).a.a()).a() != 1) {
                return;
            }
            final rp_eJ rp_eJ;
            this.a.a().a(((rp_bZ)this.a.a()).a("action=show-sku&SKU=" + rp_C.b((rp_eJ = (rp_eJ)a).d) + "&Catalog=" + rp_C.b(rp_eJ.a), rp_aJ.c), null);
        }
        catch (MalformedURLException ex) {
            System.out.println("Plane.print: MalformedURLException");
            System.out.println(ex);
        }
        catch (IOException ex2) {
            System.out.println("Plane.print: IOException");
            System.out.println(ex2);
        }
    }
    
    public final void a(final rp_dv rp_dv) {
        final Dimension size = this.getSize();
        final Point a = this.a().a(new Point(size.width / 2, size.height / 2));
        final rp_cv rp_cv;
        (rp_cv = new rp_cv(this.a.a(), a.x, a.y, 0.0, (rp_dv)rp_dv.clone(), null)).a(this.a);
        this.a.b((rp_dC)rp_cv);
    }
    
    public final void a(final Vector vector) {
        rp_C.a(10, "Laying down " + vector.size() + " items");
        if (vector.size() == 0) {
            return;
        }
        final rp_dC[] array = new rp_dC[vector.size()];
        final Point a = this.a();
        final Dimension b = this.b();
        int n = a.x;
        int y = a.y;
        int max = 0;
        rp_b rp_b = null;
        for (int i = 0; i < vector.size(); ++i) {
            final rp_dv rp_dv;
            final int n2 = (rp_dv = (rp_dv)vector.elementAt(i).clone()).a() + 10000;
            final int n3 = rp_dv.b() + 10000;
            if (n + n2 > a.x + b.width && max != 0) {
                n = a.x;
                y += max;
                max = 0;
            }
            max = Math.max(max, n3);
            final rp_cv rp_cv;
            (rp_cv = new rp_cv(this.a.a(), n + n2 / 2, y + n3 / 2, 0.0, rp_dv, null)).a(this.a);
            final rp_fn rp_fn;
            if (!rp_dv.a() && rp_dv instanceof rp_fn && (rp_fn = (rp_fn)rp_dv).a != null) {
                if (rp_b == null) {
                    rp_b = new rp_b();
                }
                rp_b.a(rp_fn.a, rp_fn, this.a, rp_cv, null);
            }
            array[i] = rp_cv;
            n += n2;
        }
        this.a.e(array);
        if (rp_b != null) {
            rp_b.a(rp_au.a.a(), this.a);
        }
    }
    
    public final void a(final rp_C rp_C) {
        final Point point;
        SwingUtilities.convertPointFromScreen(point = (Point)rp_C.a.clone(), this);
        if (this.getBounds().contains(point)) {
            this.a.b((rp_dC)rp_C.a);
        }
    }
    
    private void g() {
        ((rp_N)(this.a = 0)).d(this.a);
        this.a.e = 0;
    }
    
    private void h() {
        this.a.a((rp_dC[])null);
    }
    
    final void a() {
        if (this.a != null && this.a.a(this.a())) {
            this.repaint();
        }
    }
    
    public final void d() {
        final Dimension size = this.getSize();
        Rectangle rectangle;
        if (this.a.a.size() == 0) {
            rectangle = new Rectangle(this.a().width / 2 - 425000, this.a().height / 2 - 300000, 850000, 600000);
        }
        else {
            if ((rectangle = this.a.a(this.getGraphics())) == null) {
                rectangle = this.a.a(this.getGraphics(), this.a, false);
            }
            rectangle.grow(rectangle.width / 20, rectangle.height / 20);
        }
        final Point point = new Point();
        if (this.a().a(rectangle, size, point)) {
            this.a(point);
            if (super.a != null) {
                super.a.a();
            }
            this.repaint();
        }
    }
    
    public final int a() {
        if (this.c == 0) {
            this.c = this.a.a("gr_sz");
        }
        return this.c;
    }
    
    final void e() {
        final Enumeration a = this.a.a(2);
        while (a.hasMoreElements()) {
            a.nextElement();
        }
        if (this.a != null) {
            this.repaint(this.a.a());
        }
        this.a = null;
        if (this.a.c == 0) {
            return;
        }
        if (this.a.c == 1) {
            final rp_dC rp_dC;
            final Rectangle a2;
            if ((a2 = (rp_dC = this.a.a(2).nextElement()).a(1)) == null) {
                return;
            }
            this.a = new rp_dd(this.a, rp_dC, this.a().a(a2), true, null);
        }
        if (this.a.c > 1) {
            final Enumeration a3 = this.a.a(2);
            final rp_dC[] array = new rp_dC[this.a.c];
            int n = 0;
            Rectangle rectangle = null;
            while (a3.hasMoreElements()) {
                final rp_dC rp_dC2;
                final Rectangle a4;
                if ((a4 = (rp_dC2 = a3.nextElement()).a(1)) != null) {
                    if (rectangle == null) {
                        rectangle = a4;
                    }
                    else {
                        rectangle.add(a4);
                    }
                    array[n++] = rp_dC2;
                }
            }
            if (rectangle != null) {
                this.a = new rp_dd(this.a, array, this.a().a(rectangle), null);
            }
        }
        if (this.a != null) {
            this.repaint(this.a.a());
        }
    }
    
    public final String getToolTipText(final MouseEvent mouseEvent) {
        if (this.a != 0) {
            return null;
        }
        final String a;
        if (this.a != null && (a = this.a.a(mouseEvent.getPoint())) != null) {
            return this.a.b(a);
        }
        final Point a2 = this.a().a(mouseEvent.getPoint());
        final rp_dC a3;
        if ((a3 = this.a.a(a2.x, a2.y, -1)) != null) {
            if (a3.a(4096)) {
                return this.a.b("tt_wall");
            }
            if (a3.a(131072)) {
                return this.a.b("ht_box");
            }
        }
        return null;
    }
    
    private Cursor a() {
        if (this.a != null) {
            return this.a;
        }
        if (this.a != null && this.a.getWidth(null) != -1) {
            try {
                return this.a = Toolkit.getDefaultToolkit().createCustomCursor(this.a, new Point(3, 3), "");
            }
            catch (Exception ex) {
                this.a = null;
            }
        }
        return Cursor.getPredefinedCursor(7);
    }
    
    private Cursor b() {
        if (this.b != null) {
            return this.b;
        }
        if (this.b != null && this.b.getWidth(null) != -1) {
            try {
                return this.b = Toolkit.getDefaultToolkit().createCustomCursor(this.b, new Point(3, 3), "");
            }
            catch (Exception ex) {
                this.b = null;
            }
        }
        return Cursor.getPredefinedCursor(7);
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
        this.c = true;
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        this.c = false;
        if (this.a() == null) {
            return;
        }
        this.a.a().c("");
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        this.requestFocus();
        if (this.a == 0 && this.a != null) {
            this.a.d(mouseEvent);
        }
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        if (this.a() == null) {
            return;
        }
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            this.b = false;
            if (this.a == 0 && this.a != null) {
                final Rectangle a = this.a.a();
                if (this.a.b(mouseEvent)) {
                    this.repaint(this.a.a().union(a));
                }
            }
            if (this.a == 2) {
                if (this.c) {
                    final rp_N a2;
                    if ((a2 = this.a).c != 0 && (a2.b || a2.b())) {
                        final rp_dC[] a3;
                        if ((a3 = a2.a(true)) != null) {
                            rp_dt a4 = null;
                            if (a2.b) {
                                for (int i = 0; i < a3.length; ++i) {
                                    if (a3[i].a(16)) {
                                        final rp_dC rp_dC = a3[i];
                                        if (i == 0) {
                                            a4 = rp_dC.a(true);
                                        }
                                        else {
                                            rp_dC.a(false);
                                        }
                                    }
                                }
                            }
                            else if (Math.abs(a2.a.a) > 0.001) {
                                final Point a5 = a2.a(2).nextElement().a();
                                final Point a6 = a2.a(4).nextElement().a();
                                a4 = new rp_fg(a3, a5.x, a5.y, a2.a.a, a6.x - a5.x, a6.y - a5.y);
                            }
                            else {
                                a4 = new rp_ca(a3, a2.a.e, a2.a.f);
                            }
                            a2.d();
                            if (a2.b) {
                                for (int j = 0; j < a3.length; ++j) {
                                    a2.e(a3[j]);
                                }
                            }
                            if (a4 != null) {
                                a2.a(a4);
                            }
                        }
                    }
                }
                else {
                    this.repaint(this.a.a());
                    final rp_N a7;
                    if ((a7 = this.a).b) {
                        final rp_dC[] a8 = a7.a(true);
                        for (int k = 0; k < a8.length; ++k) {
                            if (a8[k].a(16)) {
                                a8[k].a(false);
                            }
                        }
                    }
                    else {
                        a7.d();
                    }
                    this.f();
                }
                this.a = 0;
            }
            if (this.a == 4) {
                if (this.a) {
                    this.a((Graphics2D)this.getGraphics(), this.f, this.g);
                }
                final Rectangle rectangle;
                (rectangle = new Rectangle(this.a().c(new Point(this.j, this.k)))).add(this.a().c(mouseEvent.getPoint()));
                this.a.a(rectangle, mouseEvent.isControlDown());
                this.a = 0;
            }
            if (this.a != 3) {
                this.a = 0;
            }
        }
        mouseEvent.getModifiers();
        this.requestFocus();
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        if (this.a() == null) {
            return;
        }
        final Point a = this.a().a(mouseEvent.getPoint());
        this.d = a.x;
        this.e = a.y;
        this.f = mouseEvent.getX();
        this.g = mouseEvent.getY();
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            if (mouseEvent.getClickCount() == 2) {
                final rp_N a2 = this.a;
                final int d = this.d;
                final int e = this.e;
                mouseEvent.isControlDown();
                mouseEvent.isShiftDown();
                final int n = e;
                final int n2 = d;
                final rp_N rp_N = a2;
                final rp_dC a3;
                if ((a3 = a2.a(n2, n, -1)) != null) {
                    a3.a(rp_N.a.a().a());
                }
                this.f();
                return;
            }
            this.b = true;
            switch (this.a) {
                case 0: {
                    if (this.a != null) {
                        if (this.a.c(mouseEvent)) {
                            break;
                        }
                        if (this.a.a(mouseEvent.getPoint(), false)) {
                            this.a = 1;
                            if (this.a.a(1)) {
                                this.repaint(this.a.a());
                                return;
                            }
                            this.a.a(1, true);
                            for (int i = 0; i < this.a.a(); ++i) {
                                this.a.b(this.a.a(i), true);
                            }
                        }
                        final Rectangle a4;
                        if ((a4 = this.a.a()) != null) {
                            this.repaint(a4);
                        }
                    }
                    final rp_dC a5 = this.a.a(this.d, this.e, -1);
                    final int a6 = this.a.a;
                    if (a5 != null && ((a6 & 0x2) != 0x0 || (a6 & 0x4) != 0x0)) {
                        this.a = 1;
                    }
                    final rp_N a7 = this.a;
                    final int d2 = this.d;
                    final int e2 = this.e;
                    final boolean controlDown = mouseEvent.isControlDown();
                    mouseEvent.isShiftDown();
                    final boolean b = controlDown;
                    final int n3 = e2;
                    final int n4 = d2;
                    final rp_N rp_N2 = a7;
                    rp_dC a8 = a7.a(n4, n3, -1);
                    final int a9;
                    if (((a9 = rp_N2.a) & 0x2) == 0x0) {
                        a8 = null;
                    }
                    if (a8 != null && a8.a(4096)) {
                        rp_N2.c();
                    }
                    final boolean b2 = a8 != null && a8.a(2);
                    boolean b3;
                    if (b && a8 == null) {
                        b3 = false;
                    }
                    else {
                        if (!b && (a8 == null || !b2)) {
                            rp_N2.c();
                        }
                        if (a8 != null) {
                            if (b2) {
                                if (b) {
                                    rp_N2.e(a8);
                                }
                            }
                            else if ((a9 & 0x2) != 0x0) {
                                rp_N2.b(a8, false);
                            }
                        }
                        b3 = true;
                    }
                    if (b3 && this.a != null && this.a.c == 0) {
                        this.a = null;
                    }
                    if (a5 == null) {
                        this.j = this.h;
                        this.k = this.i;
                        this.a = false;
                        this.a = 4;
                        return;
                    }
                    break;
                }
                case 3: {
                    final rp_cw a10 = this.a;
                    final Point point = a;
                    final rp_cw rp_cw = a10;
                    if (a10.e == 0) {
                        rp_cw.a = point.x;
                        rp_cw.b = point.y;
                        rp_cw.e = 1;
                    }
                    else if (rp_cw.e == 1) {
                        rp_cw.c = point.x;
                        rp_cw.d = point.y;
                        rp_cw.e = 2;
                    }
                    if (this.a.e != 2) {
                        break;
                    }
                    if (rp_aJ.c) {
                        this.a.h = this.a.a();
                        this.a.b((rp_dC)this.a.clone());
                        this.a.e = 0;
                    }
                    else {
                        final Rectangle a11 = this.a.a(0);
                        this.a.e = 0;
                        this.a(a11);
                    }
                    if (rp_aJ.d) {
                        this.a.a.a("cmArrow");
                        this.g();
                        return;
                    }
                    break;
                }
                case 5: {
                    this.a.b((rp_dC)new rp_et(this.a.a(), a.x, a.y, 100000, 50000, 0.0));
                    if (rp_aJ.e) {
                        this.a.a.a("cmArrow");
                        this.g();
                        break;
                    }
                    break;
                }
            }
        }
        else if ((mouseEvent.getModifiers() & 0x4) != 0x0 && this.a == 0) {
            final Rectangle rectangle = new Rectangle();
            if (this.a != null) {
                rectangle.add(this.a.a());
            }
            if (this.a.a(this.d, this.e, mouseEvent.isControlDown(), mouseEvent.isShiftDown())) {
                this.repaint(rectangle);
            }
        }
    }
    
    public final void f() {
        if (this.a != null) {
            this.a.a(this.a());
            this.repaint(this.a.a());
        }
    }
    
    private void a(final Graphics2D graphics2D, final int n, final int n2) {
        graphics2D.setXORMode(Color.WHITE);
        graphics2D.setStroke(new BasicStroke(1.0f, 1, 1, 1.0f, new float[] { 1.0f, 2.0f }, 0.0f));
        graphics2D.drawLine(this.j, this.k, this.j, n2);
        graphics2D.drawLine(this.j, n2, n, n2);
        graphics2D.drawLine(n, n2, n, this.k);
        graphics2D.drawLine(n, this.k, this.j, this.k);
        graphics2D.setPaintMode();
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
        if (this.a() == null) {
            return;
        }
        this.h = mouseEvent.getX();
        this.i = mouseEvent.getY();
        final Point a = this.a().a(mouseEvent.getPoint());
        if (this.b) {
            switch (this.a) {
                case 0: {
                    if (this.a != null && this.a.a(mouseEvent)) {
                        this.repaint(this.a.a());
                    }
                }
                case 4: {
                    if (this.a) {
                        this.a((Graphics2D)this.getGraphics(), this.f, this.g);
                    }
                    this.a((Graphics2D)this.getGraphics(), this.h, this.i);
                    this.f = this.h;
                    this.g = this.i;
                    this.a = true;
                }
                case 3: {
                    this.setCursor(this.a());
                    this.a.a(this, a);
                }
                case 1: {
                    final rp_N a2 = this.a;
                    final int d = this.d;
                    final int e = this.e;
                    final int n = d;
                    final rp_N rp_N = a2;
                    a2.b = false;
                    if (rp_N.c == 0) {
                        final rp_dC a3 = rp_N.a(n, e, -1);
                        final int a4 = rp_N.a;
                        if ((a3 != null && (a4 & 0x2) != 0x0) || (a4 & 0x4) != 0x0) {
                            rp_N.b(a3, true);
                            rp_N.b = true;
                        }
                    }
                    if (rp_N.c > 0) {
                        final rp_dC[] a5;
                        if ((a5 = rp_N.a(true)) != null && a5.length > 0 && a5[0].a(4096)) {
                            rp_N.b = true;
                        }
                        if (rp_N.b) {
                            a5[0].a(rp_N, n, e);
                        }
                        else {
                            rp_N.a.a();
                            rp_N.e();
                        }
                    }
                    ((JComponent)(this.a = 2)).requestFocus();
                }
                case 2: {
                    final rp_N a6 = this.a;
                    final int n2 = a.x - this.d;
                    final int n3 = a.y - this.e;
                    final int n4 = n2;
                    final rp_N rp_N2 = a6;
                    boolean b = false;
                    Label_0930: {
                        if (a6.b) {
                            final Dimension dimension = new Dimension(0, 0);
                            final Enumeration a7 = rp_N2.a(2);
                            while (a7.hasMoreElements()) {
                                final rp_dC rp_dC;
                                if ((rp_dC = a7.nextElement()).a(16)) {
                                    rp_dC.a(n4, n3, dimension, mouseEvent.isControlDown());
                                }
                            }
                            final Enumeration a8 = rp_N2.a(2);
                            while (a8.hasMoreElements()) {
                                a8.nextElement().a(dimension.width, dimension.height);
                            }
                            b = false;
                        }
                        else {
                            rp_N2.a.a().c(rp_au.a("pe6"));
                            rp_N2.a.a(n4, n3);
                            rp_N2.a.b();
                            int g = 0;
                            int h = 0;
                            if (!mouseEvent.isShiftDown() && rp_N2.b(rp_N2.a.a - rp_N2.a.e, rp_N2.a.b - rp_N2.a.f)) {
                                g = rp_N2.a.g;
                                h = rp_N2.a.h;
                                rp_N2.a(g, h);
                                if (mouseEvent.isShiftDown() || rp_N2.a(null, 0, 0)) {
                                    b = (n4 - g != 0 || n3 - h != 0 || rp_N2.a.b != 0.0);
                                    break Label_0930;
                                }
                            }
                            if (rp_N2.a.a != 0.0) {
                                rp_N2.e();
                                final rp_aY a9 = rp_N2.a;
                                final rp_aY a10 = rp_N2.a;
                                final double n5 = 0.0;
                                a10.a = n5;
                                a9.b = n5;
                                final rp_aY a11 = rp_N2.a;
                                final rp_aY a12 = rp_N2.a;
                                final boolean b2 = false;
                                a12.f = (b2 ? 1 : 0);
                                a11.e = (b2 ? 1 : 0);
                            }
                            else {
                                rp_N2.a(-g, -h);
                            }
                            final Point point = new Point();
                            int n6;
                            int n7;
                            if (mouseEvent.isShiftDown() || rp_N2.a(point, rp_N2.a.a - rp_N2.a.e, rp_N2.a.b - rp_N2.a.f)) {
                                n6 = rp_N2.a.a - rp_N2.a.e;
                                n7 = rp_N2.a.b - rp_N2.a.f;
                            }
                            else {
                                n6 = point.x + rp_N2.a.a - rp_N2.a.e;
                                n7 = point.y + rp_N2.a.b - rp_N2.a.f;
                            }
                            rp_N2.a(n6, n7);
                            b = (n4 - n6 != 0 || n3 - n7 != 0 || rp_N2.a.b != 0.0);
                        }
                    }
                    final boolean b3 = b;
                    if (this.a != null) {
                        final Rectangle a13 = this.a.a();
                        if (b3) {
                            final Rectangle a14;
                            if ((a14 = this.a.a()) != null) {
                                this.a.a(this.a().a(a14));
                            }
                        }
                        else {
                            this.a.a(mouseEvent.getX() - this.f, mouseEvent.getY() - this.g);
                        }
                        if (a13 != null) {
                            a13.grow(2, 2);
                            this.repaint(a13);
                        }
                        else {
                            this.repaint();
                        }
                    }
                    else {
                        this.repaint();
                    }
                    this.d = a.x;
                    this.e = a.y;
                    this.f = this.h;
                    this.g = this.i;
                    break;
                }
            }
        }
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
        if (this.a() == null) {
            return;
        }
        this.h = mouseEvent.getX();
        this.i = mouseEvent.getY();
        final Point a = this.a().a(mouseEvent.getPoint());
        int n = 1;
        switch (this.a) {
            case 0: {
                if (this.a != null) {
                    final boolean a2 = this.a.a(new Point(this.h, this.i), false);
                    this.a.a().c(rp_au.a(a2 ? "pe5" : "pe4"));
                    if (a2) {
                        n = 12;
                        if (this.a.a(new Point(this.h, this.i), true)) {
                            n = 0;
                        }
                    }
                    if (!this.a.a(1) || this.a.c == 0) {
                        if (a2) {
                            break;
                        }
                        final Rectangle a3 = this.a.a();
                        this.a = null;
                        this.repaint(a3);
                    }
                }
                final rp_dC a4 = this.a.a(a.x, a.y, -1);
                final int a5 = this.a.a;
                if (a4 != null && this.a == null) {
                    if (a4.a(4096)) {
                        final rp_c rp_c = (rp_c)a4;
                        final rp_aJ a6 = this.a;
                        final rp_ew a7 = this.a();
                        final int x = a.x;
                        final int y = a.y;
                        final int n2 = x;
                        final rp_ew rp_ew = a7;
                        final rp_aJ rp_aJ = a6;
                        final rp_c rp_c2 = rp_c;
                        final rp_J a8 = rp_c.a;
                        int n3 = 0;
                        final Enumeration elements = a8.a.elements();
                        while (elements.hasMoreElements()) {
                            if (elements.nextElement().a(4096)) {
                                ++n3;
                            }
                        }
                        rp_cf a9 = null;
                        Label_0679: {
                            if (n3 >= 4) {
                                final Point point = new Point();
                                int n4 = -1;
                                if (rp_C.a(rp_c2.b, rp_c2.c, n2, y) < rp_c2.a) {
                                    n4 = 1;
                                    point.x = rp_c2.b;
                                    point.y = rp_c2.c;
                                }
                                if (rp_C.a(rp_c2.d, rp_c2.e, n2, y) < rp_c2.a) {
                                    n4 = 2;
                                    point.x = rp_c2.d;
                                    point.y = rp_c2.e;
                                }
                                if (n4 != -1) {
                                    final Point b;
                                    final Point point2 = b = rp_ew.b(point);
                                    point2.x += 15;
                                    final rp_W rp_W = new rp_W(rp_c2, rp_aJ, b, new Integer(n4));
                                    final Point b2 = rp_ew.b(new Point(point.x - rp_c2.a, point.y - rp_c2.a));
                                    final Point b3 = rp_ew.b(new Point(point.x + rp_c2.a, point.y + rp_c2.a));
                                    rp_W.a = new Rectangle(b2, new Dimension(Math.abs(b3.x - b2.x), Math.abs(b3.y - b2.y)));
                                    a9 = rp_W;
                                    break Label_0679;
                                }
                            }
                            a9 = null;
                        }
                        this.a = a9;
                    }
                    else {
                        this.a.a().c(rp_au.a("pe5"));
                        final Rectangle a10;
                        if ((a5 & 0x2) != 0x0 && (a10 = a4.a(1)) != null) {
                            this.a = new rp_dd(this.a, a4, this.a().a(a10), false, null);
                        }
                    }
                    if (this.a != null) {
                        this.a.a(this.getGraphics());
                    }
                }
                final rp_dC a11 = this.a.a;
                final rp_N a12 = this.a;
                rp_dC a13 = a4;
                final rp_N rp_N = a12;
                if (a13 != null && !a13.a(131072)) {
                    a13 = null;
                }
                boolean b4;
                if (rp_N.a != a13) {
                    rp_N.a = a13;
                    b4 = true;
                }
                else {
                    b4 = false;
                }
                if (b4) {
                    if (a11 != null) {
                        this.a(a11.a(0));
                    }
                    if (a4 != null) {
                        this.a(a4.a(0));
                    }
                }
                if (a4 != null && n == 1 && ((a5 & 0x2) != 0x0 || (a5 & 0x4) != 0x0)) {
                    n = 12;
                }
                if (this.a != null) {
                    break;
                }
                if (a4 == null) {
                    this.a.a().c(rp_au.a("pe4"));
                    n = 1;
                    break;
                }
                this.a.a().c(rp_au.a("pe5"));
                final rp_c rp_c3 = (rp_c)a4;
                final int x2 = a.x;
                final int y2 = a.y;
                mouseEvent.isControlDown();
                mouseEvent.isShiftDown();
                this.setCursor(rp_c3.a(x2, y2));
                n = -1;
                break;
            }
            case 3: {
                this.setCursor(this.a());
                n = -1;
                this.a.a(this, a);
                break;
            }
            case 5: {
                this.setCursor(this.b());
                n = -1;
                break;
            }
        }
        if (n != -1) {
            this.setCursor(Cursor.getPredefinedCursor(n));
        }
    }
    
    public final void keyReleased(final KeyEvent keyEvent) {
    }
    
    public final void keyPressed(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 127: {
                this.h();
            }
            case 39: {
                this.a(this.a.a(), 0);
            }
            case 37: {
                this.a(-this.a.a(), 0);
            }
            case 38: {
                this.a(0, -this.a.a());
            }
            case 40: {
                this.a(0, this.a.a());
            }
            case 83: {
                this.l = 83;
                this.b = System.currentTimeMillis();
            }
            case 65: {
                if (this.l != 83) {
                    break;
                }
                final long currentTimeMillis = System.currentTimeMillis();
                if (this.b + 1000L >= currentTimeMillis) {
                    this.l = 65;
                    this.b = currentTimeMillis;
                    return;
                }
                break;
            }
            case 68: {
                if (this.l == 65 && this.b + 1000L >= System.currentTimeMillis()) {
                    this.l = 0;
                    this = this;
                    new rp_co(this).setVisible(true);
                    break;
                }
                break;
            }
        }
    }
    
    public final void keyTyped(final KeyEvent keyEvent) {
    }
    
    private void a(final int n, final int n2) {
        this.a.a(new rp_ca(this.a.a(true), n, n2));
        this.f();
    }
    
    public final void a(final Color color, final Color color2) {
        if (color == null || color2 == null) {
            return;
        }
        if (rp_ch.a(color, color2)) {
            return;
        }
        for (int i = 0; i < this.a.size(); ++i) {
            if (((rp_dZ)this.a.get(i)).a(color, color2)) {
                return;
            }
        }
        this.a.add(new rp_dZ(color, color2));
    }
    
    public final boolean a() {
        return this.a != 2 && this.a != 4;
    }
}
