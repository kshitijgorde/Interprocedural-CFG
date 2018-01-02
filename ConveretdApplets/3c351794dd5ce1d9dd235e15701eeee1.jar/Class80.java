import jagdx.kg;
import jaclib.memory.Buffer;
import jaclib.memory.Source;
import jagdx.IDirect3DVertexBuffer;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class80 implements Interface2_Impl1
{
    IDirect3DVertexBuffer anIDirect3DVertexBuffer5337;
    private byte aByte5338;
    private int anInt5339;
    private boolean aBoolean5340;
    private int anInt5341;
    private ha_Sub3_Sub1 aHa_Sub3_Sub1_5342;
    private boolean aBoolean5343;
    
    final int method810(final byte b) {
        byte aByte5338;
        try {
            if (b != -22) {
                this.method810((byte)(-72));
            }
            aByte5338 = this.aByte5338;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return aByte5338;
    }
    
    @Override
    public final boolean method73(final byte b, final int n, final int n2, final Source source) {
        boolean b2;
        try {
            if (this.method74(-20279, n2, n)) {
                return this.anIDirect3DVertexBuffer5337.a(source, 0, 0, this.anInt5339, this.aBoolean5340 ? 8192 : 0);
            }
            if (b >= -79) {
                return false;
            }
            b2 = false;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return b2;
    }
    
    @Override
    public final boolean method74(final int n, final int n2, final int anInt5339) {
        boolean b;
        try {
            this.anInt5339 = anInt5339;
            if (n != -20279) {
                this.method73((byte)73, 31, -108, null);
            }
            this.aByte5338 = (byte)n2;
            if (this.anInt5341 < this.anInt5339) {
                int n3 = 8;
                int n4;
                if (this.aBoolean5340) {
                    n4 = 0;
                    n3 |= 0x200;
                }
                else {
                    n4 = 1;
                }
                if (null != this.anIDirect3DVertexBuffer5337) {
                    this.anIDirect3DVertexBuffer5337.b(119);
                }
                this.anIDirect3DVertexBuffer5337 = this.aHa_Sub3_Sub1_5342.anIDirect3DDevice6098.a(this.anInt5339, n3, 0, n4, this.anIDirect3DVertexBuffer5337);
                this.anInt5341 = this.anInt5339;
            }
            if (null != this.anIDirect3DVertexBuffer5337) {
                return true;
            }
            b = false;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return b;
    }
    
    @Override
    public final Buffer method75(final boolean b, final byte b2) {
        Buffer buffer;
        try {
            if (null == this.anIDirect3DVertexBuffer5337) {
                return null;
            }
            if (b2 != 27) {
                this.method74(118, -74, 74);
            }
            final boolean b3 = b & this.aBoolean5340;
            if (!this.aBoolean5343 && kg.a(-21593, this.anIDirect3DVertexBuffer5337.Lock(0, this.anInt5341, b3 ? 8192 : 0, this.aHa_Sub3_Sub1_5342.aGeometryBuffer6086))) {
                this.aBoolean5343 = true;
                return this.aHa_Sub3_Sub1_5342.aGeometryBuffer6086;
            }
            buffer = null;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return buffer;
    }
    
    @Override
    public final int method2(final int n) {
        int anInt5339;
        try {
            if (n != 200) {
                this.method2(44);
            }
            anInt5339 = this.anInt5339;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return anInt5339;
    }
    
    @Override
    public final boolean method71(final int n) {
        boolean b;
        try {
            if (this.aBoolean5343 && kg.a(-21593, this.anIDirect3DVertexBuffer5337.Unlock())) {
                this.aBoolean5343 = false;
                return true;
            }
            if (n != 13623) {
                this.aHa_Sub3_Sub1_5342 = null;
            }
            b = false;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return b;
    }
    
    @Override
    public final void method72(final boolean b) {
        try {
            if (b) {
                this.method74(-75, -93, 67);
            }
            if (null != this.anIDirect3DVertexBuffer5337) {
                this.anIDirect3DVertexBuffer5337.b(112);
                this.anIDirect3DVertexBuffer5337 = null;
            }
            this.anInt5339 = 0;
            this.anInt5341 = 0;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    Class80(final ha_Sub3_Sub1 aHa_Sub3_Sub1_5342, final boolean aBoolean5340) {
        this.aBoolean5343 = false;
        try {
            this.aBoolean5340 = aBoolean5340;
            this.aHa_Sub3_Sub1_5342 = aHa_Sub3_Sub1_5342;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}
