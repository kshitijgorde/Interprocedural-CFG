// 
// Decompiled by Procyon v0.5.30
// 

package com.javathings.math;

import java.util.StringTokenizer;
import java.util.Hashtable;

public class zzzzl
{
    Hashtable zzztl;
    Hashtable zzzul;
    Hashtable zzzvl;
    Hashtable zzzwl;
    int zzzxl;
    int zzzyl;
    
    public zzzzl() {
        this.zzztl = new Hashtable(52);
        this.zzzwl = new Hashtable(12);
        this.zzzul = new Hashtable(101);
        this.zzztl.put("^", new zzzw("^", 2, 3));
        this.zzztl.put("+", new zzzw("+", 2, 6));
        this.zzztl.put("-", new zzzw("-", 2, 6));
        this.zzztl.put("/", new zzzw("/", 2, 4));
        this.zzztl.put("*", new zzzw("*", 2, 4));
        this.zzztl.put("cos", new zzzw("cos", 1, 2));
        this.zzztl.put("sin", new zzzw("sin", 1, 2));
        this.zzztl.put("exp", new zzzw("exp", 1, 2));
        this.zzztl.put("ln", new zzzw("ln", 1, 2));
        this.zzztl.put("tan", new zzzw("tan", 1, 2));
        this.zzztl.put("acos", new zzzw("acos", 1, 2));
        this.zzztl.put("asin", new zzzw("asin", 1, 2));
        this.zzztl.put("atan", new zzzw("atan", 1, 2));
        this.zzztl.put("cosh", new zzzw("cosh", 1, 2));
        this.zzztl.put("sinh", new zzzw("sinh", 1, 2));
        this.zzztl.put("tanh", new zzzw("tanh", 1, 2));
        this.zzztl.put("sqrt", new zzzw("sqrt", 1, 2));
        this.zzztl.put("cotan", new zzzw("cotan", 1, 2));
        this.zzztl.put("fpart", new zzzw("fpart", 1, 2));
        this.zzztl.put("acotan", new zzzw("acotan", 1, 2));
        this.zzztl.put("round", new zzzw("round", 1, 2));
        this.zzztl.put("ceil", new zzzw("ceil", 1, 2));
        this.zzztl.put("floor", new zzzw("floor", 1, 2));
        this.zzztl.put("fac", new zzzw("fac", 1, 2));
        this.zzztl.put("sfac", new zzzw("sfac", 1, 2));
        this.zzztl.put("abs", new zzzw("abs", 1, 2));
        this.zzztl.put("log", new zzzw("log", 2, 5));
        this.zzztl.put("%", new zzzw("%", 2, 4));
        this.zzztl.put(">", new zzzw(">", 2, 7));
        this.zzztl.put("<", new zzzw("<", 2, 7));
        this.zzztl.put("&&", new zzzw("&&", 2, 10));
        this.zzztl.put("==", new zzzw("==", 2, 8));
        this.zzztl.put("!=", new zzzw("!=", 2, 8));
        this.zzztl.put("||", new zzzw("||", 2, 9));
        this.zzztl.put("!", new zzzw("!", 1, 1));
        this.zzztl.put(">=", new zzzw(">=", 2, 7));
        this.zzztl.put("<=", new zzzw("<=", 2, 7));
        this.zzzwl.put("euler", new Double(2.718281828459045));
        this.zzzwl.put("pi", new Double(3.141592653589793));
        this.zzzwl.put("nan", new Double(Double.NaN));
        this.zzzwl.put("infinity", new Double(Double.POSITIVE_INFINITY));
        this.zzzwl.put("true", new Double(1.0));
        this.zzzwl.put("false", new Double(0.0));
        this.zzzxl = 6;
        this.zzzyl = 50;
    }
    
    private boolean zzzpl(final String s) {
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
    
    private boolean zzznl(final char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
    
    private boolean zzzll(final String s) {
        final int length = s.length();
        if (this.zzzel(s)) {
            return false;
        }
        for (int i = 0; i < length; ++i) {
            if (this.zzzbl(s, i) != null || this.zzzfl(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    private boolean zzzgl(final char c) {
        return Character.isDigit(c);
    }
    
    private boolean zzzdl(final String s) {
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
    
    private boolean zzzel(final String s) {
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
    
    private boolean zzzil(final String s) {
        return this.zzztl.containsKey(s);
    }
    
    private boolean zzzxk(final String s) {
        if (s == null) {
            return false;
        }
        final zzzw value = this.zzztl.get(s);
        return value != null && value.zzzq() == 2;
    }
    
    private boolean zzzhl(final double n) {
        return n - (int)n == 0.0;
    }
    
    private boolean zzzsl(final int n) {
        return this.zzzhl(n / 2);
    }
    
    private boolean zzzfl(final char c) {
        return c == ')' || c == '(' || c == '.' || c == '>' || c == '<' || c == '&' || c == '=' || c == '|';
    }
    
    private void zzzyk(final String s) throws Exception {
        int i = 0;
        if (!this.zzzpl(s)) {
            throw new Exception("Non matching brackets");
        }
        while (i < s.length()) {
            try {
                final String zzzbl;
                if ((zzzbl = this.zzzbl(s, i)) != null) {
                    final int length = zzzbl.length();
                    i += length;
                    final String zzzbl2 = this.zzzbl(s, i);
                    if (zzzbl2 != null && this.zzzxk(zzzbl2) && !zzzbl2.equals("+") && !zzzbl2.equals("-")) {
                        throw new Exception("Syntax error near -> " + s.substring(i - length, s.length()));
                    }
                    continue;
                }
                else {
                    if (!this.zzznl(s.charAt(i)) && !this.zzzgl(s.charAt(i)) && !this.zzzfl(s.charAt(i))) {
                        throw new Exception("Syntax error near -> " + s.substring(i, s.length()));
                    }
                    ++i;
                }
            }
            catch (StringIndexOutOfBoundsException ex) {
                ++i;
            }
        }
    }
    
    private String zzzvk(final String s) {
        int i = 0;
        int n = 0;
        String zzzbl = null;
        final StringBuffer sb = new StringBuffer(s);
        while (i < s.length()) {
            try {
                if ((zzzbl = this.zzzbl(s, i)) != null && !this.zzzxk(zzzbl) && this.zzznl(s.charAt(i - 1))) {
                    sb.insert(i + n, '*');
                    ++n;
                }
                else if (this.zzznl(s.charAt(i)) && this.zzzgl(s.charAt(i - 1)) && (zzzbl == null || !zzzbl.equals("log"))) {
                    sb.insert(i + n, '*');
                    ++n;
                }
                else if (s.charAt(i) == '(' && this.zzzgl(s.charAt(i - 1))) {
                    sb.insert(i + n, '*');
                    ++n;
                }
                else if (this.zzznl(s.charAt(i)) && s.charAt(i - 1) == ')' && (zzzbl == null || !zzzbl.equals("log"))) {
                    sb.insert(i + n, '*');
                    ++n;
                }
                else if (s.charAt(i) == '(' && s.charAt(i - 1) == ')') {
                    sb.insert(i + n, '*');
                    ++n;
                }
                else if (s.charAt(i) == '(' && this.zzznl(s.charAt(i - 1)) && this.zzzal(s.substring(0, i)) == null) {
                    sb.insert(i + n, '*');
                    ++n;
                }
            }
            catch (StringIndexOutOfBoundsException ex) {}
            if (zzzbl != null) {
                i += zzzbl.length();
            }
            else {
                ++i;
            }
            zzzbl = null;
        }
        return sb.toString();
    }
    
    private String zzzrl(final String s) {
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
    
    private String zzztk(final String s) {
        int i = 0;
        final int length = s.length();
        final StringBuffer sb = new StringBuffer(length);
        while (i < length) {
            if (s.charAt(i) != ' ') {
                sb.append(s.charAt(i));
            }
            ++i;
        }
        return sb.toString();
    }
    
    private int zzzkl(final String s, final int n) {
        final int length = s.length();
        int i = n;
        int n2 = 0;
        while (i < length) {
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
    
    private String zzzbl(final String s, final int n) {
        final int length = s.length();
        for (int i = 0; i < this.zzzxl; ++i) {
            if (n >= 0 && n + this.zzzxl - i <= length) {
                final String substring = s.substring(n, n + (this.zzzxl - i));
                if (this.zzzil(substring)) {
                    return substring;
                }
            }
        }
        return null;
    }
    
    private zzztm zzzsk(final String s) throws Exception {
        zzztm zzztm = null;
        int i = 0;
        final int length = s.length();
        if (length == 0) {
            throw new Exception("Wrong number of arguments to operator");
        }
        final int zzzkl;
        if (s.charAt(0) == '(' && (zzzkl = this.zzzkl(s, 0)) == length - 1) {
            return this.zzzsk(s.substring(1, zzzkl));
        }
        if (this.zzzll(s)) {
            return new zzztm(s);
        }
        if (this.zzzel(s)) {
            return new zzztm(Double.valueOf(s));
        }
        while (i < length) {
            final String zzzbl;
            if ((zzzbl = this.zzzbl(s, i)) == null) {
                final String zzzql = this.zzzql(null, s, i);
                final String zzzbl2 = this.zzzbl(s, i + zzzql.length());
                if (zzzbl2 == null) {
                    throw new Exception("Missing operator");
                }
                if (this.zzzxk(zzzbl2)) {
                    final String zzzql2 = this.zzzql(zzzbl2, s, i + zzzql.length() + zzzbl2.length());
                    if (zzzql2.equals("")) {
                        throw new Exception("Wrong number of arguments to operator " + zzzbl2);
                    }
                    zzztm = new zzztm(zzzbl2, this.zzzsk(zzzql), this.zzzsk(zzzql2));
                    i += zzzql.length() + zzzbl2.length() + zzzql2.length();
                }
                else {
                    if (zzzql.equals("")) {
                        throw new Exception("Wrong number of arguments to operator " + zzzbl2);
                    }
                    zzztm = new zzztm(zzzbl2, this.zzzsk(zzzql));
                    i += zzzql.length() + zzzbl2.length();
                }
            }
            else if (this.zzzxk(zzzbl)) {
                final String zzzql3 = this.zzzql(zzzbl, s, i + zzzbl.length());
                if (zzzql3.equals("")) {
                    throw new Exception("Wrong number of arguments to operator " + zzzbl);
                }
                if (zzztm == null) {
                    if (!zzzbl.equals("+") && !zzzbl.equals("-")) {
                        throw new Exception("Wrong number of arguments to operator " + zzzbl);
                    }
                    zzztm = new zzztm(0.0);
                }
                zzztm = new zzztm(zzzbl, zzztm, this.zzzsk(zzzql3));
                i += zzzql3.length() + zzzbl.length();
            }
            else {
                final String zzzql4 = this.zzzql(zzzbl, s, i + zzzbl.length());
                if (zzzql4.equals("")) {
                    throw new Exception("Wrong number of arguments to operator " + zzzbl);
                }
                zzztm = new zzztm(zzzbl, this.zzzsk(zzzql4));
                i += zzzql4.length() + zzzbl.length();
            }
        }
        return zzztm;
    }
    
    private String zzzql(final String s, final String s2, final int n) {
        final int length = s2.length();
        final StringBuffer sb = new StringBuffer(this.zzzyl);
        int i = n;
        int zzzr;
        if (s == null) {
            zzzr = -1;
        }
        else {
            zzzr = this.zzztl.get(s).zzzr();
        }
        while (i < length) {
            if (s2.charAt(i) == '(') {
                final int zzzkl = this.zzzkl(s2, i);
                sb.append(s2.substring(i, zzzkl + 1));
                i = zzzkl + 1;
            }
            else {
                final String zzzbl;
                if ((zzzbl = this.zzzbl(s2, i)) != null) {
                    if (sb.length() != 0 && !this.zzzxk(this.zzzal(sb.toString())) && ((zzzw)this.zzztl.get(zzzbl)).zzzr() >= zzzr) {
                        return sb.toString();
                    }
                    sb.append(zzzbl);
                    i += zzzbl.length();
                }
                else {
                    sb.append(s2.charAt(i));
                    ++i;
                }
            }
        }
        return sb.toString();
    }
    
    private String zzzal(final String s) {
        final int length = s.length();
        try {
            for (int i = 0; i <= this.zzzxl; ++i) {
                final String zzzbl;
                if ((zzzbl = this.zzzbl(s, length - 1 - this.zzzxl + i)) != null && length - this.zzzxl - 1 + i + zzzbl.length() == length) {
                    return zzzbl;
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    private double zzzcl(final double n) {
        if (!this.zzzhl(n)) {
            return Double.NaN;
        }
        if (n < 0.0) {
            return Double.NaN;
        }
        if (n <= 1.0) {
            return 1.0;
        }
        return n * this.zzzcl(n - 1.0);
    }
    
    private double zzzzk(final double n) {
        if (!this.zzzhl(n)) {
            return Double.NaN;
        }
        if (n < 0.0) {
            return Double.NaN;
        }
        if (n <= 1.0) {
            return 1.0;
        }
        return n * this.zzzzk(n - 2.0);
    }
    
    private double zzzwk(final double n) {
        if (n >= 0.0) {
            return n - Math.floor(n);
        }
        return n - Math.ceil(n);
    }
    
    private final double zzzrk(final zzztm zzztm) throws Exception {
        if (zzztm.zzzcm() == zzztm.zzzim) {
            return zzztm.zzzem();
        }
        if (zzztm.zzzcm() != zzztm.zzzhm) {
            final String zzzfm = zzztm.zzzfm();
            final zzztm zzzbm = zzztm.zzzbm();
            if (zzztm.zzzgm() == 2) {
                final zzztm zzzam = zzztm.zzzam();
                if (zzzfm.equals("+")) {
                    return this.zzzrk(zzzbm) + this.zzzrk(zzzam);
                }
                if (zzzfm.equals("-")) {
                    return this.zzzrk(zzzbm) - this.zzzrk(zzzam);
                }
                if (zzzfm.equals("*")) {
                    return this.zzzrk(zzzbm) * this.zzzrk(zzzam);
                }
                if (zzzfm.equals("/")) {
                    return this.zzzrk(zzzbm) / this.zzzrk(zzzam);
                }
                if (zzzfm.equals("^")) {
                    return Math.pow(this.zzzrk(zzzbm), this.zzzrk(zzzam));
                }
                if (zzzfm.equals("log")) {
                    return Math.log(this.zzzrk(zzzam)) / Math.log(this.zzzrk(zzzbm));
                }
                if (zzzfm.equals("%")) {
                    return this.zzzrk(zzzbm) % this.zzzrk(zzzam);
                }
                if (zzzfm.equals("==")) {
                    if (this.zzzrk(zzzbm) == this.zzzrk(zzzam)) {
                        return 1.0;
                    }
                    return 0.0;
                }
                else if (zzzfm.equals("!=")) {
                    if (this.zzzrk(zzzbm) != this.zzzrk(zzzam)) {
                        return 1.0;
                    }
                    return 0.0;
                }
                else if (zzzfm.equals("<")) {
                    if (this.zzzrk(zzzbm) < this.zzzrk(zzzam)) {
                        return 1.0;
                    }
                    return 0.0;
                }
                else if (zzzfm.equals(">")) {
                    if (this.zzzrk(zzzbm) > this.zzzrk(zzzam)) {
                        return 1.0;
                    }
                    return 0.0;
                }
                else if (zzzfm.equals("&&")) {
                    if (this.zzzrk(zzzbm) == 1.0 && this.zzzrk(zzzam) == 1.0) {
                        return 1.0;
                    }
                    return 0.0;
                }
                else if (zzzfm.equals("||")) {
                    if (this.zzzrk(zzzbm) == 1.0 || this.zzzrk(zzzam) == 1.0) {
                        return 1.0;
                    }
                    return 0.0;
                }
                else if (zzzfm.equals(">=")) {
                    if (this.zzzrk(zzzbm) >= this.zzzrk(zzzam)) {
                        return 1.0;
                    }
                    return 0.0;
                }
                else if (zzzfm.equals("<=")) {
                    if (this.zzzrk(zzzbm) <= this.zzzrk(zzzam)) {
                        return 1.0;
                    }
                    return 0.0;
                }
            }
            else {
                if (zzzfm.equals("sqrt")) {
                    return Math.sqrt(this.zzzrk(zzzbm));
                }
                if (zzzfm.equals("sin")) {
                    return Math.sin(this.zzzrk(zzzbm));
                }
                if (zzzfm.equals("cos")) {
                    return Math.cos(this.zzzrk(zzzbm));
                }
                if (zzzfm.equals("tan")) {
                    return Math.tan(this.zzzrk(zzzbm));
                }
                if (zzzfm.equals("asin")) {
                    return Math.asin(this.zzzrk(zzzbm));
                }
                if (zzzfm.equals("acos")) {
                    return Math.acos(this.zzzrk(zzzbm));
                }
                if (zzzfm.equals("atan")) {
                    return Math.atan(this.zzzrk(zzzbm));
                }
                if (zzzfm.equals("ln")) {
                    return Math.log(this.zzzrk(zzzbm));
                }
                if (zzzfm.equals("exp")) {
                    return Math.exp(this.zzzrk(zzzbm));
                }
                if (zzzfm.equals("cotan")) {
                    return 1.0 / Math.tan(this.zzzrk(zzzbm));
                }
                if (zzzfm.equals("acotan")) {
                    return 1.5707963267948966 - Math.atan(this.zzzrk(zzzbm));
                }
                if (zzzfm.equals("ceil")) {
                    return Math.ceil(this.zzzrk(zzzbm));
                }
                if (zzzfm.equals("round")) {
                    return Math.round(this.zzzrk(zzzbm));
                }
                if (zzzfm.equals("floor")) {
                    return Math.floor(this.zzzrk(zzzbm));
                }
                if (zzzfm.equals("fac")) {
                    return this.zzzcl(this.zzzrk(zzzbm));
                }
                if (zzzfm.equals("abs")) {
                    return Math.abs(this.zzzrk(zzzbm));
                }
                if (zzzfm.equals("fpart")) {
                    return this.zzzwk(this.zzzrk(zzzbm));
                }
                if (zzzfm.equals("sfac")) {
                    return this.zzzzk(this.zzzrk(zzzbm));
                }
                if (zzzfm.equals("sinh")) {
                    final double zzzrk = this.zzzrk(zzzbm);
                    return (Math.exp(zzzrk) - 1.0 / Math.exp(zzzrk)) / 2.0;
                }
                if (zzzfm.equals("cosh")) {
                    final double zzzrk2 = this.zzzrk(zzzbm);
                    return (Math.exp(zzzrk2) + 1.0 / Math.exp(zzzrk2)) / 2.0;
                }
                if (zzzfm.equals("tanh")) {
                    final double zzzrk3 = this.zzzrk(zzzbm);
                    return (Math.exp(zzzrk3) - 1.0 / Math.exp(zzzrk3)) / 2.0 / ((Math.exp(zzzrk3) + 1.0 / Math.exp(zzzrk3)) / 2.0);
                }
                if (zzzfm.equals("!")) {
                    if (this.zzzrk(zzzbm) != 1.0) {
                        return 1.0;
                    }
                    return 0.0;
                }
            }
            throw new Exception("Unknown operator");
        }
        final String zzzdm = zzztm.zzzdm();
        if (this.zzzwl.containsKey(zzzdm)) {
            return (double)this.zzzwl.get(zzzdm);
        }
        final String zzzml = this.zzzml(zzzdm);
        if (this.zzzdl(zzzml)) {
            return Double.valueOf(zzzml);
        }
        this.zzzyk(zzzml);
        return this.zzzrk(this.zzzsk(this.zzzvk(this.zzzrl(zzzml))));
    }
    
    private String zzzml(final String s) throws Exception {
        final String value = this.zzzvl.get(s);
        if (value == null) {
            throw new Exception("No value associated with " + s);
        }
        String s2;
        try {
            s2 = value;
        }
        catch (ClassCastException ex) {
            throw new Exception("Wrong type value for " + s + " expected String");
        }
        return s2;
    }
    
    public synchronized double zzzjl(final String s, final Hashtable zzzvl) throws zzzep {
        if (s == null || s.equals("")) {
            throw new zzzep("First argument to method eval is null or empty string");
        }
        if (zzzvl == null) {
            return this.zzzol(s);
        }
        this.zzzvl = zzzvl;
        final String zzztk = this.zzztk(s.toLowerCase());
        this.zzzyl = zzztk.length();
        try {
            double n;
            if (this.zzzul.containsKey(zzztk)) {
                n = this.zzzrk(this.zzzul.get(zzztk));
            }
            else {
                this.zzzyk(zzztk);
                final zzztm zzzsk = this.zzzsk(this.zzzvk(this.zzzrl(zzztk)));
                n = this.zzzrk(zzzsk);
                this.zzzul.put(zzztk, zzzsk);
            }
            return n;
        }
        catch (Exception ex) {
            throw new zzzep(ex.getMessage());
        }
    }
    
    public synchronized double zzzuk(final String s, String zzztk) throws zzzep {
        String nextToken = "";
        final Hashtable<String, String> hashtable = new Hashtable<String, String>(1001);
        if (s == null || s.equals("")) {
            throw new zzzep("First argument to method eval is null or empty string");
        }
        if (zzztk == null || zzztk.equals("")) {
            return this.zzzol(s);
        }
        try {
            zzztk = this.zzztk(zzztk.toLowerCase());
            final StringTokenizer stringTokenizer = new StringTokenizer(zzztk, ";", false);
            while (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
                hashtable.put(nextToken.substring(0, nextToken.indexOf("=")), nextToken.substring(nextToken.indexOf("=") + 1, nextToken.length()));
            }
        }
        catch (StringIndexOutOfBoundsException ex) {
            throw new zzzep("Syntax error ->" + zzztk);
        }
        catch (Exception ex2) {
            throw new zzzep("Syntax error ->" + nextToken);
        }
        return this.zzzjl(s, hashtable);
    }
    
    public synchronized double zzzol(final String s) throws zzzep {
        return this.zzzjl(s, new Hashtable());
    }
}
