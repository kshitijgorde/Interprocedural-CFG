// 
// Decompiled by Procyon v0.5.30
// 

public class ep
{
    private final char[] KGb;
    private String LGb;
    private boolean MGb;
    private int NGb;
    private int OGb;
    private int PGb;
    private char QGb;
    private String[] RGb;
    private int SGb;
    private int TGb;
    private int UGb;
    private int VGb;
    private int WGb;
    private int XGb;
    private int YGb;
    private int ZGb;
    private int _Hb;
    
    public ep() {
        this.KGb = new char[] { '-', '/', '.', ' ' };
        this.LGb = "";
        this.MGb = false;
        this.QGb = '\0';
        this.RGb = new String[3];
    }
    
    public ep(final String s) {
        this.KGb = new char[] { '-', '/', '.', ' ' };
        this.LGb = "";
        this.MGb = false;
        this.QGb = '\0';
        this.RGb = new String[3];
        this.x(s);
    }
    
    public void x(String upperCase) {
        final boolean nGb = false;
        this.PGb = (nGb ? 1 : 0);
        this.OGb = (nGb ? 1 : 0);
        this.NGb = (nGb ? 1 : 0);
        this.MGb = false;
        if (upperCase == null || upperCase.length() == 0) {
            throw new IllegalArgumentException("Empty format string.");
        }
        upperCase = upperCase.trim().toUpperCase();
        this.QGb = '\0';
        for (int i = 0; i < upperCase.length(); ++i) {
            final char char1 = upperCase.charAt(i);
            for (int j = 0; j < this.KGb.length; ++j) {
                if (char1 == this.KGb[j]) {
                    this.QGb = this.KGb[j];
                    break;
                }
            }
            if (this.QGb != '\0') {
                break;
            }
        }
        if (this.QGb != '\0') {
            for (int k = 0; k < upperCase.length(); ++k) {
                final char char2 = upperCase.charAt(k);
                for (int l = 0; l < this.KGb.length; ++l) {
                    if (char2 != this.QGb && char2 == this.KGb[l]) {
                        throw new IllegalArgumentException("Invalid format string: " + upperCase + ". Only one separator type is allowed.");
                    }
                }
            }
            int n = 0;
            for (int n2 = 0; n2 < upperCase.length(); ++n2) {
                if (this.QGb == upperCase.charAt(n2)) {
                    ++n;
                }
            }
            if (n != 2) {
                throw new IllegalArgumentException("Invalid format string: " + upperCase + ". 0 or 2 separator chars required.");
            }
            final int index = upperCase.indexOf(this.QGb);
            final int lastIndex = upperCase.lastIndexOf(this.QGb);
            if (index == lastIndex || index == 0 || index == upperCase.length() - 1 || lastIndex == 0 || lastIndex == upperCase.length() - 1) {
                throw new IllegalArgumentException("Invalid format string: " + upperCase);
            }
            this.y(upperCase);
            int n3 = 0;
            int n4 = 0;
            int n5 = 0;
            final int uGb = -1;
            this.SGb = uGb;
            this.TGb = uGb;
            this.UGb = uGb;
            for (int sGb = 0; sGb < this.RGb.length; ++sGb) {
                if ("Y".equals(this.RGb[sGb]) || "YY".equals(this.RGb[sGb]) || "YYYY".equals(this.RGb[sGb])) {
                    ++n3;
                    this.UGb = sGb;
                }
                if ("M".equals(this.RGb[sGb]) || "MM".equals(this.RGb[sGb]) || "MMM".equals(this.RGb[sGb])) {
                    ++n4;
                    this.TGb = sGb;
                }
                if ("D".equals(this.RGb[sGb]) || "DD".equals(this.RGb[sGb])) {
                    ++n5;
                    this.SGb = sGb;
                }
            }
            if (n3 != 1 || n4 != 1 || n5 != 1) {
                throw new IllegalArgumentException("Invalid format string: " + upperCase);
            }
            if (this.UGb == this.TGb || this.TGb == this.SGb || this.UGb == this.SGb) {
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
            this.VGb = index2;
            this.WGb = lastIndex2;
            final int index3 = upperCase.indexOf(77);
            final int lastIndex3 = upperCase.lastIndexOf(77);
            if (index3 == -1 || lastIndex3 == -1 || (lastIndex3 != index3 + 1 && lastIndex3 != index3 + 2)) {
                throw new IllegalArgumentException("Invalid format string: " + upperCase);
            }
            this.XGb = index3;
            this.YGb = lastIndex3;
            final int index4 = upperCase.indexOf(89);
            final int lastIndex4 = upperCase.lastIndexOf(89);
            if (index4 == -1 || lastIndex4 == -1 || (lastIndex4 != index4 + 1 && lastIndex4 != index4 + 3)) {
                throw new IllegalArgumentException("Invalid format string: " + upperCase);
            }
            this.ZGb = index4;
            this._Hb = lastIndex4;
        }
        this.LGb = upperCase;
        this.MGb = true;
    }
    
    private void y(final String s) {
        final int index = s.indexOf(this.QGb);
        final int lastIndex = s.lastIndexOf(this.QGb);
        this.RGb[0] = s.substring(0, index);
        this.RGb[1] = s.substring(index + 1, lastIndex);
        this.RGb[2] = s.substring(lastIndex + 1);
    }
    
    public void z(final String s) {
        final boolean nGb = false;
        this.PGb = (nGb ? 1 : 0);
        this.OGb = (nGb ? 1 : 0);
        this.NGb = (nGb ? 1 : 0);
        if (!this.MGb) {
            throw new IllegalStateException("Format not initialized.");
        }
        if (this.QGb != '\0') {
            this.y(s);
            this.NGb = Integer.parseInt(this.RGb[this.SGb]);
            this.OGb = f(this.RGb[this.TGb]);
            this.PGb = Integer.parseInt(this.RGb[this.UGb]);
        }
        else {
            this.NGb = Integer.parseInt(s.substring(this.VGb, this.WGb + 1));
            this.OGb = f(s.substring(this.XGb, this.YGb + 1));
            this.PGb = Integer.parseInt(s.substring(this.ZGb, this._Hb + 1));
        }
        if (this.PGb != 0 && this.PGb < 100) {
            if (this.PGb < 68) {
                this.PGb += 2000;
            }
            else {
                this.PGb += 1900;
            }
        }
    }
    
    private static int f(final String s) {
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
        return this.NGb;
    }
    
    public String e() {
        return this.LGb;
    }
    
    public int getMonth() {
        return this.OGb;
    }
    
    public int getYear() {
        return this.PGb;
    }
}
