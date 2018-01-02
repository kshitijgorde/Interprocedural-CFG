// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.g;

import java.awt.event.WindowEvent;
import java.util.Hashtable;
import flaxchat.f.g;
import flaxchat.i.h;
import java.awt.BorderLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import flaxchat.a.e;
import flaxchat.n;
import java.awt.Color;
import flaxchat.i.b;
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
    
    public d(final n n) {
        final int b = flaxchat.g.c.b;
        super(n);
        this.f = new Color(9, 147, 210);
        final String s = flaxchat.g.d.z[17];
        final String string = String.valueOf(s) + flaxchat.g.d.z[15];
        final String string2 = flaxchat.g.d.z[18] + n.l().i();
        final String string3 = flaxchat.g.d.z[13] + n.l().j();
        final String s2 = flaxchat.g.d.z[12];
        final String s3 = flaxchat.g.d.z[14];
        final String s4 = flaxchat.g.d.z[16];
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
        if (b != 0) {
            int c = flaxchat.a.e.c;
            flaxchat.a.e.c = ++c;
        }
    }
    
    private Panel a() {
        final e e = new e();
        e.setLayout(new FlowLayout(2));
        final flaxchat.i.e e2 = new flaxchat.i.e(flaxchat.g.d.z[3]);
        e2.a(this);
        e2.setFocusable(true);
        e.add(e2);
        final flaxchat.i.e e3 = new flaxchat.i.e(flaxchat.g.d.z[5]);
        e3.a(this);
        e3.setFocusable(true);
        e.add(e3);
        final flaxchat.i.e e4 = new flaxchat.i.e(flaxchat.g.d.z[2]);
        e4.a(this);
        e4.setFocusable(true);
        e.add(e4);
        return e;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (flaxchat.g.d.z[2].equals(actionCommand)) {
            this.d.dispose();
            return;
        }
        if (flaxchat.g.d.z[3].equals(actionCommand)) {
            try {
                super.a.l().getAppletContext().showDocument(new URL(flaxchat.g.d.z[1]), flaxchat.g.d.z[0]);
                return;
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
                return;
            }
        }
        if (flaxchat.g.d.z[5].equals(actionCommand)) {
            try {
                super.a.l().getAppletContext().showDocument(new URL(flaxchat.g.d.z[4]), flaxchat.g.d.z[0]);
            }
            catch (MalformedURLException ex2) {
                ex2.printStackTrace();
            }
        }
    }
    
    private void b() {
        (this.c = new Panel()).setLayout(new BorderLayout());
        this.c.add(this.c(), flaxchat.g.d.z[6]);
        this.c.add(this.d(), flaxchat.g.d.z[7]);
        (this.d = new a(flaxchat.a.c.b())).add(this.c, flaxchat.g.d.z[7]);
        this.d.add(this.a(), flaxchat.g.d.z[9]);
        this.d.addWindowListener(this);
        this.d.setSize(310, 320);
        this.d.setModal(false);
        this.d.setResizable(false);
        this.d.setTitle(String.valueOf(super.a.l().h()) + flaxchat.g.d.z[8]);
    }
    
    private Panel c() {
        final flaxchat.g.b b = new flaxchat.g.b(this);
        b.setBackground(this.f);
        return b;
    }
    
    private h d() {
        this.e.setForeground(Color.white);
        final h h = new h();
        h.a(this.e, flaxchat.g.d.z[10]);
        h.a(2, 2);
        h.setBackground(this.f);
        return h;
    }
    
    public void a(final Object o, final String s, final g g, final String s2, final Hashtable hashtable) {
        if (this.d == null) {
            this.b();
        }
        this.d.setLocation(flaxchat.a.h.a(super.a, this.d.getSize()));
        this.d.setVisible(true);
    }
    
    public String a() {
        return flaxchat.g.d.z[11];
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
        d.z = new String[] { z(z("+\u007fht^\u001f")), z(z("\u001cipe\n[2sbGZ{htH\u0017uea\u001e\u0017ri")), z(z("?|ttD")), z(z("#xf5c\u001diafY")), z(z("\u0019|myD\u001b'm{V\u001b]byQ\f~ltDZ~kx")), z(z("1pe|\\")), z(z(":rvaX")), z(z("7xjaU\u0006")), z(z("TUe~[\u001ds`t")), z(z("'rqaX")), z(z("2qems\u001c|p5x\u0015vo|^\u0010|")), z(z("\u0015\u007fk`D")), z(z("~\u0002sbGZ{htH\u0017uea\u001e\u0017ri")), z(z("~\u001e0LU\u0000vmyYT_elYT'$\u0017")), z(z("9NJ5\nTtjs_4{htH\u0017uea\u001e\u0017ri")), z(z("~\u001f\u0007!v\u0018||\u0016\u00027uea\u0010v\u001e55&:xs5w\u0011sagQ\u0000tk{\u00107uea\u0010'rbaG\u0015oa")), z(z("7rtlB\u001dzla\u0010F-4,\u009e")), z(z("~=$5")), z(z("~\u001e5YY\u0007|jf\u0010N=")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '0';
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
                    c2 = 't';
                    break;
                }
                case 1: {
                    c2 = '\u001d';
                    break;
                }
                case 2: {
                    c2 = '\u0004';
                    break;
                }
                case 3: {
                    c2 = '\u0015';
                    break;
                }
                default: {
                    c2 = '0';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
