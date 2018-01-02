// 
// Decompiled by Procyon v0.5.30
// 

public class null
{
    private final char[] upa;
    private String vpa;
    private boolean wpa;
    private int xpa;
    private int ypa;
    private int zpa;
    private char Apa;
    private String[] Bpa;
    private int Cpa;
    private int Dpa;
    private int Epa;
    private int Fpa;
    private int Gpa;
    private int Hpa;
    private int Ipa;
    private int Jpa;
    private int Kpa;
    
    public null() {
        this.upa = new char[] { '-', '/', '.', ' ' };
        this.vpa = "";
        this.wpa = false;
        this.Apa = '\0';
        this.Bpa = new String[3];
    }
    
    public null(final String s) {
        this.upa = new char[] { '-', '/', '.', ' ' };
        this.vpa = "";
        this.wpa = false;
        this.Apa = '\0';
        this.Bpa = new String[3];
        this.t(s);
    }
    
    public void t(String upperCase) {
        final boolean xpa = false;
        this.zpa = (xpa ? 1 : 0);
        this.ypa = (xpa ? 1 : 0);
        this.xpa = (xpa ? 1 : 0);
        this.wpa = false;
        if (upperCase == null || upperCase.length() == 0) {
            throw new IllegalArgumentException("Empty format string.");
        }
        upperCase = upperCase.trim().toUpperCase();
        this.Apa = '\0';
        for (int i = 0; i < upperCase.length(); ++i) {
            final char char1 = upperCase.charAt(i);
            for (int j = 0; j < this.upa.length; ++j) {
                if (char1 == this.upa[j]) {
                    this.Apa = this.upa[j];
                    break;
                }
            }
            if (this.Apa != '\0') {
                break;
            }
        }
        if (this.Apa != '\0') {
            for (int k = 0; k < upperCase.length(); ++k) {
                final char char2 = upperCase.charAt(k);
                for (int l = 0; l < this.upa.length; ++l) {
                    if (char2 != this.Apa && char2 == this.upa[l]) {
                        throw new IllegalArgumentException("Invalid format string: " + upperCase + ". Only one separator type is allowed.");
                    }
                }
            }
            int n = 0;
            for (int n2 = 0; n2 < upperCase.length(); ++n2) {
                if (this.Apa == upperCase.charAt(n2)) {
                    ++n;
                }
            }
            if (n != 2) {
                throw new IllegalArgumentException("Invalid format string: " + upperCase + ". 0 or 2 separator chars required.");
            }
            final int index = upperCase.indexOf(this.Apa);
            final int lastIndex = upperCase.lastIndexOf(this.Apa);
            if (index == lastIndex || index == 0 || index == upperCase.length() - 1 || lastIndex == 0 || lastIndex == upperCase.length() - 1) {
                throw new IllegalArgumentException("Invalid format string: " + upperCase);
            }
            this.u(upperCase);
            int n3 = 0;
            int n4 = 0;
            int n5 = 0;
            final int epa = -1;
            this.Cpa = epa;
            this.Dpa = epa;
            this.Epa = epa;
            for (int cpa = 0; cpa < this.Bpa.length; ++cpa) {
                if ("Y".equals(this.Bpa[cpa]) || "YY".equals(this.Bpa[cpa]) || "YYYY".equals(this.Bpa[cpa])) {
                    ++n3;
                    this.Epa = cpa;
                }
                if ("M".equals(this.Bpa[cpa]) || "MM".equals(this.Bpa[cpa]) || "MMM".equals(this.Bpa[cpa])) {
                    ++n4;
                    this.Dpa = cpa;
                }
                if ("D".equals(this.Bpa[cpa]) || "DD".equals(this.Bpa[cpa])) {
                    ++n5;
                    this.Cpa = cpa;
                }
            }
            if (n3 != 1 || n4 != 1 || n5 != 1) {
                throw new IllegalArgumentException("Invalid format string: " + upperCase);
            }
            if (this.Epa == this.Dpa || this.Dpa == this.Cpa || this.Epa == this.Cpa) {
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
            this.Fpa = index2;
            this.Gpa = lastIndex2;
            final int index3 = upperCase.indexOf(77);
            final int lastIndex3 = upperCase.lastIndexOf(77);
            if (index3 == -1 || lastIndex3 == -1 || (lastIndex3 != index3 + 1 && lastIndex3 != index3 + 2)) {
                throw new IllegalArgumentException("Invalid format string: " + upperCase);
            }
            this.Hpa = index3;
            this.Ipa = lastIndex3;
            final int index4 = upperCase.indexOf(89);
            final int lastIndex4 = upperCase.lastIndexOf(89);
            if (index4 == -1 || lastIndex4 == -1 || (lastIndex4 != index4 + 1 && lastIndex4 != index4 + 3)) {
                throw new IllegalArgumentException("Invalid format string: " + upperCase);
            }
            this.Jpa = index4;
            this.Kpa = lastIndex4;
        }
        this.vpa = upperCase;
        this.wpa = true;
    }
    
    private void u(final String s) {
        final int index = s.indexOf(this.Apa);
        final int lastIndex = s.lastIndexOf(this.Apa);
        this.Bpa[0] = s.substring(0, index);
        this.Bpa[1] = s.substring(index + 1, lastIndex);
        this.Bpa[2] = s.substring(lastIndex + 1);
    }
    
    public void v(final String s) {
        final boolean xpa = false;
        this.zpa = (xpa ? 1 : 0);
        this.ypa = (xpa ? 1 : 0);
        this.xpa = (xpa ? 1 : 0);
        if (!this.wpa) {
            throw new IllegalStateException("Format not initialized.");
        }
        if (this.Apa != '\0') {
            this.u(s);
            this.xpa = Integer.parseInt(this.Bpa[this.Cpa]);
            this.ypa = b(this.Bpa[this.Dpa]);
            this.zpa = Integer.parseInt(this.Bpa[this.Epa]);
        }
        else {
            this.xpa = Integer.parseInt(s.substring(this.Fpa, this.Gpa + 1));
            this.ypa = b(s.substring(this.Hpa, this.Ipa + 1));
            this.zpa = Integer.parseInt(s.substring(this.Jpa, this.Kpa + 1));
        }
        if (this.zpa != 0 && this.zpa < 100) {
            if (this.zpa < 68) {
                this.zpa += 2000;
            }
            else {
                this.zpa += 1900;
            }
        }
    }
    
    private static int b(final String s) {
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
        return this.xpa;
    }
    
    public String D() {
        return this.vpa;
    }
    
    public int getMonth() {
        return this.ypa;
    }
    
    public int getYear() {
        return this.zpa;
    }
}
