// 
// Decompiled by Procyon v0.5.30
// 

class rp
{
    public String sU;
    public String sM;
    public String sT;
    public String sH;
    public int l1;
    public int l2;
    public int lg;
    
    public boolean get(final String s, final char c, final String st, final int l1, final int l2) {
        int n = 0;
        int n2 = 0;
        if (s == null) {
            return false;
        }
        this.sM = null;
        this.sU = "";
        this.sT = st;
        this.sH = null;
        this.l1 = l1;
        this.l2 = l2;
        this.lg = -1;
        while (true) {
            final int index = s.indexOf(c, n);
            String s2;
            if (index > 0) {
                s2 = new String(s.substring(n, index).trim());
            }
            else {
                s2 = new String(s.substring(n).trim());
            }
            if (index > -1 || s2.length() > 0) {
                if (n2 == 0) {
                    this.sM = new String(s2);
                }
                if (n2 == 1) {
                    this.sU = new String(s2);
                }
                if (s2.length() > 0) {
                    if (s2.charAt(0) == '?') {
                        this.sH = s2.substring(1);
                    }
                    else {
                        if (n2 == 2) {
                            this.sT = new String(s2);
                        }
                        if (n2 == 3) {
                            this.l1 = Integer.parseInt(s2);
                        }
                        if (n2 == 4) {
                            this.l2 = Integer.parseInt(s2);
                        }
                    }
                }
                ++n2;
            }
            if (index == -1) {
                break;
            }
            n = index + 1;
        }
        return n2 > 0;
    }
    
    rp() {
        this.lg = -1;
    }
}
