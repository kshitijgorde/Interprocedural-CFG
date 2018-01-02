// 
// Decompiled by Procyon v0.5.30
// 

package org.a.a;

import java.util.StringTokenizer;
import java.util.Date;

public class c
{
    private String a;
    private String b;
    private Date c;
    private String d;
    private String e;
    private boolean f;
    private static String[] z;
    
    public c() {
        this.a = "";
        this.b = "";
        this.c = null;
        this.d = "";
        this.e = "";
        this.f = false;
    }
    
    public c(final String e, final String s) throws b {
        this.a = "";
        this.b = "";
        this.c = null;
        this.d = "";
        this.e = e;
        this.f = false;
        int n = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";", false);
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (nextToken == null) {
                throw new b(org.a.a.c.z[0]);
            }
            final int index = nextToken.indexOf(61);
            String substring = "";
            String s2;
            if (index >= 0) {
                s2 = nextToken.substring(0, index);
                substring = nextToken.substring(index + 1);
            }
            else {
                s2 = nextToken;
            }
            if (s2 == null || s2.length() <= 0 || substring == null) {}
            if (n == 0) {
                n = 1;
                this.a(s2);
                this.b(substring);
            }
            else {
                if (s2.length() > 0) {
                    s2 = s2.substring(1);
                }
                if (s2.equalsIgnoreCase(org.a.a.c.z[2])) {
                    this.c(substring);
                }
                else if (s2.equalsIgnoreCase(org.a.a.c.z[1])) {
                    this.a((Date)null);
                }
                else {
                    if (!s2.equalsIgnoreCase(org.a.a.c.z[3])) {
                        continue;
                    }
                    this.a(false);
                }
            }
        }
    }
    
    public String a() {
        return this.a;
    }
    
    public void a(final String a) {
        this.a = a;
    }
    
    public String b() {
        return this.b;
    }
    
    public void b(final String b) {
        this.b = b;
    }
    
    public void a(final Date c) {
        this.c = c;
    }
    
    public String c() {
        return this.d;
    }
    
    public void c(final String d) {
        this.d = d;
    }
    
    public String d() {
        return this.e;
    }
    
    public void d(final String e) {
        this.e = e;
    }
    
    public void a(final boolean f) {
        this.f = f;
    }
    
    static {
        final String[] z = new String[4];
        final int n = 0;
        final char[] charArray = "_\\k\u007fW`Pb}\u0018QRhrQw\u0013".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '\u0012';
                    break;
                }
                case 1: {
                    c2 = '=';
                    break;
                }
                case 2: {
                    c2 = '\u0007';
                    break;
                }
                case 3: {
                    c2 = '\u0019';
                    break;
                }
                default: {
                    c2 = '8';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "wEwpJwN".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '\u0012';
                    break;
                }
                case 1: {
                    c4 = '=';
                    break;
                }
                case 2: {
                    c4 = '\u0007';
                    break;
                }
                case 3: {
                    c4 = '\u0019';
                    break;
                }
                default: {
                    c4 = '8';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "b\\sq".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '\u0012';
                    break;
                }
                case 1: {
                    c6 = '=';
                    break;
                }
                case 2: {
                    c6 = '\u0007';
                    break;
                }
                case 3: {
                    c6 = '\u0019';
                    break;
                }
                default: {
                    c6 = '8';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "aXdlJw".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '\u0012';
                    break;
                }
                case 1: {
                    c8 = '=';
                    break;
                }
                case 2: {
                    c8 = '\u0007';
                    break;
                }
                case 3: {
                    c8 = '\u0019';
                    break;
                }
                default: {
                    c8 = '8';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        c.z = z;
    }
}
