// 
// Decompiled by Procyon v0.5.30
// 

package b.a.e;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;
import java.awt.Container;
import java.awt.event.WindowListener;
import b.a.d.b;
import b.a.d.a;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JRootPane;
import javax.swing.Action;
import javax.swing.KeyStroke;
import java.awt.Frame;
import java.awt.Dialog;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public abstract class k extends JDialog implements ActionListener
{
    protected int a;
    protected int b;
    protected int c;
    protected boolean d;
    protected boolean e;
    protected JPanel f;
    protected JPanel g;
    protected int h;
    protected JButton i;
    protected JButton j;
    protected JButton k;
    protected JButton l;
    protected m m;
    protected boolean n;
    protected boolean o;
    protected boolean p;
    
    public k(final Dialog dialog, final String s) {
        super(dialog, s, true);
        this.a = 360;
        this.b = 240;
        this.c = 2;
        this.d = false;
        this.e = false;
        this.h = 32773;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.n = false;
        this.o = false;
        this.p = true;
        this.a(32773);
    }
    
    public k(final Frame frame, final String s) {
        super(frame, s, true);
        this.a = 360;
        this.b = 240;
        this.c = 2;
        this.d = false;
        this.e = false;
        this.h = 32773;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.n = false;
        this.o = false;
        this.p = true;
        this.a(32773);
    }
    
    protected void a(final int h) {
        this.h = h;
        this.setDefaultCloseOperation(0);
        final JRootPane rootPane = this.getRootPane();
        final InputMap inputMap = rootPane.getInputMap(1);
        final ActionMap actionMap = rootPane.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke(27, 0), "escape.dialog");
        actionMap.put("escape.dialog", new h(this));
    }
    
    protected void a() {
        final GridLayout gridLayout = new GridLayout();
        final Container contentPane = this.getContentPane();
        final JPanel panel = new JPanel(new BorderLayout());
        contentPane.add(panel);
        this.f = new JPanel(new f(3, 8, 10, 8));
        gridLayout.setHgap(5);
        this.g = new JPanel(gridLayout);
        panel.add("South", this.f);
        (this.m = new m()).a(8, 8, 4, 8);
        panel.add("Center", this.m);
        final ResourceBundle a = b.a.d.a.a();
        final JButton[] array = new JButton[3];
        int n = 0;
        if ((this.h & 0x1) != 0x0) {
            this.a(this.i = new JButton(a.getString("org.shetline.util.ok")));
            array[n++] = this.i;
            this.i.addActionListener(this);
            this.i.setEnabled(true);
        }
        if ((this.h & 0x2) != 0x0) {
            this.j = new JButton(a.getString("org.shetline.util.apply"));
            array[n++] = this.j;
            this.j.addActionListener(this);
            this.j.setEnabled(false);
        }
        if ((this.h & 0x4) != 0x0) {
            this.k = new JButton(a.getString("org.shetline.util.cancel"));
            array[n++] = this.k;
            this.k.addActionListener(this);
            this.k.setEnabled(false);
        }
        if (b.a.d.b.a()) {
            this.f.setLayout(new f(2, 8, 8, 16));
            for (int i = n - 1; i >= 0; --i) {
                this.g.add(array[i]);
            }
        }
        else {
            this.f.setLayout(new f(3, 8, 8, 8));
            for (int j = 0; j < n; ++j) {
                this.g.add(array[j]);
            }
        }
        this.f.add(this.g);
        this.c();
        this.d();
        this.addWindowListener(new c(this));
        this.e = true;
    }
    
    public void dispose() {
        this.n = true;
        super.dispose();
    }
    
    public int b() {
        return this.c;
    }
    
    public void setVisible(final boolean visible) {
        if (visible) {
            if (!this.e) {
                this.a();
            }
            if (!this.isVisible()) {
                this.pack();
            }
        }
        super.setVisible(visible);
    }
    
    public void pack() {
        if (!this.e) {
            this.a();
        }
        super.pack();
        final int width = this.getWidth();
        final int height = this.getHeight();
        if (width < this.a || height < this.b) {
            this.setSize(Math.max(width, this.a), Math.max(height, this.b));
        }
    }
    
    protected void c() {
    }
    
    protected void d() {
    }
    
    protected void a(final JButton defaultButton) {
        this.getRootPane().setDefaultButton(defaultButton);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source != null) {
            if (source == this.i) {
                this.e();
            }
            else if (source == this.j) {
                this.f();
            }
            else if (source == this.k) {
                this.g();
            }
        }
    }
    
    public void e() {
        if (this.k()) {
            if (this.i()) {
                this.j();
                this.d = true;
                this.c = 1;
                this.dispose();
            }
        }
        else {
            this.c = (this.d ? 1 : 0);
            this.dispose();
        }
    }
    
    public void f() {
        if (this.k() && this.i()) {
            this.j();
            this.d = true;
        }
    }
    
    public void g() {
        this.c = 2;
        this.dispose();
    }
    
    protected void h() {
        if ((this.h & 0x8000) != 0x0) {
            this.g();
        }
    }
    
    protected boolean i() {
        return true;
    }
    
    protected void j() {
        this.a(false);
    }
    
    protected boolean k() {
        return this.o;
    }
    
    protected void a(final boolean o) {
        if (this.o != o) {
            this.o = o;
            if (this.j != null) {
                this.j.setEnabled(this.o);
            }
            if (this.k != null) {
                this.k.setEnabled(this.o);
            }
        }
    }
    
    protected void l() {
    }
}
