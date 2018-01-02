// 
// Decompiled by Procyon v0.5.30
// 

package A.A;

import java.util.Hashtable;
import java.util.Stack;

public class E
{
    static final int D = 0;
    static final int G = 1;
    static final int C = 2;
    private Stack I;
    private B E;
    private B A;
    private StringBuffer F;
    private Hashtable J;
    private static final String B;
    private static final String H;
    
    public E() {
        this.I = new Stack();
        this.F = new StringBuffer(1024);
        (this.J = new Hashtable()).put("nbsp", " ");
        this.J.put("quot", "\"");
        this.J.put("apos", "'");
    }
    
    private void A(final B b) {
        if (this.E == null) {
            this.A = b;
        }
        else {
            this.E.A(b);
        }
        this.I.push(b);
        this.E = b;
    }
    
    private void A() {
        this.I.pop();
        try {
            this.E = this.I.peek();
        }
        catch (Exception ex) {
            this.E = null;
        }
    }
    
    private void F(final String s) {
        int n;
        String s2;
        if (s.endsWith("/>")) {
            n = 2;
            s2 = s.substring(1, s.length() - 2);
        }
        else if (s.startsWith("</")) {
            n = 1;
            s2 = s.substring(2, s.length() - 1);
        }
        else {
            n = 0;
            s2 = s.substring(1, s.length() - 1);
        }
        try {
            if (s2.indexOf(32) < 0) {
                final String s3 = s2;
                switch (n) {
                    case 0: {
                        this.A(new B(s3));
                        break;
                    }
                    case 1: {
                        if (this.E.F(s3)) {
                            this.A();
                            break;
                        }
                        throw new Error("Expected close of '" + this.E.D() + "' instead of " + s);
                    }
                    case 2: {
                        this.A(new B(s3));
                        this.A();
                        break;
                    }
                }
            }
            else {
                B b = null;
                final String substring = s2.substring(0, s2.indexOf(32));
                switch (n) {
                    case 0: {
                        b = new B(substring);
                        this.A(b);
                        break;
                    }
                    case 1: {
                        final Error error = new Error("Syntax Error: " + s);
                        break;
                    }
                    case 2: {
                        b = new B(substring);
                        this.A(b);
                        this.A();
                        break;
                    }
                }
                final String substring2 = s2.substring(s2.indexOf(32) + 1);
                boolean b2 = false;
                int n2 = 0;
                StringBuffer sb = new StringBuffer(128);
                StringBuffer sb2 = new StringBuffer(32);
                for (int i = 0; i < substring2.length(); ++i) {
                    switch (substring2.charAt(i)) {
                        case '\"': {
                            if (n2 == 1 && b2) {
                                b.B(sb2.toString().trim(), this.D(sb.toString()));
                                n2 = 0;
                                sb = new StringBuffer();
                                sb2 = new StringBuffer();
                            }
                            b2 = !b2;
                            continue;
                        }
                        case '\n':
                        case '\r':
                        case ' ': {
                            if (n2 == 1 && b2) {
                                sb.append(substring2.charAt(i));
                            }
                            continue;
                        }
                        case '=': {
                            if (!b2) {
                                n2 = 1;
                                continue;
                            }
                            break;
                        }
                    }
                    if (n2 != 0) {
                        sb.append(substring2.charAt(i));
                    }
                    else {
                        sb2.append(substring2.charAt(i));
                    }
                }
                if (sb.length() > 0) {
                    b.B(sb2.toString(), this.D(sb.toString()));
                }
            }
        }
        catch (Exception ex) {
            throw new Error("Cannot parse element '" + s + "' - (" + ex + ")");
        }
    }
    
    private void E(final String s) {
        if (this.E != null) {
            this.E.A((Object)this.C(s));
        }
        else if (this.A == null) {
            this.F.append(s);
        }
    }
    
    private static String A(final String s, final String s2, final String s3) {
        int i = s.indexOf(s2);
        if (i == -1) {
            return s;
        }
        final int length = s2.length();
        final char[] charArray = s.toCharArray();
        final StringBuffer sb = new StringBuffer();
        int n;
        for (n = 0; i != -1; i = s.indexOf(s2, n)) {
            sb.append(charArray, n, i - n);
            sb.append(s3);
            n = i + length;
        }
        sb.append(charArray, n, charArray.length - n);
        return sb.toString();
    }
    
    private String C(final String s) {
        String a = s;
        if (s.indexOf(A.A.E.H) != -1) {
            a = A(a, A.A.E.B, "");
        }
        return this.D(A(a, A.A.E.B, A.A.E.H));
    }
    
    private String D(final String s) {
        String string = "";
        int min = 0;
        for (int i = s.indexOf(38); i > -1; i = s.indexOf(38, min)) {
            final int index = s.indexOf(59, i);
            final String substring = s.substring(i + 1, index);
            String s2;
            if (substring.startsWith("#")) {
                s2 = new Character((char)(substring.startsWith("#x") ? Integer.valueOf(substring.substring(2), 16) : ((int)Integer.valueOf(substring.substring(1), 10)))).toString();
            }
            else if (this.J.get(substring) != null) {
                s2 = this.J.get(substring);
            }
            else {
                s2 = "!!unknow xml object: " + substring + "!!";
            }
            string = string + s.substring(min, i) + s2;
            min = Math.min(s.length(), index + 1);
        }
        return string + s.substring(min);
    }
    
    private A B(final String s) {
        final C c = new C(s);
        while (c.C()) {
            final String a = c.A();
            if (a.startsWith("<?") && a.endsWith("?>")) {
                continue;
            }
            if (a.startsWith("<!--") && a.endsWith("-->")) {
                continue;
            }
            if (a.charAt(0) == '<') {
                this.F(a);
            }
            else {
                this.E(a);
            }
        }
        return new A(this.F.toString(), this.A);
    }
    
    public static A A(final String s) {
        return new E().B(s);
    }
    
    static {
        B = String.valueOf('\r');
        H = String.valueOf('\n');
    }
}
