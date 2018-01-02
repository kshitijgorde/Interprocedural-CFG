import java.net.MalformedURLException;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.StringTokenizer;
import java.awt.Point;
import java.net.URL;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class C13 implements C24
{
    public static int x;
    Hashtable y;
    Hashtable z;
    int A;
    String C;
    Hashtable D;
    public static String E;
    int[] F;
    boolean G;
    URL H;
    C43 I;
    Hashtable J;
    int K;
    Point L;
    C38 N;
    C50 O;
    public static int P;
    public static int Q;
    
    public C35 a(final int[] array, final int[] array2) {
        final C04 c04 = new C04(array, array2);
        if (this.K > 0) {
            c04.m(this.K);
        }
        if (this.F != C28.S) {
            c04.j(this.F);
        }
        return c04;
    }
    
    private void b(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ,\t\r)");
        if (stringTokenizer.countTokens() < 3) {
            System.out.println("Error #442300 " + s);
            return;
        }
        final String nextToken = stringTokenizer.nextToken();
        final int[] f = new int[stringTokenizer.countTokens()];
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            try {
                if (stringTokenizer.countTokens() < 2) {
                    break;
                }
                f[n++] = Integer.parseInt(stringTokenizer.nextToken());
                f[n++] = Integer.parseInt(stringTokenizer.nextToken());
            }
            catch (NumberFormatException ex) {
                System.out.println("Error #2221 " + ex);
            }
        }
        this.F = f;
        this.D.put(nextToken, this.F);
    }
    
    public C35 c(final String s) throws IOException {
        try {
            final C50 c50 = new C50(new StringBufferInputStream(s));
            final int g = c50.g();
            c50.read();
            final int g2 = c50.g();
            this.L = new Point(g, g2);
            final int g3 = c50.g();
            final int g4 = c50.g();
            c50.read();
            final int g5 = c50.g();
            final int n = (int)(0.0054931640625 * g4);
            int n2 = (int)(0.0054931640625 * g5);
            if (n == n2) {
                if (g4 == g5) {
                    if (C13.Q <= 30) {
                        System.out.println("AGLE555");
                        return null;
                    }
                    n2 += 360;
                }
                else {
                    ++n2;
                }
            }
            final C31 c51 = new C31(g, g2, g3, n, n2);
            if (this.K > 0) {
                c51.m(this.K);
            }
            if (this.F != C28.S) {
                c51.j(this.F);
            }
            return c51;
        }
        catch (Throwable t) {
            System.out.println("Warning #44454 " + s + t);
            return null;
        }
    }
    
    public C35 d(String trim) {
        if (trim.trim().length() <= 0) {
            return new C37();
        }
        trim = trim.trim();
        try {
            final C50 c50 = new C50(new StringBufferInputStream(trim));
            System.out.println("ascii=" + trim);
            System.out.println("Tuong: before reading name");
            final String a = c50.a();
            System.out.println("Tuong: after reading name name=" + a);
            System.out.println("Tuong: max_x=2147483647");
            int g;
            try {
                g = c50.g();
            }
            catch (IOException ex2) {
                return (C35)this.z.get(a);
            }
            System.out.println("Tuong: name=" + a + " count=" + g);
            int n = Integer.MAX_VALUE;
            int n2 = Integer.MAX_VALUE;
            int n3 = -2147483647;
            int n4 = -2147483647;
            for (int i = 0; i < g; ++i) {
                final int g2 = c50.g();
                c50.read();
                final int g3 = c50.g();
                c50.read();
                if (g2 < n) {
                    n = g2;
                }
                else if (g2 > n3) {
                    n3 = g2;
                }
                if (g3 < n2) {
                    n2 = g3;
                }
                else if (g3 > n4) {
                    n4 = g3;
                }
            }
            final C37 c51 = new C37(a, n, n2, n3 - n, n4 - n2);
            this.z.put(a, c51);
            return c51;
        }
        catch (Exception ex) {
            System.out.println("Error #4432 " + ex + " " + trim);
            System.out.println("Err #22444 " + trim);
            return null;
        }
    }
    
    public C35 e(final String s) throws IOException {
        if (s.indexOf(",") != -1) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\r\n\t ,");
            if (stringTokenizer.countTokens() < 4) {
                throw new IOException("Error reading the color for background, not enought aruments");
            }
            final int int1 = Integer.parseInt(stringTokenizer.nextToken());
            final int int2 = Integer.parseInt(stringTokenizer.nextToken());
            final int int3 = Integer.parseInt(stringTokenizer.nextToken());
            final int int4 = Integer.parseInt(stringTokenizer.nextToken());
            if (int1 == -1 || int2 == -1 || int3 == -1 || int4 == -1) {
                throw new IOException("Error, found EOF while reading Background color");
            }
            return new C29(new Color(int1, int2, int3));
        }
        else {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(s, "\r\n\t ,");
            if (stringTokenizer2.countTokens() < 1) {
                throw new IOException("Error reading the color for background, not enought aruments");
            }
            final int int5 = Integer.parseInt(stringTokenizer2.nextToken());
            if (int5 == -1) {
                throw new IOException("Error, found EOF while reading the color index for the background");
            }
            return new C29(int5);
        }
    }
    
    public String f() {
        return this.C;
    }
    
    public C35 g(final String s) throws IOException {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " \r\n\t,");
        if (stringTokenizer.countTokens() != 4) {
            throw new IOException("Error #45043 ");
        }
        final int int1 = Integer.parseInt(stringTokenizer.nextToken());
        final int int2 = Integer.parseInt(stringTokenizer.nextToken());
        final int int3 = Integer.parseInt(stringTokenizer.nextToken());
        Integer.parseInt(stringTokenizer.nextToken());
        return new C30(new Color(int1, int2, int3));
    }
    
    protected C35 h(final int n, final int n2, final int n3, final int n4) {
        final C10 c10 = new C10(n, n2, n3, n4);
        if (this.K > 0) {
            c10.m(this.K);
        }
        if (this.F != C28.S) {
            c10.j(this.F);
        }
        return c10;
    }
    
    public C35 a(final String s) throws IOException {
        final String lowerCase = s.toLowerCase();
        if (lowerCase.startsWith("units ")) {
            return this.s(s.substring(5));
        }
        if (lowerCase.startsWith("view ") || lowerCase.startsWith("view\t")) {
            return this.p(s.substring(4));
        }
        if (lowerCase.startsWith("namedview ")) {
            return this.p(s.substring(10));
        }
        if (lowerCase.startsWith("background ")) {
            return this.e(s.substring(10));
        }
        if (lowerCase.startsWith("colormap ")) {
            return this.o(s.substring(8));
        }
        if (lowerCase.startsWith("color ")) {
            return this.g(s.substring(5));
        }
        if (lowerCase.startsWith("layer ")) {
            return this.r(s.substring(5));
        }
        if (lowerCase.startsWith("text ")) {
            return this.t(s.substring(5));
        }
        if (lowerCase.startsWith("font ")) {
            return this.v(s.substring(5));
        }
        if (lowerCase.startsWith("circle ")) {
            return this.c(s.substring(7));
        }
        if (lowerCase.startsWith("ellipse ")) {
            return this.l(s.substring(8));
        }
        if (lowerCase.startsWith("viewport")) {
            return this.d(s.substring(8));
        }
        if (lowerCase.startsWith("url ")) {
            return this.w(s);
        }
        if (lowerCase.startsWith("lineweight")) {
            try {
                this.K = Integer.parseInt(s.substring(11, s.length()).trim());
                System.out.println("LineWeights=" + this.K);
            }
            catch (NumberFormatException ex) {
                System.out.println("Error #344443 " + ex);
            }
            return null;
        }
        if (lowerCase.startsWith("linepattern")) {
            this.q(s.substring(12).toLowerCase());
            return null;
        }
        if (lowerCase.startsWith("dashpattern")) {
            this.b(s.substring(12).toLowerCase());
            return null;
        }
        if (lowerCase.startsWith("comments")) {
            this.C = s.substring(10, s.length() - 1);
            return null;
        }
        if (lowerCase.startsWith("comment")) {
            System.out.println(s);
            return null;
        }
        if (!s.toLowerCase().equals("endofdwf") && !lowerCase.startsWith("comment") && !lowerCase.startsWith("embed") && this.y.get(s) == null) {
            System.out.println("Warning 3002 " + s);
            this.y.put(s, s);
        }
        return null;
    }
    
    public int i(String s) {
        s = s.trim();
        try {
            if (s.startsWith("+")) {
                s = s.substring(1);
            }
        }
        catch (Throwable t) {
            throw new NumberFormatException("Error with number format " + t.getMessage() + " " + s);
        }
        return Integer.parseInt(s);
    }
    
    protected int k(final int y) {
        if (this.L.y == -1) {
            this.L.y = y;
        }
        else {
            this.L.y += y;
        }
        return this.L.y;
    }
    
    public C35 l(final String s) throws IOException {
        try {
            final C50 c50 = new C50(new StringBufferInputStream(s));
            final int g = c50.g();
            c50.read();
            final int g2 = c50.g();
            this.L = new Point(g, g2);
            final int n = c50.g() * 2;
            c50.read();
            final int n2 = c50.g() * 2;
            final double n3 = c50.g();
            c50.read();
            final double n4 = c50.g();
            final int n5 = (int)(0.0054931640625 * n3);
            int n6 = (int)(0.0054931640625 * n4);
            final int g3 = c50.g();
            if (n5 == n6) {
                if (n3 == n4) {
                    if (C13.Q <= 30) {
                        System.out.println("AGLE444");
                        return null;
                    }
                    n6 += 360;
                }
                else {
                    ++n6;
                }
            }
            final C11 c51 = new C11(g, g2, n, n2, n5, n6, (int)(0.0054931640625 * g3));
            if (this.K > 0) {
                c51.m(this.K);
            }
            if (this.F != C28.S) {
                c51.j(this.F);
            }
            return c51;
        }
        catch (Throwable t) {
            System.out.println("Warning #44456 " + s + t);
            return null;
        }
    }
    
    public C35 b(final int n, final int n2) throws IOException {
        if (n == 1) {
            int read = this.O.read();
            if (read == 0) {
                read = 256;
            }
            final Color[] array = new Color[read];
            int n3 = 0;
            int n4 = 0;
            while (0 != -1 && n4 < read && n3 < n2) {
                final int read2 = this.O.read();
                final int read3 = this.O.read();
                final int read4 = this.O.read();
                final int read5 = this.O.read();
                if (read2 == -1 || read3 == -1 || read4 == -1 || read5 == -1) {
                    throw new IOException("Found EOF while reading color map");
                }
                if (C13.Q < 34) {
                    array[n4] = new Color(read4, read3, read2);
                }
                else {
                    array[n4] = new Color(read2, read3, read4);
                }
                ++n4;
                if (this.G) {
                    continue;
                }
                n3 += 4;
            }
            char c = (char)this.O.read();
            if (this.G) {
                System.out.println("<<tc>>");
                while (c != '}') {
                    c = (char)this.O.read();
                }
            }
            return this.N = new C38(array);
        }
        if (n < 9) {
            int n5 = 0;
            final int b = this.O.b();
            final int b2 = this.O.b();
            final int u = this.u(this.O.k());
            final int k = this.k(this.O.k());
            final int u2 = this.u(this.O.k());
            final int i = this.k(this.O.k());
            final int j = this.O.k();
            n5 += 24;
            C38 c2 = null;
            if (n == 2 || n == 3 || n == 5) {
                int read6 = this.O.read();
                if (read6 == 0) {
                    read6 = 256;
                }
                ++n5;
                final Color[] array2 = new Color[read6];
                for (int l = 0; l < read6; ++l) {
                    final int read7 = this.O.read();
                    final int read8 = this.O.read();
                    final int read9 = this.O.read();
                    this.O.read();
                    n5 += 4;
                    array2[l] = new Color(read7, read8, read9);
                }
                c2 = new C38(array2);
            }
            final int n6 = this.O.k() + 1;
            if (n == 2) {
                return new C40(b, b2, u, k, u2, i, j, c2, n6, this.O, 0);
            }
            if (n == 3) {
                return new C40(b, b2, u, k, u2, i, j, c2, n6, this.O, 1);
            }
            if (n == 4) {
                return new C40(b, b2, u, k, u2, i, j, this.N, n6, this.O, 2);
            }
            if (n == 5) {
                return new C40(b, b2, u, k, u2, i, j, c2, n6, this.O, 3);
            }
            if (n == 6) {
                return new C40(b, b2, u, k, u2, i, j, n6, false, this.O, 4);
            }
            if (n == 7) {
                return new C40(b, b2, u, k, u2, i, j, n6, true, this.O, 5);
            }
            if (n == 8) {
                return new C40(b, b2, u, k, u2, i, j, n6, true, this.O, 6);
            }
        }
        if (n == 17) {
            throw new Error("done");
        }
        System.out.println("Warning 49382 " + Integer.toHexString(n));
        return null;
    }
    
    private void m() {
        (this.D = new Hashtable()).put("1", C28.S);
        this.D.put("2", C28.R);
        this.D.put("3", C28.E);
        this.D.put("4", C28.u);
        this.D.put("5", C28.w);
        this.D.put("6", C28.s);
        this.D.put("7", C28.x);
        this.D.put("8", C28.F);
        this.D.put("9", C28.t);
        this.D.put("10", C28.M);
        this.D.put("11", C28.v);
        this.D.put("12", C28.G);
        this.D.put("13", C28.N);
        this.D.put("14", C28.L);
        this.D.put("15", C28.I);
        this.D.put("16", C28.K);
        this.D.put("17", C28.O);
    }
    
    public void n(final boolean g) {
        this.G = g;
        this.A = 0;
    }
    
    protected C35 o(final String s) throws IOException {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\r\n\t ,");
        final int int1 = Integer.parseInt(stringTokenizer.nextToken());
        final Color[] array = new Color[int1];
        for (int i = 0; i < int1; ++i) {
            final int int2 = Integer.parseInt(stringTokenizer.nextToken());
            final int int3 = Integer.parseInt(stringTokenizer.nextToken());
            final int int4 = Integer.parseInt(stringTokenizer.nextToken());
            final int int5 = Integer.parseInt(stringTokenizer.nextToken());
            if (int2 == -1 || int3 == -1 || int4 == -1 || int5 == -1) {
                throw new IOException("Error, found EOF while reading Background color");
            }
            if (C13.Q < 34) {
                array[i] = new Color(int4, int3, int2);
            }
            else {
                array[i] = new Color(int2, int3, int4);
            }
        }
        return this.N = new C38(array);
    }
    
    static {
        C13.E = "Copyright (c) 2000 - ZoomON Inc.  All Rights Reserved";
        C13.x = 0;
        C13.P = 0;
        C13.Q = 1000;
    }
    
    protected C35 p(final String s) throws IOException {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\t \r\n,'");
        if (stringTokenizer.countTokens() < 4) {
            throw new IOException("Error, the format for the view definition coordinamtes was wrong");
        }
        final int[] array = new int[4];
        for (int n = 0; n < array.length && stringTokenizer.hasMoreTokens(); ++n) {
            array[n] = this.i(stringTokenizer.nextToken());
        }
        String string = null;
        if (stringTokenizer.hasMoreTokens()) {
            final StringBuffer sb = new StringBuffer();
            while (stringTokenizer.hasMoreTokens()) {
                sb.append(stringTokenizer.nextToken());
            }
            string = sb.toString();
        }
        return new C17(array[0], array[1], array[2], array[3], string);
    }
    
    private void q(final String s) {
        if (s.equals("solid")) {
            this.F = C28.S;
        }
        else if (s.equals("dashed")) {
            this.F = C28.R;
        }
        else if (s.equals("dotted")) {
            this.F = C28.E;
        }
        else if (s.equals("dash_dot")) {
            this.F = C28.u;
        }
        else if (s.equals("short_dash")) {
            this.F = C28.w;
        }
        else if (s.equals("medium_dash")) {
            this.F = C28.s;
        }
        else if (s.equals("long_dash")) {
            this.F = C28.x;
        }
        else if (s.equals("short_dash_x2")) {
            this.F = C28.F;
        }
        else if (s.equals("medium_dash_x2")) {
            this.F = C28.t;
        }
        else if (s.equals("long_dash_x2")) {
            this.F = C28.M;
        }
        else if (s.equals("medium_long_dash")) {
            this.F = C28.v;
        }
        else if (s.equals("medium_dash_short_dash_short_dash")) {
            this.F = C28.G;
        }
        else if (s.equals("long_dash_short_dash")) {
            this.F = C28.N;
        }
        else if (s.equals("long_dash_dot_dot")) {
            this.F = C28.L;
        }
        else if (s.equals("long_dash_dot")) {
            this.F = C28.I;
        }
        else if (s.equals("medium_dash_dot_short_dash_dot")) {
            this.F = C28.K;
        }
        else if (s.equals("sparse_dot")) {
            this.F = C28.O;
        }
    }
    
    public C35 c(final char c) throws IOException {
        if (c == '\0') {
            System.out.print(".");
            return null;
        }
        switch (c) {
            case '\u0003': {
                final int read = this.O.read();
                final int read2 = this.O.read();
                final int read3 = this.O.read();
                final int read4 = this.O.read();
                if (read == -1 || read2 == -1 || read3 == -1 || read4 == -1) {
                    throw new IOException("Error, while reading set color operands, found EOF");
                }
                Color color;
                if (C13.Q < 34) {
                    color = new Color(255 - read, 255 - read2, 255 - read3);
                }
                else {
                    color = new Color(read, read2, read3);
                }
                return new C30(color);
            }
            case ' ': {
                return null;
            }
            case '\t': {
                return null;
            }
            case '\r': {
                return null;
            }
            case '\n': {
                return null;
            }
            case 'f': {
                return new C00(false);
            }
            case 'F': {
                return new C00(true);
            }
            case '\u0014': {
                int read5 = this.O.read();
                if (read5 == 0) {
                    read5 = this.O.b() + 256;
                }
                final int[] array = new int[read5];
                final int[] array2 = new int[read5];
                for (int i = 0; i < read5; ++i) {
                    array[i] = this.u(this.O.h());
                    array2[i] = this.k(this.O.h());
                }
                return new C19(array, array2);
            }
            case 't': {
                int read6 = this.O.read();
                if (read6 == 0) {
                    read6 = this.O.b() + 256;
                }
                final int[] array3 = new int[read6];
                final int[] array4 = new int[read6];
                for (int j = 0; j < read6; ++j) {
                    array3[j] = this.u(this.O.k());
                    array4[j] = this.k(this.O.k());
                }
                return new C19(array3, array4);
            }
            case 'l': {
                return this.h(this.u(this.O.k()), this.k(this.O.k()), this.u(this.O.k()), this.k(this.O.k()));
            }
            case '\f': {
                return this.h(this.u(this.O.h()), this.k(this.O.h()), this.u(this.O.h()), this.k(this.O.h()));
            }
            case 'L': {
                final int g = this.O.g();
                this.O.read();
                final int g2 = this.O.g();
                final int g3 = this.O.g();
                this.O.read();
                final int g4 = this.O.g();
                this.L = new Point(g3, g4);
                return this.h(g, g2, g3, g4);
            }
            case '\u008c': {
                final int read7 = this.O.read();
                final C36 c2 = new C36();
                for (int k = 0; k < read7; ++k) {
                    c2.b((C28)this.h(this.u(this.O.h()), this.k(this.O.h()), this.u(this.O.h()), this.k(this.O.h())));
                }
                return c2;
            }
            case 'c': {
                return new C30(this.O.read());
            }
            case 'C': {
                return new C30(this.O.g());
            }
            case 'R': {
                final int g5 = this.O.g();
                this.O.read();
                final int g6 = this.O.g();
                final int g7 = this.O.g();
                this.L = new Point(g5, g6);
                final C22 c3 = new C22(g5, g6, g7);
                if (this.K > 0) {
                    c3.m(this.K);
                }
                if (this.F != C28.S) {
                    c3.j(this.F);
                }
                return c3;
            }
            case 'P': {
                final int g8 = this.O.g();
                final int[] array5 = new int[g8];
                final int[] array6 = new int[g8];
                for (int l = 0; l < g8; ++l) {
                    array5[l] = this.O.g();
                    this.O.read();
                    array6[l] = this.O.g();
                }
                if (g8 > 0) {
                    this.L = new Point(array5[array5.length - 1], array6[array6.length - 1]);
                }
                if (g8 == 1) {
                    return new C16(array5[0], array6[0]);
                }
                if (g8 == 2) {
                    return this.h(array5[0], array6[1], array5[1], array6[0]);
                }
                return this.a(array5, array6);
            }
            case 'T': {
                final int g9 = this.O.g();
                final int[] array7 = new int[g9];
                final int[] array8 = new int[g9];
                for (int n = 0; n < g9; ++n) {
                    array7[n] = this.O.g();
                    this.O.read();
                    array8[n] = this.O.g();
                }
                if (g9 > 0) {
                    this.L = new Point(array7[array7.length - 1], array8[array8.length - 1]);
                }
                return new C19(array7, array8);
            }
            case 'M': {
                final int g10 = this.O.g();
                final int[] array9 = new int[g10];
                final int[] array10 = new int[g10];
                for (int n2 = 0; n2 < g10; ++n2) {
                    array9[n2] = this.O.g();
                    this.O.read();
                    array10[n2] = this.O.g();
                }
                if (g10 > 0) {
                    this.L = new Point(array9[array9.length - 1], array10[array10.length - 1]);
                }
                return new C08(array9, array10);
            }
            case 'r': {
                final int u = this.u(this.O.k());
                final int m = this.k(this.O.k());
                long i2 = this.O.i();
                if (i2 > 2147483647L) {
                    System.out.println("Warning: 22998 " + i2);
                    i2 = 2147483647L;
                }
                final C22 c4 = new C22(u, m, (int)i2);
                if (this.K > 0) {
                    c4.m(this.K);
                }
                if (this.F != C28.S) {
                    c4.j(this.F);
                }
                return c4;
            }
            case '\u0012': {
                final C22 c5 = new C22(this.u(this.O.h()), this.k(this.O.h()), this.O.b());
                if (this.K > 0) {
                    c5.m(this.K);
                }
                if (this.F != C28.S) {
                    c5.j(this.F);
                }
                return c5;
            }
            case '\u0092': {
                final int u2 = this.u(this.O.k());
                final int k2 = this.k(this.O.k());
                long i3 = this.O.i();
                if (i3 > 2147483647L) {
                    System.out.println("Warning: 2935 " + i3);
                    i3 = 2147483647L;
                }
                final int b = this.O.b();
                final int b2 = this.O.b();
                final int n3 = (int)(0.0054931640625 * b);
                int n4 = (int)(0.0054931640625 * b2);
                if (n3 == n4) {
                    if (b == b2) {
                        if (C13.Q <= 30) {
                            System.out.println("AGLE222");
                            return null;
                        }
                        n4 += 360;
                    }
                    else {
                        ++n4;
                    }
                }
                final C31 c6 = new C31(u2, k2, (int)i3, n3, n4);
                if (this.K > 0) {
                    c6.m(this.K);
                }
                if (this.F != C28.S) {
                    c6.j(this.F);
                }
                return c6;
            }
            case 'O': {
                long i4 = this.O.i();
                long i5 = this.O.i();
                if (i4 > 2147483647L) {
                    System.out.println("Warning: 123 " + i4);
                    i4 = 2147483647L;
                }
                if (i5 > 2147483647L) {
                    System.out.println("Warning: 345 " + i5);
                    i5 = 2147483647L;
                }
                this.L = new Point((int)i4, (int)i5);
                return null;
            }
            case '\u0010': {
                int read8 = this.O.read();
                if (read8 == 0) {
                    read8 = this.O.b() + 256;
                }
                int n5 = 0;
                final int[] array11 = new int[read8];
                final int[] array12 = new int[read8];
                while (read8-- > 0) {
                    final int u3 = this.u(this.O.h());
                    final int k3 = this.k(this.O.h());
                    array11[n5] = u3;
                    array12[n5++] = k3;
                }
                return this.a(array11, array12);
            }
            case 'p': {
                int read9 = this.O.read();
                if (read9 == 0) {
                    read9 = this.O.b() + 256;
                }
                int n6 = 0;
                final int[] array13 = new int[read9];
                final int[] array14 = new int[read9];
                while (read9-- > 0) {
                    final int u4 = this.u(this.O.k());
                    final int k4 = this.k(this.O.k());
                    array13[n6] = u4;
                    array14[n6++] = k4;
                }
                return this.a(array13, array14);
            }
            case '\u0006': {
                if (C13.Q <= 30) {
                    int read10 = this.O.read();
                    if (read10 == 0) {
                        read10 = 256 + this.O.b();
                    }
                    final String f = this.O.f(read10);
                    final int read11 = this.O.read();
                    boolean b3 = false;
                    boolean b4 = false;
                    if ((read11 & 0x1) == 0x1) {
                        b3 = true;
                    }
                    if ((read11 & 0x2) == 0x1) {
                        b4 = true;
                    }
                    this.O.i();
                    this.O.i();
                    return this.I = new C43(f, 8, b3, b4);
                }
                if (C13.Q < 34) {
                    final String a = this.O.a();
                    this.O.read();
                    this.O.read();
                    return this.I = new C43(a, 8, false, false);
                }
                long i6 = 8L;
                final int b5 = this.O.b();
                String a2 = "TimesRoman";
                if ((b5 & 0x1) > 0 || b5 == 65535) {
                    a2 = this.O.a();
                }
                if ((b5 & 0x2) == 0x2 || b5 == 65535) {
                    final byte b6 = (byte)this.O.read();
                }
                if ((b5 & 0x4) == 0x4 || b5 == 65535) {
                    final byte b7 = (byte)this.O.read();
                }
                if ((b5 & 0x8) == 0x8 || b5 == 65535) {
                    final byte b8 = (byte)this.O.read();
                }
                if ((b5 & 0x10) == 0x10 || b5 == 65535) {
                    final byte b9 = (byte)this.O.read();
                }
                if ((b5 & 0x20) == 0x20 || b5 == 65535) {
                    i6 = this.O.i();
                }
                if ((b5 & 0x40) == 0x40 || b5 == 65535) {
                    this.O.b();
                }
                if ((b5 & 0x80) == 0x80 || b5 == 65535) {
                    this.O.b();
                }
                if ((b5 & 0x100) == 0x100 || b5 == 65535) {
                    this.O.b();
                }
                if ((b5 & 0x200) == 0x200 || b5 == 65535) {
                    this.O.b();
                }
                if ((b5 & 0x400) == 0x400 || b5 == 65535) {
                    this.O.i();
                }
                if (i6 > 2147483647L) {
                    i6 = 2147483647L;
                    System.out.println("Error: 456 ");
                }
                return this.I = new C43(a2, (int)i6, false, false);
            }
            case '\u0018': {
                if (C13.Q < 34) {
                    final int b10 = this.O.b();
                    final int k5 = this.O.k();
                    final int u5 = this.u(this.O.k());
                    final int k6 = this.k(this.O.k());
                    int read12 = this.O.read();
                    if (read12 == 0) {
                        read12 = this.O.b() + 256;
                    }
                    return new C34(b10, k5, u5, k6, this.O.j(read12));
                }
                final int u6 = this.u(this.O.k());
                final int k7 = this.k(this.O.k());
                final String a3 = this.O.a();
                int read13 = this.O.read();
                if (read13 == 0) {
                    read13 = this.O.b() + 256;
                }
                --read13;
                for (int n7 = 0; n7 < read13; ++n7) {
                    if (this.O.read() == 0) {
                        this.O.b();
                    }
                }
                int read14 = this.O.read();
                if (read14 == 0) {
                    read14 = this.O.b() + 256;
                }
                --read14;
                for (int n8 = 0; n8 < read14; ++n8) {
                    if (this.O.read() == 0) {
                        this.O.b();
                    }
                }
                final int[] array15 = new int[4];
                final int[] array16 = new int[4];
                for (int n9 = 0; n9 < 4; ++n9) {
                    final int u7 = this.u(this.O.k());
                    final int k8 = this.k(this.O.k());
                    array15[n9] = u7;
                    array16[n9] = k8;
                }
                int n10;
                if (array16[1] - array16[0] < array16[2] - array16[1]) {
                    n10 = array16[2] - array16[1];
                }
                else {
                    n10 = array15[1] - array15[2];
                }
                return new C34(0, n10, u6, k7, a3);
            }
            case 'x': {
                if (C13.Q < 34) {
                    this.O.b();
                    this.O.b();
                    this.O.k();
                    this.O.b();
                    int read15 = this.O.read();
                    if (read15 == 0) {
                        read15 = this.O.b() + 256;
                    }
                    --read15;
                    for (int n11 = 0; n11 < read15; ++n11) {
                        if (this.O.read() == 0) {
                            this.O.b();
                        }
                    }
                    int read16 = this.O.read();
                    if (read16 == 0) {
                        read16 = this.O.b() + 256;
                    }
                    --read16;
                    for (int n12 = 0; n12 < read16; ++n12) {
                        if (this.O.read() == 0) {
                            this.O.b();
                        }
                    }
                    final int b11 = this.O.b();
                    final int k9 = this.O.k();
                    final int u8 = this.u(this.O.k());
                    final int k10 = this.k(this.O.k());
                    for (int n13 = 0; n13 < 8; ++n13) {
                        this.O.k();
                    }
                    int read17 = this.O.read();
                    if (read17 == 0) {
                        read17 = this.O.b() + 256;
                    }
                    return new C34(b11, k9, u8, k10, this.O.j(read17));
                }
                final int u9 = this.u(this.O.k());
                final int k11 = this.k(this.O.k());
                final String a4 = this.O.a();
                int e = 8;
                if (this.I != null) {
                    e = this.I.e();
                }
                return new C34(0, e, u9, k11, a4);
            }
            case 'm': {
                int read18 = this.O.read();
                if (read18 == 0) {
                    read18 = 256 + this.O.b();
                }
                final int[] array17 = new int[read18];
                final int[] array18 = new int[read18];
                for (int n14 = 0; n14 < read18; ++n14) {
                    array17[n14] = this.u(this.O.k());
                    array18[n14] = this.k(this.O.k());
                }
                return new C08(array17, array18);
            }
            case '\u008d': {
                int read19 = this.O.read();
                if (read19 == 0) {
                    read19 = 256 + this.O.b();
                }
                final int[] array19 = new int[read19];
                final int[] array20 = new int[read19];
                for (int n15 = 0; n15 < read19; ++n15) {
                    array19[n15] = this.u(this.O.h());
                    array20[n15] = this.k(this.O.h());
                }
                return new C08(array19, array20);
            }
            case 'e': {
                final int u10 = this.u(this.O.k());
                final int k12 = this.k(this.O.k());
                long n16;
                long n17;
                if (C13.Q > 33) {
                    n16 = this.O.k() * 2;
                    n17 = this.O.k() * 2;
                }
                else {
                    n16 = this.O.i() * 2L;
                    n17 = this.O.i() * 2L;
                }
                if (n16 > 2147483647L) {
                    System.out.println("Warning: 3553 " + n16);
                    n16 = 2147483647L;
                }
                if (n17 > 2147483647L) {
                    System.out.println("Warning: 4565 " + n16);
                    n17 = 2147483647L;
                }
                final int b12 = this.O.b();
                final int b13 = this.O.b();
                final int n18 = (int)(0.0054931640625 * b12);
                int n19 = (int)(0.0054931640625 * b13);
                final int n20 = (int)(0.0054931640625 * this.O.b());
                if (n18 == n19) {
                    if (b12 == b13) {
                        if (C13.Q <= 30) {
                            System.out.println("AGLE333");
                            return null;
                        }
                        n19 += 360;
                    }
                    else {
                        ++n19;
                    }
                }
                final C11 c7 = new C11(u10, k12, (int)n16, (int)n17, n18, n19, n20);
                if (this.K > 0) {
                    c7.m(this.K);
                }
                if (this.F != C28.S) {
                    c7.j(this.F);
                }
                return c7;
            }
            case 'E': {
                final int g11 = this.O.g();
                this.O.read();
                final int g12 = this.O.g();
                this.L = new Point(g11, g12);
                final int n21 = this.O.g() * 2;
                this.O.read();
                final C07 c8 = new C07(g11, g12, n21, this.O.g() * 2);
                if (this.K > 0) {
                    c8.m(this.K);
                }
                if (this.F != C28.S) {
                    c8.j(this.F);
                }
                return c8;
            }
            case '¬': {
                int b14 = (char)this.O.read();
                if (b14 == 0) {
                    b14 = this.O.b();
                }
                final C32 c9 = new C32(b14);
                c9.f("Layer " + b14);
                return c9;
            }
            case '\u00cc': {
                int read20 = this.O.read();
                if (read20 == 0) {
                    read20 = 256 + this.O.b();
                }
                final int[] f2 = this.D.get(read20 + "");
                if (f2 == null) {
                    System.out.println("Error #2233 !");
                }
                else {
                    this.F = f2;
                }
                return null;
            }
            case '\u0017': {
                this.K = this.O.k();
                return null;
            }
            case '\u00ef': {
                if (C13.Q < 16) {
                    return this.h(this.L.x, this.L.y, this.O.read(), this.O.read());
                }
                break;
            }
        }
        if (!this.G) {
            System.out.println("UNKNOWN OP CODE(hex)=" + Integer.toHexString(c) + " '" + c + "'");
            for (int n22 = 0; n22 < 20; ++n22) {
                final char c10 = (char)this.O.read();
                System.out.println(Integer.toHexString(c10) + " " + ">>>>" + c10);
            }
            throw new IOException("not implemented ");
        }
        ++this.A;
        return null;
    }
    
    public C35 r(final String s) throws IOException {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " \t\r\n");
        if (stringTokenizer.countTokens() == 0) {
            return new C32();
        }
        final C32 c32 = new C32(Integer.parseInt(stringTokenizer.nextToken()));
        if (stringTokenizer.hasMoreTokens()) {
            c32.f(stringTokenizer.nextToken());
        }
        return c32;
    }
    
    protected C35 s(String replace) throws IOException {
        replace = replace.replace(',', '.');
        final StringTokenizer stringTokenizer = new StringTokenizer(replace, " ()'\r\n\t");
        if (stringTokenizer.countTokens() != 17 && stringTokenizer.countTokens() != 16) {
            System.out.println("ERROR, 3920 " + replace);
            return null;
        }
        String nextToken = "";
        if (!replace.trim().startsWith("''") && !replace.trim().startsWith("\"\"")) {
            nextToken = stringTokenizer.nextToken();
        }
        final double[][] array = new double[4][4];
        try {
            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 4; ++j) {
                    array[i][j] = new Double(stringTokenizer.nextToken());
                }
            }
        }
        catch (Throwable t) {
            System.out.println("Error 3948 " + t);
            return null;
        }
        return new C26(nextToken, array);
    }
    
    public C35 t(final String s) throws IOException {
        try {
            final C50 c50 = new C50(new StringBufferInputStream(s));
            final int g = c50.g();
            c50.read();
            final int g2 = c50.g();
            String s2 = c50.a();
            int e = 8;
            if (this.I != null) {
                e = this.I.e();
            }
            this.L = new Point(g, g2);
            if (s2.toLowerCase().indexOf("(bounds") > 0) {
                s2 = s2.substring(0, s2.toLowerCase().indexOf("(bounds"));
            }
            if (s2.toLowerCase().indexOf("(overscore") > 0) {
                s2 = s2.substring(0, s2.toLowerCase().indexOf("(bounds"));
            }
            if (s2.toLowerCase().indexOf("(underscore") > 0) {
                s2 = s2.substring(0, s2.toLowerCase().indexOf("(bounds"));
            }
            C34 c51 = new C34(0, e, g, g2, s2);
            final int index = s.toLowerCase().indexOf("(bounds");
            if (index != -1 && s.indexOf(")", index) > 0) {
                final String substring = s.substring(index + 8);
                final StringTokenizer stringTokenizer = new StringTokenizer(substring, " ,)");
                if (stringTokenizer.countTokens() != 8) {
                    System.out.println("Error 30029 " + substring);
                    return c51;
                }
                try {
                    final int i = this.i(stringTokenizer.nextToken());
                    final int j = this.i(stringTokenizer.nextToken());
                    for (int k = 0; k < 4; ++k) {
                        stringTokenizer.nextToken();
                    }
                    final int l = this.i(stringTokenizer.nextToken());
                    final int m = this.i(stringTokenizer.nextToken());
                    if (i == l) {
                        c51 = new C34(0, Math.abs(m - j), g, g2, s2);
                    }
                    this.L = new Point(l, m);
                }
                catch (NumberFormatException ex) {
                    System.out.println("Warning 39920 " + ex);
                }
            }
            return c51;
        }
        catch (Throwable t) {
            System.out.println("Error 843 " + t + s);
            return null;
        }
    }
    
    protected int u(final int x) {
        if (this.L.x == -1) {
            this.L.x = x;
        }
        else {
            this.L.x += x;
        }
        return this.L.x;
    }
    
    public C35 v(final String s) throws IOException {
        String s2 = "";
        boolean c = false;
        boolean b = false;
        int n = 8;
        if (this.I != null) {
            n = this.I.e();
            s2 = this.I.a();
            c = this.I.c();
            b = this.I.b();
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ()");
        while (stringTokenizer.hasMoreTokens()) {
            final String lowerCase = stringTokenizer.nextToken().toLowerCase();
            if (!stringTokenizer.hasMoreTokens()) {
                continue;
            }
            if (lowerCase.equals("name")) {
                s2 = stringTokenizer.nextToken();
            }
            else if (lowerCase.equals("height")) {
                try {
                    n = Integer.parseInt(stringTokenizer.nextToken());
                }
                catch (NumberFormatException ex) {
                    System.out.println("Error 342345 " + ex);
                }
            }
            else {
                if (!lowerCase.equals("style") || !stringTokenizer.hasMoreTokens()) {
                    continue;
                }
                final String lowerCase2 = stringTokenizer.nextToken().toLowerCase();
                if (lowerCase2.equals("bold")) {
                    c = true;
                }
                else {
                    if (!lowerCase2.equals("italic")) {
                        continue;
                    }
                    b = true;
                }
            }
        }
        return this.I = new C43(s2, n, c, b);
    }
    
    public C35 w(final String s) throws IOException {
        String s2 = null;
        try {
            if (C13.Q < 38) {
                return new C25(this.H, s.substring(4));
            }
            final boolean b = false;
            if (b) {
                System.out.println("createURLWidget(  " + s + "  )");
            }
            if (b) {
                System.out.println("ascii: " + s);
            }
            final int length = s.length();
            if (length == 3) {
                if (b) {
                    System.out.println("Empty urlToken, (URL), found: " + s);
                }
                return new C25(this.H, s.substring(4));
            }
            final Hashtable<String, String> hashtable = new Hashtable<String, String>();
            if (b) {
                System.out.println("ascii=====" + s + " length=" + length);
            }
            if (s.indexOf(")") == -1 && s.indexOf("(") == -1) {
                if (b) {
                    System.out.println("ascii=====" + s);
                }
                final String trim = s.substring(4, length).trim();
                if (b) {
                    System.out.println("urlRefe=" + trim);
                }
                if (b) {
                    System.out.println(this.J.get("0"));
                }
                if (b) {
                    System.out.println("case 0 urlReferenceIndex" + Integer.parseInt(trim));
                }
                return new C25(this.H, (String)this.J.get(trim));
            }
            int i = s.indexOf(40);
            if (i == -1 && s.charAt(5) != '(') {
                i = 3;
            }
            while (i != -1) {
                int n = s.indexOf(41, i);
                if (n == -1) {
                    if (i != 3) {
                        System.out.println("Warning 4756z2 " + s);
                        continue;
                    }
                    n = s.length();
                }
                final StringTokenizer stringTokenizer = new StringTokenizer(s.substring(i, n), "() ", false);
                final int countTokens = stringTokenizer.countTokens();
                s2 = null;
                String string = null;
                if (b) {
                    System.out.println("tokenCount=" + countTokens);
                }
                switch (countTokens) {
                    case 1: {
                        if (!stringTokenizer.hasMoreTokens()) {
                            System.out.println("Warning 4756z3 " + s);
                            return null;
                        }
                        s2 = stringTokenizer.nextToken();
                        if (b) {
                            System.out.println("case 2urlReferenceIndex" + Integer.parseInt(s2));
                        }
                        final String s3 = this.J.get(s2);
                        hashtable.put(s2, s3);
                        if (b) {
                            System.out.println("case 2 url : " + s3);
                            break;
                        }
                        break;
                    }
                    case 3: {
                        if (!stringTokenizer.hasMoreTokens()) {
                            System.out.println("Warning 4756z4 " + s);
                            return null;
                        }
                        s2 = stringTokenizer.nextToken();
                        if (b) {
                            System.out.println("case 3: urlReferenceIndex" + Integer.parseInt(s2));
                        }
                        if (stringTokenizer.hasMoreTokens()) {
                            string = new StringBuffer(stringTokenizer.nextToken()).toString();
                            if (b) {
                                System.out.println("case 3:url : " + string);
                            }
                        }
                        if (stringTokenizer.hasMoreTokens()) {
                            final String string2 = new StringBuffer(stringTokenizer.nextToken()).toString();
                            if (b) {
                                System.out.println("case 3:userFriendlyName : " + string2);
                            }
                        }
                        if (s2 != null && string != null) {
                            this.J.put(s2, string);
                            hashtable.put(s2, string);
                            break;
                        }
                        System.out.println("Warning 4756z5 " + s);
                        return null;
                    }
                    default: {
                        System.out.println("Warning 4756z2 " + s);
                        break;
                    }
                }
                i = s.indexOf(40, n);
            }
            if (b) {
                System.out.println("END: ");
                System.out.println("urltabel.get=" + this.J.get("0"));
                System.out.println("urlRefe=" + (String)this.J.get(s2));
            }
            return new C25(this.H, (String)this.J.get(s2));
        }
        catch (MalformedURLException ex2) {
            System.out.println("ERROR, 4756 " + s.substring(3));
        }
        catch (Exception ex) {
            System.out.println("ERROR, 4756z1 " + s.substring(3));
            System.out.println("\t  " + ex.toString());
        }
        return null;
    }
    
    public C13(final C50 o, final int x, final int p4, final URL h) throws IOException {
        this.L = new Point(-1, -1);
        this.G = false;
        this.A = 0;
        this.K = 0;
        this.F = C28.S;
        if (x > 0 || p4 > 34) {
            System.out.println("Warning 89302 ");
        }
        C13.x = x;
        C13.P = p4;
        C13.Q = x * 100 + p4;
        this.H = h;
        this.O = o;
        this.y = new Hashtable();
        this.z = new Hashtable();
        this.m();
        this.J = new Hashtable();
    }
}
