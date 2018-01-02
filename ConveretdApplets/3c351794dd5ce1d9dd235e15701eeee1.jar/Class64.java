// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class64
{
    int anInt494;
    Class98_Sub27 aClass98_Sub27_495;
    
    abstract void method550(final int p0, final int p1);
    
    abstract void method551(final byte p0);
    
    abstract int method552(final int p0);
    
    static final int method553(final int n) {
        try {
            if (Class278.aFloat2068 == 3.0) {
                return 37;
            }
            if (Class278.aFloat2068 == 4.0) {
                return 50;
            }
            if (n != 1024) {
                method555(58, -19, -15);
            }
            if (Class278.aFloat2068 == 6.0) {
                return 75;
            }
            if (Class278.aFloat2068 == 8.0) {
                return 100;
            }
            return 200;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eea.K(" + n + ')');
        }
    }
    
    final void method554(final int n, final int n2) {
        try {
            if (this.method556(n, 29053) != 3) {
                this.method550(n2 ^ 0xFFFFD9F7, n);
            }
            if (n2 != 9848) {
                this.anInt494 = 1;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eea.L(" + n + ',' + n2 + ')');
        }
    }
    
    static final boolean method555(final int n, final int n2, final int n3) {
        try {
            return n3 < -33 && ~(0x400 & n2) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eea.J(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    abstract int method556(final int p0, final int p1);
    
    Class64(final Class98_Sub27 aClass98_Sub27_495) {
        try {
            this.aClass98_Sub27_495 = aClass98_Sub27_495;
            this.anInt494 = this.method552(0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eea.<init>(" + ((aClass98_Sub27_495 != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class64(final int anInt494, final Class98_Sub27 aClass98_Sub27_495) {
        try {
            this.aClass98_Sub27_495 = aClass98_Sub27_495;
            this.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eea.<init>(" + anInt494 + ',' + ((aClass98_Sub27_495 != null) ? "{...}" : "null") + ')');
        }
    }
}
