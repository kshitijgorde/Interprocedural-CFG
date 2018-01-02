import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Label;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.List;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class u extends Panel implements WindowListener, ActionListener
{
    static int a;
    Frame b;
    bt c;
    List d;
    int e;
    int f;
    bf g;
    private String[] h;
    Font i;
    FontMetrics j;
    int[] k;
    int l;
    int m;
    boolean n;
    Label o;
    a p;
    
    void a(final bf g) {
        this.g = g;
    }
    
    void a() {
        this.b();
        if (this.b != null) {
            this.b.dispose();
            return;
        }
        irc.cy.e(bm.cW);
    }
    
    int a(final String s) {
        final int index = s.indexOf(32);
        int int1 = 0;
    Label_0053:
        while (true) {
            final int i = s.indexOf(58);
            while (i >= index) {
                try {
                    int1 = Integer.parseInt(s.substring(index + 1, i - 1), 10);
                    if (bm.dX) {
                        continue;
                    }
                }
                catch (NumberFormatException ex) {
                    int1 = 0;
                }
                break Label_0053;
            }
        }
        return int1;
    }
    
    u() {
        this.b = null;
        this.d = new List(20, false);
        this.h = Toolkit.getDefaultToolkit().getFontList();
        this.k = new int[] { 10, 12, 14, 16, 18, 20 };
        this.n = false;
        this.o = new Label();
        this.d.setBackground(bn.y);
        this.d.setForeground(bn.z);
        this.o.setBackground(bn.A);
        this.o.setForeground(bn.B);
        this.i = new Font(this.h[irc.cd], irc.ce, this.k[irc.cc]);
        this.j = this.getFontMetrics(this.i);
        this.l = this.j.charWidth(' ');
        this.m = this.j.charWidth('W');
        if (this.l == 0) {
            this.l = 4;
            this.m = 15;
            this.n = true;
        }
        if (!this.n) {
            this.d.setFont(this.i);
        }
        this.b();
        this.d.addActionListener(this);
        if (irc.e) {
            this.setLayout(new BorderLayout());
            this.add(c("3>\u00188\u0017\u0002"), this.d);
            this.a(this.d);
            return;
        }
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(gridBagLayout);
        final Panel panel = new Panel();
        this.p = null;
        panel.setLayout(gridBagLayout);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 99.0;
        gridBagLayout.setConstraints(this.o, gridBagConstraints);
        panel.add(this.o);
        (this.p = new a(bm.da, null, null)).setBackground(bn.C);
        this.p.setForeground(bn.D);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(this.p, gridBagConstraints);
        panel.add(this.p);
        this.o.setText(bm.cX);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 99.0;
        gridBagLayout.setConstraints(this.d, gridBagConstraints);
        this.add(this.d);
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.a();
    }
    
    private String a(String s, int n) {
        final boolean dx = bm.dX;
        int stringWidth = 0;
        Label_0036: {
            if (!this.n) {
                stringWidth = this.j.stringWidth(s);
                if (!dx) {
                    break Label_0036;
                }
            }
            stringWidth = s.length() * this.m;
        }
        n *= this.m;
        if (stringWidth >= n) {
            return s + " ";
        }
        String s2 = null;
        while (true) {
            if (stringWidth >= n) {
                s2 = s;
                if (!dx) {
                    break;
                }
            }
            else {
                new StringBuffer().append(s).append(" ").toString();
            }
            s = s2;
            stringWidth += this.l;
        }
        return s2;
    }
    
    void b(final String s) {
        final int a = this.a(s);
        if (this.e < u.a) {
            this.b(s, a);
            if (a < this.f) {
                this.f = a;
            }
            ++this.e;
            return;
        }
        if (a > this.f) {
            this.f = this.c();
            this.b(s, a);
        }
    }
    
    void b() {
        this.d.clear();
        if (this.c == null) {
            return;
        }
        bt bt = this.c;
    Block_3:
        while (true) {
            final bt bt2 = bt;
            bt = bt.c;
            bt2.c = null;
            while (bt == null) {
                this.c = null;
                this.e = 0;
                this.f = 3000;
                if (!bm.dX) {
                    break Block_3;
                }
            }
        }
    }
    
    int c() {
        final boolean dx = bm.dX;
        bt c = this.c;
        bt c2 = this.c;
        while (true) {
            while (true) {
                Label_0025: {
                    if (!dx) {
                        break Label_0025;
                    }
                    c = c2;
                    final bt c3 = c2.c;
                    final bt bt;
                    c2 = bt;
                }
                if (c2.c != null) {
                    continue;
                }
                break;
            }
            c.c = null;
            final bt bt = c;
            if (!dx) {
                return bt.b;
            }
            continue;
        }
    }
    
    void b(final String s, final int b) {
        final boolean dx = bm.dX;
        final bt c = new bt();
        c.a = d.a(s);
        c.b = b;
        c.c = null;
        if (this.c == null) {
            this.c = c;
            return;
        }
        bt c2 = this.c;
        bt bt = this.c;
        bt bt2;
        while (true) {
            Label_0072: {
                if (!dx) {
                    break Label_0072;
                }
                c2 = bt;
                bt = bt.c;
            }
            if (bt.c == null) {
                goto Label_0094;
            }
            bt2 = bt;
            if (!dx && bt2.b > b) {
                continue;
            }
            break;
        }
        if (bt2 == bt) {
            c.c = this.c;
            this.c = c;
            return;
        }
        c.c = c2.c;
        c2.c = c;
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String selectedItem = ((List)actionEvent.getSource()).getSelectedItem();
        final int index = selectedItem.indexOf(32);
        if (index > 0 && !selectedItem.equals(bm.cZ)) {
            this.g.a(c(":\u0014?\u0002R") + selectedItem.substring(0, index) + "\n");
        }
    }
    
    static {
        u.a = 100;
    }
    
    void a(final List list) {
        (this.b = new Frame()).addWindowListener(this);
        this.b.setTitle(bm.cX);
        this.b.setSize(500, 300);
        this.b.add(c("3>\u00188\u0017\u0002"), list);
    }
    
    void d() {
        if (irc.e) {
            this.b.setVisible(true);
            return;
        }
        this.p.a(this);
        irc.cy.a(bm.cW, this, null);
        this.validate();
        irc.cy.b(bm.cW);
    }
    
    void e() {
        final boolean dx = bm.dX;
        Label_0165: {
            if (irc.e) {
                if (this.e >= u.a) {
                    this.b.setTitle(u.a + bm.cY);
                    if (!dx) {
                        break Label_0165;
                    }
                }
                this.b.setTitle(this.e + bm.da);
                if (!dx) {
                    break Label_0165;
                }
            }
            if (this.e >= u.a) {
                this.o.setText(u.a + bm.cY);
                if (!dx) {
                    break Label_0165;
                }
            }
            this.o.setText(this.e + bm.da);
        }
        if (this.c == null) {
            this.d.addItem(bm.cZ);
        }
        bt bt = this.c;
        while (true) {
            Label_0292: {
                if (!dx) {
                    break Label_0292;
                }
                final int index = bt.a.indexOf(32);
                final String a = this.a(bt.a.substring(0, index), 10);
                final int index2 = bt.a.indexOf(58);
                this.d.addItem(a + this.a(bt.a.substring(index + 1, index2), 5) + bt.a.substring(index2 + 1));
                bt = bt.c;
            }
            if (bt == null) {
                return;
            }
            continue;
        }
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    private static String c(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'p';
                    break;
                }
                case 1: {
                    c2 = '[';
                    break;
                }
                case 2: {
                    c2 = 'v';
                    break;
                }
                case 3: {
                    c2 = 'L';
                    break;
                }
                default: {
                    c2 = 'r';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
