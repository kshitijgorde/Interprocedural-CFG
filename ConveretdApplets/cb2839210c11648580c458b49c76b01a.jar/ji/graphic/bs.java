// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.ItemEvent;
import ji.awt.b4;
import ji.awt.bb;
import ji.util.d;
import java.awt.event.KeyListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.Component;
import ji.awt.c;
import java.awt.ScrollPane;
import java.awt.List;
import java.awt.ItemSelectable;
import ji.v1base.jiPanel;

public class bs extends jiPanel implements ItemSelectable
{
    private String a;
    private String b;
    private int c;
    private int d;
    private List[] e;
    private ScrollPane f;
    private int[] g;
    private double[] h;
    private int i;
    private int j;
    private int k;
    private jiPanel l;
    private tf m;
    private tg n;
    private th o;
    private boolean p;
    private c q;
    private c r;
    private int s;
    private final int t = 15;
    private int u;
    private String[] v;
    private String w;
    
    public bs(final Component component, final String s, final String s2, final int c, final int d, final String w) {
        super(w);
        this.a = null;
        this.b = null;
        this.c = 100;
        this.d = 100;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = false;
        this.q = null;
        this.r = null;
        this.s = -1;
        this.u = 1;
        this.v = null;
        this.w = null;
        try {
            this.w = w;
            this.setBorderStyle(0);
            this.setLayout(null);
            this.c = c;
            this.d = d;
            this.j = this.a(component);
            this.a(component, s, s2);
            this.e = new List[this.u];
            this.i = 0;
            this.f = new ScrollPane(0);
            (this.l = new jiPanel(w)).setBorderStyle(0);
            this.l.setLayout(null);
            this.m = new tf();
            this.n = new tg();
            this.o = new th();
            for (int i = 0; i < this.g.length; ++i) {
                this.i += this.g[i];
                (this.e[i] = new List()).addActionListener(this.m);
                this.e[i].addItemListener(this.n);
                this.e[i].addKeyListener(this.o);
                this.l.add(this.e[i]);
            }
            if (ji.util.d.em()) {
                new bb(w, new ti()).start();
            }
            else {
                this.e();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private final void e() {
        this.f.add(this.l);
        this.add(this.f);
        this.a();
        this.validate();
    }
    
    public void a() {
        try {
            for (int i = 0; i < this.e.length; ++i) {
                this.e[i].removeAll();
            }
            for (int j = 0; j < this.v.length; ++j) {
                final b4 b4 = new b4(this.v[j], this.b);
                if (b4.a() == 0) {
                    this.e[0].add(this.v[j]);
                    for (int k = 1; k < this.g.length; ++k) {
                        this.e[k].add(" ");
                    }
                }
                else {
                    final int a = b4.a();
                    int n = 0;
                    while (b4.b()) {
                        this.e[n].add(ji.util.d.b((Object)ji.util.d.b(b4.c(), "<EQ>", "=")));
                        ++n;
                    }
                    for (int l = a; l < this.g.length; ++l) {
                        this.e[l].add(" ");
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public void a(final Component component, final String a, final String b) {
        try {
            if (a != null) {
                this.a = a;
            }
            else {
                this.a = "";
            }
            if (b != null) {
                this.b = b;
            }
            else {
                this.b = "\n";
            }
            this.a = ji.util.d.b(this.a, "\r\n", "\n");
            this.a = ji.util.d.b(this.a, "\r", "\n");
            final b4 b2 = new b4(this.a, "\n");
            this.v = new String[b2.a()];
            int n = 0;
            while (b2.b()) {
                this.v[n++] = b2.c();
            }
            this.k = this.j * this.v.length;
            this.u = 1;
            for (int i = 0; i < this.v.length; ++i) {
                final b4 b3 = new b4(this.v[i], this.b);
                this.u = Math.max(this.u, b3.a());
                b3.d();
            }
            this.g = new int[this.u];
            for (int j = 0; j < this.v.length; ++j) {
                final b4 b4 = new b4(this.v[j], this.b);
                if (b4.a() == 0) {
                    this.g[0] = Math.max(this.g[0], 15 + this.a(component, this.v[j]));
                }
                else {
                    int n2 = 0;
                    while (b4.b()) {
                        this.g[n2] = Math.max(this.g[n2], 15 + this.a(component, b4.c()));
                        ++n2;
                    }
                }
            }
            final int[] g = this.g;
            final int n3 = this.g.length - 1;
            g[n3] += this.a(component, "W");
            this.h = new double[this.g.length];
            this.i = 0;
            for (int k = 0; k < this.g.length; ++k) {
                this.i += this.g[k];
            }
            this.i = Math.max(this.i, 1);
            for (int l = 0; l < this.g.length; ++l) {
                this.h[l] = this.g[l] / this.i;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public int b() {
        return this.i;
    }
    
    public int c() {
        try {
            return this.v.length;
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    public Object[] getSelectedObjects() {
        return null;
    }
    
    public void addItemListener(final ItemListener itemListener) {
        if (this.q == null) {
            this.q = new c("jiTable1", 2);
        }
        if (!this.q.a(itemListener)) {
            this.q.c(itemListener);
        }
    }
    
    public void removeItemListener(final ItemListener itemListener) {
        if (this.q != null && this.q.a(itemListener)) {
            this.q.b(itemListener);
        }
    }
    
    public void a(final ItemEvent itemEvent) {
        if (this.q != null) {
            final ItemEvent itemEvent2 = new ItemEvent(this, itemEvent.getID(), itemEvent.getItem(), itemEvent.getStateChange());
            for (int i = 0; i < this.q.b(); ++i) {
                ((ItemListener)this.q.b(i)).itemStateChanged(itemEvent2);
            }
        }
    }
    
    public void addKeyListener(final KeyListener keyListener) {
        if (this.r == null) {
            this.r = new c("jiTable1", 2);
        }
        if (!this.r.a(keyListener)) {
            this.r.c(keyListener);
        }
    }
    
    public void removeKeyListener(final KeyListener keyListener) {
        if (this.r != null && this.r.a(keyListener)) {
            this.r.b(keyListener);
        }
    }
    
    public void a(final KeyEvent keyEvent) {
        try {
            if (this.r != null) {
                final KeyEvent keyEvent2 = new KeyEvent(this, keyEvent.getID(), keyEvent.getWhen(), keyEvent.getModifiers(), keyEvent.getKeyCode(), keyEvent.getKeyChar());
                for (int i = 0; i < this.r.b(); ++i) {
                    switch (keyEvent2.getID()) {
                        case 401: {
                            ((KeyListener)this.r.b(i)).keyPressed(keyEvent2);
                            break;
                        }
                        case 402: {
                            ((KeyListener)this.r.b(i)).keyReleased(keyEvent2);
                            break;
                        }
                        case 400: {
                            ((KeyListener)this.r.b(i)).keyTyped(keyEvent2);
                            break;
                        }
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private final int a(final Component component, final String s) {
        return component.getGraphics().create().getFontMetrics().stringWidth(s);
    }
    
    private final int a(final Component component) {
        return component.getGraphics().create().getFontMetrics().getHeight();
    }
    
    public final void setBounds(final int n, final int n2, final int n3, final int n4) {
        super.setBounds(n, n2, n3, n4);
        this.validate();
    }
    
    public final void setBounds(final Rectangle bounds) {
        super.setBounds(bounds);
        this.validate();
    }
    
    public final void validate() {
        try {
            final int n = 5;
            final Dimension size = this.getSize();
            this.f.setBounds(0, 0, size.width, size.height);
            int max = Math.max(size.height, this.k + 15);
            int n2 = Math.max(size.width - 4, this.i);
            final int n3 = max - 5;
            if (this.v.length > n) {
                max += 15;
            }
            if (max >= size.height) {
                n2 = Math.max(size.width - 15 - 5, this.i);
            }
            if (this.v.length <= n) {
                this.l.setSize(n2 + 15, n3);
            }
            else {
                this.l.setSize(n2, n3);
            }
            if (this.e != null) {
                int n4 = 0;
                for (int i = 0; i < this.g.length; ++i) {
                    if (i == this.g.length - 1) {
                        if (this.i < n2) {
                            if (this.v.length <= n) {
                                this.e[i].setBounds(n4, 0, n2 - n4 + 15, max + 15);
                            }
                            else {
                                this.e[i].setBounds(n4, 0, n2 - n4, max + 15);
                            }
                        }
                        else {
                            this.e[i].setBounds(n4, 0, this.g[i] + 15, max + 15);
                        }
                    }
                    else {
                        this.e[i].setBounds(n4, 0, this.g[i] + 15, max + 15);
                    }
                    n4 += this.g[i] + 15;
                }
            }
            this.f.doLayout();
        }
        catch (Exception ex) {}
    }
    
    public int d() {
        return this.s;
    }
    
    public Insets getInsets() {
        return new Insets(0, 0, 0, 0);
    }
    
    public final void releaseResources() {
        try {
            for (int i = 0; i < this.g.length; ++i) {
                this.remove(this.e[i]);
                this.e[i].removeActionListener(this.m);
                this.e[i].removeItemListener(this.n);
                this.e[i].removeAll();
                this.l.remove(this.e[i]);
                this.e[i] = null;
            }
            this.m = null;
            this.n = null;
            this.o = null;
            this.l.remove(this.f);
            this.remove(this.l);
        }
        catch (Exception ex) {}
        if (this.q != null) {
            this.q.c();
            this.q = null;
        }
        if (this.r != null) {
            this.r.c();
            this.r = null;
        }
        super.releaseResources();
    }
    
    public Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(this.c, this.d);
    }
    
    class th implements KeyListener
    {
        public void keyPressed(final KeyEvent keyEvent) {
            bs.this.a(keyEvent);
        }
        
        public void keyReleased(final KeyEvent keyEvent) {
            bs.this.a(keyEvent);
        }
        
        public void keyTyped(final KeyEvent keyEvent) {
            bs.this.a(keyEvent);
        }
    }
    
    class tg implements ItemListener
    {
        public void itemStateChanged(final ItemEvent itemEvent) {
            if (!bs.this.p) {
                try {
                    bs.this.p = true;
                    bs.this.s = ((List)itemEvent.getSource()).getSelectedIndex();
                    for (int i = 0; i < bs.this.g.length; ++i) {
                        bs.this.e[i].select(bs.this.s);
                    }
                    bs.this.a(itemEvent);
                }
                catch (Exception ex) {}
                finally {
                    bs.this.p = false;
                }
            }
        }
    }
    
    class tf implements ActionListener
    {
        tf(final bs bs) {
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
        }
    }
    
    class ti implements Runnable
    {
        public void run() {
            try {
                ji.util.d.b(100, 125, bs.this.w);
                bs.this.e();
            }
            catch (Exception ex) {}
        }
    }
}
