// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.display;

import java.io.PrintStream;

public class Format
{
    private int width;
    private int precision;
    private String pre;
    private String post;
    private boolean leading_zeroes;
    private boolean show_plus;
    private boolean alternate;
    private boolean show_space;
    private boolean left_align;
    private char fmt;
    
    public Format(final String s) {
        this.width = 0;
        this.precision = -1;
        this.pre = "";
        this.post = "";
        this.leading_zeroes = false;
        this.show_plus = false;
        this.alternate = false;
        this.show_space = false;
        this.left_align = false;
        this.fmt = ' ';
        final int state = 0;
        final int length = s.length();
        int parse_state = 0;
        int i = 0;
        while (parse_state == 0) {
            if (i >= length) {
                parse_state = 5;
            }
            else if (s.charAt(i) == '%') {
                if (i >= length - 1) {
                    throw new IllegalArgumentException();
                }
                if (s.charAt(i + 1) == '%') {
                    this.pre = String.valueOf(this.pre).concat(String.valueOf('%'));
                    ++i;
                }
                else {
                    parse_state = 1;
                }
            }
            else {
                this.pre = String.valueOf(this.pre).concat(String.valueOf(s.charAt(i)));
            }
            ++i;
        }
        while (parse_state == 1) {
            if (i >= length) {
                parse_state = 5;
            }
            else if (s.charAt(i) == ' ') {
                this.show_space = true;
            }
            else if (s.charAt(i) == '-') {
                this.left_align = true;
            }
            else if (s.charAt(i) == '+') {
                this.show_plus = true;
            }
            else if (s.charAt(i) == '0') {
                this.leading_zeroes = true;
            }
            else if (s.charAt(i) == '#') {
                this.alternate = true;
            }
            else {
                parse_state = 2;
                --i;
            }
            ++i;
        }
        while (parse_state == 2) {
            if (i >= length) {
                parse_state = 5;
            }
            else if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                this.width = this.width * 10 + s.charAt(i) - 48;
                ++i;
            }
            else if (s.charAt(i) == '.') {
                parse_state = 3;
                this.precision = 0;
                ++i;
            }
            else {
                parse_state = 4;
            }
        }
        while (parse_state == 3) {
            if (i >= length) {
                parse_state = 5;
            }
            else if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                this.precision = this.precision * 10 + s.charAt(i) - 48;
                ++i;
            }
            else {
                parse_state = 4;
            }
        }
        if (parse_state == 4) {
            if (i >= length) {
                parse_state = 5;
            }
            else {
                this.fmt = s.charAt(i);
            }
            ++i;
        }
        if (i < length) {
            this.post = s.substring(i, length);
        }
    }
    
    public static void print(final PrintStream s, final String fmt, final double x) {
        s.print(new Format(fmt).form(x));
    }
    
    public static void print(final PrintStream s, final String fmt, final long x) {
        s.print(new Format(fmt).form(x));
    }
    
    public static void print(final PrintStream s, final String fmt, final char x) {
        s.print(new Format(fmt).form(x));
    }
    
    public static void print(final PrintStream s, final String fmt, final String x) {
        s.print(new Format(fmt).form(x));
    }
    
    public static int atoi(final String s) {
        return (int)atol(s);
    }
    
    public static long atol(final String s) {
        int i;
        for (i = 0; i < s.length() && Character.isWhitespace(s.charAt(i)); ++i) {}
        if (i >= s.length() || s.charAt(i) != '0') {
            return parseLong(s, 10);
        }
        if (i + 1 < s.length() && (s.charAt(i + 1) == 'x' || s.charAt(i + 1) == 'X')) {
            return parseLong(s.substring(i + 2), 16);
        }
        return parseLong(s, 8);
    }
    
    private static long parseLong(final String s, final int base) {
        int i = 0;
        int sign = 1;
        long r = 0L;
        while (i < s.length() && Character.isWhitespace(s.charAt(i))) {
            ++i;
        }
        if (i < s.length() && s.charAt(i) == '-') {
            sign = -1;
            ++i;
        }
        else if (i < s.length() && s.charAt(i) == '+') {
            ++i;
        }
        while (i < s.length()) {
            final char ch = s.charAt(i);
            if ('0' <= ch && ch < 48 + base) {
                r = r * base + ch - 48;
            }
            else if ('A' <= ch && ch < 65 + base - 10) {
                r = r * base + ch - 65 + 10;
            }
            else {
                if ('a' > ch || ch >= 97 + base - 10) {
                    return r * sign;
                }
                r = r * base + ch - 97 + 10;
            }
            ++i;
        }
        return r * sign;
    }
    
    public static double atof(final String s) {
        int i = 0;
        int sign = 1;
        double r = 0.0;
        final double f = 0.0;
        double p = 1.0;
        int state = 0;
        while (i < s.length() && Character.isWhitespace(s.charAt(i))) {
            ++i;
        }
        if (i < s.length() && s.charAt(i) == '-') {
            sign = -1;
            ++i;
        }
        else if (i < s.length() && s.charAt(i) == '+') {
            ++i;
        }
        while (i < s.length()) {
            final char ch = s.charAt(i);
            if ('0' <= ch && ch <= '9') {
                if (state == 0) {
                    r = r * 10 + ch - 48;
                }
                else if (state == 1) {
                    p /= 10;
                    r += p * (ch - '0');
                }
            }
            else if (ch == '.') {
                if (state != 0) {
                    return sign * r;
                }
                state = 1;
            }
            else {
                if (ch == 'e' || ch == 'E') {
                    final long e = (int)parseLong(s.substring(i + 1), 10);
                    return sign * r * Math.pow(10.0, e);
                }
                return sign * r;
            }
            ++i;
        }
        return sign * r;
    }
    
    public String form(double x) {
        if (this.fmt == 'i' || this.fmt == 'd') {
            return this.form((int)x);
        }
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            return "NaN";
        }
        if (this.precision < 0) {
            this.precision = 6;
        }
        int s = 1;
        if (x < 0) {
            x = -x;
            s = -1;
        }
        String r;
        if (this.fmt == 'f') {
            r = this.fixed_format(x);
        }
        else {
            if (this.fmt != 'e' && this.fmt != 'E' && this.fmt != 'g' && this.fmt != 'G') {
                throw new IllegalArgumentException();
            }
            r = this.exp_format(x);
        }
        return this.pad(this.sign(s, r));
    }
    
    public String form(final long x) {
        int s = 0;
        String r;
        if (this.fmt == 'd' || this.fmt == 'i') {
            if (x < 0) {
                r = String.valueOf("").concat(String.valueOf(x)).substring(1);
                s = -1;
            }
            else {
                r = String.valueOf("").concat(String.valueOf(x));
                s = 1;
            }
        }
        else if (this.fmt == 'o') {
            r = convert(x, 3, 7, "01234567");
        }
        else if (this.fmt == 'x') {
            r = convert(x, 4, 15, "0123456789abcdef");
        }
        else {
            if (this.fmt != 'X') {
                throw new IllegalArgumentException();
            }
            r = convert(x, 4, 15, "0123456789ABCDEF");
        }
        return this.pad(this.sign(s, r));
    }
    
    public String form(final char c) {
        if (this.fmt != 'c') {
            throw new IllegalArgumentException();
        }
        final String r = String.valueOf("").concat(String.valueOf(c));
        return this.pad(r);
    }
    
    public String form(String s) {
        if (this.fmt != 's') {
            throw new IllegalArgumentException();
        }
        if (this.precision >= 0) {
            s = s.substring(0, this.precision);
        }
        return this.pad(s);
    }
    
    public static void main(final String[] a) {
        final double x = 1.23456789012;
        final double y = 123.0;
        final double z = 1.2345E30;
        final double w = 1.02;
        final double u = 1.234E-5;
        final int d = 51966;
        print(System.out, "x = |%f|\n", x);
        print(System.out, "u = |%20f|\n", u);
        print(System.out, "x = |% .5f|\n", x);
        print(System.out, "w = |%20.5f|\n", w);
        print(System.out, "x = |%020.5f|\n", x);
        print(System.out, "x = |%+20.5f|\n", x);
        print(System.out, "x = |%+020.5f|\n", x);
        print(System.out, "x = |% 020.5f|\n", x);
        print(System.out, "y = |%#+20.5f|\n", y);
        print(System.out, "y = |%-+20.5f|\n", y);
        print(System.out, "z = |%20.5f|\n", z);
        print(System.out, "x = |%e|\n", x);
        print(System.out, "u = |%20e|\n", u);
        print(System.out, "x = |% .5e|\n", x);
        print(System.out, "w = |%20.5e|\n", w);
        print(System.out, "x = |%020.5e|\n", x);
        print(System.out, "x = |%+20.5e|\n", x);
        print(System.out, "x = |%+020.5e|\n", x);
        print(System.out, "x = |% 020.5e|\n", x);
        print(System.out, "y = |%#+20.5e|\n", y);
        print(System.out, "y = |%-+20.5e|\n", y);
        print(System.out, "x = |%g|\n", x);
        print(System.out, "z = |%g|\n", z);
        print(System.out, "w = |%g|\n", w);
        print(System.out, "u = |%g|\n", u);
        print(System.out, "y = |%.2g|\n", y);
        print(System.out, "y = |%#.2g|\n", y);
        print(System.out, "d = |%d|\n", d);
        print(System.out, "d = |%20d|\n", d);
        print(System.out, "d = |%020d|\n", d);
        print(System.out, "d = |%+20d|\n", d);
        print(System.out, "d = |% 020d|\n", d);
        print(System.out, "d = |%-20d|\n", d);
        print(System.out, "d = |%20.8d|\n", d);
        print(System.out, "d = |%x|\n", d);
        print(System.out, "d = |%20X|\n", d);
        print(System.out, "d = |%#20x|\n", d);
        print(System.out, "d = |%020X|\n", d);
        print(System.out, "d = |%20.8x|\n", d);
        print(System.out, "d = |%o|\n", d);
        print(System.out, "d = |%020o|\n", d);
        print(System.out, "d = |%#20o|\n", d);
        print(System.out, "d = |%#020o|\n", d);
        print(System.out, "d = |%20.12o|\n", d);
        print(System.out, "s = |%-20s|\n", "Hello");
        print(System.out, "s = |%-20c|\n", '!');
        print(System.out, "|%i|\n", Long.MIN_VALUE);
        print(System.out, "|%6.2e|\n", 0.0);
        print(System.out, "|%6.2g|\n", 0.0);
        print(System.out, "|%6.2f|\n", 9.99);
        print(System.out, "|%6.2f|\n", 9.999);
        print(System.out, "|%6.0f|\n", 9.999);
    }
    
    private static String repeat(final char c, final int n) {
        if (n <= 0) {
            return "";
        }
        final StringBuffer s = new StringBuffer(n);
        for (int i = 0; i < n; ++i) {
            s.append(c);
        }
        return s.toString();
    }
    
    private static String convert(long x, final int n, final int m, final String d) {
        if (x == 0) {
            return "0";
        }
        String r = "";
        while (x != 0) {
            r = String.valueOf(d.charAt((int)(x & m))).concat(String.valueOf(r));
            x >>>= n;
        }
        return r;
    }
    
    private String pad(final String r) {
        final String p = repeat(' ', this.width - r.length());
        if (this.left_align) {
            return String.valueOf(String.valueOf(String.valueOf(this.pre).concat(String.valueOf(r))).concat(String.valueOf(p))).concat(String.valueOf(this.post));
        }
        return String.valueOf(String.valueOf(String.valueOf(this.pre).concat(String.valueOf(p))).concat(String.valueOf(r))).concat(String.valueOf(this.post));
    }
    
    private String sign(final int s, final String r) {
        String p = "";
        if (s < 0) {
            p = "-";
        }
        else if (s > 0) {
            if (this.show_plus) {
                p = "+";
            }
            else if (this.show_space) {
                p = " ";
            }
        }
        else if (this.fmt == 'o' && this.alternate && r.length() > 0 && r.charAt(0) != '0') {
            p = "0";
        }
        else if (this.fmt == 'x' && this.alternate) {
            p = "0x";
        }
        else if (this.fmt == 'X' && this.alternate) {
            p = "0X";
        }
        int w = 0;
        if (this.leading_zeroes) {
            w = this.width;
        }
        else if ((this.fmt == 'd' || this.fmt == 'i' || this.fmt == 'x' || this.fmt == 'X' || this.fmt == 'o') && this.precision > 0) {
            w = this.precision;
        }
        return String.valueOf(String.valueOf(p).concat(String.valueOf(repeat('0', w - p.length() - r.length())))).concat(String.valueOf(r));
    }
    
    private String fixed_format(final double d) {
        final boolean removeTrailing = (this.fmt == 'G' || this.fmt == 'g') && !this.alternate;
        if (d > Long.MAX_VALUE) {
            return this.exp_format(d);
        }
        if (this.precision == 0) {
            return String.valueOf((long)(d + 0.5)).concat(String.valueOf(removeTrailing ? "" : "."));
        }
        long whole = (long)d;
        final double fr = d - whole;
        if (fr >= 1 || fr < 0) {
            return this.exp_format(d);
        }
        double factor = 1.0;
        String leading_zeroes = "";
        for (int i = 1; i <= this.precision && factor <= Long.MAX_VALUE; factor *= 10, leading_zeroes = String.valueOf(leading_zeroes).concat(String.valueOf("0")), ++i) {}
        long l = (long)(factor * fr + 0.5);
        if (l >= factor) {
            l = 0L;
            ++whole;
        }
        String z = String.valueOf(leading_zeroes).concat(String.valueOf(l));
        z = String.valueOf(".").concat(String.valueOf(z.substring(z.length() - this.precision, z.length())));
        if (removeTrailing) {
            int t;
            for (t = z.length() - 1; t >= 0 && z.charAt(t) == '0'; --t) {}
            if (t >= 0 && z.charAt(t) == '.') {
                --t;
            }
            z = z.substring(0, t + 1);
        }
        return String.valueOf(whole).concat(String.valueOf(z));
    }
    
    private String exp_format(double d) {
        String f = "";
        int e = 0;
        double dd = d;
        double factor = 1.0;
        if (d != 0) {
            while (dd > 10) {
                ++e;
                factor /= 10;
                dd /= 10;
            }
            while (dd < 1) {
                --e;
                factor *= 10;
                dd *= 10;
            }
        }
        if ((this.fmt == 'g' || this.fmt == 'G') && e >= -4 && e < this.precision) {
            return this.fixed_format(d);
        }
        d *= factor;
        f = String.valueOf(f).concat(String.valueOf(this.fixed_format(d)));
        if (this.fmt == 'e' || this.fmt == 'g') {
            f = String.valueOf(f).concat(String.valueOf("e"));
        }
        else {
            f = String.valueOf(f).concat(String.valueOf("E"));
        }
        String p = "000";
        if (e >= 0) {
            f = String.valueOf(f).concat(String.valueOf("+"));
            p = String.valueOf(p).concat(String.valueOf(e));
        }
        else {
            f = String.valueOf(f).concat(String.valueOf("-"));
            p = String.valueOf(p).concat(String.valueOf(-e));
        }
        return String.valueOf(f).concat(String.valueOf(p.substring(p.length() - 3, p.length())));
    }
}
