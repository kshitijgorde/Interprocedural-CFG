// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.GridLayout;
import java.util.StringTokenizer;
import java.awt.Label;
import java.awt.Insets;
import ji.v1base.b5;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Color;
import ji.v1event.ob;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.awt.BorderLayout;
import java.awt.event.KeyListener;
import ji.util.d;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import ji.io.h;
import ji.util.i;
import ji.util.e;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionListener;
import java.awt.Rectangle;
import java.awt.Component;
import ji.v1event.ah;
import ji.v1base.jiPanel;
import ji.v1base.bn;
import ji.v1base.bl;

public class bk extends bl
{
    private bn a;
    private bn b;
    private bn c;
    private bn d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private boolean j;
    private static boolean k;
    private s1 l;
    private bs m;
    private jiPanel n;
    private bj o;
    private boolean p;
    private String q;
    private tj r;
    private String s;
    private String t;
    private bn u;
    private ah v;
    private Component w;
    private boolean x;
    private static Rectangle y;
    private boolean z;
    private ActionListener aa;
    private KeyAdapter ab;
    
    public bk(final Component component, final Frame frame, final bj bj, final String s, final String s2, final boolean b, final boolean b2, final String s3, final String s4, final String s5, final boolean b3, final boolean b4) {
        super(frame, s, b);
        this.e = "";
        this.f = "";
        this.g = "";
        this.j = false;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = false;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = false;
        this.z = false;
        this.aa = new td();
        this.ab = new te();
        this.a(component, bj, s, s2, b, b2, s3, s4, s5, b3, b4);
    }
    
    public bk(final Component component, final Dialog dialog, final bj bj, final String s, final String s2, final boolean b, final boolean b2, final String s3, final String s4, final String s5, final boolean b3, final boolean b4) {
        super(dialog, s, b);
        this.e = "";
        this.f = "";
        this.g = "";
        this.j = false;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = false;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = false;
        this.z = false;
        this.aa = new td();
        this.ab = new te();
        this.a(component, bj, s, s2, b, b2, s3, s4, s5, b3, b4);
    }
    
    private final void a(final Component w, final bj o, final String s, final String i, final boolean b, final boolean j, final String q, final String t, final String s2, final boolean x, final boolean b2) {
        try {
            this.x = x;
            this.z = false;
            this.p = false;
            this.v = ji.util.e.w;
            if (i.c(124)) {
                ji.io.h.d(t, "Shutdown dialog: modal ".concat(String.valueOf(String.valueOf(b))));
            }
            this.t = t;
            this.j = j;
            this.q = q;
            this.s = s2;
            this.i = i;
            this.w = w;
            (this.n = new jiPanel(t)).setBorderStyle(0);
            this.o = o;
            if (this.e()) {
                this.n.setLayout(new FlowLayout());
            }
            this.g = String.valueOf(String.valueOf(new StringBuffer("  ").append(ji.util.d.bc(ji.util.d.b(946, t))).append("  ")));
            if (ji.util.d.by(ji.util.d.bc(this.g))) {
                this.g = "Copy";
            }
            if (s2 != null || b2) {
                this.d = new bn(t, String.valueOf(String.valueOf(new StringBuffer("   ").append(this.g).append("   "))));
                this.n.add(this.d);
            }
            if (j) {
                this.e = String.valueOf(String.valueOf(new StringBuffer("       ").append(ji.util.d.bc(ji.util.d.b(233, t))).append("       ")));
                this.b = new bn(t, this.e);
                this.f = String.valueOf(String.valueOf(new StringBuffer("       ").append(ji.util.d.bc(ji.util.d.b(234, t))).append("       ")));
                this.c = new bn(t, this.f);
                this.n.add(this.b);
                this.n.add(this.c);
            }
            else {
                final String bc = ji.util.d.bc(ji.util.d.b(232, t));
                String value;
                if (bc == null) {
                    value = "       OK       ";
                }
                else if (bc.equals("")) {
                    value = "       OK       ";
                }
                else {
                    value = String.valueOf(String.valueOf(new StringBuffer("       ").append(bc).append("       ")));
                }
                this.e = value;
                this.a = new bn(t, value);
                this.n.add(this.a);
            }
            if (s2 != null) {
                (this.m = new bs(w, i, s2, 400, 350, t)).addKeyListener(this.ab);
                this.d().setLayout(new BorderLayout());
                this.d().add("Center", this.m);
                this.d().add("South", this.n);
            }
            else if (q != null) {
                this.d().setLayout(null);
                this.l = new s1(t, w, i, q, s2);
                this.d().add(this.l);
                this.l.setBounds(5, 25, 450, 80);
                this.d().add(this.r = new tj(t, q));
                this.r.setBounds(5, 100, 450, 300);
                this.d().add(this.n);
                this.n.setBounds(5, 400, 450, 50);
                this.setBounds(10, 10, 460, 455);
            }
            else {
                this.d().setLayout(new BorderLayout());
                this.d().add("Center", this.l = new s1(t, w, i, q, s2));
                this.d().add("South", this.n);
            }
            if (q == null) {
                this.pack();
            }
            this.addWindowListener(new tk());
            if (j) {
                this.b.addActionListener(this.aa);
                this.b.addKeyListener(this.ab);
                this.c.addActionListener(this.aa);
                this.c.addKeyListener(this.ab);
            }
            else {
                this.a.addActionListener(this.aa);
                if (this.d != null) {
                    this.d.addActionListener(this.aa);
                }
                this.a.addKeyListener(this.ab);
                if (this.d != null) {
                    this.d.addKeyListener(this.ab);
                }
            }
            this.g();
            if (q == null) {
                this.setResizable(true);
            }
            else {
                this.setResizable(false);
            }
            this.setBackground(ji.util.e.aq());
        }
        catch (Exception ex) {}
    }
    
    public final boolean a() {
        return this.x;
    }
    
    public void dispose() {
        try {
            this.f();
            this.hide();
            this.b();
        }
        catch (Exception ex) {}
    }
    
    public void requestFocus() {
        try {
            if (ji.util.e.at()) {
                if (this.u != null) {
                    ji.util.e.b(this.u);
                }
                else if (this.j) {
                    ji.util.e.b(this.b);
                }
                else {
                    ji.util.e.b(this.a);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public void b() {
        if (ji.util.i.c(124)) {
            ji.io.h.d(this.t, "Shutdown dialog: dialog resources relased ".concat(String.valueOf(String.valueOf(Thread.currentThread().getName()))));
        }
        try {
            this.z = true;
            this.w = null;
            this.v = null;
            if (this.b != null) {
                this.b.removeActionListener(this.aa);
                this.b.removeKeyListener(this.ab);
                this.b.a();
            }
            if (this.c != null) {
                this.c.removeActionListener(this.aa);
                this.c.removeKeyListener(this.ab);
                this.c.a();
            }
            if (this.d != null) {
                this.d.removeActionListener(this.aa);
                this.d.removeKeyListener(this.ab);
                this.d.a();
            }
            if (this.n != null) {
                this.n.releaseResources();
                this.n = null;
            }
            if (this.l != null) {
                this.l.removeKeyListener(this.ab);
                this.l.releaseResources();
                this.l = null;
            }
            if (this.m != null) {
                this.m.releaseResources();
                this.m = null;
            }
            this.u = null;
            this.n = null;
        }
        catch (Exception ex) {}
    }
    
    private void f() {
        try {
            if (this.x) {
                final Rectangle bounds = this.getBounds();
                if (bounds != null) {
                    bk.y = new Rectangle();
                    bk.y.x = bounds.x;
                    bk.y.y = bounds.y;
                    bk.y.width = bounds.width;
                    bk.y.height = bounds.height;
                }
            }
            this.x = false;
        }
        catch (Exception ex) {}
    }
    
    private void g() {
        try {
            this.pack();
            if (bk.y == null || !this.x) {
                Dimension size = new Dimension(200, 100);
                try {
                    size = this.getSize();
                }
                catch (Exception ex) {}
                this.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - size.width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - size.height / 2, size.width, size.height);
            }
            else {
                this.setBounds(bk.y.x, bk.y.y, bk.y.width, bk.y.height);
            }
        }
        catch (Exception ex2) {}
    }
    
    public Dimension getSize() {
        if (this.e()) {
            if (this.q != null) {
                return new Dimension(470, 470);
            }
            return super.getSize();
        }
        else {
            if (this.q != null) {
                return new Dimension(460, 455);
            }
            if (this.m != null) {
                this.m.getBounds();
                int n = 0;
                try {
                    n = this.m.c() * this.h();
                }
                catch (Exception ex) {}
                try {
                    if (n == 0) {
                        n = super.getSize().height;
                    }
                    else {
                        n += 3 * this.h();
                    }
                }
                catch (Exception ex2) {}
                if (n == 0) {
                    n = super.getSize().height;
                }
                return new Dimension(Math.min(this.m.b(), 500), Math.min(2 * Toolkit.getDefaultToolkit().getScreenSize().height / 3, n));
            }
            return super.getSize();
        }
    }
    
    private final int h() {
        return this.w.getGraphics().create().getFontMetrics().getHeight();
    }
    
    public void hide() {
        if (this.isVisible()) {
            if (ji.util.i.c(124)) {
                ji.io.h.d(this.t, "Shutdown dialog: request for hide() ".concat(String.valueOf(String.valueOf(Thread.currentThread().getName()))));
            }
            ji.util.e.a(new sx(this), this.t);
        }
        else if (ji.util.i.c(124)) {
            ji.io.h.d(this.t, "Shutdown dialog: ignoring request to hide, we're already hidden");
        }
    }
    
    public void show() {
        int n = 0;
        if (ji.util.e.av() && Thread.currentThread().getName().indexOf("jiApplet.class") > -1) {
            if (ji.util.i.c(124)) {
                ji.io.h.d(this.t, "Shutdown dialog: attempt to show dialog from applet thread - may cause lockup");
            }
            n = 120;
        }
        try {
            this.p = false;
            ji.util.e.a(this);
            if (ji.util.i.c(124)) {
                ji.io.h.d(this.t, "Shutdown dialog: creating dialog listener");
            }
            final tl tl = new tl((ad5)null);
            final Thread thread = new Thread(tl);
            if (ji.util.i.c(124)) {
                ji.io.h.d(this.t, "Shutdown dialog: starting listener thread");
            }
            thread.start();
            this.addWindowListener(tl);
            if (ji.util.i.c(124)) {
                ji.io.h.d(this.t, "Shutdown dialog: invoking runnable");
            }
            ji.util.e.a(tl.c(), this.t);
            if (this.isModal()) {
                if (ji.util.i.c(124)) {
                    ji.io.h.d(this.t, "Shutdown dialog: modal, waiting");
                }
                if (ji.util.i.c(124)) {
                    ji.io.h.d(this.t, "Shutdown dialog: about to wait for modal dialog to close in ".concat(String.valueOf(String.valueOf(Thread.currentThread().getName()))));
                }
                if (thread != null && thread.isAlive()) {
                    if (ji.util.d.ck(this.t)) {
                        if (ji.util.i.c(124)) {
                            ji.io.h.d(this.t, "Shutdown dialog: parent is not visible, setting timeout 30s");
                        }
                        n = 30;
                    }
                    final long currentTimeMillis = System.currentTimeMillis();
                    while (thread.isAlive() && (n <= 0 || System.currentTimeMillis() - currentTimeMillis <= n * 1000)) {
                        thread.join(1000L);
                        if (ji.util.d.ck(this.t)) {
                            this.toFront();
                            if (!ji.util.i.c(124)) {
                                continue;
                            }
                            ji.io.h.d(this.t, "Shutdown dialog: bringing to front");
                        }
                        else {
                            if (!ji.util.i.c(124)) {
                                continue;
                            }
                            ji.io.h.d(this.t, "Shutdown dialog: waiting for close");
                        }
                    }
                    if (this.isVisible() && !tl.a()) {
                        if (ji.util.i.c(124)) {
                            ji.io.h.d(this.t, "Shutdown dialog: Timed out: auto-closing");
                        }
                        this.setVisible(false);
                    }
                }
                if (ji.util.i.c(124)) {
                    ji.io.h.d(this.t, "Shutdown dialog: finished waiting");
                }
                this.removeWindowListener(tl);
                if (ji.util.i.c(124)) {
                    ji.io.h.d(this.t, "Shutdown dialog: removed window listener");
                }
            }
            else if (ji.util.i.c(124)) {
                ji.io.h.d(this.t, "Shutdown dialog: non modal, not waiting");
            }
            if (!tl.a()) {
                if (ji.util.i.c(124)) {
                    ji.io.h.d(this.t, "Shutdown dialog: close listener not finished, finishing");
                }
                synchronized (tl) {
                    tl.notify();
                }
            }
            if (ji.util.e.at() && this.isVisible()) {
                if (this.j) {
                    ji.util.e.b(this.b);
                }
                else {
                    ji.util.e.b(this.a);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
    }
    
    private void i() {
        try {
            if (this.o != null) {
                try {
                    this.o.a(this, null);
                }
                catch (Exception ex) {}
            }
            try {
                this.dispose();
            }
            catch (Exception ex2) {}
        }
        catch (Exception ex3) {}
    }
    
    private final void j() {
        try {
            bk.k = true;
            Object o = null;
            if (this.h != null) {
                o = this.h;
            }
            else if (this.q != null) {
                o = this.q;
            }
            else if (this.i != null) {
                o = this.i;
            }
            if (this.v != null) {
                this.v.a(new ob(this.v, 1, ji.util.d.b(o)));
            }
            if (this.d != null) {
                this.d.setEnabled(false);
            }
        }
        catch (Exception ex) {}
        finally {
            bk.k = false;
        }
    }
    
    public boolean c() {
        return this.p;
    }
    
    public void setBackground(final Color background) {
        try {
            if (background != null) {
                super.setBackground(background);
                if (this.n != null) {
                    this.n.setBackground(background);
                }
                if (this.a != null) {
                    this.a.setBackground(background);
                }
                if (this.d != null) {
                    this.d.setBackground(background);
                }
                if (this.b != null) {
                    this.b.setBackground(background);
                }
                if (this.c != null) {
                    this.c.setBackground(background);
                }
                if (this.l != null) {
                    this.l.setBackground(background);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    static {
        bk.k = false;
        bk.y = null;
    }
    
    private class tl extends WindowAdapter implements Runnable
    {
        boolean a;
        boolean b;
        
        private tl() {
            this.a = false;
            this.b = false;
        }
        
        public void windowClosed(final WindowEvent windowEvent) {
            synchronized (this) {
                this.a = true;
                this.notify();
            }
        }
        
        public void windowClosing(final WindowEvent windowEvent) {
            synchronized (this) {
                ji.util.d.b0(false);
                this.a = true;
                this.notify();
            }
        }
        
        public boolean a() {
            synchronized (this) {
                return this.a;
            }
        }
        
        public synchronized void b() {
            this.b = true;
        }
        
        public Runnable c() {
            return new tm(this);
        }
        
        public void run() {
            synchronized (this) {
                if (!this.a) {
                    try {
                        if (ji.util.i.c(124)) {
                            ji.io.h.d(bk.this.t, "Shutdown dialog: dialog opened, waiting for close");
                        }
                        this.wait();
                    }
                    catch (InterruptedException ex) {
                        if (ji.util.i.c(124)) {
                            ji.io.h.d(bk.this.t, "Shutdown dialog: interrupt caught (dialog finished)");
                        }
                    }
                }
                if (ji.util.i.c(124)) {
                    ji.io.h.d(bk.this.t, "Shutdown dialog: closed listener thread finished");
                }
            }
        }
    }
    
    class tj extends jiPanel
    {
        b5 a;
        
        public tj(final bk bk, final String s, final String s2) {
            super(s);
            this.a = null;
            try {
                this.setBorderStyle(0);
                this.setLayout(null);
                (this.a = new b5(s2)).setEditable(false);
                this.a.setRows(5);
                this.a.setBackground(Color.white);
                this.a.setBounds(5, 5, 440, 290);
                this.add(this.a);
                this.setBounds(5, 5, 450, 310);
            }
            catch (Exception ex) {}
        }
        
        public Insets getInsets() {
            return new Insets(5, 5, 5, 5);
        }
    }
    
    class s1 extends jiPanel
    {
        Label[] a;
        
        public s1(final bk bk, final String s, final Component component, String s2, final String s3, final String s4) {
            super(s);
            this.a = null;
            try {
                this.setBorderStyle(0);
                int countTokens = 0;
                int n = 0;
                this.setLayout(new FlowLayout());
                if (s2 != null) {
                    if ((s3 != null || s4 != null) && !this.isSwing()) {
                        this.setLayout(null);
                    }
                    final StringTokenizer stringTokenizer = new StringTokenizer(s2, "\n");
                    String s5 = "";
                    int n2 = 300;
                    try {
                        final int n3 = Toolkit.getDefaultToolkit().getScreenSize().width / 7;
                        if (n3 < n2) {
                            n2 = n3;
                        }
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    boolean b = false;
                    while (stringTokenizer.hasMoreTokens()) {
                        final String nextToken = stringTokenizer.nextToken();
                        if (!ji.util.d.by(s5)) {
                            s5 = String.valueOf(String.valueOf(s5)).concat("\n");
                        }
                        if (s5.length() > n2) {
                            s5 = this.a(s5, n2);
                            b = true;
                        }
                        s5 = String.valueOf(String.valueOf(s5)).concat(String.valueOf(String.valueOf(nextToken)));
                    }
                    if (b) {
                        s2 = s5;
                    }
                    final StringTokenizer stringTokenizer2 = new StringTokenizer(s2, "\n");
                    countTokens = stringTokenizer2.countTokens();
                    if (countTokens > 0) {
                        this.a = new Label[countTokens];
                    }
                    while (stringTokenizer2.hasMoreTokens()) {
                        this.a[n] = new Label(stringTokenizer2.nextToken(), 1);
                        if (s3 != null) {
                            this.a[n].setBounds(5, 10 + n * 25, 440, 25);
                        }
                        this.add(this.a[n++]);
                    }
                }
                if (countTokens == 0) {
                    countTokens = 1;
                }
                if (s3 == null) {
                    this.setLayout(new GridLayout(countTokens, 1));
                    bk.pack();
                }
                else if (s4 != null) {
                    this.setBounds(5, 5, 450, n * 26);
                }
                else {
                    this.setBounds(5, 5, 450, n * 26);
                }
            }
            catch (Exception ex2) {}
        }
        
        private final String a(final String s, final int n) {
            return s;
        }
        
        public Insets getInsets() {
            return new Insets(5, 5, 5, 5);
        }
        
        public void setBackground(final Color color) {
            try {
                super.setBackground(color);
                if (this.a != null) {
                    for (int i = 0; i < this.a.length; ++i) {
                        if (this.a[i] != null) {
                            this.a[i].setBackground(color);
                        }
                    }
                }
            }
            catch (Exception ex) {}
        }
        
        public final void releaseResources() {
        }
    }
    
    class tk extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            if (ji.util.i.c(124)) {
                ji.io.h.d(bk.this.t, "Shutdown dialog: windowClosing() called in adaptor");
            }
            try {
                ji.util.d.b0(false);
                bk.this.i();
                ji.util.d.ew();
            }
            catch (Exception ex) {}
        }
    }
    
    class te extends KeyAdapter
    {
        public void keyPressed(final KeyEvent keyEvent) {
            try {
                if (keyEvent.getKeyCode() == 10) {
                    if (bk.this.j) {
                        if (ji.util.d.bc(((bn)keyEvent.getSource()).getLabel()).toLowerCase().toLowerCase().indexOf(ji.util.d.bc(ji.util.d.bc(bk.this.e.toLowerCase()).toLowerCase())) >= 0) {
                            bk.this.u = bk.this.b;
                            bk.this.p = true;
                        }
                        else {
                            bk.this.u = bk.this.c;
                            bk.this.p = false;
                        }
                        bk.this.i();
                    }
                    else {
                        bk.this.p = false;
                        if (ji.util.d.bc(((bn)keyEvent.getSource()).getLabel()).toLowerCase().toLowerCase().indexOf(ji.util.d.bc(bk.this.g.toLowerCase())) >= 0) {
                            bk.this.u = bk.this.d;
                            bk.this.j();
                        }
                        else {
                            bk.this.u = bk.this.a;
                            bk.this.i();
                        }
                    }
                }
                else if (keyEvent.getKeyCode() == 27) {
                    bk.this.p = false;
                    ji.util.d.b0(false);
                    bk.this.i();
                }
            }
            catch (Exception ex) {}
        }
    }
    
    class td implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            try {
                bk.this.u = (bn)actionEvent.getSource();
            }
            catch (Exception ex) {}
            ji.util.d.b0(false);
            if (bk.this.j) {
                final String bc = ji.util.d.bc(((bn)actionEvent.getSource()).getLabel());
                if (ji.util.d.by(ji.util.d.bc(bk.this.g))) {
                    bk.this.g = "Copy";
                }
                if (bk.this.e == null) {
                    bk.this.e = "";
                }
                if (bc.toLowerCase().indexOf(ji.util.d.bc(bk.this.e).toLowerCase()) >= 0) {
                    bk.this.u = bk.this.b;
                    bk.this.p = true;
                }
                else {
                    bk.this.u = bk.this.c;
                    bk.this.p = false;
                }
                bk.this.i();
            }
            else {
                bk.this.p = false;
                final String bc2 = ji.util.d.bc(((bn)actionEvent.getSource()).getLabel());
                if (ji.util.d.by(ji.util.d.bc(bk.this.g))) {
                    bk.this.g = "Copy";
                }
                if (bk.this.g == null) {
                    bk.this.g = "";
                }
                if (bc2.toLowerCase().indexOf(ji.util.d.bc(bk.this.g).toLowerCase()) >= 0) {
                    bk.this.u = bk.this.d;
                    bk.this.j();
                }
                else {
                    bk.this.u = bk.this.a;
                    bk.this.i();
                }
            }
        }
    }
    
    interface ad5
    {
    }
}
