// 
// Decompiled by Procyon v0.5.30
// 

public final class aH
{
    private aD[] a;
    private String a;
    
    public aH() {
        this.a = new aD[4];
        this.a = "APZLGITYEOXUKSVN";
    }
    
    private final int b(String upperCase, final int n) {
        if ((upperCase = upperCase.toUpperCase()).length() != 8) {
            return -1;
        }
        final int[] array = new int[8];
        for (int i = 0; i < 8; ++i) {
            final int index;
            if ((index = this.a.indexOf(upperCase.substring(i, 1 + i))) < 0) {
                return -1;
            }
            array[i] = index;
        }
        final int b = (array[1] & 0x7) << 4 | (array[0] & 0x8) << 4 | (array[0] & 0x7) | (array[7] & 0x8);
        final int a = 32768 + ((array[3] & 0x7) << 12) | (array[5] & 0x7) << 8 | (array[4] & 0x8) << 8 | (array[2] & 0x7) << 4 | (array[1] & 0x8) << 4 | (array[4] & 0x7) | (array[3] & 0x8);
        final int c = (array[7] & 0x7) << 4 | (array[6] & 0x8) << 4 | (array[6] & 0x7) | (array[5] & 0x8);
        final aD ad;
        (ad = new aD()).a = upperCase;
        ad.a = a;
        ad.b = b;
        ad.c = c;
        this.a(ad, n);
        return 0;
    }
    
    private final int c(String upperCase, final int n) {
        if ((upperCase = upperCase.toUpperCase()).length() != 6) {
            return -1;
        }
        final int[] array = new int[6];
        for (int i = 0; i < 6; ++i) {
            final int index;
            if ((index = this.a.indexOf(upperCase.substring(i, 1 + i))) < 0) {
                return -1;
            }
            array[i] = index;
        }
        final int b = (array[1] & 0x7) << 4 | (array[0] & 0x8) << 4 | (array[0] & 0x7) | (array[5] & 0x8);
        final int a = 32768 + ((array[3] & 0x7) << 12) | (array[5] & 0x7) << 8 | (array[4] & 0x8) << 8 | (array[2] & 0x7) << 4 | (array[1] & 0x8) << 4 | (array[4] & 0x7) | (array[3] & 0x8);
        final aD ad;
        (ad = new aD()).a = upperCase;
        ad.a = a;
        ad.b = b;
        ad.c = -1;
        this.a(ad, n);
        return 0;
    }
    
    public final boolean a(final int n) {
        this.a[n] = null;
        boolean b = false;
        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null) {
                b = true;
            }
        }
        return b;
    }
    
    private final void a(final aD ad, final int n) {
        this.a[n] = ad;
    }
    
    public final void a() {
        for (int i = 0; i < this.a.length; ++i) {
            this.a[i] = null;
        }
        System.gc();
    }
    
    public final int a(final String s, final int n) {
        if (s.length() == 6) {
            if (this.c(s, n) < 0) {
                return -1;
            }
        }
        else if (s.length() == 8) {
            if (this.b(s, n) < 0) {
                return -1;
            }
        }
        else if (s.length() > 0) {
            return -1;
        }
        return 1;
    }
    
    public final int a(final int n) {
        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null && this.a[i].a == n) {
                return i;
            }
        }
        return -1;
    }
    
    public final int b(final int n) {
        if (n < this.a.length && this.a[n] != null) {
            return this.a[n].b;
        }
        return 0;
    }
    
    public final int c(final int n) {
        if (n < this.a.length && this.a[n] != null) {
            return this.a[n].c;
        }
        return 0;
    }
    
    public final String a(final int n) {
        if (n < this.a.length && this.a[n] != null) {
            return this.a[n].a;
        }
        return "";
    }
    
    public final String b(final int n) {
        if (n < this.a.length && this.a[n] != null) {
            return aK.a(this.a[n].a, 4);
        }
        return "";
    }
}
