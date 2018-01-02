// 
// Decompiled by Procyon v0.5.30
// 

final class Class22
{
    static int anInt216;
    static int anInt217;
    static int anInt218;
    static int anInt219;
    
    static final Class98_Sub46_Sub4 method280(final byte[] array, final int n) {
        try {
            final Class98_Sub46_Sub4 class98_Sub46_Sub4 = new Class98_Sub46_Sub4();
            final Class98_Sub22 class98_Sub22 = new Class98_Sub22(array);
            class98_Sub22.anInt3991 = -2 + class98_Sub22.aByteArray3992.length;
            final int anInt3991 = -12 + -class98_Sub22.readShort((byte)127) + (class98_Sub22.aByteArray3992.length - 2);
            class98_Sub22.anInt3991 = anInt3991;
            final int int1 = class98_Sub22.readInt(-2);
            class98_Sub46_Sub4.anInt5958 = class98_Sub22.readShort((byte)127);
            class98_Sub46_Sub4.anInt5964 = class98_Sub22.readShort((byte)127);
            class98_Sub46_Sub4.anInt5966 = class98_Sub22.readShort((byte)127);
            class98_Sub46_Sub4.anInt5965 = class98_Sub22.readShort((byte)127);
            final int i = class98_Sub22.readUnsignedByte((byte)39);
            if (~i < -1) {
                class98_Sub46_Sub4.aClass377Array5956 = new Class377[i];
                for (int n2 = 0; i > n2; ++n2) {
                    int short1 = class98_Sub22.readShort((byte)127);
                    final Class377 class377 = new Class377(Class48.method453(423660257, short1));
                    class98_Sub46_Sub4.aClass377Array5956[n2] = class377;
                    while (short1-- > 0) {
                        class377.method3996(new Class98_Sub34(class98_Sub22.readInt(-2)), class98_Sub22.readInt(-2), -1);
                    }
                }
            }
            class98_Sub22.anInt3991 = 0;
            class98_Sub46_Sub4.aString5968 = class98_Sub22.method1222(-1);
            class98_Sub46_Sub4.aStringArray5959 = new String[int1];
            class98_Sub46_Sub4.anIntArray5963 = new int[int1];
            class98_Sub46_Sub4.anIntArray5967 = new int[int1];
            int n3 = n;
            while (~anInt3991 < ~class98_Sub22.anInt3991) {
                final int short2 = class98_Sub22.readShort((byte)127);
                if (~short2 == 0xFFFFFFFC) {
                    class98_Sub46_Sub4.aStringArray5959[n3] = class98_Sub22.readString((byte)84).intern();
                }
                else if (~short2 > -101 && short2 != 21 && ~short2 != 0xFFFFFFD9 && short2 != 39) {
                    class98_Sub46_Sub4.anIntArray5967[n3] = class98_Sub22.readInt(n - 2);
                }
                else {
                    class98_Sub46_Sub4.anIntArray5967[n3] = class98_Sub22.readUnsignedByte((byte)111);
                }
                class98_Sub46_Sub4.anIntArray5963[n3++] = short2;
            }
            return class98_Sub46_Sub4;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bk.A(" + ((array != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static {
        Class22.anInt216 = 0;
        Class22.anInt218 = 1337;
    }
}
