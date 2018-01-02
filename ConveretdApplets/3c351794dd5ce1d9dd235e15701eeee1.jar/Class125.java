import jagdx.kg;
import jaclib.memory.Buffer;
import jagdx.IDirect3DIndexBuffer;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class125 implements Interface2_Impl2
{
    private Class162 aClass162_5344;
    private int anInt5345;
    private ha_Sub3_Sub1 aHa_Sub3_Sub1_5346;
    private boolean aBoolean5347;
    private boolean aBoolean5348;
    private int anInt5349;
    IDirect3DIndexBuffer anIDirect3DIndexBuffer5350;
    
    @Override
    public final Buffer method78(final boolean b, final int n) {
        Buffer buffer;
        try {
            if (this.anIDirect3DIndexBuffer5350 == null) {
                return null;
            }
            final boolean b2 = b & this.aBoolean5347;
            if (!this.aBoolean5348 && kg.a(-21593, this.anIDirect3DIndexBuffer5350.Lock(0, this.anInt5345, b2 ? 8192 : 0, this.aHa_Sub3_Sub1_5346.aGeometryBuffer6086))) {
                this.aBoolean5348 = true;
                return this.aHa_Sub3_Sub1_5346.aGeometryBuffer6086;
            }
            if (n > -79) {
                this.anInt5349 = -35;
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
        int anInt5349;
        try {
            if (n != 200) {
                return -80;
            }
            anInt5349 = this.anInt5349;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return anInt5349;
    }
    
    @Override
    public final Class162 method77(final int n) {
        Class162 aClass162_5344;
        try {
            if (n != -15448) {
                this.method2(-126);
            }
            aClass162_5344 = this.aClass162_5344;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return aClass162_5344;
    }
    
    @Override
    public final void method72(final boolean b) {
        try {
            if (b) {
                this.method2(71);
            }
            if (null != this.anIDirect3DIndexBuffer5350) {
                this.anIDirect3DIndexBuffer5350.b(31);
                this.anIDirect3DIndexBuffer5350 = null;
            }
            this.anInt5345 = 0;
            this.anInt5349 = 0;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    public final boolean method79(final byte b) {
        boolean b2;
        try {
            if (this.aBoolean5348 && kg.a(-21593, this.anIDirect3DIndexBuffer5350.Unlock())) {
                this.aBoolean5348 = false;
                return true;
            }
            b2 = false;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return b2;
    }
    
    @Override
    public final void method76(final int n, final int n2) {
        try {
            this.anInt5349 = n * this.aClass162_5344.anInt1263;
            if (this.anInt5349 > this.anInt5345) {
                int n3 = 8;
                int n4;
                if (!this.aBoolean5347) {
                    n4 = 1;
                }
                else {
                    n4 = 0;
                    n3 |= 0x200;
                }
                if (this.anIDirect3DIndexBuffer5350 != null) {
                    this.anIDirect3DIndexBuffer5350.b(n2 - 20747);
                }
                this.anIDirect3DIndexBuffer5350 = this.aHa_Sub3_Sub1_5346.anIDirect3DDevice6098.a(this.anInt5349, n3, (this.aClass162_5344 == Class162.aClass162_1267) ? 101 : 102, n4, this.anIDirect3DIndexBuffer5350);
                this.anInt5345 = this.anInt5349;
            }
            if (n2 != 20779) {
                this.method78(true, 102);
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    Class125(final ha_Sub3_Sub1 aHa_Sub3_Sub1_5346, final Class162 aClass162_5344, final boolean aBoolean5347) {
        this.aBoolean5348 = false;
        try {
            this.aClass162_5344 = aClass162_5344;
            this.aHa_Sub3_Sub1_5346 = aHa_Sub3_Sub1_5346;
            this.aBoolean5347 = aBoolean5347;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}
