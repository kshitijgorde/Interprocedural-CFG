import java.awt.event.WindowEvent;
import java.awt.Image;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

// 
// Decompiled by Procyon v0.5.30
// 

public class if extends JFrame implements WindowListener
{
    private static String DUa = "\ua18b\ua180\ua191\ua1e5\ua186\ua1a9\ua1aa\ua1a6\ua1ae";
    private implements EUa;
    private import FUa;
    private import GUa;
    private boolean ya;
    private Point HUa;
    private static String T = "\ua18b\ua180\ua191\ua186\ua1a9\ua1aa\ua1a6\ua1ae\ua183\ua1b7\ua1a4\ua1a8\ua1a0\ua1eb\ua1b5\ua1b7\ua1aa\ua1b5\ua1a0\ua1b7\ua1b1\ua1ac\ua1a0\ua1b6";
    private static String U = "\ua1ab\ua1a0\ua1b1\ua1a6\ua1a9\ua1aa\ua1a6\ua1ae\ua1eb\ua1a1\ua1a0\ua1a7\ua1b0\ua1a2";
    private static String V = "\ua1a3\ua1a4\ua1a9\ua1b6\ua1a0";
    private static String W = "\ua1a3\ua1b7\ua1a4\ua1a8\ua1a0\ua1eb\ua1b2\ua1ac\ua1a1\ua1b1\ua1ad";
    private static String ba = "\ua1a3\ua1b7\ua1a4\ua1a8\ua1a0\ua1eb\ua1ad\ua1a0\ua1ac\ua1a2\ua1ad\ua1b1";
    private static String ca = "\ua1a3\ua1b7\ua1a4\ua1a8\ua1a0\ua1eb\ua1a9\ua1aa\ua1a6\ua1a4\ua1b1\ua1ac\ua1aa\ua1ab\ua1eb\ua1bd";
    private static String da = "\ua1a3\ua1b7\ua1a4\ua1a8\ua1a0\ua1eb\ua1a9\ua1aa\ua1a6\ua1a4\ua1b1\ua1ac\ua1aa\ua1ab\ua1eb\ua1bc";
    private static String ea = "\ua189\ua1aa\ua1a4\ua1a1\ua1ac\ua1ab\ua1a2\ua1e5\ua1a6\ua1a9\ua1aa\ua1a6\ua1ae\ua182\ua1a9\ua1a4\ua1b6\ua1b6\ua1eb\ua1eb\ua1eb";
    private static String ta = "\ua1ac\ua1a8\ua1a4\ua1a2\ua1a0\ua1eb\ua1a6\ua1a9\ua1aa\ua1a6\ua1ae\ua1a2\ua1a9\ua1a4\ua1b6\ua1b6\ua1eb\ua1b0\ua1b7\ua1a9";
    private static String ua = "\ua1ac\ua1a8\ua1a4\ua1a2\ua1a0\ua1eb\ua1a6\ua1a9\ua1aa\ua1a6\ua1ae\ua1a2\ua1a9\ua1a4\ua1b6\ua1b6\ua1eb\ua1bd";
    private static String Sa = "\ua1ac\ua1a8\ua1a4\ua1a2\ua1a0\ua1eb\ua1a6\ua1a9\ua1aa\ua1a6\ua1ae\ua1a2\ua1a9\ua1a4\ua1b6\ua1b6\ua1eb\ua1bc";
    private static String Ta = "\ua1a6\ua1a9\ua1aa\ua1a6\ua1ae\ua182\ua1a9\ua1a4\ua1b6\ua1b6\ua1e5\ua1ac\ua1a8\ua1a4\ua1a2\ua1a0\ua1e5\ua1a9\ua1aa\ua1a4\ua1a1\ua1e5\ua1a3\ua1a4\ua1ac\ua1a9\ua1a0\ua1a1\ua1e4\ua1e5\ua18c\ua1a8\ua1a4\ua1a2\ua1a0\ua1e5\ua1ac\ua1b6\ua1e5\ua1a1\ua1ac\ua1b6\ua1a4\ua1a7\ua1a9\ua1a0\ua1a1\ua1eb";
    private static String Ua = "\ua189\ua1aa\ua1a4\ua1a1\ua1ac\ua1ab\ua1a2\ua1e5\ua1ac\ua1a6\ua1aa\ua1ab\ua1e5\ua1a3\ua1aa\ua1b7\ua1e5\ua1a8\ua1a4\ua1ac\ua1ab\ua1e5\ua1a3\ua1b7\ua1a4\ua1a8\ua1a0\ua1eb\ua1eb\ua1eb";
    private static String Va = "\ua1a3\ua1b7\ua1a4\ua1a8\ua1a0\ua1eb\ua1ac\ua1a6\ua1aa\ua1ab\ua1eb\ua1b0\ua1b7\ua1a9";
    private static String Wa = "\ua189\ua1aa\ua1a4\ua1a1\ua1ac\ua1ab\ua1a2\ua1e5\ua1ac\ua1ab\ua1a3\ua1aa\ua196\ua1a6\ua1b7\ua1a0\ua1a0\ua1ab\ua18c\ua1ab\ua1a3\ua1aa\ua1eb\ua1eb\ua1eb";
    private static String Xa = "\ua1ac\ua1ab\ua1a3\ua1aa\ua1b6\ua1a6\ua1b7\ua1a0\ua1a0\ua1ab\ua1eb\ua1a4\ua1a6\ua1b1\ua1ac\ua1b3\ua1a0\ua1a4\ua1b7\ua1a0\ua1a4\ua1eb\ua1bd";
    private static String Ya = "\ua1ac\ua1ab\ua1a3\ua1aa\ua1b6\ua1a6\ua1b7\ua1a0\ua1a0\ua1ab\ua1eb\ua1a4\ua1a6\ua1b1\ua1ac\ua1b3\ua1a0\ua1a4\ua1b7\ua1a0\ua1a4\ua1eb\ua1bc";
    private static String Za = "\ua1ac\ua1ab\ua1a3\ua1aa\ua1b6\ua1a6\ua1b7\ua1a0\ua1a0\ua1ab\ua1eb\ua1a4\ua1a6\ua1b1\ua1ac\ua1b3\ua1a0\ua1a4\ua1b7\ua1a0\ua1a4\ua1eb\ua1b2\ua1ac\ua1a1\ua1b1\ua1ad";
    private static String h = "\ua1ac\ua1ab\ua1a3\ua1aa\ua1b6\ua1a6\ua1b7\ua1a0\ua1a0\ua1ab\ua1eb\ua1a4\ua1a6\ua1b1\ua1ac\ua1b3\ua1a0\ua1a4\ua1b7\ua1a0\ua1a4\ua1eb\ua1ad\ua1a0\ua1ac\ua1a2\ua1ad\ua1b1";
    private static String i = "\ua1b6\ua1ae\ua1ac\ua1ab\ua1eb\ua1a6\ua1b7\ua1a0\ua1a4\ua1b1\ua1aa\ua1b7";
    private static String j = "\ua189\ua1aa\ua1a4\ua1a1\ua1ac\ua1ab\ua1a2\ua1e5\ua1b6\ua1b5\ua1b7\ua1ac\ua1b1\ua1a0\ua1eb\ua1eb\ua1eb";
    private static String k = "\ua1ac\ua1a8\ua1a4\ua1a2\ua1a0\ua1eb\ua1a1\ua1ac\ua1a2\ua1ac\ua1b1\ua1a4\ua1a9\ua1a3\ua1aa\ua1ab\ua1b1\ua1b6\ua1eb\ua1a3\ua1aa\ua1ab\ua1b1\ua1eb\ua1b2\ua1ac\ua1a1\ua1b1\ua1ad";
    private static String l = "\ua1ac\ua1a8\ua1a4\ua1a2\ua1a0\ua1eb\ua1a1\ua1ac\ua1a2\ua1ac\ua1b1\ua1a4\ua1a9\ua1a3\ua1aa\ua1ab\ua1b1\ua1b6\ua1eb\ua1b6\ua1b5\ua1a4\ua1a6\ua1a0\ua1eb\ua1b2\ua1ac\ua1a1\ua1b1\ua1ad";
    private static String m = "\ua1ac\ua1a8\ua1a4\ua1a2\ua1a0\ua1eb\ua1a1\ua1ac\ua1a2\ua1ac\ua1b1\ua1a4\ua1a9\ua1a3\ua1aa\ua1ab\ua1b1\ua1b6\ua1eb\ua1b0\ua1b7\ua1a9";
    private static String n = "\ua1a1\ua1ac\ua1a2\ua1ac\ua1b1\ua1a4\ua1a9\ua1a1\ua1ac\ua1b6\ua1b5\ua1a9\ua1a4\ua1bc\ua1eb\ua1bd";
    private static String o = "\ua1a1\ua1ac\ua1a2\ua1ac\ua1b1\ua1a4\ua1a9\ua1a1\ua1ac\ua1b6\ua1b5\ua1a9\ua1a4\ua1bc\ua1eb\ua1bc";
    private static String p = "\ua1a1\ua1ac\ua1a2\ua1ac\ua1b1\ua1a4\ua1a9\ua1a1\ua1ac\ua1b6\ua1b5\ua1a9\ua1a4\ua1bc\ua1eb\ua1b2\ua1ac\ua1a1\ua1b1\ua1ad";
    private static String q = "\ua1a1\ua1ac\ua1a2\ua1ac\ua1b1\ua1a4\ua1a9\ua1a1\ua1ac\ua1b6\ua1b5\ua1a9\ua1a4\ua1bc\ua1eb\ua1ad\ua1a0\ua1ac\ua1a2\ua1ad\ua1b1";
    private static String r = "\ua1a1\ua1ac\ua1a2\ua1ac\ua1b1\ua1a4\ua1a9\ua1a1\ua1ac\ua1b6\ua1b5\ua1a9\ua1a4\ua1bc\ua1eb\ua1a6\ua1ad\ua1a4\ua1ab\ua1a2\ua1a0\ua1b7\ua1a4\ua1b1\ua1a0";
    private static String s = "\ua189\ua1aa\ua1a4\ua1a1\ua1ac\ua1ab\ua1a2\ua1e5\ua1a6\ua1a9\ua1aa\ua1a6\ua1ae\ua183\ua1a4\ua1a6\ua1a0\ua1eb\ua1eb\ua1eb";
    private static String t = "\ua1ac\ua1a8\ua1a4\ua1a2\ua1a0\ua1eb\ua1a6\ua1a9\ua1aa\ua1a6\ua1ae\ua1a3\ua1a4\ua1a6\ua1a0\ua1eb\ua1b0\ua1b7\ua1a9";
    private static String u = "\ua1ac\ua1a8\ua1a4\ua1a2\ua1a0\ua1eb\ua1a6\ua1a9\ua1aa\ua1a6\ua1ae\ua1a3\ua1a4\ua1a6\ua1a0\ua1eb\ua1bd";
    private static String v = "\ua1ac\ua1a8\ua1a4\ua1a2\ua1a0\ua1eb\ua1a6\ua1a9\ua1aa\ua1a6\ua1ae\ua1a3\ua1a4\ua1a6\ua1a0\ua1eb\ua1bc";
    private static String w = "\ua189\ua1aa\ua1a4\ua1a1\ua1ac\ua1ab\ua1a2\ua1e5\ua1a6\ua1a9\ua1aa\ua1a6\ua1ae\ua183\ua1b7\ua1a4\ua1a8\ua1a0\ua1eb\ua1eb\ua1eb";
    private static String x = "\ua1ac\ua1a8\ua1a4\ua1a2\ua1a0\ua1eb\ua1a6\ua1a9\ua1aa\ua1a6\ua1ae\ua1a3\ua1b7\ua1a4\ua1a8\ua1a0\ua1eb\ua1b0\ua1b7\ua1a9";
    private static String y = "\ua1ac\ua1a8\ua1a4\ua1a2\ua1a0\ua1eb\ua1a6\ua1a9\ua1aa\ua1a6\ua1ae\ua1a3\ua1b7\ua1a4\ua1a8\ua1a0\ua1eb\ua1a4\ua1ab\ua1a6\ua1ad\ua1aa\ua1b7\ua1eb\ua1bd";
    private static String z = "\ua1ac\ua1a8\ua1a4\ua1a2\ua1a0\ua1eb\ua1a6\ua1a9\ua1aa\ua1a6\ua1ae\ua1a3\ua1b7\ua1a4\ua1a8\ua1a0\ua1eb\ua1a4\ua1ab\ua1a6\ua1ad\ua1aa\ua1b7\ua1eb\ua1bc";
    private static String A = "\ua189\ua1aa\ua1a4\ua1a1\ua1ac\ua1ab\ua1a2\ua1e5\ua1a1\ua1a0\ua1a2\ua1b7\ua1a0\ua1a0\ua18d\ua1a4\ua1ab\ua1a1\ua1eb\ua1eb\ua1eb";
    private static String B = "\ua1ac\ua1a8\ua1a4\ua1a2\ua1a0\ua1eb\ua1a1\ua1a0\ua1a2\ua1b7\ua1a0\ua1a0\ua1ad\ua1a4\ua1ab\ua1a1\ua1eb\ua1b0\ua1b7\ua1a9";
    private static String C = "\ua1ac\ua1a8\ua1a4\ua1a2\ua1a0\ua1eb\ua1a1\ua1a0\ua1a2\ua1b7\ua1a0\ua1a0\ua1ad\ua1a4\ua1ab\ua1a1\ua1eb\ua1a4\ua1ab\ua1a6\ua1ad\ua1aa\ua1b7\ua1eb\ua1bd";
    private static String D = "\ua1ac\ua1a8\ua1a4\ua1a2\ua1a0\ua1eb\ua1a1\ua1a0\ua1a2\ua1b7\ua1a0\ua1a0\ua1ad\ua1a4\ua1ab\ua1a1\ua1eb\ua1a4\ua1ab\ua1a6\ua1ad\ua1aa\ua1b7\ua1eb\ua1bc";
    private static String E = "\ua189\ua1aa\ua1a4\ua1a1\ua1ac\ua1ab\ua1a2\ua1e5\ua1a8\ua1ac\ua1ab\ua1b0\ua1b1\ua1a0\ua18d\ua1a4\ua1ab\ua1a1\ua1eb\ua1eb\ua1eb";
    private static String IUa = "\ua1ac\ua1a8\ua1a4\ua1a2\ua1a0\ua1eb\ua1a8\ua1ac\ua1ab\ua1b0\ua1b1\ua1a0\ua1ad\ua1a4\ua1ab\ua1a1\ua1eb\ua1b0\ua1b7\ua1a9";
    private static String JUa = "\ua1ac\ua1a8\ua1a4\ua1a2\ua1a0\ua1eb\ua1a8\ua1ac\ua1ab\ua1b0\ua1b1\ua1a0\ua1ad\ua1a4\ua1ab\ua1a1\ua1eb\ua1a4\ua1ab\ua1a6\ua1ad\ua1aa\ua1b7\ua1eb\ua1bd";
    private static String KUa = "\ua1ac\ua1a8\ua1a4\ua1a2\ua1a0\ua1eb\ua1a8\ua1ac\ua1ab\ua1b0\ua1b1\ua1a0\ua1ad\ua1a4\ua1ab\ua1a1\ua1eb\ua1a4\ua1ab\ua1a6\ua1ad\ua1aa\ua1b7\ua1eb\ua1bc";
    private static String LUa = "\ua195\ua1a4\ua1b7\ua1b6\ua1ac\ua1ab\ua1a2\ua1e5\ua1a7\ua1a2\ua186\ua1aa\ua1a9\ua1aa\ua1b7\ua1eb\ua1eb\ua1eb";
    private static String MUa = "\ua1a3\ua1b7\ua1a4\ua1a8\ua1a0\ua1eb\ua1a7\ua1a4\ua1a6\ua1ae\ua1a2\ua1b7\ua1aa\ua1b0\ua1ab\ua1a1\ua1eb\ua1a6\ua1aa\ua1a9\ua1aa\ua1b7\ua1eb\ua1b7";
    private static String NUa = "\ua1a3\ua1b7\ua1a4\ua1a8\ua1a0\ua1eb\ua1a7\ua1a4\ua1a6\ua1ae\ua1a2\ua1b7\ua1aa\ua1b0\ua1ab\ua1a1\ua1eb\ua1a6\ua1aa\ua1a9\ua1aa\ua1b7\ua1eb\ua1a2";
    private static String OUa = "\ua1a3\ua1b7\ua1a4\ua1a8\ua1a0\ua1eb\ua1a7\ua1a4\ua1a6\ua1ae\ua1a2\ua1b7\ua1aa\ua1b0\ua1ab\ua1a1\ua1eb\ua1a6\ua1aa\ua1a9\ua1aa\ua1b7\ua1eb\ua1a7";
    private static String PUa = "\ua1a7\ua1a2\ua186\ua1aa\ua1a9\ua1aa\ua1b7\ua1ff\ua1e5\ua1b7\ua1f8";
    private static String QUa = "\ua1e9\ua1e5\ua1a2\ua1f8";
    private static String RUa = "\ua1e9\ua1e5\ua1a7\ua1f8";
    private static String SUa = "\ua195\ua1b7\ua1aa\ua1a2\ua1b7\ua1a4\ua1a8\ua1e5\ua1a0\ua1b7\ua1b7\ua1aa\ua1b7\ua1e9\ua1e5\ua1a6\ua1a4\ua1ab\ua1ab\ua1aa\ua1b1\ua1e5\ua1a6\ua1aa\ua1ab\ua1b1\ua1ac\ua1ab\ua1b0\ua1a0\ua1ff\ua1e5";
    private static String TUa = "\ua190\ua1ab\ua1a0\ua1bd\ua1b5\ua1a0\ua1a6\ua1b1\ua1a0\ua1a1\ua1e5\ua1a0\ua1b7\ua1b7\ua1aa\ua1b7\ua1e5\ua1aa\ua1a6\ua1a6\ua1b0\ua1b7\ua1b7\ua1a0\ua1a1\ua1e9\ua1e5\ua1a6\ua1a4\ua1ab\ua1ab\ua1aa\ua1b1\ua1e5\ua1a6\ua1aa\ua1ab\ua1b1\ua1ac\ua1ab\ua1b0\ua1a0\ua1ff\ua1e5";
    private static String UUa = "\ua186\ua1a0\ua1ab\ua1b1\ua1a0\ua1b7";
    private static String VUa = "\ua18b\ua180\ua191\ua1e5\ua186\ua1a9\ua1aa\ua1a6\ua1ae";
    private static String WUa = "\ua192\ua1ac\ua1ab\ua1a1\ua1aa\ua1b2\ua1e5\ua1ad\ua1a4\ua1b6\ua1e5\ua1a7\ua1a0\ua1a0\ua1ab\ua1e5\ua1a6\ua1a9\ua1aa\ua1b6\ua1a0\ua1a1\ua1e9\ua1e5\ua1b6\ua1aa\ua1e5\ua1b5\ua1b7\ua1aa\ua1a2\ua1b7\ua1a4\ua1a8\ua1e5\ua1b2\ua1ac\ua1a9\ua1a9\ua1e5\ua1a0\ua1bd\ua1ac\ua1b1\ua1e4";
    private static String XUa = "\ua196\ua1a4\ua1b3\ua1ac\ua1ab\ua1a2\ua1e5\ua1a6\ua1b0\ua1b7\ua1b7\ua1a0\ua1ab\ua1b1\ua1e5\ua1a3\ua1b7\ua1a4\ua1a8\ua1a0\ua1e5\ua1a9\ua1aa\ua1a6\ua1a4\ua1b1\ua1ac\ua1aa\ua1ab\ua1eb\ua1eb\ua1eb";
    private static String YUa = "";
    private static String ZUa = "\ua196\ua1a4\ua1b3\ua1a0\ua1e5\ua1b6\ua1b0\ua1a6\ua1a6\ua1a0\ua1a0\ua1a1\ua1a0\ua1a1\ua1e4";
    private static String _Va = "\ua196\ua1a4\ua1b3\ua1a0\ua1e5\ua1a3\ua1a4\ua1ac\ua1a9\ua1a0\ua1a1\ua1ff\ua1e5";
    private static String aVa = "\ua1e5\ua1e8\ua1e5";
    
    public if(final String s) {
        this.FUa = null;
        this.GUa = null;
        this.ya = true;
        this.HUa = new Point();
        interface _ = null;
        interface _2 = null;
        interface _3 = null;
        interface _4 = null;
        interface _5 = null;
        final instanceof instanceof1 = new instanceof();
        native native1 = null;
        null null = null;
        Image _6 = null;
        int int1 = 0;
        int int2 = 0;
        Color color = null;
        try {
            this.FUa = new import(s, this);
            this.GUa = new import(if.T);
            if (this.FUa.getProperty(if.U).equalsIgnoreCase(if.V)) {
                this.ya = false;
            }
            int1 = Integer.parseInt(this.FUa.getProperty(if.W));
            int2 = Integer.parseInt(this.FUa.getProperty(if.ba));
            if (this.GUa.getProperty(if.ca) == null || this.GUa.getProperty(if.da) == null) {
                this.HUa.setLocation(0, 0);
            }
            else {
                this.HUa.setLocation(Double.parseDouble(this.GUa.getProperty(if.ca)), Double.parseDouble(this.GUa.getProperty(if.da)));
            }
            this.b(if.ea);
            try {
                _2 = this.FUa._(if.ta, if.ua, if.Sa, this);
            }
            catch (IOException ex3) {
                this.b(if.Ta);
                _2 = null;
            }
            this.b(if.Ua);
            _6 = instanceof1._(this.FUa.getProperty(if.Va), this);
            this.b(if.Wa);
            native1 = new native(this.ya, Integer.parseInt(this.FUa.getProperty(if.Xa)), Integer.parseInt(this.FUa.getProperty(if.Ya)), Integer.parseInt(this.FUa.getProperty(if.Za)), Integer.parseInt(this.FUa.getProperty(if.h)), this.FUa.getProperty(if.i), this);
            this.b(if.j);
            null = new null(this.ya, new new(this.ya, Integer.parseInt(this.FUa.getProperty(if.k)), Integer.parseInt(this.FUa.getProperty(if.l)), this.FUa._(if.m, this), this), Integer.parseInt(this.FUa.getProperty(if.n)), Integer.parseInt(this.FUa.getProperty(if.o)), Integer.parseInt(this.FUa.getProperty(if.p)), Integer.parseInt(this.FUa.getProperty(if.q)), Integer.parseInt(this.FUa.getProperty(if.r)), this);
            this.b(if.s);
            _ = this.FUa._(if.t, if.u, if.v, this);
            this.b(if.w);
            _3 = this.FUa._(if.x, if.y, if.z, this);
            this.b(if.A);
            _4 = this.FUa._(if.B, if.C, if.D, this);
            this.b(if.E);
            _5 = this.FUa._(if.IUa, if.JUa, if.KUa, this);
            this.b(if.LUa);
            final int int3 = Integer.parseInt(this.FUa.getProperty(if.MUa));
            final int int4 = Integer.parseInt(this.FUa.getProperty(if.NUa));
            final int int5 = Integer.parseInt(this.FUa.getProperty(if.OUa));
            color = new Color(int3, int4, int5);
            this.b(String.valueOf(String.valueOf(new StringBuffer(if.PUa).append(int3).append(if.QUa).append(int4).append(if.RUa).append(int5))));
        }
        catch (IOException ex) {
            this.b(if.SUa.concat(String.valueOf(String.valueOf(ex.toString()))));
            System.exit(1);
        }
        catch (Exception ex2) {
            this.b(if.TUa.concat(String.valueOf(String.valueOf(ex2.toString()))));
            System.exit(1);
        }
        this.EUa = new implements(this.ya, _2, _, _3, _4, _5, native1, null, color);
        this.getContentPane().add(this.EUa, if.UUa);
        this.addWindowListener(this);
        this.setResizable(false);
        this.setSize(int1, int2);
        this.setLocation(this.HUa);
        this.setTitle(if.VUa);
        this.setIconImage(_6);
        this.validate();
        this.show();
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.EUa.a(false);
        this.b(if.WUa);
        this.b(if.XUa);
        try {
            this.GUa._(if.ca, if.YUa.concat(String.valueOf(String.valueOf(this.getLocationOnScreen().getX()))));
            this.GUa._(if.da, if.YUa.concat(String.valueOf(String.valueOf(this.getLocationOnScreen().getY()))));
            this.GUa.b(this);
            this.b(if.ZUa);
        }
        catch (IOException ex) {
            this.b(if._Va.concat(String.valueOf(String.valueOf(ex.toString()))));
        }
        this.dispose();
        System.exit(0);
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
        this.EUa.a(true);
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
        this.EUa.a(false);
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
        this.EUa.a(true);
    }
    
    private void b(final String s) {
        if (this.ya) {
            System.out.println(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.i()))).append(if.aVa).append(s))));
        }
    }
    
    private String i() {
        return this.getClass().getName();
    }
    
    static {
        if.DUa = f(if.DUa);
        if.T = f(if.T);
        if.U = f(if.U);
        if.V = f(if.V);
        if.W = f(if.W);
        if.ba = f(if.ba);
        if.ca = f(if.ca);
        if.da = f(if.da);
        if.ea = f(if.ea);
        if.ta = f(if.ta);
        if.ua = f(if.ua);
        if.Sa = f(if.Sa);
        if.Ta = f(if.Ta);
        if.Ua = f(if.Ua);
        if.Va = f(if.Va);
        if.Wa = f(if.Wa);
        if.Xa = f(if.Xa);
        if.Ya = f(if.Ya);
        if.Za = f(if.Za);
        if.h = f(if.h);
        if.i = f(if.i);
        if.j = f(if.j);
        if.k = f(if.k);
        if.l = f(if.l);
        if.m = f(if.m);
        if.n = f(if.n);
        if.o = f(if.o);
        if.p = f(if.p);
        if.q = f(if.q);
        if.r = f(if.r);
        if.s = f(if.s);
        if.t = f(if.t);
        if.u = f(if.u);
        if.v = f(if.v);
        if.w = f(if.w);
        if.x = f(if.x);
        if.y = f(if.y);
        if.z = f(if.z);
        if.A = f(if.A);
        if.B = f(if.B);
        if.C = f(if.C);
        if.D = f(if.D);
        if.E = f(if.E);
        if.IUa = f(if.IUa);
        if.JUa = f(if.JUa);
        if.KUa = f(if.KUa);
        if.LUa = f(if.LUa);
        if.MUa = f(if.MUa);
        if.NUa = f(if.NUa);
        if.OUa = f(if.OUa);
        if.PUa = f(if.PUa);
        if.QUa = f(if.QUa);
        if.RUa = f(if.RUa);
        if.SUa = f(if.SUa);
        if.TUa = f(if.TUa);
        if.UUa = f(if.UUa);
        if.VUa = f(if.VUa);
        if.WUa = f(if.WUa);
        if.XUa = f(if.XUa);
        if.YUa = f(if.YUa);
        if.ZUa = f(if.ZUa);
        if._Va = f(if._Va);
        if.aVa = f(if.aVa);
        if.DUa = if.VUa;
    }
    
    private static String f(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFFA1C5);
        }
        return new String(array);
    }
}
