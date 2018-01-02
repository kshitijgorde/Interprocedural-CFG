import java.awt.Component;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class veraEyes extends Applet implements Runnable
{
    private Thread m_veraEyes;
    private Graphics gscreen;
    private Graphics gbuf;
    private Image imEyeball;
    private Image imEyelids;
    private Image buf;
    private int m_nImgWidth;
    private int m_nImgHeight;
    boolean m_fAllLoaded;
    int screen_W;
    int screen_H;
    private int lx;
    private int ly;
    private int lcx;
    private int lcy;
    private int rx;
    private int ry;
    private int rcx;
    private int rcy;
    private int disp;
    private int eyerad;
    int mouse_X;
    int mouse_Y;
    private String m_eyeball;
    private String m_eyelids;
    
    public void stop() {
        if (this.m_veraEyes != null) {
            this.m_veraEyes.stop();
            this.m_veraEyes = null;
        }
    }
    
    public boolean mouseEnter(final Event event, final int i, final int j) {
        this.showStatus("Eyes applet by Xavier Potier, v-era.com");
        return true;
    }
    
    public void moveEyes(final int x, final int y) {
        final int xlx = x - this.lx;
        final int yly = y - this.ly;
        final int xrx = x - this.rx;
        final int yry = y - this.ry;
        int rsq = xlx * xlx + yly * yly;
        if (rsq > this.disp * this.disp) {
            this.lcx = this.lx - this.eyerad + (int)(this.disp * xlx / Math.sqrt(rsq));
            this.lcy = this.ly - this.eyerad + (int)(this.disp * yly / Math.sqrt(rsq));
        }
        else {
            this.lcx = x - this.eyerad;
            this.lcy = y - this.eyerad;
        }
        rsq = xrx * xrx + yry * yry;
        if (rsq > this.disp * this.disp) {
            this.rcx = this.rx - this.eyerad + (int)(this.disp * xrx / Math.sqrt(rsq));
            this.rcy = this.ry - this.eyerad + (int)(this.disp * yry / Math.sqrt(rsq));
        }
        else {
            this.rcx = x - this.eyerad;
            this.rcy = y - this.eyerad;
        }
        try {
            Thread.sleep(40L);
        }
        catch (InterruptedException ex) {}
    }
    
    public void paint(final Graphics g) {
        if (!this.m_fAllLoaded) {
            return;
        }
        this.moveEyes(this.mouse_X, this.mouse_Y);
        this.gbuf.drawImage(this.imEyeball, this.lcx, this.lcy, null);
        this.gscreen.drawImage(this.imEyeball, this.rcx, this.rcy, null);
        this.gbuf.drawImage(this.imEyelids, 0, 0, null);
        g.drawImage(this.buf, 0, 0, this);
    }
    
    public veraEyes() {
        this.lx = 68;
        this.ly = 68;
        this.rx = 196;
        this.ry = 68;
        this.disp = 32;
        this.eyerad = 32;
        this.m_eyeball = "";
        this.m_eyelids = "";
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void start() {
        if (this.m_veraEyes == null) {
            (this.m_veraEyes = new Thread(this)).start();
        }
    }
    
    public boolean mouseDown(final Event event, final int i, final int j) {
        try {
            this.getAppletContext().showDocument(new URL("http://www.v-era.com"));
        }
        catch (MalformedURLException ex) {}
        return true;
    }
    
    public void doEyes(final int x, final int y) {
        this.mouse_X = x;
        this.mouse_Y = y;
        this.repaint();
    }
    
    public void run() {
        if (!this.m_fAllLoaded) {
            final MediaTracker tracker = new MediaTracker(this);
            this.imEyeball = this.getImage(this.getDocumentBase(), this.m_eyeball);
            this.imEyelids = this.getImage(this.getDocumentBase(), this.m_eyelids);
            tracker.addImage(this.imEyeball, 0);
            tracker.addImage(this.imEyelids, 0);
            try {
                tracker.waitForAll();
                this.m_fAllLoaded = !tracker.isErrorAny();
            }
            catch (InterruptedException ex) {}
            if (!this.m_fAllLoaded) {
                this.stop();
                this.gscreen.drawString("Error loading images!", 10, 40);
                return;
            }
            this.m_nImgWidth = this.imEyeball.getWidth(this);
            this.m_nImgHeight = this.imEyeball.getHeight(this);
        }
        this.gscreen.clipRect(this.screen_W / 2, 0, this.screen_W / 2, this.screen_H);
        this.repaint();
    }
    
    public void init() {
        String param = this.getParameter("eyeball");
        if (param != null) {
            this.m_eyeball = param;
        }
        param = this.getParameter("eyelids");
        if (param != null) {
            this.m_eyelids = param;
        }
        param = this.getParameter("displacement");
        if (param != null) {
            this.disp = Integer.parseInt(param);
        }
        param = this.getParameter("leyeleft");
        if (param != null) {
            this.lx = Integer.parseInt(param);
        }
        param = this.getParameter("leyetop");
        if (param != null) {
            this.ly = Integer.parseInt(param);
        }
        param = this.getParameter("reyeleft");
        if (param != null) {
            this.rx = Integer.parseInt(param);
        }
        param = this.getParameter("reyetop");
        if (param != null) {
            this.ry = Integer.parseInt(param);
        }
        param = this.getParameter("ballradius");
        if (param != null) {
            this.eyerad = Integer.parseInt(param);
        }
        final int width = this.size().width;
        this.screen_W = width;
        final int height = this.size().height;
        this.screen_H = height;
        this.buf = this.createImage(width, height);
        this.gscreen = this.buf.getGraphics();
        this.gbuf = this.gscreen.create();
    }
    
    public boolean mouseMove(final Event evt, final int i, final int j) {
        this.mouse_X = i;
        this.mouse_Y = j;
        this.repaint();
        return true;
    }
}
