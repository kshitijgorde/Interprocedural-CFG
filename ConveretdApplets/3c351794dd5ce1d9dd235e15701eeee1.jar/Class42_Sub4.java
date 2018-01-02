import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class42_Sub4 extends Class42
{
    private int anInt5367;
    int anInt5368;
    int anInt5369;
    private int anInt5370;
    static int anInt5371;
    int anInt5372;
    
    final void method395(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        try {
            super.aHa_Sub1_3227.method1863(1, this);
            OpenGL.glCopyTexSubImage3D(super.anInt3226, n4, n2, n8, n3, n, n5, n6, n7);
            OpenGL.glFlush();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qj.A(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ')');
        }
    }
    
    @Override
    public final void method3(final byte b) {
        try {
            OpenGL.glFramebufferTexture3DEXT(this.anInt5370, this.anInt5367, super.anInt3226, 0, 0, 0);
            this.anInt5370 = -1;
            this.anInt5367 = -1;
            if (b > -117) {
                Class42_Sub4.anInt5371 = -122;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qj.B(" + b + ')');
        }
    }
    
    Class42_Sub4(final ha_Sub1 ha_Sub1, final int n, final int anInt5369, final int anInt5370, final int anInt5371, final byte[] array, final int n2) {
        super(ha_Sub1, 32879, n, anInt5371 * anInt5369 * anInt5370, false);
        this.anInt5367 = -1;
        this.anInt5370 = -1;
        try {
            this.anInt5368 = anInt5371;
            this.anInt5369 = anInt5369;
            this.anInt5372 = anInt5370;
            super.aHa_Sub1_3227.method1863(1, this);
            OpenGL.glPixelStorei(3317, 1);
            OpenGL.glTexImage3Dub(super.anInt3226, 0, super.anInt3230, this.anInt5369, this.anInt5372, this.anInt5368, 0, n2, 5121, array, 0);
            OpenGL.glPixelStorei(3317, 4);
            this.method372(-28003, true);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qj.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n + ',' + anInt5369 + ',' + anInt5370 + ',' + anInt5371 + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    Class42_Sub4(final ha_Sub1 ha_Sub1, final int n, final int anInt5369, final int anInt5370, final int anInt5371) {
        super(ha_Sub1, 32879, n, anInt5371 * (anInt5369 * anInt5370), false);
        this.anInt5367 = -1;
        this.anInt5370 = -1;
        try {
            this.anInt5369 = anInt5369;
            this.anInt5368 = anInt5371;
            this.anInt5372 = anInt5370;
            super.aHa_Sub1_3227.method1863(1, this);
            OpenGL.glTexImage3Dub(super.anInt3226, 0, super.anInt3230, this.anInt5369, this.anInt5372, this.anInt5368, 0, Class98_Sub31_Sub2.method1339(super.anInt3230, 124), 5121, null, 0);
            this.method372(-28003, true);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qj.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n + ',' + anInt5369 + ',' + anInt5370 + ',' + anInt5371 + ')');
        }
    }
}
