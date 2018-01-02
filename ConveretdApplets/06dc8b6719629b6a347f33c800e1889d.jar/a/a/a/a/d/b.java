// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a.d;

import a.a.a.a.a.d;

public abstract class b extends a
{
    public void Add_hotspot(final String s) {
        if (super.B != null) {
            ((d)super.B).new(s);
        }
    }
    
    public float GetPitch() {
        if (super.B == null) {
            return 0.0f;
        }
        final float[] array = new float[3];
        ((d)super.B).if(array);
        return array[0] * 180.0f / 3.1415927f;
    }
    
    public float GetYaw() {
        if (super.B == null) {
            return 0.0f;
        }
        final float[] array = new float[3];
        ((d)super.B).if(array);
        return array[1] * 180.0f / 3.1415927f;
    }
    
    public float GetZoom() {
        if (super.B == null) {
            return 1.0f;
        }
        return ((d)super.B).z() * 180.0f / 3.1415927f;
    }
    
    public void Pause() {
        if (super.B != null) {
            ((d)super.B).w();
        }
    }
    
    public void RotateLeft() {
        if (super.B != null) {
            ((d)super.B).x();
        }
    }
    
    public void RotateRight() {
        if (super.B != null) {
            ((d)super.B).c();
        }
    }
    
    public void SetView(float n, float n2, float n3, final long n4) {
        final d d = (d)super.B;
        final float[] array = new float[3];
        if (d == null) {
            return;
        }
        if (!d.D()) {
            d.w();
        }
        final float a = d.A();
        d.a(1.0E-4f, false);
        n *= 0.017453292f;
        n2 *= 0.017453292f;
        n3 *= 0.017453292f;
        if (n4 < 1L) {
            array[0] = n;
            array[1] = n2;
            array[2] = 0.0f;
            d.a(array);
            d.a(n3);
            d.g();
        }
        else {
            final float[] array2 = new float[3];
            d.if(array2);
            final float z = d.z();
            long n6;
            for (long n5 = n6 = System.currentTimeMillis(); n6 - n5 < n4; n6 = System.currentTimeMillis()) {
                final float n7 = (n6 - n5) / n4;
                array[0] = array2[0] + (n - array2[0]) * n7;
                array[1] = array2[1] + (n2 - array2[1]) * n7;
                array[2] = 0.0f;
                final float n8 = z + (n3 - z) * n7;
                d.a(array);
                d.a(n8);
                d.g();
            }
            array[0] = n;
            array[1] = n2;
            array[2] = 0.0f;
            d.a(array);
            d.a(n3);
            d.g();
        }
        d.a(a, false);
    }
    
    public void Set_autospin(final String s) {
        if (super.B != null) {
            ((d)super.B).char(s);
        }
    }
    
    public void Set_file(final String s) {
        super.z.a("param/file", s);
    }
    
    public void Set_initialView(final float n, final float n2, final float n3, final float n4) {
        if (super.B != null) {
            ((d)super.B).a(n, n2, n3, n4);
        }
    }
    
    public void a(final int n, final float n2, final float n3, final float n4, final float n5) {
    }
    
    public void Set_minZoomAngle(final String s) {
        if (super.B != null) {
            ((d)super.B).else(s);
        }
    }
    
    protected String long() {
        return "pano";
    }
}
