import java.net.URL;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.net.URLEncoder;
import java.util.StringTokenizer;
import java.awt.Font;
import java.util.Vector;
import java.awt.Image;
import java.awt.Component;
import java.awt.MediaTracker;
import java.applet.Applet;
import java.util.Hashtable;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class o extends l implements ActionListener
{
    private Hashtable Fa;
    private r Ga;
    private static String l = "\uf735\uf730\uf709\uf723\uf72f\uf72e\uf715\uf712\uf70c";
    private static String m = "\uf733\uf734\uf721\uf739\uf709\uf723\uf72f\uf72e\uf715\uf712\uf70c";
    private static String o = "\uf724\uf72f\uf737\uf72e\uf709\uf723\uf72f\uf72e\uf715\uf712\uf70c";
    private static String p = "\uf726\uf72f\uf72e\uf734";
    private static String s = "\uf716\uf725\uf732\uf724\uf721\uf72e\uf721";
    private static String u = "\uf733\uf739\uf72d\uf722\uf72f\uf72c\uf706\uf72f\uf72e\uf734";
    private static String v = "\uf736\uf721\uf72c\uf735\uf725\uf706\uf72f\uf72e\uf734";
    private static String w = "\uf713\uf719\uf70d\uf702\uf70f\uf70c";
    private static String O = "\uf70e\uf701\uf70d\uf705";
    private static String P = "\uf733\uf739\uf72d\uf722\uf72f\uf72c\uf715\uf712\uf70c";
    private static String sa = "\uf733\uf739\uf72d\uf722\uf72f\uf72c\uf733";
    private static String ta = "\uf76c";
    private static String ua = "\uf715\uf712\uf70c";
    private static String va = "\uf762\uf733\uf739\uf72d\uf722\uf72f\uf72c\uf733\uf762\uf760\uf730\uf721\uf732\uf721\uf72d\uf725\uf734\uf725\uf732\uf760\uf72e\uf72f\uf734\uf760\uf733\uf725\uf734\uf761\uf760\uf726\uf721\uf734\uf721\uf72c\uf760\uf725\uf738\uf723\uf725\uf730\uf734\uf729\uf72f\uf72e\uf761";
    private static String wa = "\uf734\uf721\uf732\uf727\uf725\uf734";
    private static String xa = "\uf71f\uf722\uf72c\uf721\uf72e\uf72b";
    
    public o(final Applet applet) {
        super(applet);
        super.Ha.addActionListener(this);
    }
    
    protected void init() {
        final MediaTracker mediaTracker = new MediaTracker(super.Ia);
        final Image[] array = new Image[3];
        final String[] array2 = { o.l, o.m, o.o };
        for (int i = 0; i < array2.length; ++i) {
            mediaTracker.addImage(array[i] = this.a(array2[i], mediaTracker, i), i);
        }
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        final Hashtable[] a = this.a();
        final Font _ = m._(super.Ia, o.p, o.s, 0, 13);
        int n = Math.max(super.Ia.getFontMetrics(m._(super.Ia, o.u, _.getName(), _.getStyle(), _.getSize())).getAscent(), super.Ia.getFontMetrics(m._(super.Ia, o.v, _.getName(), _.getStyle(), _.getSize())).getAscent());
        if (this.b(a)) {
            n = Math.max(super.t, super.Ia.getFontMetrics(_).getAscent());
        }
        n -= 2;
        super.t = (super.height - n) / 2 + n;
        this.Ga = new r(super.height, super.t, array, super.Ia, super.Ia);
        this.Fa = new Hashtable();
        final t ka = new t(this, super.Ia);
        for (int length = a.length, j = 0; j < length; ++j) {
            final String s = a[j].get(o.w);
            if (s.charAt(0) == '@') {
                super.Ha._(this.a((String)a[j].get(o.O)), new Object(), super.Ja);
            }
            else {
                final Object o = new Object();
                final u b = this.Ga.b(a[j], super.Ia);
                ka._(a[j]);
                Vector<?> vector = this.Fa.get(s);
                if (vector == null) {
                    vector = new Vector<Object>();
                    this.Fa.put(s, vector);
                }
                vector.addElement(o);
                super.Ha._(b, o, super.Ja);
            }
        }
        super.Ka = ka;
    }
    
    private boolean b(final Hashtable[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (((String)array[i].get(o.w)).charAt(0) == '@') {
                return true;
            }
        }
        return false;
    }
    
    private Hashtable[] a() {
        final String parameter = super.Ia.getParameter(o.P);
        final String parameter2 = super.Ia.getParameter(o.sa);
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter2, o.ta);
            final Hashtable[] array = new Hashtable[stringTokenizer.countTokens()];
            for (int i = 0; i < array.length; ++i) {
                array[i] = new Hashtable();
                String s = stringTokenizer.nextToken().trim();
                final int index = s.indexOf(61);
                String s2;
                if (index >= 0 && s.charAt(0) != '@') {
                    s2 = s.substring(index + 1);
                    s = s.substring(0, index);
                }
                else {
                    s2 = s;
                }
                array[i].put(o.w, s);
                if (s.charAt(0) == '@' && s2.charAt(0) == '@') {
                    s2 = s2.substring(1);
                }
                array[i].put(o.O, s2);
                if (parameter != null && s.charAt(0) != '@') {
                    array[i].put(o.ua, URLEncoder.encode(s));
                }
            }
            return array;
        }
        catch (Exception ex) {
            System.out.println(o.va);
            return new Hashtable[0];
        }
    }
    
    public void b(final Hashtable hashtable, final Object o) {
        final Vector<Object> vector = this.Fa.get(hashtable.get(o.w));
        if (vector == null) {
            System.out.println(hashtable);
            return;
        }
        final u b = this.Ga.b(hashtable, super.Ia);
        for (int i = 0; i < vector.size(); ++i) {
            super.Ha._(b, vector.elementAt(i), super.Ja);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof Button) {
            super.actionPerformed(actionEvent);
            return;
        }
        final String a = m.a(super.Ia, o.wa, o.xa);
        final URL b = m.b(super.Ia, String.valueOf(super.Ia.getParameter(o.P)) + actionEvent.getActionCommand(), false);
        if (b != null) {
            super.Ia.getAppletContext().showDocument(b, a);
        }
    }
    
    private static String n(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFEF740);
        }
        return new String(array);
    }
    
    static {
        o.l = n(o.l);
        o.m = n(o.m);
        o.o = n(o.o);
        o.p = n(o.p);
        o.s = n(o.s);
        o.u = n(o.u);
        o.v = n(o.v);
        o.w = n(o.w);
        o.O = n(o.O);
        o.P = n(o.P);
        o.sa = n(o.sa);
        o.ta = n(o.ta);
        o.ua = n(o.ua);
        o.va = n(o.va);
        o.wa = n(o.wa);
        o.xa = n(o.xa);
    }
}
