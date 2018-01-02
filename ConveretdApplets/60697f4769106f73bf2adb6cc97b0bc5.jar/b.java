import java.net.UnknownHostException;
import java.net.InetAddress;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.Cursor;
import java.io.IOException;
import java.util.Hashtable;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class b extends Applet implements MouseMotionListener, MouseListener, Runnable
{
    private int c;
    private int e;
    private int f;
    private boolean j;
    private static String[] g;
    private boolean i;
    private Color k;
    private Image h;
    private int b;
    private d[] d;
    private Thread a;
    
    public void update(final Graphics graphics) {
        if (this.h != null) {
            graphics.drawImage(this.h, 0, 0, null);
        }
    }
    
    public void run() {
        if (this.a()) {
            this.i = false;
        }
        final String parameter = this.getParameter("copyright");
        if (parameter == null) {
            return;
        }
        if (!parameter.equalsIgnoreCase("(c) 2000 Dan MacFarlane. http://www.dancity.com/")) {
            return;
        }
        Image image = null;
        if (this.getParameter("background") != null) {
            try {
                image = this.getImage(new URL(this.getDocumentBase(), this.getParameter("background")));
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(image, 0);
                mediaTracker.waitForID(0);
            }
            catch (InterruptedException ex) {
                System.out.println("ScrollerII -> Interrupted Loading Background Image.");
            }
            catch (MalformedURLException ex2) {
                System.out.println("ScrollerII -> MalformedURLException (Background).");
            }
        }
        final int n = (this.getParameter("speed") != null) ? (20 - Integer.parseInt(this.getParameter("speed"))) : 20;
        int n2 = 0;
        try {
            final StreamTokenizer streamTokenizer = new StreamTokenizer(new InputStreamReader(new URL(this.getDocumentBase(), this.getParameter("scrollText")).openStream()));
            streamTokenizer.slashSlashComments(false);
            streamTokenizer.quoteChar(34);
            streamTokenizer.ordinaryChar(39);
            streamTokenizer.ordinaryChar(47);
            streamTokenizer.ordinaryChar(46);
            final Vector a = new c(streamTokenizer, "", null, this).a();
            this.d = new d[a.size()];
            for (int i = 0; i < a.size(); ++i) {
                this.d[i] = a.elementAt(i);
                n2 += this.d[i].a();
            }
        }
        catch (MalformedURLException ex3) {
            System.out.println("ScrollerII -> MalformedURLException (Scrolltext).");
        }
        catch (IOException ex4) {
            System.out.println("ScrollerII -> IOException (Scrolltext).");
        }
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        this.h = this.createImage(width, height);
        this.b = width;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        String s = null;
        if (this.i && n2 > 4000) {
            n2 = 4000;
        }
        while (this.b > -n2) {
            String s2 = null;
            final Graphics graphics = this.h.getGraphics();
            graphics.setColor(this.k);
            graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
            if (image != null) {
                graphics.drawImage(image, 0, 0, null);
            }
            int b = this.b;
            for (int j = 0; j < this.d.length; ++j) {
                final int a2 = this.d[j].a();
                if (b + a2 > 0 || b < width) {
                    final String a3 = this.d[j].a(graphics, b, height / 2 + 4, this.e > b && this.e < b + a2 && this.e > 0);
                    if (a3 != null) {
                        s2 = a3;
                    }
                }
                b += a2;
            }
            this.repaint();
            if (s2 != null) {
                if (!s2.equals(s)) {
                    s = s2;
                    this.setCursor(Cursor.getPredefinedCursor(12));
                    this.showStatus(s);
                }
            }
            else if (s != null) {
                s = null;
                this.setCursor(Cursor.getPredefinedCursor(0));
                this.showStatus("");
            }
            --this.b;
            if (!this.i && this.b <= -n2) {
                this.b = width;
            }
            if (this.j) {
                this.b = this.c - this.f + this.e;
            }
            if (n > 0) {
                try {
                    Thread.sleep(n);
                }
                catch (InterruptedException ex5) {}
            }
        }
        if (this.i) {
            final Graphics graphics2 = this.h.getGraphics();
            graphics2.setColor(Color.black);
            graphics2.fillRect(0, 0, this.getSize().width, this.getSize().height);
            graphics2.setColor(Color.white);
            final String s3 = "ScrollerII Trial Version. (www.dancity.com)";
            graphics2.drawString(s3, width / 2 - graphics2.getFontMetrics().stringWidth(s3) / 2, height - 6);
            this.repaint();
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.j = false;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.c = this.b;
        final int x = mouseEvent.getX();
        this.e = x;
        this.f = x;
        this.j = true;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.e = mouseEvent.getX();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.e = 0;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.e = mouseEvent.getX();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        int b = this.b;
        for (int i = 0; i < this.d.length; ++i) {
            final int a = this.d[i].a();
            if (this.e > b && this.e < b + a) {
                this.d[i].b();
            }
            b += a;
        }
    }
    
    private boolean a() {
        String s = this.getDocumentBase().getHost();
        String hostName = "";
        String hostAddress = "";
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '.') {
                ++n;
            }
        }
        if (!s.startsWith("www") && n == 1) {
            s = "www." + s;
        }
        try {
            final InetAddress byName = InetAddress.getByName(this.getDocumentBase().getHost());
            hostName = byName.getHostName();
            hostAddress = byName.getHostAddress();
        }
        catch (UnknownHostException ex) {
            System.out.println("UHE");
        }
        for (int j = 0; j < b.g.length; ++j) {
            if (b.g[j].equals(s)) {
                return true;
            }
            if (b.g[j].equals(hostName)) {
                return true;
            }
            if (b.g[j].equals(hostAddress)) {
                return true;
            }
        }
        return false;
    }
    
    public void init() {
        this.setBackground((this.getParameter("bgcolor") != null) ? Color.decode(this.getParameter("bgcolor")) : Color.white);
        this.a.start();
    }
    
    public b() {
        this.a = new Thread(this);
        this.h = null;
        this.i = true;
        this.j = false;
    }
    
    static {
        b.g = new String[] { "www.dancity.com", "www.dancity.freeserve.co.uk" };
    }
}
