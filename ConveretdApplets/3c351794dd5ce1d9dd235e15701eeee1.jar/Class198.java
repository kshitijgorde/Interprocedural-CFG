// 
// Decompiled by Procyon v0.5.30
// 

final class Class198
{
    static OutgoingOpcode aClass171_1521;
    private Class207 aClass207_1522;
    private Class79 aClass79_1523;
    static int anInt1524;
    static long aLong1525;
    
    static final int method2678(final byte b, final int n, final int n2) {
        try {
            if (b != 6) {
                Class198.aClass171_1521 = null;
            }
            return ((n >>> 1656906079) + n) % n2 + (-1 + n2 & n >> -1893021153);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nb.F(" + b + ',' + n + ',' + n2 + ')');
        }
    }
    
    final void method2679(final int n, final byte b) {
        try {
            if (b > -76) {
                this.aClass207_1522 = null;
            }
            synchronized (this.aClass79_1523) {
                this.aClass79_1523.method794(92);
                this.aClass79_1523 = new Class79(n);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nb.E(" + n + ',' + b + ')');
        }
    }
    
    final Class366 method2680(final int n, final byte b) {
        try {
            if (b < 19) {
                Class198.aClass171_1521 = null;
            }
            final Class366 class366;
            synchronized (this.aClass79_1523) {
                class366 = (Class366)this.aClass79_1523.method802(-125, n);
            }
            if (class366 != null) {
                return class366;
            }
            final byte[] method2745;
            synchronized (this.aClass207_1522) {
                method2745 = this.aClass207_1522.method2745(Class32.method318(n, (byte)(-79)), Class234.method2886(n, -123), false);
            }
            final Class366 class367 = new Class366();
            if (method2745 != null) {
                class367.method3945(new Class98_Sub22(method2745), -6364);
            }
            synchronized (this.aClass79_1523) {
                this.aClass79_1523.method805(n, class367, (byte)(-80));
            }
            return class367;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nb.D(" + n + ',' + b + ')');
        }
    }
    
    final void method2681(final byte b, final int n) {
        try {
            if (b >= 125) {
                synchronized (this.aClass79_1523) {
                    this.aClass79_1523.method800((byte)62, n);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nb.B(" + b + ',' + n + ')');
        }
    }
    
    public static void method2682(final boolean b) {
        try {
            if (b) {
                method2682(false);
            }
            Class198.aClass171_1521 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nb.A(" + b + ')');
        }
    }
    
    final void method2683(final int n) {
        try {
            synchronized (this.aClass79_1523) {
                this.aClass79_1523.method806((byte)(-127));
                if (n != 0) {
                    Class198.aClass171_1521 = null;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nb.C(" + n + ')');
        }
    }
    
    Class198(final Class279 class279, final int n, final Class207 aClass207_1522) {
        this.aClass79_1523 = new Class79(64);
        try {
            this.aClass207_1522 = aClass207_1522;
            if (this.aClass207_1522 != null) {
                this.aClass207_1522.method2761(0, this.aClass207_1522.method2752((byte)(-11)) - 1);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nb.<init>(" + ((class279 != null) ? "{...}" : "null") + ',' + n + ',' + ((aClass207_1522 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method2684(final int n) {
        try {
            synchronized (this.aClass79_1523) {
                if (n == -4742) {
                    this.aClass79_1523.method794(64);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nb.G(" + n + ')');
        }
    }
    
    static {
        Class198.anInt1524 = 0;
        Class198.aClass171_1521 = new OutgoingOpcode(27, 3);
    }
}
