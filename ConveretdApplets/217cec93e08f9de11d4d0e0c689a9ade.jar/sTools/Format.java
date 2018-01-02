// 
// Decompiled by Procyon v0.5.30
// 

package sTools;

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
        final int length = s.length();
        int i = 0;
        int n = 0;
        while (i == 0) {
            if (n >= length) {
                i = 5;
            }
            else if (s.charAt(n) == '%') {
                if (n >= length - 1) {
                    throw new IllegalArgumentException();
                }
                if (s.charAt(n + 1) == '%') {
                    this.pre = String.valueOf(this.pre).concat(String.valueOf('%'));
                    ++n;
                }
                else {
                    i = 1;
                }
            }
            else {
                this.pre = String.valueOf(this.pre).concat(String.valueOf(s.charAt(n)));
            }
            ++n;
        }
        while (i == 1) {
            if (n >= length) {
                i = 5;
            }
            else if (s.charAt(n) == ' ') {
                this.show_space = true;
            }
            else if (s.charAt(n) == '-') {
                this.left_align = true;
            }
            else if (s.charAt(n) == '+') {
                this.show_plus = true;
            }
            else if (s.charAt(n) == '0') {
                this.leading_zeroes = true;
            }
            else if (s.charAt(n) == '#') {
                this.alternate = true;
            }
            else {
                i = 2;
                --n;
            }
            ++n;
        }
        while (i == 2) {
            if (n >= length) {
                i = 5;
            }
            else if ('0' <= s.charAt(n) && s.charAt(n) <= '9') {
                this.width = this.width * 10 + s.charAt(n) - 48;
                ++n;
            }
            else if (s.charAt(n) == '.') {
                i = 3;
                this.precision = 0;
                ++n;
            }
            else {
                i = 4;
            }
        }
        while (i == 3) {
            if (n >= length) {
                i = 5;
            }
            else if ('0' <= s.charAt(n) && s.charAt(n) <= '9') {
                this.precision = this.precision * 10 + s.charAt(n) - 48;
                ++n;
            }
            else {
                i = 4;
            }
        }
        if (i == 4) {
            if (n < length) {
                this.fmt = s.charAt(n);
            }
            ++n;
        }
        if (n < length) {
            this.post = s.substring(n, length);
        }
    }
    
    public static void print(final PrintStream printStream, final String s, final double n) {
        printStream.print(new Format(s).form(n));
    }
    
    public static void print(final PrintStream printStream, final String s, final long n) {
        printStream.print(new Format(s).form(n));
    }
    
    public static void print(final PrintStream printStream, final String s, final char c) {
        printStream.print(new Format(s).form(c));
    }
    
    public static void print(final PrintStream printStream, final String s, final String s2) {
        printStream.print(new Format(s).form(s2));
    }
    
    public static int atoi(final String s) {
        return (int)atol(s);
    }
    
    public static long atol(final String s) {
        int n;
        for (n = 0; n < s.length() && Character.isWhitespace(s.charAt(n)); ++n) {}
        if (n >= s.length() || s.charAt(n) != '0') {
            return parseLong(s, 10);
        }
        if (n + 1 < s.length() && (s.charAt(n + 1) == 'x' || s.charAt(n + 1) == 'X')) {
            return parseLong(s.substring(n + 2), 16);
        }
        return parseLong(s, 8);
    }
    
    private static long parseLong(final String s, final int n) {
        int i = 0;
        int n2 = 1;
        long n3 = 0L;
        while (i < s.length() && Character.isWhitespace(s.charAt(i))) {
            ++i;
        }
        if (i < s.length() && s.charAt(i) == '-') {
            n2 = -1;
            ++i;
        }
        else if (i < s.length() && s.charAt(i) == '+') {
            ++i;
        }
        while (i < s.length()) {
            final char char1 = s.charAt(i);
            if ('0' <= char1 && char1 < 48 + n) {
                n3 = n3 * n + char1 - 48;
            }
            else if ('A' <= char1 && char1 < 65 + n - 10) {
                n3 = n3 * n + char1 - 65 + 10;
            }
            else {
                if ('a' > char1 || char1 >= 97 + n - 10) {
                    return n3 * n2;
                }
                n3 = n3 * n + char1 - 97 + 10;
            }
            ++i;
        }
        return n3 * n2;
    }
    
    public static double atof(final String s) {
        int i = 0;
        int n = 1;
        double n2 = 0.0;
        double n3 = 1.0;
        int n4 = 0;
        while (i < s.length() && Character.isWhitespace(s.charAt(i))) {
            ++i;
        }
        if (i < s.length() && s.charAt(i) == '-') {
            n = -1;
            ++i;
        }
        else if (i < s.length() && s.charAt(i) == '+') {
            ++i;
        }
        while (i < s.length()) {
            final char char1 = s.charAt(i);
            if ('0' <= char1 && char1 <= '9') {
                if (n4 == 0) {
                    n2 = n2 * 10 + char1 - 48;
                }
                else if (n4 == 1) {
                    n3 /= 10;
                    n2 += n3 * (char1 - '0');
                }
            }
            else if (char1 == '.') {
                if (n4 != 0) {
                    return n * n2;
                }
                n4 = 1;
            }
            else {
                if (char1 == 'e' || char1 == 'E') {
                    return n * n2 * Math.pow(10.0, (int)parseLong(s.substring(i + 1), 10));
                }
                return n * n2;
            }
            ++i;
        }
        return n * n2;
    }
    
    public String form(double n) {
        if (Double.isNaN(n) || Double.isInfinite(n)) {
            return "NaN";
        }
        if (this.precision < 0) {
            this.precision = 6;
        }
        int n2 = 1;
        if (n < 0) {
            n = -n;
            n2 = -1;
        }
        String s;
        if (this.fmt == 'f') {
            s = this.fixed_format(n);
        }
        else {
            if (this.fmt != 'e' && this.fmt != 'E' && this.fmt != 'g' && this.fmt != 'G') {
                throw new IllegalArgumentException();
            }
            s = this.exp_format(n);
        }
        return this.pad(this.sign(n2, s));
    }
    
    public String form(final long n) {
        int n2 = 0;
        String s;
        if (this.fmt == 'd' || this.fmt == 'i') {
            if (n < 0) {
                s = String.valueOf("").concat(String.valueOf(n)).substring(1);
                n2 = -1;
            }
            else {
                s = String.valueOf("").concat(String.valueOf(n));
                n2 = 1;
            }
        }
        else if (this.fmt == 'o') {
            s = convert(n, 3, 7, "01234567");
        }
        else if (this.fmt == 'x') {
            s = convert(n, 4, 15, "0123456789abcdef");
        }
        else {
            if (this.fmt != 'X') {
                throw new IllegalArgumentException();
            }
            s = convert(n, 4, 15, "0123456789ABCDEF");
        }
        return this.pad(this.sign(n2, s));
    }
    
    public String form(final char c) {
        if (this.fmt != 'c') {
            throw new IllegalArgumentException();
        }
        return this.pad(String.valueOf("").concat(String.valueOf(c)));
    }
    
    public String form(String substring) {
        if (this.fmt != 's') {
            throw new IllegalArgumentException();
        }
        if (this.precision >= 0) {
            substring = substring.substring(0, this.precision);
        }
        return this.pad(substring);
    }
    
    public static void main(final String[] array) {
        final double n = 1.23456789012;
        final double n2 = 123.0;
        final double n3 = 1.2345E30;
        final double n4 = 1.02;
        final double n5 = 1.234E-5;
        final int n6 = 51966;
        print(System.out, "x = |%f|\n", n);
        print(System.out, "u = |%20f|\n", n5);
        print(System.out, "x = |% .5f|\n", n);
        print(System.out, "w = |%20.5f|\n", n4);
        print(System.out, "x = |%020.5f|\n", n);
        print(System.out, "x = |%+20.5f|\n", n);
        print(System.out, "x = |%+020.5f|\n", n);
        print(System.out, "x = |% 020.5f|\n", n);
        print(System.out, "y = |%#+20.5f|\n", n2);
        print(System.out, "y = |%-+20.5f|\n", n2);
        print(System.out, "z = |%20.5f|\n", n3);
        print(System.out, "x = |%e|\n", n);
        print(System.out, "u = |%20e|\n", n5);
        print(System.out, "x = |% .5e|\n", n);
        print(System.out, "w = |%20.5e|\n", n4);
        print(System.out, "x = |%020.5e|\n", n);
        print(System.out, "x = |%+20.5e|\n", n);
        print(System.out, "x = |%+020.5e|\n", n);
        print(System.out, "x = |% 020.5e|\n", n);
        print(System.out, "y = |%#+20.5e|\n", n2);
        print(System.out, "y = |%-+20.5e|\n", n2);
        print(System.out, "x = |%g|\n", n);
        print(System.out, "z = |%g|\n", n3);
        print(System.out, "w = |%g|\n", n4);
        print(System.out, "u = |%g|\n", n5);
        print(System.out, "y = |%.2g|\n", n2);
        print(System.out, "y = |%#.2g|\n", n2);
        print(System.out, "d = |%d|\n", n6);
        print(System.out, "d = |%20d|\n", n6);
        print(System.out, "d = |%020d|\n", n6);
        print(System.out, "d = |%+20d|\n", n6);
        print(System.out, "d = |% 020d|\n", n6);
        print(System.out, "d = |%-20d|\n", n6);
        print(System.out, "d = |%20.8d|\n", n6);
        print(System.out, "d = |%x|\n", n6);
        print(System.out, "d = |%20X|\n", n6);
        print(System.out, "d = |%#20x|\n", n6);
        print(System.out, "d = |%020X|\n", n6);
        print(System.out, "d = |%20.8x|\n", n6);
        print(System.out, "d = |%o|\n", n6);
        print(System.out, "d = |%020o|\n", n6);
        print(System.out, "d = |%#20o|\n", n6);
        print(System.out, "d = |%#020o|\n", n6);
        print(System.out, "d = |%20.12o|\n", n6);
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
        final StringBuffer sb = new StringBuffer(n);
        for (int i = 0; i < n; ++i) {
            sb.append(c);
        }
        return sb.toString();
    }
    
    private static String convert(long n, final int n2, final int n3, final String s) {
        if (n == 0) {
            return "0";
        }
        String concat = "";
        while (n != 0) {
            concat = String.valueOf(s.charAt((int)(n & n3))).concat(String.valueOf(concat));
            n >>>= n2;
        }
        return concat;
    }
    
    private String pad(final String s) {
        final String repeat = repeat(' ', this.width - s.length());
        if (this.left_align) {
            return String.valueOf(String.valueOf(String.valueOf(this.pre).concat(String.valueOf(s))).concat(String.valueOf(repeat))).concat(String.valueOf(this.post));
        }
        return String.valueOf(String.valueOf(String.valueOf(this.pre).concat(String.valueOf(repeat))).concat(String.valueOf(s))).concat(String.valueOf(this.post));
    }
    
    private String sign(final int n, final String s) {
        String s2 = "";
        if (n < 0) {
            s2 = "-";
        }
        else if (n > 0) {
            if (this.show_plus) {
                s2 = "+";
            }
            else if (this.show_space) {
                s2 = " ";
            }
        }
        else if (this.fmt == 'o' && this.alternate && s.length() > 0 && s.charAt(0) != '0') {
            s2 = "0";
        }
        else if (this.fmt == 'x' && this.alternate) {
            s2 = "0x";
        }
        else if (this.fmt == 'X' && this.alternate) {
            s2 = "0X";
        }
        int n2 = 0;
        if (this.leading_zeroes) {
            n2 = this.width;
        }
        else if ((this.fmt == 'd' || this.fmt == 'i' || this.fmt == 'x' || this.fmt == 'X' || this.fmt == 'o') && this.precision > 0) {
            n2 = this.precision;
        }
        return String.valueOf(String.valueOf(s2).concat(String.valueOf(repeat('0', n2 - s2.length() - s.length())))).concat(String.valueOf(s));
    }
    
    private String fixed_format(final double n) {
        final boolean b = (this.fmt == 'G' || this.fmt == 'g') && !this.alternate;
        if (n > Long.MAX_VALUE) {
            return this.exp_format(n);
        }
        if (this.precision == 0) {
            return String.valueOf((long)(n + 0.5)).concat(String.valueOf(b ? "" : "."));
        }
        long n2 = (long)n;
        final double n3 = n - n2;
        if (n3 >= 1 || n3 < 0) {
            return this.exp_format(n);
        }
        double n4 = 1.0;
        String concat = "";
        for (int n5 = 1; n5 <= this.precision && n4 <= Long.MAX_VALUE; n4 *= 10, concat = String.valueOf(concat).concat(String.valueOf("0")), ++n5) {}
        long n6 = (long)(n4 * n3 + 0.5);
        if (n6 >= n4) {
            n6 = 0L;
            ++n2;
        }
        final String concat2 = String.valueOf(concat).concat(String.valueOf(n6));
        String s = String.valueOf(".").concat(String.valueOf(concat2.substring(concat2.length() - this.precision, concat2.length())));
        if (b) {
            int n7;
            for (n7 = s.length() - 1; n7 >= 0 && s.charAt(n7) == '0'; --n7) {}
            if (n7 >= 0 && s.charAt(n7) == '.') {
                --n7;
            }
            s = s.substring(0, n7 + 1);
        }
        return String.valueOf(n2).concat(String.valueOf(s));
    }
    
    private String exp_format(double n) {
        final String s = "";
        int n2 = 0;
        double n3 = n;
        double n4 = 1.0;
        if (n != 0) {
            while (n3 > 10) {
                ++n2;
                n4 /= 10;
                n3 /= 10;
            }
            while (n3 < 1) {
                --n2;
                n4 *= 10;
                n3 *= 10;
            }
        }
        if ((this.fmt == 'g' || this.fmt == 'G') && n2 >= -4 && n2 < this.precision) {
            return this.fixed_format(n);
        }
        n *= n4;
        final String concat = String.valueOf(s).concat(String.valueOf(this.fixed_format(n)));
        String s2;
        if (this.fmt == 'e' || this.fmt == 'g') {
            s2 = String.valueOf(concat).concat(String.valueOf("e"));
        }
        else {
            s2 = String.valueOf(concat).concat(String.valueOf("E"));
        }
        final String s3 = "000";
        String s4;
        String s5;
        if (n2 >= 0) {
            s4 = String.valueOf(s2).concat(String.valueOf("+"));
            s5 = String.valueOf(s3).concat(String.valueOf(n2));
        }
        else {
            s4 = String.valueOf(s2).concat(String.valueOf("-"));
            s5 = String.valueOf(s3).concat(String.valueOf(-n2));
        }
        return String.valueOf(s4).concat(String.valueOf(s5.substring(s5.length() - 3, s5.length())));
    }
}
