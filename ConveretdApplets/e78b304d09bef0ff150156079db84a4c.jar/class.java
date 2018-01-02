import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class class
{
    public static final int Ca = 0;
    public static final int Da = 1;
    public static final int Ea = 2;
    public static final int Fa = 3;
    public static final int BLACK = 0;
    public static final int RED = 1;
    private Image Ga;
    private Rectangle Ha;
    private boolean Ia;
    private int Ja;
    private int Ka;
    private int La;
    private int Ma;
    private int Na;
    private static String qb = "\ue1bc\ue194\ue192\ue18e\ue193\ue182\ue1da";
    private static String rb = "\ue1cb\ue18e\ue183\ue1da";
    private static String sb = "\ue1cb\ue184\ue18e\ue183\ue1da";
    private static String tb = "\ue1ba";
    
    public class(final Image ga, final int ma, final int na, final Dimension dimension, final int la, final int ka, final int ja) {
        this.Ma = ma;
        this.Na = na;
        this.Ga = ga;
        this.La = la;
        this.Ka = ka;
        this.Ja = ja;
        this.Ha = new Rectangle(ma, na, dimension.width, dimension.height);
    }
    
    public final int _() {
        return this.Ja;
    }
    
    public final int getID() {
        return this.La;
    }
    
    public final int a() {
        return this.Ka;
    }
    
    public final Image b() {
        return this.Ga;
    }
    
    public void c() {
        this.Ha.x = this.Ma;
        this.Ha.y = this.Na;
    }
    
    public void a(final int x, final int y) {
        this.Ma = this.Ha.x;
        this.Na = this.Ha.y;
        this.Ha.x = x;
        this.Ha.y = y;
    }
    
    public final boolean g() {
        return this.Ia;
    }
    
    public final void a(final boolean ia) {
        this.Ia = ia;
    }
    
    public boolean contains(final int n, final int n2) {
        return this.Ha.contains(n, n2);
    }
    
    public Rectangle getBounds() {
        return this.Ha;
    }
    
    public String toString() {
        return String.valueOf(this.getClass().getName()) + class.qb + this.Ja + class.rb + this.La + class.sb + this.Ka + class.tb;
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFFE1E7);
        }
        return new String(array);
    }
    
    static {
        class.qb = a(class.qb);
        class.rb = a(class.rb);
        class.sb = a(class.sb);
        class.tb = a(class.tb);
    }
}
