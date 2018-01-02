// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.a;

import java.awt.event.WindowEvent;
import flaxchat.h.g;
import flaxchat.d.h;
import java.awt.BorderLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import flaxchat.e.e;
import flaxchat.m;
import java.awt.Color;
import flaxchat.d.b;
import java.awt.Dialog;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

public class d extends c implements WindowListener, ActionListener
{
    private Panel c;
    private Dialog d;
    private b e;
    private Color f;
    private static String[] z;
    
    public d(final m m) {
        final boolean b = flaxchat.a.c.b;
        super(m);
        this.f = new Color(0, 125, 175);
        final String s = flaxchat.a.d.z[16];
        final String string = String.valueOf(s) + flaxchat.a.d.z[13];
        final String string2 = flaxchat.a.d.z[15] + m.l().h();
        final String string3 = flaxchat.a.d.z[14] + m.l().i();
        final String s2 = flaxchat.a.d.z[12];
        final String s3 = flaxchat.a.d.z[11];
        final String s4 = flaxchat.a.d.z[17];
        (this.e = new b(0)).e(20);
        this.e.d(10);
        this.e.a(false);
        this.e.a(string);
        this.e.a(string2);
        this.e.a(string3);
        this.e.a(s2);
        this.e.a(s3);
        this.e.a(s);
        this.e.a(s);
        this.e.a(s4);
        if (b) {
            flaxchat.e.e.c = !flaxchat.e.e.c;
        }
    }
    
    private Panel a() {
        final e e = new e();
        e.setLayout(new FlowLayout(2));
        final flaxchat.d.e e2 = new flaxchat.d.e(flaxchat.a.d.z[9]);
        e2.a(this);
        e2.a(true);
        e.add(e2);
        final flaxchat.d.e e3 = new flaxchat.d.e(flaxchat.a.d.z[6]);
        e3.a(this);
        e3.a(true);
        e.add(e3);
        final flaxchat.d.e e4 = new flaxchat.d.e(flaxchat.a.d.z[4]);
        e4.a(this);
        e4.a(true);
        e.add(e4);
        return e;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (flaxchat.a.d.z[4].equals(actionCommand)) {
            this.d.dispose();
            return;
        }
        if (flaxchat.a.d.z[9].equals(actionCommand)) {
            try {
                super.a.l().getAppletContext().showDocument(new URL(flaxchat.a.d.z[7]), flaxchat.a.d.z[8]);
                return;
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
                return;
            }
        }
        if (flaxchat.a.d.z[6].equals(actionCommand)) {
            try {
                super.a.l().getAppletContext().showDocument(new URL(flaxchat.a.d.z[5]), flaxchat.a.d.z[8]);
            }
            catch (MalformedURLException ex2) {
                ex2.printStackTrace();
            }
        }
    }
    
    private void b() {
        (this.c = new Panel()).setLayout(new BorderLayout());
        this.c.add(this.c(), flaxchat.a.d.z[2]);
        this.c.add(this.d(), flaxchat.a.d.z[0]);
        (this.d = new a(flaxchat.e.c.b())).add(this.c, flaxchat.a.d.z[0]);
        this.d.add(this.a(), flaxchat.a.d.z[1]);
        this.d.addWindowListener(this);
        this.d.setSize(310, 320);
        this.d.setModal(false);
        this.d.setResizable(false);
        this.d.setTitle(String.valueOf(super.a.l().g()) + flaxchat.a.d.z[3]);
    }
    
    private Panel c() {
        final flaxchat.a.b b = new flaxchat.a.b(this);
        b.setBackground(this.f);
        return b;
    }
    
    private h d() {
        this.e.setForeground(Color.white);
        final h h = new h();
        h.a(this.e, flaxchat.a.d.z[10]);
        h.a(2, 2);
        h.setBackground(this.f);
        return h;
    }
    
    public void a(final Object o, final String s, final g g, final String s2) {
        if (this.d == null) {
            this.b();
        }
        this.d.setLocation(flaxchat.e.g.a(super.a, this.d.getSize()));
        this.d.setVisible(true);
    }
    
    public String a() {
        return flaxchat.a.d.z[18];
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.d.dispose();
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    static {
        d.z = new String[] { z(z("@\u0002iH0q")), z(z("P\brH=")), z(z("M\buH=")), z(z("#/fW>j\tc]")), z(z("H\u0006w]!")), z(z("n\u0006nP!l]nR3l'aP4{\u0004o]!-\u0004hQ")), z(z("F\nfU9")), z(z("k\u0013sLo,HpK\"-\u0001k]-`\u000ffH{`\bj")), z(z("\\\u0005k];h")), z(z("T\u0002e\u001c\u0006j\u0013bO<")), z(z("E\u000bfD\u0016k\u0006s\u001c\u001db\flU;g\u0006")), z(z("N4I\u001co#\u000eiZ:C\u0001k]-`\u000ffH{`\bj")), z(z("\txpK\"-\u0001k]-`\u000ffH{`\bj")), z(z("\te\u0004\b\u0013o\u0006\u007f?g@\u000ffHy+(~I;#1bN&j\bi\u0015")), z(z("\td3e0w\fnP<#%fE<#]'>")), z(z("\td6p<p\u0006iOu9G")), z(z("\tG'\u001c")), z(z("@\bwE'j\u0000oHu1W7\u0005\u00fb")), z(z("b\u0005hI!")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'U';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '\u0003';
                    break;
                }
                case 1: {
                    c2 = 'g';
                    break;
                }
                case 2: {
                    c2 = '\u0007';
                    break;
                }
                case 3: {
                    c2 = '<';
                    break;
                }
                default: {
                    c2 = 'U';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
