import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.TextComponent;
import java.awt.Button;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class j extends Frame implements ActionListener, WindowListener
{
    String a;
    protected boolean b;
    protected Button c;
    ActionListener d;
    private static j e;
    private TextComponent f;
    private int g;
    private int h;
    private static final String[] z;
    
    public j(final Frame frame, final String title, final String s) {
        this.b = false;
        this.d = null;
        this.f = null;
        this.g = 0;
        this.h = 0;
        final int n = 0;
        this.a = e.a(s, n);
        final int n2 = 2 + this.a.length() / 40 + n;
        this.setTitle(title);
        this.setSize((int)Math.round(400.0), 25 * (n2 + 5));
        this.setLayout(new mb(j.z[3]));
        this.add(j.z[2], new e(this.a, (int)Math.round(300.0), (n2 + 1) * 25));
        (this.c = new Button(j.z[0])).addActionListener(this);
        this.add(j.z[1], this.c);
        this.setBackground(Color.white);
        this.addWindowListener(this);
        this.validate();
    }
    
    public void setVisible(final boolean b) {
        if (!b) {
            super.setVisible(false);
            if (this.d != null) {
                this.d.actionPerformed(new ActionEvent(this, -1, ""));
            }
            return;
        }
        super.setVisible(true);
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
        this.setVisible(false);
        if (this.f != null) {
            this.f.setEditable(true);
        }
        if (this.f != null) {
            this.f.requestFocus();
            this.f.select(this.g, this.h);
        }
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
        if (this.f != null) {
            this.f.setEditable(true);
        }
        if (this.f != null) {
            this.f.requestFocus();
            this.f.select(this.g, this.h);
        }
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.c) {
            this.setVisible(false);
            if (this.f != null) {
                this.f.setEditable(true);
            }
            if (this.f != null) {
                this.f.requestFocus();
                this.f.select(this.g, this.h);
            }
        }
    }
    
    public static Frame a(final Object o) {
        final int c = d.c;
        Object parent = o;
        while (!(parent instanceof Frame)) {
            parent = ((Component)parent).getParent();
            if (c != 0) {
                break;
            }
        }
        return (Frame)parent;
    }
    
    static {
        final String[] z2 = new String[4];
        final int n = 0;
        final char[] charArray = "vNSJ\u001a".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '5';
                    break;
                }
                case 1: {
                    c2 = '\"';
                    break;
                }
                case 2: {
                    c2 = '<';
                    break;
                }
                case 3: {
                    c2 = '9';
                    break;
                }
                default: {
                    c2 = '\u007f';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z2[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "vGRM\u001aG".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '5';
                    break;
                }
                case 1: {
                    c4 = '\"';
                    break;
                }
                case 2: {
                    c4 = '<';
                    break;
                }
                case 3: {
                    c4 = '9';
                    break;
                }
                default: {
                    c4 = '\u007f';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z2[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "yGZM".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '5';
                    break;
                }
                case 1: {
                    c6 = '\"';
                    break;
                }
                case 2: {
                    c6 = '<';
                    break;
                }
                case 3: {
                    c6 = '9';
                    break;
                }
                default: {
                    c6 = '\u007f';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z2[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "{MNM\u0017".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '5';
                    break;
                }
                case 1: {
                    c8 = '\"';
                    break;
                }
                case 2: {
                    c8 = '<';
                    break;
                }
                case 3: {
                    c8 = '9';
                    break;
                }
                default: {
                    c8 = '\u007f';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z2[n10] = new String(charArray4).intern();
        z = z2;
        j.e = null;
    }
}
