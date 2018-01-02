import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.Font;
import java.net.URL;
import java.awt.Image;
import java.awt.MediaTracker;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class b extends Applet implements Runnable
{
    private String _fldlong;
    private String _fldelse;
    private String b;
    private int _flddo;
    private String _fldtry;
    private String _fldnull;
    private String c;
    private MediaTracker _fldbyte;
    private Image _fldnew;
    private Thread e;
    private Thread d;
    private boolean _fldgoto;
    private int _fldchar;
    private long _fldvoid;
    private URL _fldcase;
    private c _fldif;
    private a a;
    private int _fldfor;
    Font _fldint;
    
    public b() {
        this._fldint = new Font("arial", 1, 12);
        this._fldtry = "contacting server...";
    }
    
    public void destroy() {
        this._flddo = 0;
        this._fldchar = 0;
        this._fldgoto = false;
        this.e = null;
        this._fldif._mthfor();
        this._fldif = null;
    }
    
    public void init() {
        this._fldlong = this.getParameter("filename");
        this.c = "down.jpg";
        final String parameter = this.getParameter("refresh");
        this._fldfor = 0;
        final String parameter2 = this.getParameter("preview");
        if (parameter2 != null) {
            this._fldfor = Integer.parseInt(parameter2);
        }
        if (parameter != null) {
            this._fldchar = Integer.parseInt(parameter);
        }
        else {
            this._fldchar = 0;
        }
        this._fldbyte = new MediaTracker(this);
        this.setLayout(null);
        this.setBackground(Color.white);
        this.setVisible(true);
        this._fldnull = this._fldtry;
        this._fldif = new c();
        final URL codeBase = this.getCodeBase();
        try {
            this._fldcase = new URL(codeBase.getProtocol() + "://" + codeBase.getHost() + ":" + codeBase.getPort() + "/");
        }
        catch (MalformedURLException ex) {}
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        if (this._fldnew != null) {
            graphics.drawImage(this._fldnew, 0, 0, this);
            return;
        }
        if (this._fldnull != null) {
            graphics.setFont(this._fldint);
            graphics.drawString(this._fldnull, 4, 12);
        }
    }
    
    public void a() throws Exception {
        if (this._fldnew != null) {
            this._fldnew.flush();
        }
        this._fldnew = this._fldif._mthif();
        this._fldbyte.addImage(this._fldnew, 0);
        this._fldbyte.waitForID(0);
        this._fldbyte.removeImage(this._fldnew, 0);
        this.repaint();
    }
    
    public void run() {
        this._fldgoto = true;
        this.showStatus(this._fldtry);
        while (this._fldgoto) {
            if (Thread.currentThread() == this.e) {
                try {
                    this.a();
                    if (this._fldchar <= 0) {
                        continue;
                    }
                    Thread.sleep(this._fldchar);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public void start() {
        this._fldif.a(false);
        if (this.e == null) {
            (this.e = new Thread(this)).start();
        }
        if (this.d == null) {
            this.a = new a(this._fldif, this._fldcase, this._fldlong, this, this._fldchar / 2, this._fldfor == 1);
            (this.d = new Thread(this.a)).start();
        }
    }
    
    public void stop() {
        this._fldgoto = false;
        if (this.e != null) {
            try {
                this.e.join();
            }
            catch (InterruptedException ex) {}
            this.e = null;
        }
        if (this.d != null) {
            this.a.a(false);
            this._fldif.a(true);
            try {
                this.d.join();
            }
            catch (InterruptedException ex2) {}
            this.d = null;
            this.a = null;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
