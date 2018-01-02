import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// 
// Decompiled by Procyon v0.5.30
// 

public class z extends y
{
    public static final boolean e = false;
    public static final int f = 0;
    public static final int g = 1;
    public static final int h = 2;
    protected float[] i;
    protected float[] j;
    protected int k;
    protected float l;
    protected boolean m;
    protected boolean n;
    protected boolean o;
    protected boolean p;
    protected List q;
    protected long r;
    
    public z(final bk d, final float[] array, final int k) {
        this.i = new float[] { 0.0f, 0.0f, 0.0f, 1.0f };
        this.j = null;
        this.k = 2;
        this.l = 0.20943952f;
        this.m = false;
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = new LinkedList();
        this.r = 0L;
        this.d = d;
        (this.i = bj.a(array))[3] = Math.max(this.i[3], 0.4f);
        this.k = k;
        this.q.add(new Float(30.0f));
    }
    
    boolean a(float[] array) {
        if (this.j == null) {
            this.j = bj.a(array);
        }
        if (!this.d.c(this)) {
            return false;
        }
        if (this.a(array, this.i, new float[] { 1.0E-4f, 1.0E-4f, 1.0E-4f, 1.0E-4f })) {
            array = this.i;
            this.d.b(this);
            return true;
        }
        final long f = f();
        if (this.r != 0L) {
            this.q.add(new Float(f - this.r));
        }
        this.r = f;
        float n;
        for (n = this.i[0] - array[0]; n < 0.0f; n += 6.283185307179586) {}
        while (n > 6.283185307179586) {
            n -= 6.283185307179586;
        }
        float n2;
        for (n2 = this.i[0] - array[0]; n2 < -6.283185307179586; n2 += 6.283185307179586) {}
        while (n2 > 0.0f) {
            n2 -= 6.283185307179586;
        }
        float n3 = 0.0f;
        final float[] array2 = { 0.0f, 0.0f, 0.0f, 1.0f };
        switch (this.k) {
            case 0: {
                n3 = n2;
                break;
            }
            case 1: {
                n3 = n;
                break;
            }
            case 2: {
                n3 = ((Math.abs(n) > Math.abs(n2)) ? n2 : n);
                break;
            }
        }
        final float n4 = this.i[2] - array[2];
        final float n5 = this.i[1] - array[1];
        final float d = this.d();
        if (d <= 0.0f) {
            array = this.i;
            this.d.b(this);
            return true;
        }
        final float e = this.e();
        final float max = Math.max(Math.max(Math.abs(n3), Math.abs(n5)), Math.abs(n4));
        if (max == 0.0f) {
            array2[0] = 0.0f;
        }
        else {
            array2[0] = n3 / max * d * e;
        }
        final float max2 = Math.max(Math.max(Math.abs(n3), Math.abs(n5)), Math.abs(n4));
        if (max2 == 0.0f) {
            array2[1] = 0.0f;
        }
        else {
            array2[1] = n5 / max2 * d * e;
        }
        final float max3 = Math.max(Math.max(Math.abs(n3), Math.abs(n5)), Math.abs(n4));
        if (max3 == 0.0f) {
            array2[2] = 0.0f;
        }
        else {
            array2[2] = n4 / max3 * d * e;
        }
        float n6 = Math.max(Math.max(Math.abs(n3), Math.abs(n5)), Math.abs(n4)) / (d * e);
        if (n6 == 0.0f) {
            n6 = Math.abs(this.i[3] - array[3]) / (d * e);
        }
        final float n7 = (float)Math.exp(Math.log(this.i[3]) - (Math.log(this.i[3]) - Math.log(array[3])) * ((n6 - 1.0f) / n6));
        array2[3] = n7 - array[3];
        if (this.a(array, this.i, array2)) {
            array = this.i;
            this.d.b(this);
            return true;
        }
        if (!this.m) {
            final float[] array3 = array;
            final int n8 = 0;
            array3[n8] += array2[0];
        }
        if (!this.n) {
            final float[] array4 = array;
            final int n9 = 1;
            array4[n9] += array2[1];
        }
        if (!this.o) {
            final float[] array5 = array;
            final int n10 = 2;
            array5[n10] += array2[2];
        }
        if (!this.p) {
            array[3] = n7;
        }
        return true;
    }
    
    public float d() {
        return this.l;
    }
    
    public final boolean a(final float[] array, final float[] array2, final float[] array3) {
        final float n = array3[0];
        final float n2 = array3[1];
        final float n3 = array3[2];
        final float n4 = array3[3];
        float abs = Math.abs(array[0] - array2[0]);
        if (abs <= 1.0E-4f) {
            abs = 0.0f;
        }
        float abs2 = Math.abs(array[1] - array2[1]);
        if (abs2 <= 1.0E-4f) {
            abs2 = 0.0f;
        }
        float abs3 = Math.abs(array[2] - array2[2]);
        if (abs3 <= 1.0E-4f) {
            abs3 = 0.0f;
        }
        float abs4 = Math.abs(array[3] - array2[3]);
        if (abs4 <= 1.0E-4f) {
            abs4 = 0.0f;
        }
        if (abs <= Math.abs(n)) {
            this.m = true;
        }
        if (abs2 <= Math.abs(n2)) {
            this.n = true;
        }
        if (abs3 <= Math.abs(n3)) {
            this.o = true;
        }
        if (abs4 <= Math.abs(n4)) {
            this.p = true;
        }
        return this.m && this.n && this.o && this.p;
    }
    
    protected float e() {
        float n = 0.0f;
        final Iterator<Float> iterator = this.q.iterator();
        while (iterator.hasNext()) {
            n += iterator.next();
        }
        return n / this.q.size() * 0.001f;
    }
    
    public static long f() {
        return new Date().getTime();
    }
}
