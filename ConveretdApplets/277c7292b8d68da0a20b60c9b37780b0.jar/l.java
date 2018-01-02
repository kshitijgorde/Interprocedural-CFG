import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.Hashtable;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Insets;
import java.awt.Image;
import java.awt.Component;
import java.awt.MediaTracker;
import java.applet.Applet;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class l implements ActionListener
{
    public static String Na = "\u3de1\u3df0\u3ded\u3de1";
    public static String Oa = "\u3df3\u3df2\u3dd6\u3dfa\u3df9\u3dfa\u3de7";
    public static String Pa = "\u3df7\u3df2\u3dd6\u3dfa\u3df9\u3dfa\u3de7";
    public static String Qa = "\u3dfd\u3dfa\u3de3\u3df0\u3de7\u3dd6\u3dfa\u3df9\u3dfa\u3de7";
    public static String Ra = "\u3df3\u3dfa\u3dfb\u3de1";
    public static String Sa = "\u3dfd\u3dfa\u3de3\u3df0\u3de7\u3dd3\u3dfa\u3dfb\u3de1";
    public static String Ta = "\u3de0\u3de7\u3df9";
    public static final Object ba;
    public static final boolean Ua = false;
    protected Applet Ia;
    protected s Ha;
    protected abstract Ka;
    protected v Ja;
    protected int t;
    protected int height;
    private static String l = "\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8\u3da8";
    private static String m = "\u3db5\u3db5\u3db5\u3db5\u3ddb\u3df0\u3de2\u3de6\u3db5\u3db3\u3db5\u3dc4\u3de0\u3dfa\u3de1\u3df0\u3de6\u3db5\u3dc1\u3dfc\u3df6\u3dfe\u3df0\u3de7\u3db5\u3db8\u3db5\u3ddf\u3df4\u3de3\u3df4\u3db5\u3dd4\u3de5\u3de5\u3df9\u3df0\u3de1\u3db5\u3da6\u3dbb\u3da5";
    private static String o = "\u3db5\u3db5\u3db5\u3db5\u3de2\u3de2\u3de2\u3dbb\u3dff\u3df4\u3de3\u3df4\u3db8\u3de1\u3dfc\u3df6\u3dfe\u3df0\u3de7\u3dbb\u3dfb\u3df0\u3de1";
    private static String p = "\u3de6\u3de1\u3df4\u3de7\u3de1\u3dd1\u3df0\u3df9\u3df4\u3dec";
    private static String s = "\u3df1\u3df0\u3df9\u3df4\u3dec";
    private static String u = "\u3de6\u3de1\u3df0\u3de5";
    private static String v = "\u3de1\u3dfc\u3df6\u3dfe\u3df0\u3de7\u3dd1\u3dfc\u3de7\u3df0\u3df6\u3de1\u3dfc\u3dfa\u3dfb";
    private static String w = "\u3df9\u3de7";
    private static String O = "\u3df7\u3df2\u3dd6\u3dfa\u3df9\u3dfa\u3de7";
    private static String P = "\u3de6\u3df0\u3de5\u3df4\u3de7\u3df4\u3de1\u3dfa\u3de7\u3dc2\u3dfc\u3df1\u3de1\u3dfd";
    private static String sa = "\u3de6\u3df0\u3de5\u3df4\u3de7\u3df4\u3de1\u3dfa\u3de7\u3ddc\u3df8\u3df4\u3df2\u3df0";
    private static String ta = "\u3dd8\u3dfc\u3de6\u3de6\u3dfc\u3dfb\u3df2\u3db5\u3de5\u3df4\u3de7\u3df4\u3df8\u3df0\u3de1\u3df0\u3de7\u3db5\u3db2";
    private static String ua = "\u3db2";
    private static String va = "\u3dfd\u3de1\u3de1\u3de5\u3daf\u3dba\u3dba";
    private static String wa = "\u3ddc\u3dfb\u3de3\u3df4\u3df9\u3dfc\u3df1\u3db5\u3de5\u3df4\u3de7\u3df4\u3df8\u3df0\u3de1\u3df0\u3de7\u3db5\u3db2";
    private static String xa = "\u3db2\u3db5";
    private static String ya = "\u3de1\u3df0\u3ded\u3de1";
    private static String za = "\u3df3\u3df2\u3dd6\u3dfa\u3df9\u3dfa\u3de7";
    private static String Aa = "\u3df3\u3dfa\u3dfb\u3de1";
    private static String Ba = "\u3dd4\u3de7\u3dfc\u3df4\u3df9";
    private static String Ca = "\u3df1\u3dfc\u3de7\u3df0\u3df6\u3de1\u3dfc\u3dfa\u3dfb";
    private static String Da = "\u3da9\u3da9";
    private static String Ea = "\u3dab\u3dab";
    
    private void i() {
        System.out.println(l.l);
        System.out.println(l.m);
        System.out.println(l.o);
        System.out.println(l.l);
    }
    
    protected l(final Applet ia) {
        this.i();
        this.Ia = ia;
        this.j();
        (this.Ha = new s(m.b(ia, l.p, 3500))).a(m.b(ia, l.s, 15));
        this.Ha.g(m.b(ia, l.u, 1));
        if (l.w.equalsIgnoreCase(ia.getParameter(l.v))) {
            this.Ha._((byte)1);
        }
        else {
            this.Ha._((byte)(-1));
        }
        this.k();
        this.init();
    }
    
    private void k() {
        this.Ha.setBackground(m.a(this.Ia, l.O, 16777215));
        final int b = m.b(this.Ia, l.P, -1);
        if (b != 0) {
            Image _ = null;
            if (this.Ia.getParameter(l.sa) != null) {
                _ = this._(l.sa);
                final MediaTracker mediaTracker = new MediaTracker(this.Ia);
                mediaTracker.addImage(_, 0);
                try {
                    mediaTracker.waitForAll();
                }
                catch (InterruptedException ex) {}
            }
            if (_ != null || b > 0) {
                this.Ja = new v(_, b, this.height);
            }
        }
    }
    
    protected void j() {
        final Insets insets = this.Ia.getInsets();
        this.height = this.Ia.getSize().height - insets.top - insets.bottom;
    }
    
    protected abstract void init();
    
    public void start() {
        this.Ha.start();
        this.Ka.start();
    }
    
    public void stop() {
        this.Ha.stop();
        this.Ka.stop();
    }
    
    protected Image a(final String s, final MediaTracker mediaTracker, final int n) {
        final Image _ = this._(s);
        if (_ != null) {
            mediaTracker.addImage(_, n);
        }
        return _;
    }
    
    private Image _(final String s) {
        final String parameter = this.Ia.getParameter(s);
        if (parameter == null) {
            System.out.println(l.ta + s + l.ua);
            return this.Ia.createImage(1, 1);
        }
        if (parameter.startsWith(l.va)) {
            try {
                return this.Ia.getImage(new URL(parameter));
            }
            catch (MalformedURLException ex) {
                System.out.println(l.wa + s + l.xa + ex);
                return this.Ia.createImage(1, 1);
            }
        }
        return this.Ia.getImage(this.Ia.getDocumentBase(), parameter);
    }
    
    public Component _() {
        return this.Ha;
    }
    
    protected u a(final String s) {
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        hashtable.put(l.ya, s);
        final Color a = m.a(this.Ia, l.O, 16777215);
        hashtable.put(l.za, (String)m.a(this.Ia, l.za, 16711680));
        hashtable.put(l.O, (String)a);
        hashtable.put(l.Aa, (String)m._(this.Ia, l.Aa, l.Ba, 1, 12));
        return new class(this.height, this.t).a(hashtable, this.Ia);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (l.Ca.equals(actionEvent.getActionCommand())) {
            if (this.Ha.b() == -1) {
                this.Ha._((byte)1);
                ((Button)actionEvent.getSource()).setLabel(l.Da);
            }
            else {
                this.Ha._((byte)(-1));
                ((Button)actionEvent.getSource()).setLabel(l.Ea);
            }
        }
    }
    
    static {
        l.Na = e(l.Na);
        l.Oa = e(l.Oa);
        l.Pa = e(l.Pa);
        l.Qa = e(l.Qa);
        l.Ra = e(l.Ra);
        l.Sa = e(l.Sa);
        l.Ta = e(l.Ta);
        l.l = e(l.l);
        l.m = e(l.m);
        l.o = e(l.o);
        l.p = e(l.p);
        l.s = e(l.s);
        l.u = e(l.u);
        l.v = e(l.v);
        l.w = e(l.w);
        l.O = e(l.O);
        l.P = e(l.P);
        l.sa = e(l.sa);
        l.ta = e(l.ta);
        l.ua = e(l.ua);
        l.va = e(l.va);
        l.wa = e(l.wa);
        l.xa = e(l.xa);
        l.ya = e(l.ya);
        l.za = e(l.za);
        l.Aa = e(l.Aa);
        l.Ba = e(l.Ba);
        l.Ca = e(l.Ca);
        l.Da = e(l.Da);
        l.Ea = e(l.Ea);
        ba = new Object();
    }
    
    private static String e(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF3D95);
        }
        return new String(array);
    }
}
