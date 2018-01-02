import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import menu_v3_0.c;
import java.awt.MediaTracker;
import menu_v3_0.g;
import java.util.Vector;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DisplayImage3_0 extends Applet implements Runnable, MouseMotionListener
{
    private String void;
    private String long;
    private String for;
    private Graphics case;
    private Image null;
    private Dimension c;
    private String else;
    private Image if;
    private Color try;
    private Vector char;
    private g a;
    private int d;
    private MediaTracker byte;
    private MediaTracker new;
    private Thread do;
    private String b;
    private Color goto;
    private boolean int;
    
    public synchronized void init() {
        if (this.int) {
            return;
        }
        final String long1 = this.long;
        final String parameter = this.getParameter("Author");
        if (parameter == null || long1 == null) {
            return;
        }
        if (!long1.replace('i', 'I').toUpperCase().equals(parameter.replace('i', 'I').toUpperCase())) {
            return;
        }
        this.else = this.getParameter("BGImage");
        this.try = menu_v3_0.c.a(this.getParameter("BGColor"), this.try);
        this.goto = menu_v3_0.c.a(this.getParameter("LoadingTextColor"), this.goto);
        this.b = this.getParameter("LoadingMessage");
        this.char = menu_v3_0.c.a(new String[] { this.getParameter("Images") }, this.getCodeBase(), false);
        (this.do = new Thread(this)).start();
        if (this.char.size() > 0) {
            this.a = this.char.elementAt(0);
            this.d = 0;
        }
        this.int = true;
        this.addMouseMotionListener(this);
    }
    
    public synchronized void start() {
    }
    
    public synchronized void stop() {
    }
    
    public synchronized void destroy() {
        if (this.do != null) {
            this.do = null;
        }
        if (this.if != null) {
            this.if.flush();
            this.if = null;
        }
        if (this.char != null) {
            for (int size = this.char.size(), i = 0; i < size; ++i) {
                final g g = this.char.elementAt(i);
                if (g.if != null) {
                    g.if.flush();
                    g.if = null;
                }
            }
        }
    }
    
    public void run() {
        final Thread currentThread = Thread.currentThread();
        Thread.currentThread().setPriority(1);
        if (this.do != currentThread) {
            return;
        }
        if (this.else != null && !this.else.equals("")) {
            final Image image = this.getImage(this.getCodeBase(), this.else);
            if (image != null) {
                this.byte.addImage(image, 0);
                final Image image2 = this.createImage(1, 1);
                final Graphics graphics = image2.getGraphics();
                graphics.setClip(0, 0, 1, 1);
                graphics.drawImage(image, 0, 0, this);
                try {
                    this.byte.waitForID(0);
                }
                catch (InterruptedException ex) {
                    return;
                }
                if (image2 != null) {
                    image2.flush();
                }
                if (currentThread != this.do) {
                    return;
                }
                this.if = image;
                this.repaint();
            }
        }
        for (int i = 0; i < this.char.size(); ++i) {
            if (this.do != currentThread) {
                return;
            }
            final g g = this.char.elementAt(i);
            if (g != null && g.do != null && !g.do.equals("")) {
                final Image image3 = this.getImage(this.getCodeBase(), g.do);
                if (image3 != null) {
                    this.new.addImage(image3, i);
                    final Image image4 = this.createImage(1, 1);
                    final Graphics graphics2 = image4.getGraphics();
                    graphics2.setClip(0, 0, 1, 1);
                    graphics2.drawImage(image3, 0, 0, this);
                    try {
                        this.new.waitForID(i);
                    }
                    catch (InterruptedException ex2) {
                        return;
                    }
                    if (image4 != null) {
                        image4.flush();
                    }
                }
                if (currentThread != this.do) {
                    return;
                }
                g.if = image3;
                this.repaint();
            }
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.showStatus("");
    }
    
    public synchronized void a() {
        if (this.char == null) {
            return;
        }
        if (this.char.size() > 0) {
            this.a = this.char.elementAt(0);
            this.d = 0;
            this.if(this.getGraphics());
        }
    }
    
    public synchronized void a(final String s) {
        if (this.char == null) {
            return;
        }
        if (s == null || s.length() == 0) {
            this.a = null;
            this.d = -1;
            this.if(this.getGraphics());
            return;
        }
        if (this.a == null || !s.equals(this.a.a)) {
            for (int i = 0; i < this.char.size(); ++i) {
                final g a = this.char.elementAt(i);
                if (s.equals(a.a)) {
                    this.a = a;
                    this.d = i;
                    this.if(this.getGraphics());
                    return;
                }
            }
            this.a = null;
            this.d = -1;
            this.if(this.getGraphics());
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        this.if(graphics);
    }
    
    public synchronized void if(final Graphics graphics) {
        this.a(graphics);
        graphics.drawImage(this.null, 0, 0, this);
    }
    
    public void a(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (size.width > 0 && size.height > 0) {
            if (this.null == null || this.c.width != size.width || this.c.height != size.height) {
                this.null = this.createImage(size.width, size.height);
                this.c = size;
                this.case = this.null.getGraphics();
            }
            this.case.setClip(0, 0, size.width, size.height);
            this.case.setColor(this.try);
            this.case.fillRect(0, 0, size.width, size.height);
            if (this.if != null && (this.byte.statusID(0, false) & 0x8) != 0x0) {
                this.case.drawImage(this.if, 0, 0, this);
            }
            if (this.d >= 0 && this.a != null && this.a.if != null && (this.new.statusID(this.d, false) & 0x8) != 0x0) {
                this.case.drawImage(this.a.if, (size.width - this.a.if.getWidth(this)) / 2, (size.height - this.a.if.getHeight(this)) / 2, this);
                return;
            }
            if (this.b != null) {
                final FontMetrics fontMetrics = this.getFontMetrics(new Font("Serif", 0, 9));
                final int height = fontMetrics.getHeight();
                final int stringWidth = fontMetrics.stringWidth(this.b);
                final int descent = fontMetrics.getDescent();
                final int n = (size.width - stringWidth) / 2;
                final int n2 = (size.height + height - descent) / 2;
                this.case.setColor(this.goto);
                this.case.drawString(this.b, n, n2);
            }
        }
    }
    
    public DisplayImage3_0() {
        this.void = "JTM - Java Tree Menu v3.0 - (c) exsys GbR Emden - www.exsys.net";
        this.long = "JTM - Java Tree Menu - (c) exsys GbR Emden - www.exsys.net";
        this.for = "by Raul Molino Garcia";
        this.try = Color.black;
        this.d = -1;
        this.byte = new MediaTracker(this);
        this.new = new MediaTracker(this);
        this.goto = Color.white;
        this.int = false;
    }
}
