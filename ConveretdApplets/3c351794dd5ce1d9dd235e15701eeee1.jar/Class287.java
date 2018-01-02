import jaclib.memory.Buffer;
import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class287
{
    ha_Sub1 aHa_Sub1_2185;
    static int anInt2186;
    static IncomingOpcode aClass58_2187;
    static int[] anIntArray2188;
    private int anInt2189;
    static int anInt2190;
    int anInt2191;
    private boolean aBoolean2192;
    private int anInt2193;
    static IncomingOpcode aClass58_2194;
    static int[] anIntArray2195;
    static int anInt2196;
    
    abstract void method3384(final int p0);
    
    static final void method3385(final byte b) {
        try {
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub3_4041);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub3_4076);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub15_4034);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub15_4058);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub11_4038);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub25_4039);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub24_4047);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub13_4063);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub26_4035);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub7_4073);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub20_4056);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub10_4070);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub28_4064);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub14_4049);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub23_4044);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub23_4055);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub6_4033);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub17_4046);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub5_4065);
            if (b < 17) {
                method3385((byte)(-54));
            }
            Class151_Sub1.method2450((byte)62);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 2, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub16_4040);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 2, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub12_4048);
            Class98_Sub46_Sub13_Sub1.method1593((byte)111);
            Class374.method3980((byte)122);
            Class33.aBoolean316 = true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rq.M(" + b + ')');
        }
    }
    
    static final boolean method3386(final int n, final int n2, final byte b) {
        try {
            return ((0x70000 & n) != 0x0 | Class98.method944(n, n2, (byte)85)) || Class76_Sub7.method763(n, n2, false);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rq.N(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    static final void method3387(final String s, final String s2, final byte b, final boolean b2, final int n) {
        try {
            Class277.method3288(b2, n, s, 2, s2, true, -1);
            if (b <= 0) {
                Class287.aClass58_2194 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rq.J(" + ((s != null) ? "{...}" : "null") + ',' + ((s2 != null) ? "{...}" : "null") + ',' + b + ',' + b2 + ',' + n + ')');
        }
    }
    
    public static void method3388(final int n) {
        try {
            Class287.anIntArray2195 = null;
            Class287.anIntArray2188 = null;
            Class287.aClass58_2194 = null;
            Class287.aClass58_2187 = null;
            if (n != 32414) {
                method3385((byte)(-61));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rq.K(" + n + ')');
        }
    }
    
    final void method3389(final int n, final int anInt2193, final byte[] array) {
        try {
            this.method3384(n + n);
            if (anInt2193 > this.anInt2193) {
                OpenGL.glBufferDataARBub(this.anInt2189, anInt2193, array, 0, this.aBoolean2192 ? 35040 : 35044);
                final ha_Sub1 aHa_Sub1_2185 = this.aHa_Sub1_2185;
                aHa_Sub1_2185.anInt4336 += -this.anInt2193 + anInt2193;
                this.anInt2193 = anInt2193;
            }
            else {
                OpenGL.glBufferSubDataARBub(this.anInt2189, 0, anInt2193, array, 0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rq.L(" + n + ',' + anInt2193 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    protected final void finalize() throws Throwable {
        try {
            this.aHa_Sub1_2185.method1879(this.anInt2193, (byte)121, this.anInt2191);
            super.finalize();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rq.finalize()");
        }
    }
    
    Class287(final ha_Sub1 aHa_Sub1_2185, final int anInt2189, final byte[] array, final int anInt2190, final boolean aBoolean2192) {
        try {
            this.anInt2189 = anInt2189;
            this.anInt2193 = anInt2190;
            this.aHa_Sub1_2185 = aHa_Sub1_2185;
            this.aBoolean2192 = aBoolean2192;
            OpenGL.glGenBuffersARB(1, Class23.anIntArray222, 0);
            this.anInt2191 = Class23.anIntArray222[0];
            this.method3384(0);
            OpenGL.glBufferDataARBub(anInt2189, this.anInt2193, array, 0, this.aBoolean2192 ? 35040 : 35044);
            final ha_Sub1 aHa_Sub1_2186 = this.aHa_Sub1_2185;
            aHa_Sub1_2186.anInt4336 += this.anInt2193;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rq.<init>(" + ((aHa_Sub1_2185 != null) ? "{...}" : "null") + ',' + anInt2189 + ',' + ((array != null) ? "{...}" : "null") + ',' + anInt2190 + ',' + aBoolean2192 + ')');
        }
    }
    
    Class287(final ha_Sub1 aHa_Sub1_2185, final int anInt2189, final Buffer buffer, final int anInt2190, final boolean aBoolean2192) {
        try {
            this.aHa_Sub1_2185 = aHa_Sub1_2185;
            this.anInt2189 = anInt2189;
            this.aBoolean2192 = aBoolean2192;
            this.anInt2193 = anInt2190;
            OpenGL.glGenBuffersARB(1, Class23.anIntArray222, 0);
            this.anInt2191 = Class23.anIntArray222[0];
            this.method3384(0);
            OpenGL.glBufferDataARBa(anInt2189, this.anInt2193, buffer.getAddress(), this.aBoolean2192 ? 35040 : 35044);
            final ha_Sub1 aHa_Sub1_2186 = this.aHa_Sub1_2185;
            aHa_Sub1_2186.anInt4336 += this.anInt2193;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rq.<init>(" + ((aHa_Sub1_2185 != null) ? "{...}" : "null") + ',' + anInt2189 + ',' + ((buffer != null) ? "{...}" : "null") + ',' + anInt2190 + ',' + aBoolean2192 + ')');
        }
    }
    
    static {
        Class287.aClass58_2187 = new IncomingOpcode(111, 1);
        Class287.anIntArray2195 = new int[2];
        Class287.aClass58_2194 = new IncomingOpcode(14, 2);
        Class287.anInt2196 = 0;
    }
}
