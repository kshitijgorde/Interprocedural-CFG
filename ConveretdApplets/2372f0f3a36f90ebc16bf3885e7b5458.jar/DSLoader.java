import java.awt.Event;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Image;
import java.applet.AppletStub;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DSLoader extends Applet implements Runnable, AppletStub
{
    public String a;
    public Thread b;
    public d c;
    public Image d;
    public boolean e;
    public boolean f;
    public int g;
    public Applet h;
    
    public DSLoader() {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = false;
        this.f = false;
        this.g = 0;
        this.h = null;
    }
    
    public void init() {
        super.init();
        this.a = this.getParameter(zkmToString("ngRn>pICz7g|"));
        final String parameter;
        if ((parameter = this.getParameter(zkmToString("ngRn>pIF~4NgRn"))) != null && Boolean.valueOf(parameter)) {
            this.g = 1;
        }
        final String parameter2;
        if ((parameter2 = this.getParameter(zkmToString("@iPa<pgFd?Ag_e)"))) != null) {
            this.setBackground(new Color(Integer.valueOf(parameter2.substring(1), 16)));
        }
        final String parameter3;
        if ((parameter3 = this.getParameter(zkmToString("VmK~\u0018md\\x"))) != null) {
            this.setForeground(new Color(Integer.valueOf(parameter3.substring(1), 16)));
        }
        if (this.a != null) {
            this.d = this.getImage(this.getCodeBase(), this.getParameter(zkmToString("ngRn>pA^k<g")));
        }
        if (this.d != null) {
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(this.d, 0);
            try {
                mediaTracker.waitForAll();
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            this.c = new d(this.d);
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            this.setLayout(layout);
            gridBagConstraints.anchor = 11;
            layout.setConstraints(this.c, gridBagConstraints);
            this.add(this.c);
        }
    }
    
    public void paint(final Graphics graphics) {
        String s;
        if (this.a == null) {
            s = zkmToString("KfEk7kl\u0013f4clVx\u001arx_o/\"xRx:omGo),");
        }
        else {
            s = zkmToString("AdZi0\"|\\*7miW*/jm\u0013K+rdV~u");
        }
        graphics.drawString(s, 4, 30);
        this.showStatus(s);
        if (this.g == 1) {
            this.g = 2;
        }
    }
    
    public void run() {
        while (true) {
            try {
                if (this.c != null) {
                    this.c.repaint(50L);
                    if (this.g == 1) {
                        this.g = 2;
                    }
                }
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
            if (this.g == 2) {
                this.a();
            }
        }
    }
    
    public void a() {
        if (!this.e && !this.f && this.a != null) {
            this.f = true;
            try {
                this.showStatus(zkmToString("NgRn2lo\u0013") + this.a);
                (this.h = (Applet)Class.forName(this.a).newInstance()).setStub(this);
                this.setLayout(new GridLayout(1, 0));
                this.add(this.h);
                this.h.init();
                this.h.start();
                this.showStatus(this.a + zkmToString("\"d\\k?gl"));
                this.e = true;
                if (this.c != null) {
                    this.remove(this.c);
                    this.c = null;
                }
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
            this.validate();
            this.f = false;
        }
    }
    
    public void start() {
        (this.b = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.b != null) {
            this.b.stop();
            this.b = null;
        }
        if (this.h != null) {
            this.h.stop();
            this.h.destroy();
        }
    }
    
    public synchronized void destroy() {
        this.stop();
        super.destroy();
    }
    
    public boolean action(final Event event, final Object o) {
        if (this.c != null && event.target == this.c) {
            this.a();
        }
        return false;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.a();
        return false;
    }
    
    public void appletResize(final int n, final int n2) {
        this.resize(n, n2);
    }
    
    private static String zkmToString(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '\u0002';
                    break;
                }
                case 1: {
                    c2 = '\b';
                    break;
                }
                case 2: {
                    c2 = '3';
                    break;
                }
                case 3: {
                    c2 = '\n';
                    break;
                }
                default: {
                    c2 = '[';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
