// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class76
{
    ha_Sub3 aHa_Sub3_585;
    static Class28[][] aClass28ArrayArray586;
    static IncomingOpcode aClass58_587;
    static int anInt588;
    static IncomingOpcode aClass58_589;
    static byte[][] aByteArrayArray590;
    
    void method737(final int n) {
        try {
            if (n != 2899) {
                this.method746(-41, 122, 8);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ew.J(" + n + ')');
        }
    }
    
    void method738(final int n) {
        try {
            if (n > -45) {
                this.method745((byte)50);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ew.K(" + n + ')');
        }
    }
    
    abstract void method739(final int p0);
    
    void method740(final int n) {
        try {
            if (n >= -49) {
                this.method748(93, false);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ew.N(" + n + ')');
        }
    }
    
    Class76(final ha_Sub3 aHa_Sub3_585) {
        try {
            this.aHa_Sub3_585 = aHa_Sub3_585;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ew.<init>(" + ((aHa_Sub3_585 != null) ? "{...}" : "null") + ')');
        }
    }
    
    void method741(final byte b) {
    }
    
    abstract void method742(final int p0, final int p1, final Interface4 p2);
    
    abstract void method743(final int p0, final boolean p1);
    
    public static void method744(final int n) {
        try {
            Class76.aClass58_589 = null;
            Class76.aClass58_587 = null;
            if (n <= 72) {
                method744(-44);
            }
            Class76.aClass28ArrayArray586 = null;
            Class76.aByteArrayArray590 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ew.Q(" + n + ')');
        }
    }
    
    abstract boolean method745(final byte p0);
    
    abstract void method746(final int p0, final int p1, final int p2);
    
    void method747(final int n) {
        try {
            if (n != -25684) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ew.P(" + n + ')');
        }
    }
    
    abstract void method748(final int p0, final boolean p1);
    
    void method749(final int n) {
        try {
            if (n != 8) {
                this.method739(112);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ew.M(" + n + ')');
        }
    }
    
    static {
        Class76.aClass58_587 = new IncomingOpcode(6, -2);
        Class76.aClass58_589 = new IncomingOpcode(69, 8);
    }
}
