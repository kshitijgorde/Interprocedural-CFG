// 
// Decompiled by Procyon v0.5.30
// 

final class Class4
{
    public static int method155(int n, final int n2, final int n3) {
        n &= 0x3;
        if (n == 0) {
            return n3;
        }
        if (n == 1) {
            return n2;
        }
        if (n == 2) {
            return 7 - n3;
        }
        return 7 - n2;
    }
    
    public static int method156(final int n, int n2, final int n3) {
        n2 &= 0x3;
        if (n2 == 0) {
            return n;
        }
        if (n2 == 1) {
            return 7 - n3;
        }
        if (n2 == 2) {
            return 7 - n;
        }
        return n3;
    }
    
    public static int method157(int n, final int n2, final int n3, final int n4, final int n5) {
        n &= 0x3;
        if (n == 0) {
            return n3;
        }
        if (n == 1) {
            return n4;
        }
        if (n == 2) {
            return 7 - n3 - (n5 - 1);
        }
        return 7 - n4 - (n2 - 1);
    }
    
    public static int method158(final int n, final int n2, int n3, final int n4, final int n5) {
        n3 &= 0x3;
        if (n3 == 0) {
            return n;
        }
        if (n3 == 1) {
            return 7 - n5 - (n4 - 1);
        }
        if (n3 == 2) {
            return 7 - n - (n2 - 1);
        }
        return n5;
    }
}
