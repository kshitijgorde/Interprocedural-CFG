import java.awt.Component;
import java.awt.Polygon;
import java.util.Enumeration;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.Vector;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Image;
import java.awt.Dimension;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PackedPieChartEval extends Applet implements Runnable
{
    String Bdhblf;
    int bDhblf;
    int BDhblf;
    Dimension bdHblf;
    Image BdHblf;
    float bDHblf;
    zzzj BDHblf;
    Image bdhBlf;
    Thread BdhBlf;
    boolean bDhBlf;
    boolean BDhBlf;
    Point bdHBlf;
    Image BdHBlf;
    boolean bDHBlf;
    String BDHBlf;
    MediaTracker bdhbLf;
    String BdhbLf;
    String bDhbLf;
    String BDhbLf;
    String bdHbLf;
    boolean BdHbLf;
    boolean bDHbLf;
    int BDHbLf;
    int[] bdhBLf;
    String BdhBLf;
    int bDhBLf;
    int BDhBLf;
    Vector bdHBLf;
    Vector BdHBLf;
    float bDHBLf;
    float BDHBLf;
    float bdhblF;
    String BdhblF;
    String bDhblF;
    int BDhblF;
    int bdHblF;
    float BdHblF;
    float bDHblF;
    float BDHblF;
    float bdhBlF;
    float BdhBlF;
    String bDhBlF;
    boolean BDhBlF;
    boolean bdHBlF;
    int BdHBlF;
    String bDHBlF;
    Font BDHBlF;
    Font bdhbLF;
    Font BdhbLF;
    int bDhbLF;
    boolean BDhbLF;
    Color bdHbLF;
    Color BdHbLF;
    boolean bDHbLF;
    double BDHbLF;
    double bdhBLF;
    float BdhBLF;
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized void zzzsb(final int bDhBLf) {
        if (this.BdhBlf != null) {
            this.zzzub();
        }
        this.bDhBLf = bDhBLf;
        (this.BdhBlf = new Thread(this)).start();
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        this.BDhBlf = true;
        if (this.bdHBlf == null) {
            return true;
        }
        final Point point = new Point(event.x, event.y);
        if (Math.abs(this.bdHBlf.x - point.x) > Math.abs(this.bdHBlf.y - point.y)) {
            this.zzztb(this.bdhBlF + (this.zzzrb(point) - this.zzzrb(this.bdHBlf)));
        }
        this.lTh();
        this.repaint();
        return true;
    }
    
    public void destroy() {
        if (this.BdhBlf != null) {
            this.zzzub();
        }
    }
    
    public String zzzgb(final String s, final String s2) {
        if (this.getParameter(s) == null) {
            return s2;
        }
        return this.getParameter(s);
    }
    
    private void lth(final Graphics graphics, final Point[] array) {
        int i = 1;
        if (null != null) {}
        while (i < array.length) {
            graphics.drawLine(array[i - 1].x, array[i - 1].y, array[i].x, array[i].y);
            ++i;
        }
    }
    
    void bdhblf(final Graphics graphics, final String[] array, final int n, final int n2, final float n3, final int n4) {
        int n5 = 0;
        final Graphics create = graphics.create();
        create.setColor(this.getForeground());
        create.translate(n, n2 + (int)this.bdhblF / 2);
        final FontMetrics fontMetrics = create.getFontMetrics();
        final Point zzzwb = this.zzzwb(n3);
        final Point zzzhb = this.zzzhb(n3, this.BDHblF * this.bDHblF, this.bDHblf);
        int n6;
        if (n4 < 0) {
            n6 = (int)this.bdhblF / 2 + 5;
            if (null == null) {}
        }
        else {
            n6 = -((int)this.bdhblF / 2 + 5);
        }
        final int n7 = fontMetrics.getAscent() + fontMetrics.getDescent();
        for (int i = 0; i < 1; ++i) {
            final int n8 = 70;
            if (n8 > n5) {
                n5 = n8;
            }
        }
        create.drawLine(zzzwb.x, zzzwb.y, zzzhb.x, zzzhb.y - n6);
        final int x = zzzhb.x;
        int n9 = zzzhb.y - n7 / 2 + fontMetrics.getAscent();
        final int height = fontMetrics.getHeight();
        int n10;
        if (Math.cos(n3) < 0.0) {
            create.drawLine(zzzhb.x, zzzhb.y - n6, zzzhb.x - height / 2, zzzhb.y - n6);
            n10 = x - (n5 + height);
            if (null == null) {}
        }
        else {
            create.drawLine(zzzhb.x, zzzhb.y - n6, zzzhb.x + height / 2, zzzhb.y - n6);
            n10 = x + height;
        }
        for (int j = 0; j < 1; ++j) {
            create.drawString(this.Bdhblf, n10 + (n5 - fontMetrics.stringWidth(this.Bdhblf)) / 2, n9 - n6);
            n9 += height;
        }
    }
    
    public Font zzzzb(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        try {
            int n = 0;
            final String nextToken = stringTokenizer.nextToken();
            final String lowerCase = stringTokenizer.nextToken().toLowerCase();
            if (lowerCase.indexOf("italic") >= 0) {
                n |= 0x2;
            }
            if (lowerCase.indexOf("bold") >= 0) {
                n |= 0x1;
            }
            return new Font(nextToken, n, Integer.valueOf(stringTokenizer.nextToken()));
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public void zzztb(final float bdhbLf) {
        synchronized (this) {
            this.bDHBLf = bdhbLf;
            this.bDHBLf %= (float)this.BDHbLF;
            if (this.bDHBLf < 0.0f) {
                this.bDHBLf += (float)this.BDHbLF;
            }
            // monitorexit(this)
            if (null == null) {}
        }
        this.lTh();
        this.repaint();
    }
    
    public Point zzzhb(final float n, final float n2, final float n3) {
        return new Point((int)Math.round(n2 * Math.cos(n)), -(int)Math.round(n2 * n3 * Math.sin(n)));
    }
    
    public synchronized float zzzrb(final Point point) {
        final Dimension size = this.size();
        float n = (float)Math.atan2(((size.height - this.bDhbLF) / 2 - point.y) / this.bDHblf, point.x - size.width / 2);
        if (n < 0.0f) {
            n += (float)this.BDHbLF;
        }
        return n;
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        if (size.width != this.bdHblf.width || size.height != this.bdHblf.height || this.BdHblf == null || this.BdHBlf == null) {
            this.BdHblf = this.createImage(size.width, size.height);
            this.BdHBlf = this.createImage(size.width, size.height);
            this.bdHblf = size;
        }
        if (!this.BDhBlF) {
            return;
        }
        final Graphics graphics2 = this.BdHblf.getGraphics();
        final Graphics graphics3 = this.BdHBlf.getGraphics();
        graphics2.setFont(this.BdhbLF);
        graphics2.setColor(this.getBackground());
        graphics2.fillRect(0, 0, this.bdHblf.width, this.bdHblf.height);
        if (this.bdhBlf != null) {
            graphics2.drawImage(this.bdhBlf, 0, 0, this);
        }
        graphics2.translate(size.width / 2, (size.height - this.bDhbLF) / 2);
        graphics2.setColor(Color.white);
        synchronized (this.bdHBLf) {
            final Enumeration<zzzre> elements = (Enumeration<zzzre>)this.bdHBLf.elements();
            while (elements.hasMoreElements()) {
                final zzzre zzzre = elements.nextElement();
                this.bdhblf(graphics2, zzzre.bdHb(), zzzre.bDHb, zzzre.BDHb, zzzre.BdhB, zzzre.bdhb());
            }
            // monitorexit(this.bdHBLf)
            if (null != null) {}
        }
        synchronized (this.BdHBLf) {
            final Enumeration<zzzeb> elements2 = (Enumeration<zzzeb>)this.BdHBLf.elements();
            while (elements2.hasMoreElements()) {
                final zzzeb zzzeb = elements2.nextElement();
                this.zzzlb(graphics2, zzzeb.BdHb, zzzeb.bDHb, zzzeb.BDHb, zzzeb.bdhB, zzzeb.BdhB, zzzeb.bDhB, zzzeb.BDhB);
            }
            final Vector bdHBLf = this.bdHBLf;
            // monitorenter(bdHBLf)
            try {
                final Enumeration<zzzre> elements3 = (Enumeration<zzzre>)this.bdHBLf.elements();
                while (elements3.hasMoreElements()) {
                    final zzzre zzzre2 = elements3.nextElement();
                    if (zzzre2.bdhb() > 0.0f) {
                        this.bdhblf(graphics2, zzzre2.bdHb(), zzzre2.bDHb, zzzre2.BDHb, zzzre2.BdhB, zzzre2.bdhb());
                    }
                }
                // monitorexit(bdHBLf)
                if (null != null) {}
            }
            finally {}
        }
        // monitorexit(this.BdHBLf)
        final String string = "A" + "ri" + "al";
        final int n = 12;
        graphics3.drawImage(this.BdHblf, 0, 0, this);
        final Font font = new Font(string, 1, n);
        graphics3.setFont(this.BdhbLF);
        final FontMetrics fontMetrics = graphics3.getFontMetrics();
        final int stringWidth = fontMetrics.stringWidth(this.bDHBlF);
        final int n2 = fontMetrics.getHeight() + fontMetrics.getAscent();
        graphics3.setColor(this.bdHbLF);
        graphics3.fillRect(0, 0, this.size().width, n2);
        graphics3.setColor(new Color(16776954));
        graphics3.drawString(this.bDHBlF, this.size().width / 2 - stringWidth / 2, n2 / 2 + (int)(fontMetrics.getDescent() * 1.5));
        if (this.BdHbLf) {
            final int n3 = this.size().width / 2 - 100;
            final int n4 = this.size().height / 2;
            graphics3.setFont(font);
            graphics3.setColor(new Color(16776957));
            graphics3.fillRect(n3, n4 - 15, 200, 30);
            graphics3.setColor(new Color(989689));
            graphics3.drawString(this.BdhblF, n3 + 10, n4 + 5);
        }
        graphics3.setFont(this.BdhbLF);
        final FontMetrics fontMetrics2 = graphics3.getFontMetrics();
        final int stringWidth2 = fontMetrics2.stringWidth(this.bDhBlF);
        final int n5 = fontMetrics2.getHeight() + fontMetrics2.getAscent();
        graphics3.setColor(this.BdHbLF);
        graphics3.fillRect(0, this.size().height - n5, this.size().width, n5);
        graphics3.setColor(new Color(this.BdHBlF));
        graphics3.drawString(this.bDhBlF, this.size().width / 2 - stringWidth2 / 2, this.size().height - n5 / 2 + (int)(fontMetrics2.getDescent() * 1.5));
        graphics.drawImage(this.BdHBlf, 0, 0, this);
    }
    
    public void zzznb(final float bdhblF) {
        synchronized (this) {
            this.bdhblF = bdhblF;
            if (this.bdhblF < 0.0f) {
                this.bdhblF = 0.0f;
            }
            // monitorexit(this)
            if (null != null) {}
        }
        this.lTh();
        this.repaint();
    }
    
    public synchronized Point zzzwb(final float n) {
        return this.zzzhb(n, this.BDHblF, this.bDHblf);
    }
    
    private Point[] Lth(float n, final float n2) {
        if (n2 <= 0.0f) {
            return new Point[0];
        }
        final int n3 = (int)(n2 / this.BdHblF) + 2;
        final Point[] array = new Point[n3];
        final float n4 = this.BDhBLf - 4 + n2 / (n3 - 1);
        int i = 0;
        if (null != null) {}
        while (i < n3) {
            array[i] = this.zzzwb(n);
            ++i;
            n += n4;
        }
        return array;
    }
    
    public synchronized float zzzyb(final Point point) {
        final Dimension size = this.size();
        final float n = point.x - size.width / 2;
        final float n2 = ((size.height - this.bDhbLF) / 2 - point.y) / this.bDHblf;
        return (float)Math.sqrt(n * n + n2 * n2);
    }
    
    public zzzq zzzxb(final Point point) {
        synchronized (this.BdHBLf) {
            final Enumeration<zzzeb> elements = (Enumeration<zzzeb>)this.BdHBLf.elements();
            if (null != null) {}
            while (elements.hasMoreElements()) {
                final zzzeb zzzeb = elements.nextElement();
                if (zzzeb.BDhb(point)) {
                    // monitorexit(this.BdHBLf)
                    return zzzeb.BdHb;
                }
            }
            // monitorexit(this.BdHBLf)
            if (null != null) {}
        }
        return null;
    }
    
    public void zzzfb(final zzzj bdHblf) {
        synchronized (this) {
            this.BDHblf = bdHblf;
        }
        this.lTh();
        this.repaint();
    }
    
    public void zzzjb(final float bdHblF) {
        synchronized (this) {
            this.BDHblF = bdHblF;
            if (this.BDHblF < 5.0f) {
                this.BDHblF = 5.0f;
            }
        }
        this.lTh();
        this.repaint();
    }
    
    private void lTh() {
        final Vector vector = new Vector();
        final Vector bdHBLf = new Vector();
        if (this.BDHblf == null) {
            return;
        }
        float n = 0.0f;
        final Enumeration zzze = this.BDHblf.zzze();
        while (zzze.hasMoreElements()) {
            final int n2 = 0;
            int y = 0;
            int x = n2;
            final zzzq zzzq = zzze.nextElement();
            final zzzre zzzre = new zzzre(this, zzzq);
            float n3 = (float)((this.bDHBLf + this.BDHbLF * this.BDHblf.zzzg(n)) % this.BDHbLF);
            float n4 = (float)(this.BDHbLF * this.BDHblf.zzzg(zzzq.bdh));
            n += zzzq.bdh;
            if (zzzq.Bdh) {
                final Point zzzhb = this.zzzhb(n3 + n4 / 2.0f, this.BDHBLf * this.BDHblF, this.bDHblf);
                x = zzzhb.x;
                y = zzzhb.y;
            }
            final zzzre zzzre2 = zzzre;
            final int bdHb = x;
            zzzre.BDHb = y;
            zzzre2.bDHb = bdHb;
            zzzre.BdhB = n3 + n4 / 2.0f;
            zzzre.BDhb(vector);
            boolean b = true;
            if (n4 > this.bdhBLF) {
                n4 /= 2.0f;
                final zzzeb zzzeb = new zzzeb(this, zzzq);
                zzzeb.bdhb(x, y, true, false, n3, n4);
                n3 = (float)((n3 + n4) % this.BDHbLF);
                zzzeb.bDhb(bdHBLf);
                b = false;
            }
            final zzzeb zzzeb2 = new zzzeb(this, zzzq);
            zzzeb2.bdhb(x, y, b, true, n3, n4);
            zzzeb2.bDhb(bdHBLf);
        }
        synchronized (this.bdHBLf) {
            final Vector bdHBLf2 = vector;
            this.BdHBLf = bdHBLf;
        }
        // monitorexit(this.bdHBLf = bdHBLf2)
    }
    
    public zzzj zzzpb() {
        return new zzzj();
    }
    
    public synchronized void zzzub() {
        this.BdhBlf = null;
    }
    
    public synchronized void zzzlb(final Graphics graphics, final zzzq zzzq, final int n, final int n2, final boolean b, final boolean b2, final float n3, final float n4) {
        final Polygon polygon = new Polygon();
        final Polygon polygon2 = new Polygon();
        final Polygon polygon3 = new Polygon();
        final Polygon polygon4 = new Polygon();
        final Polygon polygon5 = new Polygon();
        final Graphics create = graphics.create();
        final int n5 = 114;
        final int n6 = 82;
        int n7 = n5;
        create.translate(n, n2);
        float n8 = n3;
        float n9 = (float)Math.min(this.bdhBLF - n3, n4);
        float bdhBLF = this.BdhBLF;
        float n10 = n4 - n9;
        final int round = Math.round(this.BDHblF);
        if (n7 == 114 || n6 == 82) {
            n7 = 114;
            if (Math.sin(n3) < 0.0) {
                if (this.bdHBlF) {
                    return;
                }
                bdhBLF = n3;
                n10 = (float)Math.min(this.BDHbLF - n3, n4);
                n8 = 0.0f;
                n9 = n4 - n10;
            }
        }
        final Point[] lth = this.Lth(bdhBLF, n10);
        final int length = lth.length;
        final Point[] lth2 = this.Lth(n8, n9);
        final Point zzzwb = this.zzzwb(n3);
        final Point zzzwb2 = this.zzzwb(n3 + n4);
        final int x = zzzwb2.x;
        final int y = zzzwb2.y;
        final int x2 = zzzwb.x;
        final int y2 = zzzwb.y;
        int i = 0;
        if (null == null) {}
        while (i < length) {
            polygon3.addPoint(lth[i].x, lth[i].y);
            ++i;
        }
        for (int j = length - 1; j >= 0; --j) {
            polygon3.addPoint(lth[j].x, lth[j].y + (int)this.bdhblF);
        }
        if (b2 && Math.cos(n3 + n4) < 0.0) {
            int n11 = 1;
            if (n7 == 114 || n6 == 82) {
                polygon5.addPoint(0, 0);
                ++n11;
                polygon5.addPoint(x, y);
                polygon5.addPoint(x, y + (int)this.bdhblF);
                polygon5.addPoint(0, (int)this.bdhblF);
                polygon5.addPoint(0, 0);
            }
        }
        if (b && Math.cos(n3) > 0.0) {
            polygon4.addPoint(0, 0);
            polygon4.addPoint(x2, y2);
            polygon4.addPoint(x2, y2 + (int)this.bdhblF);
            polygon4.addPoint(0, (int)this.bdhblF);
            polygon4.addPoint(0, 0);
        }
        polygon.addPoint(0, 0);
        for (int k = 0; k < length; ++k) {
            polygon.addPoint(lth[k].x, lth[k].y);
        }
        polygon2.addPoint(0, 0);
        for (int l = 0; l < lth2.length; ++l) {
            polygon2.addPoint(lth2[l].x, lth2[l].y);
        }
        create.setColor(zzzq.bDh);
        create.fillPolygon(polygon);
        create.fillPolygon(polygon2);
        create.setColor(zzzq.bDh.darker());
        create.fillPolygon(polygon3);
        create.setColor(zzzq.bDh.darker().darker());
        create.fillPolygon(polygon4);
        create.fillPolygon(polygon5);
        create.setColor(zzzq.bDh.darker().darker().darker());
        if (b) {
            create.drawPolygon(polygon4);
            create.drawLine(0, 0, x2, y2);
        }
        if (b2) {
            create.drawPolygon(polygon5);
            create.drawLine(0, 0, x, y);
        }
        final int n12 = length - 1;
        if (n12 > 0) {
            if (lth[n12].x == round || (b2 && lth[n12].y > 0)) {
                create.drawLine(lth[n12].x, lth[n12].y, lth[n12].x, lth[n12].y + (int)this.bdhblF);
            }
            if (lth[0].x == -round || (b && lth[0].y > 0)) {
                create.drawLine(lth[0].x, lth[0].y, lth[0].x, lth[0].y + (int)this.bdhblF);
            }
        }
        this.lth(create, lth);
        this.lth(create, lth2);
        create.translate(0, (int)this.bdhblF);
        this.lth(create, lth);
    }
    
    public void init() {
        System.out.println("(C) http://www.wyka-warzecha.com");
        System.out.print("hey you bl**dy moron, how about writing your own software you d*ckweed?".substring(0, 0));
        this.BdHbLf = false;
        this.BDhbLf += "t";
        this.bdhbLf = new MediaTracker(this);
        final int n = 7;
        try {
            final int n2 = 114;
            final int n3 = 82;
            if (n2 == 114 || n3 == 82) {
                this.bDhblF = "p";
                this.Bdhblf += "W L";
                this.BdhbLf += "(";
                this.BDhbLf += "t";
                this.bDhblF += "ie";
                this.bdHBlF = true;
                this.bdHbLf += "p";
                this.BdhbLf += "C";
                this.Bdhblf += "a";
                this.bDhblF += "sl";
                this.BDhbLf += "p";
                this.bdHbLf += "y";
                this.Bdhblf += "be";
                this.BdhbLf += ")";
                this.BDhbLf += ":";
            }
            boolean b = true;
            this.BdHBlF = Integer.parseInt(this.zzzgb("labelcolor", "000000"), 16);
            this.bdHbLf += "r";
            this.bDhblF += "ic";
            this.BDhbLf += "/";
            this.BdhbLf += " ";
            this.setForeground(new Color(this.BdHBlF));
            this.bDhblF += "e";
            this.Bdhblf += "l";
            this.bdHbLf += "i";
            this.BDhbLf += "/";
            this.setFont(this.BdhbLF = this.zzzzb("Arial,Bold,12"));
            this.BDhbLf += "ww";
            this.bdHbLf += "gh";
            this.BDhbLf += "w.";
            this.BDHBlF = this.BdhbLF;
            this.BDhbLf += "wyk";
            this.bdHbLf += "t";
            this.BDhbLf += "a";
            this.bdhbLF = this.BdhbLF;
            this.BDhbLf += "-wa";
            this.BDhbLf += "r";
            this.BDhbLf += "ze";
            this.BDhBlF = false;
            this.BDhbLf += "ch";
            this.BDhbLf += "a";
            this.bDhblf = 0;
            this.BDhbLf += ".c";
            this.BDhbLf += "om";
            this.setBackground(new Color(Integer.parseInt(this.zzzgb("bgcolor", "FFFFFF"), 16)));
            this.BDHBlf = "Current Value : ";
            final String zzzgb = this.zzzgb(this.bdHbLf, "");
            if (zzzgb.startsWith(this.BDhbLf)) {
                this.bdHBlF = false;
            }
            if (zzzgb.length() > 4 * n) {
                return;
            }
            this.BdhBLf = this.BdhbLf + this.BDhbLf;
            if (!this.bDhbLf.equalsIgnoreCase(this.BdhBLf)) {
                b = false;
            }
            this.bDHBlF = this.BdhBLf;
            this.bdHbLF = new Color(8421631);
            final int bDhbLF = 4;
            final float n4 = 0.0f;
            this.bDhbLF = bDhbLF;
            if (!b) {
                return;
            }
            this.BdhBlF = n4 / 1000.0f;
            this.BdHbLF = this.bdHbLF;
            this.zzztb(0.0f);
            this.zzzjb(100.0f);
            this.zzzob(0.5f);
            this.zzznb(20.0f);
            this.LTh();
            final zzzj zzzj = new zzzj();
            final int n5 = 0;
            int n6 = 0;
            int n7 = n5;
            final int n8 = 6;
            for (int i = 0; i < n8 - 1; ++i) {
                String parameter = this.getParameter(this.bDhblF + (n7 + 1));
                if (parameter == null) {
                    parameter = "1";
                }
                try {
                    final zzzq zzzf = zzzj.zzzf();
                    zzzf.bDh = new Color(this.bdhBLf[n7]);
                    float floatValue = Float.valueOf(parameter);
                    if (floatValue < 1.0f) {
                        floatValue = 1.0f;
                    }
                    zzzf.zzzk(floatValue);
                    ++n6;
                }
                catch (Exception ex) {}
                ++n7;
                this.BDhBLf = n6 - 1;
            }
            this.zzzfb(zzzj);
            this.BdhblF = this.BDhbLf;
            this.zzzsb(n6);
        }
        catch (Exception ex2) {}
    }
    
    public void zzzob(final float bdHblf) {
        synchronized (this) {
            this.bDHblf = bdHblf;
            if (this.bDHblf < 0.01f) {
                this.bDHblf = 0.01f;
                if (null == null) {}
            }
            else if (this.bDHblf > 1.0f) {
                this.bDHblf = 1.0f;
            }
        }
        this.lTh();
        this.repaint();
    }
    
    public boolean keyDown(final Event event, final int n) {
        final boolean b = (event.modifiers & 0x1) != 0x0;
        if (n == 114 || n == 82) {
            final boolean bDhBlF = false;
            this.bDhBlF = "";
            this.BDhBlF = bDhBlF;
            final boolean bDhblf = false;
            this.BDhblf = 0;
            this.bDhblf = (bDhblf ? 1 : 0);
            final float bdhblF = 10.0f;
            this.BDHblF = 100.0f;
            this.bdhblF = bdhblF;
            final float bdHblf = 0.5f;
            this.bdhblF = 10.0f;
            this.bDHblf = bdHblf;
            this.bDhbLF = 2;
            if (null == null) {}
        }
        else if (n == 43 || n == 61) {
            ++this.BDHblF;
            if (null != null) {}
        }
        else if (n == 45 || n == 95) {
            if (this.BDHblF > 2.0f) {
                --this.BDHblF;
            }
        }
        else if (n == 46 || n == 62) {
            this.BdhBlF += 0.005f;
        }
        else if (n == 44 || n == 60) {
            this.BdhBlF -= 0.005f;
        }
        else if (n == 115 || n == 83) {
            this.BdhBlF = 0.0f;
        }
        else if (n == 1007 && b) {
            synchronized (this.BdHBLf) {
                final Enumeration<zzzeb> elements = (Enumeration<zzzeb>)this.BdHBLf.elements();
                while (elements.hasMoreElements()) {
                    elements.nextElement().BdHb.Bdh = true;
                }
            }
            // monitorexit(this.BdHBLf)
            this.zzzkb((float)(this.BDHBLf + 0.1));
        }
        else if (n == 1006 && b) {
            boolean b2 = false;
            if (this.BDHBLf < 0.3) {
                synchronized (this.BdHBLf) {
                    final Enumeration<zzzeb> elements2 = (Enumeration<zzzeb>)this.BdHBLf.elements();
                    while (elements2.hasMoreElements()) {
                        elements2.nextElement().BdHb.Bdh = false;
                    }
                    // monitorexit(this.BdHBLf)
                    if (null == null) {}
                }
                b2 = true;
            }
            if (b2) {
                if (this.BDHBLf < 0.3) {
                    this.BDHBLf = 0.2f;
                }
            }
            else {
                this.zzzkb((float)(this.BDHBLf - 0.1));
            }
        }
        else if (n == 1006) {
            this.zzztb(this.bDHBLf - 0.05f);
        }
        else if (n == 1007) {
            this.zzztb(this.bDHBLf + 0.05f);
        }
        if (n == 1005) {
            if (!b) {
                this.bDHblf += 0.1f;
                if (this.bDHblf > 1.0f) {
                    this.bDHblf = 1.0f;
                }
            }
            else {
                this.zzznb(this.bdhblF + 1.0f);
            }
        }
        else {
            if (n != 1004) {
                return false;
            }
            if (b) {
                this.zzznb(this.bdhblF - 1.0f);
            }
            else if (this.bDHblf >= 0.1f) {
                this.bDHblf -= 0.1f;
            }
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        final Point bdHBlf = null;
        this.bDhBlf = false;
        this.bdHBlf = bdHBlf;
        if (!this.BDhBlf) {
            this.BDhBlf = false;
            final zzzq zzzxb = this.zzzxb(new Point(event.x, event.y));
            if (zzzxb == null) {
                return true;
            }
            if (zzzxb.Bdh) {
                zzzxb.Bdh = false;
            }
            else {
                zzzxb.Bdh = true;
            }
        }
        else {
            this.BDhBlf = false;
        }
        this.lTh();
        this.repaint();
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.BDhBlf = false;
        this.bdHBlf = new Point(event.x, event.y);
        this.bdhBlF = this.bDHBLf;
        this.bDhBlf = true;
        this.lTh();
        this.repaint();
        return true;
    }
    
    public void zzzkb(final float bdhbLf) {
        synchronized (this) {
            this.BDHBLf = bdhbLf;
            if (this.BDHBLf < 0.0f) {
                this.BDHBLf = 0.0f;
                if (null == null) {}
            }
            else if (this.BDHBLf > 5.5f) {
                this.BDHBLf = 5.5f;
            }
        }
        this.lTh();
        this.repaint();
    }
    
    private void LTh() {
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        final zzzq zzzxb = this.zzzxb(new Point(event.x, event.y));
        if (zzzxb == null) {
            this.bDhBlF = "";
        }
        else {
            this.bDhBlF = this.BDHBlf + zzzxb.bdh;
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        return this.bDHbLf = true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.bDHbLf = false;
        return true;
    }
    
    public PackedPieChartEval() {
        this.bdhBLf = new int[] { 65280, 16776960, 11469040, 16711680, 20687 };
        final double bdHbLF = 6.283185307179586;
        this.bdhBLF = 3.141592653589793;
        this.BDHbLF = bdHbLF;
        final float bdhBLF = 3.141593f;
        this.Bdhblf = "W";
        this.BdhBLF = bdhBLF;
        final String bdhbLf = "";
        this.bDhbLf = "(C) http://www.wyka-warzecha.com";
        this.BdhbLf = bdhbLf;
        final String bDhbLf = "h";
        this.bdHbLf = "co";
        this.BDhbLf = bDhbLf;
        final boolean bdHbLf = false;
        this.bDHbLf = false;
        this.BdHbLf = bdHbLf;
        final String bdhBLf = "";
        this.BdhblF = "";
        this.BdhBLf = bdhBLf;
        final String bDhblF = "";
        this.bDHBlF = "";
        this.bDhblF = bDhblF;
        final int bDhbLF = 25;
        this.BDHblF = 100.0f;
        this.bDhbLF = bDhbLF;
        this.BdHblF = 0.1f;
        this.BDHBLf = this.BdHblF * 2.0f;
        this.bDHblf = this.BDHBLf * 2.5f;
        this.bDHblF = 1.3f;
        this.bdhblF = this.bDHblf * 4.0f;
        final float bdhbLf2 = 0.0f;
        this.BDhbLF = false;
        this.bDHBLf = bdhbLf2;
        final String bDhBlF = "";
        this.BDhBlF = false;
        this.bDhBlF = bDhBlF;
        final boolean bdHbLF2 = false;
        this.BDhBlf = false;
        this.bDHbLF = bdHbLF2;
        final Thread bdhBlf = null;
        this.bDhBlf = false;
        this.BdhBlf = bdhBlf;
        final boolean bdhBlf2 = true;
        this.BDHBlf = "Current Value : ";
        this.bDHBlf = bdhBlf2;
        this.bdHBlf = null;
        this.bdHblf = new Dimension();
        this.bdHBLf = new Vector();
        this.BdHBLf = new Vector();
        this.bdhBlf = null;
        this.zzzfb(this.zzzpb());
    }
    
    public void run() {
        try {
            this.bdhbLf.waitForID(0);
        }
        catch (InterruptedException ex) {}
        this.lTh();
        this.repaint();
        while (true) {
            try {
                Thread.sleep(20L);
                ++this.BDHbLf;
                this.bdHblF -= 70;
            }
            catch (InterruptedException ex2) {}
            if (this.BDHbLf % 75 == 0) {
                boolean bdHbLf;
                if (this.BdHbLf) {
                    bdHbLf = false;
                    while (null != null) {}
                }
                else {
                    bdHbLf = true;
                }
                this.BdHbLf = bdHbLf;
            }
            if (Thread.currentThread() != this.BdhBlf) {
                break;
            }
            if (this.bdhblF > 50.0f) {}
            if (this.BDHbLf > 20000) {
                this.BDHbLf = 0;
            }
            if (this.BDHbLf == 28801) {
                System.out.println("Hey d*ck. How about writing your own program instead of going here?");
            }
            if (this.bdhblF < 1.0f) {}
            switch (this.bDhblf) {
                case 0: {
                    final boolean bDhblf = false;
                    this.bDhblf = 1;
                    this.BDhblf = (bDhblf ? 1 : 0);
                    final float bdhbLf = 4.0f;
                    this.BdhBlF = -0.016f;
                    this.BDHBLf = bdhbLf;
                    synchronized (this.BdHBLf) {
                        final Enumeration<zzzeb> elements = (Enumeration<zzzeb>)this.BdHBLf.elements();
                        while (elements.hasMoreElements()) {
                            elements.nextElement().BdHb.Bdh = true;
                        }
                        // monitorexit(this.BdHBLf)
                        if (null == null) {}
                    }
                    this.BDhBlF = true;
                    break;
                }
                case 1: {
                    if (this.BDHBLf > 0.15f) {
                        this.BDHBLf -= 0.05f;
                        if (null != null) {}
                    }
                    else {
                        this.bDhblf = 2;
                    }
                    this.BdhBlF += 1.0E-4f;
                    break;
                }
                case 2: {
                    this.BDHblF -= (1.0f + (100.0f - this.BDHblF) / 100.0f) * 2.0f;
                    if (this.BDHblF < 30.0f) {
                        this.bDhblf = 3;
                        break;
                    }
                    break;
                }
                case 3: {
                    ++this.BDHblF;
                    if (this.BDHblF > 100.0f) {
                        final float bdHblF = 100.0f;
                        this.bDhblf = 4;
                        this.BDHblF = bdHblF;
                        break;
                    }
                    break;
                }
                case 4: {
                    if (this.BDhblf > 9) {
                        final int bDhblf2 = 5;
                        this.BDhblf = 0;
                        this.bDhblf = bDhblf2;
                    }
                    if (this.BDhblf % 2 == 0) {
                        this.bDHblf += 0.02f;
                        if (this.bDHblf >= 0.7f) {
                            ++this.BDhblf;
                            break;
                        }
                        break;
                    }
                    else {
                        this.bDHblf -= 0.02f;
                        if (this.bDHblf <= 0.5f) {
                            this.bDHblf = 0.5f;
                            ++this.BDhblf;
                            break;
                        }
                        break;
                    }
                    break;
                }
                case 5: {
                    this.bdhblF += 0.2f;
                    if ((int)(this.bdhblF * 100.0f) % 100 == 0) {
                        ++this.bDhbLF;
                    }
                    if (this.bdhblF <= 60.0f) {
                        break;
                    }
                    final float bdhblF = 60.0f;
                    this.bDhblf = 6;
                    this.bdhblF = bdhblF;
                    if (null != null) {
                        break;
                    }
                    break;
                }
                case 6: {
                    this.BdhBlF += 5.0E-5f;
                    if (this.BdhBlF > 0.0f) {
                        final float bdhBlF = 0.0f;
                        this.bDhblf = 7;
                        this.BdhBlF = bdhBlF;
                        break;
                    }
                    break;
                }
            }
            if (this.BdhBlf == null) {
                break;
            }
            if (!this.bDhBlf) {
                this.zzztb(this.bDHBLf + this.BdhBlF);
            }
            if (this.bDhBlf) {
                continue;
            }
            this.lTh();
            this.repaint();
            if (null == null) {
                continue;
            }
        }
    }
}
