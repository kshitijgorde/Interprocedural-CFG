import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class HorizontalPro extends Applet implements Runnable
{
    private int j;
    private int k;
    private int l;
    private boolean t;
    private boolean i;
    int s;
    Image p;
    private Object g;
    private boolean f;
    private boolean m;
    private Font c;
    private Color o;
    private Color d;
    private Color u;
    private int r;
    private int a;
    private int n;
    private int h;
    private Vector e;
    private Image q;
    private Thread b;
    
    static Vector a(final HorizontalPro horizontalPro) {
        return horizontalPro.e;
    }
    
    static int b(final HorizontalPro horizontalPro) {
        return horizontalPro.n;
    }
    
    static int a(final HorizontalPro horizontalPro, final int n) {
        return horizontalPro.n = n;
    }
    
    static Color c(final HorizontalPro horizontalPro) {
        return horizontalPro.o;
    }
    
    static Font d(final HorizontalPro horizontalPro) {
        return horizontalPro.c;
    }
    
    static Color e(final HorizontalPro horizontalPro) {
        return horizontalPro.d;
    }
    
    static Color f(final HorizontalPro horizontalPro) {
        return horizontalPro.u;
    }
    
    static int a(final HorizontalPro horizontalPro, final String s) {
        return horizontalPro.a(s);
    }
    
    private String a() {
        String string = "";
        URL url = null;
        try {
            url = new URL(this.getDocumentBase(), this.getParameter("file"));
        }
        catch (MalformedURLException ex) {
            System.out.println("Bad scroll-text file URL: " + ex);
        }
        InputStream openStream = null;
        try {
            openStream = url.openStream();
        }
        catch (IOException ex2) {
            System.out.println("Error opening scroll-text file: " + ex2);
        }
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openStream));
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                string += line;
            }
        }
        catch (IOException ex3) {
            System.out.println("Error reading scroll-text file: " + ex3);
        }
        try {
            openStream.close();
        }
        catch (IOException ex4) {
            System.out.println("Error closing scroll-text file: " + ex4);
        }
        return string;
    }
    
    private int a(final String s) {
        final Graphics graphics = this.getGraphics();
        graphics.setFont(this.c);
        return graphics.getFontMetrics().stringWidth(s);
    }
    
    public Image b(final String s) {
        URL url;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (MalformedURLException ex2) {
            System.out.println("Bad Image URL in ScrollText.");
            return null;
        }
        final Image image = this.getImage(url);
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {
            System.out.println("Error Loading Scroller Images: " + ex);
        }
        return image;
    }
    
    public boolean mouseMove(final Event event, final int k, final int n) {
        this.k = k;
        if (this.m) {
            this.i = true;
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int k, final int n) {
        this.k = k;
        return this.t = true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (!this.t) {
            this.a(n);
        }
        this.t = false;
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.j = this.h;
        this.k = n;
        this.l = n;
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.k = 0;
        this.i = false;
        return true;
    }
    
    public void a(final int n) {
        for (int i = 0; i < this.e.size(); ++i) {
            final b b = this.e.elementAt(i);
            if (b.a(this.h, this.h + this.a) && b.a(b) && b.b(b) - this.h < n && b.b(b) + b.c(b) - this.h > n && n > 0) {
                b.d(b);
            }
        }
    }
    
    public void stop() {
        try {
            if (this.b != null) {
                this.b.stop();
            }
        }
        catch (Exception ex) {}
        this.b = null;
    }
    
    public void start() {
        if (this.b == null) {
            (this.b = new Thread(this)).start();
        }
    }
    
    public void run() {
        final String parameter = this.getParameter("copyright");
        if (parameter == null) {
            return;
        }
        if (!parameter.equalsIgnoreCase("(c) 2000 Dan MacFarlane.")) {
            return;
        }
        int int1 = 12;
        if (this.getParameter("fontSize") != null) {
            try {
                int1 = Integer.parseInt(this.getParameter("fontSize"));
            }
            catch (NumberFormatException ex2) {
                System.out.println("Scroller: Bad Font Size Specified.");
            }
        }
        String parameter2 = this.getParameter("fontName");
        if (parameter2 == null) {
            parameter2 = "";
        }
        this.c = new Font(parameter2, 0, int1);
        final String parameter3 = this.getParameter("mousepause");
        if (parameter3 == null) {
            this.m = false;
        }
        else if (parameter3.equals("on") || parameter3.equals("true")) {
            this.m = true;
        }
        if (this.getParameter("fontcolor") != null) {
            this.d = this.c(this.getParameter("fontcolor"));
        }
        if (this.getParameter("linkcolor") != null) {
            this.o = this.c(this.getParameter("linkcolor"));
        }
        final Dimension size = this.size();
        this.a = size.width;
        this.r = size.height;
        try {
            final int r = (this.getParameter("height") != null) ? Integer.parseInt(this.getParameter("height")) : this.r;
            if (this.r != r) {
                this.r = r;
            }
        }
        catch (NumberFormatException ex3) {
            System.out.println("Could not determine correct applet height.");
        }
        String s = this.getParameter("text");
        if (s == null) {
            if (this.getParameter("file") != null) {
                s = this.a();
            }
            else {
                s = "Error: unable to determine scroll-text.";
            }
        }
        final a a = new a(this, this.a, this.r, s);
        final int n = (this.getParameter("speed") != null) ? (20 - Integer.parseInt(this.getParameter("speed"))) : 20;
        this.q = this.createImage(this.a, this.r);
        this.p = this.createImage(this.a, this.r);
        while (this.q != null) {
            if (this.t) {
                this.h = this.j + this.l - this.k;
            }
            else if (!this.i) {
                ++this.h;
            }
            if (this.h > this.n) {
                this.h = 0;
            }
            this.q.getGraphics().drawImage(this.p, 0, 0, this);
            this.f = false;
            this.repaint();
            this.b();
            synchronized (this.g) {
                while (!this.f) {
                    try {
                        this.g.wait(100L);
                    }
                    catch (InterruptedException ex) {
                        System.out.println("Error in synchronized wait(): " + ex);
                        System.exit(0);
                    }
                }
            }
            if (n > 0) {
                try {
                    Thread.sleep(n);
                }
                catch (InterruptedException ex4) {}
            }
        }
    }
    
    private void b() {
        final Graphics graphics = this.p.getGraphics();
        graphics.setColor(this.u);
        graphics.fillRect(0, 0, this.a, this.r);
        for (int i = 0; i < this.e.size(); ++i) {
            final b b = this.e.elementAt(i);
            if (b.a(this.h, this.h + this.a)) {
                boolean b2 = false;
                if (b.b(b) - this.h < this.k && b.b(b) + b.c(b) - this.h > this.k && this.k > 0) {
                    b2 = true;
                }
                graphics.drawImage(b.a(b2), b.b(b) - this.h, 0, null);
            }
        }
    }
    
    public void update(final Graphics graphics) {
        graphics.drawImage(this.q, 0, 0, this);
        this.f = true;
        synchronized (this.g) {
            this.g.notifyAll();
        }
    }
    
    public void init() {
        if (this.getParameter("bgcolor") != null) {
            this.u = this.c(this.getParameter("bgcolor"));
        }
        this.setBackground(this.u);
        this.b.start();
    }
    
    private Color c(String s) {
        s = s.trim();
        if (s.startsWith("#")) {
            s = s.substring(1);
        }
        return new Color(Integer.parseInt(s, 16));
    }
    
    public HorizontalPro() {
        this.b = new Thread(this);
        this.q = null;
        this.e = new Vector();
        this.h = 0;
        this.u = Color.black;
        this.d = Color.white;
        this.o = Color.blue;
        this.m = false;
        this.f = false;
        this.g = new Object();
        this.i = false;
        this.t = false;
    }
}
