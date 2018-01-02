// 
// Decompiled by Procyon v0.5.30
// 

package ji.annotate;

import ji.io.h;
import java.util.StringTokenizer;

public class gs
{
    private static int a;
    private static int b;
    private static int c;
    private static String d;
    private static String e;
    private static String f;
    private int[] g;
    private String[] h;
    private String[] i;
    private int j;
    
    public gs() {
    }
    
    public gs(final String[] array, final String s) {
        this.j = array.length;
        this.g = new int[this.j];
        this.i = new String[this.j];
        this.h = new String[this.j];
        for (int i = 0; i < this.j; ++i) {
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(array[i], ",");
                if (stringTokenizer.countTokens() > 2) {
                    final String trim = stringTokenizer.nextToken().trim();
                    final String trim2 = stringTokenizer.nextToken().trim();
                    while (stringTokenizer.hasMoreTokens()) {
                        final String trim3 = stringTokenizer.nextToken().trim();
                        if (trim3.equals(gs.e)) {
                            final int[] g = this.g;
                            final int n = i;
                            g[n] |= gs.b;
                        }
                        else if (trim3.equals(gs.d)) {
                            final int[] g2 = this.g;
                            final int n2 = i;
                            g2[n2] |= gs.a;
                        }
                        else {
                            if (!trim3.equals(gs.f)) {
                                continue;
                            }
                            final int[] g3 = this.g;
                            final int n3 = i;
                            g3[n3] |= gs.c;
                        }
                    }
                    if (trim.indexOf("0x") > -1) {
                        this.i[i] = new String(new char[] { (char)(int)Integer.decode(trim) });
                    }
                    else {
                        this.i[i] = trim;
                    }
                    if (trim2.indexOf("0x") > -1) {
                        String substring = new String(trim2);
                        this.h[i] = "";
                        while (substring.startsWith("0x")) {
                            String substring2;
                            if (substring.indexOf("0x", 2) > -1) {
                                substring2 = substring.substring(0, substring.indexOf("0x", 2));
                                substring = substring.substring(substring.indexOf("0x", 2));
                            }
                            else {
                                substring2 = substring;
                                substring = "";
                            }
                            final String[] h = this.h;
                            final int n4 = i;
                            h[n4] = String.valueOf(String.valueOf(h[n4])).concat(String.valueOf(String.valueOf(new String(new char[] { (char)(int)Integer.decode(substring2) }))));
                        }
                    }
                    else {
                        this.h[i] = trim2;
                    }
                }
            }
            catch (Exception ex) {
                ji.io.h.d(s, "failed to load text annotation key mapping: ".concat(String.valueOf(String.valueOf(array[i]))));
            }
        }
    }
    
    public int a() {
        return this.j;
    }
    
    public boolean a(final int n) {
        return n >= 0 && n < this.j && (this.g[n] & gs.b) > 0;
    }
    
    public boolean b(final int n) {
        return n >= 0 && n < this.j && (this.g[n] & gs.a) > 0;
    }
    
    public boolean c(final int n) {
        return n >= 0 && n < this.j && (this.g[n] & gs.c) > 0;
    }
    
    public String d(final int n) {
        if (n >= 0 && n < this.j) {
            return this.h[n];
        }
        return "";
    }
    
    public String e(final int n) {
        if (n >= 0 && n < this.j) {
            return this.i[n];
        }
        return "";
    }
    
    static {
        gs.a = 1;
        gs.b = 2;
        gs.c = 4;
        gs.d = "CTRL";
        gs.e = "SHIFT";
        gs.f = "ALT";
    }
}
