// 
// Decompiled by Procyon v0.5.30
// 

package com.iseemedia.apps.tourclients40.players;

import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.MenuItem;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Font;
import java.awt.PopupMenu;
import com.iseemedia.apps.tourclients40.toolbar.g;
import com.iseemedia.apps.tourclients40.toolbar.a;
import java.awt.Frame;
import java.applet.AppletContext;
import java.awt.Color;
import com.iseemedia.apps.tourclients40.applets.b;
import java.awt.event.ActionListener;
import java.awt.Panel;

public abstract class h extends Panel implements Runnable, ActionListener
{
    public static int l;
    protected boolean m;
    protected b n;
    private Thread P;
    private boolean Q;
    private int R;
    private Color S;
    public AppletContext o;
    public com.iseemedia.apps.tourclients40.util.b p;
    protected Frame q;
    public int r;
    protected boolean s;
    protected float t;
    public boolean u;
    protected a v;
    public boolean w;
    public boolean x;
    public g y;
    protected boolean z;
    private Thread T;
    protected volatile int A;
    protected int B;
    public boolean C;
    public boolean D;
    protected boolean E;
    public boolean F;
    protected static String G;
    private PopupMenu U;
    protected int H;
    protected long I;
    public static boolean J;
    public boolean K;
    protected boolean L;
    protected boolean M;
    private boolean V;
    int N;
    int O;
    
    public final void o() {
        this.I = System.currentTimeMillis();
    }
    
    public final void b(final int h) {
        this.H = h;
        this.I = System.currentTimeMillis();
    }
    
    protected final boolean p() {
        return this.I + this.H < System.currentTimeMillis();
    }
    
    public h() {
        this.m = true;
        this.n = null;
        this.p = null;
        this.q = null;
        this.s = true;
        this.t = 0.0f;
        this.u = true;
        this.x = true;
        this.y = null;
        new Font("Arial Black", 1, 12);
        new Object();
        this.A = 0;
        this.B = 15;
        this.C = false;
        this.D = false;
        this.E = false;
        this.F = false;
        this.H = 1000000;
        this.I = 0L;
        this.K = false;
        this.L = true;
        this.V = false;
        this.N = 0;
        this.O = 0;
        h.J = true;
        this.w();
        this.enableEvents(56L);
    }
    
    public final void q() {
        if (this.P == null) {
            this.a();
            this.Q = false;
            this.R = 10;
            synchronized (this) {
                this.V = true;
            }
            (this.P = new Thread(this, "Player Thread")).start();
            if (this.C) {
                (this.T = new Thread(new com.iseemedia.apps.tourclients40.players.a(this), "progress tracker")).start();
            }
        }
        else {
            this.repaint();
            this.requestFocus();
        }
    }
    
    public final void r() {
        if (this.P != null) {
            boolean b = false;
            this.Q = true;
            while (this.P != null && !b) {
                try {
                    synchronized (this) {
                        while (this.V && this.P != null) {
                            this.wait(1L);
                        }
                    }
                }
                catch (InterruptedException ex) {}
                b = true;
            }
            this.P = null;
            this.c();
        }
    }
    
    public final void run() {
        try {
            Thread.sleep(this.R);
            while (!this.Q) {
                if (this.e()) {
                    Thread.yield();
                    this.R = 10;
                }
                else {
                    Thread.sleep(this.R);
                    if (this.R >= 75) {
                        continue;
                    }
                    this.R += 5;
                }
            }
        }
        catch (Throwable t) {}
        finally {
            this.P = null;
        }
        synchronized (this) {
            this.V = false;
            this.notifyAll();
        }
    }
    
    public void a(final int n) {
        if (n == 100) {
            this.p.b();
        }
        this.w = true;
    }
    
    public final boolean s() {
        final String property;
        if ((property = System.getProperty("java.version")).startsWith("1.1") && com.iseemedia.apps.tourclients40.resource.a.d) {
            int intValue = 5;
            final String substring = property.substring(property.lastIndexOf(".") + 1, property.lastIndexOf(".") + 2);
            try {
                intValue = Integer.valueOf(substring);
            }
            catch (NumberFormatException ex) {}
            return intValue > 7;
        }
        return true;
    }
    
    public final void b(final float t) {
        this.t = t;
        if (!(this.u = (this.t == 0.0f))) {
            this.b();
        }
    }
    
    protected void b() {
    }
    
    protected abstract boolean e();
    
    protected abstract void a();
    
    protected abstract void c();
    
    public abstract float d();
    
    public abstract void a(final Color p0);
    
    public final void t() {
        try {
            this.o.showDocument(new URL(this.v.i), "iseemedia");
        }
        catch (MalformedURLException ex) {}
    }
    
    public final void c(final int r) {
        this.r = r;
        if (this.q == null) {
            return;
        }
        if (!h.J) {
            return;
        }
        switch (this.r) {
            case 0: {
                this.q.setCursor(1);
            }
            case 1: {
                this.q.setCursor(1);
            }
            case 2: {
                this.q.setCursor(13);
            }
            default: {
                this.q.setCursor(0);
            }
        }
    }
    
    public final void a(final String s) {
        this.S = new Color(Integer.parseInt(s.substring(1), 16));
    }
    
    public final Color u() {
        return this.S;
    }
    
    protected final void a(final Graphics graphics) {
        new com.iseemedia.apps.tourclients40.resource.b();
        final int width = this.bounds().width;
        int height;
        if (h.l == 1) {
            height = this.bounds().height - com.iseemedia.apps.tourclients40.resource.b.g();
        }
        else {
            height = this.bounds().height;
        }
        graphics.setColor(this.S);
        graphics.fillRect(0, 0, width, height);
        this.M = true;
    }
    
    protected final void b(final Graphics graphics) {
        final int n = this.N + this.B + 1;
        final int n2 = this.O + this.B + 1;
        final int n4;
        final int n3 = n4 = (this.B - 1) * 2;
        graphics.setColor(this.S);
        graphics.fillArc(n, n2, n3, n4, 90 - this.A, 90);
        graphics.fillArc(n, n2, n3, n4, 270 - this.A, 90);
        graphics.setColor(Color.black);
        graphics.fillArc(n, n2, n3, n4, 0 - this.A, 90);
        graphics.fillArc(n, n2, n3, n4, 180 - this.A, 90);
        graphics.drawOval(n, n2, n3 - 1, n4 - 1);
    }
    
    private void w() {
        (this.U = new PopupMenu("")).addSeparator();
        final MenuItem menuItem;
        (menuItem = new MenuItem("HELP")).addActionListener(this);
        this.U.add(menuItem);
        this.U.addSeparator();
        this.add(this.U);
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        if (mouseEvent.isPopupTrigger()) {
            this.U.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
            this.E = false;
        }
        else {
            switch (mouseEvent.getID()) {
                case 501: {
                    if ((mouseEvent.getModifiers() & 0x10) == 0x10) {
                        this.E = true;
                    }
                    this.getParent().requestFocus();
                    this.requestFocus();
                    break;
                }
                case 502: {
                    this.E = false;
                    this.getParent().requestFocus();
                    this.requestFocus();
                    break;
                }
                case 504: {
                    h.J = true;
                    this.c(this.r);
                    break;
                }
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    public void processKeyEvent(final KeyEvent keyEvent) {
        switch (keyEvent.getID()) {
            case 401: {
                switch (keyEvent.getKeyCode()) {
                    case 65: {
                        if (this.y != null) {
                            this.y.a(0);
                        }
                        this.c(0);
                        this.k();
                        break;
                    }
                    case 90: {
                        if (this.y != null) {
                            this.y.a(1);
                        }
                        this.c(1);
                        this.l();
                        break;
                    }
                    case 86: {
                        this.K = true;
                    }
                    case 73:
                    case 112: {
                        if (this.y != null) {
                            this.y.e();
                            break;
                        }
                        break;
                    }
                    case 84: {
                        this.t();
                        break;
                    }
                }
            }
            case 402: {
                this.w = true;
                break;
            }
        }
        super.processKeyEvent(keyEvent);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        try {
            if (actionCommand.equals("HELP")) {
                this.n();
            }
        }
        catch (Exception ex) {}
        this.m();
    }
    
    public void n() {
    }
    
    public final void b(final String s) {
        final String[] array = { "Arial Black", "Font.BOLD", "12" };
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        int n;
        try {
            for (n = 0; n < 3 && stringTokenizer.hasMoreTokens(); ++n) {
                array[n] = stringTokenizer.nextToken().trim();
            }
        }
        catch (NumberFormatException ex) {
            System.out.println("Need the re format input");
            return;
        }
        if (n < 3) {
            return;
        }
        new Font(array[0], this.c(array[1]), Integer.parseInt(array[2]));
    }
    
    private int c(final String s) {
        final String[] array = { "Font.BOLD", "Font.CENTER_BASELINE", "Font.HANGING_BASELINE", "Font.ITALIC", "Font.PLAIN", "Font.ROMAN_BASELINE", "Font.TRUETYPE_FONT" };
        int n = 0;
        for (int i = 0; i < array.length; ++i) {
            if (s.equals(array[i])) {
                n = i;
                break;
            }
        }
        if (n == array.length) {
            n = 0;
        }
        return n;
    }
    
    public final Frame v() {
        return this.q;
    }
    
    public void a(final float n, final float n2, final float n3, final float n4) {
    }
    
    public void a(final float n, final float n2, final float n3, final float n4, final boolean b) {
    }
    
    public abstract void m();
    
    public synchronized boolean h() {
        return false;
    }
    
    public void a(final String s, final int n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final boolean b) {
    }
    
    public void a(final String s, final boolean b) {
    }
    
    public void a(final float n, final boolean b) {
    }
    
    public void a(final float n) {
    }
    
    public void a(final float[] array) {
    }
    
    public void k() {
    }
    
    public void l() {
    }
    
    static {
        h.l = 1;
        h.G = "http://www.realbiz360.com/viewerhelp.html";
    }
}
