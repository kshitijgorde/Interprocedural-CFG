// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub46_Sub8 extends Class98_Sub46
{
    boolean aBoolean5983;
    boolean aBoolean5984;
    String aString5985;
    int anInt5986;
    long aLong5987;
    int anInt5988;
    boolean aBoolean5989;
    int anInt5990;
    long aLong5991;
    String aString5992;
    int anInt5993;
    String aString5994;
    int anInt5995;
    static IncomingOpcode aClass58_5996;
    
    public static void method1552(final boolean b) {
        try {
            Class98_Sub46_Sub8.aClass58_5996 = null;
            if (!b) {
                Class98_Sub46_Sub8.aClass58_5996 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "em.A(" + b + ')');
        }
    }
    
    Class98_Sub46_Sub8(final String aString5994, final String aString5995, final int anInt5986, final int anInt5987, final int anInt5988, final long aLong5987, final int anInt5989, final int anInt5990, final boolean aBoolean5984, final boolean aBoolean5985, final long aLong5988, final boolean aBoolean5986) {
        try {
            this.aLong5991 = aLong5988;
            this.anInt5988 = anInt5988;
            this.anInt5986 = anInt5986;
            this.anInt5993 = anInt5990;
            this.aString5992 = aString5995;
            this.anInt5990 = anInt5987;
            this.aBoolean5983 = aBoolean5985;
            this.aBoolean5984 = aBoolean5984;
            this.aBoolean5989 = aBoolean5986;
            this.aLong5987 = aLong5987;
            this.anInt5995 = anInt5989;
            this.aString5994 = aString5994;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "em.<init>(" + ((aString5994 != null) ? "{...}" : "null") + ',' + ((aString5995 != null) ? "{...}" : "null") + ',' + anInt5986 + ',' + anInt5987 + ',' + anInt5988 + ',' + aLong5987 + ',' + anInt5989 + ',' + anInt5990 + ',' + aBoolean5984 + ',' + aBoolean5985 + ',' + aLong5988 + ',' + aBoolean5986 + ')');
        }
    }
    
    static {
        Class98_Sub46_Sub8.aClass58_5996 = new IncomingOpcode(30, -1);
    }
}
