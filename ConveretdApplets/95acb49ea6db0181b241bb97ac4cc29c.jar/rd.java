// 
// Decompiled by Procyon v0.5.30
// 

public class rd
{
    static double iNan;
    public int iN;
    public String sIt;
    public String sUrl;
    public String sFont;
    public String sBg;
    public double[] iD;
    
    public boolean get(final rd rd, final String s, final char c, int in) {
        int n = 0;
        int n2 = 0;
        Double value = new Double(0.0);
        this.iN = in;
        this.iD = new double[this.iN];
        for (int i = 0; i < this.iN; this.iD[i++] = rd.iNan) {}
        in = 0;
        if (s == null) {
            return false;
        }
        while (true) {
            final int index = s.indexOf(c, in);
            String s2;
            if (index > 0) {
                s2 = new String(s.substring(in, index).trim());
            }
            else {
                s2 = new String(s.substring(in).trim());
            }
            boolean b;
            try {
                value = Double.valueOf(s2);
                b = (n2 >= 1);
            }
            catch (NumberFormatException ex) {
                b = false;
            }
            if (s2.length() == 0 && n2 > 0) {
                b = true;
                value = new Double(rd.iNan);
            }
            if (index > -1 || s2.length() > 0) {
                if (b) {
                    if (n < this.iN) {
                        rd.iD[n++] = value;
                    }
                }
                else {
                    if (n2 == 0) {
                        rd.sIt = new String(s2);
                    }
                    if (n2 == 1) {
                        rd.sUrl = new String(s2);
                    }
                    if (n2 == 2) {
                        rd.sFont = new String(s2);
                    }
                    if (n2 == 3) {
                        rd.sBg = new String(s2);
                    }
                    ++n2;
                }
            }
            if (index == -1) {
                break;
            }
            in = index + 1;
        }
        return n + n2 > 0;
    }
    
    public rd() {
        this.iN = 1;
        this.sIt = "";
        this.sUrl = "";
        this.sFont = "Arial";
        this.sBg = "";
    }
    
    static {
        rd.iNan = -1.254E-4;
    }
}
