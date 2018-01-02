// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.SystemColor;
import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import ji.util.e;
import ji.util.d;
import java.awt.Dialog;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import ji.awt.bb;
import java.awt.Panel;
import java.awt.Color;
import ji.v1base.bn;
import ji.v1base.bl;

public class bt extends bl
{
    private static int a;
    private bn b;
    private bn c;
    private boolean d;
    private boolean e;
    private Color f;
    private Panel g;
    private bj h;
    private bb i;
    private String j;
    private Checkbox k;
    private Checkbox l;
    private Checkbox m;
    private CheckboxGroup n;
    private int o;
    private boolean[] p;
    private TextField q;
    private TextField r;
    private Label s;
    private String t;
    
    public bt(final Frame frame, final bj bj, final String s, final int n, final boolean[] array, final String s2) {
        super(frame, s, true);
        this.e = true;
        this.f = null;
        this.g = new Panel();
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = 1;
        this.p = null;
        this.t = null;
        this.a(frame, bj, s, n, array, s2);
    }
    
    public bt(final Dialog dialog, final bj bj, final String s, final int n, final boolean[] array, final String s2) {
        super(dialog, s, true);
        this.e = true;
        this.f = null;
        this.g = new Panel();
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = 1;
        this.p = null;
        this.t = null;
        this.a(dialog, bj, s, n, array, s2);
    }
    
    public void a(final Component component, final bj h, final String s, final int o, final boolean[] array, final String t) {
        try {
            this.t = t;
            this.o = o;
            ji.util.d.b7(false);
            ji.util.e.a(component);
            ji.util.e.a(this);
            this.h = h;
            this.d().setLayout(null);
            this.g.setLayout(null);
            (this.b = new bn(t, ji.util.d.b(232, t))).addActionListener(new abj());
            this.g.add(this.b);
            (this.c = new bn(t, ji.util.d.b(235, t))).addActionListener(new abk());
            this.g.add(this.c);
            this.n = new CheckboxGroup();
            (this.k = new Checkbox(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(541, t)))).append(" (").append(o).append(")"))), this.n, true)).addItemListener(new abl());
            this.g.add(this.k);
            boolean b = false;
            String s2 = "";
            if (array != null) {
                this.p = new boolean[array.length];
                for (int i = 0; i < array.length; ++i) {
                    this.p[i] = array[i];
                    if (array[i]) {
                        b = true;
                        if (!ji.util.d.by(s2)) {
                            s2 = String.valueOf(String.valueOf(s2)).concat(",");
                        }
                        s2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append(i + 1)));
                    }
                }
            }
            if (b) {
                (this.l = new Checkbox(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(542, t)))).append(" (").append(s2).append(")"))), this.n, false)).setEnabled(true);
                this.l.addItemListener(new abl());
            }
            else {
                (this.l = new Checkbox(ji.util.d.b(542, t), this.n, false)).setEnabled(false);
            }
            this.g.add(this.l);
            (this.m = new Checkbox(ji.util.d.b(543, t), this.n, false)).addItemListener(new abl());
            this.q = new TextField("1");
            this.r = new TextField("".concat(String.valueOf(String.valueOf(o))));
            this.s = new Label(ji.util.d.b(546, t));
            this.g.add(this.q);
            this.g.add(this.r);
            this.g.add(this.s);
            this.q.setEnabled(false);
            this.r.setEnabled(false);
            this.s.setEnabled(false);
            this.q.repaint();
            this.r.repaint();
            ji.util.e.a(this.q);
            ji.util.e.a(this.r);
            ji.util.e.a(this.s);
            ji.util.d.ew();
            ji.util.e.a(this);
            ji.util.e.a(this.g);
            ji.util.e.a(this.k);
            ji.util.e.a(this.l);
            ji.util.e.a(this.m);
            this.g.add(this.m);
            this.d().add(this.g);
            this.addWindowListener(new abm());
            this.f();
            this.setResizable(false);
            ji.util.d.ew();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private final void c() {
        try {
            if (this.k.getState()) {
                this.q.setEnabled(false);
                this.r.setEnabled(false);
                this.s.setEnabled(false);
                this.j = "";
                for (int i = 0; i < this.o; ++i) {
                    if (!ji.util.d.by(this.j)) {
                        this.j = String.valueOf(String.valueOf(this.j)).concat(",");
                    }
                    this.j = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.j))).append(i + 1)));
                }
            }
            else if (this.l.getState()) {
                this.j = "";
                for (int j = 0; j < this.p.length; ++j) {
                    if (this.p[j]) {
                        if (!ji.util.d.by(this.j)) {
                            this.j = String.valueOf(String.valueOf(this.j)).concat(",");
                        }
                        this.j = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.j))).append(j + 1)));
                    }
                }
                this.q.setEnabled(false);
                this.r.setEnabled(false);
                this.s.setEnabled(false);
            }
            else if (this.m.getState()) {
                this.q.setEnabled(true);
                this.r.setEnabled(true);
                this.s.setEnabled(true);
                final int max = Math.max(ji.util.d.c(this.q.getText(), 1), 1);
                final int min = Math.min(ji.util.d.c(this.r.getText(), this.o), this.o);
                this.j = "";
                for (int k = max; k <= min; ++k) {
                    if (!ji.util.d.by(this.j)) {
                        this.j = String.valueOf(String.valueOf(this.j)).concat(",");
                    }
                    this.j = String.valueOf(String.valueOf(this.j)).concat(String.valueOf(String.valueOf(k)));
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void a() {
        this.d = true;
        this.g();
    }
    
    public void dispose() {
        this.hide();
    }
    
    public Dimension getSize() {
        if (ji.util.d.av(this.t)) {
            return new Dimension(350, 260);
        }
        return new Dimension(350, 240);
    }
    
    private void f() {
        final Dimension size;
        final Dimension dimension = size = this.getSize();
        size.height += 5;
        int n = 0;
        if (this.e()) {
            n = 30;
        }
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setBounds((screenSize.width - dimension.width) / 2, (screenSize.height - dimension.height) / 2, dimension.width, dimension.height);
        this.g.setBounds(0, 0, dimension.width, dimension.height);
        final Dimension dimension2 = new Dimension(80, 25);
        final int n2 = 20;
        final int n3 = (dimension.width - 2 * dimension2.width - n2) / 2;
        int n4 = dimension.height - dimension2.height - 20;
        if (ji.util.d.av(this.t)) {
            n4 -= 20;
        }
        this.b.setBounds(n3, n4 - n, dimension2.width, dimension2.height);
        this.c.setBounds(n3 + dimension2.width + n2, n4 - n, dimension2.width, dimension2.height);
        final int n5 = 50;
        final int n6 = n3 / 2;
        final int n7 = 45;
        final int n8 = dimension.width - 20;
        final int n9 = 20;
        this.k.setBounds(n6, n5 - n, n8, n9);
        final int n10 = n5 + n7;
        this.l.setBounds(n6, n10 - n, n8, n9);
        final int n11 = n10 + n7;
        this.m.setBounds(n6, n11 - n, n8, n9);
        final int n12 = 30;
        final int n13 = dimension.width - 80 - 2 * n12;
        this.q.setBounds(n13, n11 - n, n12, n9);
        this.s.setBounds(n13 + n12 + 10, n11 - n, n12, n9);
        this.r.setBounds(n13 + n12 + 40, n11 - n, n12, n9);
    }
    
    public void show() {
        if (this.i == null) {
            (this.i = new bb(this.t, new zx())).setPriority(1);
            this.i.start();
        }
        super.show();
    }
    
    public boolean b() {
        return this.d;
    }
    
    public void setBackground(final Color color) {
        if (!(color instanceof SystemColor)) {
            try {
                super.setBackground(this.f = color);
                this.g.setBackground(color);
                this.b.setBackground(color);
                this.c.setBackground(color);
                this.k.setBackground(color);
                this.l.setBackground(color);
                this.m.setBackground(color);
                if (this.s != null) {
                    this.s.setBackground(color);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void g() {
        this.e = false;
        ji.util.d.b7(true);
        if (this.h != null) {
            try {
                this.h.a(this, this.j);
            }
            catch (Exception ex) {}
        }
        this.dispose();
    }
    
    static {
        bt.a = 18;
    }
    
    class abm extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            bt.this.d = true;
            bt.this.g();
            ji.util.d.ew();
        }
    }
    
    class abk implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            bt.this.j = null;
            bt.this.d = true;
            bt.this.g();
        }
    }
    
    class abl implements ItemListener
    {
        public void itemStateChanged(final ItemEvent itemEvent) {
            bt.this.c();
        }
    }
    
    class abj implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            bt.this.c();
            bt.this.d = false;
            bt.this.g();
        }
    }
    
    class zx implements Runnable
    {
        public final void run() {
            try {
                final int n = 2000;
                int n2 = 0;
                while (bt.this.e && n2 < n) {
                    try {
                        for (int n3 = 0; n3 < 20 && bt.this.e; ++n3) {
                            ji.util.d.b(10, 108, bt.this.t);
                            n2 += 10;
                        }
                        if (!bt.this.e || !ji.util.d.dh()) {
                            continue;
                        }
                        bt.this.toFront();
                    }
                    catch (Exception ex) {}
                }
            }
            catch (Exception ex2) {}
            finally {
                bt.this.i = null;
            }
        }
    }
}
