import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Frame;
import java.applet.Applet;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class p extends Panel implements ActionListener
{
    public static final String a;
    protected Button b;
    j c;
    Applet d;
    Frame e;
    private static final String[] z;
    
    public p(final Applet d) {
        this.d = null;
        this.e = null;
        this.d = d;
        this.e = null;
        (this.b = new Button(p.z[1])).addActionListener(this);
        this.add(this.b);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.b && (this.c == null || !this.c.isShowing())) {
            (this.c = new j((this.e == null) ? j.a(this.d.getParent()) : this.e, p.z[0], p.a)).setVisible(true);
            this.c.requestFocus();
            this.c.requestFocusInWindow();
        }
    }
    
    static {
        z = new String[] { z(z("O\u0018\u0003LknP%Fbs\u0002\u0001Ipu\u001f\u0002")), z(z("µP?@kx\u001f\u001e")) };
        a = z(z("_\u001f\u001cQvu\u0017\u0004\\$-IU\u001f)")) + Calendar.getInstance().get(1) + z(z("2PL|lyP?@kx\u001f\u001e\bAx\u0005\u000fIpu\u001f\u0002\bBs\u0005\u0002Leh\u0019\u0003F(<9\u0002K*\u0016z")) + z(z("Z\u001f\u001e\bex\u0014\u0005\\ms\u001e\rD$\u007f\u001f\u001cQvu\u0017\u0004\\$u\u001e\nGvq\u0011\u0018Akr\\LMi}\u0019\u0000\bsy\u0012\u0001Iwh\u0015\u001ehwt\u001f\bGv2\u001f\u001eO*\u0016z")) + z(z("H\u001fLDa}\u0002\u0002\bis\u0002\t\be~\u001f\u0019\\$O\u0018\u0003Lkn\\L^mo\u0019\u0018\blh\u0004\u001c\u0012+3\u0007\u001b_*o\u0018\u0003Lkn^\u0003Zc2"));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '\u0004';
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
                    c2 = '\u001c';
                    break;
                }
                case 1: {
                    c2 = 'p';
                    break;
                }
                case 2: {
                    c2 = 'l';
                    break;
                }
                case 3: {
                    c2 = '(';
                    break;
                }
                default: {
                    c2 = '\u0004';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
