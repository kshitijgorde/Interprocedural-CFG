import java.awt.MediaTracker;
import java.applet.Applet;
import java.awt.Component;
import java.awt.Toolkit;

// 
// Decompiled by Procyon v0.5.30
// 

public class l
{
    public boolean h;
    static final byte[] f;
    static final byte[] void;
    public ak try;
    public int new;
    public static float l;
    public Toolkit byte;
    public d k;
    public Component e;
    public k n;
    public y o;
    public Applet c;
    public static final double for = 6.283185307179586;
    public static final double if = 1.5707963267948966;
    public MediaTracker b;
    public an d;
    public boolean case;
    public boolean j;
    public static int int;
    public boolean p;
    public static String long;
    public int m;
    public float g;
    public static boolean a;
    float[][] goto;
    float[][] else;
    float[][] char;
    float[][] do;
    float[] i;
    
    static {
        f = new byte[] { 67, 97, 110, 39, 116, 32, 111, 112, 101, 110, 32, 99, 117, 114, 114, 101, 110, 116, 32, 112, 97, 99, 107, 97, 103, 101, 32, 98, 101, 99, 97, 117, 115, 101, 32, 111, 102, 32, 116, 105, 109, 101, 32, 114, 101, 115, 116, 114, 105, 99, 116, 105, 111, 110, 46 };
        void = new byte[] { 67, 97, 110, 39, 116, 32, 100, 101, 99, 114, 121, 112, 116, 32 };
        l.l = 0.9f;
        l.int = 0;
        l.long = "ImmerId";
        l.a = true;
    }
    
    public void a() {
        if (this.d != null) {
            this.d.a();
        }
        this.d = null;
        this.b = null;
        this.try = null;
        this.byte = null;
        this.k = null;
        this.e = null;
        if (this.n != null) {
            this.n.a();
        }
        this.n = null;
        if (this.o != null) {
            this.o.a();
        }
        this.o = null;
        this.c = null;
    }
    
    public static int a(final String s) {
        final String lowerCase = s.toLowerCase();
        if (s.startsWith("0x") | s.startsWith("#")) {
            int n = 0;
            String s2;
            if (s.startsWith("0x")) {
                s2 = lowerCase.substring(2);
            }
            else {
                s2 = lowerCase.substring(1);
            }
            for (int i = 0; i < s2.length(); ++i) {
                if (s2.charAt(i) >= 'a' && s2.charAt(i) <= 'f') {
                    n = (n << 4) + s2.charAt(i) - 97 + 10;
                }
                else if (s2.charAt(i) >= '0' && s2.charAt(i) <= '9') {
                    n = (n << 4) + s2.charAt(i) - 48;
                }
            }
            return n;
        }
        try {
            return Integer.parseInt(s);
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    l() {
        this.h = false;
        this.new = -2;
        this.case = false;
        this.j = true;
        this.p = false;
        this.m = 0;
        this.g = 100.0f;
        this.goto = this.if();
        this.else = this.if();
        this.char = this.if();
        this.do = this.if();
    }
    
    float[][] if() {
        return new float[3][3];
    }
    
    void a(final float n, final float n2, final float n3, final float[][] array) {
        this.a(this.goto);
        this.a(this.else);
        this.a(this.char);
        this.goto[0][0] = 1.0f;
        this.goto[2][2] = (float)Math.cos(-n3);
        this.goto[1][1] = this.goto[2][2];
        this.goto[1][2] = (float)Math.sin(-n3);
        this.goto[2][1] = -this.goto[1][2];
        this.else[1][1] = 1.0f;
        this.else[2][2] = (float)Math.cos(n2);
        this.else[0][0] = this.else[2][2];
        this.else[0][2] = (float)Math.sin(n2);
        this.else[2][0] = -this.else[0][2];
        this.char[2][2] = 1.0f;
        this.char[1][1] = (float)Math.cos(n);
        this.char[0][0] = this.char[1][1];
        this.char[0][1] = (float)Math.sin(n);
        this.char[1][0] = -this.char[0][1];
        this.a(this.char, this.else, this.do);
        this.a(this.do, this.goto, array);
    }
    
    void a(final float[][] array) {
        array[1][0] = 0.0f;
        array[2][0] = 0.0f;
        array[0][1] = 0.0f;
        array[0][2] = 0.0f;
    }
    
    void a(final float[][] array, final float[][] array2, final float[][] array3) {
        array3[0][0] = array[0][0] * array2[0][0] + array[1][0] * array2[0][1] + array[2][0] * array2[0][2];
        array3[1][0] = array[0][0] * array2[1][0] + array[1][0] * array2[1][1] + array[2][0] * array2[1][2];
        array3[2][0] = array[0][0] * array2[2][0] + array[1][0] * array2[2][1] + array[2][0] * array2[2][2];
        array3[0][1] = array[0][1] * array2[0][0] + array[1][1] * array2[0][1] + array[2][1] * array2[0][2];
        array3[1][1] = array[0][1] * array2[1][0] + array[1][1] * array2[1][1] + array[2][1] * array2[1][2];
        array3[2][1] = array[0][1] * array2[2][0] + array[1][1] * array2[2][1] + array[2][1] * array2[2][2];
        array3[0][2] = array[0][2] * array2[0][0] + array[1][2] * array2[0][1] + array[2][2] * array2[0][2];
        array3[1][2] = array[0][2] * array2[1][0] + array[1][2] * array2[1][1] + array[2][2] * array2[1][2];
        array3[2][2] = array[0][2] * array2[2][0] + array[1][2] * array2[2][1] + array[2][2] * array2[2][2];
    }
    
    void a(final float[][] array, final al al, final al al2) {
        final float try1 = array[0][0] * al.try + array[1][0] * al.if + array[2][0] * al.byte;
        final float if1 = array[0][1] * al.try + array[1][1] * al.if + array[2][1] * al.byte;
        final float byte1 = array[0][2] * al.try + array[1][2] * al.if + array[2][2] * al.byte;
        al2.try = try1;
        al2.if = if1;
        al2.byte = byte1;
    }
    
    public float a(float n, final float n2) {
        while (n - n2 >= 3.141592653589793) {
            n -= 6.283185307179586;
        }
        while (n - n2 <= -3.141592653589793) {
            n += 6.283185307179586;
        }
        return n;
    }
    
    public void if(final w[] array, final int n, final int n2) {
        int i = n;
        int n3 = n2;
        if (n2 > n) {
            final int new1 = array[(n + n2) / 2].new;
            while (i <= n3) {
                while (i < n2) {
                    if (array[i].new >= new1) {
                        break;
                    }
                    ++i;
                }
                while (n3 > n && array[n3].new > new1) {
                    --n3;
                }
                if (i <= n3) {
                    this.a(array, i, n3);
                    ++i;
                    --n3;
                }
            }
            if (n < n3) {
                this.if(array, n, n3);
            }
            if (i < n2) {
                this.if(array, i, n2);
            }
        }
    }
    
    private void a(final w[] array, final int n, final int n2) {
        final w w = array[n];
        array[n] = array[n2];
        array[n2] = w;
    }
}
