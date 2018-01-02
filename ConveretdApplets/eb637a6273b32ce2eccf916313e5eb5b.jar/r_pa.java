// 
// Decompiled by Procyon v0.5.30
// 

public class r_pa
{
    static String sU;
    public String sURL;
    public String sN;
    public String sP;
    public String sR;
    public int iType;
    
    public boolean get(final r_pa r_pa, final String s, final char c) {
        int n = 0;
        int n2 = 0;
        if (s == null) {
            return false;
        }
        while (true) {
            final int index = s.indexOf(c, n);
            String s2;
            if (index > 0) {
                s2 = new String(s.substring(n, index).trim());
            }
            else {
                s2 = new String(s.substring(n).trim());
            }
            if (s2.length() > 0) {
                if (n2 == 0) {
                    r_pa.sN = new String(s2);
                }
                if (n2 == 1) {
                    r_pa.sP = new String(s2);
                }
                if (n2 == 2) {
                    r_pa.sR = new String(s2);
                }
                if (n2 == 3) {
                    r_pa.sURL = new String(s2);
                }
                ++n2;
            }
            if (index == -1) {
                break;
            }
            n = index + 1;
        }
        if (r_pa.sP.equals("-") && r_pa.sURL == null) {
            r_pa.sU = r_pa.sN;
        }
        else if (r_pa.sURL == null) {
            r_pa.sURL = r_pa.sU;
        }
        else if (r_pa.sURL.equals("")) {
            r_pa.sURL = r_pa.sU;
        }
        return n2 > 0;
    }
    
    public String toString() {
        return this.sN + " § -- § " + this.sR + " § " + this.sURL;
    }
    
    static {
        r_pa.sU = "";
    }
    
    public boolean verif(final String s, final String s2) {
        return s.equals(this.sN) && s2.equals(this.sP);
    }
    
    public r_pa() {
        this.sN = "";
        this.sP = "";
    }
}
