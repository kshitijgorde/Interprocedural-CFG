// 
// Decompiled by Procyon v0.5.30
// 

package pa.a.a.a;

import java.awt.Rectangle;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.AWTEventMulticaster;
import java.awt.MediaTracker;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.awt.event.ActionListener;
import java.applet.AppletContext;
import java.awt.Image;
import java.awt.Component;

public class a extends Component
{
    public static int int;
    public static int goto;
    private int do;
    private Image h;
    private Image c;
    private Image d;
    public int else;
    public int for;
    private String if;
    private boolean try;
    private boolean void;
    private boolean e;
    public static int byte;
    public static int char;
    public static int long;
    public static int null;
    private int a;
    private AppletContext new;
    private String case;
    private String b;
    private ActionListener g;
    public boolean f;
    
    static {
        a.int = 0;
        a.goto = 1;
        a.byte = 4;
        a.char = 3;
        a.long = 2;
        a.null = 1;
    }
    
    public a(final Image d) {
        this.do = pa.a.a.a.a.goto;
        this.void = false;
        this.e = false;
        this.b = "ZOOM Server 3.5";
        this.g = null;
        this.f = false;
        this.d = d;
        this.else = this.d.getWidth(null);
        this.for = this.d.getHeight(null);
        this.setVisible(true);
    }
    
    public a(final Image image, final Image image2, final String s, final boolean b, final int n, final AppletContext appletContext, final String s2) {
        this(image, image2, s, b, false, n, appletContext, s2);
    }
    
    public a(final Image h, final Image c, final String name, final boolean void1, final boolean f, final int a, final AppletContext new1, final String case1) {
        this.do = pa.a.a.a.a.goto;
        this.void = false;
        this.e = false;
        this.b = "ZOOM Server 3.5";
        this.g = null;
        this.f = false;
        this.a = a;
        this.void = void1;
        this.f = f;
        this.setName(name);
        this.case = case1;
        this.new = new1;
        this.h = h;
        this.c = c;
        this.d = this.c;
        this.else = this.d.getWidth(null);
        this.for = this.d.getHeight(null);
        final a a2 = new a();
        this.addMouseListener(a2);
        this.addMouseMotionListener(a2);
        this.setVisible(true);
    }
    
    private boolean a(final Image image, final Component component) {
        if (image == null) {
            return false;
        }
        final MediaTracker mediaTracker = new MediaTracker(component);
        mediaTracker.addImage(image, 1);
        try {
            mediaTracker.waitForID(1);
        }
        catch (InterruptedException ex) {
            return false;
        }
        return !mediaTracker.isErrorAny();
    }
    
    static /* synthetic */ void access$1(final a a, final boolean e) {
        a.e = e;
    }
    
    static /* synthetic */ void access$5(final a a, final boolean try1) {
        a.try = try1;
    }
    
    public synchronized void a(final ActionListener actionListener) {
        this.g = AWTEventMulticaster.add(this.g, actionListener);
    }
    
    public void do() {
        if (this.g != null) {
            this.g.actionPerformed(new ActionEvent(this, 1001, this.if));
        }
    }
    
    public String getName() {
        return this.if;
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.else, this.for);
    }
    
    public int a() {
        return this.do;
    }
    
    public void paint(final Graphics graphics) {
        this.a(this.d);
    }
    
    public void a(final Image image) {
        final Graphics graphics = this.getGraphics();
        if (this.a(image, this)) {
            graphics.drawImage(image, 0, 0, this);
        }
        graphics.dispose();
    }
    
    public void if() {
        final Graphics graphics = this.getGraphics();
        if (this.a(this.c, this)) {
            graphics.drawImage(this.c, 0, 0, this);
        }
        this.do = pa.a.a.a.a.goto;
        this.d = this.c;
        graphics.dispose();
    }
    
    public void for() {
        final Graphics graphics = this.getGraphics();
        if (this.a(this.h, this)) {
            graphics.drawImage(this.h, 0, 0, this);
        }
        this.do = pa.a.a.a.a.int;
        this.d = this.h;
        graphics.dispose();
    }
    
    public synchronized void if(final ActionListener actionListener) {
        this.g = AWTEventMulticaster.remove(this.g, actionListener);
    }
    
    public void a(final MouseListener mouseListener, final MouseMotionListener mouseMotionListener) {
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseMotionListener);
    }
    
    public void setName(final String if1) {
        this.if = if1;
    }
    
    public void a(final int do1) {
        if (this.c == null) {
            return;
        }
        if (do1 == pa.a.a.a.a.int) {
            this.for();
        }
        else {
            this.if();
        }
        this.do = do1;
    }
    
    private class a extends MouseAdapter implements MouseMotionListener
    {
        public void mouseClicked(final MouseEvent mouseEvent) {
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            if (new Rectangle(new Point(), pa.a.a.a.a.this.getSize()).contains(mouseEvent.getPoint())) {
                if (!pa.a.a.a.a.this.try) {
                    pa.a.a.a.a.access$5(pa.a.a.a.a.this, true);
                    pa.a.a.a.a.this.for();
                }
            }
            else if (pa.a.a.a.a.this.try) {
                pa.a.a.a.a.access$5(pa.a.a.a.a.this, false);
                pa.a.a.a.a.this.if();
            }
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            pa.a.a.a.a.this.new.showStatus(pa.a.a.a.a.this.case);
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            pa.a.a.a.a.this.new.showStatus(pa.a.a.a.a.this.b);
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            pa.a.a.a.a.access$1(pa.a.a.a.a.this, true);
            if (pa.a.a.a.a.this.a == pa.a.a.a.a.byte) {
                if (pa.a.a.a.a.this.a() == pa.a.a.a.a.int) {
                    pa.a.a.a.a.this.if();
                }
                else {
                    pa.a.a.a.a.this.for();
                }
            }
            else if (pa.a.a.a.a.this.a() == pa.a.a.a.a.int && pa.a.a.a.a.this.f) {
                pa.a.a.a.a.this.if();
            }
            else {
                pa.a.a.a.a.this.for();
            }
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            pa.a.a.a.a.access$1(pa.a.a.a.a.this, false);
            if (pa.a.a.a.a.this.a != pa.a.a.a.a.byte) {
                if (pa.a.a.a.a.this.void && !pa.a.a.a.a.this.f) {
                    pa.a.a.a.a.this.if();
                }
                else if (pa.a.a.a.a.this.f) {
                    pa.a.a.a.a.this.for();
                }
            }
            if (pa.a.a.a.a.this.contains(mouseEvent.getX(), mouseEvent.getY())) {
                pa.a.a.a.a.this.do();
            }
        }
    }
}
