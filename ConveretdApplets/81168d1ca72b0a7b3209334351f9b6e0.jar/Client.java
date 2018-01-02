import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.MediaTracker;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.net.URLClassLoader;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.AppletStub;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Client extends Applet implements Runnable, AppletStub, MouseListener, MouseMotionListener
{
    private volatile Thread a;
    private Applet b;
    private int c;
    private int d;
    private Image e;
    private Image f;
    private int g;
    private int h;
    private int i;
    private int j;
    private boolean k;
    
    public Client() {
        this.c = 0;
        this.d = 0;
        this.e = null;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = false;
    }
    
    public void init() {
        try {
            new Integer(this.getParameter("room"));
            this.c = new Integer(this.getParameter("iconsize"));
        }
        catch (Exception ex) {
            this.c = 0;
        }
        System.out.println("\nAddonChat v9.00.29.43 (en" + ((this.c > 0) ? ",big" : "") + ")");
        System.out.println("Copyright © 2001-2011, AddonInteractive. All Rights Reserved.\n");
        System.out.println("[Bootstrap]");
        try {
            System.out.println("   java.version: " + System.getProperty("java.version"));
            System.out.println("   java.vendor: " + System.getProperty("java.vendor"));
            System.out.println("   os.name: " + System.getProperty("os.name"));
            System.out.println("   os.arch: " + System.getProperty("os.arch"));
            System.out.println("   os.version: " + System.getProperty("os.version"));
            if (System.getProperty("java.vendor").indexOf("Apple") != -1) {
                System.out.println("   mrj.version: " + System.getProperty("mrj.version"));
            }
        }
        catch (Exception ex2) {}
        try {
            Class.forName("javax.jnlp.ServiceManager");
        }
        catch (Throwable t) {
            System.out.println("   jnlp: unavailable");
        }
        if (System.getProperty("java.vendor").indexOf("Microsoft") != -1) {
            System.out.println("Microsoft MVM detected. [Fatal]");
            this.a(1);
        }
        try {
            Class.forName("javax.swing.JApplet");
        }
        catch (ClassNotFoundException ex3) {
            System.out.println("This JRE does not support Swing. [Fatal]");
            this.a(1);
        }
        try {
            Class.forName("java.lang.Appendable");
        }
        catch (Throwable t2) {
            System.out.println("Java 5 or later required. [Fatal]");
            this.a(1);
        }
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        try {
            this.setBackground(Color.decode(this.getParameter("bgcolor")));
        }
        catch (Exception ex4) {}
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        final int size = graphics.getFont().getSize();
        switch (this.a()) {
            case 0: {
                if (this.e == null) {
                    this.e = this.a("loader.gif");
                }
                graphics.drawImage(this.e, (width - 24) / 2, (height - 24) / 2, this);
                break;
            }
            case 1: {
                graphics.setColor(Color.white);
                graphics.fillRect(0, 0, width, height);
                graphics.setColor(Color.black);
                graphics.drawString("This chat software requires a newer version of Java(tm)", (width - graphics.getFontMetrics().stringWidth("This chat software requires a newer version of Java(tm)")) / 2, height / 2 + size / 2 - size - 40);
                graphics.drawString("Please click the image below to download the latest Java(tm) software.", (width - graphics.getFontMetrics().stringWidth("Please click the image below to download the latest Java(tm) software.")) / 2, height / 2 + size / 2 + size - 40);
                if (this.f == null) {
                    this.f = this.a("getjava.gif");
                }
                final int g = (width - this.f.getWidth(this)) / 2;
                graphics.drawImage(this.f, g, height / 2, this);
                this.g = g;
                this.i = height / 2;
                this.h = g + this.f.getWidth(this);
                this.j = height / 2 + this.f.getHeight(this);
                break;
            }
            case 2: {
                graphics.setColor(Color.white);
                graphics.fillRect(0, 0, width, height);
                graphics.setColor(Color.black);
                graphics.drawString("We encountered a problem while attempting to load the chat room.", (width - graphics.getFontMetrics().stringWidth("We encountered a problem while attempting to load the chat room.")) / 2, height / 2 + size / 2 - size);
                graphics.drawString("Please refresh this page and try again.", (width - graphics.getFontMetrics().stringWidth("Please refresh this page and try again.")) / 2, height / 2 + size / 2 + size);
                break;
            }
            default: {
                graphics.setColor(Color.white);
                graphics.fillRect(0, 0, width, height);
                graphics.setColor(Color.black);
                break;
            }
        }
    }
    
    public void run() {
        try {
            final URL[] array = { null };
            if (this.c == 1) {
                array[0] = new URL(this.getCodeBase(), "AddonChat_en_lg.zip");
            }
            else if (this.c > 1) {
                array[0] = new URL(this.getCodeBase(), "AddonChat_en_xl.zip");
            }
            else {
                array[0] = new URL(this.getCodeBase(), "AddonChat_en.zip");
            }
            final URLClassLoader instance = URLClassLoader.newInstance(array);
            System.out.println("   [Loading Applet]");
            System.out.println("   applet.href: " + array[0]);
            (this.b = (Applet)instance.loadClass("AddonChat").newInstance()).setStub(this);
            this.setLayout(new GridLayout(1, 0));
            this.add(this.b);
            this.b.init();
            this.b.start();
            this.a(3);
        }
        catch (Exception ex) {
            System.out.println();
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println("A program  exception  has  occurred.  ACCOUNT OWNERS ONLY: Please");
            System.out.println("copy the following content and send it along with your account ID");
            System.out.println("number to  support@addonchat.com  when  reporting a  program  bug");
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println("Location: bootstrap::run()");
            System.out.println("Exception: " + ex.toString());
            System.out.println("Message: " + ex.getMessage());
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            ex.printStackTrace();
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println();
            this.a(2);
            this.repaint();
        }
        this.validate();
    }
    
    public void start() {
        if (!this.k) {
            final a a = new a(this);
            this.repaint();
            a.start();
            if (this.a() == 0) {
                (this.a = new Thread(this)).start();
            }
            this.k = true;
        }
    }
    
    protected synchronized int a() {
        return this.d;
    }
    
    protected synchronized void a(final int d) {
        this.d = d;
    }
    
    public void stop() {
        if (this.b != null) {
            this.b.stop();
        }
    }
    
    public void destroy() {
        if (this.b != null) {
            this.b.destroy();
        }
        this.a = null;
        this.a(3);
    }
    
    public void appletResize(final int n, final int n2) {
        this.resize(n, n2);
    }
    
    public Image a(final String s) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Image image = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(s));
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (Exception ex) {}
        this.repaint();
        return image;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (x >= this.g && x <= this.h && y >= this.i && y <= this.j) {
            this.setCursor(new Cursor(12));
        }
        else {
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (x >= this.g && x <= this.h && y >= this.i && y <= this.j) {
            try {
                this.getAppletContext().showDocument(new URL("http://www.java.com/"), "oracle");
            }
            catch (Exception ex) {}
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
}
