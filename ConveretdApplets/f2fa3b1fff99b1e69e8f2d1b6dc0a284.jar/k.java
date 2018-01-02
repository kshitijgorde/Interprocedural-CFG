import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.text.NumberFormat;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class k extends n
{
    public static Color d;
    public static Color C;
    public static int void;
    public static Image s;
    public static Image g;
    public static Image byte;
    public static String w;
    public static int m;
    public static boolean y;
    public static boolean f;
    public static int goto;
    public static int o;
    public static int x;
    private String[] null;
    private int[] i;
    public String e;
    public String c;
    public String h;
    public String l;
    public Color[] u;
    public Font p;
    public byte z;
    public boolean char;
    public String j;
    public String q;
    private boolean r;
    private boolean n;
    private FontMetrics else;
    private int B;
    private int v;
    private DCQuoteTable try;
    public int t;
    public int b;
    public boolean A;
    public double k;
    public double long;
    private NumberFormat case;
    
    public k(final Font p2, final DCQuoteTable try1) {
        this.e = "";
        this.c = "";
        this.char = false;
        this.r = false;
        this.n = false;
        this.B = 0;
        this.case = NumberFormat.getInstance();
        this.h = "Default";
        this.p = p2;
        this.try = try1;
        this.q = "";
        this.u = new Color[7];
        this.A = false;
    }
    
    public synchronized void a(int n, final int b, final int n2, final int n3) {
        int n4 = 1;
        final int n5 = this.else.getHeight() - 2;
        if (this.B != b) {
            this.a(super.a);
        }
        this.B = b;
        final int a = f.a();
        super.a.setClip(0, 0, b, this.v + a);
        super.a.setColor(this.u[2]);
        super.a.fillRect(0, this.v, b, a);
        if (this.r) {
            super.a.setColor(this.u[4]);
        }
        else {
            super.a.setColor(this.u[2]);
        }
        super.a.fillRect(0, 0, b, this.v);
        if (this.try.Z) {
            ++n;
        }
        if (this.null != null) {
            for (int i = 0; i < this.try.P.length + (k.f ? 2 : 0); ++i) {
                this.a(i, n, -1);
            }
        }
        super.a.setClip(0, 0, b, this.v + a);
        if (this.try.Z) {
            super.a.setColor(this.u[3]);
            super.a.drawRect(0, 0, b - 1, this.v - 1);
            ++n4;
        }
        if (this.try.i) {
            super.a.setXORMode(this.try.t[1]);
            super.a.setColor(this.try.t[0]);
            final int n6 = (super.for.y + this.try.I + a) % 8;
            if (n2 > 0) {
                super.a.drawLine(n2, 0, n2, this.v + a);
            }
            super.a.setPaintMode();
        }
        Color color;
        if (this.n) {
            color = this.u[4];
        }
        else {
            color = this.u[0];
        }
        if (!this.e.equals("#")) {
            if (this.z == 1) {
                if (this.try.X.equals("BACKGROUND")) {
                    super.a.setColor(this.u[6]);
                    super.a.fillRect(0, 1, Math.min(this.else.stringWidth(this.e) + 1, this.try.goto - 1), this.v - 2);
                }
                if (this.try.X.equals("FOREGROUND")) {
                    color = this.u[6];
                }
            }
            if (!this.c.equals("#")) {
                super.a.setClip(0, 0, this.try.goto - 1, this.v);
            }
            if (this.e.equals("*")) {
                this.a(super.a, k.w, n4, n5, color, this.p);
            }
            else {
                this.a(super.a, this.e, n4, n5, color, this.p);
            }
        }
    }
    
    public void a(int goto1, final int n, int if1) {
        boolean b = false;
        if (k.f && (goto1 == k.o || goto1 == k.x)) {
            goto1 = k.goto;
        }
        b b2 = this.try.P[goto1];
        if (k.f && goto1 == k.goto) {
            b2 = this.try.P[k.goto];
            this.null[goto1] = this.a(this.null[k.o], this.null[k.x]);
        }
        final String s = this.null[goto1];
        final int n2 = this.else.getHeight() - 2;
        final int n3 = b2.new + n;
        final int case1 = b2.case();
        int n4 = 0;
        int n5 = 0;
        if1 = b2.if();
        if (n3 + case1 > this.try.goto) {
            int stringWidth = this.else.stringWidth(s);
            if (this.p.isItalic()) {
                stringWidth += Math.max(this.else.getMaxAdvance() / 4, 2);
            }
            int n6;
            if (!s.equals("N/A") && !s.equals(k.w) && !s.equals("")) {
                b = true;
                n6 = stringWidth + this.else.stringWidth(b2.long);
            }
            else {
                n6 = stringWidth;
            }
            int max = 0;
            final int max2 = Math.max(n3, this.try.goto);
            super.a.setClip(max2, 0, n3 + case1 - max2, this.v);
            if (b2.a() != 0) {
                max = Math.max(k.g.getWidth(null), k.byte.getWidth(null));
            }
            int n7;
            if (max != 0) {
                n7 = n6 + max + 4;
            }
            else {
                n7 = n6;
            }
            switch (if1) {
                case -1: {
                    n4 = 2;
                    n5 = n4 + n6 + 2;
                    break;
                }
                case 0: {
                    n4 = (case1 - n7) / 2;
                    n5 = n4 + n6 + 2;
                    break;
                }
                case 1: {
                    n5 = case1 - 2 - max;
                    n4 = n5 - 2 - n6;
                    break;
                }
            }
            final int char1 = b2.char();
            Color color = Color.lightGray;
            if (b2.a() != 0) {
                switch (this.i[goto1]) {
                    case 1: {
                        super.a.drawImage(k.g, n3 + n5, (this.v - k.g.getHeight(null)) / 2, null);
                        if (char1 == -1) {
                            color = k.d;
                            break;
                        }
                        break;
                    }
                    case 2: {
                        super.a.drawImage(k.byte, n3 + n5, (this.v - k.byte.getHeight(null)) / 2, null);
                        if (char1 == -1) {
                            color = k.C;
                            break;
                        }
                        break;
                    }
                    default: {
                        if (char1 == -1 && !s.equals("N/A")) {
                            color = this.u[5];
                            break;
                        }
                        break;
                    }
                }
            }
            if (b && this.z == 1 && b2.int()) {
                super.a.setColor(this.u[6]);
                super.a.fillRect(n3 + n4, 1, n6, this.v - 2);
            }
            if (char1 >= 0) {
                color = this.u[char1];
            }
            this.a(super.a, s, n3 + n4, n2, color, this.p);
        }
    }
    
    public void a(final int n, final int n2) {
        boolean b = false;
        final b b2 = this.try.P[n];
        final String s = this.null[n];
        final int n3 = this.else.getHeight() - 2;
        final int n4 = b2.new + n2;
        int case1 = b2.case();
        if (n4 + case1 > this.try.goto) {
            int stringWidth = this.else.stringWidth(s);
            if (this.p.isItalic()) {
                stringWidth += Math.max(this.else.getMaxAdvance() / 4, 2);
            }
            int n5;
            if (!s.equals("N/A") && !s.equals(k.w) && !s.equals("")) {
                b = true;
                n5 = stringWidth + this.else.stringWidth(b2.long);
            }
            else {
                n5 = stringWidth;
            }
            final int max = Math.max(n4, this.try.goto);
            super.a.setClip(max, 0, n4 + case1 - max, this.v);
            case1 -= 2;
            final int char1 = b2.char();
            Color color = Color.lightGray;
            if (b2.a() != 0) {
                case1 -= Math.max(k.g.getWidth(null), k.byte.getWidth(null));
                switch (this.i[n]) {
                    case 1: {
                        super.a.drawImage(k.g, n4 + case1, (this.v - k.g.getHeight(null)) / 2, null);
                        if (char1 == -1) {
                            color = k.d;
                            break;
                        }
                        break;
                    }
                    case 2: {
                        super.a.drawImage(k.byte, n4 + case1, (this.v - k.byte.getHeight(null)) / 2, null);
                        if (char1 == -1) {
                            color = k.C;
                            break;
                        }
                        break;
                    }
                    default: {
                        if (char1 == -1 && !s.equals("N/A")) {
                            color = this.u[5];
                            break;
                        }
                        break;
                    }
                }
                case1 -= 2;
            }
            final int n6 = case1 - n5;
            if (b && this.z == 1 && b2.int()) {
                super.a.setColor(this.u[6]);
                super.a.fillRect(n4 + n6, 1, n5, this.v - 2);
            }
            if (char1 >= 0) {
                color = this.u[char1];
            }
            final int n7 = n4 + n6;
            this.a(super.a, s, n7, n3, color, this.p);
            if (b) {
                this.a(super.a, b2.long, n7 + stringWidth, n3, color, this.p);
            }
        }
    }
    
    public void int() {
        this.null = new String[this.try.P.length + 2];
        this.i = new int[this.try.P.length + 2];
        for (int i = 0; i < this.try.P.length + 2; ++i) {
            this.null[i] = k.w;
            this.i[i] = 0;
        }
        this.l = "0";
        this.z = 2;
    }
    
    public void a(final Graphics graphics) {
        this.else = graphics.getFontMetrics(this.p);
        this.v = this.else.getHeight() + 2;
        this.B = this.try.goto;
        for (int i = 0; i < this.try.P.length; ++i) {
            this.B += this.try.P[i].case();
        }
        if (this.try.Z) {
            this.B += 2;
            this.v += 2;
        }
        if (this.try.getSize().width > 0) {
            this.B = this.try.getSize().width;
        }
        super.int = this.try.createImage(this.B, this.v + f.a());
        super.a = super.int.getGraphics();
    }
    
    public void a(String trim, int goto1) {
        int n = 0;
        final int n2 = goto1;
        if (goto1 == k.goto && k.f) {
            return;
        }
        if (goto1 == k.o || goto1 == k.x) {
            goto1 = k.goto;
        }
        trim = trim.trim();
        if (!trim.equals("N/A") && !trim.equals(k.w)) {
            switch (this.try.P[goto1].a()) {
                case 1: {
                    final String s = this.null[n2];
                    if (s.equals(k.w) || s.equals(this.try.P[goto1].new() ? "N/A" : "")) {
                        break;
                    }
                    final double a = this.a(s);
                    final double a2 = this.a(trim);
                    if (a > a2) {
                        n = 2;
                        break;
                    }
                    if (a < a2) {
                        n = 1;
                        break;
                    }
                    break;
                }
                case 2: {
                    final double a3 = this.a(trim);
                    if (a3 > 0.0) {
                        n = 1;
                        break;
                    }
                    if (a3 < 0.0) {
                        n = 2;
                        break;
                    }
                    break;
                }
            }
        }
        else if (trim.equals("N/A") && !this.try.P[goto1].new()) {
            trim = "";
        }
        this.null[n2] = trim;
        this.i[goto1] = n;
    }
    
    private String a(final String s, final String s2) {
        if (s == null || s2 == null) {
            return "N/A";
        }
        if (s.equals("N/A") || s2.equals("N/A")) {
            return s;
        }
        s.substring(0, 1);
        s2.substring(0, 1);
        String string = " / ";
        int n = 0;
        for (int n2 = 0; n2 < s.length() && n2 < s2.length(); ++n2) {
            final String substring = s.substring(n2, n2 + 1);
            final String substring2 = s2.substring(n2, n2 + 1);
            if (!substring.equals(substring2) || n != 0) {
                n = 1;
                string += substring2;
            }
        }
        if (!string.equals("/")) {
            return s + string;
        }
        return s;
    }
    
    private double a(String substring) {
        final int index = substring.indexOf("-");
        boolean b = false;
        if (index >= 0) {
            substring = substring.substring(index + 1);
            b = true;
        }
        if (substring.indexOf("/") < 0) {
            try {
                return b ? (-1.0 * this.case.parse(substring).doubleValue()) : this.case.parse(substring).doubleValue();
            }
            catch (Exception ex) {
                System.out.println("Cannot parse " + substring + " " + ex);
                return 0.0;
            }
        }
        final int index2 = substring.indexOf(" ");
        double doubleValue = 0.0;
        String substring2 = substring;
        if (index2 > 0) {
            try {
                doubleValue = this.case.parse(substring.substring(0, index2)).doubleValue();
                substring2 = substring.substring(index2);
            }
            catch (Exception ex2) {
                System.out.println("Cannot parse " + substring + " " + ex2);
                return 0.0;
            }
        }
        final int index3 = substring2.indexOf("/");
        final double n = doubleValue + Double.valueOf(substring2.substring(0, index3)) / Double.valueOf(substring2.substring(index3 + 1));
        return b ? (-1.0 * n) : n;
    }
    
    public synchronized void a(final Graphics graphics, final int n, final int n2, final Dimension dimension) {
        final int a = f.a();
        final int n3 = super.for.y + n2;
        if (n3 < dimension.height) {
            if (n3 >= this.try.I + a) {
                graphics.drawImage(super.int, super.for.x + n, n3, null);
            }
            else {
                final int n4 = n3 + (this.v + a);
                if (n4 > this.try.I + a) {
                    graphics.drawImage(super.int, super.for.x + n, this.try.I + a, super.for.x + n + this.B, n4, 0, this.v + 2 * a - n4 + this.try.I, this.B, this.v + a, null);
                }
            }
        }
    }
    
    public int if() {
        return this.B;
    }
    
    public int for() {
        return this.v;
    }
    
    public String new() {
        return this.h;
    }
    
    public void a(final boolean r) {
        this.r = r;
    }
    
    public void if(final boolean n) {
        this.n = n;
    }
    
    public final String a() {
        return this.c;
    }
    
    static {
        k.f = false;
        k.goto = -1;
        k.o = -1;
        k.x = -1;
    }
}
