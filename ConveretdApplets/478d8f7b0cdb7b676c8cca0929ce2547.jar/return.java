import java.util.Date;
import java.util.TimeZone;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

public class return
{
    private public va;
    private private wa;
    private ImageObserver xa;
    private double Y;
    private boolean ya;
    private boolean za;
    private Thread Aa;
    private static String T = "\u4c7d\u4c5e\u4c52\u4c50\u4c5d\u4c11\u4c45\u4c58\u4c5c\u4c54\u4c11\u4c4b\u4c5e\u4c5f\u4c54\u4c11\u4c58\u4c42\u4c0b\u4c11";
    private static String U = "\u4c7d\u4c5e\u4c52\u4c50\u4c5d\u4c11\u4c45\u4c58\u4c5c\u4c54\u4c11\u4c5e\u4c57\u4c57\u4c42\u4c54\u4c45\u4c11\u4c58\u4c42\u4c0b\u4c11";
    private static String V = "\u4c7d\u4c5e\u4c52\u4c50\u4c5d\u4c11\u4c45\u4c58\u4c5c\u4c54\u4c11\u4c5e\u4c57\u4c57\u4c42\u4c54\u4c45\u4c11\u4c50\u4c42\u4c11\u4c50\u4c5f\u4c56\u4c5d\u4c54\u4c11\u4c58\u4c42\u4c0b\u4c11";
    private static String W = "\u4c11\u4c1c\u4c11";
    
    public return(final interface interface1, final ImageObserver xa, final private wa, final boolean ya) {
        this.za = true;
        this.ya = ya;
        this.xa = xa;
        this.wa = wa;
        this.va = new public(interface1, 2, this.xa);
        this.Y = this.a();
        this.Aa = new Thread(new super(this, this.Y));
    }
    
    public void _(final Graphics2D graphics2D) {
        this.va._(graphics2D);
    }
    
    public void start() {
        this.Aa.start();
    }
    
    private double a() {
        double n = 0.0;
        final TimeZone default1 = TimeZone.getDefault();
        this.b(return.T.concat(String.valueOf(String.valueOf(default1.getDisplayName()))));
        final long n2 = default1.getOffset(new Date().getTime()) / 3600000;
        this.b(return.U.concat(String.valueOf(String.valueOf(n2))));
        if (n2 != 0) {
            n = n2 * 15;
        }
        final double n3 = n * -1;
        this.b(return.V.concat(String.valueOf(String.valueOf(n3))));
        return n3;
    }
    
    private void b(final String s) {
        if (this.ya) {
            System.out.println(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.i()))).append(return.W).append(s))));
        }
    }
    
    private String i() {
        return this.getClass().getName();
    }
    
    static public a(final return return1) {
        return return1.va;
    }
    
    static void b(final return return1, final String s) {
        return1.b(s);
    }
    
    static Thread a(final return return1, final Thread aa) {
        return return1.Aa = aa;
    }
    
    static private b(final return return1) {
        return return1.wa;
    }
    
    private static String i(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u4c31');
        }
        return new String(array);
    }
    
    static {
        return.T = i(return.T);
        return.U = i(return.U);
        return.V = i(return.V);
        return.W = i(return.W);
    }
}
