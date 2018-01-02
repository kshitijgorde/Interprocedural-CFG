// 
// Decompiled by Procyon v0.5.30
// 

package com.javathings.math;

import java.util.Enumeration;
import java.util.Vector;

public class zzznf
{
    private static final String[] zzzgf;
    private static final String[] zzzhf;
    private static final int zzzif = 6;
    private Vector zzzjf;
    private String zzzkf;
    private int zzzlf;
    private int zzzmf;
    
    public zzznf() {
        this.zzzjf = new Vector(50);
        this.zzzkf = "x";
        this.zzzlf = 50;
    }
    
    private String zzzue(final String s) {
        s.length();
        int n = 0;
        int i = 2;
        int n2 = 0;
        if (s.charAt(2) == '(') {
            while (i < s.length()) {
                if (s.charAt(i) == '(') {
                    ++n2;
                }
                else if (s.charAt(i) == ')') {
                    --n2;
                }
                if (n2 == 0) {
                    n = i;
                    break;
                }
                ++i;
            }
            return s.substring(2, n + 1);
        }
        return s.substring(2, s.indexOf(" ", 2));
    }
    
    private String zzzqb(final String s) {
        final StringBuffer sb = new StringBuffer();
        sb.append('(').append(s.substring(this.zzzue(s).length() + 2, s.length()));
        return sb.toString();
    }
    
    private String zzzvb(final String s) {
        return this.zzzue(this.zzzqb(s));
    }
    
    private String zzztb(final String s) {
        return this.zzzue(this.zzzqb(this.zzzqb(s)));
    }
    
    private void zzzpe(final String s) throws Exception {
        int i = 0;
        if (!this.zzzef(s)) {
            throw new Exception("Non matching brackets");
        }
        while (i < s.length()) {
            try {
                final String zzzjd;
                if ((zzzjd = this.zzzjd(s, i)) != null) {
                    final String zzzjd2 = this.zzzjd(s, zzzjd.length() + i);
                    if (this.zzzfb(zzzjd2) && !zzzjd2.equals("+") && !zzzjd2.equals("-")) {
                        throw new Exception("Syntax error near -> " + s.substring(i, s.length()));
                    }
                }
                else if (!this.zzzte(s.charAt(i)) && !this.zzzdc(s.charAt(i)) && !this.zzzuc(s.charAt(i))) {
                    throw new Exception("Syntax error near -> " + s.substring(i, s.length()));
                }
            }
            catch (StringIndexOutOfBoundsException ex) {}
            ++i;
        }
    }
    
    private String zzzie(final String s) {
        int i = 0;
        int n = 0;
        String zzzjd = null;
        final StringBuffer sb = new StringBuffer(s);
        while (i < s.length()) {
            try {
                if ((zzzjd = this.zzzjd(s, i)) != null && !this.zzzfb(zzzjd) && this.zzzte(s.charAt(i - 1))) {
                    sb.insert(i + n, '*');
                    ++n;
                }
                else if (this.zzzte(s.charAt(i)) && this.zzzdc(s.charAt(i - 1))) {
                    sb.insert(i + n, '*');
                    ++n;
                }
                else if (s.charAt(i) == '(' && this.zzzdc(s.charAt(i - 1))) {
                    sb.insert(i + n, '*');
                    ++n;
                }
                else if (this.zzzte(s.charAt(i)) && s.charAt(i - 1) == ')') {
                    sb.insert(i + n, '*');
                    ++n;
                }
                else if (s.charAt(i) == '(' && s.charAt(i - 1) == ')') {
                    sb.insert(i + n, '*');
                    ++n;
                }
                else if (s.charAt(i) == '(' && this.zzzte(s.charAt(i - 1)) && this.zzzjb(s.substring(0, i)) == null) {
                    sb.insert(i + n, '*');
                    ++n;
                }
            }
            catch (StringIndexOutOfBoundsException ex) {}
            if (zzzjd != null) {
                i += zzzjd.length();
            }
            else {
                ++i;
            }
            zzzjd = null;
        }
        return sb.toString();
    }
    
    private boolean zzzef(final String s) {
        int n = 0;
        for (int length = s.length(), i = 0; i < length; ++i) {
            if (s.charAt(i) == '(') {
                ++n;
            }
            else if (s.charAt(i) == ')') {
                --n;
            }
        }
        return n == 0;
    }
    
    private String zzzbc(final String s, final String s2, final String s3) {
        final StringBuffer sb = new StringBuffer(this.zzzlf);
        sb.append("( ");
        sb.append(s);
        sb.append(" ");
        sb.append(s2);
        sb.append(" ");
        sb.append(s3);
        sb.append(" )");
        return sb.toString();
    }
    
    private String zzzfc(final String s, final String s2) {
        final StringBuffer sb = new StringBuffer(this.zzzlf);
        sb.append("( ");
        sb.append(s);
        sb.append(" ");
        sb.append(s2);
        sb.append(" )");
        return sb.toString();
    }
    
    private boolean zzzlb(final String s) {
        final int length = s.length();
        if (this.zzzsc(s)) {
            return false;
        }
        for (int i = 0; i < length; ++i) {
            if (this.zzzjd(s, i) != null || this.zzzuc(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    private boolean zzzeb(final String s) {
        if (s == null) {
            return false;
        }
        if (s.length() > 1) {
            return false;
        }
        final char char1 = s.charAt(0);
        return (char1 >= 'a' && char1 <= 'z') || (char1 >= 'A' && char1 <= 'Z');
    }
    
    private boolean zzzte(final char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
    
    private boolean zzzuc(final char c) {
        return c == '(' || c == ')' || c == '.';
    }
    
    private boolean zzzdc(final char c) {
        return Character.isDigit(c);
    }
    
    private boolean zzzmd(final String s) {
        try {
            if (Double.isNaN(Double.valueOf(s))) {
                return false;
            }
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    private boolean zzzsc(final String s) {
        int i = 0;
        int n = 0;
        final char char1 = s.charAt(0);
        if (char1 == '-' || char1 == '+') {
            i = 1;
        }
        while (i < s.length()) {
            final char char2 = s.charAt(i);
            if (!Character.isDigit(char2) && (char2 != '.' || n != 0)) {
                return false;
            }
            if (char2 == '.') {
                n = 1;
            }
            ++i;
        }
        return true;
    }
    
    private boolean zzzpc(final String s, final String s2) {
        return this.zzzlb(s) && this.zzzlb(s2) && s.equalsIgnoreCase(s2);
    }
    
    private boolean zzzee(final String s) {
        for (int i = 0; i < zzznf.zzzgf.length; ++i) {
            if (zzznf.zzzgf[i].equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean zzzfb(final String s) {
        for (int i = 0; i < zzznf.zzzhf.length; ++i) {
            if (zzznf.zzzhf[i].equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean zzzsd(final String s) {
        return Double.valueOf(s) == 1.0;
    }
    
    private boolean zzzcb(final String s) {
        return Double.valueOf(s) == 0.0;
    }
    
    private boolean zzzvd(final double n) {
        return n - (int)n == 0.0;
    }
    
    private boolean zzzed(final int n) {
        return this.zzzvd(n / 2);
    }
    
    private boolean zzzec(final double n) {
        return this.zzzvd(n / 2.0);
    }
    
    private boolean zzzje(final float n) {
        return this.zzzvd(n / 2.0f);
    }
    
    private boolean zzzoe(final String s) {
        return this.zzzue(s).equals("+");
    }
    
    private boolean zzzdf(final String s) {
        return this.zzzue(s).equals("-");
    }
    
    private boolean zzzic(final String s) {
        return this.zzzue(s).equals("*");
    }
    
    private boolean zzznb(final String s) {
        return this.zzzue(s).equals("/");
    }
    
    private boolean zzznc(final String s) {
        return this.zzzue(s).equals("sqrt");
    }
    
    private boolean zzzab(final String s) {
        return this.zzzue(s).equals("cos");
    }
    
    private boolean zzzjc(final String s) {
        return this.zzzue(s).equals("sin");
    }
    
    private boolean zzzob(final String s) {
        return this.zzzue(s).equals("tan");
    }
    
    private boolean zzzkb(final String s) {
        return this.zzzue(s).equals("atan");
    }
    
    private boolean zzzmb(final String s) {
        return this.zzzue(s).equals("acos");
    }
    
    private boolean zzzac(final String s) {
        return this.zzzue(s).equals("asin");
    }
    
    private boolean zzzgb(final String s) {
        return this.zzzue(s).equals("sinh");
    }
    
    private boolean zzzbd(final String s) {
        return this.zzzue(s).equals("cosh");
    }
    
    private boolean zzzbe(final String s) {
        return this.zzzue(s).equals("tanh");
    }
    
    private boolean zzzz(final String s) {
        return this.zzzue(s).equals("ln");
    }
    
    private boolean zzzcd(final String s) {
        return this.zzzue(s).equals("^");
    }
    
    private boolean zzzpb(final String s) {
        return this.zzzue(s).equals("exp");
    }
    
    private boolean zzzoc(final String s) {
        return this.zzzue(s).equals("cotan");
    }
    
    private boolean zzzne(final String s) {
        return this.zzzue(s).equals("acotan");
    }
    
    private String zzzid(final String s) {
        if (!this.zzzmd(s) && !this.zzzlb(s) && this.zzzz(s)) {
            return this.zzzvb(s);
        }
        if (this.zzzmd(s) && this.zzzcb(s)) {
            return "1";
        }
        return this.zzzfc("exp", s);
    }
    
    private String zzzle(final String s, final String s2) {
        if (this.zzzmd(s) && this.zzzmd(s2)) {
            return new StringBuffer().append(Double.valueOf(s) + Double.valueOf(s2)).toString();
        }
        if (this.zzzmd(s) && this.zzzcb(s)) {
            return s2;
        }
        if (this.zzzmd(s2) && this.zzzcb(s2)) {
            return s;
        }
        if (s.equals(s2)) {
            return this.zzzde("2", s);
        }
        if (!this.zzzmd(s) && !this.zzzlb(s)) {
            if (this.zzzmd(s2)) {
                return this.zzzkc(s, s2);
            }
            if (this.zzzlb(s2)) {
                return this.zzzwb(s, s2);
            }
            return this.zzzfd(s, s2);
        }
        else {
            if (this.zzzmd(s2) || this.zzzlb(s2)) {
                return this.zzzbc("+", s, s2);
            }
            if (this.zzzmd(s)) {
                return this.zzzkc(s2, s);
            }
            if (this.zzzlb(s)) {
                return this.zzzwb(s2, s);
            }
            return this.zzzfd(s2, s);
        }
    }
    
    private String zzzqe(final String s) {
        if (this.zzzmd(s) && this.zzzec(Math.sqrt(Double.valueOf(s)))) {
            return new StringBuffer().append(Math.sqrt(Double.valueOf(s))).toString();
        }
        if (!this.zzzmd(s) && !this.zzzlb(s) && this.zzzcd(s) && this.zzzec(Double.valueOf(this.zzztb(s)))) {
            return this.zzzff(this.zzzvb(s), new StringBuffer().append(Double.valueOf(this.zzztb(s)) / 2.0).toString());
        }
        return this.zzzfc("sqrt", s);
    }
    
    private String zzzde(final String s, final String s2) {
        if (this.zzzmd(s) && this.zzzmd(s2)) {
            return new StringBuffer().append(Double.valueOf(s) * Double.valueOf(s2)).toString();
        }
        if (this.zzzmd(s) && this.zzzcb(s)) {
            return "0";
        }
        if (this.zzzmd(s) && this.zzzsd(s)) {
            return s2;
        }
        if (this.zzzmd(s2) && this.zzzcb(s2)) {
            return "0";
        }
        if (this.zzzmd(s2) && this.zzzsd(s2)) {
            return s;
        }
        if (s.equals(s2)) {
            return this.zzzff(s, "2");
        }
        if (!this.zzzmd(s) && !this.zzzlb(s) && !this.zzzmd(s2) && !this.zzzlb(s2)) {
            return this.zzztc(s, s2);
        }
        if (!this.zzzmd(s) && !this.zzzlb(s)) {
            if (this.zzzmd(s2)) {
                return this.zzzyd(s, s2);
            }
            if (this.zzzlb(s2)) {
                return this.zzzme(s, s2);
            }
        }
        else if (!this.zzzmd(s2) && !this.zzzlb(s2)) {
            if (this.zzzmd(s)) {
                return this.zzzyd(s2, s);
            }
            if (this.zzzlb(s)) {
                return this.zzzme(s2, s);
            }
        }
        return this.zzzbc("*", s, s2);
    }
    
    private String zzzsb(final String s, final String s2) {
        if (this.zzzmd(s) && this.zzzmd(s2)) {
            if (Double.valueOf(s2) != 0.0 && this.zzzvd(Double.valueOf(s) / Double.valueOf(s2))) {
                return new StringBuffer().append(Double.valueOf(s) / Double.valueOf(s2)).toString();
            }
        }
        else {
            if (this.zzzmd(s) && this.zzzcb(s)) {
                return "0";
            }
            if (this.zzzmd(s2) && this.zzzsd(s2)) {
                return s;
            }
            if (s.equals(s2)) {
                return "1";
            }
            if (!this.zzzmd(s) && !this.zzzlb(s)) {
                if (this.zzzoe(s) || this.zzzdf(s)) {
                    return this.zzzod(s, s2);
                }
                if (this.zzznb(s)) {
                    if (this.zzzlb(s2) || this.zzzmd(s2)) {
                        return this.zzzsb(this.zzzvb(s), this.zzzde(s2, this.zzztb(s)));
                    }
                    if (this.zzznb(s2)) {
                        return this.zzzsb(this.zzzde(this.zzzvb(s), this.zzztb(s2)), this.zzzde(this.zzztb(s), this.zzzvb(s2)));
                    }
                }
            }
        }
        return this.zzzbc("/", s, s2);
    }
    
    private String zzzad(final String s, final String s2) {
        if (this.zzzmd(s) && this.zzzmd(s2)) {
            return new StringBuffer().append(Double.valueOf(s) - Double.valueOf(s2)).toString();
        }
        if (this.zzzmd(s) && this.zzzcb(s)) {
            return this.zzzde("-1", s2);
        }
        if (this.zzzmd(s2) && this.zzzcb(s2)) {
            return s;
        }
        if (s.equals(s2)) {
            return "0";
        }
        if (!this.zzzmd(s2) && !this.zzzlb(s2) && (this.zzzmd(s) || this.zzzlb(s))) {
            return this.zzzlc(s, s2);
        }
        if (!this.zzzmd(s) && !this.zzzlb(s) && (this.zzzmd(s2) || this.zzzlb(s2))) {
            return this.zzzmc(s, s2);
        }
        if (!this.zzzmd(s) && !this.zzzlb(s) && !this.zzzmd(s2) && !this.zzzlb(s2)) {
            return this.zzzbb(s, s2);
        }
        return this.zzzbc("-", s, s2);
    }
    
    private String zzzff(final String s, final String s2) {
        if (this.zzzmd(s) && this.zzzmd(s2)) {
            if (this.zzzsd(s) || this.zzzcb(s2)) {
                return "1";
            }
            if (this.zzzsd(s2)) {
                return s;
            }
            if (this.zzzvd(Math.pow(Double.valueOf(s), Double.valueOf(s2)))) {
                return new StringBuffer().append(Math.pow(Double.valueOf(s), Double.valueOf(s2))).toString();
            }
        }
        else {
            if (this.zzzmd(s2) && this.zzzcb(s2)) {
                return "1";
            }
            if (this.zzzmd(s2) && this.zzzsd(s2)) {
                return s;
            }
            if (!this.zzzmd(s) && !this.zzzlb(s) && this.zzzcd(s) && this.zzzmd(s2) && this.zzzmd(this.zzztb(s))) {
                return this.zzzff(this.zzzvb(s), this.zzzde(this.zzztb(s), s2));
            }
        }
        return this.zzzbc("^", s, s2);
    }
    
    private String zzzrb(final String s) {
        if (!this.zzzlb(s) && !this.zzzmd(s) && this.zzzac(s)) {
            return this.zzzvb(s);
        }
        if (!this.zzzlb(s) && !this.zzzmd(s) && this.zzzmb(s)) {
            return this.zzzqe(this.zzzad("1", this.zzzff(this.zzzvb(s), "2")));
        }
        return this.zzzfc("sin", s);
    }
    
    private String zzzhb(final String s) {
        if (!this.zzzlb(s) && !this.zzzmd(s) && this.zzzmb(s)) {
            return this.zzzvb(s);
        }
        if (!this.zzzlb(s) && !this.zzzmd(s) && this.zzzac(s)) {
            return this.zzzqe(this.zzzad("1", this.zzzff(this.zzzvb(s), "2")));
        }
        return this.zzzfc("cos", s);
    }
    
    private String zzzrd(final String s) {
        if (!this.zzzlb(s) && !this.zzzmd(s) && this.zzzkb(s)) {
            return this.zzzvb(s);
        }
        if (!this.zzzlb(s) && !this.zzzmd(s) && this.zzzne(s)) {
            return this.zzzsb("1", this.zzzvb(s));
        }
        return this.zzzfc("tan", s);
    }
    
    private String zzzgc(final String s) {
        if (!this.zzzlb(s) && !this.zzzmd(s) && this.zzzpb(s)) {
            return this.zzzvb(s);
        }
        return this.zzzfc("ln", s);
    }
    
    private String zzzxd(final String s) {
        return this.zzzfc("sinh", s);
    }
    
    private String zzzld(final String s) {
        return this.zzzfc("cosh", s);
    }
    
    private String zzzre(final String s) {
        return this.zzzfc("tanh", s);
    }
    
    private String zzzcc(final String s) {
        if (!this.zzzlb(s) && !this.zzzmd(s) && this.zzzne(s)) {
            return this.zzzvb(s);
        }
        return this.zzzfc("cotan", s);
    }
    
    private String zzzye(final String s) {
        if (!this.zzzlb(s) && !this.zzzmd(s) && this.zzzoc(s)) {
            return this.zzzvb(s);
        }
        return this.zzzfc("acotan", s);
    }
    
    private String zzzkc(final String s, final String s2) {
        if (this.zzzoe(s)) {
            if (this.zzzmd(this.zzzvb(s))) {
                final double n = Double.valueOf(s2) + Double.valueOf(this.zzzvb(s));
                if (n >= 0.0) {
                    return this.zzzle(new StringBuffer().append(n).toString(), this.zzztb(s));
                }
                return this.zzzad(this.zzztb(s), new StringBuffer().append(-1.0 * n).toString());
            }
            else if (this.zzzmd(this.zzztb(s))) {
                final double n2 = Double.valueOf(s2) + Double.valueOf(this.zzztb(s));
                if (n2 >= 0.0) {
                    return this.zzzle(new StringBuffer().append(n2).toString(), this.zzzvb(s));
                }
                return this.zzzle(this.zzzvb(s), new StringBuffer().append(-1.0 * n2).toString());
            }
        }
        else if (this.zzzdf(s)) {
            if (this.zzzmd(this.zzzvb(s))) {
                final double n3 = Double.valueOf(s2) + Double.valueOf(this.zzzvb(s));
                if (n3 >= 0.0) {
                    return this.zzzle(new StringBuffer().append(n3).toString(), this.zzztb(s));
                }
                return this.zzzad(this.zzztb(s), new StringBuffer().append(-1.0 * n3).toString());
            }
            else if (this.zzzmd(this.zzztb(s))) {
                final double n4 = Double.valueOf(s2) - Double.valueOf(this.zzztb(s));
                if (n4 >= 0.0) {
                    return this.zzzle(new StringBuffer().append(n4).toString(), this.zzzvb(s));
                }
                return this.zzzad(this.zzzvb(s), new StringBuffer().append(-1.0 * n4).toString());
            }
        }
        return this.zzzbc("+", s, s2);
    }
    
    private String zzzwb(final String s, final String s2) {
        if (this.zzzoe(s)) {
            if (this.zzzlb(this.zzzvb(s)) && this.zzzpc(s2, this.zzzvb(s))) {
                return this.zzzle(this.zzzde("2", s2), this.zzztb(s));
            }
            if (this.zzzlb(this.zzztb(s)) && this.zzzpc(s2, this.zzztb(s))) {
                return this.zzzle(this.zzzde("2", s2), this.zzzvb(s));
            }
        }
        else if (this.zzzdf(s)) {
            if (this.zzzlb(this.zzzvb(s)) && this.zzzpc(s2, this.zzzvb(s))) {
                return this.zzzle(this.zzzde("2", s2), this.zzztb(s));
            }
            if (this.zzzlb(this.zzztb(s)) && this.zzzpc(s2, this.zzztb(s))) {
                return this.zzzvb(s);
            }
        }
        else if (this.zzzic(s)) {
            if (this.zzzmd(this.zzzvb(s)) && this.zzztb(s).equals(s2)) {
                return this.zzzde(this.zzzle("1", this.zzzvb(s)), s2);
            }
            if (this.zzzmd(this.zzztb(s)) && this.zzzvb(s).equals(s2)) {
                return this.zzzde(this.zzzle("1", this.zzztb(s)), s2);
            }
        }
        return this.zzzbc("+", s, s2);
    }
    
    private String zzzfd(final String s, final String s2) {
        if (this.zzzoe(s) && this.zzzoe(s2)) {
            if (this.zzzvb(s).equals(this.zzzvb(s2))) {
                return this.zzzle(this.zzzde("2", this.zzzvb(s)), this.zzzle(this.zzztb(s), this.zzztb(s2)));
            }
            if (this.zzztb(s).equals(this.zzztb(s2))) {
                return this.zzzle(this.zzzde("2", this.zzztb(s)), this.zzzle(this.zzzvb(s), this.zzzvb(s2)));
            }
            if (this.zzzvb(s).equals(this.zzztb(s2))) {
                return this.zzzle(this.zzzde("2", this.zzzvb(s)), this.zzzle(this.zzztb(s), this.zzzvb(s2)));
            }
            if (this.zzztb(s).equals(this.zzzvb(s2))) {
                return this.zzzle(this.zzzde("2", this.zzzvb(s2)), this.zzzle(this.zzzvb(s), this.zzztb(s2)));
            }
        }
        else if (this.zzzoe(s) && this.zzzdf(s2)) {
            if (this.zzzvb(s).equals(this.zzzvb(s2))) {
                return this.zzzle(this.zzzde("2", this.zzzvb(s)), this.zzzad(this.zzztb(s), this.zzztb(s2)));
            }
            if (this.zzzvb(s).equals(this.zzztb(s2))) {
                return this.zzzle(this.zzztb(s), this.zzzvb(s2));
            }
            if (this.zzztb(s).equals(this.zzzvb(s2))) {
                return this.zzzle(this.zzzde("2", this.zzzvb(s2)), this.zzzad(this.zzzvb(s), this.zzztb(s2)));
            }
            if (this.zzztb(s).equals(this.zzztb(s2))) {
                return this.zzzle(this.zzzvb(s), this.zzzvb(s2));
            }
        }
        else if (this.zzzoe(s) && this.zzzic(s2)) {
            if (this.zzzmd(this.zzzvb(s2))) {
                if (this.zzzvb(s).equals(this.zzztb(s2))) {
                    return this.zzzle(this.zzztb(s), this.zzzde(this.zzzle("1", this.zzzvb(s2)), this.zzzvb(s)));
                }
                if (this.zzztb(s).equals(this.zzztb(s2))) {
                    return this.zzzle(this.zzzvb(s), this.zzzde(this.zzzle("1", this.zzzvb(s2)), this.zzztb(s)));
                }
            }
            else if (this.zzzmd(this.zzztb(s2))) {
                if (this.zzzvb(s).equals(this.zzzvb(s2))) {
                    return this.zzzle(this.zzztb(s), this.zzzde(this.zzzle("1", this.zzztb(s2)), this.zzzvb(s)));
                }
                if (this.zzztb(s).equals(this.zzzvb(s2))) {
                    return this.zzzle(this.zzzvb(s), this.zzzde(this.zzzle("1", this.zzztb(s2)), this.zzztb(s)));
                }
            }
        }
        else {
            if (this.zzzdf(s) && this.zzzoe(s2)) {
                return this.zzzfd(s2, s);
            }
            if (this.zzzdf(s) && this.zzzdf(s2)) {
                if (this.zzzvb(s).equals(this.zzzvb(s2))) {
                    return this.zzzad(this.zzzde("2", this.zzzvb(s)), this.zzzle(this.zzztb(s), this.zzztb(s2)));
                }
                if (this.zzzvb(s).equals(this.zzztb(s2))) {
                    return this.zzzad(this.zzzvb(s2), this.zzztb(s));
                }
                if (this.zzztb(s).equals(this.zzzvb(s2))) {
                    return this.zzzad(this.zzzvb(s), this.zzztb(s2));
                }
                if (this.zzztb(s).equals(this.zzztb(s2))) {
                    return this.zzzad(this.zzzle(this.zzzvb(s), this.zzzvb(s2)), this.zzzde("2", this.zzztb(s)));
                }
            }
            else if (this.zzzdf(s) && this.zzzic(s2)) {
                if (this.zzzmd(this.zzzvb(s2))) {
                    if (this.zzzvb(s).equals(this.zzztb(s2))) {
                        return this.zzzad(this.zzzde(this.zzzle("1", this.zzzvb(s2)), this.zzzvb(s)), this.zzztb(s));
                    }
                    if (this.zzztb(s).equals(this.zzztb(s2))) {
                        return this.zzzle(this.zzzde(this.zzzad(this.zzzvb(s2), "1"), this.zzztb(s)), this.zzzvb(s));
                    }
                }
                else if (this.zzzmd(this.zzztb(s2))) {
                    if (this.zzzvb(s).equals(this.zzzvb(s2))) {
                        return this.zzzad(this.zzzde(this.zzzle("1", this.zzztb(s2)), this.zzzvb(s)), this.zzztb(s));
                    }
                    if (this.zzztb(s).equals(this.zzzvb(s2))) {
                        return this.zzzle(this.zzzde(this.zzzad(this.zzztb(s2), "1"), this.zzztb(s)), this.zzzvb(s));
                    }
                }
            }
        }
        return this.zzzbc("+", s, s2);
    }
    
    private String zzzme(final String s, final String s2) {
        if (this.zzzoe(s)) {
            return this.zzzle(this.zzzde(s2, this.zzzvb(s)), this.zzzde(s2, this.zzztb(s)));
        }
        if (this.zzzdf(s)) {
            return this.zzzad(this.zzzde(s2, this.zzzvb(s)), this.zzzde(s2, this.zzztb(s)));
        }
        if (this.zzzcd(s) && s2.equals(this.zzzvb(s))) {
            return this.zzzff(s2, this.zzzle("1", this.zzztb(s)));
        }
        return this.zzzbc("*", s2, s);
    }
    
    private String zzztc(final String s, final String s2) {
        if (this.zzzoe(s) && this.zzzdf(s2) && this.zzzvb(s).equals(this.zzzvb(s2)) && this.zzztb(s).equals(this.zzztb(s2))) {
            return this.zzzad(this.zzzff(this.zzzvb(s), "2"), this.zzzff(this.zzztb(s), "2"));
        }
        if (this.zzzoe(s) && this.zzzdf(s2) && this.zzzvb(s).equals(this.zzztb(s2)) && this.zzztb(s).equals(this.zzzvb(s2))) {
            return this.zzzad(this.zzzff(this.zzztb(s), "2"), this.zzzff(this.zzzvb(s), "2"));
        }
        if (this.zzzdf(s) && this.zzzoe(s2) && this.zzzvb(s).equals(this.zzzvb(s2)) && this.zzztb(s).equals(this.zzztb(s2))) {
            return this.zzzad(this.zzzff(this.zzzvb(s), "2"), this.zzzff(this.zzztb(s), "2"));
        }
        if (this.zzzdf(s) && this.zzzoe(s2) && this.zzzvb(s).equals(this.zzztb(s2)) && this.zzztb(s).equals(this.zzzvb(s2))) {
            return this.zzzad(this.zzzff(this.zzzvb(s), "2"), this.zzzff(this.zzztb(s), "2"));
        }
        return this.zzzbc("*", s, s2);
    }
    
    private String zzzyd(final String s, final String s2) {
        final double doubleValue = Double.valueOf(s2);
        if (this.zzzoe(s)) {
            if (doubleValue < 0.0) {
                return this.zzzad(this.zzzde(s2, this.zzzvb(s)), this.zzzde(new StringBuffer().append(-1.0 * doubleValue).toString(), this.zzztb(s)));
            }
            if (doubleValue > 0.0) {
                return this.zzzle(this.zzzde(s2, this.zzzvb(s)), this.zzzde(s2, this.zzztb(s)));
            }
            return "0";
        }
        else {
            if (!this.zzzdf(s)) {
                if (this.zzzic(s)) {
                    if (this.zzzmd(this.zzzvb(s))) {
                        return this.zzzde(new StringBuffer().append(doubleValue * Double.valueOf(this.zzzvb(s))).toString(), this.zzztb(s));
                    }
                    if (this.zzzmd(this.zzztb(s))) {
                        return this.zzzde(new StringBuffer().append(doubleValue * Double.valueOf(this.zzztb(s))).toString(), this.zzzvb(s));
                    }
                }
                return this.zzzbc("*", s2, s);
            }
            if (doubleValue > 0.0) {
                return this.zzzad(this.zzzde(s2, this.zzzvb(s)), this.zzzde(s2, this.zzztb(s)));
            }
            if (doubleValue < 0.0) {
                return this.zzzle(this.zzzde(s2, this.zzzvb(s)), this.zzzde(new StringBuffer().append(-1.0 * doubleValue).toString(), this.zzztb(s)));
            }
            return "0";
        }
    }
    
    private String zzzod(final String s, final String s2) {
        if (s2.equals(this.zzzvb(s))) {
            if (this.zzzoe(s)) {
                return this.zzzle("1", this.zzzsb(this.zzztb(s), s2));
            }
            if (this.zzzdf(s)) {
                return this.zzzad("1", this.zzzsb(this.zzztb(s), s2));
            }
            if (this.zzzic(s)) {
                return this.zzzsb(this.zzztb(s), s2);
            }
        }
        else if (s2.equals(this.zzztb(s))) {
            if (this.zzzoe(s)) {
                return this.zzzle(this.zzzsb(this.zzzvb(s), s2), "1");
            }
            if (this.zzzdf(s)) {
                return this.zzzad(this.zzzsb(this.zzzvb(s), s2), "1");
            }
            if (this.zzzic(s)) {
                return this.zzzsb(this.zzzvb(s), s2);
            }
        }
        return this.zzzbc("/", s, s2);
    }
    
    private String zzzmc(final String s, final String s2) {
        if (this.zzzmd(s2)) {
            if (this.zzzoe(s)) {
                if (this.zzzmd(this.zzzvb(s))) {
                    return this.zzzle(new StringBuffer().append(Double.valueOf(this.zzzvb(s)) - Double.valueOf(s2)).toString(), this.zzztb(s));
                }
                if (this.zzzmd(this.zzztb(s))) {
                    return this.zzzle(new StringBuffer().append(Double.valueOf(this.zzztb(s)) - Double.valueOf(s2)).toString(), this.zzzvb(s));
                }
            }
            else if (this.zzzdf(s)) {
                if (this.zzzmd(this.zzzvb(s))) {
                    return this.zzzle(new StringBuffer().append(Double.valueOf(this.zzzvb(s)) - Double.valueOf(s2)).toString(), this.zzztb(s));
                }
                if (this.zzzmd(this.zzztb(s))) {
                    return this.zzzle(new StringBuffer().append(-1.0 * Double.valueOf(this.zzztb(s)) - Double.valueOf(s2)).toString(), this.zzzvb(s));
                }
            }
        }
        else if (this.zzzlb(s2)) {
            if (this.zzzoe(s)) {
                if (this.zzzlb(this.zzzvb(s)) && this.zzzpc(s2, this.zzzvb(s))) {
                    return this.zzztb(s);
                }
                if (this.zzzlb(this.zzztb(s)) && this.zzzpc(s2, this.zzztb(s))) {
                    return this.zzzvb(s);
                }
            }
            else if (this.zzzdf(s)) {
                if (this.zzzlb(this.zzzvb(s)) && this.zzzpc(s2, this.zzzvb(s))) {
                    return this.zzzde("-1", this.zzztb(s));
                }
                if (this.zzzlb(this.zzztb(s)) && this.zzzpc(s2, this.zzztb(s))) {
                    return this.zzzad(this.zzzvb(s), this.zzzde("2", this.zzztb(s)));
                }
            }
            else if (this.zzzic(s)) {
                if (this.zzzmd(this.zzzvb(s)) && this.zzztb(s).equals(s2)) {
                    return this.zzzde(this.zzzad(this.zzzvb(s), "1"), s2);
                }
                if (this.zzzmd(this.zzztb(s)) && this.zzzvb(s).equals(s2)) {
                    return this.zzzde(this.zzzad(this.zzztb(s), "1"), s2);
                }
            }
        }
        return this.zzzbc("-", s, s2);
    }
    
    private String zzzlc(final String s, final String s2) {
        if (this.zzzmd(s)) {
            if (this.zzzoe(s2)) {
                if (this.zzzmd(this.zzzvb(s2))) {
                    return this.zzzad(new StringBuffer().append(Double.valueOf(this.zzzvb(s2)) - Double.valueOf(s)).toString(), this.zzztb(s2));
                }
                if (this.zzzmd(this.zzztb(s2))) {
                    return this.zzzad(new StringBuffer().append(Double.valueOf(this.zzztb(s2)) - Double.valueOf(s)).toString(), this.zzzvb(s2));
                }
            }
            else if (this.zzzdf(s2)) {
                if (this.zzzmd(this.zzzvb(s2))) {
                    return this.zzzle(new StringBuffer().append(Double.valueOf(this.zzzvb(s2)) - Double.valueOf(s)).toString(), this.zzztb(s2));
                }
                if (this.zzzmd(this.zzztb(s2))) {
                    return this.zzzad(new StringBuffer().append(Double.valueOf(s) + Double.valueOf(this.zzztb(s2))).toString(), this.zzzvb(s2));
                }
            }
        }
        else if (this.zzzlb(s)) {
            if (this.zzzoe(s2)) {
                if (this.zzzlb(this.zzzvb(s2)) && this.zzzpc(s, this.zzzvb(s2))) {
                    return this.zzzde("-1", this.zzztb(s2));
                }
                if (this.zzzlb(this.zzztb(s2)) && this.zzzpc(s, this.zzztb(s2))) {
                    return this.zzzde("-1", this.zzzvb(s2));
                }
            }
            else if (this.zzzdf(s2)) {
                if (this.zzzlb(this.zzzvb(s2)) && this.zzzpc(s, this.zzzvb(s2))) {
                    return this.zzztb(s2);
                }
                if (this.zzzlb(this.zzztb(s2)) && this.zzzpc(s, this.zzztb(s2))) {
                    return this.zzzad(this.zzzvb(s2), this.zzzde("2", this.zzztb(s2)));
                }
            }
            else if (this.zzzic(s2)) {
                if (this.zzzmd(this.zzzvb(s2)) && this.zzztb(s2).equals(s)) {
                    return this.zzzde(this.zzzad("1", this.zzzvb(s2)), s);
                }
                if (this.zzzmd(this.zzztb(s2)) && this.zzzvb(s2).equals(s)) {
                    return this.zzzde(this.zzzad("1", this.zzztb(s2)), s);
                }
            }
        }
        return this.zzzbc("-", s, s2);
    }
    
    private String zzzbb(final String s, final String s2) {
        if (this.zzzoe(s) && this.zzzoe(s2)) {
            if (this.zzzvb(s).equals(this.zzzvb(s2))) {
                return this.zzzad(this.zzztb(s), this.zzztb(s2));
            }
            if (this.zzzvb(s).equals(this.zzztb(s2))) {
                return this.zzzad(this.zzztb(s), this.zzzvb(s2));
            }
            if (this.zzztb(s).equals(this.zzzvb(s2))) {
                return this.zzzad(this.zzzvb(s), this.zzztb(s2));
            }
            if (this.zzztb(s).equals(this.zzztb(s2))) {
                return this.zzzad(this.zzzvb(s), this.zzzvb(s2));
            }
        }
        else if (this.zzzoe(s) && this.zzzdf(s2)) {
            if (this.zzzvb(s).equals(this.zzzvb(s2))) {
                return this.zzzle(this.zzztb(s), this.zzztb(s2));
            }
            if (this.zzzvb(s).equals(this.zzztb(s2))) {
                return this.zzzle(this.zzzde("2", this.zzzvb(s)), this.zzzad(this.zzztb(s), this.zzzvb(s2)));
            }
            if (this.zzztb(s).equals(this.zzzvb(s2))) {
                return this.zzzle(this.zzzvb(s), this.zzztb(s2));
            }
            if (this.zzztb(s).equals(this.zzztb(s2))) {
                return this.zzzle(this.zzzde("2", this.zzztb(s)), this.zzzad(this.zzzvb(s), this.zzzvb(s2)));
            }
        }
        else if (this.zzzoe(s) && this.zzzic(s2)) {
            if (this.zzzmd(this.zzzvb(s2))) {
                if (this.zzzvb(s).equals(this.zzztb(s2))) {
                    return this.zzzle(this.zzzde(this.zzzad("1", this.zzzvb(s2)), this.zzzvb(s)), this.zzztb(s));
                }
                if (this.zzztb(s).equals(this.zzztb(s2))) {
                    return this.zzzle(this.zzzde(this.zzzad("1", this.zzzvb(s2)), this.zzztb(s)), this.zzzvb(s));
                }
            }
            else if (this.zzzmd(this.zzztb(s2))) {
                if (this.zzzvb(s).equals(this.zzzvb(s2))) {
                    return this.zzzle(this.zzzde(this.zzzad("1", this.zzztb(s2)), this.zzzvb(s)), this.zzztb(s));
                }
                if (this.zzztb(s).equals(this.zzzvb(s2))) {
                    return this.zzzle(this.zzzde(this.zzzad("1", this.zzztb(s2)), this.zzztb(s)), this.zzzvb(s));
                }
            }
        }
        else if (this.zzzdf(s) && this.zzzoe(s2)) {
            if (this.zzzvb(s).equals(this.zzzvb(s2))) {
                return this.zzzad(this.zzzde("-1", this.zzztb(s)), this.zzztb(s2));
            }
            if (this.zzzvb(s).equals(this.zzztb(s2))) {
                return this.zzzad(this.zzzde("-1", this.zzztb(s)), this.zzzvb(s2));
            }
            if (this.zzztb(s).equals(this.zzzvb(s2))) {
                return this.zzzad(this.zzzad(this.zzzvb(s), this.zzztb(s2)), this.zzzde("2", this.zzzvb(s2)));
            }
            if (this.zzztb(s).equals(this.zzztb(s2))) {
                return this.zzzad(this.zzzad(this.zzzvb(s), this.zzzvb(s2)), this.zzzde("2", this.zzztb(s)));
            }
        }
        else if (this.zzzdf(s) && this.zzzdf(s2)) {
            if (this.zzzvb(s).equals(this.zzzvb(s2))) {
                return this.zzzad(this.zzztb(s2), this.zzztb(s));
            }
            if (this.zzzvb(s).equals(this.zzztb(s2))) {
                return this.zzzad(this.zzzde("2", this.zzzvb(s)), this.zzzle(this.zzztb(s), this.zzzvb(s2)));
            }
            if (this.zzztb(s).equals(this.zzzvb(s2))) {
                return this.zzzad(this.zzzle(this.zzzvb(s), this.zzztb(s2)), this.zzzde("2", this.zzzvb(s2)));
            }
            if (this.zzztb(s).equals(this.zzztb(s2))) {
                return this.zzzad(this.zzzvb(s), this.zzzvb(s2));
            }
        }
        else if (this.zzzdf(s) && this.zzzic(s2)) {
            if (this.zzzmd(this.zzzvb(s2))) {
                if (this.zzzvb(s).equals(this.zzztb(s2))) {
                    return this.zzzad(this.zzzde(this.zzzad("1", this.zzzvb(s2)), this.zzzvb(s)), this.zzztb(s));
                }
                if (this.zzztb(s).equals(this.zzztb(s2))) {
                    return this.zzzad(this.zzzde(this.zzzad("-1", this.zzzvb(s2)), this.zzztb(s)), this.zzzvb(s));
                }
            }
            else if (this.zzzmd(this.zzztb(s2))) {
                if (this.zzzvb(s).equals(this.zzzvb(s2))) {
                    return this.zzzad(this.zzzde(this.zzzad("1", this.zzztb(s2)), this.zzzvb(s)), this.zzztb(s));
                }
                if (this.zzztb(s).equals(this.zzzvb(s2))) {
                    return this.zzzad(this.zzzde(this.zzzad("-1", this.zzztb(s2)), this.zzztb(s)), this.zzzvb(s));
                }
            }
        }
        return this.zzzbc("-", s, s2);
    }
    
    private String zzzgd(final String s, final String s2) {
        if (this.zzzmd(s)) {
            return "0";
        }
        if (this.zzzlb(s)) {
            return this.zzzbf(s, s2);
        }
        if (this.zzzoe(s)) {
            return this.zzzzd(s, s2);
        }
        if (this.zzzdf(s)) {
            return this.zzzhc(s, s2);
        }
        if (this.zzzic(s)) {
            return this.zzzyc(s, s2);
        }
        if (this.zzznb(s)) {
            return this.zzzy(s, s2);
        }
        if (this.zzznc(s)) {
            return this.zzzdd(s, s2);
        }
        if (this.zzzjc(s)) {
            return this.zzzub(s, s2);
        }
        if (this.zzzab(s)) {
            return this.zzzqd(s, s2);
        }
        if (this.zzzob(s)) {
            return this.zzzib(s, s2);
        }
        if (this.zzzcd(s)) {
            return this.zzzud(s, s2);
        }
        if (this.zzzz(s)) {
            return this.zzzzb(s, s2);
        }
        if (this.zzzpb(s)) {
            return this.zzztd(s, s2);
        }
        if (this.zzzkb(s)) {
            return this.zzzge(s, s2);
        }
        if (this.zzzac(s)) {
            return this.zzzaf(s, s2);
        }
        if (this.zzzmb(s)) {
            return this.zzzhe(s, s2);
        }
        if (this.zzzgb(s)) {
            return this.zzzdb(s, s2);
        }
        if (this.zzzbd(s)) {
            return this.zzzzc(s, s2);
        }
        if (this.zzzbe(s)) {
            return this.zzzae(s, s2);
        }
        if (this.zzzoc(s)) {
            return this.zzznd(s, s2);
        }
        if (this.zzzne(s)) {
            return this.zzzke(s, s2);
        }
        return "";
    }
    
    private String zzzke(final String s, final String s2) {
        return this.zzzsb(this.zzzde("-1", this.zzzgd(this.zzzvb(s), s2)), this.zzzle("1", this.zzzff(this.zzzvb(s), "2")));
    }
    
    private String zzznd(final String s, final String s2) {
        return this.zzzde(this.zzzgd(this.zzzvb(s), s2), this.zzzad("-1", this.zzzff(this.zzzcc(this.zzzvb(s)), "2")));
    }
    
    private String zzzae(final String s, final String s2) {
        return this.zzzde(this.zzzgd(this.zzzvb(s), s2), this.zzzad("1", this.zzzff(this.zzzre(this.zzzvb(s)), "2")));
    }
    
    private String zzzzc(final String s, final String s2) {
        return this.zzzde(this.zzzgd(this.zzzvb(s), s2), this.zzzxd(this.zzzvb(s)));
    }
    
    private String zzzdb(final String s, final String s2) {
        return this.zzzde(this.zzzgd(this.zzzvb(s), s2), this.zzzld(this.zzzvb(s)));
    }
    
    private String zzzhe(final String s, final String s2) {
        return this.zzzde("-1", this.zzzsb(this.zzzgd(this.zzzvb(s), s2), this.zzzqe(this.zzzad("1", this.zzzff(this.zzzvb(s), "2")))));
    }
    
    private String zzzaf(final String s, final String s2) {
        return this.zzzsb(this.zzzgd(this.zzzvb(s), s2), this.zzzqe(this.zzzad("1", this.zzzff(this.zzzvb(s), "2"))));
    }
    
    private String zzzge(final String s, final String s2) {
        return this.zzzsb(this.zzzgd(this.zzzvb(s), s2), this.zzzle("1", this.zzzff(this.zzzvb(s), "2")));
    }
    
    private String zzztd(final String s, final String s2) {
        return this.zzzde(this.zzzgd(this.zzzvb(s), s2), this.zzzid(this.zzzvb(s)));
    }
    
    private String zzzzb(final String s, final String s2) {
        return this.zzzde(this.zzzgd(this.zzzvb(s), s2), this.zzzsb("1", this.zzzvb(s)));
    }
    
    String zzzib(final String s, final String s2) {
        return this.zzzde(this.zzzgd(this.zzzvb(s), s2), this.zzzle("1", this.zzzff(this.zzzrd(this.zzzvb(s)), "2")));
    }
    
    private String zzzub(final String s, final String s2) {
        return this.zzzde(this.zzzgd(this.zzzvb(s), s2), this.zzzhb(this.zzzvb(s)));
    }
    
    private String zzzqd(final String s, final String s2) {
        return this.zzzde(this.zzzgd(this.zzzvb(s), s2), this.zzzde("-1", this.zzzrb(this.zzzvb(s))));
    }
    
    private String zzzdd(final String s, final String s2) {
        return this.zzzsb(this.zzzgd(this.zzzvb(s), s2), this.zzzde("2", this.zzzqe(this.zzzvb(s))));
    }
    
    private String zzzy(final String s, final String s2) {
        return this.zzzsb(this.zzzad(this.zzzde(this.zzztb(s), this.zzzgd(this.zzzvb(s), s2)), this.zzzde(this.zzzvb(s), this.zzzgd(this.zzztb(s), s2))), this.zzzff(this.zzztb(s), "2"));
    }
    
    private String zzzyc(final String s, final String s2) {
        return this.zzzle(this.zzzde(this.zzzvb(s), this.zzzgd(this.zzztb(s), s2)), this.zzzde(this.zzzgd(this.zzzvb(s), s2), this.zzztb(s)));
    }
    
    private String zzzhc(final String s, final String s2) {
        return this.zzzad(this.zzzgd(this.zzzvb(s), s2), this.zzzgd(this.zzztb(s), s2));
    }
    
    private String zzzzd(final String s, final String s2) {
        return this.zzzle(this.zzzgd(this.zzzvb(s), s2), this.zzzgd(this.zzztb(s), s2));
    }
    
    private String zzzud(final String s, final String s2) {
        if (this.zzzmd(this.zzzvb(s))) {
            return this.zzzde(this.zzzde(this.zzzgc(this.zzzvb(s)), this.zzzgd(this.zzztb(s), s2)), s);
        }
        if (this.zzzmd(this.zzztb(s))) {
            return this.zzzde(this.zzzgd(this.zzzvb(s), s2), this.zzzde(this.zzztb(s), this.zzzff(this.zzzvb(s), this.zzzad(this.zzztb(s), "1"))));
        }
        return this.zzzde(s, this.zzzle(this.zzzde(this.zzzgd(this.zzztb(s), s2), this.zzzgc(this.zzzvb(s))), this.zzzde(this.zzzgd(this.zzzgc(this.zzzvb(s)), s2), this.zzztb(s))));
    }
    
    private String zzzbf(final String s, final String s2) {
        if (this.zzzpc(s, s2)) {
            return "1";
        }
        return "0";
    }
    
    private String zzzkd(final String s) {
        if (this.zzzmd(s)) {
            if (this.zzzvd(Double.valueOf(s))) {
                return new StringBuffer().append((int)(Object)Double.valueOf(s)).toString();
            }
            return s;
        }
        else {
            if (this.zzzlb(s)) {
                return s;
            }
            if (this.zzzoe(s)) {
                return this.zzzle(this.zzzkd(this.zzzvb(s)), this.zzzkd(this.zzztb(s)));
            }
            if (this.zzzdf(s)) {
                return this.zzzad(this.zzzkd(this.zzzvb(s)), this.zzzkd(this.zzztb(s)));
            }
            if (this.zzzic(s)) {
                return this.zzzde(this.zzzkd(this.zzzvb(s)), this.zzzkd(this.zzztb(s)));
            }
            if (this.zzznb(s)) {
                return this.zzzsb(this.zzzkd(this.zzzvb(s)), this.zzzkd(this.zzztb(s)));
            }
            if (this.zzznc(s)) {
                return this.zzzqe(this.zzzkd(this.zzzvb(s)));
            }
            if (this.zzzjc(s)) {
                return this.zzzrb(this.zzzkd(this.zzzvb(s)));
            }
            if (this.zzzab(s)) {
                return this.zzzhb(this.zzzkd(this.zzzvb(s)));
            }
            if (this.zzzob(s)) {
                return this.zzzrd(this.zzzkd(this.zzzvb(s)));
            }
            if (this.zzzcd(s)) {
                return this.zzzff(this.zzzkd(this.zzzvb(s)), this.zzzkd(this.zzztb(s)));
            }
            if (this.zzzz(s)) {
                return this.zzzgc(this.zzzkd(this.zzzvb(s)));
            }
            if (this.zzzpb(s)) {
                return this.zzzid(this.zzzkd(this.zzzvb(s)));
            }
            if (this.zzzgb(s)) {
                return this.zzzxd(this.zzzkd(this.zzzvb(s)));
            }
            if (this.zzzbd(s)) {
                return this.zzzld(this.zzzkd(this.zzzvb(s)));
            }
            if (this.zzzbe(s)) {
                return this.zzzre(this.zzzkd(this.zzzvb(s)));
            }
            if (this.zzzoc(s)) {
                return this.zzzcc(this.zzzkd(this.zzzvb(s)));
            }
            if (this.zzzne(s)) {
                return this.zzzye(this.zzzkd(this.zzzvb(s)));
            }
            return s;
        }
    }
    
    private String zzzrc(final String s) {
        String s2 = "";
        String zzzkd = s;
        while (true) {
            zzzkd = this.zzzkd(zzzkd);
            if (s2.equalsIgnoreCase(zzzkd)) {
                break;
            }
            s2 = zzzkd;
        }
        return zzzkd;
    }
    
    private String zzzcf(final String s) {
        return this.zzzue(s);
    }
    
    private String zzzxc(final String s) {
        if (this.zzzlb(s) || this.zzzmd(s)) {
            return s;
        }
        final String zzzcf = this.zzzcf(s);
        final String zzzvb = this.zzzvb(s);
        if (!this.zzzfb(zzzcf)) {
            return zzzcf + '(' + this.zzzxc(zzzvb) + ')';
        }
        final String zzztb = this.zzztb(s);
        if (this.zzzmd(zzzvb) || this.zzzlb(zzzvb)) {
            if (this.zzzmd(zzztb) || this.zzzlb(zzztb)) {
                return zzzvb + zzzcf + zzztb;
            }
            if (zzzcf.equalsIgnoreCase("+")) {
                return zzzvb + zzzcf + this.zzzxc(zzztb);
            }
            if (zzzcf.equalsIgnoreCase("-") && (this.zzznb(zzztb) || this.zzzic(zzztb))) {
                return zzzvb + zzzcf + this.zzzxc(zzztb);
            }
            if (zzzcf.equalsIgnoreCase("*") && (this.zzzcd(zzztb) || this.zzzic(zzztb) || !this.zzzfb(this.zzzcf(zzztb)))) {
                return zzzvb + zzzcf + this.zzzxc(zzztb);
            }
            if (this.zzzfb(this.zzzcf(zzztb))) {
                return zzzvb + zzzcf + '(' + this.zzzxc(zzztb) + ')';
            }
            return zzzvb + zzzcf + this.zzzxc(zzztb);
        }
        else if (this.zzzmd(zzztb) || this.zzzlb(zzztb)) {
            if (zzzcf.equalsIgnoreCase("+") || zzzcf.equalsIgnoreCase("-")) {
                return this.zzzxc(zzzvb) + zzzcf + zzztb;
            }
            if (this.zzzfb(this.zzzcf(zzzvb))) {
                return '(' + this.zzzxc(zzzvb) + ')' + zzzcf + zzztb;
            }
            return this.zzzxc(zzzvb) + zzzcf + zzztb;
        }
        else {
            if (zzzcf.equalsIgnoreCase("+")) {
                return this.zzzxc(zzzvb) + zzzcf + this.zzzxc(zzztb);
            }
            if (zzzcf.equalsIgnoreCase("-")) {
                if (this.zzzic(zzztb) || this.zzznb(zzztb)) {
                    return this.zzzxc(zzzvb) + zzzcf + this.zzzxc(zzztb);
                }
                return this.zzzxc(zzzvb) + zzzcf + '(' + this.zzzxc(zzztb) + ')';
            }
            else {
                if (this.zzzfb(this.zzzcf(zzzvb)) && this.zzzfb(this.zzzcf(zzztb))) {
                    return '(' + this.zzzxc(zzzvb) + ')' + zzzcf + '(' + this.zzzxc(zzztb) + ')';
                }
                if (this.zzzfb(this.zzzcf(zzzvb)) && !this.zzzfb(this.zzzcf(zzztb))) {
                    return '(' + this.zzzxc(zzzvb) + ')' + zzzcf + this.zzzxc(zzztb);
                }
                if (this.zzzfb(this.zzzcf(zzztb)) && !this.zzzfb(this.zzzcf(zzzvb))) {
                    return this.zzzxc(zzzvb) + zzzcf + '(' + this.zzzxc(zzztb) + ')';
                }
                return this.zzzxc(zzzvb) + zzzcf + this.zzzxc(zzztb);
            }
        }
    }
    
    private String zzzve(final String s) throws Exception {
        String s2 = "";
        int i = 0;
        if (s == "") {
            throw new Exception("Wrong number of arguments to operator");
        }
        if (this.zzzlb(s)) {
            this.zzzfe(s);
            return s;
        }
        if (this.zzzsc(s)) {
            return s;
        }
        final int zzzyb;
        if (s.charAt(0) == '(' && (zzzyb = this.zzzyb(s, 0)) == s.length() - 1) {
            return this.zzzve(s.substring(1, zzzyb));
        }
        while (i < s.length()) {
            final String zzzjd;
            if ((zzzjd = this.zzzjd(s, i)) != null) {
                if (this.zzzfb(zzzjd)) {
                    String s3;
                    if (zzzjd.equalsIgnoreCase("+") || zzzjd.equalsIgnoreCase("-")) {
                        if (s2 == "") {
                            s2 = "0";
                        }
                        s3 = this.zzzvc(s, i + 1);
                    }
                    else if (zzzjd.equalsIgnoreCase("*") || zzzjd.equalsIgnoreCase("/")) {
                        if (s2 == "") {
                            throw new Exception("Wrong number of arguments to operator");
                        }
                        s3 = this.zzzwd(s, i + 1, "^");
                    }
                    else {
                        if (s2 == "") {
                            throw new Exception("Wrong number of arguments to operator");
                        }
                        s3 = this.zzzqc(s, i + zzzjd.length());
                    }
                    s2 = "( " + zzzjd + " " + s2 + " " + this.zzzve(s3) + " )";
                    i += zzzjd.length() + s3.length();
                }
                else {
                    final String zzzqc = this.zzzqc(s, i + zzzjd.length());
                    s2 = String.valueOf(s2) + "( " + zzzjd + " " + this.zzzve(zzzqc) + " )";
                    i += zzzjd.length() + zzzqc.length();
                }
            }
            else {
                final String zzzqc2 = this.zzzqc(s, i);
                final String zzzjd2 = this.zzzjd(s, i + zzzqc2.length());
                if (zzzjd2 == null) {
                    throw new Exception("Missing operator");
                }
                if (this.zzzfb(zzzjd2)) {
                    String s4;
                    if (zzzjd2.equalsIgnoreCase("+") || zzzjd2.equalsIgnoreCase("-")) {
                        s4 = this.zzzvc(s, i + 1 + zzzqc2.length());
                    }
                    else if (zzzjd2.equalsIgnoreCase("*") || zzzjd2.equalsIgnoreCase("/")) {
                        s4 = this.zzzwd(s, i + 1 + zzzqc2.length(), "^");
                    }
                    else {
                        s4 = this.zzzqc(s, i + zzzjd2.length() + zzzqc2.length());
                    }
                    s2 = String.valueOf(s2) + "( " + zzzjd2 + " " + this.zzzve(zzzqc2) + " " + this.zzzve(s4) + " )";
                    i += zzzqc2.length() + s4.length() + zzzjd2.length();
                }
                else {
                    s2 = String.valueOf(s2) + "( " + zzzjd2 + " " + this.zzzve(zzzqc2) + " )";
                    i += zzzjd2.length() + zzzqc2.length();
                }
            }
        }
        return s2;
    }
    
    private String zzzqc(final String s, final int n) {
        final StringBuffer sb = new StringBuffer();
        int i = n;
        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                final int zzzyb = this.zzzyb(s, i);
                sb.append(s.substring(i, zzzyb + 1));
                i = zzzyb + 1;
            }
            else {
                final String zzzjd;
                if ((zzzjd = this.zzzjd(s, i)) != null) {
                    if (sb.length() != 0 && !this.zzzfb(this.zzzjb(sb.toString()))) {
                        return sb.toString();
                    }
                    sb.append(zzzjd);
                    i += zzzjd.length();
                }
                else {
                    sb.append(s.charAt(i));
                    ++i;
                }
            }
        }
        return sb.toString();
    }
    
    private String zzzwd(final String s, final int n, final String s2) {
        final StringBuffer sb = new StringBuffer();
        int i = n;
        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                final int zzzyb = this.zzzyb(s, i);
                sb.append(s.substring(i, zzzyb + 1));
                i = zzzyb + 1;
            }
            else {
                final String zzzjd;
                if ((zzzjd = this.zzzjd(s, i)) != null) {
                    if (sb.length() != 0 && !this.zzzfb(this.zzzjb(sb.toString())) && !zzzjd.equalsIgnoreCase(s2)) {
                        return sb.toString();
                    }
                    sb.append(zzzjd);
                    i += zzzjd.length();
                }
                else {
                    sb.append(s.charAt(i));
                    ++i;
                }
            }
        }
        return sb.toString();
    }
    
    private String zzzvc(final String s, final int n) {
        int i = n;
        String s2 = "";
        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                final int zzzyb = this.zzzyb(s, i);
                s2 = String.valueOf(s2) + s.substring(i, zzzyb + 1);
                i = zzzyb;
            }
            else if ((s.charAt(i) == '+' || s.charAt(i) == '-') && s2 != "") {
                if (!this.zzzfb(this.zzzjb(s2))) {
                    return s2;
                }
                s2 = String.valueOf(s2) + s.charAt(i);
            }
            else {
                s2 = String.valueOf(s2) + s.charAt(i);
            }
            ++i;
        }
        return s2;
    }
    
    private String zzzjb(final String s) {
        try {
            for (int i = 0; i <= 6; ++i) {
                final String zzzjd;
                if ((zzzjd = this.zzzjd(s, s.length() - 1 - 6 + i)) != null && s.length() - 6 - 1 + i + zzzjd.length() == s.length()) {
                    return zzzjd;
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    private String zzzjd(final String s, final int n) {
        final int length = s.length();
        for (int i = 0; i < 6; ++i) {
            if (n >= 0 && n + 6 - i <= length) {
                final String substring = s.substring(n, n + (6 - i));
                if (this.zzzee(substring)) {
                    return substring;
                }
            }
        }
        return null;
    }
    
    private String zzzse(final String s) {
        final StringBuffer sb = new StringBuffer(s);
        int i;
        for (int n = i = 0; i < s.length(); ++i) {
            try {
                if (s.charAt(i) == 'e' && Character.isDigit(s.charAt(i - 1)) && (Character.isDigit(s.charAt(i + 1)) || ((s.charAt(i + 1) == '-' || s.charAt(i + 1) == '+') && Character.isDigit(s.charAt(i + 2))))) {
                    sb.setCharAt(i + n, '*');
                    sb.insert(i + n + 1, "10^");
                    n += 3;
                }
            }
            catch (Exception ex) {}
        }
        return sb.toString();
    }
    
    private String zzzhd(final String s) {
        final StringBuffer sb = new StringBuffer(100);
        for (int length = s.length(), i = 0; i < length; ++i) {
            if (s.charAt(i) != ' ') {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
    
    private String zzzze(final String s) {
        final StringBuffer sb = new StringBuffer(100);
        for (int i = 0; i < s.length(); ++i) {
            try {
                if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                    sb.append('+');
                    ++i;
                }
                else if (s.charAt(i) == '+' && s.charAt(i + 1) == '-') {
                    sb.append('-');
                    ++i;
                }
                else if (s.charAt(i) == '-' && s.charAt(i + 1) == '+') {
                    sb.append('-');
                    ++i;
                }
                else if (s.charAt(i) == '-' && s.charAt(i + 1) == '-') {
                    sb.append('+');
                    ++i;
                }
                else {
                    sb.append(s.charAt(i));
                }
            }
            catch (Exception ex) {}
        }
        return sb.toString();
    }
    
    private int zzzyb(final String s, final int n) {
        int i = n;
        int n2 = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                ++n2;
            }
            else if (s.charAt(i) == ')') {
                --n2;
            }
            if (n2 == 0) {
                return i;
            }
            ++i;
        }
        return n;
    }
    
    private String zzzwe(final String s) {
        if (this.zzzmf >= s.length()) {
            this.zzzmf = 0;
            return null;
        }
        final int index = s.indexOf(";", this.zzzmf);
        if (index == -1) {
            final String substring = s.substring(this.zzzmf, s.length());
            this.zzzmf = s.length();
            return substring;
        }
        final String substring2 = s.substring(this.zzzmf, index);
        this.zzzmf = index + 1;
        return substring2;
    }
    
    private String[] zzzwc(final String[] array) {
        final int length = array.length;
        final String[] array2 = new String[length * 2];
        for (int i = 0; i < length; ++i) {
            array2[i] = array[i];
        }
        return array2;
    }
    
    void zzzfe(final String s) {
        if (!this.zzzjf.contains(s)) {
            this.zzzjf.addElement(s);
        }
    }
    
    public synchronized String zzzxe() {
        final StringBuffer sb = new StringBuffer(50);
        final Enumeration<String> elements = (Enumeration<String>)this.zzzjf.elements();
        int n = 0;
        while (elements.hasMoreElements()) {
            if (n == 0) {
                sb.append(elements.nextElement());
            }
            else {
                sb.append(';').append(elements.nextElement());
            }
            n = 1;
        }
        return sb.toString();
    }
    
    void zzzpd() {
        this.zzzjf.removeAllElements();
    }
    
    public synchronized String[] zzzce(final String s, final String s2) throws zzzfp {
        String[] zzzwc = new String[100];
        int n = 0;
        if (s == null || s.equals("")) {
            throw new zzzfp("Arguments null or empty string");
        }
        this.zzzpd();
        final String zzzie = this.zzzie(this.zzzse(this.zzzhd(s).toLowerCase()));
        try {
            this.zzzpe(zzzie);
            final String zzzrc = this.zzzrc(this.zzzve(zzzie));
            String s3;
            if (s2 == null || s2.equals("")) {
                final String zzzxe = this.zzzxe();
                s3 = this.zzzhd((zzzxe.equals("") ? this.zzzkf : zzzxe).toLowerCase());
            }
            else {
                s3 = this.zzzhd(s2.toLowerCase());
            }
            String zzzwe;
            while ((zzzwe = this.zzzwe(s3)) != null) {
                this.zzzpe(zzzwe);
                if (!this.zzzlb(zzzwe)) {
                    throw new Exception("Not a valid variable " + zzzwe);
                }
                this.zzzlf = zzzrc.length();
                final String zzzrc2 = this.zzzrc(this.zzzgd(zzzrc, zzzwe));
                System.out.println(zzzrc2);
                final String zzzxc = this.zzzxc(zzzrc2);
                if (n > zzzwc.length - 1) {
                    zzzwc = this.zzzwc(zzzwc);
                }
                zzzwc[n] = this.zzzze(zzzxc);
                ++n;
            }
            return zzzwc;
        }
        catch (StringIndexOutOfBoundsException ex2) {
            throw new zzzfp("Wrong number of arguments to operator");
        }
        catch (Exception ex) {
            throw new zzzfp(ex.getMessage());
        }
    }
    
    public synchronized String[] zzzxb(final String s) throws zzzfp {
        return this.zzzce(s, "");
    }
    
    static {
        zzzgf = new String[] { "^", "+", "-", "/", "*", "cos", "sin", "exp", "ln", "tan", "acos", "asin", "atan", "cosh", "sinh", "tanh", "sqrt", "cotan", "acotan" };
        zzzhf = new String[] { "^", "+", "-", "/", "*" };
    }
}
