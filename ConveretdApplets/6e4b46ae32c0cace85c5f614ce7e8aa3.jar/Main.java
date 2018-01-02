import java.awt.Component;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Color;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Main extends Applet implements Runnable
{
    public final double a = 1.1;
    public Thread b;
    public c c;
    public int d;
    public int e;
    public Vector f;
    public int g;
    public int[] h;
    public int[] i;
    public int j;
    public Color k;
    public boolean l;
    public boolean m;
    public String n;
    public final int o = 50;
    public int p;
    
    public void stop() {
        if (this.b != null) {
            this.b.stop();
            this.b = null;
            this.destroy();
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        final e e = this.f.elementAt(this.g);
        if (e.b != null && this.l) {
            this.setCursor(new Cursor(12));
            this.showStatus(e.b);
        }
        else {
            this.showStatus(null);
        }
        return true;
    }
    
    private void a() {
        final int a = this.a(Math.random() * 5.0);
        boolean b = false;
        if (Math.random() < 0.5) {
            b = true;
        }
        this.a(a, b);
    }
    
    private void a(final int n, final boolean b) {
        final double n2 = 255.0 / this.d;
        final double n3 = 255.0 / this.e;
        double n4 = 0.0;
        int n5 = 0;
        switch (n) {
            case 0: {
                for (int i = 0; i < this.e; ++i) {
                    for (int j = 0; j < this.d; ++j) {
                        this.h[n5++] = (int)n4;
                    }
                    n4 += n3;
                }
                break;
            }
            case 1: {
                for (int k = 0; k < this.e; ++k) {
                    double n6 = 0.0;
                    for (int l = 0; l < this.d; ++l) {
                        this.h[n5++] = (int)n6;
                        n6 += n2;
                    }
                }
                break;
            }
            case 2: {
                for (int n7 = 0; n7 < this.e; ++n7) {
                    for (int n8 = 0; n8 < this.d; ++n8) {
                        this.h[n5++] = (int)(Math.sin(n8 / 15.0) * Math.cos(n7 / 10.0) * 127.0 + 127.0);
                    }
                }
                break;
            }
            case 3: {
                for (int n9 = 0; n9 < this.e; ++n9) {
                    for (int n10 = 0; n10 < this.d; ++n10) {
                        this.h[n5++] = (int)(Math.sin(n10 / 3.0) * 127.0 + 127.0);
                    }
                }
                break;
            }
            case 4: {
                for (int n11 = 0; n11 < this.e; ++n11) {
                    for (int n12 = 0; n12 < this.d; ++n12) {
                        this.h[n5++] = (int)(Math.sin(n11 / 3.0) * 127.0 + 127.0);
                    }
                }
                break;
            }
            case 5: {
                for (int n13 = 0; n13 < this.e; ++n13) {
                    double n14 = 0.0;
                    for (int n15 = 0; n15 < this.d; ++n15) {
                        if ((n15 / 12 + n13 / 12) % 2 == 0) {
                            this.h[n5++] = (int)n14;
                        }
                        else {
                            this.h[n5++] = 0;
                        }
                        n14 += n2;
                    }
                }
                break;
            }
        }
        if (b) {
            for (int n16 = 0; n16 < this.h.length; ++n16) {
                this.h[n16] = 255 - this.h[n16];
            }
        }
        this.j = -256;
    }
    
    private synchronized void b() {
        if (this.n != null) {
            return;
        }
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return;
        }
        final e e = this.f.elementAt(this.g);
        if (e.c == null) {
            final Image a = this.a(e.a);
            if (a == null) {
                return;
            }
            final Image image = this.createImage(this.d, this.e);
            final Graphics graphics2 = image.getGraphics();
            graphics2.setColor(this.k);
            graphics2.fillRect(0, 0, this.d, this.e);
            graphics2.drawImage(a, (this.d - a.getWidth(null)) / 2, (this.e - a.getHeight(null)) / 2, null);
            e.c = new c(image, this);
            graphics2.dispose();
        }
        final int[] i = this.i;
        final int[] b = e.c.b();
        final int[] b2 = this.c.b();
        for (int j = 0; j < b2.length; ++j) {
            int n = this.h[j] + this.j;
            if (n > 0) {
                if (n > 255) {
                    n = 255;
                }
                final int n2 = 255 - n;
                final int n3 = i[j];
                final int n4 = n3 >> 16 & 0xFF;
                final int n5 = n3 >> 8 & 0xFF;
                final int n6 = n3 & 0xFF;
                final int n7 = b[j];
                b2[j] = (n2 * n4 + n * (n7 >> 16 & 0xFF) >> 8 << 16 | n2 * n5 + n * (n7 >> 8 & 0xFF) >> 8 << 8 | n2 * n6 + n * (n7 & 0xFF) >> 8);
            }
        }
        final Image a2 = this.c.a();
        graphics.drawImage(a2, 0, 0, null);
        a2.flush();
        this.j += 10;
        if (this.j >= 255 + this.p) {
            this.i = e.c.b();
            this.a();
            this.g = (this.g + 1) % this.f.size();
        }
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.l) {
            this.setCursor(new Cursor(0));
        }
        return true;
    }
    
    private boolean a(final String s, final boolean b) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return b;
        }
        return parameter.indexOf("true") >= 0;
    }
    
    public void paint(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        if (this.n != null) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, this.d, this.e);
            graphics.setColor(Color.white);
            graphics.drawString(this.n, 0, this.e / 2);
            return;
        }
        graphics.setColor(this.k);
        graphics.fillRect(0, 0, this.d, this.e);
    }
    
    public Main() {
        this.b = null;
        this.m = false;
    }
    
    public synchronized boolean mouseUp(final Event event, final int n, final int n2) {
        final e e = this.f.elementAt(this.g);
        final String a = this.a("target" + (this.g + 1), this.a("target", "_top"));
        if (e.b == null) {
            return true;
        }
        final String b = e.b;
        try {
            URL url;
            if (b.toLowerCase().indexOf("http://") < 0) {
                url = new URL(this.getDocumentBase(), b);
            }
            else {
                url = new URL(b);
            }
            this.getAppletContext().showDocument(url, a);
        }
        catch (Exception ex) {}
        return true;
    }
    
    private Color a(final String s, final Color color) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return color;
        }
        return new Color(Integer.parseInt(parameter, 16));
    }
    
    public void destroy() {
        System.out.println("Cleanup");
        this.c = null;
        this.f = null;
        this.h = null;
        this.i = null;
        this.k = null;
        this.n = null;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void start() {
        System.out.println("Plutonium Software, BannerFx v" + 1.1);
        this.d = this.size().width;
        this.e = this.size().height;
        final b b = new b();
        this.m = b.a(this, "bann");
        final String[] array = { "www.plutoniumsoftware.com", "www.artistcope.com" };
        final String parameter = this.getParameter("info");
        boolean b2 = false;
        if (parameter != null) {
            final String lowerCase = parameter.toLowerCase();
            for (int i = 0; i < array.length; ++i) {
                if (lowerCase.indexOf("banner applet distributed by http://" + array[i]) >= 0) {
                    b2 = true;
                }
            }
        }
        if (!b2) {
            this.b("* Info parameter must describe valid distributor");
            return;
        }
        this.c = new c(this.d, this.e);
        this.h = new int[this.d * this.e];
        if (this.b == null) {
            (this.b = new Thread(this)).start();
        }
        this.p = this.a("delay", 5);
        this.p *= 200;
        this.f = new Vector();
        String s = this.getParameter("image1");
        for (int n = 1; s != null; s = this.getParameter("image" + ++n)) {
            this.f.addElement(new e(s));
        }
        if (!this.m) {
            this.f.addElement(new e("Config.class=http://www.plutoniumsoftware.com"));
        }
        this.g = 0;
        this.a();
        this.k = this.a("color", Color.black);
        this.l = f.b();
        this.i = new int[this.d * this.e];
        final int rgb = this.k.getRGB();
        final int[] b3 = this.c.b();
        for (int j = 0; j < this.i.length; ++j) {
            b3[j] = (this.i[j] = rgb);
        }
        if (this.a("random", false)) {
            for (int k = 0; k < this.f.size(); ++k) {
                final int a = this.a(Math.random() * this.f.size());
                final Object element = this.f.elementAt(k);
                this.f.setElementAt(this.f.elementAt(a), k);
                this.f.setElementAt(element, a);
            }
        }
        this.b.setPriority(10);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        return true;
    }
    
    private Image a(final String s) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Image image = this.getImage(this.getCodeBase(), s);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {}
        if (mediaTracker.isErrorAny()) {
            if (s.indexOf(".class") < 0) {
                this.b("* Required image missing: " + s);
            }
            else {
                this.b("* Internal error, missing Config.class file");
            }
            return null;
        }
        return image;
    }
    
    public void run() {
        while (true) {
            try {
                while (true) {
                    this.b();
                    Thread.sleep(50L);
                }
            }
            catch (Exception ex) {
                continue;
            }
            break;
        }
    }
    
    private int a(final double n) {
        if (n < 0.0) {
            return (int)(n - 0.5);
        }
        return (int)(n + 0.5);
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        return true;
    }
    
    private String a(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return s2;
        }
        return parameter;
    }
    
    private int a(final String s, final int n) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            return n;
        }
        return Integer.parseInt(parameter);
    }
    
    private synchronized void b(final String n) {
        System.out.println(n);
        this.n = n;
        this.repaint();
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        return true;
    }
}
