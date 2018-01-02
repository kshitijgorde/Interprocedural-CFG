// 
// Decompiled by Procyon v0.5.30
// 

package ji.annotate;

import ji.util.d;

public class ed
{
    private int a;
    private int b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    
    public final String toString() {
        return this.g();
    }
    
    public ed(final int a, final int b, final String g) {
        this.a = -1;
        this.b = -1;
        this.c = null;
        this.d = "";
        this.e = "";
        this.f = "_self";
        this.g = null;
        this.g = g;
        this.a = a;
        this.b = b;
    }
    
    public ed(final int a, final String c, final String g) {
        this.a = -1;
        this.b = -1;
        this.c = null;
        this.d = "";
        this.e = "";
        this.f = "_self";
        this.g = null;
        this.g = g;
        if (a == 2) {
            this.a = a;
            this.b = 0;
            this.c = null;
            this.d = c;
        }
        else if (a == 3) {
            this.a = a;
            this.b = 0;
            this.c = null;
            this.e = c;
        }
        else if (a == 1) {
            this.a = a;
            this.c = c;
            this.b = 0;
            this.d = null;
        }
    }
    
    public ed(final int a, final String e, final String s, final String g) {
        this.a = -1;
        this.b = -1;
        this.c = null;
        this.d = "";
        this.e = "";
        this.f = "_self";
        this.g = null;
        this.g = g;
        if (a == 3) {
            this.a = a;
            this.b = 0;
            this.c = null;
            this.f = "_self";
            if (!ji.util.d.by(s)) {
                this.f = ji.util.d.bc(s);
            }
            this.e = e;
        }
    }
    
    public ed(final String s, final String g) {
        this.a = -1;
        this.b = -1;
        this.c = null;
        this.d = "";
        this.e = "";
        this.f = "_self";
        this.g = null;
        this.g = g;
        try {
            if (!ji.util.d.by(s)) {
                final String[] b = ji.util.d.b(s, s, "<", ">");
                if (b != null && b.length > 1) {
                    final String lowerCase = b[1].toLowerCase();
                    if (lowerCase.startsWith("page") && b.length > 2) {
                        this.a = 0;
                        this.b = ji.util.d.c(b[2], 0);
                    }
                    if (lowerCase.startsWith("annotation") && b.length > 2) {
                        this.a = 1;
                        this.b = 0;
                        this.c = ji.util.d.bc(b[2]);
                    }
                    if (lowerCase.startsWith("javascript")) {
                        this.a = 2;
                        this.d = "";
                        this.b = 0;
                        this.c = null;
                        if (b.length > 2) {
                            this.d = ji.util.d.bc(b[2]);
                        }
                    }
                    if (lowerCase.startsWith("web")) {
                        this.a = 3;
                        this.e = "";
                        this.f = "_self";
                        if (b.length > 2) {
                            this.e = ji.util.d.bc(b[2]);
                        }
                        if (b.length > 3) {
                            this.f = ji.util.d.bc(b[3]);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final String a() {
        return this.f;
    }
    
    public final int b() {
        return this.a;
    }
    
    public final void a(final int b) {
        this.b = b;
    }
    
    public final int c() {
        return this.b;
    }
    
    public final String d() {
        return this.c;
    }
    
    public final String e() {
        return this.d;
    }
    
    public final String f() {
        return this.e;
    }
    
    public final String g() {
        Object o = null;
        if (this.a >= 0) {
            switch (this.a) {
                case 0: {
                    o = String.valueOf(String.valueOf(new StringBuffer("<page><").append(this.b).append(">")));
                    break;
                }
                case 1: {
                    if (ji.util.d.by(this.c)) {
                        o = "<annotation><>";
                        break;
                    }
                    o = String.valueOf(String.valueOf(new StringBuffer("<annotation><").append(this.c).append(">")));
                    break;
                }
                case 2: {
                    if (ji.util.d.by(this.d)) {
                        o = "<javascript>";
                        break;
                    }
                    o = String.valueOf(String.valueOf(new StringBuffer("<javascript><").append(this.d).append(">")));
                    if (this.b > 0) {
                        o = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(o))).append("<").append(this.b).append(">")));
                        break;
                    }
                    if (!ji.util.d.by(this.c)) {
                        o = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(o))).append("<").append(this.c).append(">")));
                        break;
                    }
                    break;
                }
                case 3: {
                    if (ji.util.d.by(this.e)) {
                        o = "<web><>";
                    }
                    else {
                        o = String.valueOf(String.valueOf(new StringBuffer("<web><").append(this.e).append(">")));
                    }
                    if (!ji.util.d.by(this.f)) {
                        o = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(o))).append("<").append(this.f).append(">")));
                        break;
                    }
                    break;
                }
            }
        }
        return (String)o;
    }
    
    public final String h() {
        String s = null;
        if (this.a >= 0) {
            switch (this.a) {
                case 0: {
                    s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(302, this.g)))).append(" ").append(this.b)));
                    break;
                }
                case 1: {
                    if (ji.util.d.by(this.c)) {
                        s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(302, this.g)))).append(" ").append(this.b)));
                        break;
                    }
                    s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(302, this.g)))).append(" ").append(this.b).append(" - ").append(this.c)));
                    break;
                }
                case 2: {
                    if (ji.util.d.by(this.d)) {
                        s = "";
                        break;
                    }
                    s = this.d;
                    break;
                }
                case 3: {
                    if (ji.util.d.by(this.e)) {
                        s = "";
                        break;
                    }
                    s = this.e;
                    break;
                }
            }
        }
        return s;
    }
}
