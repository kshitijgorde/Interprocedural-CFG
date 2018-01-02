import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.Label;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.event.FocusListener;
import java.awt.event.ItemListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class z extends Panel implements ItemListener, FocusListener
{
    bb a;
    Choice b;
    Choice c;
    Checkbox d;
    Checkbox e;
    Choice f;
    bf g;
    
    public void a(final String s) {
        final boolean dx = bm.dX;
        int n = 0;
        while (true) {
            while (true) {
                Label_0037: {
                    if (!dx) {
                        break Label_0037;
                    }
                    if (this.f.getItem(n).equals(s)) {
                        this.f.select(n);
                        return;
                    }
                    ++n;
                }
                if (n != this.f.countItems()) {
                    continue;
                }
                break;
            }
            this.f.addItem(s);
            this.f.select(s);
            if (!dx) {
                return;
            }
            continue;
        }
    }
    
    z(final bb a, final String[] array, final bf g) {
        final boolean dx = bm.dX;
        this.b = new Choice();
        this.c = new Choice();
        this.d = new Checkbox(bm.dC);
        this.e = new Checkbox(bm.dD);
        this.f = new Choice();
        this.a = a;
        this.b.addItemListener(this);
        this.b.addFocusListener(this);
        this.c.addItemListener(this);
        this.c.addFocusListener(this);
        this.g = g;
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        if (irc.bs) {
            irc.cz.a(panel, a);
            if (irc.bu) {
                this.setLayout(new GridLayout(2, 1));
            }
            this.add(panel);
        }
        int n = 0;
    Label_0379_Outer:
        while (true) {
            while (true) {
                Label_0210: {
                    if (!dx) {
                        break Label_0210;
                    }
                    this.b.add(bn.P[n]);
                    this.c.add(bn.P[n]);
                    ++n;
                }
                if (n != bn.P.length) {
                    continue;
                }
                break;
            }
            this.b.select(irc.cn);
            this.c.select(irc.co);
            this.a.a(irc.cn);
            this.a.b(irc.co);
            if (!dx) {
                if (irc.bo) {
                    panel2.add(new Label(bm.dH));
                    panel2.add(this.b);
                    panel2.add(new Label(bm.dI));
                    panel2.add(this.c);
                    panel2.add(this.d);
                    panel2.add(this.e);
                    this.d.addItemListener(this);
                    this.e.addItemListener(this);
                }
                if (array != null && array.length > 0) {
                    int n2 = 0;
                    Choice f2 = null;
                    while (true) {
                        while (true) {
                            Label_0389: {
                                if (!dx) {
                                    break Label_0389;
                                }
                                final Choice f = this.f;
                                f2.addItem(array[n2]);
                                ++n2;
                            }
                            if (n2 != array.length) {
                                continue Label_0379_Outer;
                            }
                            break;
                        }
                        panel2.add(this.f);
                        f2 = this.f;
                        if (dx) {
                            continue;
                        }
                        break;
                    }
                    f2.addItemListener(this);
                }
                if (irc.bu) {
                    this.add(panel2);
                }
                return;
            }
            continue;
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() == this.b) {
            this.a.a(this.b.getSelectedIndex());
            this.a.requestFocus();
        }
        if (itemEvent.getSource() == this.c) {
            this.a.b(this.c.getSelectedIndex());
            this.a.requestFocus();
        }
        if (itemEvent.getSource() == this.d) {
            this.a.c(2);
            this.a.requestFocus();
        }
        if (itemEvent.getSource() == this.e) {
            this.a.c(31);
            this.a.requestFocus();
        }
        if (itemEvent.getSource() == this.f && irc.bB) {
            irc.cy.b(this.f.getSelectedItem());
            this.g.a(b(")$)VH") + this.f.getSelectedItem() + "\n");
        }
    }
    
    public void a(final int n) {
        if (n < 0 || n > bn.P.length) {
            return;
        }
        this.b.select(n);
        this.a.a(this.b.getSelectedIndex());
        this.a.requestFocus();
    }
    
    public void focusLost(final FocusEvent focusEvent) {
    }
    
    public void b(final int n) {
        if (n < 0 || n > bn.P.length) {
            return;
        }
        this.c.select(n);
        this.a.b(this.c.getSelectedIndex());
        this.a.requestFocus();
    }
    
    public void a() {
        this.a.setText("");
        if (this.d.getState()) {
            this.a.c(2);
        }
        if (this.e.getState()) {
            this.a.c(31);
        }
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        if (this.a.g) {
            this.a.g = false;
            this.a.requestFocus();
        }
    }
    
    private static String b(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'c';
                    break;
                }
                case 1: {
                    c2 = 'k';
                    break;
                }
                case 2: {
                    c2 = '`';
                    break;
                }
                case 3: {
                    c2 = '\u0018';
                    break;
                }
                default: {
                    c2 = 'h';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
