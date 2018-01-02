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

public class native extends import implements ActionListener
{
    private Hashtable sb;
    private package tb;
    private static String V = "\u3bd0\u3bd5\u3bec\u3bc6\u3bca\u3bcb\u3bf0\u3bf7\u3be9";
    private static String W = "\u3bd6\u3bd1\u3bc4\u3bdc\u3bec\u3bc6\u3bca\u3bcb\u3bf0\u3bf7\u3be9";
    private static String _a = "\u3bc1\u3bca\u3bd2\u3bcb\u3bec\u3bc6\u3bca\u3bcb\u3bf0\u3bf7\u3be9";
    private static String aa = "\u3bc3\u3bca\u3bcb\u3bd1";
    private static String ba = "\u3bf3\u3bc0\u3bd7\u3bc1\u3bc4\u3bcb\u3bc4";
    private static String fa = "\u3bd6\u3bdc\u3bc8\u3bc7\u3bca\u3bc9\u3be3\u3bca\u3bcb\u3bd1";
    private static String ga = "\u3bd3\u3bc4\u3bc9\u3bd0\u3bc0\u3be3\u3bca\u3bcb\u3bd1";
    private static String ha = "\u3bf6\u3bfc\u3be8\u3be7\u3bea\u3be9";
    private static String ia = "\u3beb\u3be4\u3be8\u3be0";
    private static String db = "\u3bd6\u3bdc\u3bc8\u3bc7\u3bca\u3bc9\u3bf0\u3bf7\u3be9";
    private static String eb = "\u3bd6\u3bdc\u3bc8\u3bc7\u3bca\u3bc9\u3bd6";
    private static String fb = "\u3b89";
    private static String gb = "\u3bf0\u3bf7\u3be9";
    private static String hb = "\u3b87\u3bd6\u3bdc\u3bc8\u3bc7\u3bca\u3bc9\u3bd6\u3b87\u3b85\u3bd5\u3bc4\u3bd7\u3bc4\u3bc8\u3bc0\u3bd1\u3bc0\u3bd7\u3b85\u3bcb\u3bca\u3bd1\u3b85\u3bd6\u3bc0\u3bd1\u3b84\u3b85\u3bc3\u3bc4\u3bd1\u3bc4\u3bc9\u3b85\u3bc0\u3bdd\u3bc6\u3bc0\u3bd5\u3bd1\u3bcc\u3bca\u3bcb\u3b84";
    private static String ib = "\u3bd1\u3bc4\u3bd7\u3bc2\u3bc0\u3bd1";
    private static String jb = "\u3bfa\u3bc7\u3bc9\u3bc4\u3bcb\u3bce";
    
    public native(final Applet applet) {
        super(applet);
        super._.addActionListener(this);
    }
    
    protected void init() {
        final MediaTracker mediaTracker = new MediaTracker(super.a);
        final Image[] array = new Image[3];
        final String[] array2 = { native.V, native.W, native._a };
        for (int i = 0; i < array2.length; ++i) {
            mediaTracker.addImage(array[i] = this.b(array2[i], mediaTracker, i), i);
        }
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        final Hashtable[] a = this.a();
        final Font a2 = instanceof.a(super.a, native.aa, native.ba, 0, 13);
        int n = Math.max(super.a.getFontMetrics(instanceof.a(super.a, native.fa, a2.getName(), a2.getStyle(), a2.getSize())).getAscent(), super.a.getFontMetrics(instanceof.a(super.a, native.ga, a2.getName(), a2.getStyle(), a2.getSize())).getAscent());
        if (this.a(a)) {
            n = Math.max(super.ea, super.a.getFontMetrics(a2).getAscent());
        }
        n -= 2;
        super.ea = (super.height - n) / 2 + n;
        this.tb = new package(super.height, super.ea, array, super.a, super.a);
        this.sb = new Hashtable();
        final protected c = new protected(this, super.a);
        for (int length = a.length, j = 0; j < length; ++j) {
            final String s = a[j].get(native.ha);
            if (s.charAt(0) == '@') {
                super._.a(this.a((String)a[j].get(native.ia), null), new Object(), super.b);
            }
            else {
                final Object o = new Object();
                final public b = this.tb.b(a[j], super.a);
                c.b(a[j]);
                Vector<?> vector = this.sb.get(s);
                if (vector == null) {
                    vector = new Vector<Object>();
                    this.sb.put(s, vector);
                }
                vector.addElement(o);
                super._.a(b, o, super.b);
            }
        }
        super.c = c;
    }
    
    private boolean a(final Hashtable[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (((String)array[i].get(native.ha)).charAt(0) == '@') {
                return true;
            }
        }
        return false;
    }
    
    private Hashtable[] a() {
        final String parameter = super.a.getParameter(native.db);
        final String parameter2 = super.a.getParameter(native.eb);
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter2, native.fb);
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
                array[i].put(native.ha, s);
                if (s.charAt(0) == '@' && s2.charAt(0) == '@') {
                    s2 = s2.substring(1);
                }
                array[i].put(native.ia, s2);
                if (parameter != null && s.charAt(0) != '@') {
                    array[i].put(native.gb, String.valueOf(super.a.getParameter(native.db)) + URLEncoder.encode(s));
                }
            }
            return array;
        }
        catch (Exception ex) {
            System.out.println(native.hb);
            return new Hashtable[0];
        }
    }
    
    public void _(final Hashtable hashtable, final Object o) {
        final Vector<Object> vector = this.sb.get(hashtable.get(native.ha));
        if (vector == null) {
            System.out.println(hashtable);
            return;
        }
        final public b = this.tb.b(hashtable, super.a);
        for (int i = 0; i < vector.size(); ++i) {
            super._.a(b, vector.elementAt(i), super.b);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof Button) {
            super.actionPerformed(actionEvent);
            return;
        }
        final String _ = instanceof._(super.a, native.ib, native.jb);
        final URL _2 = instanceof._((Object)super.a, actionEvent.getActionCommand(), false);
        if (_2 != null) {
            super.a.getAppletContext().showDocument(_2, _);
        }
    }
    
    private static String f(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF3BA5);
        }
        return new String(array);
    }
    
    static {
        native.V = f(native.V);
        native.W = f(native.W);
        native._a = f(native._a);
        native.aa = f(native.aa);
        native.ba = f(native.ba);
        native.fa = f(native.fa);
        native.ga = f(native.ga);
        native.ha = f(native.ha);
        native.ia = f(native.ia);
        native.db = f(native.db);
        native.eb = f(native.eb);
        native.fb = f(native.fb);
        native.gb = f(native.gb);
        native.hb = f(native.hb);
        native.ib = f(native.ib);
        native.jb = f(native.jb);
    }
}
