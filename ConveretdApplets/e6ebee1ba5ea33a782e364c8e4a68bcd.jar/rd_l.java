// 
// Decompiled by Procyon v0.5.30
// 

public class rd_l
{
    public String[] sDesc;
    public String sURL;
    public String sTarg;
    public String sH;
    public String sH2;
    public String sVal;
    public String sKeys;
    public int iN;
    public int iE;
    public int iS;
    public int i3;
    public int i4;
    public int iL;
    public int iP;
    public int iF;
    
    public void open_close() {
        if (this.iS == 1) {
            this.iS = 0;
            return;
        }
        if (this.iS == 0) {
            this.iS = 1;
        }
    }
    
    public boolean isKeys(final String s) {
        return this.sKeys != null && this.sKeys.indexOf(s) > -1;
    }
    
    public boolean get(final rd_l rd_l, final String s, final String s2, final char c) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        this.iS = -1;
        if (s == null) {
            return false;
        }
        rd_l.sTarg = new String(s2);
        while (true) {
            final int index = s.indexOf(c, n);
            String s3;
            if (index > 0) {
                s3 = new String(s.substring(n, index).trim());
            }
            else {
                s3 = new String(s.substring(n).trim());
            }
            boolean b = false;
            try {
                if (Integer.parseInt(s3) < 19900101 && (n2 > 0 || n3 < 1)) {
                    b = true;
                }
            }
            catch (NumberFormatException ex) {}
            if (index > -1 || s3.length() > 0) {
                if (n2 > 0 && s3.charAt(0) == '?') {
                    rd_l.sH = new String(s3);
                }
                else if (n2 > 0 && s3.charAt(0) == '#') {
                    rd_l.sH2 = new String(s3.substring(1).trim());
                }
                else if (n2 > 0 && s3.startsWith("key=")) {
                    rd_l.sKeys = new String(s3.substring(5).trim().toLowerCase());
                }
                else if (b) {
                    if (n3 == 0) {
                        rd_l.iN = Integer.parseInt(s3);
                    }
                    if (n3 == 1) {
                        rd_l.iE = Integer.parseInt(s3);
                    }
                    if (n3 == 2) {
                        rd_l.i3 = Integer.parseInt(s3);
                    }
                    if (n3 == 3) {
                        rd_l.iF = Integer.parseInt(s3);
                    }
                    ++n3;
                }
                else {
                    if (n2 == 0) {
                        rd_l.iL = 0;
                        int n4 = 0;
                        for (int i = s3.indexOf("\\n", n4); i > -1; i = s3.indexOf("\\n", n4)) {
                            rd_l.sDesc[rd_l.iL++] = new String(s3.substring(n4, i));
                            n4 = i + 2;
                        }
                        rd_l.sDesc[rd_l.iL++] = new String(s3.substring(n4, s3.length()));
                    }
                    if (n2 == 1) {
                        rd_l.sURL = new String(s3);
                        rd_l.sH2 = new String(s3);
                    }
                    if (n2 == 2 && !s3.equals("_")) {
                        rd_l.sTarg = new String(s3);
                    }
                    if (n2 == 3) {
                        rd_l.sVal = new String(s3);
                    }
                    ++n2;
                }
            }
            if (index == -1) {
                break;
            }
            n = index + 1;
        }
        return n2 + n3 > 0;
    }
    
    public rd_l() {
        this.sDesc = new String[5];
        this.sTarg = "";
        this.sH2 = "";
        this.sVal = "";
        this.iE = 10;
        this.iL = 1;
        this.iF = 1;
    }
}
