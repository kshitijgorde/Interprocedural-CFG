// 
// Decompiled by Procyon v0.5.30
// 

final class d
{
    public static final int \u00f4 = 0;
    public static final int \u00f5 = 1;
    public static final int \u00f6 = 2;
    public static final int \u00f8 = 3;
    public static final int \u00f9 = 4;
    public static final int \u00fa = 5;
    private static final int \u00fb = 0;
    private static final int \u00fc = 1;
    private static final int \u00fd = 2;
    private static final int \u00fe = 3;
    private int \u00ff;
    private int[] \u0100;
    private int[] \u0101;
    private int \u0102;
    private int[] \u0103;
    private int \u0104;
    private int \u0105;
    private int \u0106;
    private int \u0107;
    private int \u0108;
    private int \u0109;
    private int \u010a;
    private boolean \u010b;
    private int \u010c;
    private int \u010d;
    private int \u010e;
    private int \u010f;
    private int \u0110;
    private float \u0111;
    
    public d(final int[] \u0101, final int[] \u01012, final int[] \u0103, final int \u01032, final int \u0105, final int \u01052, final int \u0107, final int \u01072, final String s, final int \u0109) {
        this.\u010e = 16;
        this.\u0100 = \u0101;
        this.\u0101 = \u01012;
        this.\u0102 = \u01032;
        this.\u0104 = \u0105;
        this.\u0105 = \u01052;
        this.\u0106 = \u0107;
        this.\u0107 = \u01072;
        this.\u0109 = \u0109;
        if (s.toUpperCase().equals("ROLLDOWN")) {
            this.\u0108 = 4;
        }
        else if (s.toUpperCase().equals("ROLLUP")) {
            this.\u0108 = 3;
        }
        else if (s.toUpperCase().equals("LEFTRIGHT")) {
            this.\u0108 = 1;
        }
        else if (s.toUpperCase().equals("FADE")) {
            this.\u0108 = 2;
        }
        else if (s.toUpperCase().equals("CIRCLEOUT")) {
            this.\u0108 = 5;
        }
        else {
            this.\u0108 = 0;
        }
        this.\u010a = 0;
        this.\u010b = false;
        this.\u00ff = 0;
        this.\u0103 = \u0103;
        this.u();
    }
    
    public final void p(final int[] \u0101, final int[] \u01012, final int[] \u0103, final int \u01032, final int \u0105, final int \u01052, final int \u0107, final int \u01072, final String s, final int \u0109) {
        this.\u0100 = \u0101;
        this.\u0101 = \u01012;
        this.\u0102 = \u01032;
        this.\u0104 = \u0105;
        this.\u0105 = \u01052;
        this.\u0106 = \u0107;
        this.\u0107 = \u01072;
        this.\u0109 = \u0109;
        if (s.toUpperCase().equals("ROLLDOWN")) {
            this.\u0108 = 4;
        }
        else if (s.toUpperCase().equals("ROLLUP")) {
            this.\u0108 = 3;
        }
        else if (s.toUpperCase().equals("LEFTRIGHT")) {
            this.\u0108 = 1;
        }
        else if (s.toUpperCase().equals("FADE")) {
            this.\u0108 = 2;
        }
        else if (s.toUpperCase().equals("CIRCLEOUT")) {
            this.\u0108 = 5;
        }
        else {
            this.\u0108 = 0;
        }
        this.\u010a = 0;
        this.\u010b = false;
        this.\u00ff = 0;
        this.\u0103 = \u0103;
        this.u();
    }
    
    private void x() {
        for (int i = 0; i < this.\u0107; ++i) {
            for (int j = 0; j < this.\u0106; ++j) {
                this.\u0103[(this.\u0105 + i) * this.\u0102 + this.\u0104 + j] = this.\u0100[(this.\u0105 + i) * this.\u0102 + this.\u0104 + j];
            }
        }
    }
    
    public final void t() {
        if (this.\u00ff == 0) {
            this.\u010b = false;
            this.u();
            this.\u00ff = 1;
            return;
        }
        if (this.\u00ff == 1) {
            if (this.\u010a <= this.\u0109) {
                ++this.\u010a;
                return;
            }
            this.\u010a = 0;
            this.\u00ff = 2;
        }
        else {
            if (this.\u00ff == 2) {
                this.q();
                return;
            }
            this.\u010b = true;
        }
    }
    
    private void u() {
        if (this.\u0108 == 4) {
            this.x();
            return;
        }
        if (this.\u0108 == 3) {
            this.x();
            return;
        }
        if (this.\u0108 == 1) {
            this.x();
            return;
        }
        if (this.\u0108 == 2) {
            this.x();
            return;
        }
        if (this.\u0108 == 5) {
            this.\u010f = Math.round(this.\u0107 / 2);
            this.\u0110 = Math.round(this.\u0106 / 2);
            this.\u0111 = (float)Math.sqrt(this.\u010f * this.\u010f + this.\u0110 * this.\u0110);
            this.x();
            return;
        }
        this.x();
    }
    
    private void q() {
        if (this.\u0108 == 3) {
            for (int i = 0; i < this.\u010c; ++i) {
                for (int j = 0; j < this.\u0106; ++j) {
                    this.\u0103[(this.\u0105 + this.\u0107 - this.\u010c + i) * this.\u0102 + this.\u0104 + j] = this.\u0101[(this.\u0105 + i) * this.\u0102 + this.\u0104 + j];
                }
            }
            for (int k = 0; k < this.\u0107 - this.\u010c; ++k) {
                for (int l = 0; l < this.\u0106; ++l) {
                    this.\u0103[(this.\u0105 + k) * this.\u0102 + this.\u0104 + l] = this.\u0100[(this.\u0105 + k + this.\u010c) * this.\u0102 + this.\u0104 + l];
                }
            }
            if (this.\u010c < this.\u0107) {
                ++this.\u010c;
                return;
            }
            this.\u010c = 0;
            this.\u00ff = 3;
        }
        else if (this.\u0108 == 4) {
            for (int n = 0; n < this.\u010c; ++n) {
                for (int n2 = 0; n2 < this.\u0106; ++n2) {
                    this.\u0103[(this.\u0105 + n) * this.\u0102 + this.\u0104 + n2] = this.\u0101[(this.\u0105 + this.\u0107 - this.\u010c + n) * this.\u0102 + this.\u0104 + n2];
                }
            }
            for (int n3 = 0; n3 < this.\u0107 - this.\u010c; ++n3) {
                for (int n4 = 0; n4 < this.\u0106; ++n4) {
                    this.\u0103[(this.\u0105 + n3 + this.\u010c) * this.\u0102 + this.\u0104 + n4] = this.\u0100[(this.\u0105 + n3) * this.\u0102 + this.\u0104 + n4];
                }
            }
            if (this.\u010c < this.\u0107) {
                ++this.\u010c;
                return;
            }
            this.\u010c = 0;
            this.\u00ff = 3;
        }
        else if (this.\u0108 == 1) {
            for (int n5 = 0; n5 < this.\u0107; ++n5) {
                this.\u0103[(this.\u0105 + n5) * this.\u0102 + this.\u0104 + this.\u010d] = this.\u0101[(this.\u0105 + n5) * this.\u0102 + this.\u0104 + this.\u010d];
            }
            if (this.\u010d < this.\u0106 - 1) {
                ++this.\u010d;
                return;
            }
            this.\u010d = 0;
            this.\u00ff = 3;
        }
        else if (this.\u0108 == 2) {
            for (int n6 = 0; n6 < this.\u0107; ++n6) {
                for (int n7 = 0; n7 < this.\u0106; ++n7) {
                    final int n8 = this.\u0100[(this.\u0105 + n6) * this.\u0102 + this.\u0104 + n7] >> 24 & 0xFF;
                    final int n9 = this.\u0101[(this.\u0105 + n6) * this.\u0102 + this.\u0104 + n7] >> 24 & 0xFF;
                    final int n10 = this.\u0100[(this.\u0105 + n6) * this.\u0102 + this.\u0104 + n7] >> 16 & 0xFF;
                    final int n11 = this.\u0101[(this.\u0105 + n6) * this.\u0102 + this.\u0104 + n7] >> 16 & 0xFF;
                    final int n12 = this.\u0100[(this.\u0105 + n6) * this.\u0102 + this.\u0104 + n7] >> 8 & 0xFF;
                    final int n13 = this.\u0101[(this.\u0105 + n6) * this.\u0102 + this.\u0104 + n7] >> 8 & 0xFF;
                    final int n14 = this.\u0100[(this.\u0105 + n6) * this.\u0102 + this.\u0104 + n7] & 0xFF;
                    final int n15 = this.\u0101[(this.\u0105 + n6) * this.\u0102 + this.\u0104 + n7] & 0xFF;
                    final float n16 = this.\u010e;
                    final float n17 = this.\u010c;
                    this.\u0103[(this.\u0105 + n6) * this.\u0102 + this.\u0104 + n7] = ((Math.round(n8 + (n9 - n8) / n16 * n17) << 24 & 0xFF000000) | (Math.round(n10 + (n11 - n10) / n16 * n17) << 16 & 0xFF0000) | (Math.round(n12 + (n13 - n12) / n16 * n17) << 8 & 0xFF00) | (Math.round(n14 + (n15 - n14) / n16 * n17) & 0xFF));
                }
            }
            if (this.\u010c < this.\u010e) {
                ++this.\u010c;
                return;
            }
            this.\u010c = 0;
            this.\u00ff = 3;
        }
        else if (this.\u0108 == 5) {
            for (int n18 = 0; n18 < this.\u0107; ++n18) {
                for (int n19 = 0; n19 < this.\u0106; ++n19) {
                    if ((float)Math.sqrt((n18 - this.\u010f) * (n18 - this.\u010f) + (n19 - this.\u0110) * (n19 - this.\u0110)) >= this.\u0111) {
                        this.\u0103[(this.\u0105 + n18) * this.\u0102 + this.\u0104 + n19] = this.\u0101[(this.\u0105 + n18) * this.\u0102 + this.\u0104 + n19];
                    }
                    else {
                        this.\u0103[(this.\u0105 + n18) * this.\u0102 + this.\u0104 + n19] = this.\u0100[(this.\u0105 + n18) * this.\u0102 + this.\u0104 + n19];
                    }
                }
            }
            if (this.\u0111 > 0.0f) {
                --this.\u0111;
                return;
            }
            this.\u0111 = (float)Math.sqrt(this.\u010f * this.\u010f + this.\u0110 * this.\u0110);
            this.\u00ff = 3;
        }
        else {
            for (int n20 = 0; n20 < this.\u0106; ++n20) {
                this.\u0103[(this.\u0105 + this.\u010c) * this.\u0102 + this.\u0104 + n20] = this.\u0101[(this.\u0105 + this.\u010c) * this.\u0102 + this.\u0104 + n20];
            }
            if (this.\u010c < this.\u0107 - 1) {
                ++this.\u010c;
                return;
            }
            this.\u010c = 0;
            this.\u00ff = 3;
        }
    }
    
    public final void r(final int[] \u0101) {
        this.\u0100 = \u0101;
    }
    
    public final void m(final int[] \u0101) {
        this.\u0101 = \u0101;
    }
    
    public final void o() {
        this.\u00ff = 0;
    }
    
    public final boolean l() {
        return this.\u010b;
    }
    
    public final int s() {
        return this.\u0107;
    }
    
    public final int y() {
        return this.\u0106;
    }
    
    public final int w() {
        return this.\u0104;
    }
    
    public final int v() {
        return this.\u0105;
    }
    
    public final int[] n() {
        return this.\u0103;
    }
}
