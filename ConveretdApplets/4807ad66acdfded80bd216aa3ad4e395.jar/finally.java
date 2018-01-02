import java.net.MalformedURLException;
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

public class finally extends else implements ActionListener
{
    private Hashtable l;
    private if m;
    private static String ja = "\u2590\u2595\u25ac\u2586\u258a\u258b\u25b0\u25b7\u25a9";
    private static String na = "\u2596\u2591\u2584\u259c\u25ac\u2586\u258a\u258b\u25b0\u25b7\u25a9";
    private static String oa = "\u2581\u258a\u2592\u258b\u25ac\u2586\u258a\u258b\u25b0\u25b7\u25a9";
    private static String pa = "\u2583\u258a\u258b\u2591";
    private static String sa = "\u25b3\u2580\u2597\u2581\u2584\u258b\u2584";
    private static String ta = "\u2596\u259c\u2588\u2587\u258a\u2589\u25a3\u258a\u258b\u2591";
    private static String ua = "\u2593\u2584\u2589\u2590\u2580\u25a3\u258a\u258b\u2591";
    private static String va = "\u25af\u25a4\u25b3\u25a4\u25c8\u25b1\u25ac\u25a6\u25ae\u25a0\u25b7\u25cb\u25ab\u25a0\u25b1\u25c5\u25a1\u25a0\u25a8\u25aa";
    private static String wa = "\u25b6\u25bc\u25a8\u25a7\u25aa\u25a9";
    private static String rb = "\u25ab\u25a4\u25a8\u25a0";
    private static String sb = "\u2596\u259c\u2588\u2587\u258a\u2589\u25b0\u25b7\u25a9";
    private static String tb = "\u2596\u259c\u2588\u2587\u258a\u2589\u2596";
    private static String _ = "\u25c9";
    private static String a = "\u25b0\u25b7\u25a9";
    private static String b = "\u25c7\u2596\u259c\u2588\u2587\u258a\u2589\u2596\u25c7\u25c5\u2595\u2584\u2597\u2584\u2588\u2580\u2591\u2580\u2597\u25c5\u258b\u258a\u2591\u25c5\u2596\u2580\u2591\u25c4\u25c5\u2583\u2584\u2591\u2584\u2589\u25c5\u2580\u259d\u2586\u2580\u2595\u2591\u258c\u258a\u258b\u25c4";
    private static String c = "\u2591\u2584\u2597\u2582\u2580\u2591";
    private static String d = "\u25ba\u2587\u2589\u2584\u258b\u258e";
    private static String e = "\u258d\u2591\u2591\u2595\u25df\u25ca\u25ca\u2592\u2592\u2592\u25cb\u258f\u2584\u2593\u2584\u25c8\u2591\u258c\u2586\u258e\u2580\u2597\u25cb\u258b\u2580\u2591";
    
    public finally(final Applet applet) {
        super(applet);
        super.n.addActionListener(this);
    }
    
    protected void init() {
        final MediaTracker mediaTracker = new MediaTracker(super.o);
        final Image[] array = new Image[3];
        final String[] array2 = { finally.ja, finally.na, finally.oa };
        for (int i = 0; i < array2.length; ++i) {
            mediaTracker.addImage(array[i] = this.b(array2[i], mediaTracker, i), i);
        }
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        final Hashtable[] a = this.a();
        final Font b = extends.b(super.o, finally.pa, finally.sa, 0, 13);
        int n = Math.max(super.o.getFontMetrics(extends.b(super.o, finally.ta, b.getName(), b.getStyle(), b.getSize())).getAscent(), super.o.getFontMetrics(extends.b(super.o, finally.ua, b.getName(), b.getStyle(), b.getSize())).getAscent());
        if (this.b(a)) {
            n = Math.max(super.ra, super.o.getFontMetrics(b).getAscent());
        }
        n -= 2;
        super.ra = (super.height - n) / 2 + n;
        this.m = new if(super.height, super.ra, array, super.o, super.o);
        this.l = new Hashtable();
        final import q = new import(this, super.o);
        final int n2 = 3;
        super.n.b(this.a(finally.va, null), new Object(), super.p);
        for (int j = 0; j < n2; ++j) {
            final String s = a[j].get(finally.wa);
            if (s.charAt(0) == '@') {
                super.n.b(this.a((String)a[j].get(finally.rb), null), new Object(), super.p);
            }
            else {
                final Object o = new Object();
                final instanceof a2 = this.m.a(a[j], super.o);
                q.a(a[j]);
                Vector<?> vector = this.l.get(s);
                if (vector == null) {
                    vector = new Vector<Object>();
                    this.l.put(s, vector);
                }
                vector.addElement(o);
                super.n.b(a2, o, super.p);
            }
        }
        super.q = q;
    }
    
    private boolean b(final Hashtable[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (((String)array[i].get(finally.wa)).charAt(0) == '@') {
                return true;
            }
        }
        return false;
    }
    
    private Hashtable[] a() {
        final String parameter = super.o.getParameter(finally.sb);
        final String parameter2 = super.o.getParameter(finally.tb);
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter2, finally._);
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
                array[i].put(finally.wa, s);
                if (s.charAt(0) == '@' && s2.charAt(0) == '@') {
                    s2 = s2.substring(1);
                }
                array[i].put(finally.rb, s2);
                if (parameter != null && s.charAt(0) != '@') {
                    array[i].put(finally.a, String.valueOf(super.o.getParameter(finally.sb)) + URLEncoder.encode(s));
                }
            }
            return array;
        }
        catch (Exception ex) {
            System.out.println(finally.b);
            return new Hashtable[0];
        }
    }
    
    public void a(final Hashtable hashtable, final Object o) {
        final Vector<Object> vector = this.l.get(hashtable.get(finally.wa));
        if (vector == null) {
            System.out.println(hashtable);
            return;
        }
        final instanceof a = this.m.a(hashtable, super.o);
        for (int i = 0; i < vector.size(); ++i) {
            super.n.b(a, vector.elementAt(i), super.p);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof Button) {
            super.actionPerformed(actionEvent);
            return;
        }
        final String _ = extends._(super.o, finally.c, finally.d);
        actionEvent.getActionCommand();
        URL url = null;
        try {
            url = new URL(finally.e);
        }
        catch (MalformedURLException ex) {}
        if (url != null) {
            super.o.getAppletContext().showDocument(url, _);
        }
    }
    
    private static String m(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF25E5);
        }
        return new String(array);
    }
    
    static {
        finally.ja = m(finally.ja);
        finally.na = m(finally.na);
        finally.oa = m(finally.oa);
        finally.pa = m(finally.pa);
        finally.sa = m(finally.sa);
        finally.ta = m(finally.ta);
        finally.ua = m(finally.ua);
        finally.va = m(finally.va);
        finally.wa = m(finally.wa);
        finally.rb = m(finally.rb);
        finally.sb = m(finally.sb);
        finally.tb = m(finally.tb);
        finally._ = m(finally._);
        finally.a = m(finally.a);
        finally.b = m(finally.b);
        finally.c = m(finally.c);
        finally.d = m(finally.d);
        finally.e = m(finally.e);
    }
}
