// 
// Decompiled by Procyon v0.5.30
// 

public class ai
{
    private final char[] V;
    private String W;
    private boolean X;
    private int Y;
    private int Z;
    private int _a;
    private char aa;
    private String[] ba;
    private int ca;
    private int da;
    private int ea;
    private int fa;
    private int ga;
    private int ha;
    private int ia;
    private int ja;
    private int ka;
    
    public ai() {
        this.V = new char[] { '-', '/', '.', ' ' };
        this.W = "";
        this.X = false;
        this.aa = '\0';
        this.ba = new String[3];
    }
    
    public ai(final String s) {
        this.V = new char[] { '-', '/', '.', ' ' };
        this.W = "";
        this.X = false;
        this.aa = '\0';
        this.ba = new String[3];
        this.t(s);
    }
    
    public void t(String upperCase) {
        final boolean y = false;
        this._a = (y ? 1 : 0);
        this.Z = (y ? 1 : 0);
        this.Y = (y ? 1 : 0);
        this.X = false;
        if (upperCase == null || upperCase.length() == 0) {
            throw new IllegalArgumentException("Empty format string.");
        }
        upperCase = upperCase.trim().toUpperCase();
        this.aa = '\0';
        for (int i = 0; i < upperCase.length(); ++i) {
            final char char1 = upperCase.charAt(i);
            for (int j = 0; j < this.V.length; ++j) {
                if (char1 == this.V[j]) {
                    this.aa = this.V[j];
                    break;
                }
            }
            if (this.aa != '\0') {
                break;
            }
        }
        if (this.aa != '\0') {
            for (int k = 0; k < upperCase.length(); ++k) {
                final char char2 = upperCase.charAt(k);
                for (int l = 0; l < this.V.length; ++l) {
                    if (char2 != this.aa && char2 == this.V[l]) {
                        throw new IllegalArgumentException("Invalid format string: " + upperCase + ". Only one separator type is allowed.");
                    }
                }
            }
            int n = 0;
            for (int n2 = 0; n2 < upperCase.length(); ++n2) {
                if (this.aa == upperCase.charAt(n2)) {
                    ++n;
                }
            }
            if (n != 2) {
                throw new IllegalArgumentException("Invalid format string: " + upperCase + ". 0 or 2 separator chars required.");
            }
            final int index = upperCase.indexOf(this.aa);
            final int lastIndex = upperCase.lastIndexOf(this.aa);
            if (index == lastIndex || index == 0 || index == upperCase.length() - 1 || lastIndex == 0 || lastIndex == upperCase.length() - 1) {
                throw new IllegalArgumentException("Invalid format string: " + upperCase);
            }
            this.u(upperCase);
            int n3 = 0;
            int n4 = 0;
            int n5 = 0;
            final int ea = -1;
            this.ca = ea;
            this.da = ea;
            this.ea = ea;
            for (int ca = 0; ca < this.ba.length; ++ca) {
                if ("Y".equals(this.ba[ca]) || "YY".equals(this.ba[ca]) || "YYYY".equals(this.ba[ca])) {
                    ++n3;
                    this.ea = ca;
                }
                if ("M".equals(this.ba[ca]) || "MM".equals(this.ba[ca]) || "MMM".equals(this.ba[ca])) {
                    ++n4;
                    this.da = ca;
                }
                if ("D".equals(this.ba[ca]) || "DD".equals(this.ba[ca])) {
                    ++n5;
                    this.ca = ca;
                }
            }
            if (n3 != 1 || n4 != 1 || n5 != 1) {
                throw new IllegalArgumentException("Invalid format string: " + upperCase);
            }
            if (this.ea == this.da || this.da == this.ca || this.ea == this.ca) {
                throw new IllegalArgumentException("Invalid format string: " + upperCase);
            }
        }
        else {
            for (int n6 = 0; n6 < upperCase.length(); ++n6) {
                final char char3 = upperCase.charAt(n6);
                if (char3 != 'D' && char3 != 'M' && char3 != 'Y') {
                    throw new IllegalArgumentException("Invalid format string: " + upperCase + ". Invalid character: " + char3);
                }
            }
            final int index2 = upperCase.indexOf(68);
            final int lastIndex2 = upperCase.lastIndexOf(68);
            if (index2 == -1 || lastIndex2 == -1 || lastIndex2 != index2 + 1) {
                throw new IllegalArgumentException("Invalid format string: " + upperCase);
            }
            this.fa = index2;
            this.ga = lastIndex2;
            final int index3 = upperCase.indexOf(77);
            final int lastIndex3 = upperCase.lastIndexOf(77);
            if (index3 == -1 || lastIndex3 == -1 || (lastIndex3 != index3 + 1 && lastIndex3 != index3 + 2)) {
                throw new IllegalArgumentException("Invalid format string: " + upperCase);
            }
            this.ha = index3;
            this.ia = lastIndex3;
            final int index4 = upperCase.indexOf(89);
            final int lastIndex4 = upperCase.lastIndexOf(89);
            if (index4 == -1 || lastIndex4 == -1 || (lastIndex4 != index4 + 1 && lastIndex4 != index4 + 3)) {
                throw new IllegalArgumentException("Invalid format string: " + upperCase);
            }
            this.ja = index4;
            this.ka = lastIndex4;
        }
        this.W = upperCase;
        this.X = true;
    }
    
    private void u(final String s) {
        final int index = s.indexOf(this.aa);
        final int lastIndex = s.lastIndexOf(this.aa);
        this.ba[0] = s.substring(0, index);
        this.ba[1] = s.substring(index + 1, lastIndex);
        this.ba[2] = s.substring(lastIndex + 1);
    }
    
    public void v(final String s) {
        final boolean y = false;
        this._a = (y ? 1 : 0);
        this.Z = (y ? 1 : 0);
        this.Y = (y ? 1 : 0);
        if (!this.X) {
            throw new IllegalStateException("Format not initialized.");
        }
        if (this.aa != '\0') {
            this.u(s);
            this.Y = Integer.parseInt(this.ba[this.ca]);
            this.Z = _(this.ba[this.da]);
            this._a = Integer.parseInt(this.ba[this.ea]);
        }
        else {
            this.Y = Integer.parseInt(s.substring(this.fa, this.ga + 1));
            this.Z = _(s.substring(this.ha, this.ia + 1));
            this._a = Integer.parseInt(s.substring(this.ja, this.ka + 1));
        }
        if (this._a != 0 && this._a < 100) {
            if (this._a < 68) {
                this._a += 2000;
            }
            else {
                this._a += 1900;
            }
        }
    }
    
    private static int _(final String s) {
        if (s == null || s.length() == 0) {
            throw new IllegalArgumentException("Invalid month: " + s);
        }
        final char char1 = s.charAt(0);
        if (char1 < '0' || char1 > '9') {
            switch (char1) {
                case 74: {
                    if (s.equals("Jan") || s.equals("JAN")) {
                        return 1;
                    }
                    if (s.equals("Jul") || s.equals("JUL")) {
                        return 7;
                    }
                    if (s.equals("Jun") || s.equals("JUN")) {
                        return 6;
                    }
                }
                case 70: {
                    if (s.equals("Feb") || s.equals("FEB")) {
                        return 2;
                    }
                }
                case 77: {
                    if (s.equals("Mar") || s.equals("MAR")) {
                        return 3;
                    }
                    if (s.equals("May") || s.equals("MAY")) {
                        return 5;
                    }
                }
                case 65: {
                    if (s.equals("Apr") || s.equals("APR")) {
                        return 4;
                    }
                    if (s.equals("Aug") || s.equals("AUG")) {
                        return 8;
                    }
                }
                case 83: {
                    if (s.equals("Sep") || s.equals("SEP")) {
                        return 9;
                    }
                }
                case 79: {
                    if (s.equals("Oct") || s.equals("OCT")) {
                        return 10;
                    }
                }
                case 78: {
                    if (s.equals("Nov") || s.equals("NOV")) {
                        return 11;
                    }
                }
                case 68: {
                    if (s.equals("Dec") || s.equals("DEC")) {
                        return 12;
                    }
                    break;
                }
            }
            throw new IllegalArgumentException("Invalid month: " + s);
        }
        final int int1 = Integer.parseInt(s);
        if (int1 > 12) {
            throw new IllegalArgumentException("Invalid month: " + s);
        }
        return int1;
    }
    
    public int getDay() {
        return this.Y;
    }
    
    public String J() {
        return this.W;
    }
    
    public int getMonth() {
        return this.Z;
    }
    
    public int getYear() {
        return this._a;
    }
}
