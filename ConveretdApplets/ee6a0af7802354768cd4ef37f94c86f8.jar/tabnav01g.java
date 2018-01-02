import java.util.StringTokenizer;
import java.awt.FontMetrics;

// 
// Decompiled by Procyon v0.5.30
// 

class tabnav01g
{
    private tabnav01 z0;
    private int z9;
    private int z2;
    private int z5;
    private int z7;
    private int z4;
    private int z6;
    private int z1;
    private int z8;
    private int[] z3;
    private String z10;
    private String z13;
    private String z12;
    private String z11;
    
    tabnav01g(final tabnav01 z0, final String[] array, final int z2, final int z3, final int z4, final FontMetrics fontMetrics, final int z5) {
        this.z3 = new int[] { -1, -1 };
        this.z0 = z0;
        this.z2 = z2;
        this.z9 = z3;
        this.z7 = z4;
        this.z5 = z5;
        final StringTokenizer stringTokenizer = new StringTokenizer(array[0], ",");
        for (int countTokens = stringTokenizer.countTokens(), i = 0; i < countTokens; ++i) {
            final String trim = stringTokenizer.nextToken().trim();
            if (i == 0) {
                this.z8 = (trim.startsWith("~") ? 1 : 0);
                this.z10 = new String(trim.substring(this.z8));
            }
            else if (i < 3) {
                try {
                    this.z3[i - 1] = Integer.parseInt(trim) - 1;
                }
                catch (NumberFormatException ex) {
                    this.z3[i - 1] = -1;
                }
            }
        }
        this.z1 = fontMetrics.stringWidth(this.z10);
        if (this.z3[0] > -1 && this.z3[1] == -1) {
            this.z3[1] = this.z3[0];
        }
        final StringTokenizer stringTokenizer2 = new StringTokenizer(array[1], ",");
        for (int countTokens2 = stringTokenizer2.countTokens(), j = 0; j < countTokens2; ++j) {
            final String trim2 = stringTokenizer2.nextToken().trim();
            if (j == 0) {
                this.z13 = new String(trim2);
            }
            else {
                this.z12 = new String(trim2);
            }
        }
        this.z11 = new String(array[2].trim());
    }
    
    int q72(final tabnav01 tabnav01, final int n, final boolean b, final int n2, final int n3, int z8) {
        if (this.z2 == n) {
            this.z4 = n3 + this.z7 * n2;
            if (this.z9 == 1 && this.z8 == 1 && this.z7 == 0) {
                this.z8 = z8;
                if (b) {
                    z8 = 0;
                }
            }
        }
        return z8;
    }
    
    private String z14(final String s) {
        if (s == null || s.length() < 2) {
            return "";
        }
        return s;
    }
    
    String q63() {
        if (this.z11.equals("=")) {
            final String z10 = this.z10;
            if (z10 == null || z10.length() < 2) {
                return "";
            }
            return z10;
        }
        else {
            final String z11 = this.z11;
            if (z11 == null || z11.length() < 2) {
                return "";
            }
            return z11;
        }
    }
    
    void q73(final int z6) {
        this.z6 = z6;
    }
    
    int q62() {
        return this.z2;
    }
    
    int q70() {
        return this.z9;
    }
    
    int q67() {
        return this.z1;
    }
    
    String q61() {
        final String z10 = this.z10;
        if (z10 == null || z10.length() < 2) {
            return "";
        }
        return z10;
    }
    
    int q69() {
        return this.z8;
    }
    
    void q74(final int z8) {
        this.z8 = z8;
    }
    
    String q71() {
        final String z13 = this.z13;
        if (z13 == null || z13.length() < 2) {
            return "";
        }
        return z13;
    }
    
    String getTarget() {
        final String z12 = this.z12;
        if (z12 == null || z12.length() < 2) {
            return "";
        }
        return z12;
    }
    
    int q64() {
        return this.z3[this.z8];
    }
    
    int q65() {
        return this.z4;
    }
    
    int q68() {
        return this.z7;
    }
    
    int q66() {
        return this.z5;
    }
}
